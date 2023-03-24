<template>
  <div>
    <!-- 工具栏1 -->
    <div style="height: 120px; padding-top: 20px; background-color: whitesmoke">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline" >
        <el-form-item label="厂家名称">
          <el-input v-model="searchForm.producerName" placeholder="请输入厂家名称" style="width: 180px"></el-input>
        </el-form-item>&nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="关键字">
          <el-input v-model="searchForm.keywords" placeholder="请输入关键字" style="width: 180px"></el-input>
        </el-form-item>&nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="厂家电话">
          <el-input v-model="searchForm.producerTel" placeholder="请输入厂家电话" style="width: 180px"></el-input>
        </el-form-item>&nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="可用状态" style="width: 180px">
            <el-option label="正常" value="0"></el-option>
            <el-option label="异常" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker v-model="searchForm.createTime" type="datetimerange" range-separator="——" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" plain @click="search">搜索</el-button>
          <el-button type="primary" icon="el-icon-refresh" plain @click="resert">重置</el-button>
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
    <div style="min-height: auto">
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" border max-height="330px">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="producerId" label="厂家ID" width="80px" align="center"></el-table-column>
        <el-table-column prop="producerName" label="厂家名称" align="center"></el-table-column>
        <el-table-column prop="producerCode" label="厂家编码" width="120px" align="center"></el-table-column>
        <el-table-column prop="producerPerson" label="联系人" width="100px" align="center"></el-table-column>
        <el-table-column prop="producerTel" label="电话" width="130px" align="center"></el-table-column>
        <el-table-column prop="keywords" label="关键字" width="80px" align="center"></el-table-column>
        <el-table-column prop="status" label="状态" width="60px" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160px" align="center"></el-table-column>
        <el-table-column label="操作" width="130px" align="center">
          <template slot-scope="scope">
            <el-button @click="delPro(scope.row)" type="text" size="small" icon="el-icon-edit">删除</el-button>
            <el-button @click="updPro(scope.row)" type="text" size="small " icon="el-icon-delete" >修改</el-button>
          </template>
        </el-table-column>
      </el-table><br>
    </div>

    <!-- 分页插件 -->
    <el-pagination :current-page="current" :page-size="size" :total="total"
                   :page-sizes="[5, 10, 20, 50]" layout="total, sizes, prev, pager, next, jumper"
                   @size-change="handleSizeChange" @current-change="handleCurrentChange">
    </el-pagination>
  </div>
</template>

<script>
  export default {
    methods: {
      // 修改操作
      updPro(){

      },
      // 删除操作
      delPro(){

      },
      // 重置模糊
      resert(){
        this.searchForm = {};
        this.initTable();
      },
      // 模糊查询
      search(){
        this.initTable();  // 模糊查询
      },
      // 查询表格数据————初始化表格
      initTable(){
        this.$axios.post("stock/api/producter/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
          this.tableData=result.data.t.records;
          this.total=result.data.t.total;
        })
      },
      //分页的事件，显示几条数据，pageSize变化时触发
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.size = val;
        this.initTable();  // 重新查询
      },
      // 点击上一页  下一页，跳转到哪一页面时触发
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.current = val;
        this.initTable();  // 重新查询
      },
    },
    data() {
      return {
        // 模糊查询的数据变量
        searchForm: {},
        // 所有的厂商信息
        tableData: [],
        // 总条数
        total: 0,
        current: 1,
        size: 5,
        //
        value3: '',
      }
    },
    // 用于挂载，在vue实例创建完成后被立即调用
    created() {
      this.initTable();  // 挂载初始化调用
    }
  }
</script>

<style>
  *{
    margin: 0;
    padding: 0;
  }
</style>
