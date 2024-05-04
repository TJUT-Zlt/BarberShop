<template>
  <div class="title-index">
    <div class="month">
      <ul class="tabs">
        <li
          class="li-tab"
          v-for="(item, index) in tabsParam"
          @click="toggleTabs(index)"
          :class="{ active: index === nowIndex }"
          :key="index"
        >
          {{ item }}
          <span></span>
        </li>
      </ul>
    </div>
    <div class="get-time">
      <p>
        已选时间：{{ tateData[0] }} 至
        {{ tateData[tateData.length - 1] }}
      </p>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'TitleIndex',
    props: {
      flag: {
        type: null,
        required: true
      },
      tateData: {
        type: null,
        required: true
      },
      turnoverData: {
        type: null,
        required: true
      }
    },
    data() {
      return {
        nowIndex: 2 - 1,
        value: [],
        tabsParam: ['昨日', '近7日', '近30日', '本周', '本月']
      }
    },
    watch: {
      flag(val) {
        this.getNowIndex(val)
      }
    },
    methods: {
      getNowIndex(val) {
        this.nowIndex = val
      },
      toggleTabs(index) {
        this.nowIndex = index
        this.value = []
        this.$emit('sendTitleInd', index + 1)
      }
    }
  }
</script>
<style scoped>
.title-index {
  .month {
    box-sizing: border-box;
    display: inline-block;
    background-color: #fff;

    ul {
      width: 400px;
      height: 40px;
      line-height: 38px;
      border: 1px solid #e5e4e4;
      border-radius: 4px;
      padding: 0;
      display: flex;
      text-align: center;
      margin: 0;

      li {
        position: relative;
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
        text-align: center;
        border-right: 1px solid #e5e4e4;
        color: #333333;
        letter-spacing: -0.2px;
        flex: 1;
      }

      li:last-child {
        border: 0 none;
      }

      .active {
        height: 38px;
        background: #11A983;
      }
    }
  }

  .get-time {
    display: inline-block;
    padding-left: 15px;
    font-size: 14px;
    color: #666;
  }
}
</style>