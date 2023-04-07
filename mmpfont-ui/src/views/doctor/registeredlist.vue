<template>
  <div id="zhuti">
    <el-form :inline="true" :model="registrationList" class="demo-form-inline">
      <el-form-item label="所属部门" prop="deptId" >
        <el-select v-model="registrationList.deptId" placeholder="请选择部门"  size="small" style="width:240px" clearable>
          <el-option v-for="dict in deptOptions" :key="dict.deptId" :label="dict.deptName"
                     :value="dict.deptId" />
        </el-select>
      </el-form-item>
      <el-form-item label="患者名称" >
        <el-input v-model="registrationList.patientName" placeholder="请输入患者名称"  clearable></el-input>
      </el-form-item>
      <el-form-item label="挂号类型" >
        <el-select v-model="registrationList.type" clearable placeholder="可用状态" clearable>
          <el-option v-for="d in registrationType"
                     :key="d.dictValue"
                     :label="d.dictLabel"
                     :value="d.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="挂号时段" >
        <el-select v-model="registrationList.timeInterval " placeholder="挂号时段" clearable>
          <el-option v-for="d in registrationPeriod"
                     :key="d.dictValue"
                     :label="d.dictLabel"
                     :value="d.dictValue"
          ></el-option>
        </el-select>
      </el-form-item><br />
      <el-form-item label="挂号状态" >
        <el-select v-model="registrationList.status" placeholder="挂号状态"  clearable>
          <el-option v-for="d in registrationStatuss"
                     :key="d.dictValue"
                     :label="d.dictLabel"
                     :value="d.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="挂号时间" >
        <el-date-picker v-model="registrationList.time" value-format="yyyy-MM-dd HH:mm:ss" type="date" placeholder="选择日期时间"   clearable>
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="queryRegistration">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-refresh" @click="clearfrom">重置</el-button>
      </el-form-item>
    </el-form>


    <div>
      <el-table
          :data="tableData"
          @selection-change="handleSelectionChange"
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="挂号ID">
                <span>{{ props.row.registrationId }}</span>
              </el-form-item>
              <el-form-item label="挂号员">
                <span>{{ props.row.doctorName }}</span>
              </el-form-item>
              <el-form-item prop="create_time" label="创建时间">
                <span>{{ props.row.createTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="patientName" label="患者姓名">
        </el-table-column>
        <el-table-column prop="deptId" label="挂号科室" :formatter="deptFormatter">
        </el-table-column>
        <el-table-column prop="doctorName" label="接诊医生">
        </el-table-column>
        <el-table-column prop="registrationAmount" label="挂号费用">
        </el-table-column>
        <el-table-column prop="registrationNumber" label="流水编号">
        </el-table-column>
        <el-table-column prop="registrationStatus" label="挂号状态" :formatter="statusFormatter">
        </el-table-column>
        <el-table-column prop="visitDate" label="就诊日期">
        </el-table-column>
        <el-table-column prop="schedulingType" label="挂号类型" :formatter="typeFormatter">
        </el-table-column>
        <el-table-column prop="subsectionType" label="挂号时段" :formatter="periodFormatter">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="200px">
          <template slot-scope="scope">
            <el-button v-if="scope.row.registrationStatus=='0'" @click="handleSuccess(scope.row)" icon="el-icon-check" type="success" size="small">收费</el-button>
            <el-button v-if="scope.row.registrationStatus=='1'" @click="handleReturn(scope.row)" icon="el-icon-close" type="danger" size="small">退号</el-button>
            <el-button v-if="scope.row.registrationStatus=='0'" @click="handleFalse(scope.row)" icon="el-icon-check" type="danger" size="small">作废</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="this.currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="this.pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="this.total">
      </el-pagination>
    </div>

  </div>



</template>

<script>

export default {
  data() {
    return {
      //所有的部门信息下拉列表
      deptOptions:[],
      //挂号的类型下拉列表
      registrationType:[],
      //挂号的时段下拉列表
      registrationPeriod:[],
      //挂号列表表格里面的状态信息
      registrationStatuss:[],
      //挂号列表里面的查询条件
      registrationList: {},
      //分页的数据
      currentPage: 1,
      pageSize: 5,
      total: 0,
      //挂号列表表格里的数据
      tableData: []

    }
  },
  created() {
    // 修改收费按钮
    // this.handleSuccess();
    // 加载所有部门
    this.initDept();
    //查询挂号列表里面的所有数据
    this.queryRegistration();
    //查询所有部门
    this.queryDept();
    //查询查询挂号列表的挂号类型字典信息
    this.queryRegistrationType();
    //查询查询挂号列表的挂号时段字典信息
    this.queryRegistrationPeriod();
    //查询查询挂号列表的挂号状态字典信息
    this.queryStatus();
  },
  methods: {
    // 收费、退号、作废 按钮点击事件
    handleSuccess(row) {
      this.$axios.post("/doctor/registered/handleSuccess/"+row.registrationId).then(result=> {
        this.queryRegistration();

      })
    },
    handleReturn(row) {
      this.$axios.post("/doctor/registered/handleReturn/"+row.registrationId).then(result=> {
        this.queryRegistration();

      })
    },
    handleFalse(row) {
      this.$axios.post("/doctor/registered/handleFalse/"+row.registrationId).then(result=> {
        this.queryRegistration();

      })
    },
    initDept() {
      this.$axios.get("/system/api/dept/list").then(result=> {
        this.deptOptions = result.data.t;
      })
    },
    // 部门的解析   翻译科室
    deptFormatter(row) {
      let v=row.deptId;
      for (let i = 0; i <this.deptOptions.length ; i++) {
        if (this.deptOptions[i].deptId==v) {
          return this.deptOptions[i].deptName;
        }
      }
      return "";
    },
    //查询挂号列表表格里的状态字典
    queryStatus(){
      this.$axios.get("/system/api/dict/data/findByType/his_registration_status").then(result=> {
        this.registrationStatuss = result.data.t;
      })
    },
    // 状态的解析
    statusFormatter(row) {
      let v = row.registrationStatus;
      return this.formatDict2(this.registrationStatuss,v);
    },
    //查询挂号类型字典下拉列表
    queryRegistrationType(){
      this.$axios.get("/system/api/dict/data/findByType/his_scheduling_type").then(result=>{

        this.registrationType=result.data.t;

      })
    },
    //把挂号类型翻译
    typeFormatter(row) {
      let v=row.schedulingType;
      return this.formatDict2(this.registrationType,v);
    },
    //查询所有部门
    queryDept(){
      this.$axios.get("/doctor/registration/getDeptAll").then(result=>{
        this.deptOptions=result.data.t;
      })
    },
    //查询挂号时段字典下拉列表
    queryRegistrationPeriod(){
      this.$axios.get("/system/api/dict/data/findByType/his_subsection_type").then(result=>{
        this.registrationPeriod=result.data.t;

      })
    },
    //把挂号时段翻译
    periodFormatter(row){
      let v=row.subsectionType;
      return this.formatDict2(this.registrationPeriod,v);
    },
    //查询挂号列表表格里面的所有数据
    queryRegistration(){
      this.$axios.post("/doctor/registered/getAllList/"+this.currentPage+"/"+this.pageSize,this.registrationList).then(result=>{
        this.tableData=result.data.t.records;
        this.total=result.data.t.total;
      })
    },


    //每页显示几条
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.queryRegistration();
    },
    //当前页数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.queryRegistration();
    },
    //重置表单按钮方法
    clearfrom(formName) {
      // this.$refs[formName].resetFields();
      this.registrationList={};
      this.queryRegistration();
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


      console.log(JSON.stringify(val))
      // console.log(val);
    }


  }
}
</script>

<style>
#zhuti {
  margin: 10px;
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
