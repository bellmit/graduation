<template>
  <div class="home-container">
    <div class="wrap" ref="editor">
      <div class="top">
        <div id="down"><img src="../assets/img/download.png" @click="btnWord"/></div>
        <div id="down1" @click="btnWord">下载</div>
        <div id="topText">案件详情系统</div>
      </div>
      <div class="content">
        <itemTable>
          <template v-slot:title><div id="title">案件详情</div></template>
          <template v-slot:search>
            <input v-model="message" placeholder="搜索案件" class="search" @keyup.enter="search">
            <button class="btn" @click="search">搜索</button>
          </template>
          <template v-slot:table>
            <table border="1px solid #ccc" cellspacing="0" cellpadding="0" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">
              <thead>
              <tr>
                <th width="300px">案号</th>
                <th width="1000px">案件名称</th>
                <th width="250px">立案日期</th>
                <th width="250px">结案日期</th>
                <th width="100px">案件性质</th>
              </tr>
              </thead>

              <tbody>
              <tr v-for = "aj in ajArry">
                <td>{{aj.ah}}</td>
                <td>{{aj.ajmc}}</td>
                <td>{{aj.larq}}</td>
                <td>{{aj.jarq}}</td>
                <td>{{aj.ajxz}}</td>
              </tr>
              </tbody>
            </table>
          </template>
          <template v-slot:pagination>
            <div class="page-bar">
              <ul>
                <li class="first">
                  <span>共{{dataNum}}条记录 第 {{cur}} / {{all}} 页</span>
                </li>
                <li v-if="cur>1" class="next">
                  <a v-on:click="cur--,pageClick()"><</a>
                </li>
                <!--                <li v-if="cur==1">-->
                <!--                  <a class="banclick"><</a>-->
                <!--                </li>-->
                <li  class="li_a" v-for="index in indexs" v-bind:class="{ 'active': cur == index}">
                  <a v-on:click="btnClick(index)">{{ index }}</a>
                </li>
                <li  v-if="cur!=all" class="next">
                  <a v-on:click="cur++,pageClick()">></a>
                </li>
                <!--                <li  v-if="cur == all">-->
                <!--                  <a class="banclick">></a>-->
                <!--                </li>-->
                <li class="last_li">
                  <span>共<i>{{all}}</i>页</span>
                </li>
              </ul>
            </div>
          </template>
        </itemTable>
      </div>
    </div>
  </div>
</template>

