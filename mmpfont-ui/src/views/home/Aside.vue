<template>
  <div>
    <!-- 系统 Logo -->
    <el-aside class="header-logo" :width="asideWidth">
      <div @click="$router.push({ name: 'Home' })">
        <a v-if="foldAside">后台管理中心</a>
        <a v-else>后台</a>
      </div>
    </el-aside>
    <el-aside class="aside" :width="asideWidth" :class='"icon-size-" + iconSize'>
      <el-scrollbar style="height: 100%; width: 100%;">
        <!--
                default-active 表示当前选中的菜单，默认为 home。
                collapse 表示是否折叠菜单，仅 mode 为 vertical（默认）可用。
                collapseTransition 表示是否开启折叠动画，默认为 true。
                background-color 表示背景颜色。
                text-color 表示字体颜色。
            -->
        <el-menu :default-active="menuActiveName || 'home'" :collapse="!foldAside" :collapseTransition="false"
                 background-color="#263238" text-color="#8a979e">
          <el-menu-item index="home" @click="$router.push({ name: 'Home' })">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-submenu index="demo">
            <template slot="title">
              <i class="el-icon-star-off"></i>
              <span>demo</span>
            </template>
            <el-menu-item index="demo-echarts" @click="$router.push({ name: 'SupplierMaintenance' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">echarts</span>
            </el-menu-item>
            <el-menu-item index="demo-ueditor" @click="$router.push({ name: 'PurchaseLnbound' })">
              <i class="el-icon-document"></i>
              <span slot="title">ueditor</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="demo2">
            <template slot="title">
              <i class="el-icon-star-off"></i>
              <span>系统管理</span>
            </template>
            <el-menu-item index="demo-na" @click="$router.push({ name: 'na' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">通知公告</span>
            </el-menu-item>
            <el-menu-item index="demo-olm" @click="$router.push({ name: 'olm' })">
              <i class="el-icon-document"></i>
              <span slot="title">操作日志管理</span>
            </el-menu-item>
            <el-menu-item index="demo-cem" @click="$router.push({ name: 'cem' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">检查费用设置</span>
            </el-menu-item>
            <el-menu-item index="demo-lotlm" @click="$router.push({ name: 'lotlm' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">登录日志管理</span>
            </el-menu-item>
            <el-menu-item index="demo-dm" @click="$router.push({ name: 'dm' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">字典管理</span>
            </el-menu-item>
            <el-menu-item index="demo-rfs" @click="$router.push({ name: 'rfs' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">挂号费用设置</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="demo3">
            <template slot="title">
              <i class="el-icon-star-off"></i>
              <span>收费管理</span>
            </template>
            <el-menu-item index="demo-pc" @click="$router.push({ name: 'pc' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">处方收费</span>
            </el-menu-item>
            <el-menu-item index="demo-ri" @click="$router.push({ name: 'ri' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">退费查询</span>
            </el-menu-item>
            <el-menu-item index="demo-pc" @click="$router.push({ name: 'PrescriptionDispensing' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">处方发药</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="看病就诊">
            <template slot="title">
              <i class="el-icon-star-off"></i>
              <span>看病就诊</span>
            </template>
            <el-menu-item index="demo-pc" @click="$router.push({ name: 'NewVisits' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">新开就诊</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="药品进销存">
            <template slot="title">
              <i class="el-icon-star-off"></i>
              <span>药品进销存</span>
            </template>
            <el-menu-item index="demo-ManufacturerMaintenance" @click="$router.push({ name: 'ManufacturerMaintenance' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">生产厂家维护</span>
            </el-menu-item>
            <el-menu-item index="demo-DrugLnformationMaintenance" @click="$router.push({ name: 'DrugLnformationMaintenance' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">药品信息维护</span>
            </el-menu-item>
            <el-menu-item index="demo-SupplierMaintenance" @click="$router.push({ name: 'SupplierMaintenance' })">
              <i class="el-icon-document"></i>
              <span slot="title">供应商维护</span>
            </el-menu-item>
            <el-menu-item index="demo-PurchaseLnbound" @click="$router.push({ name: 'PurchaseLnbound' })">
              <i class="el-icon-document"></i>
              <span slot="title">采购入库列表</span>
            </el-menu-item>
            <el-menu-item index="demo-InboundAudit" @click="$router.push({ name: 'InboundAudit' })">
              <i class="el-icon-document"></i>
              <span slot="title">入库审核</span>
            </el-menu-item>
            <el-menu-item index="demo-InventoryInquiry" @click="$router.push({ name: 'InventoryInquiry' })">
              <i class="el-icon-document"></i>
              <span slot="title">库存查询</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="kbjz">
            <template slot="title">
              <i class="el-icon-star-off"></i>
              <span>看病就诊</span>
            </template>
            <el-menu-item index="demo-or" @click="$router.push({ name: 'or' })">
              <i class="el-icon-s-data"></i>
              <span slot="title">门诊挂号</span>
            </el-menu-item>

          </el-submenu>
        </el-menu>
      </el-scrollbar>
    </el-aside>
  </div>
</template>

<script>
export default {
  name: 'Aside',
  props: ['foldAside'],
  data() {
    return {
      // 保存当前选中的菜单
      menuActiveName: 'home',
      // 保存当前侧边栏的宽度
      asideWidth: '200px',
      // 用于拼接当前图标的 class 样式
      iconSize: 'true'
    }
  },
  watch: {
    // 监视是否折叠侧边栏，折叠则宽度为 64px。
    foldAside(val) {
      this.asideWidth = val ? '200px' : '64px'
      this.iconSize = val
    }
  }
}
</script>

<style>
.aside {
  margin-bottom: 0;
  height: 100%;
  max-height: calc(100% - 50px);
  width: 100%;
  max-width: 200px;
  background-color: #263238;
  text-align: left;
  right: 0;
}

.header-logo {
  background-color: #17b3a3;
  text-align: center;
  height: 50px;
  line-height: 50px;
  width: 200px;
  font-size: 24px;
  color: #fff;
  font-weight: bold;
  margin-bottom: 0;
  cursor: pointer;
}
.el-submenu .el-menu-item {
  max-width: 200px !important;
}
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}
.icon-size-false i {
  font-size: 30px !important;
}
.icon-size-true i {
  font-size: 18px !important;
}
</style>