<template>
  <div>
    <!-- 工具栏1 -->
    <div style="height: 120px; padding-top: 20px; background-color: whitesmoke">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline" >
        <el-form-item label="厂家名称">
          <el-input v-model="searchForm.producerName" placeholder="请输入厂家名称" style="width: 180px"></el-input>
        </el-form-item> &nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="关键字">
          <el-input v-model="searchForm.keywords" placeholder="请输入关键字" style="width: 180px"></el-input>
        </el-form-item> &nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="厂家电话">
          <el-input v-model="searchForm.producerTel" placeholder="请输入厂家电话" style="width: 180px"></el-input>
        </el-form-item> &nbsp;&nbsp;&nbsp;&nbsp;
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="可用状态" style="width: 180px">
              <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='sys_normal_disable'})"
                  :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker v-model="searchForm.createTime" style="width:280px"
                          value-format="yyyy-MM-dd" type="daterange" range-separator="——"
                          start-placeholde="开始日期" end-placeholde="结束日期"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" plain @click="search">搜索</el-button>
          <el-button type="primary" icon="el-icon-refresh" plain @click="resert">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 工具栏2 -->
    <div style="float: left;clear: both;padding:15px;">
      <el-button type="primary" icon="el-icon-plus" plain @click="addUser">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" plain>删除</el-button>
    </div>

    <!-- 点击添加按钮弹出的表单,在data内设置弹出层:visible.sync="dialogVisible" -->
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-form-item label="厂家ID" prop="producerId" hidden="true" size="mini">
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
            <el-radio v-model="form.status" label="正常" value="0"/>
            <el-radio v-model="form.status" label="停用" value="1"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
            <el-button @click="dialogVisible = false">取消</el-button>
          </el-form-item>
        </el-form>
    </el-dialog>

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
        <el-table-column prop="status" label="状态" width="60px" align="center" :formatter="(row)=>this.dictFormat(row,row.status,'sys_normal_disable')"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160px" align="center"></el-table-column>
        <el-table-column label="操作" width="130px" align="center">
          <template slot-scope="scope"> <!-- 需要绑定行数据 -->
            <el-button @click="updPro(scope.row)" type="text" size="small" icon="el-icon-delete">修改</el-button>
            <el-button @click="delPro(scope.row.producerId)" type="text" size="small" icon="el-icon-edit">删除</el-button>
          </template>
        </el-table-column>
      </el-table><br>
      <!-- 分页插件 -->
      <el-pagination :current-page="current" :page-size="size" :total="total"
                     :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import qs from 'qs';
  export default {
    methods: {
      //字典解析
      dictFormat(row, column, dictType){
        return this.formatDict( this.dictList,column, dictType)
      },
      //初始化字典
      getDict() {
        this.$axios.get('/stock/api/producter/status').then(res => {
          this.dictList = res.data.t
        })
      },
      // 重置模糊
      resert(){
        this.searchForm = {};
        this.initTable();
      },
      // 模糊查询
      search(){
        this.initTable();
      },
      // 弹出层提交表单出发的提交事件
      onSubmit(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 获取表单内的所有内容,发出axios请求，将表单内的数据发送到后端
            this.$axios.post("stock/api/producter/saveAndUpdate",qs.stringify(this.form)).then(result=>{
              if (result.data.t){
                this.$message.success("添加成功")
                this.dialogVisible=false; //当执行完弹出层操作隐藏弹出层
                this.form={};  //清空已经填写过的form表单
                this.initTable(); //刷新页面
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
      // 修改操作，obj代表着当前行的数据，将对象obj赋值到form表单中 需要深拷贝
      updPro(row){
        this.dialogVisible=true;  //弹出层显示表单
        this.form = JSON.parse(JSON.stringify(row)); // 深拷贝：用到的是对象，所以需要转换
      },
      // 添加操作
      addUser(){
        this.dialogVisible=true; // 点击显示表单
        this.form={};  //清空已经填写过的form表单

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
      // 查询表格数据————初始化表格 (用axios发出请求去获取后端的值)
      initTable(){
        this.$axios.post("stock/api/producter/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
          this.tableData=result.data.t.records; // 将后台获取到的数据赋值给tableData变量
          this.current=1;
          this.total=result.data.t.total; // 更改分页后页面的总条数
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
        // 模糊查询信息
        searchForm: {},
        // 所有的厂商信息
        tableData: [],
        //设置添加弹出层的弹出表单，默认不显示，当点击时显示
        dialogVisible: false,
        //弹出层表单
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
        //校验规则
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
        //字典
        dictList: [],
      }
    },
    // 用于挂载，在vue实例创建完成后被立即调用
    created() {
      this.initTable();  // 挂载初始化调用
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
