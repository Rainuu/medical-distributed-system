<template>
  <div>
    <!-- 工具栏 -->
    <div style="height: 40px; line-height: 40px; background-color: whitesmoke">
      <el-form :inline="true" :model="formInline" style="float:left;padding-left: 20px;" >
        <el-form-item label="患者姓名">
          <el-input v-model="formInline.user" placeholder="请输入患者姓名"></el-input>
        </el-form-item>
        <el-form-item label="挂号单号" style="padding-left: 60px;">
          <el-input v-model="formInline.user" placeholder="请输入挂号单号"></el-input>
        </el-form-item>
        <el-form-item style="padding-left: 60px;">
          <el-button type="primary" @click="find"  icon="el-icon-search">搜索</el-button> &nbsp;&nbsp; |&nbsp;
          <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <br>
    <!-- 页面表格 -->
    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column fixed prop="id" label="订单号" width="200px"></el-table-column>
        <el-table-column prop="did" label="挂号单号" width="100px"></el-table-column>
        <el-table-column prop="name" label="患者姓名" width="100px"></el-table-column>
        <el-table-column prop="total" label="总金额" width="100px"></el-table-column>
        <el-table-column prop="type" label="支付类型" width="100px"></el-table-column>
        <el-table-column prop="status" label="订单状态" width="100px"></el-table-column>
        <el-table-column prop="createtime" label="创建时间" width="160px"></el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看详情</el-button>
            <el-button @click="handleClick(scope.row)" type="text" size="small">现金收费</el-button>
            <el-button @click="handleClick(scope.row)" type="text" size="small">支付宝收费</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    find() {

    },
    resetForm(){

    },
    handleClick(row) {
      console.log(row);
    }
  },

  data() {
    return {
      formInline: {
        name: '',
        did: ''
      },
      tableData: [{
        id: 'ODC1460147514475937792',
        did: '2',
        name: '方号',
        total: '3000000',
        type: '支付宝',
        status: '未支付',
        createtime: '2030-01-01 00:00:00',
      }]
    }
  }
}
</script>
<style>

</style>
