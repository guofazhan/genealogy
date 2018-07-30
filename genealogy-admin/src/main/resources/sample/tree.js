function init() {
    // for conciseness in defining templates
    var $ = go.GraphObject.make;
    myDiagram =
        // must be the ID or reference to div
        $(go.Diagram, "myDiagramDiv",
            {
                initialContentAlignment: go.Spot.Center,
                // users can select only one part at a time
                maxSelectionCount: 1,
                // make sure users can only create trees
                validCycle: go.Diagram.CycleDestinationTree,
                // allow double-click in background to create a new node
                "clickCreatingTool.archetypeNodeData": {},
                // customize the data for the new node
                "clickCreatingTool.insertPart": function(loc) {
                    this.archetypeNodeData = {
                        key: getNextKey(), // assign the key based on the number of nodes
                        name: "(new person)",
                        title: ""
                    };
                    return go.ClickCreatingTool.prototype.insertPart.call(this, loc);
                },
                layout:
                    $(go.TreeLayout,
                        {
                            treeStyle: go.TreeLayout.StyleLastParents,
                            arrangement: go.TreeLayout.ArrangementHorizontal,
                            // properties for most of the tree:
                            angle: 90,
                            layerSpacing: 35,
                            // properties for the "last parents":
                            alternateAngle: 90,
                            alternateLayerSpacing: 35,
                            alternateAlignment: go.TreeLayout.AlignmentBus,
                            alternateNodeSpacing: 20
                        }),
                "undoManager.isEnabled": true // enable undo & redo
            });

    // when the document is modified, add a "*" to the title and enable the "Save" button
    myDiagram.addDiagramListener("Modified", function(e) {
        var button = document.getElementById("SaveButton");
        if (button) button.disabled = !myDiagram.isModified;
        var idx = document.title.indexOf("*");
        if (myDiagram.isModified) {
            if (idx < 0) document.title += "*";
        } else {
            if (idx >= 0) document.title = document.title.substr(0, idx);
        }
    });

    // manage boss info manually when a node or link is deleted from the diagram
    myDiagram.addDiagramListener("SelectionDeleting", function(e) {
        // e.subject is the myDiagram.selection collection,
        var part = e.subject.first();
        // so we'll get the first since we know we only have one selection
        myDiagram.startTransaction("clear boss");
        if (part instanceof go.Node) {
            // find all child nodes
            var it = part.findTreeChildrenNodes();
            // now iterate through them and clear out the boss information
            while(it.next()) {
                var child = it.value;
                // since the boss TextBlock is named, we can access it by name
                var bossText = child.findObject("boss");
                if (bossText === null) return;
                bossText.text = "";
            }
        } else if (part instanceof go.Link) {
            var child = part.toNode;
            // since the boss TextBlock is named, we can access it by name
            var bossText = child.findObject("boss");
            if (bossText === null) return;
            bossText.text = "";
        }
        myDiagram.commitTransaction("clear boss");
    });

    var levelColors = ["#AC193D", "#2672EC", "#8C0095", "#5133AB",
        "#008299", "#D24726", "#008A00", "#094AB2"];

    // override TreeLayout.commitNodes to also modify the background brush based on the tree depth level
    myDiagram.layout.commitNodes = function() {
        // do the standard behavior
        go.TreeLayout.prototype.commitNodes.call(myDiagram.layout);
        // then go through all of the vertexes and set their corresponding node's Shape.fill
        // to a brush dependent on the TreeVertex.level value
        myDiagram.layout.network.vertexes.each(function(v) {
            if (v.node) {
                var level = v.level % (levelColors.length);
                var color = levelColors[level];
                var shape = v.node.findObject("SHAPE");
                if (shape) shape.fill = $(go.Brush, "Linear", { 0: color, 1: go.Brush.lightenBy(color, 0.05), start: go.Spot.Left, end: go.Spot.Right });
            }
        });
    };
    // This function is used to find a suitable ID when modifying/creating nodes.
    // We used the counter combined with findNodeDataForKey to ensure uniqueness.
    function getNextKey() {
        var key = nodeIdCounter;
        while (myDiagram.model.findNodeDataForKey(key) !== null) {
            key = nodeIdCounter--;
        }
        return key;
    }
    // use a sequence to guarantee key uniqueness as we add/remove/modify nodes
    var nodeIdCounter = -1;

    /**
     * 节点双击事件
     * @param e
     * @param obj
     */
    function nodeDoubleClick(e, obj) {
        var clicked = obj.part;
        if (clicked !== null) {
            var thisemp = clicked.data;
            myDiagram.startTransaction("add employee");
            var newemp = { key: getNextKey(), name: "(new person)", title: "", parent: thisemp.key };
            myDiagram.model.addNodeData(newemp);
            myDiagram.commitTransaction("add employee");
        }
    }

    //设置节点模板样式
    // define the Node template
    myDiagram.nodeTemplate =
        $(go.Node, "Auto",
            //双击事件
            { doubleClick: nodeDoubleClick },
            { // handle dragging a Node onto a Node to (maybe) change the reporting relationship
                mouseDragEnter: function (e, node, prev) {
                    var diagram = node.diagram;
                    var selnode = diagram.selection.first();
                    if (!mayWorkFor(selnode, node)) return;
                    var shape = node.findObject("SHAPE");
                    if (shape) {
                        // remember the original brush
                        shape._prevFill = shape.fill;
                        shape.fill = "darkred";
                    }
                },
                mouseDragLeave: function (e, node, next) {
                    var shape = node.findObject("SHAPE");
                    if (shape && shape._prevFill) {
                        // restore the original brush
                        shape.fill = shape._prevFill;
                    }
                },
                mouseDrop: function (e, node) {
                    var diagram = node.diagram;
                    // assume just one Node in selection
                    var selnode = diagram.selection.first();
                    if (mayWorkFor(selnode, node)) {
                        // find any existing link into the selected node
                        var link = selnode.findTreeParentLink();
                        if (link !== null) {
                            // reconnect any existing link
                            link.fromNode = node;
                        } else {  // else create a new link
                            diagram.toolManager.linkingTool.insertLink(node, node.port, selnode, selnode.port);
                        }
                    }
                }
            },
            // for sorting, have the Node.text be the data.name
            new go.Binding("text", "name"),
            // bind the Part.layerName to control the Node's layer depending on whether it isSelected
            new go.Binding("layerName", "isSelected", function(sel) { return sel ? "Foreground" : ""; }).ofObject(),
            // define the node's outer shape
            $(go.Shape, "Rectangle",
                {
                    name: "SHAPE", fill: "white", stroke: null,
                    // set the port properties:
                    portId: "", fromLinkable: true, toLinkable: true, cursor: "pointer"
                }),
            $(go.Panel, "Horizontal",
                $(go.Picture,
                    {
                        name: "Picture",
                        desiredSize: new go.Size(39, 50),
                        margin: new go.Margin(6, 8, 6, 10),
                    },
                    new go.Binding("source", "key", findHeadShot)),
                // define the panel where the text will appear
                $(go.Panel, "Table",
                    {
                        maxSize: new go.Size(150, 999),
                        margin: new go.Margin(6, 10, 0, 3),
                        defaultAlignment: go.Spot.Left
                    },
                    $(go.RowColumnDefinition, { column: 2, width: 4 }),
                    $(go.TextBlock, textStyle(),  // the name
                        {
                            row: 0, column: 0, columnSpan: 5,
                            font: "12pt Segoe UI,sans-serif",
                            editable: true, isMultiline: false,
                            minSize: new go.Size(10, 16)
                        },
                        new go.Binding("text", "name").makeTwoWay()),
                    $(go.TextBlock, "标题: ", textStyle(), { row: 1, column: 0 }),
                    $(go.TextBlock, textStyle(),
                        {
                            row: 1, column: 1, columnSpan: 4,
                            editable: true, isMultiline: false,
                            minSize: new go.Size(10, 14),
                            margin: new go.Margin(0, 0, 0, 3)
                        },
                        new go.Binding("text", "title").makeTwoWay()),
                    $(go.TextBlock, textStyle(), { row: 2, column: 0 }, new go.Binding("text", "key", function(v) {return "身份证: " + v;})),
                    // we include a name so we can access this TextBlock when deleting Nodes/Links
                    $(go.TextBlock, textStyle(), { name: "boss", row: 2, column: 3, },
                        new go.Binding("text", "parent", function(v) {return "Boss: " + v;})),
                    $(go.TextBlock, textStyle(),  // the comments
                        {
                            row: 3, column: 0, columnSpan: 5,
                            font: "italic 9pt sans-serif",
                            wrap: go.TextBlock.WrapFit,
                            editable: true,  // by default newlines are allowed
                            minSize: new go.Size(10, 14)
                        },
                        new go.Binding("text", "comments").makeTwoWay())
                )  // end Table Panel
            ) // end Horizontal Panel
        );  // end Node

    // the context menu allows users to make a position vacant,
    // remove a role and reassign the subtree, or remove a department
    //节点上绑定事件
    myDiagram.nodeTemplate.contextMenu =
        $(go.Adornment, "Vertical",
            $("ContextMenuButton",
                $(go.TextBlock, "清空当前节点数据"),
                {
                    click: function(e, obj) {
                        var node = obj.part.adornedPart;
                        if (node !== null) {
                            var thisemp = node.data;
                            myDiagram.startTransaction("vacate");
                            // update the key, name, and comments
                            myDiagram.model.setKeyForNodeData(thisemp, getNextKey());
                            myDiagram.model.setDataProperty(thisemp, "name", "(Vacant)");
                            myDiagram.model.setDataProperty(thisemp, "comments", "");
                            myDiagram.commitTransaction("vacate");
                        }
                    }
                }
            ),
            $("ContextMenuButton",
                $(go.TextBlock, "删除当前节点"),
                {
                    click: function(e, obj) {
                        // reparent the subtree to this node's boss, then remove the node
                        var node = obj.part.adornedPart;
                        if (node !== null) {
                            myDiagram.startTransaction("reparent remove");
                            var chl = node.findTreeChildrenNodes();
                            // iterate through the children and set their parent key to our selected node's parent key
                            while(chl.next()) {
                                var emp = chl.value;
                                myDiagram.model.setParentKeyForNodeData(emp.data, node.findTreeParentNode().data.key);
                            }
                            // and now remove the selected node itself
                            myDiagram.model.removeNodeData(node.data);
                            myDiagram.commitTransaction("reparent remove");
                        }
                    }
                }
            ),
            $("ContextMenuButton",
                $(go.TextBlock, "移除节点（包含子节点）"),
                {
                    click: function(e, obj) {
                        // remove the whole subtree, including the node itself
                        var node = obj.part.adornedPart;
                        if (node !== null) {
                            myDiagram.startTransaction("remove dept");
                            myDiagram.removeParts(node.findTreeParts());
                            myDiagram.commitTransaction("remove dept");
                        }
                    }
                }
            ),
            $("ContextMenuButton",
                $(go.TextBlock, "添加子节点"),
                {
                    click: function(e, obj) {
                        // remove the whole subtree, including the node itself
                        var node = obj.part.adornedPart;
                        if (node !== null) {
                            var thisemp = obj.part.data;
                            myDiagram.startTransaction("add employee");
                            var newemp = { key: getNextKey(), name: "(new person)", title: "", parent: node.key };
                            myDiagram.model.addNodeData(newemp);
                            myDiagram.commitTransaction("add employee");
                        }
                    }
                }
            )
        );
    // define the Link template
    myDiagram.linkTemplate =
        $(go.Link, go.Link.Orthogonal, { corner: 5, relinkableFrom: true, relinkableTo: true },
            // the link shape
            $(go.Shape, { strokeWidth: 4, stroke: "#00a4a4" })
        );

    //加载json数据
    load();
    // support editing the properties of the selected person in HTML
    if (window.Inspector) myInspector = new Inspector("myInspector", myDiagram,
        {
            properties: {
                "key": { readOnly: true },
                "comments": {}
            }
        });
}


