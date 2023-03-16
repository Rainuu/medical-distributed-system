<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick" stretch="false">
      <el-tab-pane label="药品总库存" name="first"  :cell-style="rowStyle" >
        <div >
          <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float: left">
            <el-form-item label="药品名称"  style="float: left">
              <el-input v-model="formInline.user" placeholder="请输入药品名称"></el-input>
            </el-form-item><el-form-item label="关键字">
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
            <el-form-item label="处方类型"  style="float: left">
              <el-select v-model="formInline.region" placeholder="处方类型">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="创建时间"  style="float: left">
              <el-date-picker
                  v-model="value3"
                  type="datetimerange"
                  range-separator="-"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item style="float: left" >
              <el-button type="primary "  icon="el-icon-search" plain >搜索</el-button>
              <el-button type="primary "  icon="el-icon-refresh" plain >重置</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div  >
          <el-table ref="multipleTable"   :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" border>
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="ID" label="药品ID" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="药品名称" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="药品编号" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="生产厂家" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="药品类型" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="处方类型" min-width="75" align="center"></el-table-column>
            <el-table-column prop="status" label="关键字" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="库存量" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="预警值" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="状态" min-width="75" align="center"></el-table-column>
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


      </el-tab-pane>


      <el-tab-pane label="批次库存及价格" name="second">
        <div >
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="药品名称"  style="float: left">
              <el-input v-model="formInline.user" placeholder="请输入药品名称"></el-input>
            </el-form-item><el-form-item label="关键字">
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
            <el-form-item label="处方类型" style="float: left">
              <el-select v-model="formInline.region" placeholder="处方类型">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="创建时间"  style="float: left">
              <el-date-picker
                  v-model="value3"
                  type="datetimerange"
                  range-separator="-"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item style="float: left" >
              <el-button type="primary "  icon="el-icon-search" plain >搜索</el-button>
              <el-button type="primary "  icon="el-icon-refresh" plain >重置</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div  >
          <el-table ref="multipleTable"   :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" border>
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="ID" label="药品ID" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="药品名称" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="采购量" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="批发价" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="批发额" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="批次号" min-width="75" align="center"></el-table-column>
            <el-table-column prop="status" label="生产厂家" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="药品类型" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="处方类型" min-width="75" align="center"></el-table-column>
            <el-table-column prop="name" label="入库时间" min-width="75" align="center"></el-table-column>
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


      </el-tab-pane>

    </el-tabs>
  </div>
</template>

<script>
export default {
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
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
      activeName: 'first',
      value1:{},
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