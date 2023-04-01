<template>
    <div class="app-container">
        <!-- 查询条件开始 -->
        <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
            <el-form-item label="角色名称" prop="roleName">
                <el-input
                        v-model="queryParams.roleName"
                        placeholder="请输入角色名称"
                        clearable
                        size="small"
                        style="width:240px"
                />
            </el-form-item>
            <el-form-item label="权限字符" prop="roleCode">
                <el-input
                        v-model="queryParams.roleCode"
                        placeholder="请输入权限字符"
                        clearable
                        size="small"
                        style="width:240px"
                />
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
                            v-for="dict in statusOptions"
                            :key="dict.dictValue"
                            :label="dict.dictLabel"
                            :value="dict.dictValue"
                    />
                </el-select>
            </el-form-item>
            <el-form-item label="创建时间">
                <el-date-picker
                        v-model="queryParams.dateRange"
                        size="small"
                        style="width:240px"
                        value-format="yyyy-MM-dd"
                        type="daterange"
                        range-separator="-"
                        start-placeholde="开始日期"
                        end-placeholde="结束日期"
                />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="mohu">搜索</el-button>
                <el-button type="primary" icon="el-icon-refresh" size="mini" @click="queryParams={}">重置</el-button>
            </el-form-item>
        </el-form>
        <!-- 查询条件结束 -->

        <!-- 表格工具按钮开始 -->
        <el-row :gutter="10" style="margin-bottom: 8px;">
            <el-col :span="1.5">
                <el-button type="primary" icon="el-icon-plus" size="mini" @click="dialogVisible=true" >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="pldel">删除</el-button>
            </el-col>

        </el-row>
        <!-- 表格工具按钮结束 -->

        <!-- 数据表格开始 -->
        <el-table ref="multipleTable" border :data="roleTableList" @selection-change="handleSelectionChnage">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="角色ID" align="center" prop="roleId" />
            <el-table-column label="角色名称" align="center" prop="roleName" />
            <el-table-column label="权限编码" align="center" prop="roleCode" />
            <el-table-column label="显示顺序" align="center" prop="roleSort" />
            <el-table-column label="状态" prop="status" align="center"  />
            <el-table-column label="备注" align="center" prop="remark" />
            <el-table-column label="创建时间" align="center" prop="createTime" />
            <el-table-column label="操作" align="center" width="280">
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
                    <el-button type="text" icon="el-icon-delete" size="mini" @click="delRole(scope.row.roleId)">删除</el-button>
                    <el-button type="text" icon="el-icon-thumb" size="mini" @click="fenMenu(scope.row)">分配权限</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 数据表格结束 -->
        <!-- 分页控件开始 -->
        <el-pagination
                v-show="total>0"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 30]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
        />
        <!-- 分页控件结束 -->
        <!-- 分配权限和菜单弹出层开始 -->
        <el-dialog
                :visible.sync="selectMenuOpen"
                width="400px"
                center
                append-to-body
        >
            <el-tree
                    ref="menu"
                    :data="menuOptions"
                    show-checkbox
                    node-key="menuId"
                    highlight-current
                    empty-text="加载中，请稍后"
                    :props="{id:'menuId',children:'children',label:'menuName'}"
            />
            <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSelectMenuSubmit" >确 定</el-button>
        <el-button >取 消</el-button>
      </span>
        </el-dialog>
        <!-- 分配权限和菜单弹出层结束 -->
      <!-- 添加弹出层开始 -->
      <el-dialog
          title="提示"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
        <el-form ref="addRole" :model="addRole" :rules="rules" label-width="80px" class="demo-ruleForm">
          <el-form-item label="部门Id" hidden="true">
            <el-input v-model="addRole.roleId"></el-input>
          </el-form-item>
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="addRole.roleName"></el-input>
          </el-form-item>
          <el-form-item label="权限编码" prop="roleCode">
            <el-input v-model="addRole.roleCode"></el-input>
          </el-form-item>
          <el-form-item label="显示顺序">
            <el-input-number v-model="addRole.roleSort"  :min="1" :max="10" label="描述文字"></el-input-number>
          </el-form-item>
          <el-form-item label="状态">
            <el-radio v-model="addRole.status" label="0">正常</el-radio>
            <el-radio v-model="addRole.status" label="1">停用</el-radio>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="addRole.remark">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('addRole')">立即创建</el-button>
            <el-button @click="handleClose">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <!-- 添加弹出层结束 -->
    </div>
</template>

