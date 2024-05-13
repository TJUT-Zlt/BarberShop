<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户账号" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phonenumber">
        <el-input
          v-model="queryParams.phonenumber"
          placeholder="请输入手机号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['business:BizCustomer:add']"
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
          v-hasPermi="['business:BizCustomer:edit']"
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
          v-hasPermi="['business:BizCustomer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:BizCustomer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="BizCustomerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户ID" align="center" prop="customerId" />

      <el-table-column label="客户办卡门店" align="center" prop="customerDeptId">
        <!-- 待完善 -->
        <!-- <template slot-scope="scope">
            <span :options="deptOptions" :value="scope.row.customerDeptId"></span>
        </template> -->
      </el-table-column>

      <el-table-column label="客户账号" align="center" prop="customerName" />
      <el-table-column label="昵称" align="center" prop="nickName" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="生日" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号余额" align="center" prop="customerAccountBalance" />
      <el-table-column label="累计消费" align="center" prop="cumulativeConsumption" />
      <el-table-column label="偏好" align="center" prop="perferences" />
      <!-- <el-table-column label="邮箱" align="center" prop="email" /> -->
      <el-table-column label="手机号码" align="center" prop="phonenumber" />
      <el-table-column label="账号状态" align="center" prop="customerAccountStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.customerAccountStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="客户账号等级" align="center" prop="customerAccountLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_customer_level" :value="scope.row.customerAccountLevel"/>
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
            v-hasPermi="['business:BizCustomer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:BizCustomer:remove']"
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

    <!-- 添加或修改客户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户办卡门店" prop="customerDeptId">
            <treeselect v-model="form.customerDeptId" :options="deptOptions" :show-count="true" placeholder="请选择客户办卡门店" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
        <el-form-item label="客户账号" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户账号" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入昵称" />
        </el-form-item>
      </el-col>
    </el-row>
        <el-row>
          <el-col :span="12">
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
      </el-col>
      </el-row>
   
        <el-form-item label="生日" prop="birthday">
          <el-date-picker clearable
            v-model="form.birthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生日">
          </el-date-picker>
        </el-form-item>
      
        <el-form-item label="账号余额" prop="customerAccountBalance">
          <el-input v-model="form.customerAccountBalance" placeholder="请输入账号余额" />
        </el-form-item>
      
        <el-form-item label="偏好" prop="perferences">
          <el-input v-model="form.perferences" placeholder="请输入偏好" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phonenumber">
          <el-input v-model="form.phonenumber" placeholder="请输入手机号码" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
        <el-form-item label="账号状态" prop="customerAccountStatus">
          <el-select v-model="form.customerAccountStatus" placeholder="请选择账号状态">
            <el-option
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="客户账号等级" prop="customerAccountLevel">
          <el-select v-model="form.customerAccountLevel" placeholder="请选择客户账号等级">
            <el-option
              v-for="dict in dict.type.sys_customer_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
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
import { listBizCustomer, getBizCustomer, delBizCustomer, addBizCustomer, updateBizCustomer } from "@/api/business/BizCustomer";

//部门树相关代码1
import { deptTreeSelect } from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "BizCustomer",
  dicts: ['sys_customer_level', 'sys_user_sex', 'sys_normal_disable'],

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
      // 客户管理表格数据
      BizCustomerList: [],
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
        customerName: null,
        nickName: null,
        phonenumber: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customerName: [
          { required: true, message: "客户账号不能为空", trigger: "blur" }
        ],
        nickName: [
          { required: true, message: "昵称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    ////部门树相关代码4
    this.getDeptTree();
  },
  methods: {
    /** 查询客户管理列表 */
    getList() {
      this.loading = true;
      listBizCustomer(this.queryParams).then(response => {
        this.BizCustomerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //部门树相关代码5
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
        customerId: null,
        customerDeptId: null,
        customerName: null,
        nickName: null,
        sex: null,
        age: null,
        birthday: null,
        customerAccountBalance: null,
        cumulativeConsumption: null,
        perferences: null,
        email: null,
        phonenumber: null,
        customerAccountStatus: null,
        customerAccountLevel: null,
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
      this.ids = selection.map(item => item.customerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const customerId = row.customerId || this.ids
      getBizCustomer(customerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.customerId != null) {
            updateBizCustomer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBizCustomer(this.form).then(response => {
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
      const customerIds = row.customerId || this.ids;
      this.$modal.confirm('是否确认删除客户管理编号为"' + customerIds + '"的数据项？').then(function() {
        return delBizCustomer(customerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/BizCustomer/export', {
        ...this.queryParams
      }, `BizCustomer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
