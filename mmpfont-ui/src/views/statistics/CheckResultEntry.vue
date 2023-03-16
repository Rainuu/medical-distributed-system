<template>
  <div>
    <!--    弹窗-->
    <el-dialog title="录入检查结果" :visible.sync="dialogVisible" width="30%">
			<span>
				<el-form :model="form" ref="form" label-width="100px">
					<el-form-item label="检查结果" prop="name">
						<el-input v-model="form.name" type="textarea"></el-input>
					</el-form-item>


					<el-form-item label="结果上传" prop="city">
<!--             <el-button size="small" type="primary" style="float: left">点击上传</el-button>-->
<!--             <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->

            <!--文件上传-->
            <el-upload
                class="upload-demo"
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="3"
                :on-exceed="handleExceed"
                :file-list="fileList">
               <el-button size="small" type="primary" style="display: inline-block"><span style="float: left">点击上传</span></el-button>
            <div slot="tip" class="el-upload__tip"><span style="float: left">只能上传jpg/png文件，且不超过500kb</span></div>

            </el-upload>

					</el-form-item>


					<el-button type="primary" @click="onSubmit">立即创建</el-button>
					<el-button>取消</el-button>

				</el-form>
			</span>
    </el-dialog>



    <div style="display: flex;">
      <span style="float: contour;line-height: 40px;margin: 20px 10px">挂号单号</span>
      <el-input  placeholder="请输入挂号单号" v-model="input1" style="width: 400px;float: contour;margin: 20px 10px"/>
      <sapn style="float: contour;line-height: 40px;margin: 20px 10px">患者姓名</sapn>
      <el-input  placeholder="请输入患者姓名" v-model="input2" style="width: 400px;float: contour;margin: 20px 10px" />
      <el-button type="primary"  icon="el-icon-search" @click="resetForm" style="margin: 20px 10px">搜索</el-button>
      <el-button type="primary"  icon="el-icon-refresh" @click="resetForm" style="margin: 20px 10px">重置</el-button>

    </div>

    <div style="height: 50px;line-height: 50px">
      <span style="float: contour;float: left;margin: 0px 10px">检查项目</span>
      <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange"
                   style="float: left">全选
      </el-checkbox>
      <div style="height: 50px;padding-left: 20px;display: flex">
        <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange" >
          <el-checkbox v-for="city in cities" :label="city" :key="city">{{ city }}</el-checkbox>
        </el-checkbox-group>
      </div>

    </div>






    <div>
      <el-table
          :data="tableData"
          border
          style="width: 100%">
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


        <el-table-column
            prop="name"
            label="检查单位"
        >
        </el-table-column>
        <el-table-column
            prop="province"
            label="挂号单号"
        >
        </el-table-column>
        <el-table-column
            prop="city"
            label="项目名称"
        >
        </el-table-column>
        <el-table-column
            prop="address"
            label="患者姓名"
        >
        </el-table-column>
        <el-table-column
            prop="zip"
            label="检查状态"
        >
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
        >
          <template slot-scope="scope">
            <el-button @click="Check(scope.row)" type="text" i class="el-icon-edit">录入结果</el-button>


          </template>
        </el-table-column>
      </el-table>
    </div>



    <!--分页-->
    <el-pagination align="left"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="page.current"
                   :page-sizes="[10, 20, 30, 40]"
                   :page-size="page.size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="page.total">
    </el-pagination>

  </div>


</template>

<script>
const cityOptions = ['乙肝五项', '血常规', 'CT', 'X光'];
export default {
  methods: {
    // 文件上传
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleCheckAllChange(val) {
      this.checkedCities = val ? cityOptions : [];
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
    },
    handleSizeChange(val) {
      this.page.size = val;
    },
    handleCurrentChange(val) {
      this.page.current = val;
    },
    Check(row) {
      this.dialogVisible = true;
      this.form = {};
      console.log(row);

    }
  },

  data() {
    return {
      // 文件上传
      fileList: [],
      form:{ },
      dialogVisible: false,
      input1: '',
      input2: '',
      checkAll: false,
      checkedCities: ['乙肝五项', '血常规'],
      cities: cityOptions,
      isIndeterminate: true,


      page: {
        total: 2,
        size: 2,
        current: 1
      },
      tableData: [{
        id:'1232323',
        date: '2016-05-02',
        name: 'ITEM03940394304292',
        province: 'GH239839232032320',
        city: '血常规',
        address: '王小虎',
        zip: '检测完成',
        shop:'叽里呱啦',
        shopId:'2323'
      }, {
        id:'1232323',
        date: '2016-05-02',
        name: 'ITEM03940394304292',
        province: 'GH239839232032320',
        city: '血常规',
        address: '王小虎',
        zip: '检测完成',
        shop:'叽里呱啦',
        shopId:'2323'
      }]
    }
  }

}
</script>

<style scoped>
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