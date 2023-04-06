<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item label="字典类型" prop="dictType">
        <el-select
          v-model="queryParams.dictType"
          placeholder="请选择字典类型"
          clearable
          size="small"
          style="width:240px"
        >
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictType"
            :label="dict.dictName"
            :value="dict.dictType"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="字典标签" prop="dictLabel">
        <el-input
          v-model="queryParams.dictLabel"
          placeholder="请输入字典标签"
          clearable
          size="small"
          style="width:240px"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="字典状态"
          clearable
          size="small"
          style="width:240px"
        >
          <el-option
            v-for="dict in dictList.filter((n)=>{return n.dictType==='sys_normal_disable'})"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 查询条件结束 -->

    <!-- 表头按钮开始 -->
    <el-row :gutter="10" style="margin-bottom: 8px;">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="pldel">删除</el-button>
      </el-col>

    </el-row>
    <!-- 表头按钮结束 -->

    <!-- 数据表格开始 -->
    <el-table v-loading="loading" border :data="dictDataTableList" @selection-change="handleSelectionChnage">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="字典编码" prop="dictCode" align="center" />
      <el-table-column label="字典标签" prop="dictLabel" align="center" />
      <el-table-column label="字典键值" prop="dictValue" align="center" />
      <el-table-column label="字典排序" prop="dictSort" align="center" />
      <el-table-column label="状态" prop="status" align="center" :formatter="(row)=>dictFormat(row,row.status,'sys_normal_disable')" />
      <el-table-column label="备注" prop="remark" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" prop="createTime" align="center" width="180" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 分页组件开始 -->
    <el-pagination
      v-show="total>0"
      :current-page="queryParams.current"
      :page-sizes="[5, 10, 20, 30]"
      :page-size="queryParams.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="queryParams.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <!-- 分页组件结束 -->

    <!-- 添加和修改的弹出层开始 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="500px"
      center
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="字典类型" prop="dictType">
          <el-input v-model="form.dictType" :disabled="true" size="small" />
        </el-form-item>
        <el-form-item label="数据标签" prop="dictLabel">
          <el-input v-model="form.dictLabel" placeholder="请输入数据标签" clearable size="small" />
        </el-form-item>
        <el-form-item label="数据键值" prop="dictValue">
          <el-input v-model="form.dictValue" placeholder="请输入数据键值" clearable size="small" />
        </el-form-item>
        <el-form-item label="排序显示" prop="dictSort">
          <el-input-number v-model="form.dictSort" placeholder="请输入数据键值" clearable size="small" :min="0" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dictList.filter((n)=>{return n.dictType==='sys_normal_disable'})"
              :key="dict.dictValue"
              :label="dict.dictValue"
              :value="dict.dictValue"
            >{{ dict.dictLabel }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入字典备注" clearable size="small" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </span>
    </el-dialog>
    <!-- 添加和修改的弹出层结束 -->

  </div>
</template>
<script>
import qs from 'qs'
export default {
  data() {
    return {
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 字典数据表格数据
      dictDataTableList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      dictList: [],
      // 类型数据字典
      typeOptions: [],
      // 默认查询的类型
      defaultDictType: undefined,
      // 查询参数
      queryParams: {
        dictId:'',
        current:1,
        size:5,
        total:0,
        dictLabel: undefined,
        dictType: undefined,
        status: undefined,

      },
      // 表单数据
      form: {},
      // 表单校验
      rules: {
        dictLabel: [
          { required: true, message: '数据标签不能为空', trigger: 'blur' }
        ],
        dictValue: [
          { required: true, message: '数据键值不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    // 取路由路径上的参数
    this.queryParams.dictId = this.$route.params && this.$route.params.dictId // 路由传参
    // 做分页查询
    this.getDictDataList()
    this.getDict()
    this.GetDictType()
  },
  deactivated() {
    //解决vue组件 前进刷新，后退不刷新，
    //$destroy完全销毁一个实例，清理它与其它实例的连接，解绑它的全部指令及事件监听器。
    this.$destroy();
  },
  methods: {
    // 查询字典数据
    getDictDataList() {
      this.loading = true
      this.$axios.post("system/api/dict/data/getInfoById",qs.stringify(this.queryParams)).then(res => {
        this.loading = false
        this.dictDataTableList = res.data.t.records
        this.defaultDictType=this.dictDataTableList[0].dictType
        this.queryParams.total = res.data.t.total
      })
    },
    // 条件查询
    handleQuery() {
      this.queryParams.dictId=undefined
      this.getDictDataList()
    },
    // 重置查询条件
    resetQuery() {
      this.queryParams.dictType=undefined
      this.queryParams.dictLabel=undefined
      this.queryParams.status=undefined
      this.queryParams.dictType = this.defaultDictType
      this.getDictDataList()
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChnage(selection) {
      this.ids = selection.map(item => item.dictCode)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      // 重新查询
      this.getDictDataList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      // 重新查询
      this.getDictDataList()
    },
    // 重置表单
    reset() {
      this.form = {
        dictCode: undefined,
        dictLable: undefined,
        dictValue: undefined,
        dictType: undefined,
        status: '0',
        dictSort: 0,
        remark: undefined
      }
    },
    // 打开添加的弹出层
    handleAdd() {
      this.open = true
      this.reset()
      this.form.dictType = this.defaultDictType
    },
    // 打开修改的弹出层
    handleUpdate(row) {
      this.open = true
      this.reset()
      const dictCode = row.dictCode
      alert(dictCode)
      // 根据字典数据ID查询字典数据
      this.$axios.get('system/api/dict/data/'+dictCode).then(res => {
        this.form = res.data.t
      })
    },
    //批量删除
    pldel(){

      this.$confirm('此操作将永久删除该字典数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        for (var i=0;i<this.ids.length;i++){
          this.$axios.delete("system/api/dict/data/"+this.ids[i])
        }
      }).catch(() => {
        this.$message.error('删除已取消')
        this.loading = false
      })
      this.resetQuery()
    },
    // 进行删除
    handleDelete(row) {
      const dictCodes = row.dictCode
      this.$confirm('此操作将永久删除该字典数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        this.$axios.delete("system/api/dict/data/"+dictCodes).then(res => {
          this.loading = false
          this.$message.success('删除成功')
          this.getDictDataList()// 全查询
        })
      }).catch(() => {
        this.$message.error('删除已取消')
        this.loading = false
      })
    },
    // 保存
    handleSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // 做添加
          this.loading = true
          if (this.form.dictCode === undefined) {
            this.form.dictType=this.defaultDictType
            this.$axios.post("system/api/dict/data/saveAndUp",qs.stringify(this.form)).then(res => {
              this.$message.success('保存成功')
              this.loading = false
              this.getDictDataList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
            })
          } else { // 做修改
            this.$axios.post("system/api/dict/data/saveAndUp",qs.stringify(this.form)).then(res => {
              this.$message.success('修改成功')
              this.loading = false
              this.getDictDataList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
            })
          }
        }
      })
    },
    //字典解析
    dictFormat(row, column, dictType){
      return this.formatDict( this.dictList,column, dictType)
    },

    //初始化字典
    getDict() {
      this.$axios.get('/system/api/dict/data/getall').then(res => {
        this.dictList = res.data.t
      })
    },
    // 取消
    cancel() {
      this.open = false
    },
    GetDictType(){
      this.$axios.get("system/api/dictType/getInfo").then(res=>{
        this.typeOptions=res.data.t
      })
    }
  }
}
</script>
