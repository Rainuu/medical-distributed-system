<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" ref="formInline" style="float: left">
      <el-form-item label="科室名称" label-width="100px">
        <el-select v-model="formInline.deptId" placeholder="请选择活动区域">
          <el-option
              v-for="item in this.deptInfo"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="状态">
        <el-select v-model="formInline.status" placeholder="可用状态">
          <el-option
              v-for="dict in dictList.filter((n)=>{return n.dictType==='sys_normal_disable'})"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <div class="block">
          <el-date-picker
              v-model="formInline.between"
              type="datetimerange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :default-time="['12:00:00']">
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getInfo"  icon="el-icon-search">查询</el-button>
        <el-button type="primary" @click="resetForm('formInline')" icon="el-icon-refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="float: left">
      <el-button type="primary" icon="el-icon-plus" @click="addDeptVisible=true">新增</el-button>
      <el-button type="success" icon="el-icon-edit" >修改</el-button>
      <el-button type="danger" icon="el-icon-delete">删除</el-button>
    </div>
    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        border
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          align="center"
          prop="deptId"
          label="科室ID"
          >
      </el-table-column>
      <el-table-column
          align="center"
          prop="deptName"
          label="科室名称">
      </el-table-column>
      <el-table-column
          align="center"
          prop="deptNumber"
          label="科室编码">
      </el-table-column>
      <el-table-column
          align="center"
          prop="regNumber"
          label="当前挂号量">
      </el-table-column>
      <el-table-column
          align="center"
          prop="orderNum"
          label="排序码">
      </el-table-column>
      <el-table-column
          align="center"
          prop="deptLeader"
          label="负责人">
      </el-table-column>
      <el-table-column
          align="center"
          prop="leaderPhone"
          label="电话">
      </el-table-column>
      <el-table-column
          align="center"
          prop="status"
          label="状态"
 :formatter="(row)=>{return this.dictFormat(row,row.status,'sys_normal_disable')}">
      </el-table-column>
      <el-table-column
          align="center"
          label="创建时间"
>
        <template slot-scope="scope">{{ scope.row.createTime }}</template>
      </el-table-column>
      <el-table-column
          align="center"
          fixed="right"
          label="操作"
          min-width="150px">
        <template slot-scope="scope"  align="center">
          <el-button @click="update(scope.row)" type="text" size="small" icon="el-icon-edit">修改</el-button>
          <el-button @click="delDept(scope.row.deptId)" type="text" size="small" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="float: left">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="this.pagedata.current"
          :page-sizes="[2, 4, 6, 8]"
          :page-size="this.pagedata.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="this.pagedata.total">
      </el-pagination>
    </div>
    <el-dialog
        title="提示"
        :visible.sync="addDeptVisible"
        width="30%"
        :before-close="handleClose">
      <el-form ref="deptForm" :model="deptForm" :rules="rules" label-width="80px" class="demo-ruleForm">
        <el-form-item label="部门Id" hidden="true">
          <el-input v-model="deptForm.deptId"></el-input>
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="deptForm.deptName"></el-input>
        </el-form-item>
        <el-form-item label="科室编码" prop="deptNumber">
          <el-input v-model="deptForm.deptNumber"></el-input>
        </el-form-item>
        <el-form-item label="挂号开始编码">
          <el-input-number v-model="deptForm.regNumber"  :min="1" :max="10" label="描述文字"></el-input-number>
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="deptForm.deptLeader"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="deptForm.LeaderPhone"></el-input>
        </el-form-item>
        <el-form-item label="排序码">
          <el-input-number v-model="deptForm.orderNum"  :min="1" :max="10" label="描述文字"></el-input-number>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio v-model="deptForm.status" label="0">正常</el-radio>
          <el-radio v-model="deptForm.status" label="1">停用</el-radio>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('deptForm')">立即创建</el-button>
          <el-button @click="handleClose">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import qs from 'qs'
export default {
  methods: {
    addDept(){},
    getInfo(){
    let seach={...this.formInline,...this.pagedata}
      this.$axios.post('system/api/dept/getAll',qs.stringify(seach)).then(res=>{
        this.tableData=res.data.t.records
        this.pagedata.current=res.data.t.current
        this.pagedata.size=res.data.t.size
        this.pagedata.total=res.data.t.total
      })
    },
    resetForm(formName) {
      this.formInline.deptId='';
      this.formInline.status='';
      this.formInline.between=[];
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    update(row){
      this.addDeptVisible=true
      this.deptForm=JSON.parse(JSON.stringify(row))
    },
    //删除角色
    delDept(id){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        this.$axios.delete('/system/api/dept/'+id).then(res=>{
          if (res.data.t){
            this.getInfo()
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
    //添加科室
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/system/api/dept/add",qs.stringify(this.deptForm)).then(res=>{
            if (res.data.t){
              this.$message.success("添加成功")
              this.addDeptVisible=false;
              this.deptForm={};
              this.getInfo()
            }else {
              this.$message.error("添加失败")
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    handleSizeChange(val) {
      this.pagedata.size=val;
      this.getInfo()
    },
    handleCurrentChange(val) {
      this.pagedata.current=val;
      this.getInfo()
    },
    //字典解析
    dictFormat(row, column, dictType){
      return this.formatDict( this.dictList,column, dictType)
    },
//获取全部部门
    initDept() {
      this.$axios.get("/system/api/dept/list").then(result => {
        this.deptInfo = result.data.t;
      })
    },
    //初始化字典
    getDict() {
      this.$axios.get('/system/api/dict/data/getall').then(res => {
        this.dictList = res.data.t
      })
    },
  },
  data() {
    return {
      deptForm: {
      deptId: '',
        deptName:'',
        deptNumber:'',
        regNumber:'',
        deptLeader:'',
        LeaderPhone:'',
        orderNum:'',
        status:'',
    },
     addDeptVisible:false,
      //分页数据
      pagedata:{
        current:1,
        size:2,
        total:0
      },
      deptInfo:[],
      dictList:[],
      //展示信息
      tableData: [],
      multipleSelection: [],
      //模糊查询表单
      formInline: {
        deptId:'',
        status:'',
        between:'',
      },
      rules:{
        deptName:[
          { required: true, message: '请输入科室名称', trigger: 'blul' },
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        deptNumber:[
          { required: true, message: '请输入科室编码', trigger: 'blul' },
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ]
      }
    }

  },
  created() {
    this.getDict()
    this.initDept()
    this.getInfo()
  }


}
</script>