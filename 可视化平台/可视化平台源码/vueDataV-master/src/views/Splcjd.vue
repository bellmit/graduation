<!--
 描述: 审判流程监督可视化系统
-->

<template>
  <div class="home-container">
    <div class="wrap" ref="editor">
      <div class="top">
        <div><a href="/#/home"><img src="../assets/img/return.png"/></a></div>
        <div id="down"><img src="../assets/img/download.png" @click="btnWord"/></div>
        <div id="down1" @click="btnWord">下载</div>
        <div id="topText">审判流程监督可视化系统</div>
        <div class="date" v-show="false">
          <div><label>开始时间：</label><input type="date" v-model="beginTime"></div>
          <div><label>结束时间：</label><input type="date" v-model="endTime"></div>
        </div>
      </div>
      <div class="content">
        <item>
          <template v-slot:title>当日立案情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrla" api1="/splcjd/getDrlaList" :begin-time="beginTime" :end-time="endTime" title="当日立案情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月立案情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDyla" api1="/splcjd/getDylaList" :begin-time="beginTime" :end-time="endTime" title="当月立案情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当日分案情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrfa" api1="/splcjd/getDrfaList" :begin-time="beginTime" :end-time="endTime" title="当日分案情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月分案情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDyfa" api1="/splcjd/getDyfaList" :begin-time="beginTime" :end-time="endTime" title="当月分案情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当日开庭情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrkt" api1="/splcjd/getDrktList" :begin-time="beginTime" :end-time="endTime" title="当日开庭情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月开庭情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDykt" api1="/splcjd/getDyktList" :begin-time="beginTime" :end-time="endTime" title="当月开庭情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当日审限延长申请情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrts" api1="/splcjd/getDrtsList" :begin-time="beginTime" :end-time="endTime" title="当日审限延长申请情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月审限延长申请情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDyts" api1="/splcjd/getDytsList" :begin-time="beginTime" :end-time="endTime" title="当月审限延长申请情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当日审限中止申请情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrsx" api1="/splcjd/getDrsxList" :begin-time="beginTime" :end-time="endTime" title="当日审限中止申请情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月审限中止申请情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDysx" api1="/splcjd/getDysxList":begin-time="beginTime" :end-time="endTime" title="当月审限中止申请情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当日报结情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrbj" api1="/splcjd/getDrbjList" :begin-time="beginTime" :end-time="endTime" title="当日报结情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月报结情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDybj" api1="/splcjd/getDybjList" :begin-time="beginTime" :end-time="endTime" title="当月报结情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当日结案情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrja" api1="/splcjd/getDrjaList" :begin-time="beginTime" :end-time="endTime" title="当日结案情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月结案情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDyja" api1="/splcjd/getDyjaList" :begin-time="beginTime" :end-time="endTime" title="当月结案情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当日归档情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDrgd" api1="/splcjd/getDrgdList" :begin-time="beginTime" :end-time="endTime" title="当日归档情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月归档情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDygd" api1="/splcjd/getDygdList" :begin-time="beginTime" :end-time="endTime" title="当月归档情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当月超期未结案（结案）情况</template>
          <template v-slot:chart>
            <bar api="/splcjd/getDycqja" api1="/splcjd/getDycqjaList" :begin-time="beginTime" :end-time="endTime" title="当月超期未结案（结案）情况" top-text="审判流程监督可视化系统"/>
          </template>
        </item>
        <itemTable>
          <template v-slot:title>当月超期未归档情况</template>
          <template v-slot:search>
            <input v-model="message" placeholder="搜索案件" id="search" @keyup.enter="search">
            <button id="btn" @click="search">搜索</button>
          </template>
          <template v-slot:table>
            <table border="1px solid #ccc" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <thead>
                <tr>
                  <th width="15%">案号</th>
                  <th width="75%">案件名称</th>
                  <th width="10%">结案日期</th>
                </tr>
              </thead>

              <tbody>
                <tr v-for = "aj in ajArry">
                  <td>{{aj.ah}}</td>
                  <td>{{aj.ajmc}}</td>
                  <td>{{aj.jarq}}</td>
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
import {getCurTime, screenSize} from '@/assets/js/utils'
import $ from "jquery";
import '@/utils/export'
export default {
  name: 'Home',
  components: {},
  data() {
    return {
      beginTime:new Date().getFullYear()+'-01-01',
      endTime:getCurTime(),
      ajList:[],
      ajArry:[],
      ajSearch:[],
      all: 5, //总页数
      cur:  1,//当前页码
      num: 20, //一页显示的数量  奇数
      dataNum: 100,//数据的数量
      message:""
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
    beginTime:function (newValue,oldValue){

    },
    endTime:function (newValue,oldValue){

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
  },
  methods: {
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
      this.axios.get("/splcjd/getDycqgd").then(function (response) {
         if(response.status==200){
           that.ajList=response.data
           that.dataNum=response.data.length
           that.all=Math.ceil(that.dataNum/that.num)
           for(var i=0;i<20;i++){
             if(i>that.ajList.length){
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
        if(i>this.ajList.length){
          break
        }
        this.ajArry[i]=this.ajList[i];
      }
    },
    btnWord(){
      this.axios.get("/splcjd/word",{
        params:{
          beginTime:this.beginTime,
          endTime:this.endTime
        },
        responseType:'blob'
      }).then(function(response){
        let blob=new Blob([response.data],{type:response.data.type})
        const fileName='审判流程监督.docx'
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

#search{

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
#btn{
  position:absolute;
  right:5%;
  top:30px;
  height: 40px;
  width:80px;
}
#search{
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
  left: 35.5%;
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
