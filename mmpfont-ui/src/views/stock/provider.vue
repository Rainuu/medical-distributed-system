<template>
  <div>
    <div style="float: left">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="供应商名称">
          <el-input v-model="formInline.user" placeholder="请输入供应商名称" ></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="formInline.user" placeholder="请输入联系人"></el-input>
        </el-form-item>
        <el-form-item label="供销商电话">
          <el-input v-model="formInline.user" placeholder="请输入供销商电话"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="formInline.region" placeholder="可用状态">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item >
        <el-form-item style="padding-left: 60px;">
          <el-button type="primary "  icon="el-icon-search" plain >搜索</el-button>
          <el-button type="primary "  icon="el-icon-refresh" plain >重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div style="float: left">
    <el-button type="primary "  icon="el-icon-plus"  plain >新增</el-button>
    <el-button type="success"   icon="el-icon-edit" plain >修改</el-button>
    <el-button type="danger"    icon="el-icon-delete"  plain >删除</el-button>
    </div>
    <div>
    <el-table ref="multipleTable"  :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="ID" label="供应商ID" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="供应商名称" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="联系人手机" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="联系人电话" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="银行账号" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="供销商地址" min-width="75" align="center"></el-table-column>
      <el-table-column prop="status" label="状态" min-width="75" align="center"></el-table-column>
      <el-table-column prop="name" label="创建时间" min-width="70" align="center"></el-table-column>
<!--      <el-table-column label="日期" width="120">-->
<!--        <template slot-scope="scope">{{ scope.row.date }}</template></el-table-column>-->
      <el-table-column fixed="right" label="操作" min-width="80" align="center">
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