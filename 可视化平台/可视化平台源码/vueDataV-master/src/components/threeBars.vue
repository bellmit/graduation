<!--
 描述: 案件审结情况之案件量柱状图
-->
<template>
  <div class="container" style="height: 500px;">
    <div class="mychart" ref="bars"></div>
    <div class='describe'>
      <div class='title'>{{descriptionTitle}}</div>
      <ul>
        <li v-for="(item,index) in description">
          （{{index+1}}）{{item}}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {getThreeBars, showLoading} from '@/assets/js/utils'

export default {
  name: "threeBars",
  props:[
    'api',
    'api1',
    'api2',
    'beginTime',
    'endTime',
    'title1',
    'topText'
  ],
  data() {
    return {
      title:"",
      source: [],
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
      let endDate=new Date(endTime)
      let time=new Date(endDate.getTime()+86400000)
      let day=("0"+time.getDate()).slice(-2);
      let month=("0"+(time.getMonth()+1)).slice(-2);
      let endTime1=time.getFullYear()+'-'+month+'-'+day
      let that = this;
      let myChart = echarts.init(that.$refs.bars);
      myChart.showLoading(showLoading);
      this.axios.get(this.api, {
        params: {
          beginTime: beginTime,
          endTime: endTime1
        }
      }).then(function (response) {
        if (response.data.code == 0) {
          that.source = []
          let data = response.data.chartData;
          let t = ['product', data.name1, data.name2, data.name3]
          that.source.push(t);
          let arr = data.k3vModelList
          for (let i = 0; i < arr.length; i++) {
            let k = [arr[i].name, arr[i].value1, arr[i].value2, arr[i].value3]
            that.source.push(k);
          }
          var download=window.localStorage.getItem('download')
          download=download+that.title1+'\n'
          for(let i=0;i<arr.length;i++){
            download+='   '+(i+1)+'.'+arr[i].name+':'+data.name1+'为'+arr[i].value1+'件,'+data.name2+'为'+arr[i].value2+'件,'+data.name3+'为'+arr[i].value3+'件\n'
          }
          download=download+'\n\n'
          window.localStorage.setItem('download',download)
          getThreeBars(myChart, that.source);
          that.description = response.data.description;
          that.descriptionTitle = response.data.title;
        } else {
          // 失败
          that.$Toast({
            content: response.data.message,
            type: 'error',
          })
        }
      })
    },

    barClick(vm) {
      let myChart1 = echarts.init(this.$refs.bars)
      myChart1.on('click', function (params) {
        let index = params.name
        vm.listShow(index)
      })
    },
    listShow(index) {
      if(this.topText=='赡养纠纷案件专题分析系统') {
        window.localStorage.setItem("index", index);
        window.localStorage.setItem("api", this.api1);
        window.localStorage.setItem("api1", this.api2);
        window.localStorage.setItem("beginTime", this.beginTime)
        window.localStorage.setItem("endTime", this.endTime)
        window.open("#/ajList", '_blank')
      }
    },
    beforeDestroy() {

    }
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
ul{
  height:405px;
  overflow-y: auto;
}
</style>
