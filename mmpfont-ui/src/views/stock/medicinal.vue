<template>
  <div>
    <!-- 工具栏1——用于模糊查询 -->
    <div style="height: 120px; padding-top: 20px; background-color: whitesmoke;">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline" >
        <div style="float: left;clear: both;padding-left: 30px">
          <el-form-item label="药品名称" prop="medicinesName">
            <el-input v-model="searchForm.medicinesName" clearable placeholder="请输入药品名称" style="width: 190px"/>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="关键字" prop="keywords">
            <el-input v-model="searchForm.keywords" clearable placeholder="请输入关键字" style="width: 190px"/>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="药品类型" prop="medicinesType">
            <el-select v-model="searchForm.medicinesType" clearable placeholder="药品类型" style="width: 200px">
              <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_medicines_type'})"
                         :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="生产厂家" prop="producerName">
            <el-select v-model="searchForm.producterNameOption" clearable placeholder="生产厂家" style="width: 230px">
              <el-option v-for="item in producterNameOption" :key="item" :label="item" :value="item"/>
              <!-- 遍历的是item内容 -->
            </el-select>
          </el-form-item> &nbsp;&nbsp;
        </div>
        <div style="float: left;clear: both;padding-left: 30px">
          <el-form-item label="处方类型" prop="prescriptionType">
            <el-select v-model="searchForm.prescriptionType" clearable placeholder="处方类型" style="width: 190px">
              <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_prescription_type'})"
                         :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="状态值" prop="status">
            <el-select v-model="searchForm.status" clearable placeholder="可用状态" style="width: 190px">
              <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='sys_normal_disable'})"
                         :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" plain @click="search">搜索</el-button>&nbsp;&nbsp;
            <el-button type="primary" icon="el-icon-refresh" plain @click="resert">重置</el-button>
          </el-form-item>
        </div>
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
        <el-form-item hidden label="药品ID" prop="medicinesId" size="mini">
          <el-input v-model="form.medicinesId"/>
        </el-form-item>
        <el-form-item label="药品名称" prop="medicinesName">
          <el-input v-model="form.medicinesName"/>
        </el-form-item>
        <el-form-item label="药品编号" prop="medicinesNumber">
          <el-input v-model="form.medicinesNumber"/>
        </el-form-item>
        <el-form-item label="生产厂家" prop="producerName">
          <el-input v-model="form.producerName"/>
        </el-form-item>
        <el-form-item label="药品类型" prop="medicinesType">
          <el-input v-model="form.medicinesType"/>
        </el-form-item>
        <el-form-item label="处方类型" prop="prescriptionType">
          <el-input v-model="form.prescriptionType"/>
        </el-form-item>
        <el-form-item label="关键字" prop="keywords">
          <el-input v-model="form.keywords"></el-input>
        </el-form-item>
        <el-form-item label="处方价格" prop="prescriptionPrice">
          <el-input v-model="form.prescriptionPrice"/>
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

    <!-- 表格 -->
    <div style="min-height: auto">
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" border max-height="330px">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" style="padding-left: 120px" inline class="demo-table-expand">
              <el-form-item label="单位"><span>{{ props.row.unit }}</span></el-form-item>
              <el-form-item label="换算量"><span>{{ props.row.conversion }}</span></el-form-item>
              <el-form-item label="库存量"><span>{{ props.row.medicinesStockNum }}</span></el-form-item>
              <el-form-item label="预警值"><span>{{ props.row.medicinesStockDangerNum }}</span></el-form-item>
            </el-form>
          </template>
        </el-table-column> <!-- 弹出的表格内容 -->
        <el-table-column prop="medicinesId" label="药品ID" width="80px" align="center"/>
        <el-table-column prop="medicinesName" label="药品名称" width="100px" align="center"/>
        <el-table-column prop="medicinesNumber" label="药品编号" width="80px" align="center"/>
        <el-table-column prop="producerName" label="生产厂家" min-width="120px" align="center"/>
        <el-table-column prop="medicinesType" label="药品类型" align="center":formatter="(row)=>this.dictFormat(row,row.medicinesType,'his_medicines_type')"/>
        <el-table-column prop="prescriptionType" label="处方类型" width="80px" align="center" :formatter="(row)=>this.dictFormat(row,row.prescriptionType,'his_prescription_type')"/>
        <el-table-column prop="keywords" label="关键字" width="80px" align="center"/>
        <el-table-column prop="prescriptionPrice" label="处方价格" width="80px" align="center"/>
        <el-table-column prop="status" label="状态" width="60px" align="center" :formatter="(row)=>this.dictFormat(row,row.status,'sys_normal_disable')"/>
        <el-table-column label="操作" width="200px" align="center">
          <template slot-scope="scope"> <!-- 需要绑定行数据 -->
            <el-button @click="updPro(scope.row)" type="text" size="small" icon="el-icon-delete">修改</el-button>
            <el-button @click="delPro(scope.row.medicinesId)" type="text" size="small" icon="el-icon-edit">删除</el-button>
            <el-button type="text" size="small" icon="el-icon-plus">调整库存</el-button>
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
      // 发出请求获取对应列数据
      PNO(){
        this.$axios.post("/stock/api/medicinal/getProducerName").then(r=>{
          this.producterNameOption=r.data.t;
        })
      },
      //字典解析
      dictFormat(row, column, dictType){
        return this.formatDict( this.dictList,column, dictType)
      },
      //初始化字典，需要挂载，当页面加载时调用
      getDict() {
        this.$axios.get('/stock/api/feign/status').then(res => {
          this.dictList = res.data.t
        })
      },
      // 模糊查询
      search(){
        this.initTable();
      },
      // 重置模糊
      resert(){
        this.searchForm = {};
        this.initTable();
      },
      // 提交按钮
      onSubmit(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 获取表单内的所有内容,发出axios请求，将表单内的数据发送到后端
            this.$axios.post("/stock/api/medicinal/saveAndUpdate",qs.stringify(this.form)).then(result=>{
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
      // 修改
      updPro(row){
        this.dialogVisible=false;
        this.form=JSON.parse(JSON.stringify(row))
      },
      // 添加操作
      addUser(){
        this.dialogVisible=true; // 点击显示表单
        this.form={};  //清空已经填写过的form表单
      },
      //删除
      delPro(obj){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.delete(`stock/api/medicinal/delById/${obj}`).then(result=>{
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
        this.$axios.post("/stock/api/medicinal/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
          this.tableData=result.data.t.records; // 将后台获取到的数据赋值给tableData变量
          // this.current=1;
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
          medicinesId: '',
          medicinesNumber: '',
          medicinesName: '',
          medicinesType: '',
          prescriptionType: '',
          prescriptionPrice: '',
          unit: '',
          conversion: '',
          keywords: '',
          producterId: '',
          producerName: '',
          status: '',
          medicinesStockNum: '',
          medicinesStockDangerNum: '',
          createTime: '',
          updateTime: '',
          createBy: '',
          updateBy: '',
          delFlag: ''
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
        producterNameOption: [],
      }
    },
    // 用于挂载，在vue实例创建完成后被立即调用
    created() {
      this.initTable();
      this.getDict();
      this.PNO();
    },
  }
</script>

<style>
  *{
    margin: 0;
    padding: 0;
  }
   .demo-table-expand {
     font-size: 0;
   }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
