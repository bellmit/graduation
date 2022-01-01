<template>
  <div class="container" style="height: 500px;">
    <!--    图表显示-->
    <div v-show="showChart" class="mychart" ref="ring">
      <!--      饼图需要向里面传递数据-->
    </div>
    <!--    文字描述-->
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
import {getRing, showLoading} from '@/assets/js/utils'
export default {
  props:[
    'api',
    'beginTime',
    'endTime',
    'title'
  ],
  name: "ring",
  data() {
    return {
      // 异步获取需要的格式
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
  },
  methods: {
    getData(beginTime,endTime){
      let endDate=new Date(endTime)
      let time=new Date(endDate.getTime()+86400000)
      let day=("0"+time.getDate()).slice(-2);
      let month=("0"+(time.getMonth()+1)).slice(-2);
      let endTime1=time.getFullYear()+'-'+month+'-'+day
      let that=this
      let myChart=echarts.init(that.$refs.ring);
      myChart.showLoading(showLoading);
      this.axios.get(this.api,{
        params:{
          beginTime:beginTime,
          endTime:endTime1
        }
      }).then(function (response){
        if (response.data.code==0){
          if(response.data.chartData!=null&&response.data.chartData.length>0){
            var download=window.localStorage.getItem('download')
            download=download+that.title+'\n'
            getRing(response.data.chartData,myChart);
            that.showChart=true;
            that.description=response.data.description;
            for(let i=0;i<response.data.chartData.length;i++){
              download=download+'   '+(i+1)+'.'+response.data.chartData[i].name+'为'+response.data.chartData[i].value+'件\n'
            }
            download=download+'\n\n'
            window.localStorage.setItem('download',download)
            that.descriptionTitle=response.data.title;
          }else{
            myChart.hideLoading();
            that.showChart=false;
          }
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
.mychart{
  width: 67%;
  height:400px;
  float: left;
  margin-top: 40px
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
