<!--
 描述: 未成年犯罪
-->

<template>
  <div class="home-container">
    <div class="wrap" ref="editor">
      <div class="top">
        <div><a href="/#/home"><img src="../assets/img/return.png"/></a></div>
        <div id="down"><img src="../assets/img/download.png" @click="btnWord"/></div>
        <div id="down1" @click="btnWord">下载</div>
        <div id="topText">未成年人案件专题分析系统</div>
        <div class="time">
          <div><label>时间：</label><input type="month" v-model="time"></div>
        </div>
        <div class="date" v-show="false">
          <div><label>开始时间：</label><input type="date" v-model="beginTime"></div>
          <div><label>结束时间：</label><input type="date" v-model="endTime"></div>
        </div>
      </div>
      <div class="content">
        <item>
          <template v-slot:title>案件收结情况</template>
          <template v-slot:chart>
            <!--最近三年案件量 双柱状图-->
            <bars api="/wcnfz/minorYearAjCount" :begin-time="beginTime" :end-time="endTime" title1="案件收结情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>罪名分布</template>
          <template v-slot:chart>
            <!--罪名分布 柱状图-->
            <bar api="/wcnfz/minorZmfb" :begin-time="beginTime" :end-time="endTime" title="案件收结情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件地域分布</template>
          <template v-slot:chart>
            <!--中院显示辖区 高院显示所有-->
            <tianjin-map  api="/wcnfz/minorDyfb" :begin-time="beginTime" :end-time="endTime" title="案件地域分布"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件审理情况</template>
          <template v-slot:chart>
            <three-bars api="/wcnfz/getAjsl" :begin-time="beginTime" :end-time="endTime" title1="案件审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（立案数）</template>
          <template v-slot:chart>
            <three-bars api="/wcnfz/getBmslLa" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（结案数）</template>
          <template v-slot:chart>
            <three-bars api="/wcnfz/getBmslJa" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（平均审理天数）</template>
          <template v-slot:chart>
            <three-bars api="/wcnfz/getBmslAvg" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（性别）</template>
          <template v-slot:chart>
            <!--被告人性别-->
            <three-bars api="/wcnfz/minorBgrxb" :begin-time="beginTime" :end-time="endTime" title="当事人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（年龄）</template>
          <template v-slot:chart>
            <!--年龄分布 一年零一个段-->
            <three-bars api="/wcnfz/minorBgrAge" :begin-time="beginTime" :end-time="endTime" title="当事人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（文化程度）</template>
          <template v-slot:chart>
            <!--文化程度-->
            <three-bars api="/wcnfz/minorWhcd" :begin-time="beginTime" :end-time="endTime" title="当事人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（外来人口）</template>
          <template v-slot:chart>
            <!--外来人口-->
            <three-bars api="/wcnfz/minorWlrk" :begin-time="beginTime" :end-time="endTime" title="当事人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>罪名分布（14-15岁）</template>
          <template v-slot:chart>
            <!--诉讼罪名分析14--15周岁 柱状图-->
            <bar api="/wcnfz/minorSszmRange1" :begin-time="beginTime" :end-time="endTime" title="案件特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>罪名分布（16-17岁）</template>
          <template v-slot:chart>
            <!--诉讼罪名分析 16--17周岁 柱状图-->
            <bar api="/wcnfz/minorSszmRange2" :begin-time="beginTime" :end-time="endTime" title="案件特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件特征（有期徒刑）</template>
          <template v-slot:chart>
            <bar api="/wcnfz/yqtx" :begin-time="beginTime" :end-time="endTime" title="案件特征"/>
          </template>
        </item>
      </div>
    </div>
  </div>
</template>

<script>
import {getCurTime, nextMonth, screenSize} from '@/assets/js/utils'
import $ from "jquery";
import '@/utils/export'
export default {
  name: 'Home',
  components: {},
  data() {
    return {
      beginTime:getCurTime().substring(0,7)+'-01',
      endTime:nextMonth(),
      time:getCurTime().substring(0,7)
    }
  },
  computed: {

  },
  watch:{
    time:function(newValue,oldValue){
      this.beginTime=newValue.toString()+"-01"
      let date=new Date(this.beginTime)
      let nextMonth=date.getMonth()+1
      date.setMonth(nextMonth)
      let time=new Date(date.getTime()-86400000)
      let day=("0"+time.getDate()).slice(-2);
      let month=("0"+(time.getMonth()+1)).slice(-2);
      this.endTime=time.getFullYear()+'-'+month+'-'+day
    }
  },
  created() {
  },
  mounted() {
    screenSize(this.$refs.editor);

  },
  methods: {
    btnWord(){
      this.axios.get("/wcnfz/word",{
        params:{
          beginTime:this.beginTime,
          endTime:this.endTime
        },
        responseType:'blob'
      }).then(function(response){
        let blob=new Blob([response.data],{type:response.data.type})
        const fileName='未成年人案件专题分析.docx'
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
.home-container .wrap .top .date{
  height: 50px;
  margin-top: -25px !important;
  float: right;
}
.time{
  height: 50px;
  margin-top: -25px;
  float: right;
  position: relative;
  right:20px;
  top:20px;
}
.time div{
  height: 25px;
  padding: 2px;
  label{
    color: #00aeef;
    font-size: 18px;
  }
}
#topText{
  color: #00aeef;
  position: absolute;
  left: 34.5%;
  top: 20px;
  font-weight: bold;
  font-size:50px;
}
.home-container {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  .wrap {
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
