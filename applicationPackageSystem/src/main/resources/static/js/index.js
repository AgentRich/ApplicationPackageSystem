/**
 *Index.js
 **/

var app = new Vue({
  el: '#app',
  data: {
    message: 'AppUpdateMapList!',
    appUpdateMapList:'',
    loading:true
  },
  methods: {
	  initAppUpdateMapList: function(){
		  var that = this;
		  common.AjAxReturnJson("/listUpdateMap", null, true, "get", function(jsonData){
			  if(jsonData.code!=200) message = jsonData.msg;
			  else that.appUpdateMapList = jsonData.data.updateMapList 
		  })
		  that.loading = false
	  }
  },
  created: function () {
	  this.initAppUpdateMapList()
  }
})
