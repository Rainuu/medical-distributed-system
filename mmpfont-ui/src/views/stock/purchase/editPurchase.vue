<template>
  <div class="app-container">
    <!-- 工具栏按钮 -->
    <el-card class="box-card">
      <div style="text-align:right;">
        <el-button type="primary" icon="el-icon-plus" :disabled="isSubmit" @click="handleAddMedicines">添加药品</el-button>
        <el-button type="success" icon="el-icon-s-operation" :disabled="isSubmit" @click="handleBatchSet">批量设置</el-button>
        <el-button type="warning" icon="el-icon-check" :disabled="isSubmit" @click="handleSubmit">暂存</el-button>
        <el-button type="danger" icon="el-icon-finished" :disabled="isSubmit" @click="handleSubmitAndAudit">提交审核</el-button>
      </div>
    </el-card>
    <!-- 工具栏2 -->
    <el-card class="box-card">
      <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="100px">
        <el-form-item label="单据号" prop="purchaseId">
          <el-input v-model="form.purchaseId" placeholder="请输入单据号" :disabled="true" style="width:220px"/>
        </el-form-item>&nbsp;&nbsp;&nbsp;
        <el-form-item label="供应商" prop="providerId">
          <el-select v-model="form.providerId" placeholder="请选择供应商" style="width:220px">
            <el-option v-for="item in providerOptions" :key="item.providerId" :label="item.providerName" :value="item.providerId"/>
          </el-select>
        </el-form-item>&nbsp;&nbsp;&nbsp;
        <el-form-item label="总批发额" prop="purchaseTradeTotalAmount">
          <el-input v-model="form.purchaseTradeTotalAmount" placeholder="请输入总批发额" :disabled="true" style="width:220px"/>
        </el-form-item>&nbsp;&nbsp;&nbsp;
      </el-form>
    </el-card>
    <!-- 选择中的详情数据表格开始 -->
    <el-card class="box-card">
      <el-table :data="purchaseItemList" class="tb-edit" style="width:100%" border highlight-current-row>
        <el-table-column label="药品ID" align="center" width="70px" prop="medicinesId" />
        <el-table-column label="药品名称" align="center" width="120px" prop="medicinesName" />
        <el-table-column label="规则" align="center" width="60px" prop="conversion">
          <template slot-scope="scope">
            <span> {{ scope.row.conversion }}{{ scope.row.unit }}</span>
          </template>
        </el-table-column>
        <el-table-column label="生产厂家" align="center" width="200px" prop="producterId" :formatter="ProducterNameDict"/>
        <el-table-column label="数量" align="center" width="210px" prop="purchaseNumber">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.purchaseNumber" :step="1"/>
          </template>
        </el-table-column>
        <el-table-column label="单位" align="center" width="60px" prop="unit" />
        <el-table-column label="批发价" align="center" width="210px" prop="tradePrice">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.tradePrice" :step="0.1" :precision="2" size="small"/>
          </template>
        </el-table-column>
        <el-table-column label="批发额" align="center" width="80px" prop="tradeTotalAmount">
          <template slot-scope="scope">
            <span>{{ scope.row.tradeTotalAmount|rounding }}</span>
          </template>
        </el-table-column>
        <el-table-column label="批次号" align="center" width="180px" prop="batchNumber">
          <template slot-scope="scope">
            <el-input v-model="scope.row.batchNumber" size="small"/>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" width="180px" prop="remark">
          <template slot-scope="scope">
            <el-input v-model="scope.row.remark" size="small"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="100px">
          <template slot-scope="scope">
            <el-button size="small" type="danger" :disabled="isSubmit" @click="handleDelete(scope.$index,scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 弹出层————药品列表 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" center append-to-body>
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
        <el-form-item label="关键字" prop="keywords">
          <el-input v-model="queryParams.keywords" placeholder="请输入关键字" clearable style="width:180px"/>
        </el-form-item>
        <el-form-item label="药品类型" prop="medicinesType">
          <el-select v-model="queryParams.medicinesType" clearable placeholder="药品类型" style="width: 200px">
            <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_medicines_type'})"
                       :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="处方类型" prop="prescriptionType">
          <el-select v-model="queryParams.prescriptionType" clearable placeholder="处方类型" style="width: 190px">
            <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_prescription_type'})"
                       :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table ref="medicinesTableList" v-loading="loading" border :data="medicinesTableList" @selection-change="handleSelectionChnage">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="药品ID" align="center" prop="medicinesId" />
        <el-table-column label="药品名称" align="center" prop="medicinesName" />
        <el-table-column label="药品编号" align="center" prop="medicinesNumber" />
        <el-table-column label="生产厂家" width="280px" align="center" prop="producterId" :formatter="ProducterNameDict"/>
        <el-table-column label="药品类型" align="center" prop="medicinesType" :formatter="(row)=>this.dictFormat(row,row.medicinesType,'his_medicines_type')"/>
        <el-table-column label="处方类型" align="center" prop="prescriptionType" :formatter="(row)=>this.dictFormat(row,row.prescriptionType,'his_prescription_type')"/>
        <el-table-column label="关键字" align="center" prop="keywords" />
      </el-table>
      <!-- 分页控件 -->
      <el-pagination v-show="total>0" :current-page="current" :page-size="size" :total="total"
                     :page-sizes="[5, 10, 20, 30]" layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </el-dialog>
    <!-- 批量设置的弹出层开始 -->
    <el-dialog :title="title" :visible.sync="batchSetOpen" width="500px" center append-to-body>
      <el-form ref="batchSetForm" :model="batchSetForm" label-width="100px">
        <el-form-item label="数量" prop="purchaseNumber">
          <el-input-number v-model="batchSetForm.purchaseNumber" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="批发价格" prop="tradePrice">
          <el-input-number v-model="batchSetForm.tradePrice" :precision="2" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="批次号" prop="batchNumber">
          <el-input v-model="batchSetForm.batchNumber" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="batchSetForm.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleBatchSetSubmit">确定</el-button>
        <el-button @click="batchSetOpen = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  filters: {
    rounding(value) {
      return value.toFixed(2)
    }
  },
  watch: {
    // 监听purchaseItemList里面的数据变化，更新总批发额
    purchaseItemList: {
      handler: function() {
        this.form.purchaseTradeTotalAmount = 0.00
        this.purchaseItemList.filter(item => {
          item.tradeTotalAmount = item.tradePrice * item.purchaseNumber
          this.form.purchaseTradeTotalAmount += item.tradeTotalAmount
        })
      },
      deep: true
    }
  },
  deactivated() {
    //解决vue组件 前进刷新，后退不刷新，
    //$destroy完全销毁一个实例，清理它与其它实例的连接，解绑它的全部指令及事件监听器。
    this.$destroy();
  },
  methods: {
    //----------------------添加药品-------------------------------------
    // 打开选择药品的弹出层
    handleAddMedicines() {
      this.open = true
      this.title = '选择要添加的药品信息'
      this.getMedicinesList()
    },
    // 使用字典表转换数据————调用formatDict脚本对传入参数（行数据、对应行、字典列）处理，并返回
    dictFormat(row, column, dictType){
      return this.formatDict( this.dictList,column, dictType)
    },
    // 获取字典表数据————发请求给后端，由后端调用接口、跨域并获取字典表数据传给前端处理数据
    getDict() {
      this.$axios.get('/stock/api/feign/status').then(res => {
        this.dictList = res.data.t
      })
    },
    // 获取生产厂家表字段
    getProducterNameOption(){
      this.$axios.get("/stock/api/producter/getAllDict").then(r=>{
        this.producterOptions=r.data.t;
      })
    },
    // 循环判断，将Id转换为name,翻译生产厂家
    ProducterNameDict(row){
      let name = '';
      for (let i = 0; i < this.producterOptions.length; i++) {
        if (row.producterId == this.producterOptions[i].producerId){
          name = this.producterOptions[i].producerName;
          break;
        }
      }
      return name;
    },
    // 字典处理————获取生产厂家表字段
    getProviderOption(){
      this.$axios.get("/stock/api/provider/getAllDict").then(r=>{
        this.providerOptions=r.data.t;
      })
    },
    // 处理字典姓名————循环判断，将Id转换为name
    ProviderNameDict(row){
      let a = '';
      for (let i = 0; i < this.providerOptions.length; i++) {
        if (row.providerId == this.providerOptions[i].providerId){
          a = this.providerOptions[i].providerName;
          break;
        }
      }
      return a;
    },
    // 条件查询
    handleQuery() {
      this.current=1;
      this.getMedicinesList()
    },
    // 重置查询条件
    resetQuery() {
      this.queryParams={};
      this.current=1;
      this.getMedicinesList();
    },
    // 弹出层————显示药品数据列表
    getMedicinesList() {
      this.loading = true
      this.$axios.post("/stock/api/medicinal/getAll"+"/"+this.current+"/"+this.size,this.queryParams).then(res => {
        this.medicinesTableList = res.data.t.records;
        this.total = res.data.t.total;
        this.loading = false
        this.$nextTick(() => {
          // 反选之前有的
          this.medicinesTableList.filter(r1 => {
            this.purchaseItemList.filter(r2 => {
              if (parseInt(r1.medicinesId) === parseInt(r2.medicinesId)) {
                this.$refs.medicinesTableList.toggleRowSelection(r1, true)
              }
            })
          })
        })
      })
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChnage(selection) {
      // console.log(selection)
      this.selectMedicines = selection
      this.selectMedicines.filter(m1 => {
        let flag = false
        // 判断purchaseItemList里面有没有已选择的ID
        this.purchaseItemList.filter(purch => {
          if (m1.medicinesId === purch.medicinesId) {
            flag = true
          }
        })
        // 如果没有，就加入purchaseItemList
        if (!flag) {
          // 放到purchaseItemList对象是selection深度clone
          this.purchaseItemList.push(JSON.parse(JSON.stringify(m1)))
        }
      })
      // 剔除里面不用的属性，添加需要的属性
      this.purchaseItemList.filter(m => {
        this.$delete(m, 'status')
        this.$delete(m, 'medicinesStockNum')
        this.$delete(m, 'medicinesStockDangerNum')
        this.$delete(m, 'createTime')
        this.$delete(m, 'updateTime')
        this.$delete(m, 'createBy')
        this.$delete(m, 'updateBy')

        this.$set(m, 'purchaseNumber', m.purchaseNumber ? m.purchaseNumber : 1)
        this.$set(m, 'tradePrice', m.tradePrice ? m.tradePrice : 0.00)
        this.$set(m, 'tradeTotalAmount', m.tradeTotalAmount ? m.tradeTotalAmount : 0.00)
        this.$set(m, 'batchNumber', m.batchNumber ? m.batchNumber : '')
        this.$set(m, 'remark', m.remark ? m.remark : '')
      })
    },
    // ----------------------批量设置----------------------------------------------
    // 打开批量设置的弹出层
    handleBatchSet() {
      this.batchSetOpen = true
      this.title = '批量设置'
    },
    // 批量设置的保存
    handleBatchSetSubmit() {
      this.purchaseItemList.filter(item => {
        item.purchaseNumber = this.batchSetForm.purchaseNumber
        item.tradePrice = this.batchSetForm.tradePrice
        item.batchNumber = this.batchSetForm.batchNumber
        item.remark = this.batchSetForm.remark
      })
      this.batchSetOpen = false
    },
    // ---------------------暂存---------------------------------------
    handleSubmit() {
      if (this.purchaseItemList.length > 0) {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            // 组装要提交到后台的数据
            const purchaseAllVo = { 'purchaseDto': this.form, 'purchaseItemDtos': this.purchaseItemList }
            // console.log(JSON.stringify(purchaseAllVo))
            this.$axios.post("/stock/api/purchase/editPurchase", purchaseAllVo).then(res => {
              this.$message({type: 'success', message: '暂存成功!'});
            }).catch(() => {
              this.$message({type: 'error', message: '暂存失败!'});
            })
          }
        })
      } else {
        this.$message({type: 'info', message: '药品数据项不能为空'});
      }
    },
    //-------------------------保存并提交审核----------------------------------------
    handleSubmitAndAudit() {
      if (this.purchaseItemList.length > 0) {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            // 组装要提交到后台的数据
            const purchaseAllVo = { 'purchaseDto': this.form, 'purchaseItemDtos': this.purchaseItemList }
            this.$axios.post("/stock/api/purchase/editPurchaseToAudit", purchaseAllVo).then(res => {
              this.$message({type: 'success', message: '提交审核成功!'});
              // 上面的四个按钮不能按了
              this.isSubmit = true
            }).catch(() => {
              this.$message({type: 'error', message: '提交审核失败!'});
            })
          }
        })
      } else {
        this.$message({type: 'info', message: '药品数据项不能为空'});
      }
    },
    // 删除详情数据
    handleDelete(index, row) {
      this.purchaseItemList.splice(index, 1)
    },
    // -----------------------------展示的药品页面---------------------------------------
    // 根据单据号去查询单据信息及详情
    queryPurchaseAndItemByPurchaseId(){
      // 接收路由传参————因为是修改页面
      const purchaseId = this.$route.params && this.$route.params.purchaseId
      this.$axios.post("stock/api/purchaseItem/getAllById/"+purchaseId).then(res => {
        this.purchaseItemList=res.data.t;
        this.form.purchaseId=purchaseId;
        this.form.providerId=res.data.t[0].providerId;
        // alert(JSON.stringify(this.form));
        // alert(res.data.t[0].status);

        // 如果当前单据的状态为1或者4那么不能进行其它操作
        // this.isSubmit = true

        if (res.data.t[0].status === '1' || res.data.t[0].status === '4') {
          this.isSubmit = false
        } else if(res.data.t[0].status === '2' || res.data.t[0].status === '3' || res.data.t[0].status === '5' || res.data.t[0].status === '6'){
          this.isSubmit = true
        }
      })
    },
    // 分页————改变每页展示的数据数量，在size变化时触发
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val
      this.getMedicinesList()
    },
    // 分页————改变当前页，点击页码时触发并跳转到对应页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val
      this.getMedicinesList()
    }
  },
  created() {
    // 根据单据号去查询单据信息及详情
    this.queryPurchaseAndItemByPurchaseId();
    // 字典表数据
    this.getDict();
    // 查询生产厂家表数据
    this.getProducterNameOption();
    // 查询供应商表数据
    this.getProviderOption();
  },
  // 数据
  data() {
    return {
      // 加载情节
      loading: false,
      // 是否打开药品的弹出层
      open: false,
      // 弹出层的标题
      title: '',
      // 是否已提交
      isSubmit: false,
      // 供应商的下拉列表数据
      providerOptions: [],
      // 生产厂家的列表数据
      producterOptions: [],
      // 字典数组——接收字典表数据
      dictList: [],
      // 主表单的数据
      form: {
        purchaseId: undefined,
        providerId: undefined,
        purchaseTradeTotalAmount: undefined
      },
      // 声明数据详情表格的数据
      purchaseItemList: [],
      // 药品表格数据
      medicinesTableList: [],
      // 药品弹出层的查询条件
      queryParams: {
        medicinesType: undefined,
        prescriptionType: undefined,
        keywords: undefined
      },
      // 弹出层选中的药品
      selectMedicines: [],
      // 是否显示批量设置的弹出层
      batchSetOpen: false,
      // 批量设置的数据绑定
      batchSetForm: {
        purchaseNumber: 0,
        tradePrice: 0.00,
        batchNumber: '',
        remark: ''
      },
      // 校验规则
      rules: {
        providerId: [
          { required: true, message: '请选择供应商', trigger: 'blur' }
        ]
      },
      // 分页参数
      total: 0,
      current: 1,
      size: 5,
    }
  },
}
</script>

<style scoped>
  .box-card{
    padding: 0px;
    margin: 8px 0 0 0;
  }
</style>
