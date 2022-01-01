<template>
  <div class="home-container">
    <div class="wrap" ref="editor">
      <div class="top">
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
            <table border="1px solid #ccc" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <thead>
                <tr>
                  <th width="20%">案号</th>
                  <th width="40%">案件名称</th>
                  <th width="15%">立案日期</th>
                  <th width="15%">结案日期</th>
                  <th width="5.6%">审限</th>
                  <th>附加审限</th>
                </tr>
              </thead>

              <tbody>
              <tr v-for = "aj in ajArry">
                <td>{{aj.ah}}</td>
                <td>{{aj.ajmc}}</td>
                <td>{{aj.larq}}</td>
                <td>{{aj.jarq}}</td>
                <td>{{aj.sx}}</td>
                <td>{{aj.fjsx}}</td>
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
        <itemTable>
          <template v-slot:title><div id="title1">案件详情</div></template>
          <template v-slot:search>
            <input v-model="message1" placeholder="搜索案件" class="search" @keyup.enter="search1">
            <button class="btn" @click="search1">搜索</button>
          </template>
          <template v-slot:table>
            <table border="1px solid #ccc" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <thead>
              <tr>
                <th width="20%">案号</th>
                <th width="40%">案件名称</th>
                <th width="15%">立案日期</th>
                <th width="15%">结案日期</th>
                <th width="5.6%">审限</th>
                <th>附加审限</th>
              </tr>
              </thead>

              <tbody>
              <tr v-for = "aj in ajArry1">
                <td>{{aj.ah}}</td>
                <td>{{aj.ajmc}}</td>
                <td>{{aj.larq}}</td>
                <td>{{aj.jarq}}</td>
                <td>{{aj.sx}}</td>
                <td>{{aj.fjsx}}</td>
              </tr>
              </tbody>
            </table>
          </template>
          <template v-slot:pagination>
            <div class="page-bar">
              <ul>
                <li class="first">
                  <span>共{{dataNum1}}条记录 第 {{cur1}} / {{all1}} 页</span>
                </li>
                <li v-if="cur1>1" class="next">
                  <a v-on:click="cur1--,pageClick1()"><</a>
                </li>
                <!--                <li v-if="cur==1">-->
                <!--                  <a class="banclick"><</a>-->
                <!--                </li>-->
                <li  class="li_a" v-for="index in indexs1" v-bind:class="{ 'active': cur1 == index}">
                  <a v-on:click="btnClick1(index)">{{ index }}</a>
                </li>
                <li  v-if="cur1!=all1" class="next">
                  <a v-on:click="cur1++,pageClick1()">></a>
                </li>
                <!--                <li  v-if="cur == all">-->
                <!--                  <a class="banclick">></a>-->
                <!--                </li>-->
                <li class="last_li">
                  <span>共<i>{{all1}}</i>页</span>
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
      ajList1:[],
      ajArry1:[],
      ajSearch1:[],
      all1: 5, //总页数
      cur1:  1,//当前页码
      num1: 20, //一页显示的数量  奇数
      dataNum1: 100,//数据的数量
      message1:""
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
    },
    cur1: function(oldValue, newValue) {
      //父组件通过change方法来接受当前的页码
      this.$emit('change', oldValue)
      //这里是直接点击执行函数
      let count=0;
      this.ajArry1=[]
      for(var i=oldValue*20-20;i<oldValue*20;i++){
        if(this.message1=="") {
          if (i >= this.ajList1.length) {
            break
          }
          this.ajArry1[count] = this.ajList1[i]
          count++;
        }
        else{
          if (i >= this.ajSearch1.length) {
            break
          }
          this.ajArry1[count] = this.ajSearch1[i]
          count++;
        }
      }
    },
    ajArry1:function(oldValue,newValue){
      console.log(oldValue)
      this.$emit('change',oldValue)
    },
    message1:function(oldValue,newValue){
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
    },
    indexs1: function() {
      var left = 1;
      var right = this.all;
      var ar = [];

      if(this.cur1 > 3 && this.cur1 < this.all1 - 2) {
        left = this.cur1 -  2
        right = this.cur1 +  2
      } else {
        if(this.cur1 <= 3) {
          left = 1
          if(this.all1<5){
            right=this.all1
          }
          else {
            right = 5
          }
        } else {
          right = this.all1
          left = this.all1 - 4
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
    this.showTable2();
    this.showTitle();
  },
  methods: {
    showTitle(){
      let index=window.localStorage.getItem("index");
      let api=window.localStorage.getItem("api");
      if(api.substring(api.indexOf('/')+1,api.lastIndexOf('/'))=='syjfaj'){
        $("#title").text(index+"赡养纠纷案件立案详情")
        $("#title1").text(index+"赡养纠纷案件结案详情")
      }
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
      let api=window.localStorage.getItem("api");
      let index=window.localStorage.getItem("index")
      let beginTime=window.localStorage.getItem("beginTime")
      let endTime=window.localStorage.getItem("endTime")
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
    search1() {
      this.ajSearch1=[]
      this.ajArry1=[]
      var count=0;
      for(var i=0;i<this.ajList1.length;i++){
        if(this.ajList1[i].ah.indexOf(this.message1)!=-1||this.ajList1[i].ajmc.indexOf(this.message1)!=-1||this.ajList1[i].jarq.indexOf(this.message1)!=-1){
          this.ajSearch1[count++]=this.ajList1[i]
        }
      }
      this.cur1=1;
      this.dataNum1=this.ajSearch1.length
      this.all1=Math.ceil(this.dataNum1/this.num1)
      for(var i=0;i<20;i++){
        if(i<this.ajSearch1.length){
          this.ajArry1[i]=this.ajSearch1[i];
        }
      }
      if(this.ajArry1.length==0){
        alert("搜索不到结果")
        this.showTable3()
        this.message1=""

      }
    },
    btnClick1: function(data) { //页码点击事件
      if(data != this.cur1) {
        this.cur1 = data
      }
    },
    pageClick1: function() {
      console.log('现在在' + this.cur1 + '页');
      //父组件通过change方法来接受当前的页码
      //这里是点击下一页执行函数
      this.$emit('change',  this.cur1)
    },
    showTable2(){
      let that=this
      let api=window.localStorage.getItem("api1");
      let index=window.localStorage.getItem("index")
      let beginTime=window.localStorage.getItem("beginTime")
      let endTime=window.localStorage.getItem("endTime")
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
          that.ajList1=response.data
          that.dataNum1=response.data.length
          that.all1=Math.ceil(that.dataNum1/that.num1)
          for(var i=0;i<20;i++){
            if(i>=that.ajList1.length){
              break
            }
            that.ajArry1[i]=that.ajList1[i];
          }
        }
      })
    },
    showTable3(){
      this.dataNum1=this.ajList1.length
      this.all1=Math.ceil(this.dataNum1/this.num1);
      for(var i=0;i<20;i++){
        if(i>=this.ajList1.length){
          break
        }
        this.ajArry1[i]=this.ajList1[i];
      }
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
</style>
