<!--
 描述: 立案大数据
-->

<template>
  <div class="home-container">
    <div class="wrap" ref="editor">
      <div class="top">
        <div><a href="/#/home"><img src="../assets/img/return.png"/></a></div>
        <div id="down"><img src="../assets/img/download.png" @click="btnWord"/></div>
        <div id="down1" @click="btnWord">下载</div>
        <div id="topText">立案数据可视化系统</div>
        <div class="date">
          <div><label>开始时间：</label><input type="date" v-model="beginTime"></div>
          <div><label>结束时间：</label><input type="date" v-model="endTime"></div>
        </div>
      </div>
      <div class="content">
        <item>
          <template v-slot:title>登记立案实时数据</template>
          <template v-slot:chart>
            <ssaj api="/ladsj/getDjlasssj" :begin-time="beginTime" :end-time="endTime"/>
          </template>
        </item>
        <item class="nofile">
          <template v-slot:title>登记立案流转数据</template>
          <template v-slot:chart>
            <barPie api="/ladsj/getDjlalzsj" :begin-time="beginTime" :end-time="endTime" title="登记立案流转数据"/>
          </template>
        </item>
        <item>
          <template v-slot:title>登记立案数据</template>
          <template v-slot:chart>
            <bar api="/ladsj/getDjlasj" :begin-time="beginTime" :end-time="endTime" title="登记立案数据"/>
          </template>
        </item>
        <item>
          <template v-slot:title>登记立案数据类型</template>
          <template v-slot:chart>
            <pie api="/ladsj/getDjlasjLx" :begin-time="beginTime" :end-time="endTime" title="登记立案数据类型"/>
          </template>
        </item>
<!--        <item>-->
<!--          <template v-slot:title>登记立案当事人数据</template>-->
<!--          <template v-slot:chart>-->
<!--            <barPie api="/ladsj/getDjladsrsj" api1="/ladsj/getDjladsrsjLx" :begin-time="beginTime" :end-time="endTime" id="Pie2" index="当年登记立案人数"/>-->
<!--          </template>-->
<!--        </item>-->
        <item>
          <template v-slot:title>立案实时数据</template>
          <template v-slot:chart>
            <ssaj api="/ladsj/getLasssj" :begin-time="beginTime" :end-time="endTime"/>
          </template>
        </item>
        <item class="nofile">
          <template v-slot:title>立案移送数据</template>
          <template v-slot:chart>
            <barPie api="/ladsj/getLayssj" :begin-time="beginTime" :end-time="endTime" id="Pie1" title="立案移送数据"/>
          </template>
        </item>
        <item class="nofile">
          <template v-slot:title>当日立案数据</template>
          <template v-slot:chart>
            <pieBars api="/ladsj/getDrlasj" api1="/ladsj/getDrlasjSp" :begin-time="beginTime" :end-time="endTime" title="当日立案数据"/>
          </template>
        </item>
        <item class="nofile">
          <template v-slot:title>当月立案数据</template>
          <template v-slot:chart>
            <pieBars api="/ladsj/getDylasj" api1="/ladsj/getDylasjSp" :begin-time="beginTime" :end-time="endTime" title="当月立案数据"/>
          </template>
        </item>
        <item class="nofile">
          <template v-slot:title>当季度立案数据</template>
          <template v-slot:chart>
            <pieBars api="/ladsj/getDjdlasj" api1="/ladsj/getDjdlasjSp" :begin-time="beginTime" :end-time="endTime" title="当季度立案数据"/>
          </template>
        </item>
        <item class="nofile">
          <template v-slot:title>当年立案数据</template>
          <template v-slot:chart>
            <pieBars api="/ladsj/getDnlasj" api1="/ladsj/getDnlasjSp" :begin-time="beginTime" :end-time="endTime" title="当年立案数据"/>
          </template>
        </item>
        <item>
          <template v-slot:title>诉前调解实时数据</template>
          <template v-slot:chart>
            <ssaj api="/ladsj/getSqtjsssj" :begin-time="beginTime" :end-time="endTime"/>
          </template>
        </item>
        <item>
          <template v-slot:title>诉前调解数据</template>
          <template v-slot:chart>
            <bar api="/ladsj/getSqtjsj" :begin-time="beginTime" :end-time="endTime" title="诉前调解数据"/>
          </template>
        </item>
      </div>
    </div>
  </div>
</template>

<script>
import {getCurTime, order,screenSize} from '@/assets/js/utils'
import $ from 'jquery'
import '@/utils/export'
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
export default {
  name: 'Home',
  components: {},
  data() {
    return {
      beginTime:new Date().getFullYear()+'-01-01',
      endTime:getCurTime(),
    }
  },
  computed: {

  },
  watch:{
  },
  created() {
  },
  mounted() {
    screenSize(this.$refs.editor);
  },
  methods: {
    show(){

    },
    btnWord(){
      // var down=window.localStorage.getItem('download')
      // var download=down.split('\n\n')
      // var list=['登记立案流转数据','登记立案数据','登记立案数据类型','立案移送数据','当日立案数据','当月立案数据','当季度立案数据','当年立案数据','诉前调解数据']
      // window.localStorage.setItem('download',order(download,list).substring(1))
      // $('.content').wordExport('立案大数据');
      this.axios.get("/ladsj/word",{
        params:{
          beginTime:this.beginTime,
          endTime:this.endTime
        },
        responseType:'blob'
      }).then(function(response){
        let blob=new Blob([response.data],{type:response.data.type})
        const fileName='立案大数据.docx'
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
.home-container .wrap .date {
  height: 50px;
  margin-top: -25px !important;
  float: right;
}


#topText{
  color: #00aeef;
  position: absolute;
  left: 37%;
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
