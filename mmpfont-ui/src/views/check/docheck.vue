<!--新开检查-->
<template>
  <div >
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
        <el-form-item label="挂号单号" prop="regId" style="clear:both;float: left">
          <el-input
              v-model="queryParams.regId"
              placeholder="请输入挂号单号"
              clearable
              size="small"
              style="width:380px"
          />
        </el-form-item>
        <el-form-item style="float: left">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="query">搜索</el-button>
          <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!--table表格-->
    <el-table
        ref="refTable"
        :data="tableData"
        border
        highlight-current-row
        style="width: 100%"
        max-height="250"
        @current-change="handleCurrentChange">
      <el-table-column
          type="index"
          width="50"
          label="序号">
      </el-table-column>
      <el-table-column
          prop="itemId"
          label="详细ID"
          width="280">
      </el-table-column>
      <el-table-column
          prop="itemName"
          label="项目名称">
      </el-table-column>
      <el-table-column
          prop="num"
          label="数量">
      </el-table-column>
      <el-table-column
          prop="price"
          label="单价(元)">
      </el-table-column>
      <el-table-column
          prop="amount"
          label="金额(元)">
      </el-table-column>
      <el-table-column
          prop="remark"
          label="备注">
      </el-table-column>
      <el-table-column
          prop="regId"
          label="挂号单号"
          v-if="false">
      </el-table-column>
      <el-table-column
          prop="patientName"
          label="患者姓名"
          v-if="false">
      </el-table-column>
      <el-table-column
          prop="itemRefId"
          label="检查项目Id"
          v-if="false">
      </el-table-column>
      <el-table-column
          prop="patientId"
          label="patientId"
          v-if="false">
      </el-table-column>
      <el-table-column
          prop="status"
          label="状态"
          :formatter="statusFormat"
      >
      </el-table-column>
    </el-table>
    <!-- 开始检查开始 -->
    <el-card style="margin-bottom:3px">
      <div v-if="showBottom" v-loading="bottomLoading">
        <el-form label-position="left" label-width="120px" inline class="demo-table-expand">
          <el-form-item label="挂号单号:">
            <span>{{ fromData.regId }}</span>
          </el-form-item>
          <el-form-item label="患者姓名:">
            <span>{{ fromData.patientName }}</span>
          </el-form-item>
          <el-form-item label="检查项目:">
            <span>{{ fromData.checkItemName }}</span>
          </el-form-item>
          <el-form-item label="检查备注:">
            <span>{{ fromData.remark }}</span>
          </el-form-item>
        </el-form>
        <div>
          <el-button type="primary" style="width:100%" icon="el-icon-plus" @click="handleStartCheck">开始检查</el-button>
        </div>
      </div>
      <div v-else style="text-align:center">
        暂无数据
      </div>
    </el-card>
    <!-- 开始检查结束 -->
  </div>
</template>

<script>
const cityOptions = [];
export default {
  name: "NewOpenInspection",
  data() {
    return {
      //查询参数
      queryParams: {
        regId: undefined,
        checkItemIds: [],
      },
      //是否为全选状态
      checkAll: true,
      //检查项目数据
      checkItemOptions: [],
      cities: cityOptions,
      //是否为半选状态
      isIndeterminate: false,
      // 当前选中的行
      currentRow: undefined,

      formInline: {
        user: '',
        region: ''
      },
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      //表格数据
      tableData: [],
      //系统状态
      statusOptions:[],
      // 是否显示开始检查的card
      showBottom: false,
      // 下面的开始card的遮罩
      bottomLoading: false,
      //表单数据
      fromData:{
        regId:'',
        patientName:'',
        checkItemName:'',
        remark:'',
        cocId:'',
        checkItemId:'',
        price:'',
        patientId:"",
        resultStatus:0
      },
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
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
    //查询
    queryData(){
      this.$axios.post("/check/api/CareOrderItem/selectAll",this.queryParams).then(r=>{
        this.tableData=r.data.t;
      })
    },
    //查询
    query(){
      if (this.queryParams.checkItemIds.length==0){
        this.queryParams.checkItemIds.push(0)
      }
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
      this.$axios.get("/system/api/dict/data/findByType/his_order_details_status").then(result=>{
        this.statusOptions=result.data.t;
      })
    },
    //status
    statusFormat(row){
      let v=row.status;
      return this.formatDict2(this.statusOptions,v);
    },
    // 选中某一行之后
    handleCurrentChange(row) {
      this.currentRow = row
      if (row !== null) {
        this.bottomLoading = true;
        this.fromData.regId=row.regId
        this.fromData.patientName=row.patientName
        this.fromData.checkItemName=row.itemName
        this.fromData.remark=row.remark
        this.fromData.cocId=row.itemId;
        this.fromData.checkItemId=row.itemRefId;
        this.fromData.price=row.price
        this.fromData.patientId=row.patientId
            // alert("regId:"+this.fromData.regId+"patientName:"+this.fromData.patientName+
            //     "checkItemName:"+this.fromData.checkItemName+
            //     "remark"+this.fromData.remark)
        this.showBottom = true
        this.bottomLoading = false
      }
    },
    // 开始检查
    handleStartCheck() {
      const tx = this
      tx.$confirm('是否确定开始检查?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // alert("regId:"+this.fromData.regId+"           patientName:"+this.fromData.patientName+
        //     "           itemName:"+this.fromData.checkitemName+
        //     "            remark:"+this.fromData.remark+"            cocId:"+
        //     tx.fromData.cocId+"           checkItemId:"+
        //     tx.fromData.checkItemId+"                   price:"+
        //     tx.fromData.price+"               patientId:"+
        //     tx.fromData.patientId)
        this.$axios.get("check/api/checkResult/addAll",{params:this.fromData}).then(res => {
          this.showBottom = false
          this.queryData()// 重新查询
          this.reset()
        }).catch(() => {
          tx.msgError('开始检查失败')
          tx.loading = false
        })
      }).catch(() => {
        tx.msgError('开始已取消')
        tx.loading = false
      })
    },
    resetQuery(){
      this.selectAllCheckItem()
      this.queryParams.regId=''
    },
    reset(){
      this.$router.push({path:'/check/checkresult',query:{regId:this.fromData.regId}})
    }
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
