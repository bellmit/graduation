<!--
 描述: 新闻无缝滚动
 作者: Jack Chen
 日期: 2020-04-18
-->

<template>
  <div class="wrap-container sn-container">
    <div class="sn-content">
      <div class="sn-body">
        <div class="wrap-container">

          <div class="table">
            <table border="0" cellpadding="0" cellspacing="0" class="table-header">
              <tbody>
              <tr>
                <td width="30%">
                  <span>案 号</span>
                </td>
                <td width="40%">
                  <span>案件名称</span>
                </td>
                <td width="30%">
                  <span>立案日期</span>
                </td>
              </tr>
              </tbody>
            </table>

            <vue-seamless-scroll :data="listData" class="seamless-warp" :class-option="optionSetting">
              <table border="0" cellpadding="0" cellspacing="0" class="item">
                <tbody>
                <tr v-for="(item, index) in listData" :key="index">
                  <td width="30%">
                    <span>{{ item.ah }}</span>
                  </td>
                  <td width="40%">
                    <span>{{ item.ajmc }}</span>
                  </td>
                  <td width="30%">
                    <span>{{ item.larq }}</span>
                  </td>
                </tr>
                </tbody>
              </table>
            </vue-seamless-scroll>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import vueSeamlessScroll from 'vue-seamless-scroll'

export default {
  name: "ssaj",
  props:[
    'api',
    'beginTime',
    'endTime'
  ],
  components: {
    vueSeamlessScroll
  },
  data() {
    return {
      listData: []
    }
  },
  watch:{
    beginTime:function (newValue,oldValue){
      // 监听到变化需要重新发起请求
      this.getData(this.beginTime,this.endTime);
    },
    endTime:function (newValue,oldValue){
      this.getData(this.beginTime,this.endTime);
    }
  },
  created() {
    // 实现轮询
    window.setInterval(() => {
      setTimeout(this.getData(this.beginTime,this.endTime), 0);
    }, 180000);
  },
  computed: {
    optionSetting () {
      return {
        step: 0.5, // 数值越大速度滚动越快
        limitMoveNum: 2, // 开始无缝滚动的数据量 this.dataList.length
        hoverStop: true, // 是否开启鼠标悬停stop
        direction: 1, // 0向下 1向上 2向左 3向右
        // autoPlay: false,
        openWatch: true, // 开启数据实时监控刷新dom
        singleHeight: 0, // 单步运动停止的高度(默认值0是无缝不停止的滚动) direction => 0/1
        singleWidth: 0, // 单步运动停止的宽度(默认值0是无缝不停止的滚动) direction => 2/3
        waitTime: 1000 // 单步运动停止的时间(默认值1000ms)
      }
    }
  },
  mounted() {
    this.getData(this.beginTime,this.endTime);

  },
  methods: {
    getData(beginTime,endTime) {
      let endDate = new Date(endTime)
      let time = new Date(endDate.getTime() + 86400000)
      let day = ("0" + time.getDate()).slice(-2);
      let month = ("0" + (time.getMonth() + 1)).slice(-2);
      let endTime1 = time.getFullYear() + '-' + month + '-' + day
      let that = this;
      this.axios.get(that.api,{
        params:{
          beginTime:beginTime,
          endTime:endTime1
        }
      }).then(function (response){
        if (response.data.code==0){
          that.listData=response.data.chartData;
        } else{
          // 失败
        }
      }).catch(function (error){
        //
      });
    }

  },
  beforeDestroy() {

  }
};
</script>

<style lang="scss" scoped>
.sn-container {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 950px;
  height: 500px;
  background-blend-mode: screen;
  overflow: hidden;
  border: none;
  background: none !important;
}

.sn-container {
  %table-style {
    width: 100%;
    height: 35px;
    table-layout: fixed;
    tr {
      td {
        padding: 10px 5px;
        text-align: center;
        background-color: transparent;
        //white-space: nowrap;
        overflow: hidden;
        color: #E2E5FF;
        font-size: 14px;
      }
    }
  }
  .table {
    .table-header {
      @extend %table-style;
    }
    .seamless-warp {
      height: 400px;
      overflow: hidden;
      visibility: visible;
      .colorRed {
        color: #FF4669;
      }
      .colorOrange {
        color: #FFC956;
      }
      .item {
        height: auto;
        @extend %table-style;
        tr {
          td {
            &.title {
              text-overflow: ellipsis;
              display: inline-block;
            }
          }
        }
      }
    }
  }


}
</style>
