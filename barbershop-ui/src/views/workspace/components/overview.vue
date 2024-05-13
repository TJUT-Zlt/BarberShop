<template>
  <div class="container">
    <h2 class="homeTitle">
      今日数据<i>{{ days[1] }}</i
      ><span><router-link to="statistics" style="color: red;">详细数据</router-link></span>
    </h2>
    <div class="overviewBox">
      <ul>
        <li>
          <p class="tit">总营业额</p>
          <p class="num">¥ {{ overviewData.totalTurnover }} 元</p>
        </li>
        <li>
          <p class="tit">营业额</p>
          <p class="num">¥ {{ overviewData.turnover }} 元</p>
        </li>
        <li>
          <p class="tit">订单数</p>
          <p class="num"> {{ overviewData.orderNum }} 个</p>
        </li>
        <li>
          <p class="tit">平均客单价(保留两位小数)</p>
          <p class="num">¥ {{ overviewData.unitPrice | numFilter }} 元</p>
        </li>
        <li>
          <p class="tit">新增用户</p>
          <p class="num">{{ overviewData.newBizCustomers }} 位</p>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>

import { getday } from '@/utils/formValidate'

export default{


  name: 'Overview',
  props: {
     overviewData:{
        type: null,
        required: true
     }
  },

  computed: {
    days() {
      return getday();
    },
  },

  //保留两位小数
  filters: {
    numFilter(value) {
      let realVal = "";
      if (!isNaN(value) && value !== "") {
        // 截取当前数据到小数点后两位,改变toFixed的值即可截取你想要的数值
        realVal = parseFloat(value).toFixed(2);
      } else {
        realVal = "--";
      }
      return realVal;
    },
  }

}
</script>

<style scoped>
.homeTitle {
  font-weight: 600;
  font-size: 20px;
  color: #333333;
  letter-spacing: -0.2px;
  padding: 5px 0 0;
  margin-bottom: 18px;
  i{
    font-size: 20px;
    color: red;
    padding-left: 10px;
    font-style: normal;
    font-weight:normal
  }
  span {
    float: right;
    color: #666;
    font-size: 20px;   
    font-weight: normal; 
    /* background: url(D:\ProjectRepository\BarberShop\barbershop-uisrc\assets\icons\icon_more@2x.png) no-repeat 100% 50%; */
    background-size: contain;
    padding-right: 20px;
    line-height: 16px;
  }
}
.overviewBox {
  ul {
    display: flex;
    text-align: left;
    margin-left: -20px;

    li {
      flex: 1;
      background: #8ABFF5;
      border-radius: 4px;
      margin-left: 20px;
      padding: 20px;
    }

    .tit {
      font-size: 20px;

    }

    .num {
      font-weight: Bold;
      font-size: 28px;
      line-height: 34px;
      padding: 12px 0 10px;
      color: #333;
    }

  }
}

</style>