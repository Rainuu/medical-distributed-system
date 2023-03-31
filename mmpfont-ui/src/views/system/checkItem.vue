<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="98px">
      <el-form-item label="检查项目名称" prop="checkItemName">
        <el-input
            v-model="queryParams.checkItemName"
            placeholder="请输入检查项目名称"
            clearable
            size="small"
            style="width:240px"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="keywords">
        <el-input
            v-model="queryParams.keywords"
            placeholder="请输入关键字"
            clearable
            size="small"
            style="width:240px"
        />
      </el-form-item>
      <el-form-item label="项目类型" prop="typeId">
        <el-select
            v-model="queryParams.typeId"
            placeholder="项目类型"
            clearable
            size="small"
            style="width:240px"
        >
          <el-option
              v-for="dict in dictList.filter((n)=>{return n.dictType==='his_inspection_type'})"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
            v-model="queryParams.status"
            placeholder="可用状态"
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

    <!-- 表格工具按钮开始 -->
    <el-row :gutter="10" style="margin-bottom: 8px;">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
    </el-row>
    <!-- 表格工具按钮结束 -->

    <!-- 数据表格开始 -->
    <el-table v-loading="loading" border :data="checkItemTableList" @selection-change="handleSelectionChnage">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="检查项目ID" align="center" prop="checkItemId" />
      <el-table-column label="检查项目名称" align="center" prop="checkItemName" />
      <el-table-column label="关键字" align="center" prop="keywords" />
      <el-table-column label="项目单价" align="center" prop="unitPrice" />
      <el-table-column label="项目成本" align="center" prop="cost" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="项目类别" prop="typeId" align="center" :formatter="(row)=>dictFormat(row,row.typeId,'his_inspection_type')" />
      <el-table-column label="状态" prop="status" align="center" :formatter="(row)=>dictFormat(row,row.status,'sys_normal_disable')" />
      <el-table-column label="创建时间" align="center" prop="createTime" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->
    <!-- 分页控件开始 -->
    <el-pagination
        v-show="queryParams.total>0"
        :current-page="queryParams.current"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="queryParams.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="queryParams.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
    <!-- 分页控件结束 -->

    <!-- 添加修改弹出层开始 -->
    <el-dialog
        :title="title"
        :visible.sync="open"
        width="600px"
        center
        append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="检查项目ID" prop="checkItemId" hidden="true">
          <el-input v-model="form.checkItemId" placeholder="请输入检查项目名称" clearable size="small" />
        </el-form-item>
        <el-form-item label="项目类型" prop="typeId">
          <el-radio-group v-model="form.typeId">
            <el-radio
                v-for="dict in dictList.filter((n)=>{return n.dictType==='his_inspection_type'})"
                :key="dict.dictValue"
                :label="dict.dictValue"
                :value="dict.dictValue"
            >{{ dict.dictLabel }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="检查项目名称" prop="checkItemName">
          <el-input v-model="form.checkItemName" placeholder="请输入检查项目名称" clearable size="small" />
        </el-form-item>
        <el-form-item label="关键字" prop="keywords">
          <el-input v-model="form.keywords" placeholder="请输入关键字" clearable size="small" />
        </el-form-item>
        <el-form-item label="检查项目价格" prop="unitPrice">
          <el-input-number v-model="form.unitPrice" placeholder="请输入检查项目价格" clearable size="small" />
        </el-form-item>
        <el-form-item label="检查项目成本" prop="cost">
          <el-input-number v-model="form.cost" placeholder="请输入检查项目成本" clearable size="small" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入项目单位" clearable size="small" />
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
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </span>
    </el-dialog>
    <!-- 添加修改弹出层结束 -->

  </div>
</template>
<script>
import qs from 'qs'
export default {
  // 定义页面数据
  data() {
    return {
      // 是否启用遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 字典表格数据
      checkItemTableList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      // 类型数据字典
      typeOptions: [],
      dictList:[],
      // 查询参数
      queryParams: {
        current:1,
        size:5,
        total:0,
        checkItemName: undefined,
        keywords: undefined,
        typeId: undefined,
        status: undefined
      },
      // 表单数据
      form: {},
      // 表单校验
      rules: {
        checkItemName: [
          { required: true, message: '检查项目名称不能为空', trigger: 'blur' }
        ],
        keywords: [
          { required: true, message: '检查项目关键字不能为空', trigger: 'blur' }
        ],
        unitPrice: [
          { required: true, message: '检查项目单价不能为空', trigger: 'blur' }
        ],
        cost: [
          { required: true, message: '检查项目成本不能为空', trigger: 'blur' }
        ],
        unit: [
          { required: true, message: '检查项目单位不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  // 勾子
  created() {
    this.getDict()
    // 查询表格数据
    this.getCheckItemList()
  },
  // 方法
  methods: {
    // 查询表格数据
    getCheckItemList() {
      this.loading = true // 打开遮罩
      this.$axios.post("system/api/checkItem/getAll",qs.stringify(this.queryParams)).then(res => {
        this.checkItemTableList = res.data.t.records
        this.queryParams.total = res.data.t.total
        this.loading = false// 关闭遮罩
      })
    },
    // 条件查询
    handleQuery() {
      this.getCheckItemList()
    },
    // 重置查询条件
    resetQuery() {
      this.queryParams.checkItemName=undefined
      this.queryParams.status=undefined
      this.queryParams.typeId=undefined
      this.queryParams.keywords=undefined
      this.getCheckItemList()
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChnage(selection) {
      this.ids = selection.map(item => item.checkItemId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.size = val
      // 重新查询
      this.getCheckItemList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.current = val
      // 重新查询
      this.getCheckItemList()
    },
    // 打开添加的弹出层
    handleAdd() {
      this.open = true
      this.reset()
      this.title = '添加检查项目信息'
    },
    // 打开修改的弹出层
    handleUpdate(row) {
      this.title = '修改检查项目信息'
      this.form=JSON.parse(JSON.stringify(row));
      this.open = true
    },
    // 执行删除
    piliDelete(row) {
      const checkItemIds = this.ids
      const tx = this
      tx.$confirm('此操作将永久删除该检查项目数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        tx.loading = true
        for (var i = 0; i < checkItemIds.length; i++) {
        this.$axios.delete("system/api/checkItem/" + checkItemIds).then(res => {
        })
      }
        tx.getCheckItemList()// 全查询
      }).catch(() => {
        tx.$message.error('删除已取消')
        tx.loading = false
      })
    },
    // 执行删除
    handleDelete(row) {
      const checkItemIds = row.checkItemId
      const tx = this
      tx.$confirm('此操作将永久删除该检查项目数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        tx.loading = true
        this.$axios.delete("system/api/checkItem/"+checkItemIds).then(res => {
          tx.loading = false
          tx.$message.success('删除成功')
          tx.getCheckItemList()// 全查询
        }).catch(() => {
          tx.$message.success('删除失败')
          tx.loading = false
        })
      }).catch(() => {
        tx.$message.error('删除已取消')
        tx.loading = false
      })
    },
    // 保存
    handleSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // 做添加
          this.loading = true
          const tx = this
          if (tx.form.checkItemId === undefined) {
            this.$axios.post("system/api/checkItem/saveAndUp",qs.stringify(this.form)).then(res => {
              tx.$message.success('保存成功')
              tx.loading = false
              tx.getCheckItemList()// 列表重新查询
              tx.open = false// 关闭弹出层
            }).catch(() => {
              tx.loading = false
            })
          } else { // 做修改
            this.$axios.post("system/api/checkItem/saveAndUp",qs.stringify(this.form)).then(res => {
              tx.$message.success('修改成功')
              tx.loading = false
              tx.getCheckItemList()// 列表重新查询
              tx.open = false// 关闭弹出层
            }).catch(() => {
              tx.loading = false
            })
          }
        }
      })
      this.reset()
    },
    // 取消
    cancel() {
      this.open = false
      this.title = ''
      this.form={}
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
  }
}
</script>
