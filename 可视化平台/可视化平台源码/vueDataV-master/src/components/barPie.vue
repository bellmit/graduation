<!--
 描述: 立案大数据界面的柱状饼状图
-->
<template>
  <div class="container" style="height: 500px;">
    <div v-show="showChart" class="mychart" ref="chart_bp" style="width: 50%"></div>
    <div v-show="showPie1"  class='mychart' ref="chart_pp" style="width: 50%"></div>
    <div v-show="!showChart" style="height:500px;line-height:500px;text-align: center;font-size: 40px;color: #0a6aff">暂无数据</div>
  </div>
</template>

<script>
import {getBar,getPie,noData, showLoading} from '@/assets/js/utils'
export default {
  name: "barPie",
  props:[
    'api',
    // 'api1',
    'beginTime',
    'endTime',
    'title'
    // 'index'
  ],
  data() {
    return {
      showChart:true,
      showPie1:true,
      pieChart:null
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
    // this.barClick(this)
    // this.setChart(this.id)
    // this.pieShow(this.index)
    // let myChartContainer = document.getElementById("Pie1")
    // let that=this
    // // 获取自适应的高度和宽度
    // let resizeMyChartContainer = function () {
    //   myChartContainer.style.height = document.getElementsByClassName('mychart')[0].getBoundingClientRect().height + 'px';
    //   myChartContainer.style.width = document.getElementsByClassName('mychart')[0].getBoundingClientRect().width + 'px';
    // };
    // // 设置容器高和宽
    // resizeMyChartContainer();
    // let myPieChart = echarts.init(myChartContainer);
    // const option = {};
    // myPieChart.setOption(option);
    // // 自适应高和宽
    // // window.onresize = function () {
    // //   resizeMyChartContainer();
    // //   myChart.resize();
    // // }
    // window.addEventListener("resize",function(){
    //   myPieChart.resize();
    // })
  },
  methods: {
    getData(beginTime, endTime) {
      let endDate=new Date(endTime)
      let time=new Date(endDate.getTime()+86400000)
      let day=("0"+time.getDate()).slice(-2);
      let month=("0"+(time.getMonth()+1)).slice(-2);
      let endTime1=time.getFullYear()+'-'+month+'-'+day
      let that = this;
      let myChart = echarts.init(this.$refs.chart_bp)
      let myChart1 = echarts.init(this.$refs.chart_pp)
      myChart.showLoading(showLoading);
      myChart1.showLoading(showLoading);
      this.axios.get(this.api, {
        params: {
          beginTime: beginTime,
          endTime: endTime1
        }
      }).then(function (response) {
        if (response.data.code == 0) {
          if (response.data.chartData != null && response.data.chartData.length > 0) {
            let chart=response.data.chartData
            var download=window.localStorage.getItem('download')
            download=download+that.title+'\n'
            for(var i=0;i<chart.length;i++){
              download=download+'   '+(i+1)+'.'+chart[i].name+'为'+chart[i].value+'件\n'
            }
            download=download+'\n\n'
            window.localStorage.setItem('download',download)
            getBar(chart, myChart);
            if(chart[0].name=='登记立案数'){
              chart[0].name='登记立案非转立案数'
              chart[0].value=chart[0].value-chart[1].value
            }
            else if(chart[0].name=='立案数'){
              chart[0].name='立案非移送数'
              chart[0].value=chart[0].value-chart[1].value
            }
            getPie(chart, myChart1);
            that.showChart = true;
            that.showPie1=true;
          } else {
            that.showChart = false;
            that.showPie1=false;
            myChart.hideLoading()
            myChart1.hideLoading()
          }
        } else {
          that.$Toast({
            content: response.data.message,
            type: 'error',
          })
        }
      })
    },
    // setChart(id){
    //   let myChartContainer = document.getElementById(id)
    //   const that=this
    //   // 获取自适应的高度和宽度
    //   let resizeMyChartContainer = function () {
    //     myChartContainer.style.height = document.getElementsByClassName('mychart')[0].getBoundingClientRect().height + 'px';
    //     myChartContainer.style.width = document.getElementsByClassName('mychart')[0].getBoundingClientRect().width + 'px';
    //   };
    //   // 设置容器高和宽
    //   resizeMyChartContainer();
    //   this.pieChart = echarts.init(myChartContainer);
    //   const option = {};
    //   this.pieChart.setOption(option);
    //   // 自适应高和宽
    //   // window.onresize = function () {
    //   //   resizeMyChartContainer();
    //   //   myChart.resize();
    //   // }
    //   window.addEventListener("resize",function(){
    //     // resizeMyChartContainer();
    //     that.pieChart.resize();
    //   })
    // },
    // barClick(vm) {
    //   let myChart1 = echarts.init(this.$refs.chart_bp)
    //   myChart1.on('click', function (params) {
    //     let index = params.name
    //     vm.pieShow(index)
    //   })
    // },
    // pieShow(index) {
    //   let that = this
    //   let myChart2 = echarts.init(this.$refs.chart_pp)
    //   myChart2.showLoading(showLoading);
    //   this.axios.get(this.api1, {
    //     params: {
    //       index: index
    //     }
    //   }).then(function (response) {
    //     if (response.data.code == 0) {
    //       if (response.data.chartData != null && response.data.chartData.length > 0) {
    //         getTitlePie(response.data.chartData, myChart2,index);
    //         that.showPie1 = true;
    //       } else {
    //         that.showPie1 = false;
    //         myChart2.hideLoading()
    //       }
    //     } else {
    //       that.$Toast({
    //         content: response.data.message,
    //         type: 'error',
    //       })
    //     }
    //   })
    // },
    // changeSizePie() {
    //   let myChartContainer = document.getElementById('Pie1')
    //
    //   // 获取自适应的高度和宽度
    //   let resizeMyChartContainer = function () {
    //     myChartContainer.style.height = document.getElementsByClassName('mychart')[0].getBoundingClientRect().height + 'px';
    //     myChartContainer.style.width = document.getElementsByClassName('mychart')[0].getBoundingClientRect().width + 'px';
    //   };
    //   // 设置容器高和宽
    //   resizeMyChartContainer();
    //
    //   let myChart = echarts.init(this.$refs.chart_pp);
    //
    //   let option = {};
    //
    //   myChart.setOption(option);
    //   // 自适应高和宽
    //   window.onresize = function () {
    //     resizeMyChartContainer();
    //     myChart.resize();
    //   }
    // },
    // changeSizeBar() {
    //   var myChartContainer = document.getElementById('Bar1')
    //
    //   // 获取自适应的高度和宽度
    //   var resizeMyChartContainer = function () {
    //     myChartContainer.style.height = document.getElementsByClassName('mychart')[0].getBoundingClientRect().height + 'px';
    //     myChartContainer.style.width = document.getElementsByClassName('mychart')[0].getBoundingClientRect().width + 'px';
    //   };
    //   // 设置容器高和宽
    //   resizeMyChartContainer();
    //
    //   var myChart = echarts.init(myChartContainer);
    //
    //   let option = {};
    //
    //   myChart.setOption(option);
    //   // 自适应高和宽
    //   window.onresize = function () {
    //     resizeMyChartContainer();
    //     myChart.resize();
    //   }
    // },
    beforeDestroy() {

    }
  }
};

</script>

<style lang="scss" scoped>
.mychart{

  height:460px;
  margin-top: 40px;
  float: left
}


</style>