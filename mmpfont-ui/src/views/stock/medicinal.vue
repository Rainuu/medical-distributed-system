<template>
  <div>
    <!-- 工具栏————模糊查询 -->
    <div style="height: 120px; padding-top: 20px; background-color: whitesmoke;">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline" >
        <div style="float: left;clear: both;padding-left: 30px">
          <el-form-item label="药品名称" prop="medicinesName">
            <el-input v-model="searchForm.medicinesName" clearable placeholder="请输入药品名称" style="width: 190px"/>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="关键字" prop="keywords">
            <el-input v-model="searchForm.keywords" clearable placeholder="请输入关键字" style="width: 160px"/>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="药品类型" prop="medicinesType">
            <el-select v-model="searchForm.medicinesType" clearable placeholder="药品类型" style="width: 180px">
              <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_medicines_type'})"
                         :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item> &nbsp;&nbsp;
          <el-form-item label="生产厂家" prop="producterId"> <!-- 遍历的是item内容 -->
            <el-select v-model="searchForm.producerId" clearable placeholder="生产厂家" style="width: 230px">
              <el-option v-for="item in producterNameOption"
                         :key="item.producerId" :label="item.producerName" :value="item.producerId"/>
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
            <el-select v-model="searchForm.status" clearable placeholder="可用状态" style="width: 160px">
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
    <!-- 工具栏————新增 & 删除 -->
    <div style="float: left;clear: both;padding:15px;">
      <el-button type="primary" icon="el-icon-plus" plain @click="addUser">新增</el-button>
