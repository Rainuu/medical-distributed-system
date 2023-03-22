<!--检查结果录入-->
<template>
  <div style="margin-top: 15px;margin-left: 15px">

    <!--弹出层开始-->
    <!--<el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>-->
    <el-dialog
        :title="'录入【'+[patientName]+'】的检查结果'"
        :visible.sync="dialogVisible"
        width="50%"
        center
        :before-close="handleClose">

      <!--form表单开始-->
      <span>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="检查结果">
          <el-input
              type="textarea" :rows="2" placeholder="请输入内容" v-model="textarea">
          </el-input>
        </el-form-item>
        <el-form-item label="结果上传">
          <el-upload
              class="upload-demo" action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove" multiple :limit="3"
              :on-exceed="handleExceed" :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
      </el-form>
        <!--form表单结束-->
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        <el-button @click="dialogVisible = false" style="">取 消</el-button>

      </span>
    </el-dialog>
    <!--弹出层结束-->

    <!--form表单-->
    <!--多选框-->
    <el-form ref="form" :model="form" label-width="80px" style="text-align-last: justify;padding-left: 10px">
      <el-form-item label="检查项目" >
        <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange" >全选</el-checkbox>
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
          <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
    </el-form>

    <!--搜索框-->
    <div style="display: flex;">
      <span style="float: contour;line-height: 40px;margin: 20px 10px">挂号单号</span>
      <el-input  placeholder="请输入挂号单号" v-model="input1" style="width: 400px;float: contour;margin: 20px 10px"/>
      <sapn style="float: contour;line-height: 40px;margin: 20px 10px">患者姓名</sapn>
      <el-input  placeholder="请输入患者姓名" v-model="input2" style="width: 400px;float: contour;margin: 20px 10px" />
      <el-button type="primary"  icon="el-icon-search" @click="resetForm" style="margin: 20px 10px">搜索</el-button>
      <el-button type="primary"  icon="el-icon-refresh" @click="resetForm" style="margin: 20px 10px">重置</el-button>

    </div>

    <!--table表格-->
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
          <el-button icon="el-icon-edit" @click="handleClick(scope.row)" type="text" size="small">录入结果</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageCurrent"
        :page-sizes="[2, 4, 6, 8]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

  </div>

</template>

<script>
const cityOptions = ['乙肝五项', '血常规', 'CT', 'X光'];
export default {
  name: "InspectionResults",

  data() {
    return {
      checkAll: true,
      checkedCities: ['乙肝五项', '血常规'],
      cities: cityOptions,
      isIndeterminate: true,

      pageCurrent: 1,
      pageSize: 10,
      total: 1,

      textarea: '',

      dialogVisible: false,


      formInline: {
        user: '',
        region: ''
      },
      form: {
        type: []
      },
      patientName:'',

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
      }],
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    /*查看弹出层*/
    handleClick(row) {
      this.dialogVisible = true;
      this.patientName=row.name;
      this.form= row;
      console.log(row);
      console.log(this.patientName);
    },
    /*全选*/
    handleCheckAllChange(val) {
      this.checkedCities = val ? cityOptions : [];
      this.isIndeterminate = false;
    },
    /*单选*/
    handleCheckedCitiesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
    },
    /*分页*/
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },

    /*上传文件*/
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
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    /*弹出层关闭*/
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    }
  }
}
</script>

<style scoped>

</style>
