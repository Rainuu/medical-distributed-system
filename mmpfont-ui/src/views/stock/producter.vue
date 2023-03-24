<template>
  <div>
    <!-- 工具栏1 -->
    <div style="height: 120px; padding-top: 20px; background-color: whitesmoke">
      <el-form :inline="true" :model="formInline" class="demo-form-inline" >
        <el-form-item label="厂家名称">
          <el-input v-model="formInline.user" placeholder="请输入厂家名称" style="width: 180px"></el-input>
        </el-form-item>&nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="关键字">
          <el-input v-model="formInline.user" placeholder="请输入关键字" style="width: 180px"></el-input>
        </el-form-item>&nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="厂家电话">
          <el-input v-model="formInline.user" placeholder="请输入厂家电话" style="width: 180px"></el-input>
        </el-form-item>&nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="状态">
          <el-select v-model="formInline.region" placeholder="可用状态" style="width: 180px">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker v-model="value3" type="datetimerange" range-separator="——" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"  icon="el-icon-search" plain>搜索</el-button>
          <el-button type="primary"  icon="el-icon-refresh" plain>重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 工具栏2 -->
    <div style="float: left;clear: both;padding:15px;">
      <el-button type="primary" icon="el-icon-plus" plain>新增</el-button>
      <el-button type="success" icon="el-icon-edit" plain>修改</el-button>
      <el-button type="danger" icon="el-icon-delete" plain>删除</el-button>
    </div>

    <!-- 表格 -->
    <div>
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" border>
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="producerId" label="厂家ID" min-width="75" align="center"></el-table-column>
        <el-table-column prop="producerName" label="厂家名称" min-width="75" align="center"></el-table-column>
        <el-table-column prop="producerCode" label="厂家编码" min-width="75" align="center"></el-table-column>
        <el-table-column prop="producerPerson" label="联系人" min-width="75" align="center"></el-table-column>
        <el-table-column prop="producerTel" label="电话" min-width="75" align="center"></el-table-column>
        <el-table-column prop="keywords" label="关键字" min-width="75" align="center"></el-table-column>
        <el-table-column prop="status" label="状态" min-width="75" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="75" align="center"></el-table-column>
        <el-table-column fixed="right" label="操作" min-width="100" align="center">
          <template slot-scope="scope">
            <el-button @click="del(scope.row)" type="text" size="small" icon="el-icon-edit">删除</el-button>
            <el-button @click="upd(scope.row)" type="text" size="small " icon="el-icon-delete" >修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div><br>
    <!-- 分页插件 -->
<!--    <div class="block">-->
<!--      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"-->
<!--          :current-page="currentPage4" :page-sizes="[100, 200, 300, 400]"-->
<!--          :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400">-->
<!--      </el-pagination>-->
<!--    </div>-->

  </div>
</template>

<script>
  import axios from "axios";
  export default {
    methods: {
      // 查询表格数据————初始化表格
      initTable(){
        this.$axios.get("/stock/api/producter/getAll").then(result=>{
          this.tableData=result.data.t;
        })
      },
      // 分页pageSize变化时触发
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      // 点击上一页  下一页，跳转到哪一页面时触发
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },

      onSubmit() {
        console.log('submit!');
      },
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
    },
    data() {
      return {
        formInline: {
          user: '',
          region: ''
        },
        tableData: [],
        currentPage4: '',
        value3: '',
      }
    },
    // 用于挂载，在vue实例创建完成后被立即调用
    created() {
      this.initTable();
    }
  }
</script>

<style>
  *{
    margin: 0;
    padding: 0;
  }
</style>
