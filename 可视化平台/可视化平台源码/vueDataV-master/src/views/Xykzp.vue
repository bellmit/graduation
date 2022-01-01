<!--
 描述: 信用卡诈骗罪
-->

<template>
  <div class="home-container">
    <div class="wrap" ref="editor">
      <div class="top">
        <div><a href="/#/home"><img src="../assets/img/return.png"/></a></div>
        <div id="down"><img src="../assets/img/download.png" @click="btnWord"/></div>
        <div id="down1" @click="btnWord">下载</div>
        <div id="topText">信用卡诈骗案件专题分析系统</div>
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
            <bars api="/xykzp/getAjCount" :begin-time="beginTime" :end-time="endTime" title="案件审结情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件审理情况</template>
          <template v-slot:chart>
            <three-bars api="/xykzp/getAjsl" :begin-time="beginTime" :end-time="endTime" title1="案件审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（立案数）</template>
          <template v-slot:chart>
            <three-bars api="/xykzp/getBmslLa" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（结案数）</template>
          <template v-slot:chart>
            <three-bars api="/xykzp/getBmslJa" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（平均审理天数）</template>
          <template v-slot:chart>
            <three-bars api="/xykzp/getBmslAvg" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件审结情况-当年月案件量</template>
          <template v-slot:chart>
            <bar api="/xykzp/getAjMonthCount" :begin-time="beginTime" :end-time="endTime" title="案件审结情况-当年月案件量"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件地域分布</template>
          <template v-slot:chart>
            <tianjin-map api="/xykzp/getAreaDistribution" :begin-time="beginTime" :end-time="endTime" title="案件地域分布"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（性别）</template>
          <template v-slot:chart>
            <pie api="/xykzp/getBgrxb" :begin-time="beginTime" :end-time="endTime" title="被告人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（文化程度）</template>
          <template v-slot:chart>
            <pie api="/xykzp/getBgrWhcd" :begin-time="beginTime" :end-time="endTime" title="被告人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（身份）</template>
          <template v-slot:chart>
            <pie api="/xykzp/getBgrsf" :begin-time="beginTime" :end-time="endTime" title="被告人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（年龄）</template>
          <template v-slot:chart>
            <ring api="/xykzp/getBgrAge" :begin-time="beginTime" :end-time="endTime" title="被告人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>当事人特征（量刑）</template>
          <template v-slot:chart>
            <bar api="/xykzp/getYqtxlx" :begin-time="beginTime" :end-time="endTime" title="被告人特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>被告人特征（性别）</template>
          <template v-slot:chart>
            <!--被告人性别-->
            <three-bars api="/xykzp/getBgxb" :begin-time="beginTime" :end-time="endTime" title="被告人特征（性别）"/>
          </template>
        </item>
        <item>
          <template v-slot:title>被告人特征（年龄）</template>
          <template v-slot:chart>
            <!--被告人年龄-->
            <three-bars api="/xykzp/getBgAge" :begin-time="beginTime" :end-time="endTime" title="被告人特征（年龄）"/>
          </template>
        </item>
        <item>
          <template v-slot:title>被告人特征（文化程度）</template>
          <template v-slot:chart>
            <!--被告人身份-->
            <three-bars api="/xykzp/getBgWhcd" :begin-time="beginTime" :end-time="endTime" title="被告人特征（文化程度）"/>
          </template>
        </item>
        <item>
          <template v-slot:title>被告人特征（身份）</template>
          <template v-slot:chart>
            <!--被告人身份-->
            <three-bars api="/xykzp/getBgSf" :begin-time="beginTime" :end-time="endTime" title="被告人特征（身份）"/>
          </template>
        </item>
      </div>
    </div>
  </div>
</template>

<script>
import {getCurTime, nextMonth, screenSize} from '@/assets/js/utils'
import $ from 'jquery'
import '@/utils/export'
export default {
  name: 'Xykzp',
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
      this.axios.get("/xykzp/word",{
        params:{
          beginTime:this.beginTime,
          endTime:this.endTime
        },
        responseType:'blob'
      }).then(function(response){
        let blob=new Blob([response.data],{type:response.data.type})
        const fileName='信用卡诈骗案件专题分析.docx'
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
  left: 33%;
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
