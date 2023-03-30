<template>
  <div>
    <!-- 工具栏————模糊查询 -->
    <div style="height: 70px; padding-top: 30px; background-color: whitesmoke">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="供应商名称" prop="providerName">
          <el-input v-model="searchForm.providerName" placeholder="请输入供应商名称" clearable style="width: 160px"></el-input>
        </el-form-item>&nbsp;
        <el-form-item label="申请人" prop="contactName">
          <el-input v-model="searchForm.contactName" placeholder="请输入联系人" clearable style="width: 160px"></el-input>
        </el-form-item>&nbsp;
        <el-form-item label="单据状态" prop="contactTel">
          <el-input v-model="searchForm.contactTel" placeholder="请输入供销商电话" clearable style="width: 160px"></el-input>
        </el-form-item>&nbsp;
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" plain @click="search">搜索</el-button>&nbsp;&nbsp;
          <el-button type="primary" icon="el-icon-refresh" plain @click="resert">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 工具栏————新增 & 删除 -->
    <div style="float: left;clear: both;padding:15px;">
      <el-button type="primary" icon="el-icon-plus" plain @click="addUser">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" plain>删除</el-button>
    </div>
    <!-- 弹出层表单 -->
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item hidden label="供货商ID" prop="providerId" size="mini">
          <el-input v-model="form.providerId"></el-input>
        </el-form-item>
        <el-form-item label="供货商名称" prop="providerName">
          <el-input v-model="form.providerName"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contactName">
          <el-input v-model="form.contactName"></el-input>
        </el-form-item>
        <el-form-item label="联系人手机" prop="contactMobile">
          <el-input v-model="form.contactMobile"></el-input>
        </el-form-item>
        <el-form-item label="联系人电话" prop="contactTel">
          <el-input v-model="form.contactTel"></el-input>
        </el-form-item>
        <el-form-item label="银行账户" prop="bankAccount">
          <el-input v-model="form.bankAccount"></el-input>
        </el-form-item>
        <el-form-item label="供销商地址" prop="providerAddress">
          <el-input v-model="form.providerAddress"></el-input>
        </el-form-item>
        <!-- Data too long for column 'status' at row 1 因为label和value -->
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
    <div>
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" border max-height="330px">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column prop="purchaseId" label="单据ID" align="center"/>
        <el-table-column prop="providerId" label="供应商" align="center" width="80px"/>
        <el-table-column prop="purchaseTradeTotalAmount" label="采购批发总额" width="110px" align="center"/>
        <el-table-column prop="status" label="状态" align="center" width="80px" :formatter="(row)=>this.dictFormat(row,row.status,'his_order_status')"/>
        <el-table-column prop="applyUserName" label="申请人" align="center" width="80px"/>
        <el-table-column prop="storageOptUser" label="入库操作人" align="center" width="100px"/>
        <el-table-column prop="storageOptTime" label="入库时间" align="center"/>
        <el-table-column prop="examine" label="审核信息" align="center" width="80px"/>
        <el-table-column prop="createTime" label="创建时间" align="center"/>
      </el-table><br><br>
      <!-- 分页插件 -->
      <el-pagination :current-page="current" :page-size="size" :total="total"
                     :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </div>
  </div>
</template>

<script>
import qs from "qs";
export default {
  methods: {
    // 使用字典表转换数据————调用formatDict脚本对传入参数（行数据、对应行、字典列）处理，并返回
    dictFormat(row, column, dictType){
      return this.formatDict( this.dictList,column, dictType)
    },
    // 获取字典表数据————发请求给后端，由后端调用接口、跨域并获取字典表数据传给前端处理数据
    getDict() {
      this.$axios.get('/stock/api/feign/status').then(res => {
        this.dictList = res.data.t
      })
    },
    // 格式化翻译状态
    statusFormat(row){
      let v=row.status;
      return this.formatDict(this.statusOptions,v);
    },
    // 重置模糊
    resert(){
      this.searchForm = {};
      this.current=1;
      this.initTable();
    },
    // 模糊查询
    search(){
      this.current=1;
      this.initTable();
    },
    // 添加————点击显示表单执行提交方法，执行后清空表单数据
    addUser(){
      this.dialogVisible=true; // 点击显示表单
      this.form={};  //清空已经填写过的form表单
    },
    // 修改————传入行数据对象，弹出表单，使用深拷贝把行数据对象先转成JSON字符串，再转为JSON对象
    updPro(row){
      this.dialogVisible=true;  //弹出层显示表单
      this.form = JSON.parse(JSON.stringify(row)); // 深拷贝：用到的是对象，所以需要转换
    },
    // 弹出层提交表单出发的提交事件
    onSubmit(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 获取表单内的所有内容,发出axios请求，将表单内的数据发送到后端
          this.$axios.post("stock/api/purchase/saveAndUpdate",qs.stringify(this.form)).then(result=>{
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
    // 删除操作
    delPro(obj){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete(`stock/api/purchase/delById/${obj}`).then(result=>{
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
      this.$axios.post("stock/api/purchase/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
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
      // 状态
      statusOptions: [],
      // 接收模糊查询
      searchForm: {},
      // 查询的页面信息
      tableData: [],
      // 弹出层状态
      dialogVisible: false,
      // 弹出层表单
      form: {
        providerId: '',
        providerName: '',
        contactName: '',
        contactMobile: '',
        contactTel: '',
        bankAccount: '',
        providerAddress: '',
        status: '',
        createTime: '',
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
