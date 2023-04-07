<!--门诊挂号-->
<template>
  <div id="zhuti">
    <div>
      <el-form :inline="true" ref="queryform" :model="patientParams" class="demo-form-inline">
        <el-tabs type="border-card">
          <el-form-item label="身份证号">
            <el-input style="width: 800px;" v-model="patientParams.idCard" placeholder="请输身份证号" clearable></el-input>
          </el-form-item>
          <el-form-item style="margin-left: 100px;">
            <el-button size="mini" type="primary" icon="el-icon-search" @click="queryfrom('queryform')">
              加载身份证信息</el-button>
          </el-form-item>
          <el-form-item>
            <el-button size="mini" type="warning" @click="queryfrom('queryform')">加载患者信息</el-button>
          </el-form-item>
        </el-tabs>
      </el-form>
    </div>
    <!-- 患者基本信息表单开始 -->
    <div style="margin-top: 20px;">
      <el-form ref="form" :model="patientParams" :rules="rules" :inline="true" label-width="88px">
        <el-tabs type="border-card">
          <el-form-item label="身份证号" prop="idCard">
            <el-input
                v-model="patientParams.idCard"
                placeholder="请输身份证号"
                clearable
                size="small"
                style="width:200px"
            />
          </el-form-item>
          <el-form-item label="患者姓名" prop="name">
            <el-input
                v-model="patientParams.name"
                placeholder="请输入患者姓名"
                clearable
                size="small"
                style="width:200px"
            />
          </el-form-item>
          <el-form-item label="患者电话" prop="phone">
            <el-input
                v-model="patientParams.phone"
                placeholder="请输入患者电话"
                clearable
                size="small"
                style="width:200px"
            />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="patientParams.sex">
              <el-radio
                  v-for="d in sexOptions"
                  :key="d.dictValue"
                  :label="d.dictValue"
                  :value="d.dictValue"
              >{{ d.dictLabel }}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="出生日期" prop="birthday">
            <el-date-picker
                v-model="patientParams.birthday"
                size="small"
                style="width:200px"
                value-format="yyyy-MM-dd"
                type="date"
            />
          </el-form-item>
          <el-form-item label="患者年龄" prop="age">
            <el-input
                v-model="patientParams.age"
                :readonly="true"
                placeholder="请输入患者年龄"
                clearable
                size="small"
                style="width:200px"
            />
          </el-form-item>
          <el-form-item label="地址信息" prop="address">
            <el-input
                v-model="patientParams.address"
                placeholder="请输入地址信息"
                clearable
                style="width:500px"
                size="small"
            />
          </el-form-item>
        </el-tabs>
      </el-form>
    </div>
    <!-- 患者基本信息表单结束 -->
    <div style="margin-top: 20px;">
      <!-- 挂号项目栏开始 -->
      <el-card class="box-card">
        <el-row>
          <el-col :span="12">
            <el-radio-group v-model="queryDeptParams.regItemId" @change="registeredItemChange">
              <el-radio-button
                  v-for="d in regItemOptions"
                  :key="d.regItemId"
                  :label="d.regItemId"
                  :value="d.regItemId"
              >
                {{ d.regItemName }}
              </el-radio-button>
            </el-radio-group>
          </el-col>
          <el-col :span="12">
            <div style="float:right">
              <span style="margin-left:20px">挂号费:￥{{ this.queryDeptParams.regItemAmount }}</span>
              <el-button type="danger" icon="el-icon-search" :disabled="single" size="small" @click="handleRegistration">挂号收费</el-button>
            </div>
          </el-col>
        </el-row>
      </el-card>
      <!-- 挂号项目栏结束 -->
    </div>
    <div style="margin-top: 20px;">
      <el-form ref="queryDeptForm"  :model="queryDeptParams" :inline="true" label-width="68px">
        <el-form-item label="所属科室" prop="deptId">
          <el-select
              v-model="queryDeptParams.deptId"
              placeholder="请选择所属科室"
              clearable
              size="small"
              style="width:200px"
          >
            <el-option
                v-for="d in deptOptions"
                :key="d.deptId"
                :label="d.deptName"
                :value="d.deptId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="挂号类型" prop="schedulingType">
          <el-select
              v-model="queryDeptParams.schedulingType"
              placeholder="请选择挂号类型"
              clearable
              size="small"
              style="width:200px"
          >
            <el-option
                v-for="dict in registrationType"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="挂号时段" prop="subsectionType">
          <el-select
              v-model="queryDeptParams.subsectionType"
              placeholder="请选择挂号时段"
              clearable
              size="small"
              style="width:200px"
          >
            <el-option
                v-for="dict in registrationPeriod"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="挂号时间" prop="schedulingDay">
          <el-date-picker
              v-model="queryDeptParams.schedulingDay"
              size="small"
              style="width:240px"
              value-format="yyyy-MM-dd"
              type="date"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="queryConditionRegistration">搜索
          </el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-refresh" @click="clearfrom()">重置</el-button>
        </el-form-item>
        <!-- 部门数据表格开始 -->
        <el-table border :data="deptTableList" highlight-current-row @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="科室ID" align="center" prop="deptId" />
          <el-table-column label="科室名称" align="center" prop="deptName" :formatter="deptFormatter" />
          <el-table-column type="index" :index="indexMethod"  label="当前号数" align="center" prop="registratioNumber"  width="200"/>
        </el-table>
        <!-- 部门数据表格结束 -->
        <!--分页开始-->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="this.pageSize"
                       layout="total, sizes, prev, pager, next, jumper" :total="this.total">
        </el-pagination>
        <!--分页结束-->
      </el-form>


    </div>
  </div>
