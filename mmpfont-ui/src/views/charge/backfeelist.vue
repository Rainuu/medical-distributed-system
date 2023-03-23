<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float: left">
      <el-form-item label="患者姓名" label-width="100px">
        <el-input v-model="formInline.user" placeholder="请输入患者姓名"></el-input>
      </el-form-item>
      <el-form-item label="挂号单号">
        <el-input v-model="formInline.user" placeholder="请输入挂号单号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit"  icon="el-icon-search">查询</el-button>
        <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
        ref="multipleTable"
        :data="tableData3"
        tooltip-effect="dark"
        border
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          align="center"
          prop="tfddh"
          label="退费订单号"
          min-width="120">
      </el-table-column>
      <el-table-column
          align="center"
          prop="ghdh"
          label="挂号单号"
          min-width="120">
      </el-table-column>
      <el-table-column
          align="center"
          prop="hzxm"
          label="患者姓名"
          min-width="120">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zje"
          label="总金额"
          min-width="120">
      </el-table-column>
      <el-table-column
          align="center"
          prop="tflx"
          label="退费类型"
          min-width="120">
      </el-table-column>
      <el-table-column
          align="center"
          prop="ddzt"
          label="订单状态"
          min-width="120">
      </el-table-column>
      <el-table-column
          align="center"
          label="创建时间"
          min-width="145">
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column
          align="center"
          fixed="right"
          label="操作"
          min-width="180">
        <template slot-scope="scope"  align="center">
          <el-button @click="xiugai(scope.row)" type="text" size="small" icon="el-icon-edit">修改</el-button>
          <el-button @click="shanchu(scope.row)" type="text" size="small" icon="el-icon-delete">删除</el-button>
          <el-button @click="chakan(scope.row)" type="text" size="small" icon="el-icon-view">查看</el-button>

        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="float: left">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[100, 200, 300, 400]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    onSubmit() {
      console.log('submit!');
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  },
  data() {
    return {
      tableData3: [{
        date: '2016-05-03',
        hzxm: '方狗',
        tfddh:6666666666666,
        ghdh:250,
        zje:250,
        tflx:"硬币",
        ddzt:"未退费",
      },{
        date: '2016-05-03',
        hzxm: '方狗',
        tfddh:6666666666666,
        ghdh:250,
        zje:250,
        tflx:"硬币",
        ddzt:"未退费",
      },{
        date: '2016-05-03',
        hzxm: '方狗',
        tfddh:6666666666666,
        ghdh:250,
        zje:250,
        tflx:"硬币",
        ddzt:"未退费",
      },{
        date: '2016-05-03',
        hzxm: '方狗',
        tfddh:6666666666666,
        ghdh:250,
        zje:250,
        tflx:"硬币",
        ddzt:"未退费",
      },{
        date: '2016-05-03',
        hzxm: '方狗',
        tfddh:6666666666666,
        ghdh:250,
        zje:250,
        tflx:"硬币",
        ddzt:"未退费",
      },{
        date: '2016-05-03',
        hzxm: '方狗',
        tfddh:6666666666666,
        ghdh:250,
        zje:250,
        tflx:"硬币",
        ddzt:"未退费",
      },],
      multipleSelection: [],
      formInline: {
        user: '',
        region: ''
      },
      currentPage4: 4
    }
  }


}
</script>