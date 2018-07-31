var prefix = CTX_URL +"/modules/family/tree/"

$(document).ready(function () {
   init();
});


function init() {
    // for conciseness in defining templates
    var GO = go.GraphObject.make;
    myDiagram =
        // must be the ID or reference to div
        GO(go.Diagram, "myDiagramDiv",
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
                    GO(go.TreeLayout,
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
                if (shape) shape.fill = GO(go.Brush, "Linear", { 0: color, 1: go.Brush.lightenBy(color, 0.05), start: go.Spot.Left, end: go.Spot.Right });
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
            var newemp = { key: getNextKey(), name: "(new person)", card: "",line:"", parent: thisemp.key };
            myDiagram.model.addNodeData(newemp);
            myDiagram.commitTransaction("add employee");
        }
    }

    //设置节点模板样式
    // define the Node template
    myDiagram.nodeTemplate =
        GO(go.Node, "Auto",
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
            GO(go.Shape, "Rectangle",
                {
                    name: "SHAPE", fill: "white", stroke: null,
                    // set the port properties:
                    portId: "", fromLinkable: true, toLinkable: true, cursor: "pointer"
                }),

            GO(go.Panel, "Horizontal",
                GO(go.Picture,
                    {
                        name: "Picture",
                        desiredSize: new go.Size(39, 50),
                        margin: new go.Margin(6, 8, 6, 10),
                    },
                    new go.Binding("source", "key", findHeadShot)),
                // define the panel where the text will appear
                GO(go.Panel, "Table",
                    {
                        maxSize: new go.Size(150, 999),
                        margin: new go.Margin(6, 10, 0, 3),
                        defaultAlignment: go.Spot.Left
                    },
                    GO(go.RowColumnDefinition, { column: 2, width: 4 }),
                    //设置姓名样式
                    GO(go.TextBlock, textStyle(),  // the name
                        {
                            row: 0, column: 0, columnSpan: 5,
                            font: "12pt Segoe UI,sans-serif",
                            editable: true, isMultiline: false,
                            minSize: new go.Size(10, 16)
                        },
                        new go.Binding("text", "name").makeTwoWay()),

                    //设置用户户主关系
                    GO(go.TextBlock, textStyle(), { row: 2, column: 0 }, new go.Binding("text", "line", function(v) {return "关系: " + v;})),
                    //设置身份证样式
                    GO(go.TextBlock, textStyle(), { row: 3, column: 0 }, new go.Binding("text", "card", function(v) {return "身份证: " + v;})),

                )  // end Table Panel
            ) // end Horizontal Panel
        );  // end Node

    // the context menu allows users to make a position vacant,
    // remove a role and reassign the subtree, or remove a department
    //节点上绑定事件
    myDiagram.nodeTemplate.contextMenu =
        GO(go.Adornment, "Vertical",
            GO("ContextMenuButton",
                GO(go.TextBlock, "清空当前节点数据"),
                {
                    click: function(e, obj) {
                        var node = obj.part.adornedPart;
                        if (node !== null) {
                            var thisemp = node.data;
                            myDiagram.startTransaction("vacate");
                            // update the key, name, and comments
                            myDiagram.model.setKeyForNodeData(thisemp, getNextKey());
                            myDiagram.model.setDataProperty(thisemp, "name", "(Vacant)");
                            myDiagram.model.setDataProperty(thisemp, "card", "");
                            myDiagram.model.setDataProperty(thisemp, "line", "");
                            myDiagram.commitTransaction("vacate");
                        }
                    }
                }
            ),
            GO("ContextMenuButton",
                GO(go.TextBlock, "删除当前节点"),
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
            GO("ContextMenuButton",
                GO(go.TextBlock, "移除节点（包含子节点）"),
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
            GO("ContextMenuButton",
                GO(go.TextBlock, "添加子节点"),
                {
                    click: function(e, obj) {
                        // remove the whole subtree, including the node itself
                        var node = obj.part.adornedPart;
                        if (node !== null) {
                            var thisemp = obj.part.data;
                            myDiagram.startTransaction("add employee");
                            var newemp = { key: getNextKey(), name: "(new person)", card: "",line:"",parent: node.key };
                            myDiagram.model.addNodeData(newemp);
                            myDiagram.commitTransaction("add employee");
                        }
                    }
                }
            )
        );
    // define the Link template
    myDiagram.linkTemplate =
        GO(go.Link, go.Link.Orthogonal, { corner: 5, relinkableFrom: true, relinkableTo: true },
            // the link shape
            GO(go.Shape, { strokeWidth: 4, stroke: "#00a4a4" })
        );

    //加载json数据
    load();
    // support editing the properties of the selected person in HTML
    if (window.Inspector) myInspector = new Inspector("myInspector", myDiagram,
        {
            properties: {
                "key": { readOnly: true }
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
    if (key < 0 || key > 16) return prefix+"/images/HSnopic.png";
    return  prefix+"/images/HS" + key + ".png"
}

function load() {
    myDiagram.model = go.Model.fromJson(getJson());
}

function getJson() {
    return {
        "class": "go.TreeModel",
        "nodeDataArray": [
            {"key":1, "name":"马X德", "card":"3428251968****4932","line":"户主"},
            {"key":2, "name":"马X西", "card":"3408251991****4930","line":"长子", "parent":1},
            {"key":3, "name":"马X东", "card":"3408252009****4713","line":"次子", "parent":1},
            {"key":4, "name":"马X南", "card":"3408251986****4935","line":"三子", "parent":1},
            {"key":5, "name":"马X北", "card":"3408251961****4918","line":"四子", "parent":1},
            {"key":6, "name":"马X发", "card":"3408251979****4918","line":"孙子", "parent":2},
            {"key":7, "name":"马X白", "card":"3428251956****4913","line":"孙子", "parent":3},
            {"key":8, "name":"马X中", "card":"3408251989****4938","line":"孙子", "parent":4},
            {"key":9, "name":"马X爱", "card":"3428251966****4912","line":"孙子", "parent":5},
            {"key":10, "name":"马加爵", "card":"3428251966****1111","line":"重孙", "parent":6},
        ]
    }
}