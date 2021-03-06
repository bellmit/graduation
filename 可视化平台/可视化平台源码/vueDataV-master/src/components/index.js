/*
 * 描述: 封装组件库
 * 作者: Jack Chen
 * 日期: 2020-04-18
 */

import bgAnimation from './bgAnimation' // 登录界面背景图动画
import modal from './modal' // 自定义全局模态框
import ywtpm from './ywtpm' //业务庭排名
import ssajxx from './ssajxx' //实时案件信息
import bar from './bar'
import areaPie from './areaPie'
import pie from './pie'
import ring from './ring'
import item from "@/components/item";
import itemTable from "@/components/itemTable"
import pies from "@/components/pies";
import bars from "./bars"
import tianjinMap from "./tianjinMap"
import line from './line'
import barPie from './barPie'
import pieBars from './pieBars'
import threeBars from './threeBars'
import threeBarsWithoutDes from './threeBarsWithoutDes'
import ssaj from './ssaj'
const components = {
  bgAnimation,
  modal,
  ywtpm,
  ssajxx,
  // 系统结构框架
  item,
  itemTable,
  // 具体的图表和描述
  bar,
  areaPie,
  pie,
  ring,
  pies,
  bars,
  tianjinMap,
  line,
  barPie,
  pieBars,
  threeBars,
  threeBarsWithoutDes,
  ssaj
};

const install = (Vue = {}) => {
  if (install.installed) return;
  Object.keys(components).forEach(component => {
    Vue.component(components[component].name, components[component]);
  });

  install.installed = true;
};

install.installed = false;

if (typeof window !== "undefined" && window.Vue) {
  install(window.Vue);
  install.installed = true;
}

const Vcomp = {
  ...components,
  install
};


export default Vcomp
