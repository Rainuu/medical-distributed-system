<template>
  <div>
    <div style="margin-right:350px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="药品名称">
          <el-input v-model="formInline.user" placeholder="请输入药品名称"></el-input>
        </el-form-item>
        <el-form-item label="关键字">
          <el-input v-model="formInline.user" placeholder="请输入关键字"></el-input>
        </el-form-item>
        <el-form-item label="药品类型">
          <el-select v-model="formInline.region" placeholder="药品类型">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="生产厂家">
          <el-select v-model="formInline.region" placeholder="生产厂家">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="处方类型 " style="float: left">
          <el-select v-model="formInline.region" placeholder="处方类型">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" style="float: left">
          <el-select v-model="formInline.region" placeholder="状态">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>


        <el-button type="primary "  icon="el-icon-search" plain style="float: left">搜索</el-button>
        <el-button type="primary "  icon="el-icon-refresh" plain style="float: left"  >重置</el-button>
      </el-form>
    </div>

    <div style="float: left;clear: both;padding-bottom:10px; ">
    <el-button type="primary "  icon="el-icon-plus"  plain >新增</el-button>
    <el-button type="success"   icon="el-icon-edit" plain >修改</el-button>
    <el-button type="danger"    icon="el-icon-delete"  plain >删除</el-button>
    </div>
    <div>
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="单位"><span>{{ props.row.name }}</span></el-form-item>
              <el-form-item label="换算量"><span>{{ props.row.shop }}</span></el-form-item>
              <el-form-item label="库存"><span>{{ props.row.id }}</span></el-form-item>
              <el-form-item label="预警值"><span>{{ props.row.shopId }}</span></el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="药品ID" prop="id"></el-table-column>
        <el-table-column label="药品名称" prop="name"></el-table-column>
        <el-table-column label="药品编号" prop="name"></el-table-column>
        <el-table-column label="生产厂家" prop="name"></el-table-column>
        <el-table-column label="药品类型" prop="name"></el-table-column>
        <el-table-column label="处方类型" prop="name"></el-table-column>
        <el-table-column label="关键字" prop="name"></el-table-column>
        <el-table-column label="处方价格" prop="name"></el-table-column>
        <el-table-column label="状态" prop="name"></el-table-column>
              <el-table-column fixed="right" label="操作" width="100">
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

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

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
          id: '12987126',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        },
    {
      id: '12987126',
          name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
    }

      ]
    }
  }
}
</script>