<script>
import {screenSize} from '@/assets/js/utils'
import $ from "jquery";
export default {
  name: "Home",
  components: {},
  data() {
    return {
      ajList:[],
      ajArry:[],
      ajSearch:[],
      all: 5, //总页数
      cur:  1,//当前页码
      num: 20, //一页显示的数量  奇数
      dataNum: 100,//数据的数量
      message:"",
      api:"",
      index:"",
      beginTime:"",
      endTime:""
    }
  },
  watch: {
    cur: function(oldValue, newValue) {
      //父组件通过change方法来接受当前的页码
      this.$emit('change', oldValue)
      //这里是直接点击执行函数
      let count=0;
      this.ajArry=[]
      for(var i=oldValue*20-20;i<oldValue*20;i++){
        if(this.message=="") {
          if (i >= this.ajList.length) {
            break
          }
          this.ajArry[count] = this.ajList[i]
          count++;
        }
        else{
          if (i >= this.ajSearch.length) {
            break
          }
          this.ajArry[count] = this.ajSearch[i]
          count++;
        }
      }
    },
    ajArry:function(oldValue,newValue){
      console.log(oldValue)
      this.$emit('change',oldValue)
    },
    message:function(oldValue,newValue){
      this.$emit('change',oldValue)
    }
  },
  computed: {
    indexs: function() {
      var left = 1;
      var right = this.all;
      var ar = [];

      if(this.cur > 3 && this.cur < this.all - 2) {
        left = this.cur -  2
        right = this.cur +  2
      } else {
        if(this.cur <= 3) {
          left = 1
          if(this.all<5){
            right=this.all
          }
          else {
            right = 5
          }
        } else {
          right = this.all
          left = this.all - 4
          if(left<1){
            left=1;
          }
        }
      }

      while(left <= right) {
        ar.push(left)
        left++
      }
      return ar
    }

  },
  created() {
  },
  mounted() {
    screenSize(this.$refs.editor);
    this.showTable();
    this.showTitle();
    // this.api=window.localStorage.getItem("api")
    // this.index=window.localStorage.getItem("index")
    // this.beginTime=window.localStorage.getItem("beginTime")
    // this.endTime=window.localStorage.getItem("endTime")
  },
  methods: {
    showTitle(){
      let index=window.sessionStorage.getItem("index");
      $("#title").text(index.substring(0,index.length-1)+"详情")
    },
    search() {
      this.ajSearch=[]
      this.ajArry=[]
      var count=0;
      for(var i=0;i<this.ajList.length;i++){
        if(this.ajList[i].ah.indexOf(this.message)!=-1||this.ajList[i].ajmc.indexOf(this.message)!=-1||this.ajList[i].jarq.indexOf(this.message)!=-1){
          this.ajSearch[count++]=this.ajList[i]
        }
      }
      this.cur=1;
      this.dataNum=this.ajSearch.length
      this.all=Math.ceil(this.dataNum/this.num)
      for(var i=0;i<20;i++){
        if(i<this.ajSearch.length){
          this.ajArry[i]=this.ajSearch[i];
        }
      }
      if(this.ajArry.length==0){
        alert("搜索不到结果")
        this.showTable1()
        this.message=""

      }
    },
    btnClick: function(data) { //页码点击事件
      if(data != this.cur) {
        this.cur = data
      }
    },
    pageClick: function() {
      console.log('现在在' + this.cur + '页');
      //父组件通过change方法来接受当前的页码
      //这里是点击下一页执行函数
      this.$emit('change',  this.cur)
    },
    showTable(){
      let that=this
      let api=window.sessionStorage.getItem("api");
      let index=window.sessionStorage.getItem("index")
      let beginTime=window.sessionStorage.getItem("beginTime")
      let endTime=window.sessionStorage.getItem("endTime")
      let endDate=new Date(endTime)
      let time=new Date(endDate.getTime()+86400000)
      let day=("0"+time.getDate()).slice(-2);
      let month=("0"+(time.getMonth()+1)).slice(-2);
      let endTime1=time.getFullYear()+'-'+month+'-'+day
      this.axios.get(api,{params:{
          index:index,
          beginTime:beginTime,
          endTime:endTime1
        }
      }).then(function (response) {
        if(response.status==200){
          that.ajList=response.data
          that.dataNum=response.data.length
          that.all=Math.ceil(that.dataNum/that.num)
          for(var i=0;i<20;i++){
            if(i>=that.ajList.length){
              break
            }
            that.ajArry[i]=that.ajList[i];
          }
        }
      })
    },
    showTable1(){
      this.dataNum=this.ajList.length
      this.all=Math.ceil(this.dataNum/this.num);
      for(var i=0;i<20;i++){
        if(i>=this.ajList.length){
          break
        }
        this.ajArry[i]=this.ajList[i];
      }
    },
    btnWord(){
      this.axios.get(window.sessionStorage.getItem("api")+"Excel",{
        params:{
          index:window.sessionStorage.getItem("index"),
          beginTime:window.sessionStorage.getItem("beginTime"),
          endTime:window.sessionStorage.getItem("endTime")
        },
        responseType:'blob'
      }).then(function(response){
        let blob=new Blob([response.data],{type:response.data.type})
        let title=window.sessionStorage.getItem("title")
        let index=window.sessionStorage.getItem("index")
        let fileName=""
        if(title!='当月超期未结案（结案）情况') {
          fileName = title.substring(0, 2) + index.substring(0, 4) + title.substring(2, title.length - 2) + '详情.xlsx'
        }
        else{
          fileName=index.substring(0,index.length-1)+'详情.xlsx'
        }
        let downloadElement=document.createElement('a')
        let href=window.URL.createObjectURL(blob)
        downloadElement.href=href
        downloadElement.download=fileName
        document.body.appendChild(downloadElement)
        downloadElement.click()
        document.body.removeChild(downloadElement)
        window.URL.revokeObjectURL(href)

      })

    }

  }
}

</script>

