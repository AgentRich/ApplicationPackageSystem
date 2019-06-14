/**
 *Index.js
 **/

var app = new Vue({
  el: '#app',
  data: {
    message: 'AppUpdateMapList!',
    appUpdateMapList:[],
    loading:true,
    editableColumns: [
        { field: "name", title: "应用名", width: 180 },
        { field: "updateUrl", title: "应用更新网站", width: 180 },
        { field: "downUrl", title: "应用真实下载地址" }
    ]
  },
  methods: {
	  initAppUpdateMapList: function(){
		  var that = this;
		  common.AjAxReturnJson("/listUpdateMap", null, true, "get", function(jsonData){
			  if(jsonData.code!=200) message = jsonData.msg;
			  else {
				  that.appUpdateMapList = jsonData.data
				  for (var i = 0; i < that.appUpdateMapList; i++) {
					  that.$set(that.appUpdateMapList[i], 'isShowEdit', false)
				  }
				  console.log(that.appUpdateMapList);
			  }
		  })
		  that.loading = false
	  },
	  addRow: function(){
		  this.$message.warning('添加一个应用,需要编辑保存后才能生效~!');
		  let temp = {
				  "updateTime":common.dateFormat(new Date(),"yyyy-MM-dd hh:mm:ss")
				  ,"id": common.uuid(16)
				  , "name": ""
				  , "updateUrl": ""
				  , "downUrl": ""
				  , "isShowEdit": false};
		  this.appUpdateMapList.push(temp);
	  },
	  editRow: function(index,row){
		  row.isShowEdit = true
		  console.log(index)
	  },
	  updateRow: function(index,row){
		  //加载进度条
		  //向后台发送更新数据和请求
//		  common.AjAxReturnJson("/updateAppInfo2Map", row, true, "post", function(jsonData){
//			  console.log(jsonData)
			  //进度条完成并恢复编辑状态
			  row.isShowEdit = false
//		  })
	  }
  },
  mounted: function () {
	  this.initAppUpdateMapList()
  }
})
