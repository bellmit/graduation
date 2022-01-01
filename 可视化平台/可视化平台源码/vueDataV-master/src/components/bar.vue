<!--
 描述: 案件审结情况之案件量柱状图
-->
<template>
  <div class="container" style="height: 500px;">
    <div v-show="showChart" class="mychart" ref="chart_bp"></div>
    <div v-show="showChart" class='describe'>
      <div class='title'>{{descriptionTitle}}</div>
      <ul>
        <li v-for="(item,index) in description">
          （{{index+1}}）{{item}}
        </li>
      </ul>
    </div>
    <div v-show="!showChart" style="height:500px;line-height:500px;text-align: center;font-size: 40px;color: #0a6aff">暂无数据</div>
  </div>
</template>

<script>
import {getBar, noData, showLoading} from '@/assets/js/utils'
export default {
  name: "bar",
  props:[
    'api',
    'api1',
    'beginTime',
    'endTime',
    'title',
    'topText'
  ],
  data() {
    return {
        showChart:true,
        descriptionTitle:'',
        description:[]
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
  mounted() {
    this.getData(this.beginTime,this.endTime);
    this.barClick(this);
  },
  methods: {
    getData(beginTime, endTime) {
      let endDate = new Date(endTime)
      let time = new Date(endDate.getTime() + 86400000)
      let day = ("0" + time.getDate()).slice(-2);
      let month = ("0" + (time.getMonth() + 1)).slice(-2);
      let endTime1 = time.getFullYear() + '-' + month + '-' + day
      let that = this;
      let myChart = echarts.init(this.$refs.chart_bp)
      myChart.showLoading(showLoading);
      this.axios.get(this.api, {
        params: {
          beginTime: beginTime,
          endTime: endTime1
        }
      }).then(function (response) {
        if (response.data.code == 0) {
          if (response.data.chartData != null && response.data.chartData.length > 0) {
            var download = window.localStorage.getItem('download')
            download = download + that.title + '\n'
            getBar(response.data.chartData, myChart);
            that.showChart = true;
            that.description = response.data.description;
            for (let i = 0; i < response.data.chartData.length; i++) {
              download = download + '   ' + (i + 1) + '.' + response.data.chartData[i].name + '为' + response.data.chartData[i].value + '件\n'
            }
            download = download + '\n\n'
            window.localStorage.setItem('download', download)
            that.descriptionTitle = response.data.title;

          } else {
            that.showChart = false;
            myChart.hideLoading()
          }
        } else {
          that.$Toast({
            content: response.data.message,
            type: 'error',
          })
        }
      })
    },
    // 条状图上面的按钮可以点击查看案件列表
    barClick(vm) {
      let myChart1 = echarts.init(this.$refs.chart_bp)
      myChart1.on('click', function (params) {
        let index = params.name
        vm.listShow(index)
      })
    },
    listShow(index) {
      if(this.topText=='审判流程监督可视化系统') {
        window.sessionStorage.setItem("index", index);
        window.sessionStorage.setItem("api", this.api1);
        window.sessionStorage.setItem("beginTime", this.beginTime)
        window.sessionStorage.setItem("endTime", this.endTime)
        window.sessionStorage.setItem("title",this.title)
        window.open("#/bjAjList", '_blank')
      }
    }
  },
  beforeDestroy() {

  }
};
</script>

<style lang="scss" scoped>
.mychart{
  width: 67%;
  height:460px;
  margin-top: 40px;
  float: left
}
.describe{
  .title{
    margin-bottom: 5px;
    font-size: 18px;
    color: #489de2;
    line-height: 30px;
  }
  width: 33%;
  float: left;
  margin-top: 100px;
  padding-bottom: 25%;
  font-size: 16px;
  color: #00c2ff;
  line-height: 26px;
}
</style>
