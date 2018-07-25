!function(a, b) {
	jQuery.validator.addMethod("isUsername", function(value, element) {
		if (value.length < 6 || value.length > 30) {
			return false;
		} else {
			var regx = /^[a-z0-9_]{6,20}$/i;
			if (!regx.test(value)) {
				return false;
			}
			return true;
		}
	}, "账号中仅能使用以下字符：a-z、A-Z、0-9 以及下划线“_”");
	jQuery.validator.addMethod("isPassword", function(value, element) {
		if (value.length < 6 || value.length > 14) {
			return false;
		} else {
			var regx = /^[a-z0-9\_]{6,14}$/i;
			if (!regx.test(value)) {
				return false;
			}
			return true;
		}
	}, "密码中仅能使用以下字符：a-z、A-Z、0-9 以及下划线“_”");

	jQuery.validator.addMethod("editPassword", function(value, element) {
		if (value.length <= 0) {
			return true;
		}
		if (value.length < 6 || value.length > 14) {
			return false;
		} else {
			var regx = /^[a-z0-9\_]{6,14}$/i;
			if (!regx.test(value)) {
				return false;
			}
			return true;
		}
	}, "密码中仅能使用以下字符：a-z、A-Z、0-9 以及下划线“_”");

	/**
	 * 数据校验
	 * @param {Object} data
	 * @param {Object} form
	 * @param {Object} options
	 * @return {TypeName} 
	 */
	var validationRequest = function(data, form, options) {
		return b(form).valid();
	}

	/**
	 * 默认的校验参数
	 */
	var validationOption = {
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : false,
		ignore : "",
		/**
		 * 默认的错误提示
		 * @param {Object} error
		 * @param {Object} element
		 */
		errorPlacement : function(error, element) {
			element.parent('div').append(error);
		},
		highlight : function(element) {
			b(element).closest('.form-group').addClass('has-error');
		},
		success : function(label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		}
	};

	/**
	 * 提交选项
	 */
	var submitOption = {
		url : '',
		bnt : '',
		form : '',
		validate : validationOption,
		callback : function(data) {
			if (data.status == 0) {
			} else {
				alert(data.msg);
			}
		}
	};

	var isEmptyStr = function(str) {
		return b.trim(str) == '';
	}

	var isEmptyObj = function(obj) {
		return b.isEmptyObject(obj);
	}

	var validationParam = function(opts) {
		if (isEmptyStr(opts.url)) {
			alert("表单action不能为空!");
			return false;
		}

		if (isEmptyObj(opts.form)) {
			alert("没有发现提交的表单对象!");
			return false;
		}

		return true;
	}

	/**
	 * 初始化提交数据
	 * @param {Object} opts
	 * @return {TypeName} 
	 */
	var init = function(opts) {
		//设置校验信息
		var validate = b(opts.form).validate(opts.validate);
		//设置值
		b(opts.form).attr("action", opts.url);
		b(opts.bnt).attr('disabled', true);
		return [ {
			dataType : 'json',
			timeout : 60000,
			beforeSubmit : function(data, form, options) {
				var k = validationRequest(data, form, options);
				if (!k) {
					b(opts.bnt).removeAttr('disabled');
				}
				return k;
			},
			success : function(data, textStatus) {
				b(opts.bnt).removeAttr('disabled');
				//validate.resetForm();
			opts.callback(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			b(opts.bnt).removeAttr('disabled');
			//validate.resetForm();
			ajaxError(XMLHttpRequest, textStatus, errorThrown);
		}
		}, validate ]
	}

	/**
	 * 提交事件
	 * @param {Object} opts
	 */
	var ajax_submit = function(opts) {
		opts.validate = b.extend(true, validationOption, opts.validate);
		opts = b.extend(true, submitOption, opts);
		if (!validationParam(opts)) {
			return;
		}
		//初始化提交数据//提交参数
		var result = init(opts);
		//提交
		b(opts.form).ajaxSubmit(result[0]);
	}
	b.ajax_submit = a.ajax_submit = ajax_submit;
}(window, $)
