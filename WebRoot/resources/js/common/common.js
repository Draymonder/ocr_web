var CommonJS = {
	/**
	 * 获取项目路径
	 * 
	 * @returns
	 */
	getContextPath : function() {
		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0, index + 1);
		return result;
	},

	/**
	 * @Title: isNull
	 * @Description: 判空校验
	 * @param
	 * @param str
	 * @param
	 * @returns {Boolean} 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	isNull : function(str) {
		if (str == null || str == undefined || str.length <= 0) {
			return true;
		}
		return false;
	},
	/**
	 * 验证手机号码
	 * 
	 * @param str
	 * @return
	 */
	isPhone : function(str) {
		var reg = /^1\d{10}$/;
		return reg.test(str);
	},
	/**
	 * 判断是否含有汉字
	 * 
	 * @param str
	 * @returns
	 */
	isChinese : function(str) {
		if (escape(str).indexOf("%u") < 0) {
			return false;// 不包含中文
		} else {
			return true;// 包含中文
		}
	},
	/**
	 * 判断是否为正整数
	 * 
	 * @param str
	 * @returns
	 */
	isPositiveNum : function(str) {
		var regExp = /^[0-9]*[1-9][0-9]*$/;
		return regExp.test(str);
	},
	/**
	 * 日期比较大小
	 * 
	 * @param beginDate
	 * @param endDate
	 * @returns {String}
	 */
	compareDate : function(beginDate, endDate) {
		var flag = "0";
		var beginDates = beginDate.split("-");
		var endDates = endDate.split("-");
		var beginTime = new Date(beginDates[0], beginDates[1], beginDates[2])
				.getTime();
		var endTime = new Date(endDates[0], endDates[1], endDates[2]).getTime();
		if (beginTime > endTime) {
			flag = "1";// 开始时间大于结束时间错误
			return flag;
		}
		var myDate = new Date();
		var year = myDate.getFullYear(); // 获取完整的年份(4位,1970-????)
		var month = myDate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
		var day = myDate.getDate(); // 获取当前日(1-31)
		var nowTime = new Date(year, month, day).getTime();
		if (nowTime > endTime) {
			flag = "2";// 当前时间大于结束时间错误
			return flag;
		}
		return flag;
	},
	getStringLength : function(val) {
		var len = 0;
		for ( var i = 0; i < val.length; i++) {
			var length = val.charCodeAt(i);
			if (length >= 0 && length <= 128) {
				len += 1;
			} else {
				len += 2;
			}
		}
		return len;
	},
	getCityCode : function(name) {
		var areaCode = "";
		if (name == "南京市") {
			areaCode = 14;
		} else if (name == "苏州市") {
			areaCode = 11;
		} else if (name == "无锡市") {
			areaCode = 19;
		} else if (name == "常州市") {
			areaCode = 17;
		} else if (name == "镇江市") {
			areaCode = 18;
		} else if (name == "扬州市") {
			areaCode = 23;
		} else if (name == "泰州市") {
			areaCode = 21;
		} else if (name == "南通市") {
			areaCode = 20;
		} else if (name == "淮安市") {
			areaCode = 12;
		} else if (name == "连云港市") {
			areaCode = 15;
		} else if (name == "盐城市") {
			areaCode = 22;
		} else if (name == "徐州市") {
			areaCode = 16;
		} else if (name == "宿迁市") {
			areaCode = 13;
		} else if (name == "江苏省") {
			areaCode = 99;
		} else if (name == "系统") {
			areaCode = 0;
		}
		return areaCode;
	},
	getBrandCode : function(name) {
		var bandsCode = "";
		if (name == "全部") {
			bandsCode = 'ALL';
		} else if (name == "全球通") {
			bandsCode = 'QQT';
		} else if (name == "动感地带") {
			bandsCode = 'DGDD';
		} else if (name == "神州行") {
			bandsCode = 'SZX';
		}
		return bandsCode;
	},
	getUserLevelCode : function(name) {
		var userLevelCode = "";
		if (name == "系统管理员") {
			userLevelCode = '0';
		} else if (name == "地市操作员") {
			userLevelCode = '1';
		} else if (name == "省级操作员") {
			userLevelCode = '2';
		} else if (name == "地市审核员") {
			userLevelCode = '3';
		} else if (name == "省级审核员") {
			userLevelCode = '4';
		}
		return userLevelCode;
	},
	phoneNumberCheck : function(phoneNum,contactWay) {
		// 手机号码非空 校验
		if (phoneNum == "" || phoneNum == null) {
			showDialog1(contactWay+"不能为空");
			return false;
		}
		// 手机号码正整数校验
		if (!CommonJS.isPositiveNum(phoneNum)) {
			showDialog1(contactWay+"只能填写正整数");
			return false;
		}
		// 手机号码空格校验
		if (/\s/.test(phoneNum)) {
			showDialog1(contactWay+"格式不合法,请重新输入");
			return false;
		}
		// 手机号码位数校验
		if (phoneNum.length != 11) {
			showDialog1("请输入11位"+contactWay);
			return false;
		}
		// 手机号码格式校验
		if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(phoneNum))) {
			showDialog1(contactWay+"格式不合法,请重新输入");
			return false;
		}
		return true;
	},
	getUseStateCode : function(state) {
		var stateCode = "";
		if (state == "已启用") {
			stateCode = "0";
		} else if (state == "已停用") {
			stateCode = "1";
		}
		return stateCode;
	},
	uploadImage : function(channel, receiveOrUse) {
		var path = CommonJS.getContextPath();
		$.ajaxFileUpload({
			type : "POST",
			url : path + "/fileUpLoad/uploadImage",
			data : {
				"channel" : channel
			},// 要传到后台的参数，没有可以不写
			secureuri : false,// 是否启用安全提交，默认为false
			fileElementId : channel + 'UploadImage',// 文件选择框的id属性
			dataType : 'text',// 服务器返回的格式
			async : false,
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					if (channel == "obsh") {
						if (receiveOrUse == "1") {
							$("#fObshExplainImg").val(result.filePath);
						} else if (receiveOrUse == "2") {
							$("#fObshImageName").val(result.filePath);
						}
					} else if (channel == "wap") {
						if (receiveOrUse == "1") {
							$("#fWapExplainImg").val(result.filePath);
						} else if (receiveOrUse == "2") {
							$("#fWapImageName").val(result.filePath);
						}
					}
					showDialog1("图片已上传成功");
				} else {
					showDialog1(result.msg);
				}
			},
			error : function(result, status, e) {
				showDialog1("网络繁忙");
			}
		});
	},
	uploadFile : function(fCardNumber) {
		var path = CommonJS.getContextPath();
		$.ajaxFileUpload({
			type : "POST",
			url : path + "/fileUpLoad/uploadPrize",
			data : {
				"fCardNumber" : fCardNumber
			},// 要传到后台的参数，没有可以不写
			secureuri : false,// 是否启用安全提交，默认为false
			fileElementId : 'multipleName',// 文件选择框的id属性
			dataType : 'text',// 服务器返回的格式
			async : false,
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					$("#fileName").val(result.fileName);
					$("#fileNameList").val(result.fileNameList);
					$("#filePathList").val(result.filePathList);
					showDialog1("奖品码文件"+result.fileName+"已上传成功");
				} else {
					showDialog1(result.msg);
				}
			},
			error : function(result, status, e) {
				showDialog1("网络繁忙");
			}
		});
	},
	uploadThemeImage : function(elementId) {
		var path = CommonJS.getContextPath();
		$.ajaxFileUpload({
			type : "POST",
			url : path + "/fileUpLoad/uploadThemeImage",
			data : {
			},// 要传到后台的参数，没有可以不写
			secureuri : false,// 是否启用安全提交，默认为false
			fileElementId : elementId,// 文件选择框的id属性
			dataType : 'text',// 服务器返回的格式
			async : false,
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					if(elementId == "cardShowImg"){
						$("#fCardShowImg").val(result.filePath);
					}else if(elementId == "cardShowButton"){
						$("#fCardShowButton").val(result.filePath);
					}
					showDialog1("图片已上传成功");
				} else {
					showDialog1(result.msg);
				}
			},
			error : function(result, status, e) {
				showDialog1("网络繁忙");
			}
		});
	}
}
// 调用 var time1 = new Date().Format("yyyy-MM-dd");
// var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
// 调用date.pattern("yyyy-MM-dd hh:mm:ss")
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
