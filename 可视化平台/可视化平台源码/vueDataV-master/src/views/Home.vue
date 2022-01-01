<!--
 描述: 酷屏首页模板
 作者: Jack Chen
 日期: 2020-04-29
-->

<template>
  <div class="home-container">
  	<div class="wrap" ref="editor">
      <div class="top">
        <div><a href="/"><img src="../assets/img/return.png"/></a></div>
        <div id="fy">天津自由贸易试验区人民法院</div>
        <div id="topText">司法数据资源服务平台</div>
        <div id="time">2021/08/09<br/>星期一</div>
        <div id="time1">16:22:35</div>
      </div>
      <div class="buttonList" style="text-align: center">
        <div id="subSystem" class="wrap-container sn-container">
          <div class="sn-content">
            <div class="sn-title" id="title">
              <div id="titleText">十大子系统</div>
            </div>
            <div class="row">
              <router-link to="/ladsj">
                <div class="btn" id="ladsj">
                  <img src="../assets/img/ladsj.png" style="height:80px;width:100px"/>
                  <div>立案大数据<br/>可视化系统</div>
                </div>
              </router-link>
              <router-link to="/splcjd">
                <div class="btn">
                  <img src="../assets/img/splcjd.png" style="height:80px;width:100px"/>
                  <div>审判流程监督<br/>可视化系统</div>
                </div>
              </router-link>
              <router-link to="/shfz">
                <div class="btn">
                  <img src="../assets/img/shaj.png" style="height:80px;width:100px"/>
                  <div>涉黑案件<br/>专题分析系统</div>
                </div>
              </router-link>
            </div>
            <div class="row">
              <router-link to="/wcnfz">
                <div class="btn">
                  <img src="../assets/img/wcnraj.png" style="height:80px;width:100px"/>
                  <div>未成年人案件<br/>专题分析系统</div>
                </div>
              </router-link>
              <router-link to="/lhjf">
                <div class="btn">
                  <img src="../assets/img/lhaj.png" style="height:80px;width:100px"/>
                  <div>离婚案件<br/>专题分析系统</div>
                </div>
              </router-link>
              <router-link to="/wxjsz">
                <div class="btn">
                  <img src="../assets/img/dljtaj.png" style="height:80px;width:100px"/>
                  <div>道路交通案件<br/>专题分析系统</div>
                </div>
              </router-link>
            </div>
            <div class="row">
              <router-link to="/zscqqq">
                <div class="btn">
                  <img src="../assets/img/zscqqqaj.png" style="height:80px;width:100px"/>
                  <div>知识产权侵权案件<br/>专题分析系统</div>
                </div>
              </router-link>
              <router-link to="/xykzp">
                <div class="btn">
                  <img src="../assets/img/xykzpaj.png" style="height:80px;width:100px"/>
                  <div>信用卡诈骗案件<br/>专题分析系统</div>
                </div>
              </router-link>
              <router-link to="/dxwlzp">
                <div class="btn">
                  <img src="../assets/img/dxwlzpaj.png" style="height:80px;width:100px"/>
                  <div>诈骗案件<br/>专题分析系统</div>
                </div>
              </router-link>
            </div>
            <div class="row">
              <router-link to="/syjfaj">
                <div class="btn">
                  <img src="../assets/img/syjfaj.png" style="height:80px;width:100px"/>
                  <div>赡养纠纷案件<br/>专题分析系统</div>
                </div>
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <ywtpm/>
      <ssajxx/>
    </div>
  </div>
</template>
<script>
import {getCurTime, screenSize} from '@/assets/js/utils'

export default {
  name: 'Home',
  components: {},
  data() {
  	return {
  		  days:["星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日"]
  	}
  },
  computed: {
  	
  },
  created() {
  },
  mounted() {
    screenSize(this.$refs.editor);
    this.rewrite();
    this.time();
  },
  methods: {
    rewrite(){
      $("#fy").text(window.localStorage.getItem("fymc"))
      window.localStorage.setItem('download','')
    },
    time(){
      let now=new Date()
      let day=("0"+now.getDate()).slice(-2);
      let month=("0"+(now.getMonth()+1)).slice(-2);
      var str1=now.getFullYear()+'/'+month+'/'+day+"<br/>"+this.days[now.getDay()]
      $("#time").html(str1)
      var str2=("0"+now.getHours()).slice(-2)+":"+("0"+now.getMinutes()).slice(-2)+":"+("0"+now.getSeconds()).slice(-2)
      $("#time1").text(str2)
      setTimeout(this.time,1000)
    }
  }
}
</script>

<style lang="scss" scoped>
//#ladsj{
//  background: url(../assets/img/ladsj.png) no-repeat center center;
//  background-size: cover;
//  background-attachment: fixed;
//}
.top img{
  height: 33px;
  width: 40px;
  left: 10px;
  position: absolute;
  top: 3px;
}
#time1{
  color: #00aeef;
  font-size: 20px;
  position: absolute;
  //left: 150px;
  right:7%;
  top:8px;
}
#time{
  color: #00aeef;
  font-size: 16px;
  position: absolute;
  //left: 150px;
  right:2%;
}
#fy{
  color: #00aeef;
  font-size: 20px;
  position: absolute;
  //left: 150px;
  right: 84%;
  top: 8px;
}
.row{
  display: flex;
  justify-content: space-around;
  margin:90px;
}
#topText{
  color: #00aeef;
  position: absolute;
  left: 38%;
  top: 20px;
  font-weight: bold;
  font-size:50px;
}
#title{
  background: url(../assets/img/title.png) no-repeat;
  position: absolute;
  height: 50px;
  font-size: 25px;
  color: #00c2ff;
  line-height: 26px;
  padding: 0 0 0 20px;
  right: 10px;
  top: -2px;
  left:171px;
  transform: translate(0, 0);

}
#titleText{
  position: absolute;
  left: 115px;
  top: 8px;
  font-weight: bold;
  font-size:25px;
}
#subSystem {
  left:610px;
  top:110px;
  width:700px;
  height:700px;
}
.home-container {
  background: url(../assets/img/bj.png) no-repeat center center;
  //background-size: contain;
  background-size: cover;
  //background-position: 50% 0;
  //background-color: rgb(0, 0, 0);
  background-attachment: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  .wrap {
    transform-origin: 0px 0px 0px;
    //background: url(../assets/img/bj.jpg) no-repeat center center;
    //background-size: contain;
    //background-size: cover;
    //background-position: 50% 0;
    //background-color: rgb(0, 0, 0);
    //background-attachment: fixed;
    min-width: auto;
    width: 1920px;
    min-height: auto;
    height: 1080px;
    .buttonList{
      width: 150px;
      margin: 0 auto;
      display: flex;;
      flex-direction: column;
      justify-content: space-around;
      align-items: center ;
      height: 600px;
    }
    .btn{
      height: 60px;
      width: 150px;
      margin: 0 auto;
      //line-height: 60px;
      cursor: pointer;
      font-size: 16px;
      //border: 1px dashed rgba(92, 255, 255, 0.5);
      color: #00aeef;
      //border-radius: 10px;
    }
    .top {
      width: 100%; 
      height: 80px; 
      background-color: transparent; 
      background: url(../assets/img/top_nav.png) no-repeat;
      background-position: 65% 0;
      border: none; 
      overflow: hidden;
    }
  }
}	
</style>
