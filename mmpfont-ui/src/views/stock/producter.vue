<template>
<!-- 未完成的任务：批量删除 -->
  <div>
    <!-- 工具栏————模糊查询 -->
    <div style="height: 120px; padding-top: 20px; background-color: whitesmoke;">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline" >
        <div style="float: left;clear: both;padding-left: 30px" >
          <el-form-item label="厂家名称" prop="producerName">
            <el-input v-model="searchForm.producerName" clearable placeholder="请输入厂家名称" style="width: 240px"/>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="关键字" prop="keywords">
            <el-input v-model="searchForm.keywords" clearable placeholder="请输入关键字" style="width: 200px"/>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="厂家电话" prop="producerTel">
            <el-input v-model="searchForm.producerTel" clearable placeholder="请输入厂家电话" style="width: 200px"/>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="状态" prop="status">
            <el-select v-model="searchForm.status" clearable placeholder="可用状态" style="width: 200px">
              <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='sys_normal_disable'})"
                         :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item>
        </div>
        <div style="float: left; clear: both; padding-left:30px">
          <el-form-item label="创建时间" prop="createTime">
            <el-date-picker type="daterange" v-model="searchForm.dateRange" style="width:240px" clearable
                            value-format="yyyy-MM-dd" start-placeholder="开始日期" range-separator="-" end-placeholder="结束日期"/>
          </el-form-item> &nbsp;&nbsp;&nbsp;&nbsp;
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" plain @click="search">搜索</el-button>&nbsp;&nbsp;
            <el-button type="primary" icon="el-icon-refresh" plain @click="resert">重置</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
    <!-- 工具栏————新增 & 删除 -->
    <div style="float: left;clear: both;padding:15px;">
      <el-button type="primary" icon="el-icon-plus" plain @click="addUser">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" plain>批量删除</el-button>
    </div>
    <!-- 弹出层表单 -->
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-form-item hidden label="厂家ID" prop="producerId" size="mini">
            <el-input v-model="form.producerId"></el-input>
          </el-form-item>
          <el-form-item label="厂家名称" prop="producerName">
            <el-input v-model="form.producerName"></el-input>
          </el-form-item>
          <el-form-item label="厂家编号" prop="producerCode">
            <el-input v-model="form.producerCode"></el-input>
          </el-form-item>
          <el-form-item label="厂家地址" prop="producerAddress">
            <el-input v-model="form.producerAddress"></el-input>
          </el-form-item>
          <el-form-item label="联系人" prop="producerPerson">
            <el-input v-model="form.producerPerson"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="producerTel">
            <el-input v-model="form.producerTel"></el-input>
          </el-form-item>
          <el-form-item label="关键字" prop="keywords">
            <el-input v-model="form.keywords"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-radio v-model="form.status" label="0">正常</el-radio>
            <el-radio v-model="form.status" label="1">停用</el-radio>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
            <el-button @click="dialogVisible = false">取消</el-button>
          </el-form-item>
        </el-form>
    </el-dialog>
    <!-- 页面——数据表 & 分页 & 修改 & 删除（绑定行数据） -->
    <div style="min-height: auto">
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" border max-height="330px">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column prop="producerId" label="厂家ID" width="80px" align="center"/>
        <el-table-column prop="producerName" label="厂家名称" align="center"/>
        <el-table-column prop="producerCode" label="厂家编码" width="120px" align="center"/>
        <el-table-column prop="producerPerson" label="联系人" width="100px" align="center"/>
        <el-table-column prop="producerTel" label="电话" width="130px" align="center"/>
        <el-table-column prop="keywords" label="关键字" width="80px" align="center"/>
        <el-table-column prop="status" label="状态" width="60px" align="center" :formatter="(row)=>this.dictFormat(row,row.status,'sys_normal_disable')"/>
        <el-table-column prop="createTime" label="创建时间" width="160px" align="center"/>
        <el-table-column label="操作" width="130px" align="center">
          <template slot-scope="scope">
            <el-button @click="updPro(scope.row)" type="text" size="small" icon="el-icon-delete">修改</el-button>
            <el-button @click="delPro(scope.row.producerId)" type="text" size="small" icon="el-icon-edit">删除</el-button>
          </template>
        </el-table-column>
      </el-table><br>
      <!-- 分页插件 -->
      <el-pagination :current-page="current" :page-size="size" :total="total"
                     :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </div>
  </div>
</template>

<script>
  import qs from 'qs';
  export default {
    methods: {
      // 获取字典表数据————发请求给后端，由后端调用接口、跨域并获取字典表数据传给前端处理数据
      getDict() {
        this.$axios.get('/stock/api/feign/status').then(res => {
          this.dictList = res.data.t;
        })
      },
      // 使用字典表转换数据————调用formatDict脚本对传入参数（行数据、对应行、字典列）处理，并返回
      dictFormat(row, column, dictType){
        return this.formatDict( this.dictList,column, dictType)
      },
      // 模糊查询
      search(){
        this.current=1;
        this.initTable();
      },
      // 重置模糊
      resert(){
        this.searchForm = {};
        this.current=1;
        this.initTable();
      },
      // 修改————传入行数据对象，弹出表单，使用深拷贝把行数据对象先转成JSON字符串，再转为JSON对象
      updPro(row){
        this.dialogVisible=true;
        this.form = JSON.parse(JSON.stringify(row));
      },
      // 添加————点击显示表单执行提交方法，执行后清空表单数据
      addUser(){
        this.dialogVisible=true;
        this.form={};
      },
      // 提交————弹出层的提交按钮，获取表单内容发出请求给后端执行，执行后关闭弹出层，清空表单，重新调用查询刷新页面
      onSubmit(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post("/stock/api/producter/saveAndUpdate",qs.stringify(this.form)).then(result=>{
              if (result.data.t){
                this.$message.success("添加成功")
                this.dialogVisible=false;
                this.form={};
                this.initTable();
              } else {
                this.$message.error("添加失败");
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      // 删除操作
      delPro(obj){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.delete(`stock/api/producter/delById/${obj}`).then(result=>{
            if (result.data.t){
              // alert(result.data.t); // 测试弹出获取的数据信息,返回true/false
              this.initTable();
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }else {
              this.$message({
                type: 'error',
                message: '删除失败!'
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      // 查询————发出axios请求获取后端值，并将后端获取到的数据赋值给表格回填，挂载到页面，更改页面条数实现分页
      initTable(){
        this.$axios.post("stock/api/producter/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
          this.tableData=result.data.t.records;
          this.total=result.data.t.total;
        })
      },
      // 分页————改变每页展示的数据数量，在size变化时触发
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.size = val;
        this.initTable();
      },
      // 分页————改变当前页，点击页码时触发并跳转到对应页
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.current = val;
        this.initTable();
      },
    },
    data() {
      return {
        // 字典数组——接收字典表数据
        dictList: [],
        // 模糊查询信息
        searchForm: {},
        // 查询的页面信息
        tableData: [],
        // 弹出层状态
        dialogVisible: false,
        // 弹出层表单
        form: {
          producerId: '',
          producerName: '',
          producerAddress: '',
          producerCode: '',
          producerPerson: '',
          producerTel: '',
          keywords: '',
          status: '',
        },
        // 校验规则
        rules: {
          userName: [
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
          ],
        },
        // 分页参数
        total: 0,
        current: 1,
        size: 5,
      }
    },
    // 钩子函数————用于挂载，在vue页面创建后立即调用
    created() {
      this.initTable();
      this.getDict();
    }
  }
</script>

<style>
  *{
    margin: 0;
    padding: 0;
  }
</style>
