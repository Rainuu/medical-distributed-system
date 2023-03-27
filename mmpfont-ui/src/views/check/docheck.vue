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
        <el-form-item label="挂号单号" prop="regId">
          <el-input
              v-model="queryParams.regId"
              placeholder="请输入挂号单号"
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
          prop="status"
          label="状态">
      </el-table-column>
    </el-table>

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

      formInline: {
        user: '',
        region: ''
      },
      form: {
        type: []
      },
      //表格数据
      tableData: [],
      //系统状态
      statusOptions:[],

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
      let v=row.resultStatus;
      return this.formatDict3(this.statusOptions,v);
    },
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
