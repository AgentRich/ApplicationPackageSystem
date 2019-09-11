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
        { field: "downUrl", title: "应用下载页面地址" }
    ]
  },
  methods: {
	  initAppUpdateMapList: function(){
		  var that = this;
		  common.AjaxReturnJson("/listUpdateMap", null, true, "get", function(jsonData){
			  if(jsonData.code!=200) message = jsonData.msg;
			  else {
				  that.appUpdateMapList = jsonData.data
				  for (var i = 0; i < that.appUpdateMapList.length; i++) {
					  that.$set(that.appUpdateMapList[i], 'isShowEdit', false)
					  let updateStatus = that.appUpdateMapList[i].updateStatus
					  that.$set(that.appUpdateMapList[i],'updateStatusStr',updateStatus2Str(updateStatus))
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
//		  e.set 因为v-for的影响可能引起页面不响应式渲染
//		  Vue.set(this.appUpdateMapList, index, row)
		  Vue.delete(row, 'isShowEdit')
		  Vue.set(row, 'isShowEdit', true)
	  },
	  updateRow: function(index,row,remove){
		  var that = this
		  //加载进度条
		  
		  row.updateTime = common.dateFormat(new Date(),"yyyy-MM-dd hh:mm:ss")
		  var data;
		  if(remove) data = {"removeId" : row.id}
		  else data = row
		  //向后台发送更新数据和请求
		  common.AjaxReturnJson("/updateAppInfo2Map", data, true, "post", function(jsonData){
			  console.log(jsonData)
			  //进度条完成并恢复编辑状态或删除行
			  if(remove) Vue.delete(that.appUpdateMapList, index, row)
			  else{
				  //直接set List和先删除再set是一样的效果但是必须使用Vue.set 因为v-for的影响可能引起页面不响应式渲染
				  //Vue.set(this.appUpdateMapList, index, row)
				  Vue.delete(row, 'isShowEdit')
				  Vue.set(row, 'isShowEdit', false)
			  }
		  })
	  },
	  updatePack: function(row){
		  console.log('updatePack-row: ',row)
		  row.updateTime = common.dateFormat(new Date(),"yyyy-MM-dd hh:mm:ss")
		  var data = row
		  //向后台发送更新数据和请求
		  common.AjaxReturnJson("/updateAppPack", data, true, "post", function(jsonData){
			  row.updateStatus = 1
			  console.log('updatePack-row2: ',row)
		  })
	  }
  },
  created: function () {
	  this.initAppUpdateMapList()
  }
})

function updateStatus2Str(updateStatus){
	return updateStatus?'正在更新中...':'没有在更新'
}
