<template>
  <div>
    <div style="float: left">
      <el-form :inline="true" :model="formInline" class="demo-form-inline" >
        <el-form-item label="厂家名称">
          <el-input v-model="formInline.user" placeholder="请输入厂家名称"></el-input>
        </el-form-item>
        <el-form-item label="关键字">
          <el-input v-model="formInline.user" placeholder="请输入关键字"></el-input>
        </el-form-item>
        <el-form-item label="厂家电话">
          <el-input v-model="formInline.user" placeholder="请输入厂家电话"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="formInline.region" placeholder="可用状态">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker
              v-model="value3"
              type="datetimerange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item style="clear: both">
          <el-button type="primary "  icon="el-icon-search" plain style="float: left">搜索</el-button>
          <el-button type="primary "  icon="el-icon-refresh" plain style="float: left">重置</el-button>
        </el-form-item>

      </el-form>
    </div>

    <div style="float: left;clear: both;padding-top:10px;padding-bottom:10px; ">
    <el-button type="primary "  icon="el-icon-plus"  plain >新增</el-button>
    <el-button type="success"   icon="el-icon-edit" plain >修改</el-button>
    <el-button type="danger"    icon="el-icon-delete"  plain >删除</el-button>
    </div>
    <div>
    <el-table ref="multipleTable"  :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="ID" label="厂家ID" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="厂家名称" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="厂家编码" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="联系人" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="电话" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="关键字" min-width="75" align="center"></el-table-column>
      <el-table-column prop="status" label="状态" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="创建时间" min-width="75" align="center"></el-table-column>
<!--      <el-table-column label="日期" width="120">-->
<!--        <template slot-scope="scope">{{ scope.row.date }}</template></el-table-column>-->
      <el-table-column fixed="right" label="操作" min-width="100" align="center">
        <template slot-scope="scope">
          <el-button @click="del(scope.row)" type="text" size="small" icon="el-icon-edit">删除</el-button>
          <el-button @click="upd(scope.row)" type="text" size="small " icon="el-icon-delete" >修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    </div>
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
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },

    onSubmit() {
      console.log('submit!');
    },

    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    }



    // handleClick(row) {
    //   console.log(row);
    // }
  },

  data() {
    return {
      formInline: {
        user: '',
        region: ''
      },
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }

      ]
    }
  }
}
</script>