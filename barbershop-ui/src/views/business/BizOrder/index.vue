<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="服务人员名称" prop="userId">
         <el-select v-model="queryParams.userId" placeholder="请选择服务人员名称" clearable>
              <el-option
                v-for="item in sysUserOptions"
                :key="item.userId"
                :label="item.nickName"
                :value="item.userId"
              ></el-option>
          </el-select>
      </el-form-item>

      <el-form-item label="客户昵称" prop="customerId">
         <el-select v-model="queryParams.customerId" placeholder="请选择客户名称" clearable>
              <el-option
                v-for="item in bizCustomerOptions"
                :key="item.customerId"
                :label="item.nickName"
                :value="item.customerId"
              ></el-option>
          </el-select>
      </el-form-item>

      <el-form-item label="服务类型" prop="orderType">
        <el-select v-model="queryParams.orderType" placeholder="请选择服务类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_service_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="创建时间">
            <el-date-picker
              v-model="dateRange"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
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
          v-hasPermi="['business:BizOrder:add']"
        >新增(系统内已存在/或不存在客户)</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAddWithNoBizCustomer"
          v-hasPermi="['business:BizOrder:addWithNoBizCustomer']"
        >新增(系统内不存在客户)</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:BizOrder:edit']"
        >修改(不允许修改客户信息)</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:BizOrder:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:BizOrder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="BizOrderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务订单ID" align="center" prop="orderId" />
      <el-table-column label="服务人员ID" align="center" prop="userId" />
      <el-table-column label="服务人员昵称" align="center" prop="sysUser.nickName" />
      <el-table-column label="客户ID" align="center" prop="customerId" />
      <el-table-column label="客户昵称" align="center" prop="bizCustomer.nickName" />
      <el-table-column label="服务类型" align="center" prop="orderType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_service_type" :value="scope.row.orderType"/>
        </template>
      </el-table-column>
      <el-table-column label="服务价格" align="center" prop="orderPrice" />
      
      <el-table-column label="创建时间" align="center" prop="createTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
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
            v-hasPermi="['business:BizOrder:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:BizOrder:remove']"
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

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
          <el-form-item label="服务人员名称">
              <el-select v-model="form.userId" placeholder="请选择服务人员名称">
                <el-option
                  v-for="item in sysUserOptions"
                  :key="item.userId"
                  :label="item.nickName"
                  :value="item.userId"
                ></el-option>
              </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="服务人员ID" prop="userId">
            <el-input v-model="form.userId" placeholder="请输入服务人员ID" />
          </el-form-item>
        </el-col>
      </el-row>

       <el-row>
        <el-col :span="12">
        <el-form-item label="客户名称">
            <el-select v-model="form.customerId" placeholder="请选择客户名称">
              <el-option
                v-for="item in bizCustomerOptions"
                :key="item.customerId"
                :label="item.nickName"
                :value="item.customerId"
              ></el-option>
            </el-select>
        </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="客户ID" prop="customerId">
            <el-input v-model="form.customerId" placeholder="请输入客户ID" />
          </el-form-item>
       </el-col>
      </el-row>

        <el-form-item label="服务类型" prop="orderType">
          <el-select v-model="form.orderType" placeholder="请选择服务类型">
            <el-option
              v-for="dict in dict.type.sys_service_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="服务价格" prop="orderPrice">
          <el-input v-model="form.orderPrice" placeholder="请输入服务价格" />
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
import { listBizOrder, getBizOrder, delBizOrder, addBizOrder,addBizOrderWithNoBizCustomer, updateBizOrder,bizCustomerListSelect,sysUserListSelect } from "@/api/business/BizOrder";

export default {
  name: "BizOrder",
  dicts: ['sys_service_type'],
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
      // 订单管理表格数据
      BizOrderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      // 日期范围
      dateRange: [],

      //客户选项
      bizCustomerOptions:[],
      //用户选项
      sysUserOptions:[],


      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        customerId: null,
        orderType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "服务人员ID不能为空", trigger: "blur" }
        ],
        // customerId: [
        //   { required: true, message: "客户ID不能为空", trigger: "blur" }
        // ],
        orderPrice: [
          { required: true, message: "服务价格不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getBizCustomerList();
    this.getSysUserListSelect();
  },
  methods: {
    /** 查询客户列表 */
    getBizCustomerList() {
      bizCustomerListSelect().then(response => {
        this.bizCustomerOptions = response.data;
      });
    },
    /**查询用户列表 */  
    getSysUserListSelect() {
      sysUserListSelect().then(response => {
        //嵌套
        this.sysUserOptions = response.data.data;
      });
    },
    /** 查询订单管理列表 */
    getList() {
      this.loading = true;
      listBizOrder(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.BizOrderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderId: null,
        userId: null,
        customerId: null,
        orderType: null,
        orderPrice: null,
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getBizOrder().then(response => {
        this.open = true;
        this.title = "添加订单(系统内已存在客户)";
      });
    },
    /** 新增(系统内不存在客户)按钮操作 */
    handleAddWithNoBizCustomer() {
      this.reset();
      getBizOrder().then(response => {
        this.open = true;
        this.title = "添加订单(系统内不存在客户)";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getBizOrder(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单(不允许修改客户信息)";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateBizOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            if(this.form.customerId != null){
              addBizOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增(系统内已存在客户)成功");
              this.open = false;
              this.getList();
              });
            }else{
              addBizOrderWithNoBizCustomer(this.form).then(response => {
              this.$modal.msgSuccess("新增(系统内不存在客户)成功");
              this.open = false;
              this.getList();
              });
            }
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除订单管理编号为"' + orderIds + '"的数据项？').then(function() {
        return delBizOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/BizOrder/export', {
        ...this.queryParams
      }, `BizOrder_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>