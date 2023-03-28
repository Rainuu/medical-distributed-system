<template>
  <div>
    <!-- 工具栏1——用于模糊查询 -->
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

    <!-- 工具栏2 -->
    <div style="float: left;clear: both;padding:15px;">
      <el-button type="primary" icon="el-icon-plus" plain @click="addUser">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" plain>删除</el-button>
    </div>

    <!-- 弹出层 -->
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

    <!-- 表格 -->
    <div>
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" border max-height="330px">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column prop="purchaseId" label="单据ID" align="center"/>
        <el-table-column prop="providerId" label="供应商" align="center" width="80px"/>
        <el-table-column prop="purchaseTradeTotalAmount" label="采购批发总额" width="110px" align="center"/>
        <el-table-column prop="status" label="状态" align="center" width="60px" :formatter="(row)=>this.dictFormat(row,row.status,'sys_normal_disable')"/>
        <el-table-column prop="applyUserName" label="申请人" align="center" width="80px"/>
        <el-table-column prop="storageOptUser" label="入库操作人" align="center" width="100px"/>
        <el-table-column prop="storageOptTime" label="入库时间" align="center"/>
        <el-table-column prop="examine" label="审核信息" align="center" width="80px"/>
        <el-table-column prop="createTime" label="创建时间" align="center"/>
      </el-table><br><br>
      <!-- 分页插件 -->
      <el-pagination :current-page="current" :page-size="size" :total="total"
                     :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import qs from "qs";

export default {
  data() {
    return {
      // 接收模糊查询
      searchForm: {},
      //用于接参，所有的供应商信息
      tableData: [],
      //设置添加弹出层的弹出表单，默认不显示，当点击时显示
      dialogVisible: false,
      //弹出层表单
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
      // 状态
      statusOptions: [],
    }
  },
  methods: {
    //字典解析
    dictFormat(row, column, dictType){
      return this.formatDict( this.dictList,column, dictType)
    },
    //初始化字典
    getDict() {
      this.$axios.get('/stock/api/feign/status').then(res => {
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
    // 添加操作
    addUser(){
      this.dialogVisible=true; // 点击显示表单
      this.form={};  //清空已经填写过的form表单
    },
    // 修改操作，obj代表着当前行的数据，将对象obj赋值到form表单中 需要深拷贝
    updPro(row){
      this.dialogVisible=true;  //弹出层显示表单
      this.form = JSON.parse(JSON.stringify(row)); // 深拷贝：用到的是对象，所以需要转换
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
    // 查询表格数据————初始化表格 (用axios发出请求去获取后端的值)
    initTable(){
      this.$axios.post("stock/api/purchase/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
        this.tableData=result.data.t.records;   // 将后台获取到的数据赋值给tableData变量
        this.total=result.data.t.total; // 更改分页后页面的总条数
      })
    },
    // 格式化翻译状态
    statusFormat(row){
      let v=row.status;
      return this.formatDict(this.statusOptions,v);
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
    }
  },
  // 用于挂载，在vue实例创建完成后被立即调用
  created() {
    this.initTable(); // 挂载初始化调用
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
