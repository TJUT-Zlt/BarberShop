<template>
    <div class="app-container">
      <el-row :gutter="20">
      <!--部门数据-->
        <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input
              v-model="deptName"
              placeholder="请输入部门名称"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
          <div class="head-container">
            <el-tree
              :data="deptOptions"
              :props="defaultProps"
              :expand-on-click-node="false"
              :filter-node-method="filterNode"
              ref="tree"
              node-key="id"
              default-expand-all
              highlight-current
              @node-click="handleNodeClick"
            />
          </div>
        </el-col>
      <el-col :span="20" :xs="24">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="工具名称" prop="toolName">
          <el-input
            v-model="queryParams.toolName"
            placeholder="请输入工具名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="工具状态" prop="toolStatus">
          <el-select v-model="queryParams.toolStatus" placeholder="请选择工具状态" clearable>
            <el-option
              v-for="dict in dict.type.sys_tool_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['business:BizTool:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['business:BizTool:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['business:BizTool:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['business:BizTool:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="BizToolList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="工具ID" align="center" prop="toolId" />
        <el-table-column label="工具所属部门" align="center" prop="dept.deptName" />
        <el-table-column label="工具名称" align="center" prop="toolName" />
        <el-table-column label="工具价格" align="center" prop="price" />
        <el-table-column label="工具数量" align="center" prop="number" />
        <el-table-column label="工具状态" align="center" prop="toolStatus">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_tool_status" :value="scope.row.toolStatus"/>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['business:BizTool:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['business:BizTool:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
      </el-col>
    </el-row>
  
      <!-- 添加或修改工具管理对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="工具所属部门" prop="deptId">
            <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择工具所属部门" />
          </el-form-item>
          <el-form-item label="工具名称" prop="toolName">
            <el-input v-model="form.toolName" placeholder="请输入工具名称" />
          </el-form-item>
          <el-form-item label="工具价格" prop="price">
            <el-input v-model="form.price" placeholder="请输入工具价格" />
          </el-form-item>
          <el-form-item label="工具数量" prop="number">
            <el-input v-model="form.number" placeholder="请输入工具数量" />
          </el-form-item>
          <el-form-item label="工具状态" prop="toolStatus">
            <el-select v-model="form.toolStatus" placeholder="请选择工具状态">
              <el-option
                v-for="dict in dict.type.sys_tool_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" placeholder="请输入备注" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listBizTool, getBizTool, delBizTool, addBizTool, updateBizTool } from "@/api/business/BizTool";
  //部门树相关代码1
  import { deptTreeSelect } from "@/api/system/user";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  export default {
    name: "BizTool",
    dicts: ['sys_tool_status'],
     //部门树相关代码2
    components: { Treeselect },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 工具管理表格数据
        BizToolList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,

        //部门树相关代码3
        // 部门树选项
        deptOptions: undefined,
        // 部门名称
        deptName: undefined,

        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          toolName: null,
          toolStatus: null,
          //部门树相关代码4
          deptId: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          toolName: [
            { required: true, message: "工具名称不能为空", trigger: "blur" }
          ],
          toolStatus: [
            { required: true, message: "工具状态不能为空", trigger: "change" }
          ],
        }
      };
    },
    //部门树相关代码5
    watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
      }
    },
    created() {
      this.getList();
      //部门树相关代码6
      this.getDeptTree();
    },
    methods: {
      /** 查询工具管理列表 */
      getList() {
        this.loading = true;
        listBizTool(this.queryParams).then(response => {
          this.BizToolList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      
      //部门树相关代码7
      /** 查询部门下拉树结构 */
      getDeptTree() {
        deptTreeSelect().then(response => {
          this.deptOptions = response.data;
        });
      },
      // 筛选节点
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      // 节点单击事件
      handleNodeClick(data) {
        this.queryParams.deptId = data.id;
        this.handleQuery();
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          toolId: null,
          deptId: null,
          toolName: null,
          price: null,
          number: null,
          toolStatus: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.toolId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加工具管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const toolId = row.toolId || this.ids
        getBizTool(toolId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改工具管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.toolId != null) {
              updateBizTool(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addBizTool(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const toolIds = row.toolId || this.ids;
        this.$modal.confirm('是否确认删除工具管理编号为"' + toolIds + '"的数据项？').then(function() {
          return delBizTool(toolIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('business/BizTool/export', {
          ...this.queryParams
        }, `BizTool_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  