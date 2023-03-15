<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float:left">
      <el-form-item label="检查项目名称" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入检查项目名称"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="关键字" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入关键字"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="项目类型" style="float:left">
        <el-select v-model="formInline.region" placeholder="请选择项目类型">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" style="float:left">
        <el-select v-model="formInline.region" placeholder="请选择状态">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>&nbsp;
      <el-form-item style="clear: both">
        <el-button type="primary" @click="onSubmit"  icon="el-icon-search">查询</el-button>
        <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="float: left">
      <el-button type="primary" icon="el-icon-plus">新增</el-button>
      <el-button type="success" icon="el-icon-edit">修改</el-button>
      <el-button type="danger" icon="el-icon-delete">删除</el-button>
    </div>
    <el-table
        ref="multipleTable"
        :data="tableData3"
        tooltip-effect="dark"
        border
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          align="center"
          prop="ksid"
          label="检查项目ID"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="ksmc"
          label="检查项目名称"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zt1"
          label="关键字"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zt2"
          label="项目单价"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="项目成本"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="单位"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="项目类型"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="状态"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          label="创建时间"
          min-width="100">
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column
          align="center"
          fixed="right"
          label="操作"
          min-width="150">
        <template slot-scope="scope"  align="center">
          <el-button @click="shanchu(scope.row)" type="text" size="small" icon="el-icon-delete">删除</el-button>
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
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-08',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-06',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-07',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      multipleSelection: [],
      formInline: {
        user: '',
        region: ''
      },
      currentPage4: 4,
      value3: [],

    }
  }


}
</script>