<style lang="scss" scoped>
td{
  text-align: center;
}
#title{
  font-size:20px;
  position: absolute;
  top:-9px;
  left:8px;
}
#title1{
  font-size:20px;
  position: absolute;
  top:-9px;
  left:8px;
}


.search{

  outline-color: invert;
  outline-style: none;
  outline-width: 0px;
  border: none;
  border-style: none;
  text-shadow: none;
  -webkit-appearance: none;
  -webkit-user-select: text;
  outline-color: transparent;
  box-shadow: none;

}
.btn{
  position:absolute;
  right:5%;
  top:30px;
  height: 40px;
  width:80px;
}
.search{
  position: absolute;
  right: 10%;
  top: 30px;
  width: 300px;
  height: 40px;
  font-size: 20px;
}
#topText{
  color: #00aeef;
  position: absolute;
  left: 42%;
  top: 20px;
  font-weight: bold;
  font-size:50px;
}
.page-bar {
  color:#00aeef;
  text-align: center;
  width: 100%;
  height: 36px;
  margin: 0 auto;
  position: absolute;
  bottom:50px;
  left:50px;
}

.page-bar ul {
  min-width: 700px;
  display: block;
  overflow: hidden;
  position: absolute;
  top: 50%;
  left: 45%;
  transform: translate(-50%,-50%);
}

.page-bar li {
  display: block;
  width: 36px;
  height: 36px;
  border-radius: 4px;
  list-style: none;
  overflow: hidden;
  position: relative;
  float: left;
  margin-left: 20px;
}
.page-bar .first{
  display: block;
  width: 250px;
  height: 36px;
  font-size: 20px;
  line-height: 36px;
  text-align: center;
}
.page-bar .last_li{
  width: 85px;
  height: 36px;
  border: 1px solid #00aeef;
  font-size:20px;
}
.page-bar .last_li span{
  width: 100%;
  height: 100%;
  line-height: 36px;
  text-align: center;
  float: left;
}
.page-bar li:first-child {
  margin-left: 0px
}

.page-bar a {
  width: 34px;
  height: 34px;
  border: 1px solid #00aeef;
  text-decoration: none;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  /*margin-left: -1px;*/
  line-height:  34px;
  color: #00aeef;
  cursor: pointer
}

.page-bar .li_a{
  font-size:20px;
}

.page-bar .li_a a:hover {
  background-color: #00FFFF;
  border: 1px solid #40A9FF;
  color: #40A9FF;
}

.page-bar .next{
  font-size: 20px;
}

.page-bar .next a:hover{
  background-color: #00FFFF;
  border: 1px solid #40A9FF;
  color: #40A9FF;
}

.page-bar a.banclick {
  cursor: not-allowed;
}

.page-bar .active a {
  color: #00aeef;
  cursor: default;
  background-color: #00FFFF;
  border-color: #1890FF;
}

.page-bar i {
  font-style: normal;
  color: #00aeef;
  margin: 0px 4px;
  font-size: 20px;
}

.home-container {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  .wrap {
    .date{
      height: 50px;
      margin-top: 15px;
      float: right;
      div{
        height: 25px;
        padding: 2px;
        label{
          color: #00aeef;
          font-size: 15px;
        }
      }
    }
    transform-origin: 0px 0px 0px;
    background: url(../assets/img/bj.png) no-repeat;
    background-size: contain;
    background-position: 50% 0;
    background-color: rgb(0, 0, 0);
    min-width: auto;
    width: 1920px;
    min-height: auto;
    height: 1080px;
    overflow: auto;
    .top {
      width: 100%;
      height: 80px;
      background-color: transparent;
      background: url(../assets/img/top_nav.png) no-repeat;
      background-position: 65% 0;
      border: none;
      overflow: hidden;
    }
    .content{
      overflow: hidden;
      padding-bottom: 60px;
    }
  }
}
table {
  border-collapse: collapse;
  border-spacing: 0;
  margin-right: 20px;
}
//table[data-v-917851ca] {
//  position: absolute;
//  font-size: 20px;
//  color: #00c2ff;
//  height: 600px;
//  margin-top: 100px;
//  overflow-y: auto;
//  width: 99% !important;
//}
</style>
