import JSON from './tianjin.json'
import './china'
// 屏幕缩放
export function screenSize(editorDom) {
  let screenWidth = document.body.clientWidth || document.documentElement.clientWidth;
  let screenHeight = document.body.clientHeight || document.documentElement.clientHeight;
  let defWidth = 1920;
  let defHeight = 1080;
  let xScale = screenWidth / defWidth;
  let yScale = screenHeight / defHeight;
  editorDom.style.cssText += ';transform: scale(' + xScale + ',' + yScale + ')';

  $(window).resize(() => {
    let screenWidth = document.body.clientWidth || document.documentElement.clientWidth;
    let screenHeight = document.body.clientHeight || document.documentElement.clientHeight;
    xScale = screenWidth / defWidth;
    yScale = screenHeight / defHeight;
    editorDom.style.cssText += ';transform: scale(' + xScale + ',' + yScale + ')';
  })
}
// 生成柱状图
export function getBar(data,myChart){
  myChart.hideLoading();
  let xData=[];
  let yData=[];
  for (let i=0;i<data.length;i++){
    xData.push(data[i].name);
    yData.push(data[i].value);
  }
  let itemStyle = {
    color: '#0084ff'
  };
  let option = {
    color:'#01d8ff',
    calculable: true,
    grid: {
      top: '10%',
      bottom: '30%'
    },
    xAxis: [{
      type: 'category',
      axisLabel: {
        interval: 0,
        rotate:20,
      },
      data: xData,
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee',
        }
      },
    }],
    yAxis: [{
      type: 'value',
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee'
        }
      }
    }],
    lineStyle: {
      color: '#179bf1'
    },
    label: {
      color: '#fff'
    },
    checkpointStyle: {
      color: '#01d8ff',
      symbolSize: 10,
      borderColor: 'rgba(1, 216, 255, 0.5)',
      borderWidth: 5,
    },
    series: [{
      data: yData,
      type: 'bar',
      itemStyle: itemStyle,
      barWidth: 35,
      legendHoverLink: true,
      label: {
        show: true,
        position: 'top',
        color: '#fff'
      }
    }]
  };
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
// 多根柱子的柱状图
export function getBars(myChart,source){
  myChart.hideLoading();
  let option = {
    color:['#179bf1', 'rgba(21,248,126,0.96)'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    legend: {
      show: true,
      x: 'center',
      bottom: '7%',
      itemGap: 20,
      textStyle: {
        fontSize: 14,
        color: '#fff'
      },
    },
    grid: {
      top: '10%',
      bottom: '20%'
    },
    dataset: {
      source:source
    },
    xAxis: {
      type: 'category',
      axisLabel: {
        interval: 0,
          rotate:10,
      },
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee',
        }
      },
    },
    yAxis: {
      type: 'value',
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee'
        }
      }
    },
    // Declare several bar series, each will be mapped
    // to a column of dataset.source by default.
    series: [
      {
        type: 'bar',
        legendHoverLink: true,
        label: {
          show: true,
          position: 'top',
          color: '#fff'
        },
        barGap:'0'
      },
      {
        type: 'bar',
        legendHoverLink: true,
        label: {
          show: true,
          position: 'top',
          color: '#fff'
        }
      }
    ]
  };
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
export function getThreeBars(myChart,source){
  myChart.hideLoading();
  let option = {
    color:['#179bf1', 'rgba(21,248,126,0.96)','#01d8ff'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    legend: {
      show: true,
      x: 'center',
      bottom: '7%',
      itemGap: 20,
      textStyle: {
        fontSize: 14,
        color: '#fff'
      },
    },
    grid: {
      top: '10%',
      bottom: '20%'
    },
    dataset: {
      source:source
    },
    xAxis: {
      type: 'category',
      axisLabel: {
        interval: 0,
        rotate:10,
      },
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee',
        }
      },
    },
    yAxis: {
      type: 'value',
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee'
        }
      }
    },
    // Declare several bar series, each will be mapped
    // to a column of dataset.source by default.
    series: [
      {
        type: 'bar',
        legendHoverLink: true,
        label: {
          show: true,
          position: 'top',
          color: '#fff'
        },
        barGap:'0'
      },
      {
        type: 'bar',
        legendHoverLink: true,
        label: {
          show: true,
          position: 'top',
          color: '#fff'
        }
      },
      {
        type: 'bar',
        legendHoverLink: true,
        label: {
          show: true,
          position: 'top',
          color: '#fff'
        }
      }
    ]
  };
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
// 生成折线图
export function getLine(xData,yData,id){
  let myChart = echarts.init(id);
  let option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        axisPointer: {
          type: 'cross',
          label: {
            backgroundColor: '#283b56'
          }
        }
      }
    },
    grid: {
      top: '10%',
      bottom: '30%',
      left:'3%',
      containLabel: true
    },
    color: ['#15a3dd'],
    calculable: true,
    xAxis: {
      type: 'category',
      data: xData,
      boundaryGap: false,
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee'
        }
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      scale: true,
      min: 0,
      boundaryGap:  false,
      axisTick: {
        show: false
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#9cc0ee'
        }
      },
      splitLine: {
        show: false
      },
    },
    series: [{
      type: 'line',
      xAxisIndex: 0,
      yAxisIndex: 0,
      itemStyle: {
        opacity: 0,
      },
      data: yData,
      smooth: true
    }]
  }

  myChart.setOption(option, true);

  window.addEventListener('resize', () => {
    myChart.resize();
  });

  // this.timer = setInterval(() => {
  //   for (let i = 0; i < 5; i++) {
  //     this.xData.shift();
  //     this.xData.push();
  //   }
  //
  //   myChart.setOption(this.option, true);
  // }, 2000);
}
// 下面图都是饼状图
// 生成普通的饼状图
export function getPie(pieData,myChart){
// 初始化echarts实例
  myChart.hideLoading();
  let data=[];
  let colors=['rgba(14,120,185,0.65)', 'rgba(77,234,148,0.64)', '#f48b3b','#8bec44'];
  for(let i=0;i<pieData.length;i++){
    data.push(
        {
          value: pieData[i].value,
          name:  pieData[i].name,
          itemStyle: {
            normal: {
              color: colors[i]
            }
          }
        }
    )
  }
  let option = {
    colors:['#0772bb', '#00ffff', '#f48b3b','#8bec44'],
    tooltip:{
      trigger:"item",
      formatter:"{b}</br>占比为:{d}%"
    },
    series: {
      type: 'pie',
      clockWise: true,
      startAngle: 90,
      radius:'60%',
      center: ['50%', '50%'],
      label: {
        normal: {
          show: true,
          position:"inner",
          formatter:"{d}%"
        },
      },
      data:data,
    },
    legend: {
      show: true,
      orient:'vertical',
      left:'left',
      itemGap: 20,
      textStyle: {
        fontSize: 14,
        color: '#fff'
      },
      formatter: function(name) {
        return echarts.format.truncateText(
              name,
              130,
              "14px Microsoft Yahei",
              "…"
          )
      },
    }
  }
  // 使用制定的配置项和数据显示图表
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
//生成带标题的饼状图
export function getTitlePie(pieData,myChart,index){
// 初始化echarts实例
  myChart.hideLoading();
  let data=[];
  let colors=['rgba(14,120,185,0.65)', 'rgba(77,234,148,0.64)', '#f48b3b','#8bec44'];
  for(let i=0;i<pieData.length;i++){
    data.push(
        {
          value: pieData[i].value,
          name:  pieData[i].name,
          itemStyle: {
            normal: {
              color: colors[i]
            }
          }
        }
    )
  }
  let option = {
    colors:['#0772bb', '#00ffff', '#f48b3b','#8bec44'],
    title: {
      text: index,
      left: 'center',
      textStyle:{
        color:'#ffffff'
      }
    },

    tooltip:{
      trigger:"item",
      formatter:"{b}</br>占比为:{d}%"
    },
    series: {
      type: 'pie',
      clockWise: true,
      startAngle: 90,
      radius:'60%',
      center: ['50%', '50%'],
      label: {
        normal: {
          show: true,
          position:"inner",
          formatter:"{d}%"
        },
      },
      data:data,
    },
    legend: {
      show: true,
      orient:'vertical',
      left:'left',
      itemGap: 20,
      textStyle: {
        fontSize: 14,
        color: '#fff'
      },
      formatter: function(name) {
        return echarts.format.truncateText(
            name,
            130,
            "14px Microsoft Yahei",
            "…"
        )
      },
    }
  }
  // 使用制定的配置项和数据显示图表
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
// 生成特殊的饼状图
export function getAreaPie(pieData,myChart) {
  myChart.hideLoading();
  let option = {
    color: ['#EAEA26', '#906BF9', '#FE5656', '#01E17E', '#3DD1F9', '#FFAD05', '#4465fc'],
    tooltip: {
      trigger: 'item',
      formatter: '{b} : {c} ({d}%)'
    },
    calculable: true,
    series: [{
      type: 'pie',
      radius: ['9%', '10%'],
      hoverAnimation: false,
      labelLine: {
        normal: {
          show: false,
          length: 30,
          length2: 50
        },
        emphasis: {
          show: false
        }
      },
      tooltip: {
        show: false
      },
      data: [{
        name: '',
        value: 0,
        itemStyle: {
          normal: {
            color: '#0B4A6B'
          }
        }
      }]
    }, {
      type: 'pie',
      radius: ['60%', '61%'],
      hoverAnimation: false,
      tooltip: {
        show: false
      },
      data: [{
        name: '',
        value: 0,
        itemStyle: {
          normal: {
            color: '#0B4A6B'
          }
        }
      }]
    },{
      stack: 'a',
      type: 'pie',
      radius: ['20%', '60%'],
      roseType: 'area',
      zlevel: 10,
      label: {
        normal: {
          show: true,
          formatter: '{b}:{d}%',
          textStyle: {
            fontSize: 14,
          },
          position: 'outside'
        },
        emphasis: {
          show: false
        }
      },
      labelLine: {
        normal: {
          show: true,
          lineStyle: {
            type: 'dotted'
          }
        },
        emphasis: {
          show: true
        }
      },
      data: pieData
    }]
  }

  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
// 生成圆环图
export function getRing(resData,myChart){
  // 初始化echarts实例
  myChart.hideLoading();
  let data=[];
  let colors=['#0772bb', '#00ffff', '#f48b3b','#8bec44'];
  for(let i=0;i<resData.length;i++){
    data.push(
        {
          value: resData[i].value,
          name:  resData[i].name,
          itemStyle: {
            normal: {
              color: colors[i]
            }
          }
        }
    )
  }
  let option = {
    colors:['#0772bb', '#00ffff', '#f48b3b','#8bec44'],
    series: {
      type: 'pie',
      clockWise: true,
      startAngle: 90,
      hoverAnimation: false,
      radius: ['60%', '45%'],
      center: ['50%', '50%'],
      label: {
        normal: {
          show: true,
          position:"inner",
          formatter:"{d}%"
        },
      },
      labelLine: {
        normal: {
          show: false
        }
      },
      data:data,
    },
    legend: {
      show: true,
      orient:'vertical',
      left:'left',
      itemGap: 20,
      textStyle: {
        fontSize: 14,
        color: '#fff'
      },
      data: name,
    }
  }
  // 使用制定的配置项和数据显示图表
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
// 生成嵌套饼状图
export function getPieAndRing(ringData,pieData,id){
  let myChart = echarts.init(id);
  let option = {
    color: ['rgba(113,234,127,0.69)', '#906BF9', '#FE5656', '#01E17E', '#3DD1F9', '#FFAD05', '#4465fc'],
    tooltip: {
      trigger: 'item',
      formatter: '{b}:({d}%)'
    },
    legend: {
      show: true,
      x: 'center',
      bottom: '2%',
      itemGap: 20,
      textStyle: {
        fontSize: 14,
        color: '#fff'
      },
      data: name,
    },
    series: [
      {
        type: 'pie',
        radius: [0, '30%'],
        label: {
          position: 'inner',
          fontSize: 12,
        },
        labelLine: {
          show: false
        },
        data: pieData,
      },
      {
        type: 'pie',
        radius: ['45%', '60%'],
        label: {
          normal: {
            show: true,
            formatter: '{b}:{d}%',
            textStyle: {
              fontSize: 14,
            },
            position: 'outside'
          },
          emphasis: {
            show: false
          }
        },
        labelLine: {
          normal: {
            show: true,
            lineStyle: {
              type: 'dotted'
            }
          },
          emphasis: {
            show: true
          }
        },
        data: ringData
      }
    ]
  };
  // 使用制定的配置项和数据显示图表
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
// 生成双饼图
export function getPies(dim,pieData,myChart){
  let source=[];
  source.push(dim);
  for(let i=0;i<pieData.length;i++){
    source.push(pieData[i]);
  }
  myChart.hideLoading();
  // 将dim加入source
  let option = {
    color: ['#3382f7', 'rgba(254,86,86,0.82)', '#01E17E', '#3DD1F9', '#FFAD05', '#4465fc'],
    legend: {
      show: true,
      x: 'center',
      bottom: '2%',
      itemGap: 20,
      textStyle: {
        fontSize: 14,
        color: '#fff'
      },
      data: name,
    },
    tooltip: {},
    dataset: {
      source: source
    },
    title: [
      {
        text:'原告类型',
        left:'19%',
        top:'15%',
        textStyle: {
          fontSize: 16,
          color: '#20bbdd',
        },
      },
      {
        text: '被告类型',
        left:'59%',
        top:'15%',
        textStyle: {
          fontSize: 16,
          color: '#20bbdd'
        },
      }
    ],
    series: [{
      type: 'pie',
      radius: '40%',
      center: ['25%', '50%'],
      label: {
        normal: {
          show: true,
          position:"inner",
          formatter:"{d}%"
        },
      },
      labelLine: {
        normal: {
          show: false
        }
      },
      encode:{
        itemName:'当事人诉讼地位',
        value:'原告'
      }
    }, {
      type: 'pie',
      radius: '40%',
      center: ['65%', '50%'],
      label: {
        normal: {
          show: true,
          position:"inner",
          formatter:"{d}%"
        },
      },
      labelLine: {
        normal: {
          show: false
        }
      },
      encode:{
        itemName:'当事人诉讼地位',
        value:'被告'
      }
    }]
  };
  // 使用制定的配置项和数据显示图表
  myChart.setOption(option, true);
  window.addEventListener('resize', () => {
    myChart.resize();
  });
}
// 天津地图
export function getTianJinMap(myChart,data){
  let max=0;
  myChart.hideLoading();
  for(let i=0;i<data.length;i++){
    max=Math.max(data.value,max)
  }
  echarts.registerMap('tianjin',JSON,{})
  let option = {
    tooltip: {
      trigger: 'item',
      showDelay: 0,
      transitionDuration: 0.2,
      formatter: function (params) {
        let value=0;
        if(params.value){
          value=params.value
        }
        return params.seriesName + '<br/>' + params.name + ': ' + value;
      }
    },
    visualMap: {
      left: 'right',
      min: 1,
      max: 1000,
      inRange: {
        color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
      },
      textStyle:{
        color:'#fff',
      },
      // 文本，默认为数值文本
      calculable: true
    },
    series: {
      name:'案件量',
      type:'map',
      map: "tianjin",
      label: {
        emphasis: {
          show: true,
          color: "#fff"
        }
      },
      data:data,
      roam: true,
      //   放大我们的地图
      zoom: 1,
      itemStyle: {
        normal: {
          areaColor: "rgba(43, 196, 243, 0.42)",
          borderColor: "rgba(43, 196, 243, 1)",
          borderWidth: 1
        },
        emphasis: {
          areaColor: "#2B91B7"
        }
      }
    },
  }
  myChart.setOption(option, true);
  window.addEventListener("resize", () => {
    myChart.resize();
  });
}
// 格式化当前时间
export function getCurTime() {
  let time=new Date();
  let day=("0"+time.getDate()).slice(-2);
  let month=("0"+(time.getMonth()+1)).slice(-2);
  return time.getFullYear()+'-'+month+'-'+day
}
export function nextMonth(){
  let date=new Date()
  let nextMonth=date.getMonth()+1
  date.setMonth(nextMonth)
  date.setDate(1)
  let time=new Date(date.getTime()-86400000)
  let day=("0"+time.getDate()).slice(-2);
  let month=("0"+(time.getMonth()+1)).slice(-2);
  return time.getFullYear()+'-'+month+'-'+day
}
export function order(download,list){
  var result=''
  for(let i=0;i<list.length;i++){
    for(let j=0;j<download.length;j++){
      if(download[j][0]!='\n'){
        if(list[i]==download[j].substring(0,download[j].indexOf('\n'))){
          result=result+download[j]+'\n\n'
          break
        }
      }
      else {
        if (list[i] == download[j].substring(1, download[j].indexOf('\n',1))) {
          result = result + download[j] + '\n\n'
          break
        }
      }
    }
  }
  return result
}
// 加载的样式
export let showLoading={text:'正在加载数据',maskColor:'rgba(72,157,226,0)',color:'#8e8eed',textColor:'#8e8eed'}
// 没有数据显示的样式
export let noData='<div style="margin-top: 20%;margin-left:10%;line-height:140px;text-align: center;font-size: 40px;color: #0a6aff">暂无数据</div>';