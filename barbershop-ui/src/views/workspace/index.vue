<template>
  <div class="dashboard-container home">
    <!-- 营业数据 -->
    <Overview :overviewData="overviewData" />

    <div class="homeMain">
       <!-- 客户总览 -->
       <BizCustomerStatistics :bizCustomerData="bizCustomerData" />
        <!-- 工具总览 -->
      <BizToolStatistics :bizToolData="bizToolData" />
    </div>
    <div class="homeMain">
        <!-- 员工总览 -->
        <SysUserStatistics :sysUserData="sysUserData" />
    </div>
  </div>
</template>

<script>

import { getBusinessData } from '@/api/business/BizOrder'
import { getOverviewBizCustomer } from '@/api/business/BizCustomer'
import { getOverviewSysUser } from '@/api/system/user'
import { getOverviewBizTool } from '@/api/business/BizTool'


// 营业数据
import Overview from './components/overview.vue'
// 客户总览
import BizCustomerStatistics from './components/BizCustomerStatistics.vue'
//用户总览
import SysUserStatistics from './components/SysUserStatistics.vue'
//工具总览
import BizToolStatistics from './components/BizToolStatistics.vue'

export default {
  name: 'workspace',
  components: {
    Overview,
    BizCustomerStatistics,
    SysUserStatistics,
    BizToolStatistics
  },
  data() {
    return {
      overviewData: {},   
      bizCustomerData: {},
      sysUserData: {},
      bizToolData: {}
    }
  },
  created() {
    this.init()
  },
  methods: {
    async init() {
      this.getBusinessData()
      this.getOverviewBizCustomer()
      this.getOverviewSysUser()
      this.getOverviewBizTool()
    },
    // 获取营业数据
    async getBusinessData() {
      const data = await getBusinessData()
      this.overviewData = data.data
      console.log(data)
    },
    
    // 获取客户总览数据
    async getOverviewBizCustomer() {
      const data = await getOverviewBizCustomer()
      this.bizCustomerData = data.data
    },

    // 获取员工总览数据
    async getOverviewSysUser() {
    const data = await getOverviewSysUser()
    this.sysUserData = data.data
    },

    // 获取工具总览数据
    async getOverviewBizTool() {
    const data = await getOverviewBizTool()
    this.bizToolData = data.data
    }

  },
}
</script>

<style scoped>
.home {
  .container {
    padding: 20px;
  }
}
.dashboard-container.home{
  .homeMain {
    &.homecon{
      .container {
        margin-bottom: 0;
      }
    }
  }
}
.homeMain {
  display: flex;
  .container {
    flex: 1;
  }
}
</style>
