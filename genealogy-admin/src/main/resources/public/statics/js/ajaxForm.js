/**
 * ajax 表单提交，组合了表单验证插件以及jquery.form插件
 * 当前js 依赖 jquery.js ,jquery.form.js,jquery.validation.js
 * @param {Object} a
 * @param {Object} b
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
!function(a, b) {
	var AjaxForm = {};

	/**
	 * 校验表单信息
	 * @param {Object} data
	 * @param {Object} form
	 * @param {Object} options
	 * @return {TypeName} 
	 */
	AjaxForm.validationRequest = function(data, form, options) {
		return b(form).valid();
	}

	/**
	 * 请求异常信息
	 * @param {Object} XMLHttpRequest
	 * @param {Object} textStatus
	 * @param {Object} errorThrown
	 */
	AjaxForm.ajaxError = function(XMLHttpRequest, textStatus, errorThrown) {
		alert('Ooops!Encountered error while connecting to the server.There might be something wrong with your network.Please check your network connection!');
	}

	/**
	 * 数据初始化信息
	 * @param {Object} obj
	 * @param {Object} opts
	 * @return {TypeName} 
	 */
	AjaxForm.init = function(obj, opts) {
		var isValidate = opts.validate.isValidate, validate = null;
		if (isValidate)
			validate = b(obj).validate(opts.validate.defaults);
		//设置值
		b(obj).attr("action", opts.url);
		b(opts.button).attr('disabled', true);
		//返回参数信息
		return [
				{
					dataType : 'json',
					timeout : 60000,
					beforeSubmit : function(data, form, options) {
						//当需要校验时，进入校验表单
						var k = isValidate ? AjaxForm.validationRequest(data,
								form, options) : true;
						if (!k) {
							b(opts.button).removeAttr('disabled');
						}
						return k;
					},
					success : function(data, textStatus) {
						b(opts.button).removeAttr('disabled');
						opts.callback(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						b(opts.button).removeAttr('disabled');
						AjaxForm.ajaxError(XMLHttpRequest, textStatus,
								errorThrown);
					}
				}, validate ]
	}

	/**
	 * 默认的校验信息
	 * @param {Object} data
	 */
	AjaxForm.default_validate = {
		isValidate : true,
		defaults : {
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : false,
			ignore : "",
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
		}
	};

	/**
	 * 默认信息
	 * @param {Object} obj
	 * @param {Object} opts
	 */
	AjaxForm.defaults = {
		url : '',
		button : '',
		validate : AjaxForm.default_validate,
		callback : function(data) {
			if (data.status == 0) {
			} else {
				alert(data.msg);
			}
		}
	};

	/**
	 * 表单提交
	 * @param {Object} obj
	 * @param {Object} opts
	 */
	AjaxForm.ajaxForm = function(obj, opts) {
		opts.validate = b
				.extend(true, AjaxForm.default_validate, opts.validate);
		opts = b.extend(true, AjaxForm.defaults, opts);
		if (!AjaxForm.vparams(opts)) {
			return;
		}
		//初始化数据信息
		var result = AjaxForm.init(obj, opts);
		//提交表单信息
		b(obj).ajaxSubmit(result[0]);
	}

	/**
	 * 参数校验
	 * @param {Object} opts
	 */
	AjaxForm.vparams = function(opts) {
		if (b.trim(opts) == '')
			return false;
		return true;
	}

	/**
	 * 扩展到jquery对象上
	 * @memberOf {TypeName} 
	 */
	b.fn.extend( {
		myAjaxForm : function(opts) {
			AjaxForm.ajaxForm(b(this), opts);
		}
	});
}(window, $)
///调用样例
$("#form").myAjaxForm( {
	url : '',
	validate : {
		isValidate : false
	}
});
