<template>
    <div class="dashboard-container home">
    <!-- 标题 -->
    <TitleIndex @sendTitleInd="getTitleNum" :flag="flag" :tateData="tateData" />
    <!-- end -->
    <div class="homeMain">
      <!-- 营业额统计 -->
      <TurnoverStatistics :turnoverdata="turnoverData" />
      <!-- 订单统计 -->
      <OrderStatistics :orderdata="orderData"/>
    </div>
    <div class="homeMain homecon">
        <!-- 用户统计 -->
        <BizCustomerStatistics :bizCustomerdata="bizCustomerData" />
    </div>
  </div>

</template>

<script >

import {get1stAndToday,past7Day,past30Day,pastWeek,pastMonth} from '@/utils/formValidate'

import {getTurnoverStatistics,getOrderStatistics} from '@/api/business/BizOrder'

import {getBizCustomerStatistics} from '@/api/business/BizCustomer'

// 标题
import TitleIndex from './components/titleIndex.vue'
// 营业额统计
import TurnoverStatistics from './components/turnoverStatistics.vue'
// 订单统计
import OrderStatistics from './components/orderStatistics.vue'
// 客户统计
import BizCustomerStatistics from './components/bizCustomerStatistics.vue'

export default{
  name: 'Dashboard',
  components: {
    TitleIndex,
    TurnoverStatistics,
    OrderStatistics,
    BizCustomerStatistics
  },
  data() {
    return {
      flag: 2,
      tateData: [],
      turnoverData: {},
      orderData: {},
      bizCustomerData:{}
    }
  },
  created() {
    this.getTitleNum(2);
  },
  methods: {
    init(begin,end) {
      this.$nextTick(() => {
        this.getTurnoverStatisticsData(begin,end)
        this.getOrderStatisticsData(begin,end)
        this.getBizCustomerStatistics(begin,end)
      })
    },
    // 获取营业额统计数据
    async getTurnoverStatisticsData(begin,end) {
      const data = await getTurnoverStatistics({ begin: begin,end:end })
      const turnoverData = data.data
      this.turnoverData = {
        dateList: turnoverData.dateList.split(','),
        turnoverList: turnoverData.turnoverList.split(',')
      }
    },

    // 获取订单统计数据
    async getOrderStatisticsData(begin,end) {
      const data = await getOrderStatistics({begin: begin,end:end })
      const orderData = data.data
      this.orderData = {
          dateList: orderData.dateList.split(','),
          orderCountList: orderData.orderCountList.split(','),
          totalOrderCount: orderData.totalOrderCount
      }
    },

    // 获取客户统计数据
    async getBizCustomerStatistics(begin,end) {
      const data = await getBizCustomerStatistics({ begin: begin,end:end })
      const bizCustomerData = data.data
      this.bizCustomerData = {
        dateList: bizCustomerData.dateList.split(','),
        totalBizCustomerList: bizCustomerData.totalBizCustomerList.split(','),
        newBizCustomerList: bizCustomerData.newBizCustomerList.split(','),
      }
    },

    getTitleNum(data) {
      switch (data) {
        case 1:
          this.tateData = get1stAndToday()
          break
        case 2:
          this.tateData = past7Day()
          break
        case 3:
          this.tateData = past30Day()
          break
        case 4:
          this.tateData = pastWeek()
          break
        case 5:
          this.tateData = pastMonth()
          break
      }
      this.init(this.tateData[0],this.tateData[1])
    },

  },
}
</script>

<style>

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

    &:last-child {
      margin-left: 20px;
    }
  }
}

</style>