<script>
    import qs from "qs";

    export default {
        name: "User",
        data(){
            return {
              multiple:true,
              multipleSelection: [],
              dialogVisible: false,
              queryParams: {},
              roleTableList: [],
              statusOptions: [],
              currentPage: 1,
              total: 0,
              pageSize: 5,
              //控制分配权限弹出层的显示和隐藏
              selectMenuOpen: false,
              //树形菜单的数据
              menuOptions: [],
              //当前的角色id
              roleId: 0,
              //被勾选的菜单id
              checkIds: [],
              addRole: {
              roleName: '',
                roleCode:'',
                roleSort:'',
                status:'',
                remark:'',
            },
              rules:{
                roleCode: [
                  {required: true, message: '请输入角色名称', trigger: 'blur'},
                  {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                ],
                roleName: [
                  {required: true, message: '请输入角色权限编码', trigger: 'blur'},
                  {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                ],
              }
            }
        },
        created() {
            //加载系统状态
            this.initStatus();
            //初始化角色表格
            this.initRoleTable();
        },
        methods:{
          mohu(){
            this.currentPage=1
           this.initRoleTable()
          },
          update(row){
            this.dialogVisible=true
            this.addRole=JSON.parse(JSON.stringify(row))
          },
          pldel(){
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              for(let i=0;i<this.multipleSelection.length;i++){
                this.$axios.delete('/system/api/user/'+this.multipleSelection[i].roleId)
              }
              this.mohu()
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });


          },
          delRole(id){
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {

              this.$axios.delete('/system/api/role/'+id).then(res=>{
                if (res.data.t){
                  this.initRoleTable()
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  });
                }else {
                  this.$message({
                    type: 'error',
                    message: res.data.msg
                  });
                }
              })

            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });
          },
          submitForm(formName) {
            this.$refs[formName].validate((valid) => {
              if (valid) {
                this.$axios.post("/system/api/role/saveAndUp",qs.stringify(this.addRole)).then(res=>{
                  if (res.data.t){
                    this.$message.success("添加成功")
                    this.dialogVisible=false;
                    this.addRole={};
                    this.initRoleTable()
                  }else {
                    this.$message.error("添加失败")
                  }
                })
              } else {
                console.log('error submit!!');
                return false;
              }
            });
          },
          handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                  this.addRole={}
                  done();
                })
                .catch(_ => {
                });
          },
          // 数据表格的多选择框选择时触发
          handleSelectionChnage(selection) {
            this.multipleSelection = selection;
            this.multiple = !selection.length
          },
            //显示树形菜单的弹出层
            fenMenu(row){
                this.roleId=row.roleId;
                this.selectMenuOpen=true;
                //获取所有的树形菜单
                this.$axios.get("/system/api/menu/leftMenu").then(result=>{
                    this.menuOptions=result.data.t;
                })
                //获取当前角色具有的菜单
                this.$axios.get("system/api/menu/"+this.roleId).then(result=>{
                     this.checkIds=result.data.t;
                    //默认选中的树的数据
                    setTimeout(() =>{
                        result.data.t.forEach(value => {
                            this.$refs.menu.setChecked(value, true, false);
                        })}, 100)
                })
            },
            //获取所有被选中的id
            handleSelectMenuSubmit(){
                let checkAllIds=this.$refs.menu.getCheckedKeys()
                let checkHalfIds=this.$refs.menu.getHalfCheckedKeys()
                let keys=checkAllIds.concat(checkHalfIds);
                this.$axios.post("/system/api/menu/confirmMenu?roleId="+this.roleId+"&menuIds="+keys).then(result=>{
                    this.selectMenuOpen=false;
                    this.$message.success("分配权限成功")
                })
            },
            initStatus(){
                this.$axios.get("/system/api/dict/data/findByType/sys_normal_disable").then(result=>{
                    this.statusOptions=result.data.t;
                })
            },
            initRoleTable(){
                this.$axios.post("/system/api/role/list/"+this.currentPage+"/"+this.pageSize,this.queryParams).then(result=>{
                        this.roleTableList=result.data.t.records;
                        this.total=result.data.t.total;
                })
            },
            // 分页pageSize变化时触发
            handleSizeChange(val) {
                this.pageSize = val
                // 重新查询
                this.initRoleTable()
            },
            // 点击上一页  下一页，跳转到哪一页面时触发
            handleCurrentChange(val) {
                this.currentPage = val
                // 重新查询
                this.initRoleTable()
            },
        }
    }
</script>

<style scoped>

</style>
