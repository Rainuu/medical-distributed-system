<!--检查结果录入-->
<template>
  <div style="margin-top: 15px;margin-left: 15px">

    <!--弹出层开始-->
    <!--<el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>-->
    <el-dialog
        :title="'录入【'+[patientName]+'】的检查结果'"
        :visible.sync="dialogVisible"
        width="50%"
        center
        :before-close="handleClose">
      <!--form表单开始-->
      <span>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="检查结果">
          <el-input
              type="textarea" :rows="2" placeholder="请输入内容" v-model="textarea">
          </el-input>
        </el-form-item>
        <!-- 文件上传开始 -->
        <el-form-item label="结果上传">
          <el-upload
              class="upload-demo"
              :action="objData.host"
              :before-upload="ossPolicy"
              :on-remove="handleRemove"
              :file-list="fileList"
              :data="objData"
              accept="image/*"
              name="file"
              :on-success="handleUploadSuccess"
              :on-error="handleUploadError"
              list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <!-- 文件上传结束 -->
      </el-form>
        <!--form表单结束-->
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addMsg">确 定</el-button>
        <el-button @click="dialogVisible = false" style="">取 消</el-button>

      </span>
    </el-dialog>
    <!--弹出层结束-->

    <!-- 查询条件开始 -->
    <el-card style="margin-bottom:3px">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
        <el-form-item label="检查项目" prop="checkItemIds" style="float: left">
          <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange" style="float: left">全选</el-checkbox>
          <div style="clear: both"/>
          <el-checkbox-group v-model="queryParams.checkItemIds" @change="handleCheckedItemChange">
            <el-checkbox
                v-for="d in checkItemOptions"
                :key="d.checkItemId"
                :label="d.checkItemId"
                :value="d.checkItemId"
            >{{ d.checkItemName }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="挂号单号" prop="regId">
          <el-input
              v-model="queryParams.regId"
              placeholder="请输入挂号单号"
              clearable
              size="small"
              style="width:380px"
          />
        </el-form-item>
        <el-form-item label="患者姓名" prop="patientName" style="clear:both;float: left">
          <el-input
              v-model="queryParams.patientName"
              placeholder="请输入患者姓名"
              clearable
              size="small"
              style="width:380px"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="query">搜索</el-button>
          <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!--table表格-->
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand" style="margin-left: 80px">
            <el-form-item label="价格"><span>{{ props.row.price }}</span></el-form-item>
            <el-form-item label="创建时间"><span>{{ props.row.createTime}}</span></el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          prop="cocId"
          label="检查单位"
          min-width="100px"
      >
      </el-table-column>
      <el-table-column
          prop="regId"
          label="挂号单号"
          min-width="100px"
      >
      </el-table-column>
      <el-table-column
          prop="checkItemName"
          label="项目名称"
      >
      </el-table-column>
      <el-table-column
          prop="patientName"
          label="患者姓名"
      >
      </el-table-column>
      <el-table-column
          prop="resultStatus"
          label="检查状态"
          :formatter="statusFormat"
      >
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
      >
        <template slot-scope="scope">
          <el-button icon="el-icon-edit" @click="handleClick(scope.row)" type="text" size="small">录入结果</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination align="left"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="page.current"
                   :page-sizes="[10, 20, 30, 40]"
                   :page-size="page.size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="page.total">
    </el-pagination>

  </div>

</template>

<script>
const cityOptions = [];
export default {
  data() {
    return {
      //查询参数
      queryParams: {
        regId: undefined,
        patientName: undefined,
        checkItemIds: [],
        status:0
      },
      //是否为全选状态
      checkAll: true,
      //检查项目数据
      checkItemOptions: [],
      cities: cityOptions,
      //是否为半选状态
      isIndeterminate: false,
      //分页
      page: {
        total: 10,
        size: 10,
        current: 1
      },
      textarea: '',
      dialogVisible: false,
      formInline: {
        user: '',
        region: ''
      },
      form: {
        type: []
      },
      patientName:'',
      //表格数据
      tableData: [],
      // 是否打开录入结果的弹出层
      open: false,
      // 弹出层的标题
      title: '',
      // 当前查看的数据
      currentResult: {
        resultMsg: undefined,
        resultImg: []
      },
      //系统状态
      statusOptions:[],
      //处方检查项ID
      cocId:'',
      //阿里云文件上传
      objData:{
        OSSAccessKeyId:'',
        policy:'',
        Signature:'',
        key:'',
        host:'',
        dir:''
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    /*查看弹出层*/
    handleClick(row) {
      this.dialogVisible = true;
      this.patientName=row.patientName;
      this.cocId=row.cocId;
      this.form= row;
      console.log(row);
      console.log(this.patientName);
    },
    // 全选
    handleCheckAllChange(val) {
      this.queryParams.checkItemIds = val ? this.checkItemOptions.map(item => item.checkItemId) : []
      this.isIndeterminate = false
    },
    // 选择某一个项目
    handleCheckedItemChange(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.checkItemOptions.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.checkItemOptions.length
      this.queryAllCheckResultForPage()
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.page.size = val;
      // 重新查询
      this.queryData();
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.page.current = val;
      // 重新查询
      this.queryData();
    },
    //查询
    queryData(){
        this.$axios.post("/check/api/checkResult/listStatus/"+this.page.current+"/"+this.page.size,this.queryParams).then(r=>{
        this.tableData=r.data.t.records;
        this.page.total=r.data.t.total
      })
    },
    //查询
    query(){
      this.queryData()
    },
    // 加载所有可用的检查项目
    selectAllCheckItem(){
      this.$axios.get("/check/api/checkItem/selectAllCheckItem").then(r=>{
        this.checkItemOptions = r.data.t
        this.queryParams.checkItemIds = this.checkItemOptions.map(item => item.checkItemId)
      })
    },
    //状态字典的解析
    initStatus(){
      this.$axios.get("/system/api/dict/data/findByType/his_check_result_status").then(result=>{
        this.statusOptions=result.data.t;
      })
    },
    //status
    statusFormat(row){
      let v=row.resultStatus;
      return this.formatDict3(this.statusOptions,v);
    },
    //录入检查结果
    addMsg(){
      this.$axios.post("/check/api/checkResult/addMsg/"+this.cocId+"/"+this.textarea).then(r=>{
        this.$message({
          showClose: true,
          message: r.data.msg,
          type: 'success'
        });
        this.dialogVisible=false;
        // 刷新数据
        this.queryData();
      })
    },
    ossPolicy(file){
      //上传前进行服务器签名
      return new Promise((resolve,reject) => {
        //请求后端
        this.$axios.get(`http://localhost:8082/oss/policy`).then(r=>{
          this.objData.OSSAccessKeyId=r.data.accessId;
          this.objData.policy=r.data.policy;
          this.objData.Signature=r.data.signature;
          this.objData.dir=r.data.dir;
          this.objData.host=r.data.host;//直传oss的服务器地址
          this.objData.key=r.data.dir+"${filename}";
          resolve(true);//继续上传
        })
        .catch(function (error){
          console.log(error);
          reject(false)//停止上传
        });
      });
    },
    // /*上传文件*/
    // handleRemove(file, fileList) {
    //   console.log(file, fileList);
    // },
    // handlePreview(file) {
    //   console.log(file);
    // },
    // handleExceed(files, fileList) {
    //   this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    // },
    // beforeRemove(file, fileList) {
    //   return this.$confirm(`确定移除 ${file.name}？`);
    // },
    // /*弹出层关闭*/
    // handleClose(done) {
    //   this.$confirm('确认关闭？')
    //       .then(_ => {
    //         done();
    //       })
    //       .catch(_ => {
    //       });
    // }
  },
  created() {
    //加载系统状态
    this.initStatus();
    //加载状态的字典数据
    this.selectAllCheckItem()
    //展示页面
    this.queryData()
  },
}
</script>

<style scoped>
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