// this is used to determine feedback during drags
function mayWorkFor(node1, node2) {
    // must be a Node
    if (!(node1 instanceof go.Node)) return false;
    // cannot work for yourself
    if (node1 === node2) return false;
    // cannot work for someone who works for you
    if (node2.isInTreeOf(node1)) return false;
    return true;
}

//文本样式
function textStyle() {
    return { font: "9pt  Segoe UI,sans-serif", stroke: "white" };
}

//根据KEY获取图标信息
function findHeadShot(key) {
    // There are only 16 images on the server
    if (key < 0 || key > 16) return "images/HSnopic.png";
    return "images/HS" + key + ".png"
}

function load() {
    myDiagram.model = go.Model.fromJson(getJson());
}

function getJson() {
    return {
        "class": "go.TreeModel",
        "nodeDataArray": [
            {"key":1, "name":"郭发展", "title":"CEO"},
            {"key":2, "name":"Luke Warm", "title":"VP Marketing/Sales", "parent":1},
            {"key":3, "name":"Meg Meehan Hoffa", "title":"Sales", "parent":2},
            {"key":4, "name":"Peggy Flaming", "title":"VP Engineering", "parent":1},
            {"key":5, "name":"Saul Wellingood", "title":"Manufacturing", "parent":4},
            {"key":6, "name":"Al Ligori", "title":"Marketing", "parent":2},
            {"key":7, "name":"Dot Stubadd", "title":"Sales Rep", "parent":3},
            {"key":8, "name":"Les Ismore", "title":"Project Mgr", "parent":5},
            {"key":9, "name":"April Lynn Parris", "title":"Events Mgr", "parent":6},
            {"key":10,"name":"Xavier Breath", "title":"Engineering", "parent":4},
            {"key":11, "name":"Anita Hammer", "title":"Process", "parent":5},
            {"key":12, "name":"Billy Aiken", "title":"Software", "parent":10},
            {"key":13, "name":"Stan Wellback", "title":"Testing", "parent":10},
            {"key":14, "name":"Marge Innovera", "title":"Hardware", "parent":10},
            {"key":15, "name":"Evan Elpus", "title":"Quality", "parent":5},
            {"key":16, "name":"Lotta B. Essen", "title":"Sales Rep", "parent":3}
        ]
    }
}