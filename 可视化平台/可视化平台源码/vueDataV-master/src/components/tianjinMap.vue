<!--
 描述: 天津地图
-->
<template>
  <div class="container" style="height: 500px;">
    <div class="mychart" ref="map"></div>
    <div class='describe'>
      <div style="margin-left: 40px">
        <table border="0" width="200px">
          <tr>
            <th>地区</th>
            <th>案件数</th>
          </tr>
          <tr v-for="(item) in description">
            <td align="center">{{item.name}}</td>
            <td align="center">{{item.value}}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import {getTianJinMap, showLoading} from '@/assets/js/utils'
export default {
  name: "tianjinMap",
  props:[
    'api',
    'beginTime',
    'endTime',
    'title'
  ],
  data() {
    return {
      descriptionTitle:'各地区分布',
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
  },
  methods: {
      getData(beginTime,endTime){
        let endDate=new Date(endTime)
        let time=new Date(endDate.getTime()+86400000)
        let day=("0"+time.getDate()).slice(-2);
        let month=("0"+(time.getMonth()+1)).slice(-2);
        let endTime1=time.getFullYear()+'-'+month+'-'+day
        let that=this;
        let myChart = echarts.init(that.$refs.map);
        myChart.showLoading(showLoading);
        this.axios.get(this.api,{
          params:{
            beginTime:beginTime,
            endTime:endTime1
          }
        }).then(function (response){
          if (response.data.code==0){
            let chart=response.data.chartData
            var download=window.localStorage.getItem('download')
            download=download+that.title+'\n'
            for(var i=0;i<chart.length;i++){
              if(chart[i].value>0) {
                download = download + '   ' +(i+1)+'.'+ chart[i].name + '为' + chart[i].value + '件' + '\n'
              }
            }
            download=download+'\n'+'\n'
            window.localStorage.setItem('download',download)
            getTianJinMap(myChart,response.data.chartData);
            that.description=response.data.chartData;
          } else{
            that.$Toast({
              content: response.data.message,
              type: 'error',
            })
          }
        })
      }
  },
  beforeDestroy() {

  }
};
</script>

<style lang="scss" scoped>
td{

}
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
  margin-top: 70px;
  padding-bottom: 25%;
  font-size: 16px;
  color: #00c2ff;
  line-height: 26px;
}
</style>
