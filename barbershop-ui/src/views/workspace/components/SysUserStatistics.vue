<template>
  <div class="container">
    <h2 class="homeTitle">
      员工总览<span><router-link to="SysUser">员工管理</router-link></span>
    </h2>
    <div class="orderviewBox">
      <ul>
        <li>
          <span class="status"><i class="iconfont icon-open"></i>在岗员工数量</span>
          <span class="num">{{ sysUserData.obDutySysUsers}}位</span>
        </li>
        <li>
          <span class="status"><i class="iconfont icon-stop"></i>休假员工数量</span>
          <span class="num">{{ sysUserData.onLeaveSysUsers }}位</span>
        </li>
        <li>
          <span class="status"><i class="iconfont icon-stop"></i>出差员工数量</span>
          <span class="num">{{ sysUserData.businessSysUsers }}位</span>
        </li>
        <li>
          <span class="status"><i class="iconfont icon-stop"></i>离职员工数量</span>
          <span class="num">{{ sysUserData.departSysUsers }}位</span>
        </li>
        <li class="add">
          <router-link to="/BizCustomer/add">
            <i></i>
            <p>新增员工</p>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SysUserView',

  props: {
    sysUserData: {
      type: null,
      required: true
    }
  
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
    color: #666666;
    padding-left: 10px;
    font-style: normal;
    font-weight:normal
  }
  span {
    float: right;
    color: #666;
    font-size: 20px;
    font-weight: normal;
    /* background: url(src/assets/icons/btn_add@2x.png) no-repeat 100% 50%; */
    background-size: contain;
    padding-right: 20px;
    line-height: 16px;
  }
}
.orderviewBox {
  ul {
    display: flex;
    text-align: left;
    margin-left: -20px;

    li {
      flex: 1;
      background: #fffbf0;
      border-radius: 4px;
      margin-left: 20px;
      padding:20px;
      font-size: 20px;
      line-height: 36px;
      color: #333;
      &.add {
        width: 100px;
        flex: none;
        background: #FFC100;
        border-radius: 4px;
        text-align: center;
        color: #333;
        padding: 18px 20px 10px;
        i {
          display: block;
          width: 22px;
          height: 22px;
          margin: 0 auto;
          /* background: src="D:\ProjectRepository\BarberShop\barbershop-ui\src\assets\icons\btn_add@2x.png" no-repeat; */
          background-size: contain;
          margin-bottom: 5px;
        }
      }
    }

    .status {
      vertical-align: middle;
      line-height: 36px;
      i {
        display: inline-block;
    
        vertical-align: middle;
        margin-right: 4px;
      }
    }
    .num {
      font-weight: Bold;
      font-size: 28px;
      float: right;
    }
  }

}


</style>