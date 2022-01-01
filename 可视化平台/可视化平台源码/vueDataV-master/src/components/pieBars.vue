<!--
 描述: 立案大数据界面的饼状双树状图
-->
<template>
  <div class="container" style="height: 500px;">
    <div v-show="showChart" id="Pie1" class="mychart" ref="chart_bp" style="width: 50%"></div>
    <div v-show="showChart"  id="Bar1" class='mychart' ref="chart_pp" style="width: 50%"></div>
    <div v-show="!showChart" style="height:500px;line-height:500px;text-align: center;font-size: 40px;color: #0a6aff">暂无数据</div>
  </div>
</template>

<script>
import {getBars,getPie,noData, showLoading} from '@/assets/js/utils'
export default {
  name: "pieBars",
  props:[
    'api',
    'api1',
    'beginTime',
    'endTime',
    'title'
  ],
  data() {
    return {
      showChart:true,
      source: []
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
    var myChartContainer = document.getElementById('Bar1')
    // 获取自适应的高度和宽度
    var resizeMyChartContainer = function () {
      myChartContainer.style.height = document.getElementsByClassName('mychart')[0].getBoundingClientRect().height + 'px';
      myChartContainer.style.width = document.getElementsByClassName('mychart')[0].getBoundingClientRect().width + 'px';
    };
    // 设置容器高和宽
    resizeMyChartContainer();
    var myChart = echarts.init(this.$refs.chart_pp);
    let option = {};
    myChart.setOption(option);
    // 自适应高和宽
    window.onresize = function () {
      resizeMyChartContainer();
      myChart.resize();
    }
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
      let myChart1=echarts.init(this.$refs.chart_pp)
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
            getPie(response.data.chartData, myChart);
            that.showChart = true;
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
      this.axios.get(this.api1,{
        params:{
          beginTime:beginTime,
          endTime:endTime1
        }
      }).then(function (response){
        if (response.data.code==0){
          that.source=[]
          let data=response.data.chartData;
          let t=['product',data.name1,data.name2]
          that.source.push(t);
          let arr=data.k2vModelList
          for (let i=0;i<arr.length;i++){
            let k=[arr[i].name,arr[i].value1,arr[i].value2]
            that.source.push(k);
          }
          var download=window.localStorage.getItem('download')
          download=download+that.title+'\n'
          for(let i=0;i<arr.length;i++){
            download+='   '+(i+1)+'.'+arr[i].name+':'+data.name1+'为'+arr[i].value1+'件,'+data.name2+'为'+arr[i].value2+'件\n'
          }
          download=download+'\n\n'
          window.localStorage.setItem('download',download)
          getBars(myChart1,that.source);
        } else{
          // 失败
          that.$Toast({
            content: response.data.message,
            type: 'error',
          })
        }
      })
    },
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