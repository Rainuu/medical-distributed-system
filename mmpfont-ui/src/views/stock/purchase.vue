<template>
  <div>
    <!-- 工具栏————模糊查询 -->
    <div style="height: 70px; padding-top: 30px; background-color: whitesmoke">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="供应商名称" prop="providerId">
          <el-select v-model="searchForm.providerId" placeholder="请选择供应商名称" clearable style="width:220px">
            <el-option v-for="item in providerOptions" :key="item.providerId" :label="item.providerName" :value="item.providerId"/>
          </el-select>
        </el-form-item>&nbsp;
        <el-form-item label="申请人" prop="applyUserName">
          <el-input v-model="searchForm.applyUserName" placeholder="请输入申请人" clearable style="width: 220px"></el-input>
        </el-form-item>&nbsp;
        <el-form-item label="单据状态" prop="status">
          <el-select v-model="searchForm.status" placeholder="可用状态" clearable style="width: 200px">
            <el-option v-for="dict in this.dictList.filter((n)=>{ return n.dictType==='his_order_status'})"
                       :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" clearable/>
          </el-select>
        </el-form-item>&nbsp;
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" plain @click="search">搜索</el-button>&nbsp;&nbsp;
          <el-button type="primary" icon="el-icon-refresh" plain @click="resert">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 工具栏————新增 & 删除 -->
    <div style="float:left; clear:both; padding:15px;">
      <el-button type="primary" icon="el-icon-plus" plain @click="ToNewPurchase">新增采购</el-button>
      <el-button type="success" icon="el-icon-edit" plain :disabled="single" @click="handleDoAudit">提交审核</el-button>
      <el-button type="danger" icon="el-icon-delete" :disabled="single"  @click="handleDoInvalid">作废</el-button>
      <el-button type="success" icon="el-icon-edit" :disabled="single" @click="handleDoInventory">提交入库</el-button>
    </div>
    <!-- 页面——数据表 & 分页 & 修改 & 删除（绑定行数据） -->
    <div>
      <el-table v-loading="loading" ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" border max-height="330px" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="单据ID" align="center" width="200" prop="purchaseId">
          <template slot-scope="scope">
            <router-link :to="'/stock/purchase/editPurchase/'+scope.row.purchaseId" class="link-type">
              <span>{{ scope.row.purchaseId }}</span>
            </router-link>
          </template>
        </el-table-column>
        <el-table-column prop="providerId" label="供应商" align="center" :formatter="ProviderNameDict"/>
        <el-table-column label="采购批发总额" align="center" prop="purchaseTradeTotalAmount" width="110px">
          <template slot-scope="scope">
            <span>{{ scope.row.purchaseTradeTotalAmount | rounding }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" align="center" width="80px" :formatter="(row)=>this.dictFormat(row,row.status,'his_order_status')"/>
        <el-table-column prop="applyUserName" label="申请人" align="center" width="80px"/>
        <el-table-column prop="storageOptUser" label="入库操作人" align="center" width="100px"/>
        <el-table-column prop="storageOptTime" label="入库时间" align="center" width="160px"/>
        <el-table-column prop="auditMsg" label="审核信息" align="center" width="80px"/>
        <el-table-column prop="createTime" label="创建时间" align="center" width="160px"/>
      </el-table><br><br>
      <!-- 分页插件 -->
      <el-pagination v-show="total>0" :current-page="current" :page-size="size" :total="total"
                     :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </div>
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
  methods: {
    // 字典处理————获取生产厂家表字段
    getProviderOption(){
      this.$axios.get("/stock/api/provider/getAllDict").then(r=>{
        this.providerOptions=r.data.t;
      })
    },
    // 处理字典姓名————循环判断，将Id转换为name
    ProviderNameDict(row){
      var a = '';
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
      this.searchForm = {};
      this.current=1;
      this.initTable();
    },
    // 新增采购————跳转到新增采购的路由页面
    ToNewPurchase() {
      this.$router.replace('/stock/purchase/newPurchase')
    },
    // 提交审核
    handleDoAudit() {
      const purchaseId = this.ids[0]
      this.$confirm('是否确认提交单据ID为:' + purchaseId + '的数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.rowStatus == 1 || this.rowStatus == 4){
          this.$axios.post("/stock/api/purchase/doAudit/" + purchaseId).then(res => {
            this.$message({type: 'success', message: '提交审核成功!'});
            this.initTable();
          })
        } else if(this.rowStatus == 2 || this.rowStatus == 3 || this.rowStatus == 5){
          this.$message({type: 'error', message: '提交审核失败! '});
        } else{
          this.$message({type: 'info', message: '已取消删除'});
        }
      })
    },
    // 作废【根据采购单号】
    handleDoInvalid(row) {
      const purchaseId = this.ids[0]
      this.$confirm('是否确认作废单据ID为:' + purchaseId + '的数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.rowStatus == 1 || this.rowStatus == 4){
          this.$axios.post("/stock/api/purchase/doInvalid/" + purchaseId).then(res => {
            this.$message({type: 'success', message: '作废成功!'});
            this.initTable();
          })
        } else if(this.rowStatus == 2 || this.rowStatus == 3 || this.rowStatus == 5){
          this.$message({type: 'error', message: '作废失败!'});
        } else {
          this.$message({type: 'info', message: '作废删除'});
        }
      })
    },
    // 执行入库
    handleDoInventory(row) {
      const purchaseId = this.ids[0]
      // alert(purchaseId)
      this.$confirm('是否确认提交入库单据ID为:' + purchaseId + '的数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.rowStatus == 3){
          this.$axios.post("/stock/api/purchase/doInventory/" + purchaseId).then(res => {
            this.$message({type: 'success', message: '入库成功!'});
            this.initTable();
          })
        } else if (this.rowStatus == 1 || this.rowStatus == 2 || this.rowStatus == 4 || this.rowStatus == 5 || this.rowStatus == 6){
            this.$message({type: 'error', message: '入库失败!'});
        }else {
          this.$message({type: 'info', message: '入库删除'});
        }
      })
    },
    // 查询————发出axios请求获取后端值，并将后端获取到的数据赋值给表格回填，挂载到页面，更改页面条数实现分页
    initTable(){
      this.loading = true // 打开遮罩
      this.$axios.post("stock/api/purchase/getAll"+"/"+this.current+"/"+this.size,this.searchForm).then(result=>{
        this.tableData=result.data.t.records;
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
      // alert(JSON.stringify(selection))
      this.rowStatus=selection.map(item => item.status)
      // alert(JSON.stringify(this.rowStatus))
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
      // 获取行数据
      rowStatus: [],
      // 字典数组——接收字典表数据
      dictList: [],
      // 多选的id数组
      ids: [],
      // 供应商的数据
      providerOptions: {},
      // 接收模糊查询
      searchForm: {
        providerId: undefined,
        applyUserName: undefined,
        status: undefined
      },
      // 查询的页面信息
      tableData: [],
      // 是否启用遮罩层
      loading: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
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
