<template>
  <!--        展示图表-->
  <div class="panel" style="height: 500px;margin-top: 20px;float: left;width: 48.5%;margin-left: 1%;">
    <div class="title"><slot name="title"></slot></div>
    <slot name="chart" ></slot>
    <div class="panel-footer"></div>
  </div>
</template>

<script>
export default {
  name: "item"
}
</script>

<style lang="scss" scoped>
.title {
  position: absolute;
  height: 26px;
  font-size: 16px;
  color: #00c2ff;
  line-height: 26px;
  padding: 0 0 0 20px;
  left: 10px;
  right: 10px;
  top: 10px;
  &:before {
    content: "";
    position: absolute;
    left: 0;
    top: 50%;
    margin-top: -7px;
    width: 14px;
    height: 14px;
    background: url(../assets/img/sn-title.png) no-repeat 0% 0%;
  }
}
.panel{
  position: relative;
  border: 1px solid rgba(25, 186, 139, 0.17);
  background: rgba(10, 106, 255, 0.1) url(../assets/img/wg.png) repeat 50% 50% !important;
  background-blend-mode: screen;
  &:before {
    position: absolute;
    top: 0;
    left: 0;
    content: "";
    width: 10px;
    height: 10px;
    border-top: 2px solid #0a6aff;;
    border-left: 2px solid #0a6aff;
  }
  &:after {
    position: absolute;
    top: 0;
    right: 0;
    content: "";
    width: 10px;
    height: 10px;
    border-top: 2px solid #0a6aff;
    border-right: 2px solid #0a6aff;
  }

  .panel-footer {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    &:before {
      position: absolute;
      bottom: 0;
      left: 0;
      content: "";
      width: 10px;
      height: 10px;
      border-bottom: 2px solid #0a6aff;
      border-left: 2px solid #0a6aff;
    }
    &:after {
      position: absolute;
      bottom: 0;
      right: 0;
      content: "";
      width: 10px;
      height: 10px;
      border-bottom: 2px solid #0a6aff;
      border-right: 2px solid #0a6aff;
    }
  }
}
</style>