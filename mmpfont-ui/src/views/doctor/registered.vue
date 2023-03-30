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
            <el-button size="mini" type="primary" icon="el-icon-search" @click="handleIdCardQuery('queryform')">
              加载身份证信息</el-button>
          </el-form-item>
          <el-form-item>
            <el-button size="mini" type="warning" @click="handleIdCardQuery('queryform')">加载患者信息</el-button>
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
          <el-form-item label="性别" prop="sex" :formatter="sexFormat" >
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
              <span style="margin-left:20px">挂号费:￥{{ queryDeptParams.regItemAmount }}</span>
              <el-button type="danger" icon="el-icon-search" :disabled="single" size="small" @click="handleRegistration">挂号收费</el-button>
            </div>
          </el-col>
        </el-row>
      </el-card>
      <!-- 挂号项目栏结束 -->
    </div>
    <div style="margin-top: 20px;">
      <el-form ref="queryDeptForm" :model="queryDeptParams" :inline="true" label-width="68px">
        <el-form-item label="所属科室" prop="deptId" >
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
        <el-form-item label="挂号类型" prop="schedulingType" :formatter="typeFormatter">
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
        <el-form-item label="挂号时段" prop="subsectionType" :formatter="periodFormatter">
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
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleDeptQuery">搜索</el-button>
        <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetDeptQuery">重置</el-button>
      </el-form-item>
        <!-- 部门数据表格开始 -->
        <el-table border :data="deptTableList" highlight-current-row @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="科室ID" align="center" prop="deptId" />
          <el-table-column label="科室名称" align="center" prop="deptName" :formatter="deptFormatter"/>
          <el-table-column label="当前号数" align="center" prop="regNumber" />
        </el-table>

      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 选中部门Ids
      ids: [],
      // 部门数据表格显示
      deptTableList:[],
      patientform: {},
      patientParams:{},
      tableData:[],
      //性别的字典
      sexOptions:[],
      // 有号部门的查询条件
      queryDeptParams: {
        deptId: undefined,
        schedulingType: '1',
        subsectionType: '1',
        schedulingDay: new Date(),
        regItemId: 1,
        regItemAmount: '6'

      },
      //挂号的四个按钮
      regItemOptions:[],
      //所有的部门信息下拉列表
      deptOptions:[],
      //挂号的类型下拉列表
      registrationType:[],
      //挂号的时段下拉列表
      registrationPeriod:[],
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
  watch: {
    patientParams: {
      handler: function() {
        if (this.patientParams.birthday !== undefined) {
          this.patientParams.age = this.getAge(this.patientParams.birthday.substring(0, 10))
        }
      },
      deep: true
    }
  },
  created() {
    //查询性别字典
    this.initSex();
    //加载挂号的四个按钮 门诊急诊等按钮
    this.queryButton();
    //查询所有部门数据
    this.queryDept();
    //查询查询挂号列表的挂号时段字典信息
    this.queryRegistrationType();
    //查询挂号列表的挂号类型字典信息
    this.queryRegistrationPeriod();
    // 搜索医生排班
    this.queryDoctor();
  },
  methods: {
    // 重置
    resetDeptQuery() {
      // this.resetForm('queryDeptForm');
      this.queryDeptParams={};
      this.queryDept();
    },
    // 搜索
    handleDeptQuery() {
      // this.queryDoctor();
      this.$axios.post("doctor/registered/findDoctocList", this.queryDeptParams).then(result => {
        // alert(JSON.stringify(result.data))
        this.deptTableList = result.data.t;
      });
      // this.queryDept();
    },
    //加载排班数据
    // queryDoctor() {
    //
    //   this.$axios.post("doctor/registered/findDoctocList", this.queryDeptParams).then(result => {
    //     // alert(JSON.stringify(result.data))
    //     this.deptTableList = result.data.t;
    //   });
    // },
    //查询门诊急诊等四个按钮
    queryButton() {
      this.$axios.get("/doctor/patient/registeredItem").then(result => {
        this.regItemOptions = result.data.t;
      })
    },
    //查询表格按钮的方法
    handleIdCardQuery(formName) {
      this.$axios.post("/doctor/patient/patientAll/" + this.patientParams.idCard,).then(result => {
        this.patientParams = result.data.t;
        this.patientParams.age = this.getAge(this.patientParams.birthday);
        this.total = result.data.t.total;
      })
      // 根据输入的身份证号加载患者数据
      if (this.patientParams.idCard === undefined) {
        this.msgError('请输入要查询的身份证号')
        return
      }
      this.loading = true
      getPatientByIdCard(this.patientParams.idCard).then(res => {
        console.log(res.data)
        this.patientParams = res.data
        this.patientParams.age = this.getAge(res.data.birthday.substring(0, 10))
        this.loading = false
      }).catch(() => {
        this.loading = false
        this.patientParams = {sex: '2'}
      })

    },
    // 查询所有部门
     queryDept(){
    this.$axios.get("/system/api/dept/list").then(result => {
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
    // 把挂号时段翻译
    periodFormatter(row){
      let v=row.subsectionType;
      return this.formatDict2(this.registrationPeriod,v);
    },
    //查询挂号时段字典下拉列表  上午、下午、晚上
    queryRegistrationPeriod(){
      this.$axios.get("/system/api/dict/data/findByType/his_subsection_type").then(result=>{
        this.registrationPeriod=result.data.t;

      })
    },

    //查询挂号类型字典下拉列表   门诊、急诊
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
    // 表格选择中行事件
    handleSelectionChange(selection) {


      this.ids = selection.map(item => item.deptId)
      this.single = selection.length !== 1
    },
    //初始化性别
    initSex(){
      this.$axios.get("/doctor/api/patient/queryDataType/sys_user_sex").then(result=>{
        this.sexOptions=result.data.t;
      })
    },
    //把性别的0/1换成男女等
    sexFormat(row){
      let v=row.sex;
      return this.formatDict2(this.sexOptions,v);
    },
    // 更新挂号项目类型事件
    registeredItemChange(id) {
      this.regItemOptions.filter(item => {
        if (id === item.regItemId) {
          this.queryDeptParams.regItemId = item.regItemId
          this.queryDeptParams.regItemAmount = item.regItemFee
        }
      })
      // 动态显示挂号信息
      if (id === 1 || id === 2) {
        this.queryDeptParams.schedulingType = '1'
      } else {
        this.queryDeptParams.schedulingType = '2'
      }
      // 重新查询有号的部门
      this.queryDeptParams()
    },
    // 挂号收费
    handleRegistration() {
      const tx = this
      tx.$refs['form'].validate(vaid => {
        console.log(vaid)
        if (vaid) {
          tx.$confirm('是否确认给【' + this.patientParams.name + '】进行挂号?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            closeOnClickModal: false,
            type: 'waring',
            center: true
          }).then(() => {
            // 取出要挂号的部门
            const deptId = tx.ids[0]
            // 组装要提交到后台的挂号数据
            const data = { 'patientDto': tx.patientParams, 'registrationDto': {
                deptId: deptId,
                regItemId: tx.queryDeptParams.regItemId,
                regItemAmount: tx.queryDeptParams.regItemAmount,
                visitDate: this.moment(tx.queryDeptParams.schedulingDay).format('YYYY-MM-DD'),
                schedulingType: tx.queryDeptParams.schedulingType,
                subsectionType: tx.queryDeptParams.subsectionType
              }}
            // 提交数据到后台
            addRegistration(data).then(res => {
              const regId = res.data
              tx.msgSuccess('挂号成功 挂号单号为【' + regId + '】')
              // 清空页面上的数据
              tx.resetDeptQuery()
              tx.patientParams = { sex: '2' }

              tx.$confirm('是否确认给挂号ID为【' + regId + '】的挂号收费?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                closeOnClickModal: false,
                type: 'waring',
                center: true
              }).then(() => {
                // 重新查询挂号列表
                collectFee(regId).then(r => {
                  tx.msgSuccess('收费成功')
                  tx.getDeptForScheduling()
                }).catch(() => {
                  tx.msgError('收费失败')
                })
              }).catch(() => {
                tx.msgError('收费取消')
              })
            }).catch(() => {
              tx.msgError('挂号失败')
            })
          }).catch(() => {
            tx.msgError('挂号已取消')
          })
        }
      })
    }

  }
}
</script>

<style>

.box-card{
  padding: 0px;
  margin: 0px;
  margin-bottom: 8px;
}

</style>
