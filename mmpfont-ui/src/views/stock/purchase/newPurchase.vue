<template>
  <div>
    <!-- 工具栏————按钮 -->
    <el-card class="box-card">
      <div style="text-align:right;">
        <el-button type="primary" icon="el-icon-plus" :disabled="isSubmit" size="small" @click="handleAddMedicines">添加药品</el-button>
        <el-button type="success" icon="el-icon-s-operation" :disabled="isSubmit" size="small" @click="handleBatchSet">批量设置</el-button>
        <el-button type="warning" icon="el-icon-check" :disabled="isSubmit" size="small" @click="handleSubmit">暂存</el-button>
        <el-button type="danger" icon="el-icon-finished" :disabled="isSubmit" size="small" @click="handleSubmitAndAudit">提交审核</el-button>
      </div>
    </el-card>
    <!-- 文本框 -->
    <el-card class="box-card">
      <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="68px">
        <el-row :gutter="10">
          <el-col :span="1.5">
            <el-form-item label="单据号" prop="purchaseId">
              <el-input v-model="form.purchaseId" placeholder="请输入单据号" :disabled="true" size="small" style="width:220px"/>
            </el-form-item>
          </el-col>
          <el-col :span="1.5">
            <el-form-item label="供应商" prop="providerId">
              <el-select v-model="form.providerId" placeholder="请选择供应商" size="small" style="width:220px">
                <el-option v-for="d in providerOptions" :key="d.providerId" :label="d.providerName" :value="d.providerId"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="1.5">
            <el-form-item label="总批发额" prop="purchaseTradeTotalAmount">
              <el-input v-model="form.purchaseTradeTotalAmount" placeholder="请输入总批发额" :disabled="true" size="small" style="width:220px"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <!-- 页面表格 -->
    <el-card class="box-card">
      <el-table :data="purchaseItemList" class="tb-edit" style="width:100%" border highlight-current-row>
        <el-table-column label="药品ID" align="center" width="80" prop="medicinesId" />
        <el-table-column label="药品名称" align="center" width="150" prop="medicinesName" />
        <el-table-column label="规则" align="center" width="80" prop="conversion">
          <template slot-scope="scope">
            <span> {{ scope.row.conversion }}{{ scope.row.unit }}</span>
          </template>
        </el-table-column>
        <el-table-column label="生产厂家" align="center" width="220" prop="producterId" :formatter="producterFormatter" />
        <el-table-column label="数量" align="center" width="160" prop="purchaseNumber">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.purchaseNumber" :step="1" size="small"/>
          </template>
        </el-table-column>
        <el-table-column label="单位" align="center" width="80" prop="unit" />
        <el-table-column label="批发价" align="center" width="160" prop="tradePrice">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.tradePrice" :step="0.1" :precision="2" size="small"/>
          </template>
        </el-table-column>
        <el-table-column label="批发额" align="center" width="80" prop="tradeTotalAmount">
          <template slot-scope="scope">
            <span>{{ scope.row.tradeTotalAmount|rounding }}</span>
          </template>
        </el-table-column>
        <el-table-column label="批次号" align="center" width="180" prop="batchNumber">
          <template slot-scope="scope">
            <el-input v-model="scope.row.batchNumber" size="small"/>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" width="180" prop="remark">
          <template slot-scope="scope">
            <el-input v-model="scope.row.remark" size="small"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="small" type="danger" :disabled="isSubmit" @click="handleDelete(scope.$index,scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  filters: {
    rounding(value) {
      return value.toFixed(2)
    }
  },
  // 定义的数据
  data() {
    return {
      // 校验规则
      rules: {
        providerId: [
          { required: true, message: '请选择供应商', trigger: 'blur' }
        ]
      },
      // 弹出层的标题
      title: '',
      // 是否已提交
      isSubmit: false,

    }
  },
  // 钩子函数
  created() {

  }

}
</script>

<style scoped>
  .box-card{
    padding: 0px;
    margin: 8px 0 0 0;
  }
</style>
