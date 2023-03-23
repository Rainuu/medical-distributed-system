<template>
    <el-container class="home-container">
      <!-- 侧边栏 -->
      <el-aside :width="asideWidth">
        <div style="margin-left: 15px">
          <!-- logo -->
          <img  src="../assets/logo.jpeg" width="30" height="30" style="border-radius: 10px" alt="">
          <!-- 顶部标题 -->
          <a v-if="!isCollaps" style="color: #eeeeee">医院挂号管理系统</a>
        </div>
        <!-- 侧边栏菜单 -->
        <el-menu
            background-color="#333744"
            text-color="#fff"
            active-text-color="#ffd04b" router
            :default-active="$route.path"
            :collapse="this.isCollaps"
            class="el-menu-vertical-demo"
            :collapse-transition="false"

        >
          <!-- 一级菜单 -->
          <el-submenu :index="first.path" v-for="(first,index) in leftMenu" :key="index">
            <!-- 一级菜单模板 -->
            <template slot="title" >
              <!-- 图标 -->
<!--              <el-icon :is="first.icon"></el-icon>-->
              <el-icon :name="first.icon"></el-icon>
              <!-- 文本 -->
              <span style="margin-left: 15px" >{{first.menuName}}</span>
            </template>
            <!-- 二级子菜单 -->
            <el-menu-item :index="second.path" v-for="(second,sindex) in first.children" :key="sindex">
              <!-- 二级菜单模板 -->
              <template slot="title">
                <!-- 图标 -->
                <el-icon :name="second.icon"></el-icon>
                <!-- 文本 -->
                <span style="margin-left: 15px" @click="showContent(second.path,second.menuName)">{{second.menuName}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
        <!-- 页面主体区域 -->
        <el-container>
          <!-- 头部区域 -->
          <el-header>
            <div style="margin-left: 15px">
              <div class="header-title" @click="foldOrOpen">
                <a class="el-icon-s-fold" v-if="this.isCollaps" title="折叠侧边栏" />
                <a class="el-icon-s-unfold" v-else title="展开侧边栏" />
              </div>
            </div>
            <el-dropdown :hide-on-click="false">
              <el-avatar  :src="this.userinfo.picture"></el-avatar>
              <span style="font-weight: bolder;color: white;">{{userinfo.userName}}</span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <svg-icon icon-file-name="user"/>
                  个人中心</el-dropdown-item>
                <el-dropdown-item>
                  <svg-icon icon-file-name="lock"/>
                  修改密码</el-dropdown-item>
                <el-dropdown-item>
                  <svg-icon icon-file-name="component"/>
                  退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-header>
            <!-- 主体结构 -->
            <el-main>
                <!--标签页-->
                <el-tabs type="card"   v-model="editableTabsValue" closable @tab-remove="removeTab" @tab-click="changeTab">
                    <el-tab-pane
                       v-for="(item,index) in editableTabs"
                       :key="item.name"
                       :label="item.title"
                       :name="item.name"
                       :url="item.url"
                       >
                        <span slot="label"><i class="el-icon-date"></i> {{item.title}}</span>
                        <div style="height: 100%">
                            <keep-alive>
                                <router-view v-if="item.title === editableTabsValue"/>
                            </keep-alive>
                        </div>

                    </el-tab-pane>
                </el-tabs>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "Index",
        data(){
          return {
            asideWidth: '200px',
            isCollaps:false,
               userinfo:{},
               leftMenu:[],
              //标签页 开始
              editableTabsValue: '1',
              tabIndex: 1,
              editableTabs: [{
                  title: '首页',
                  name: '1',
                  url: '/index'
                  //content: 'Tab 1 content'
              }],
          }
        },
        created() {
            console.log(this)
          this.info()
          this.initLiftMenu()
        },
        methods:{
          initLiftMenu(){
            this.$axios.get("/system/api/menu/leftMenu").then(res=>{
              this.leftMenu=res.data.t
            })
          },
          info(){
            this.$axios.get("system/api/user/getInfo").then(res => {
              alert(res.data.data)
              this.userinfo=res.data.t

            })
          },
            changeTab(tab){
                this.$router.push(tab.$attrs.url)
            },
            //移除页签页
            removeTab(targetName) {
                let tabs = this.editableTabs;
                let activeName = this.editableTabsValue;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                                //把路由重定向到 当前的激活的路由
                                this.$router.push(nextTab.url);
                            }
                        }
                    });
                }

                this.editableTabsValue = activeName;
                this.editableTabs = tabs.filter(tab => tab.name !== targetName);
            },

            logout(){
                localStorage.clear();
            },
            //显示当前连接的内容
            showContent(url,menuName){
                //alert(url+","+menuName);
                //直接调用添加页签页
                this.addTab(menuName,url);
            },
            //添加标签页
            addTab(targetName,url) { //targetName 页签页名称   url 对应的地址
               // debugger
                let newTabName = targetName;
                //获取所有的标签页
                let tabs = this.editableTabs;
                //定义是否已经标签变量
                var  isHaveTab = false;
                //循环所有页签页 判断当前点击标签页名称已经存在
                tabs.forEach((tab, index) => {
                    //如果为true 说明当前点击节点（部门管理），已经存在标签页
                    if(targetName === tab.title){// 判断是否相等  1=='1' 值相等  true   1==='1'  false 类型和值都香港等
                        isHaveTab = true;
                        newTabName =tab.title;
                    }
                });
                //如果不存在 添加一个
                if(!isHaveTab) {
                    this.editableTabs.push({
                        title: targetName,
                        name: newTabName,
                        url: url
                        //content: 'New Tab content'
                    });
                }
                //如果存在，直接让高亮
                this.editableTabsValue = newTabName;
            },
          foldOrOpen(){
            this.isCollaps = !this.isCollaps
            this.asideWidth = !this.isCollaps ? '200px' : '64px'
          }
        },
    }
</script>

<style>
    .home-container {
        height: 100%;
    }
    .el-header{
        background-color:#373D41;
        display: flex;
        justify-content: space-between;
        padding-left: 0px;
        align-items: center;
        color: white;
        font-size: 20px;

    }
    .el-header> div{
        display: flex;
        align-items: center;

    }
    .el-header> div> span{
        margin-left: 15px;
    }
    .el-aside{
        background-color:#333744;

    }
    .el-aside> .el-menu{
        border-right: none;
    }
    .el-main{
        background-color:white;
        padding: 0px 10px;
    }
    .toggle-button{
        display: flex;
        justify-content: center;
        color: white;
    }
    .icon-size-false i {
      font-size: 30px !important;
    }
    .icon-size-true i {
      font-size: 18px !important;
    }
</style>
