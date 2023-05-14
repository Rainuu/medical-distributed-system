<template>
  <div>
    <!-- 工具栏————模糊查询 -->
    <div style="height: 70px; padding-top: 30px; background-color: whitesmoke">
      <el-form ref="queryForm" :model="queryParams" :inline="true" class="demo-form-inline">
        <el-form-item label="供应商名称" prop="providerId">
          <el-select v-model="queryParams.providerId" placeholder="请选择供应商名称" clearable style="width:240px">
            <el-option v-for="item in providerOptions" :key="item.providerId" :label="item.providerName" :value="item.providerId"/>
          </el-select>
        </el-form-item>&nbsp;
        <el-form-item label="申请人" prop="applyUserName">
          <el-input v-model="queryParams.applyUserName" placeholder="请输入申请人" clearable style="width: 240px"></el-input>
        </el-form-item>&nbsp;
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" plain @click="search">搜索</el-button>&nbsp;&nbsp;
          <el-button type="primary" icon="el-icon-refresh" plain @click="resert">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 工具栏———— -->
    <div style="float:left; clear:both; padding:25px;">
      <el-button type="success" icon="el-icon-edit" :disabled="single" @click="handleAuditPass">审核通过</el-button>
      <el-button type="danger" icon="el-icon-delete" :disabled="single" @click="handleAuditNoPass">审核不通过</el-button>
      <el-button type="success" icon="el-icon-edit" :disabled="single" @click="handleViewItem">查看详情</el-button>
    </div>
    <!-- 页面——数据表 & 分页 & 修改 & 删除（绑定行数据） -->
    <div>
      <el-table v-loading="loading" ref="multipleTable" :data="purchaseTableList" tooltip-effect="dark" style="width: 100%" border max-height="330px" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column prop="purchaseId" label="单据ID" align="center" width="200px"/>
        <el-table-column prop="providerId" label="供应商" align="center" :formatter="ProviderNameDict"/>
        <el-table-column prop="purchaseTradeTotalAmount" label="采购批发总额" align="center" width="110px">
          <template slot-scope="scope">
            <span>{{ scope.row.purchaseTradeTotalAmount | rounding }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" align="center" width="65px" :formatter="(row)=>this.dictFormat(row,row.status,'his_order_status')"/>
        <el-table-column prop="applyUserName" label="申请人" align="center" width="80px"/>
        <el-table-column prop="storageOptUser" label="入库操作人" align="center" width="100px"/>
        <el-table-column prop="storageOptTime" label="入库时间" align="center" width="155px"/>
        <el-table-column prop="auditMsg" label="审核信息" align="center" width="80px"/>
        <el-table-column prop="createTime" label="创建时间" align="center" width="155px"/>
      </el-table><br><br>
      <!-- 分页插件 -->
      <el-pagination v-show="total>0" :current-page="current" :page-size="size" :total="total"
                     :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </div>
    <!-- 单据详情的弹出层开始 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" center append-to-body>
      <el-table v-loading="loading" border :data="purchaseItemTableList">
        <el-table-column label="详情ID" width="180" align="center" prop="itemId" />
        <el-table-column label="单据ID" width="200" align="center" prop="purchaseId" />
        <el-table-column label="采购数量" align="center" prop="purchaseNumber" />
        <el-table-column label="批发价" prop="tradePrice" align="center">
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
        <el-table-column label="药品名称" align="center" prop="medicinesName" />
        <el-table-column label="单位" align="center" prop="conversion" />
        <el-table-column label="创建时间" align="center" prop="unit" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="open = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import qs from "qs";
export default {
  // 过滤器,保留两位小数
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
    // 模糊查询
    search(){
      this.current=1;
      this.initTable();
    },
    // 重置模糊
    resert(){
      this.queryParams = {};
      this.current=1;
      this.initTable();
    },
    // 审核通过【根据采购单号】
    handleAuditPass() {
      const purchaseId = this.ids[0];
      this.$confirm('是否确认审核通过单据ID为:' + purchaseId + '的数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // alert(purchaseId)
        this.$axios.post("stock/api/purchase/auditPass"+"/"+purchaseId).then(res => {
          this.$message({type: 'success', message: '审核通过成功!'});
          this.initTable();// 全查询
        }).catch(() => {
          this.$message({type: 'error', message: '审核通过失败!'});
        })
      }).catch(() => {
        this.$message({type: 'info', message: '审核通过已取消'});
      })
    },
    // 审核不通过
    handleAuditNoPass(row) {
      const purchaseId = this.ids[0];
      this.$prompt('请输入不通过的原因', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        this.$axios.post("stock/api/purchase/auditNoPass"+"/"+purchaseId+"/"+value).then(res => {
          this.$message({type: 'success', message: '审核不通过成功!'});
          this.initTable() // 全查询
        }).catch(() => {
          this.$message({type: 'error', message: '审核不通过失败!'});
        })
      }).catch(() => {
        this.$message({type: 'info', message: '审核不通过已取消'});
      })
    },
    // 查看详情
    handleViewItem() {
      this.open = true
      const purchaseId = this.ids[0]
      this.title = '单据编号为【' + purchaseId + '】的采购详情数据'
      this.loading = true
      this.$axios.get("stock/api/purchaseItem/getPurchaseItemById"+"/"+purchaseId).then(res => {
        this.loading = false
        this.purchaseItemTableList = res.data.t;
      })
    },
    // 查询————发出axios请求获取后端值，并将后端获取到的数据赋值给表格回填，挂载到页面，更改页面条数实现分页
    initTable(){
      this.loading = true // 打开遮罩
      this.$axios.post("stock/api/purchase/listPurchasePendingForPage"+"/"+this.current+"/"+this.size,this.queryParams).then(result=>{
        this.purchaseTableList=result.data.t.records;
        this.total=result.data.t.total;
        this.loading = false// 关闭遮罩
      })
    },
    // 获取批量数据————数据表格的多选择框选择时触发
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.purchaseId)
      // this.multipleSelection = selection;
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    // 分页————改变每页展示的数据数量，在size变化时触发
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val;
      this.initTable();
    },
    // 分页————改变当前页，点击页码时触发并跳转到对应页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val;
      this.initTable();
    },
  },
  data() {
    return {
      // 字典数组——接收字典表数据
      dictList: [],
      // 是否启用遮罩层
      loading: false,
      // 多选的id数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 是否打开查看详情
      open: false,
      // 弹出层的标题
      title: '',
      // 供应商的数据
      providerOptions: {},
      // 接收模糊查询
      queryParams: {
        providerId: undefined,
        applyUserName: undefined,
        status: undefined
      },
      // 查询的页面信息
      purchaseTableList: [],
      // 单据详情情数据
      purchaseItemTableList: [],
      // 分页参数
      total: 0,
      current: 1,
      size: 5,
    }
  },
  // 钩子函数————用于挂载，在vue页面创建后立即调用
  created() {
    this.initTable();
    this.getDict();
    this.getProviderOption();
  }
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
}
</style>
