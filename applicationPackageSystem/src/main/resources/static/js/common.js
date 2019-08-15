/**
 * 项目中的公共参数和通用方法在此文件中定义
 * 新变量请在顶部添加，新方法请在底部添加
 * 
 */

var common = {
	baseUrl: '',// 项目基础地址

	/** 通用方法 **/
	
	
	
	// 通用ajaxGet方法
	AjaxReturnJson: function (url, data, async, type, callBack) {
		$.ajax({
			url: url, // 请求的url地址
			data: data,
			async: async,
			dataType: "json", // 返回的格式为json
			type: type, // 请求的方式
			success: function (jsonData) { // 返回json
				callBack(jsonData)
			}
		});
	},
	
	//生成uuid 可以指定长度和基数
	uuid: function (len,radix){
		var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
		var uuid = [], i;
		  radix = radix || chars.length;
		 
		  if (len) {
		   // Compact form
		   for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
		  } else {
		   // rfc4122, version 4 form
		   var r;
		 
		   // rfc4122 requires these characters
		   uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		   uuid[14] = '4';
		 
		   // Fill in random data. At i==19 set the high bits of clock sequence as
		   // per rfc4122, sec. 4.1.5
		   for (i = 0; i < 36; i++) {
		    if (!uuid[i]) {
		     r = 0 | Math.random()*16;
		     uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
		    }
		   }
		  }
		 
		  return uuid.join('');
	},

	// 页面跳转
	pageJump: function (url) {
		location.assign(url)
	},

	// 保存数据到缓存
	setLocalstorage: function (name, value) {
		if (!window.localStorage) {
			alert("浏览器不支持localstorage");
			return false;
		} else {
			window.localStorage.setItem(name, value);
		}
	},

	// 获取缓存数据
	getLocalstorage: function (name) {
		if (!window.localStorage) {
			alert("浏览器不支持localstorage");
			return false;
		} else {
			return window.localStorage.getItem(name);
		}
	},

	// 清除缓存,如果要删除特定缓存直接传入name,如果要清除所有缓存则传入all即可
	clearLocalstorage: function (name) {
		if (!window.localStorage) {
			alert("浏览器不支持localstorage");
			return false;
		} else {
			if (name == 'all') {
				window.localStorage.clear();
			} else {
				window.localStorage.removeItem(name);
			}
		}
	},

	//对象判空方法
	isEmpty: function (str) {
		if (str === null || str === '' || typeof (str) === 'undefined') {
			return true
		}
		return false
	},
	
	//时间格式化
	timeFormat: function(time,format){
	    
	},
	
	//日期格式化
	dateFormat: function(data,fmt){
	 var o = {   
			    "M+" : data.getMonth()+1,                 //月份   
			    "d+" : data.getDate(),                    //日   
			    "h+" : data.getHours(),                   //小时   
			    "m+" : data.getMinutes(),                 //分   
			    "s+" : data.getSeconds(),                 //秒   
			    "q+" : Math.floor((data.getMonth()+3)/3), //季度   
			    "S"  : data.getMilliseconds()             //毫秒   
			  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (data.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt; 
	},
	
	//时间转换为秒
	time2Sec: function(time){
	    if (this.isEmpty(time)) return null;
	    var hour = time.split(':')[0];
	    var min = time.split(':')[1];
	    var sec = time.split(':')[2];
	    return Number(hour*3600) + Number(min*60) + Number(sec);
	},
	
	//秒转换为时间
	sec2Time: function(sec){
	    var t;
	    if(sec > -1){
		var hour = Math.floor(s/3600);
	        var min = Math.floor(s/60) % 60;
	        var sec = s % 60;
	        if(hour < 10) {
	            t = '0'+ hour + ":";
	        } else {
	            t = hour + ":";
	        }
	        if(min < 10){t += "0";}
	        t += min + ":";
	        if(sec < 10){t += "0";}
	        t += sec.toFixed(2);
	    }
	    return t;
	},
	
	
	
	
	/** 需要根据项目定制化的方法 **/
	// 消息提示框
	message: function (jqObj,message, type) {
		var dom = $('.fd-message');
		dom.find('span').html(message);
		if (type == 'success') {
			dom.find('span').css('background', '#19be6b');
		} else if (type == 'error') {
			dom.find('span').css('background', '#ed3f14');
		} else if (type == 'warning') {
			dom.find('span').css('background', '#ff9900');
		}
		dom.show();
		setTimeout(function () {
			dom.animate({
				'top': 0
			}, 500, function () {
				dom.hide().css('top', '80px')
			})
		}, 2500);
	},
	
	//alert提示框
	alertMessage: function(jqObj,msg,type){
	    
	},
	
	//confirm提示框
	confirmMessage:function(jqObj,msg,type){
	    
	},
	
	//非法字符检测
	hasIllegalCharacter:function(word,alertText,rexByUser){
		var rex = isEmpty(rexByUser)?/[(\~)(\!)(\@)(\#)(\$)(\^)(\&)(\*)(\%)]+/:rexByUser;
		if(rex.test(word)){
			alertDiv(isEmpty(alertText)?"输入字符不合法!":alertText);
			return true;
		}else{
			return false;
		}
	}


	
}