<!--      <el-button type="success" icon="el-icon-edit" :disabled="single" @click="updPro">修改</el-button>-->
      <el-button type="danger" icon="el-icon-delete" :disabled="multiple" @click="delLists" plain>批量删除</el-button>
    </div>
    <!-- 弹出层表单————修改 & 新增 -->
    <el-dialog title="提示" :visible.sync="dialogVisible" width="65%" center append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item hidden label="药品ID" prop="medicinesId" size="mini">
          <el-input v-model="form.medicinesId"/>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="药品名称" prop="medicinesName">
              <el-input v-model="form.medicinesName" clearable placeholder="请输入药品名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="药品编号" prop="medicinesNumber">
              <el-input v-model="form.medicinesNumber" clearable placeholder="请输入药品编码"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="关键字" prop="keywords">
              <el-input v-model="form.keywords" clearable  placeholder="请输入关键字"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单 位" prop="unit">
              <el-input v-model="form.unit" clearable placeholder="请输入单位"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="换算量" prop="conversion">
              <el-input v-model="form.conversion" clearable placeholder="请输入换算量"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="处方价格" prop="prescriptionPrice">
              <el-input v-model="form.prescriptionPrice" clearable placeholder="请输入处方价格"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="预警值" prop="medicinesStockDangerNum">
              <el-input-number v-model="form.medicinesStockDangerNum" style="width:340px" placeholder="请输入预警值"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio v-model="form.status" label="0">正常</el-radio>
              <el-radio v-model="form.status" label="1">停用</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="药品类型" prop="medicinesType">
              <el-select v-model="form.medicinesType" clearable  style="width:340px" placeholder="请输入药品类型">
                <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_medicines_type'})"
                           :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="处方类型" prop="prescriptionType">
              <el-select v-model="form.prescriptionType" clearable style="width:340px" placeholder="请输入处方类型">
                <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_prescription_type'})"
                           :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产厂家" prop="producterId">
              <el-select v-model="form.producterId" clearable placeholder="请输入生产厂家" style="width:340px">
                <el-option v-for="item in producterNameOption"
                           :key="item.producerId" :label="item.producerName" :value="item.producerId"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item style="text-align: center;">
          <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 页面——数据表 & 分页 & 调整库存 & 修改 & 删除（绑定行数据） -->
    <div style="min-height: auto">
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" border max-height="330px" @selection-change="handleSelectionChange">
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
        </el-table-column>
        <el-table-column prop="medicinesId" label="药品ID" width="80px" align="center"/>
        <el-table-column prop="medicinesName" label="药品名称" width="100px" align="center"/>
        <el-table-column prop="medicinesNumber" label="药品编号" width="80px" align="center"/>
        <el-table-column prop="producterId" label="生产厂家" min-width="120px" align="center" :formatter="ProducterNameDict"/>
        <el-table-column prop="medicinesType" label="药品类型" align="center" :formatter="(row)=>this.dictFormat(row,row.medicinesType,'his_medicines_type')"/>
        <el-table-column prop="prescriptionType" label="处方类型" width="80px" align="center" :formatter="(row)=>this.dictFormat(row,row.prescriptionType,'his_prescription_type')"/>
        <el-table-column prop="keywords" label="关键字" width="80px" align="center"/>
        <el-table-column prop="prescriptionPrice" label="处方价格" width="80px" align="center"/>
        <el-table-column prop="status" label="状态" width="60px" align="center" :formatter="(row)=>this.dictFormat(row,row.status,'sys_normal_disable')"/>
        <el-table-column label="操作" width="200px" align="center">
          <template slot-scope="scope">
            <el-button @click="updPro(scope.row)" type="text" size="small" icon="el-icon-delete">修改</el-button>
            <el-button @click="delPro(scope.row.medicinesId)" type="text" size="small" icon="el-icon-edit">删除</el-button>
            <el-button @click="updStorage(scope.row)" type="text" size="small" icon="el-icon-plus">调整库存</el-button>
          </template>
        </el-table-column>
      </el-table><br>
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
      // 获取生产厂家表字段
      getProducterNameOption(){
        this.$axios.get("/stock/api/producter/getAllDict").then(r=>{
          this.producterNameOption=r.data.t;
        })
      },
      // 循环判断，将Id转换为name
      ProducterNameDict(row){
        var a = '';
        for (let i = 0; i < this.producterNameOption.length; i++) {
          if (row.producterId == this.producterNameOption[i].producerId){
            a = this.producterNameOption[i].producerName;
            break;
          }
        }
        return a;
      },
      // 获取字典表数据————发请求给后端，由后端调用接口、跨域并获取字典表数据传给前端处理数据
      getDict() {
        this.$axios.get('/stock/api/feign/status').then(res => {
          this.dictList = res.data.t
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
        this.initTable();
        this.current=1;
      },
      // 添加————点击显示表单执行提交方法，执行后清空表单数据
      addUser(){
        this.dialogVisible=true;
        this.title = '添加生产药品信息'
        this.form={};
      },
      // 修改————传入行数据对象，弹出表单，使用深拷贝把行数据对象先转成JSON字符串，再转为JSON对象
      updPro(row){
        this.dialogVisible=true;
        this.form = JSON.parse(JSON.stringify(row));
      },
      // 调整库存
      updStorage(row){
        this.$prompt('请输入要调整[' + row.medicinesName + ']的值', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /^-?\d+$/,
          inputErrorMessage: '库存值输入不正确'
        }).then(({ value }) => {
          this.form=JSON.parse(JSON.stringify(row)); // 深拷贝把请求行数据转为需要的数据赋给表单
          this.form.medicinesStockNum=value;  //使用输入框输入的值赋值替换给表单内对应的字段
          //重发请求
          this.$axios.post("/stock/api/medicinal/saveAndUpdate",qs.stringify(this.form)).then(result=>{
            if (result.data.t){
              this.$message.success("添加成功");
              this.form={};
              this.initTable();
            } else {
              this.$message.error("添加失败");
            }
          })
        })
      },
      // 提交————弹出层的提交按钮，获取表单内容发出请求给后端执行，执行后关闭弹出层，清空表单，重新调用查询刷新页面
      onSubmit(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post("/stock/api/medicinal/saveAndUpdate",qs.stringify(this.form)).then(result=>{
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
          this.$axios.delete(`stock/api/medicinal/delById/${obj}`).then(result=>{
            if (result.data.t){
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
      // 批量删除
      delLists(){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("stock/api/medicinal/delListById",this.multipleSelection).then(result=>{
            if (result.data.t){
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
        this.$axios.post("/stock/api/medicinal/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
          this.tableData=result.data.t.records;
          this.total=result.data.t.total;
        })
      },
      // 获取批量数据————数据表格的多选择框选择时触发
      handleSelectionChange(selection) {
        // this.ids = selection.map(item => item.medicinesId)
        this.multipleSelection = selection;
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
        // alert(JSON.stringify(selection))
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
        // 模糊查询时，使用id获取name
        producterNameOption: {},
        // 模糊查询信息
        searchForm: {},
        // 查询的页面信息
        tableData: [],
        // 弹出层标题
        title: '',
        // 弹出层状态
        dialogVisible: false,
        // 弹出层表单
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
        // 表单校验
        rules: {
          medicinesName: [
            { required: true, message: '药品名称不能为空', trigger: 'blur' }
          ],
          keywords: [
            { required: true, message: '查询关键字不能为空', trigger: 'blur' }
          ],
          prescriptionPrice: [
            { required: true, message: '处方价格不能为空', trigger: 'blur' }
          ],
          medicinesStockDangerNum: [
            { required: true, message: '预警值不能为空', trigger: 'blur' }
          ],
          medicinesType: [
            { required: true, message: '药品类型不能为空', trigger: 'blur' }
          ],
          prescriptionType: [
            { required: true, message: '处方类型不能为空', trigger: 'blur' }
          ],
          producterId: [
            { required: true, message: '生产厂家不能为空', trigger: 'blur' }
          ]
        },
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 分页参数
        total: 0,
        current: 1,
        size: 5,
        // 获取批量数据
        multipleSelection: [],
      }
    },
    // 钩子函数————用于挂载，在vue页面创建后立即调用
    created() {
      this.initTable();
      this.getDict();
      this.getProducterNameOption();
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
