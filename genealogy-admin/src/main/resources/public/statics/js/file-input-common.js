!function(a, b) {
	var defaults = {
		uploadUrl : '',
		maxFileCount : 4,
		uploadExtraData : {},
		allowedFileExtensions : [ 'jpg', 'png', 'gif','rar', 'zip'],
		showUpload : false,
		showCaption : true,
		showPreview : true,
		showClose : false,
		showUploadedThumbs : false,
		overwriteInitial : false,
		initialPreviewCount : 1,
		browseClass : "btn btn-primary",
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		maxFileSize : 0, //文件的大小，0为不限制
		enctype : 'multipart/form-data',
		msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		dropZoneEnabled : false, //是否显示拖拽区域
		validateInitialCount : true,
		minFileCount : 4,
		callback : function(response) {
		},
		fileActionSettings : {
			removeIcon : '<i class="glyphicon glyphicon-trash text-danger"></i>',
			removeClass : 'btn btn-xs btn-default',
			removeTitle : 'Remove file',
			uploadIcon : '<i class="glyphicon glyphicon-upload text-info"></i>',
			uploadClass : 'btn btn-xs btn-default',
			uploadTitle : 'Upload file',
			indicatorNew : '',
			indicatorSuccess : '<i class="glyphicon glyphicon-ok-sign file-icon-large text-success"></i>',
			indicatorError : '<i class="glyphicon glyphicon-exclamation-sign text-danger"></i>',
			indicatorLoading : '<i class="glyphicon glyphicon-hand-up text-muted"></i>',
			indicatorNewTitle : 'Not uploaded yet',
			indicatorSuccessTitle : 'Uploaded',
			indicatorErrorTitle : 'Upload Error',
			indicatorLoadingTitle : 'Uploading ...'
		}
	};

	FileMyInput = {};
	FileMyInput.init = function(obj, opts) {
		opts = b.extend(false, defaults, opts);
		b(obj).fileinput(opts);
		b(obj).on("fileuploaded", function(event, data, previewId, index) {
			if (data.response && data.response.status == 0) {
				opts.callback(data.response);
			} else {
				alert("Error");
			}
		})
	}

	/**
	 * 扩展到jquery对象上
	 * @memberOf {TypeName} 
	 */
	b.fn.extend( {
		fileUpload : function(opts) {
			FileMyInput.init(b(this), opts);
		}
	});
}(window, $);