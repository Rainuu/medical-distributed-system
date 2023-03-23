<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float:left">
      <el-form-item label="用户名称" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入用户名称"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="用户账号" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入用户账号"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="IP地址" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入IP地址"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="登陆状态" style="float:left">
        <el-select v-model="formInline.region" placeholder="请选择登陆状态">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="登陆类型" style="float:left">
        <el-select v-model="formInline.region" placeholder="请选择登陆类型">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>&nbsp;
      <el-form-item label="创建时间" style="float:left">
        <el-date-picker
            v-model="value3"
            type="datetimerange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item style="float:left">
        <el-button type="primary" @click="onSubmit"  icon="el-icon-search">查询</el-button>
        <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="float: left">
      <el-button type="danger" icon="el-icon-delete">删除</el-button>
      <el-button type="warning" icon="el-icon-error">清空</el-button>
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
          min-width="55">
      </el-table-column>
      <el-table-column
          align="center"
          prop="ksid"
          label="日志ID"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="ksmc"
          label="用户姓名"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zt1"
          label="登陆账号"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zt2"
          label="IP"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="登陆地址"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="浏览器"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="操作系统"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="登陆状态"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="用户类型"
          min-width="85">
      </el-table-column>
      <el-table-column
          align="center"
          label="登陆时间"
          min-width="130">
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column
          align="center"
          fixed="right"
          label="操作"
          min-width="130">
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
          :page-sizes="[85, 200, 300, 400]"
          :page-size="85"
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