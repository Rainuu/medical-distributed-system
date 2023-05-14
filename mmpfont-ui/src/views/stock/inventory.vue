<template>
  <div class="app-container">
    <el-tabs v-model="activeName" :stretch="true" @tab-click="handleClick">
      <!-- 药品总库存页面 -->
      <el-tab-pane label="药品总库存" name="medicinesTab">
        <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="85px" style="margin: 20px 0;">
          <div style="float: left;clear: both;">
            <el-form-item label="药品名称" prop="medicinesName">
              <el-input v-model="queryParams.medicinesName" placeholder="请输入药品名称" clearable style="width:160px"/>
            </el-form-item>
            <el-form-item label="关键字" prop="keywords">
              <el-input v-model="queryParams.keywords" placeholder="请输入关键字" clearable style="width:200px"/>
            </el-form-item>
            <el-form-item label="药品类型" prop="medicinesType">
              <el-select v-model="queryParams.medicinesType" clearable placeholder="药品类型" style="width: 180px">
                <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_medicines_type'})"
                           :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
            <el-form-item label="生产厂家" prop="producterId"> <!-- 遍历的是item内容 -->
              <el-select v-model="queryParams.producerId" clearable placeholder="生产厂家" style="width: 220px">
                <el-option v-for="item in producterNameOption"
                           :key="item.producerId" :label="item.producerName" :value="item.producerId"/>
              </el-select>
            </el-form-item>
          </div>
          <div style="float: left;clear: both;">
            <el-form-item label="处方类型" prop="prescriptionType">
              <el-select v-model="queryParams.prescriptionType" clearable placeholder="处方类型" style="width: 160px">
                <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_prescription_type'})"
                           :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
            <el-form-item label="创建时间">
              <el-date-picker type="daterange" v-model="queryParams.dateRange" style="width:200px" clearable
                              value-format="yyyy-MM-dd" range-separator="-" start-placeholde="开始日期" end-placeholde="结束日期"/>
            </el-form-item>&nbsp;&nbsp;
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>&nbsp;&nbsp;
              <el-button type="primary" icon="el-icon-refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </div>
        </el-form>
        <el-table v-loading="loading" border :data="medicinesTableList" style="margin: 20px 0;">
          <el-table-column label="药品ID" align="center" prop="medicinesId" />
          <el-table-column label="药品名称" align="center" prop="medicinesName" />
          <el-table-column label="药品编号" align="center" prop="medicinesNumber" />
          <el-table-column label="生产厂家" width="280px" align="center" prop="producterId" :formatter="ProducterNameDict"/>
          <el-table-column label="药品类型" align="center" prop="medicinesType" :formatter="(row)=>this.dictFormat(row,row.medicinesType,'his_medicines_type')"/>
          <el-table-column label="处方类型" align="center" prop="prescriptionType" :formatter="(row)=>this.dictFormat(row,row.prescriptionType,'his_prescription_type')"/>
          <el-table-column label="关键字" align="center" prop="keywords" />
          <el-table-column label="库存量" align="center" prop="medicinesStockNum" />
          <el-table-column label="预警值" align="center" prop="medicinesStockDangerNum" />
          <el-table-column label="状态" prop="status" align="center" :formatter="(row)=>this.dictFormat(row,row.status,'sys_normal_disable')"/>
        </el-table>
        <el-pagination v-show="total>0" :current-page="current1" :page-size="size1" :total="total"
                       :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                       @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
      </el-tab-pane>
      <!--批次库存及价格页面-->
      <el-tab-pane label="批次库存及价格" name="inventoryLogTab">
        <el-form ref="queryLogForm" :model="queryLogParams" :inline="true" label-width="85px" style="margin: 20px 0;">
          <div style="float: left;clear: both;">
            <el-form-item label="采购单据号" prop="purchaseId">
              <el-input v-model="queryLogParams.purchaseId" placeholder="采购单据号" clearable style="width:180px"/>
            </el-form-item>
            <el-form-item label="药品名称" prop="medicinesName">
              <el-input v-model="queryLogParams.medicinesName" placeholder="请输入药品名称" clearable style="width:190px"/>
            </el-form-item>
            <el-form-item label="药品类型" prop="medicinesType">
              <el-select v-model="queryLogParams.medicinesType" clearable placeholder="药品类型" style="width: 170px">
                <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_medicines_type'})"
                           :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
            <el-form-item label="生产厂家" prop="producterId"> <!-- 遍历的是item内容 -->
              <el-select v-model="queryLogParams.producterId" clearable placeholder="生产厂家" style="width: 220px">
                <el-option v-for="item in producterNameOption"
                           :key="item.producerId" :label="item.producerName" :value="item.producerId"/>
              </el-select>
            </el-form-item>
          </div>
          <div style="float: left;clear: both;">
            <el-form-item label="处方类型" prop="prescriptionType">
              <el-select v-model="queryLogParams.prescriptionType" clearable placeholder="处方类型" style="width: 180px">
                <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_prescription_type'})"
                           :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
            <el-form-item label="创建时间">
              <el-date-picker type="daterange" v-model="queryLogParams.dateRange" style="width:190px" clearable
                              value-format="yyyy-MM-dd" range-separator="-" start-placeholde="开始日期" end-placeholde="结束日期"/>
            </el-form-item>&nbsp;&nbsp;&nbsp;&nbsp;
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>&nbsp;&nbsp;
              <el-button type="primary" icon="el-icon-refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </div>
        </el-form>
        <el-table v-loading="loading" border :data="inventoryLogTableList" style="margin: 20px 0;">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" style="padding-left: 120px" inline class="demo-table-expand">
                <el-form-item label="入库号">
                  <span>{{ props.row.inventoryLogId }}</span>
                </el-form-item>
                <el-form-item label="单据号">
                  <span>{{ props.row.purchaseId }}</span></el-form-item>
                <el-form-item label="规格">
                  <span>{{ props.row.conversion }}{{ props.row.unit }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="药品ID" align="center" prop="medicinesId"/>
          <el-table-column label="药品名称" align="center" prop="medicinesName" />
          <el-table-column label="采购量" align="center" prop="inventoryLogNum" />
          <el-table-column label="批发价" align="center" prop="tradePrice">
            <template slot-scope="scope">
              <span>{{ scope.row.tradePrice|rounding }}</span>
            </template>
          </el-table-column>
          <el-table-column label="批发额" align="center" prop="tradeTotalAmount">
            <template slot-scope="scope">
              <span>{{ scope.row.tradeTotalAmount|rounding }}</span>
            </template>
          </el-table-column>
          <el-table-column label="批次号" align="center" prop="batchNumber" />
          <el-table-column label="生产厂家" width="260px" align="center" prop="producterId" :formatter="ProducterNameDict"/>
          <el-table-column label="药品类型" align="center" prop="medicinesType" width="80px" :formatter="(row)=>this.dictFormat(row,row.medicinesType,'his_medicines_type')"/>
          <el-table-column label="处方类型" align="center" prop="prescriptionType" width="80px" :formatter="(row)=>this.dictFormat(row,row.prescriptionType,'his_prescription_type')"/>
          <el-table-column label="入库时间" width="155px" align="center" prop="createTime" />
        </el-table>
        <el-pagination v-show="totalLog>0" :current-page="current2" :page-size="size2" :total="totalLog"
                       :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                       @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  filters: {
    rounding(value) {
      return value.toFixed(2)
    }
  },
  // 刷新页面
  deactivated() {
    //解决vue组件 前进刷新，后退不刷新，
    //$destroy完全销毁一个实例，清理它与其它实例的连接，解绑它的全部指令及事件监听器。
    this.$destroy();
  },
  methods: {
    // 切换按钮————点击tab调用的事件
    handleClick(tab, event) {
      console.log(this.activeName)
      if (this.activeName === 'medicinesTab') {
        this.getMedicinesList()
      } else if (this.activeName === 'inventoryLogTab') {
        this.getInventoryLogList()
      }
    },
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
    // 模糊————药品条件查询
    handleQuery() {
      if (this.activeName === 'medicinesTab') {
        this.getMedicinesList()
      } else if (this.activeName === 'inventoryLogTab') {
        this.getInventoryLogList()
      }
    },
    // 模糊————药品重置查询条件
    resetQuery() {
      if (this.activeName === 'medicinesTab') {
        this.queryParams={};
        this.getMedicinesList();
        this.current1=1;
      } else if (this.activeName === 'inventoryLogTab') {
        this.queryLogParams={};
        this.getInventoryLogList();
        this.current2=1;
      }
    },
    // 查询药品表格数据
    getMedicinesList() {
      this.loading = true // 打开遮罩
      this.$axios.post("/stock/api/medicinal/getAll"+"/"+this.current1+"/"+this.size1,this.queryParams).then(res => {
        this.medicinesTableList = res.data.t.records;
        this.total = res.data.t.total;
        this.loading = false; // 关闭遮罩
      })
    },
    // 批次库存及价格
    getInventoryLogList() {
      this.loading = true // 打开遮罩
      this.$axios.post("/stock/api/InventoryLog/getAll"+"/"+this.current2+"/"+this.size2,this.addDateRange(this.queryLogParams, this.dateRange)).then(res => {
        this.inventoryLogTableList = res.data.t.records;
        this.totalLog = res.data.t.total;
        this.loading = false// 关闭遮罩
      })
    },
    // 把日期范围构造成beginTime和endTime
    addDateRange(params, dateRange) {
      var search = params
      search.beginTime = ''
      search.endTime = ''
      if (dateRange != null && dateRange !== '' && dateRange !== undefined) {
        search.beginTime = this.dateRange[0]
        search.endTime = this.dateRange[1]
      }
      return search
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      if (this.activeName === 'medicinesTab') {
        console.log(`每页 ${val} 条`);
        this.size1 = val;
        this.getMedicinesList();
      } else if (this.activeName === 'inventoryLogTab') {
        console.log(`每页 ${val} 条`);
        this.size2 = val;
        this.getInventoryLogList();
      }
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      if (this.activeName === 'medicinesTab') {
        console.log(`当前页: ${val}`);
        this.current1 = val;
        this.getMedicinesList()
      } else if (this.activeName === 'inventoryLogTab') {
        console.log(`当前页: ${val}`);
        this.current2 = val;
        this.getInventoryLogList()
      }
    },
  },
  // 勾子
  created() {
    // 查询表格数据
    this.getMedicinesList();
    // 字典表处理
    this.getDict();
    // 获取厂家名,查询所有可用的生产厂家
    this.getProducterNameOption();
  },
  // 定义页面数据
  data() {
    return {
      // 字典数组——接收字典表数据
      dictList: [],
      // 模糊查询时，使用id获取name
      producterNameOption: {},
      // 当前激活的tab
      activeName: 'medicinesTab',
      // 是否启用遮罩层
      loading: false,
      // 药品分页,数据总条数
      current1: 1,
      size1: 5,
      total: 0,
      // 入库分页,数据总条数
      current2: 1,
      size2: 5,
      totalLog: 0,
      // 字典表格数据
      medicinesTableList: [],
      // 字典表格数据
      inventoryLogTableList: [],
      // 状态数据字典
      statusOptions: [],
      // 药品类型数据字典
      medicinesTypeOptions: [],
      // 生产厂家数据
      producterOptions: [],
      // 处方类型数字典
      prescriptionTypeOptions: [],
      // 药品查询参数
      queryParams: {
        medicinesName: undefined,
        medicinesType: undefined,
        prescriptionType: undefined,
        producterId: undefined,
        dateRange: []
      },
      // 入库详情查询参数
      queryLogParams: {
        purchaseId: undefined,
        medicinesName: undefined,
        medicinesType: undefined,
        prescriptionType: undefined,
        producterId: undefined,
        dateRange: []
      },
    }
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