</template>

<script>
// import scheduling from "@/views/doctor/scheduling";
import moment from "moment";

export default {
  data() {
    return {
      //页面上的所有数据储存
      patientParams:{},
      //挂号按钮的禁用属性
      single:true,
      //当前排班的医生的科室科室id当前号数 表格
      deptTableList:[],
      //性别的字典
      sexOptions:[],
      //有号部门的的查询条件
      queryDeptParams: {
        deptId: undefined,
        schedulingType: '',
        subsectionType: undefined,
        schedulingDay: moment(new Date()).format('YYYY-MM-DD'),
        regItemId: 1,
        regItemAmount: undefined,
      },
      //分页的数据
      currentPage: 1,
      pageSize: 5,
      total: 0,
      //挂号的四个按钮
      regItemOptions:[],
      //所有的部门信息下拉列表
      deptOptions:[],
      //挂号的类型下拉列表
      registrationType:[],
      //挂号的时段下拉列表
      registrationPeriod:[],
      //选中表格里的一行中的数据
      tableData:{},
      //初始挂号数
      initialNumber:0,
      // 表单校验
      rules: {
        name: [
          { required: true, message: '患者姓名不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          { pattern: /^1[3|4|5|7|8|9][0-9]\d{8}$/, message: '请输入正确的手机号', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '身份证号不能为空', trigger: 'blur' }
        ]
      },

    }
  },
  created() {
    //查询性别字典
    this.initSex();
    //加载挂号的四个按钮 门诊急诊等按钮
    this.queryButton();
    //查询所有部门
    this.queryDept();
    //查询查询挂号列表的挂号类型字典信息
    this.queryRegistrationType();
    //查询查询挂号列表的挂号时段字典信息
    this.queryRegistrationPeriod();
    //
    this.deptTableList.schedulingDay=new Date();
  },
  watch: {
    patientParams: {
      handler: function() {
        if (this.patientParams.birthday !== undefined) {
          this.patientParams.age = this.getAge(this.patientParams.birthday)
        }
      },
      deep: true
    }
  },
  methods: {
    //查询门诊急诊等四个按钮
    queryButton(){
      this.$axios.get("/doctor/registered/registeredItem").then(result => {
        this.regItemOptions = result.data.t;
        //给挂号费 赋值 选中按钮的第一个数据取出数据给 赋值 默认选中第一个按钮
        this.queryDeptParams.regItemId=this.regItemOptions[0].regItemId
        this.queryDeptParams.regItemAmount=this.regItemOptions[0].regItemFee
      })
    },
    // 查询所有部门
    queryDept() {
      this.$axios.get("/system/api/dept/list").then(result => {
        this.deptOptions = result.data.t;
        this.queryDeptParams.deptId = this.deptOptions[0].deptId;
      })
    },
    //查询挂号类型字典下拉列表   门诊、急诊
    queryRegistrationType() {
      this.$axios.get("/system/api/dict/data/findByType/his_scheduling_type").then(result => {
        this.registrationType = result.data.t;
        //起始默认赋值为第一选项
        this.queryDeptParams.schedulingType=result.data.t[0].dictValue;

      })
    },
    //把挂号类型翻译
    typeFormatter(row) {
      let v = row.schedulingType;
      return this.formatDict2(this.registrationType, v);
    },
    // 把挂号时段翻译
    periodFormatter(row) {
      let v = row.subsectionType;
      return this.formatDict2(this.registrationPeriod, v);
    },
    //查询挂号时段字典下拉列表  上午、下午、晚上
    queryRegistrationPeriod() {
      this.$axios.get("/system/api/dict/data/findByType/his_subsection_type").then(result => {
        this.registrationPeriod = result.data.t;
        //获取当前时间
        const data1 = new Date();
        //获取当前时
        const time=data1.getHours()
        //判断是上午还是下午或者晚上
        if (time>0&&time<12){
          //给下拉选项赋值为上午
          this.queryDeptParams.subsectionType=this.registrationPeriod[0].dictValue;
        }else if (time>12&&time<18){
          //给下拉选项赋值为下午
          this.queryDeptParams.subsectionType=this.registrationPeriod[1].dictValue;
        }else {
          //给下拉选项赋值为晚上
          this.queryDeptParams.subsectionType=this.registrationPeriod[2].dictValue;
        }
      })
    },
    //根据出生日期获取年龄
    getAge(val) {
      let currentYear = new Date().getFullYear() //当前的年份
      let calculationYear = new Date(val).getFullYear() //计算的年份
      const wholeTime = currentYear + val.substring(4) //周岁时间
      const calculationAge = currentYear - calculationYear //按照年份计算的年龄
      //判断是否过了生日
      if (new Date().getTime() > new Date(wholeTime).getTime()) {
        return calculationAge
      } else {
        return calculationAge - 1
      }
    },
    //查询表格按钮的方法 加载身份证信息
    queryfrom(formName) {
      this.$axios.post("/doctor/registered/patientAll/"+this.patientParams.idCard).then(result => {
        if (result.data.t==null){
          this.$message({
            showClose: true,
            message: '患者库里没有该患者的信息',
            type: 'error'
          });
        }else {
          this.patientParams = result.data.t;
          this.patientParams.age = this.getAge(this.patientParams.birthday);
          this.total = result.data.t.total;
        }
      })
    },
    // 初始性别字典
    initSex() {
      this.$axios.get("/system/api/dict/data/findByType/sys_user_sex").then(result => {
        this.sexOptions = result.data.t;
      })
    },
    //把性别的0/1换成男女等
    sexFormat(row) {
      let v = row.sex;
      return this.formatDict2(this.sexOptions, v);
    },
    //四个按钮
    registeredItemChange(id){
      this.regItemOptions.filter(item => {
        if (id === item.regItemId) {
          this.queryDeptParams.regItemId = item.regItemId      //门诊或急诊的id
          this.queryDeptParams.regItemAmount = item.regItemFee //挂号的费用
        }
      })
      // 动态显示挂号信息
      if (id === 1 || id === 2) {
        this.queryDeptParams.schedulingType = '1'
      } else {
        this.queryDeptParams.schedulingType = '2'
      }
    },
    //查询按钮根据条件查询排班的科室 id
    queryConditionRegistration(){
      if (this.queryDeptParams.deptId){
        //查询部门当前的流水号
        this.$axios.post("/doctor/careItem/queryDeptNumber/"+this.queryDeptParams.deptId).then(result => {
          this.initialNumber=result.data.t;

        })
        //传输查询条件 查询医生排班信息
        this.$axios.post("/doctor/registered/findDoctocList/"+this.currentPage+"/"+this.pageSize,this.queryDeptParams).then(result => {
          this.deptTableList = result.data.t.records;
          this.total = result.data.t.total;
        })
      }else {
        this.$message({
          showClose: true,
          message: '请选择科室',
          type: 'error'
        });
      }

    },
    //挂号编号
    indexMethod(index){
      return this.initialNumber?this.initialNumber:1;
    },

    //翻译科室
    deptFormatter(row){

      let v=row.deptId
      for (let i = 0; i < this.deptOptions.length; i++) {
        if (this.deptOptions[i].deptId==v){
          return this.deptOptions[i].deptName;
        }
      }
      return "";
    },
    //重置清空表单
    clearfrom(){
      // 下 - 重置
        this.resetForm('queryDeptForm')
        // this.queryConditionRegistration()

      // this.patientParams={};
      // this.queryDeptParams={};
      // this.deptTableList=[];
      //清空之后获取按钮数据取第一个里面的数据赋值给表单 默认选中第一个
      this.queryDeptParams.regItemId=this.regItemOptions[0].regItemId
      this.queryDeptParams.regItemAmount=this.regItemOptions[0].regItemFee




    },
    // 挂号收费
    handleRegistration() {
      if (this.queryDeptParams.deptId){
        if (JSON.stringify(this.patientParams)=="{}"){
          this.$message({
            showClose: true,
            message: '请填写患者的信息',
            type: 'error'
          });
        }else{
          this.$confirm('确定挂号吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            const obj={tableData:this.tableData,patientParams:this.patientParams}
            //把挂单的数据储存起来发送到后端
            this.$axios.post("/doctor/patientFile/storage/"+this.tableData.userId+"/"+this.queryDeptParams.regItemAmount+"/"+this.queryDeptParams.regItemId,obj).then(result => {
              if (result.data.t==true){
                this.$message({
                  showClose: true,
                  message: '添加挂号成功',
                  type: 'success'
                });
                //清空一下页面数据
                this.clearfrom();
              }else {
                this.$message({
                  showClose: true,
                  message: '添加挂号失败',
                  type: 'error'
                });
                //清空一下页面数据
                this.clearfrom();
              }
            })

          }).catch(() => {
            this.$message({
              showClose: true,
              type: 'info',
              message: '已取消删除'
            });
            //清空一下页面数据
            this.clearfrom();
          });
        }
      }else {
        this.$message({
          showClose: true,
          message: '请选择科室',
          type: 'error'
        });
      }
    },
    //表格前面的多选框能拿到所选中的数据
    handleSelectionChange(row){
      this.single = true;
      if (row.length <= 0) {
        return;
      }
      if (row.length > 0 && row.length < 2) {
        this.single = false;
      } else {
        this.single = true;
      }
      this.tableData=row[0];
    },
    //每页显示几条
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.queryConditionRegistration();
    },
    //当前页数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.queryConditionRegistration();
    },

  }
}

</script>

<style>
#zhuti {
  margin: 10px;
}
</style>
