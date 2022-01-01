<!--
 描述: 赡养纠纷案件
-->

<template>
  <div class="home-container">
    <div class="wrap" ref="editor">
      <div class="top">
        <div><a href="/#/home"><img src="../assets/img/return.png"/></a></div>
        <div id="down"><img src="../assets/img/download.png" @click="btnWord"/></div>
        <div id="down1" @click="btnWord">下载</div>
        <div id="topText">赡养纠纷案件专题分析系统</div>
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
            <!--最近五年案件量 柱状图-->
            <bars api="/syjfaj/getAjCount" :begin-time="beginTime" :end-time="endTime" title="案件审结情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件审理情况</template>
          <template v-slot:chart>
            <three-bars api="/syjfaj/getAjsl" :begin-time="beginTime" :end-time="endTime" title1="案件审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（立案数）</template>
          <template v-slot:chart>
            <three-bars api="/syjfaj/getBmslLa" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（结案数）</template>
          <template v-slot:chart>
            <three-bars api="/syjfaj/getBmslJa" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
        <item>
          <template v-slot:title>部门审理情况（平均审理天数）</template>
          <template v-slot:chart>
            <three-bars api="/syjfaj/getBmslAvg" :begin-time="beginTime" :end-time="endTime" title1="部门审理情况"/>
          </template>
        </item>
<!--        <item>-->
<!--          <template v-slot:title>全院案件审理情况</template>-->
<!--          <template v-slot:chart>-->
<!--            <threeBars api="/syjfaj/getQyajsl" api1="/syjfaj/getQyajLalist"  api2="/syjfaj/getQyajJalist"  :begin-time="beginTime" :end-time="endTime" title1="全院案件审理情况" top-text="赡养纠纷案件专题分析系统"/>-->
<!--          </template>-->
<!--        </item>-->
<!--        <item>-->
<!--          <template v-slot:title>部门案件审理情况</template>-->
<!--          <template v-slot:chart>-->
<!--            <three-bars-without-des api="/syjfaj/getBmajsl" api1="/syjfaj/getBmajLalist"  api2="/syjfaj/getBmajJalist" :begin-time="beginTime" :end-time="endTime" title1="部门案件审理情况" top-text="赡养纠纷案件专题分析系统"/>-->
<!--          </template>-->
<!--        </item>-->
        <item>
          <template v-slot:title>被告性别特征</template>
          <template v-slot:chart>
            <pie api="/syjfaj/getBgxb" :begin-time="beginTime" :end-time="endTime" title="被告性别特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>原告性别特征</template>
          <template v-slot:chart>
            <pie api="/syjfaj/getYgxb" :begin-time="beginTime" :end-time="endTime" title="原告性别特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>被告身份特征</template>
          <template v-slot:chart>
            <pie api="/syjfaj/getBgsf" :begin-time="beginTime" :end-time="endTime" title="被告身份特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>原告身份特征</template>
          <template v-slot:chart>
            <pie api="/syjfaj/getYgsf" :begin-time="beginTime" :end-time="endTime" title="原告身份特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>被告年龄特征</template>
          <template v-slot:chart>
            <ring api="/syjfaj/getBgnl" :begin-time="beginTime" :end-time="endTime" title="被告年龄特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>原告年龄特征</template>
          <template v-slot:chart>
            <ring api="/syjfaj/getYgnl" :begin-time="beginTime" :end-time="endTime" title="原告年龄特征"/>
          </template>
        </item>
        <item>
          <template v-slot:title>被告人特征（身份）</template>
          <template v-slot:chart>
            <!--被告人身份-->
            <three-bars api="/syjfaj/getBgSf" :begin-time="beginTime" :end-time="endTime" title="被告人特征（身份）"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件判决结果</template>
          <template v-slot:chart>
            <bar api="/syjfaj/getResult" :begin-time="beginTime" :end-time="endTime" title="案件判决结果"/>
          </template>
        </item>
        <item>
          <template v-slot:title>案件特征（判决结果）</template>
          <template v-slot:chart>
            <!--被告人身份-->
            <three-bars api="/syjfaj/getPjjg" :begin-time="beginTime" :end-time="endTime" title=""/>
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
      this.axios.get("/syjfaj/word",{
        params:{
          beginTime:this.beginTime,
          endTime:this.endTime
        },
        responseType:'blob'
      }).then(function(response){
        let blob=new Blob([response.data],{type:response.data.type})
        const fileName='赡养纠纷案件专题分析.docx'
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
.home-container .wrap .date{
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