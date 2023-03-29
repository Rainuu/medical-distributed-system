<!--患者库-->
<template>
  <div id="zhuti">
    <el-form :inline="true" :model="patientform" class="demo-form-inline">
      <el-form-item label="患者姓名">
        <el-input v-model="patientform.patientName" placeholder="请输入患者姓名"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="patientform.phoneNumber" placeholder="请输手机号"></el-input>
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="patientform.idNumber" placeholder="请输身份证号"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="queryfrom('Patientform')">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-refresh" @click="clearfrom('Patientform')">重置</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-form>
        <el-button icon="el-icon-plus" type="primary" @click="lookPatient()" :disabled="this.lock">查看档案</el-button>
        <el-button icon="el-icon-edit" type="success" @click="queryIllness()" :disabled="this.lock">查看就诊病历</el-button>

        <!-- 查看患者档案弹出层开始 -->
        <el-dialog
            :title="title"
            :visible.sync="lookpatient"
            width="1000px"
            center
            append-to-body
        >
          <el-form label-position="left" label-width="120px" inline class="demo-table-expand">
            <el-form-item label="ID:">
              <span v-text="patientObj.patientId" />
            </el-form-item>
            <el-form-item label="姓名:">
              <span v-text="patientObj.name" />
            </el-form-item>
            <el-form-item label="身份证号:">
              <span v-text="patientObj.idCard" />
            </el-form-item>
            <el-form-item label="电话:">
              <span v-text="patientObj.phone" />
            </el-form-item>
            <el-form-item label="患者性别:">
              <span v-text="patientObj.sex" />
            </el-form-item>
            <el-form-item label="出生年月:">
              <span v-text="patientObj.birthday" />
            </el-form-item>
            <el-form-item label="地址信息:">
              <span v-text="patientObj.address" />
            </el-form-item>
            <el-form-item label="过敏信息:">
              <span v-text="patientObj.allergyInfo" />
            </el-form-item>
            <el-form-item label="是否完善信息:">
              <span v-text="patientObj.isFinal" />
            </el-form-item>
            <el-form-item label="最后登录ip:">
              <span v-text="patientObj.lastLoginIp" />
            </el-form-item>
            <el-form-item label="最后登录时间:">
              <span v-text="patientObj.lastLoginTime" />
            </el-form-item>
            <el-form-item label="创建时间:">
              <span v-text="patientObj.createTime" />
            </el-form-item>
            <el-form-item label="更新时间:">
              <span v-text="patientObj.updateTime" />
            </el-form-item>
            <el-form-item label="紧急联系人:">
              <span v-text="patientFileObj.emergencyContactName" />
            </el-form-item>
            <el-form-item label="紧急联系人电话:">
              <span v-text="patientFileObj.emergencyContactPhone" />
            </el-form-item>
            <el-form-item label="关系:">
              <span v-text="patientFileObj.emergencyContactRelation" />
            </el-form-item>
            <el-form-item label="左耳听力:">
              <span v-text="patientFileObj.leftEarHearing" />
            </el-form-item>
            <el-form-item label="右耳听力:">
              <span v-text="patientFileObj.rightEarHearing" />
            </el-form-item>
            <el-form-item label="左眼视力:">
              <span v-text="patientFileObj.leftVision" />
            </el-form-item>
            <el-form-item label="右眼视力:">
              <span v-text="patientFileObj.rightVision" />
            </el-form-item>
            <el-form-item label="身高:">
              <span v-text="patientFileObj.height" />
            </el-form-item>
            <el-form-item label="体重:">
              <span v-text="patientFileObj.weight" />
            </el-form-item>
            <el-form-item label="血型:">
              <span v-text="patientFileObj.bloodType" />
            </el-form-item>
            <el-form-item label="个人史:">
              <span v-text="patientFileObj.personalInfo" />
            </el-form-item>
            <el-form-item label="家族史:">
              <span v-text="patientFileObj.familyInfo" />
            </el-form-item>
            <el-form-item label="档案创建时间:">
              <span v-text="patientFileObj.createTime" />
            </el-form-item>
            <el-form-item label="档案更新时间:">
              <span v-text="patientFileObj.updateTime" />
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
        <el-button @click="lookpatient=false">取 消</el-button>
      </span>
        </el-dialog>
        <!-- 查看患者档案弹出层结束 -->

        <!-- 患者病历的弹出层开始 -->
        <el-dialog
            :title="title"
            :visible.sync="careHistoryOpen"
            width="1000px"
            center
            append-to-body
        >
          <el-form label-position="left" label-width="120px" inline class="demo-table-expand">
            <el-card>
              <el-form-item label="ID:">
                <span v-text="patientObj.patientId" />
              </el-form-item>
              <el-form-item label="姓名:">
                <span v-text="patientObj.name" />
              </el-form-item>
              <el-form-item label="身份证号:">
                <span v-text="patientObj.idCard" />
              </el-form-item>
              <el-form-item label="电话:">
                <span v-text="patientObj.phone" />
              </el-form-item>
            </el-card>
          </el-form>

          <el-card style="margin-top:3px">
            <div v-if="allPatientMsg.length>0">
              <el-collapse accordion>
                <el-collapse-item
                    v-for="(item,index) in allPatientMsg"
                    :key="index"
                    :name="index"
                >
                  <template slot="title">
                    科室:【{{ item.deptName }}】就诊时间:【{{ item.careTime }}】
                  </template>
                  <div>主诉:{{ item.caseTitle }}</div>
                  <div>诊断信息:{{ item.caseResult }}</div>
                  <div>医生建议:{{ item.doctorTips }}</div>
                  <div>备注:{{ item.remark }}</div>


                  <!--当前挂号单历史处方开始-->
                  <el-collapse v-show="item.careOrderList.length>0" accordion>
                    <el-collapse-item v-for="(it,i) in item.careOrderList" :key="i">
                      <template slot="title">
                        【 {{ it.coType==='0'?'药品处方':'检查处方' }}】【{{ i+1 }}】 <span>【处方总额】:￥{{ it.allAmount }}</span>
                      </template>
                      <el-table
                          v-loading="loading"
                          border
                          :data="it.careOrderItemList"
                      >
                        <el-table-column label="序号" align="center" type="index" width="50" />
                        <el-table-column :label="it.coType==='0'?'药品名称':'项目名称'" align="center" prop="itemName" />
                        <el-table-column label="数量" align="center" prop="num" />
                        <el-table-column label="单价(元)" align="center" prop="price" />
                        <el-table-column label="金额(元)" align="center" prop="amount" />
                        <el-table-column label="备注" prop="remark" align="center" />
                        <el-table-column label="状态" prop="status" align="center" :formatter="statusFormatter" />
                      </el-table>
                    </el-collapse-item>
                  </el-collapse>
                </el-collapse-item>
              </el-collapse>
            </div>
            <div v-else style="text-align:center">
              暂无就诊数据
            </div>
          </el-card>

          <span slot="footer" class="dialog-footer">
        <el-button @click="cancelHistory">取 消</el-button>
      </span>
        </el-dialog>
        <!-- 患者病历的弹出层结束 -->



      </el-form>
      <!--			患者表格展示-->
      <el-table ref="multipleTable" :data="patient" tooltip-effect="dark" border
                @selection-change="handleSelectionChange">

        <el-table-column prop="id" type="selection" width="60">
        </el-table-column>
        <el-table-column label="姓名" align="center" prop="name" />
        <el-table-column label="电话" align="center" prop="phone" />
        <el-table-column label="身份证号" align="center" prop="idCard" />
        <el-table-column label="出生年月" align="center" prop="birthday">
          <template slot-scope="scope">
            <span>{{ scope.row.birthday.substring(0,10) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="年龄" align="center" prop="age"/>
        <el-table-column label="性别" prop="sex" align="center" :formatter="sexFormatter" />
        <el-table-column label="信息状态" prop="isFinal" align="center" :formatter="isFinalFormatter" />
        <el-table-column label="创建时间" align="center" prop="createTime" />
      </el-table>
      <!--			患者表格展示结束-->
      <!--			分页开始-->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="this.currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="this.pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="this.total">
      </el-pagination>
      <!--			分页结束-->
    </div>
  </div>



</template>

<script>
import {formatDict} from "@/utils/DictFormat";

export default {
  data() {
    return {
      careOrders:[],
      // 是否显示档案弹出层
      fileOpen: false,
      // 弹出层标题
      title: '',
      // 是否打开病历弹出层
      careHistoryOpen: false,
      // 患者所有就诊信息
      allPatientMsg: [],
      // 判断查看档案跟病例
      lock:true,
      // 所有员工的信息
      patient:[],
      patientform: {},
      activeNames: ['1'],
      activeName: ['1'],
      //查看患者的档案
      lookpatient: false,
      //查看患者的病历
      queryillness: false,
      //分页的数据
      currentPage: 1,
      pageSize: 5,
      total: 0,
      //表格里的数据
      tableData1: [{}],
      tableData: [],
      // 患者信息
      patientObj: {},
      // 档案信息
      patientFileObj: {},
      // 性别字典
      sexOptions:[],
      // 完善信息状态
      isFinalOptions: [],
      // 订单状态
      statusOptions: [],





    }
  },
  created() {
    // 加载患者病例信息
    // this.queryHistory();
    // 加载患者信息
    this.queryfrom();

    //加载性别
    this.initSex();

    // 加载信息状态
    this.initisFinal();

    // 加载处方
    this.ChuFangItem();

    //  加载处方详情的状态字典数据
   this.initistatus();


  },
  methods: {
    // 获取处方状态
    initistatus() {
      this.$axios.get("/system/api/dict/data/findByType/his_order_details_status").then(result=>{
        this.statusOptions = result.data.t;
      })
    },
    // 处方状态的解析
    statusFormatter(row) {
      let v = row.status;
      return this.formatDict2(this.statusOptions,v);
    },
    // 获取处方详情
    ChuFangItem() {
      this.$axios.get("doctor/patient/FindByItem/"+this.patientObj.patientId,+this.patientFileObj.chId).then(result=> {
        this.careOrders = result.data.t;
      })
    },
    // 初始性别字典
    initSex() {
      this.$axios.get("/system/api/dict/data/findByType/sys_user_sex").then(result=> {
        this.sexOptions = result.data.t;
      })
    },

    //初始信息字典
    initisFinal() {
      this.$axios.get("/system/api/dict/data/findByType/his_patient_msg_final").then(result=>{
        this.isFinalOptions = result.data.t;
      })
    },
    // 性别的解析
    sexFormatter(row) {
      let v = row.sex;
      return this.formatDict2(this.sexOptions,v);
    },

    // 状态的解析
    isFinalFormatter(row) {
      let v = row.isFinal;
      return this.formatDict2(this.isFinalOptions,v);
    },

    //根据出生日期获取年龄
    getAge(val){
      let currentYear = new Date().getFullYear() //当前的年份
      let calculationYear = new Date(val).getFullYear() //计算的年份
      const wholeTime = currentYear + val.substring(4) //周岁时间
      const calculationAge = currentYear - calculationYear //按照年份计算的年龄
      //判断是否过了生日
      if (new Date().getTime() > new Date(wholeTime).getTime()){
        return calculationAge
      }else {
        return calculationAge - 1
      }
    },
    //每页显示几条
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);

      this.pageSize = val;
      this.queryfrom();
    },
    //当前页数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.queryfrom();
    },
    //重置表单按钮方法
    queryfrom(formName) {
      // alert(JSON.stringify(this.patientform))
      this.$axios.post("doctor/patient/searchPat/"+this.currentPage+"/"+this.pageSize,this.patientform).then(result=>{
        this.patient=result.data.t.records;
        for(var i=0;i<this.patient.length;i++){
          this.patient[i].age=this.getAge(this.patient[i].birthday);
          // console.log(this.patient[i].age);
        }
        this.total=result.data.t.total;
      })
    },
    clearfrom(formName) {
      this.patientform={};
      this.queryfrom();
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
      if(val ){
        if (val.length>0&&val.length<2){
          this.lock=false;
        }else {
          this.lock=true;
        }

        this.patientObj = val[0]

        this.patientObj.sex = (this.patientObj.sex === '0' ? '男' : sex === '1' ? '女' : '未知')
        this.patientObj.isFinal =this.patientObj.isFinal === '0' ? '未完善' : '已完善'
        // alert(JSON.stringify(val[0]))
        this.fileOpen = true
        this.title = '查询患者档案信息'
        // this.patientFileObj = {}

        // this.patientObj = {}


        // this.multipleSelection = val;
        console.log(JSON.stringify(this.patientObj))
        // console.log(JSON.stringify(this.tableData.id))
        // console.log(val);
      }

    },

    // 取消
    cancelFile() {
      this.fileOpen = false
      this.title = ''
    },
    //查看患者的档案
    lookPatient(row) {
      this.lookpatient = true;
    },
    //查看患者的病历
    queryIllness() {
      this.queryillness = true;
      this.careHistoryOpen = true

      // console.log(JSON.stringify(this.patientObj.patientId))
      // this.$axios.get("/doctor/patient/getCarPationId/"+this.patientObj.patientId).then(result=> {
      //   this.allPatientMsg = result.data.t;
      //   alert(JSON.stringify(result.data.t));
      // })
      // 处方
      this.$axios.get("/doctor/patient/CareHistoryByIdAll/"+this.patientObj.patientId).then(result=> {
        this.allPatientMsg = result.data.t;

      })
      // // 查询患者档案信息
      // this.$axios.get("/doctor/patient/getByFileId/"+patientId).then(result=> {
      //   if (result.data.t !== null) {
      //     this.patientFileObj=result.data.t;
      //   }
      // })

      this.title = '查询患者病历信息'
    },

    handleChange(val) {
      console.log(val);
    },

    // 关闭就诊信息的弹出层
    cancelHistory() {
      this.careHistoryOpen = false
      this.allPatientMsg = []
    }


  }
}
</script>

<style>
#zhuti
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
