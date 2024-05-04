<template>
  <div class="container">
    <h2 class="homeTitle">订单统计</h2>
    <div class="charBox">
      <div id="ordermain" style="width: 750px; height: 320px"></div>
      <ul class="orderListLine order">
        <li>订单数量(个)</li>
      </ul>
      <!-- <div>总订单数:{{orderdata.totalOrderCount}}</div> -->
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'OrderStatistics',
  props: {
    orderdata: {
      type: null,
      required: true
    }
  },
  watch: {
    orderdata() {
      this.$nextTick(() => {
        this.initChart()
      })
    }
  },
  methods: {
    initChart() {
      const EChartsOption = echarts.EChartsOption
      const chartDom = document.getElementById('ordermain')
      const myChart = echarts.init(chartDom)
      var option
      option = {
        tooltip: {
          trigger: 'axis',
        },
        grid: {
          top: '5%',
          left: '20',
          right: '50',
          bottom: '12%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            textStyle: {
              color: '#666',
              fontSize: '12px'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#E5E4E4',
              width: 1
            }
          },
          data: this.orderdata.dateList
        },
        yAxis: [
          {
            type: 'value',
            min: 0,
            interval: 50,
            axisLabel: {
              textStyle: {
                color: '#666',
                fontSize: '12px'
              }
            }
          }
        ],
        series: [
          {
            name: '订单总数',
            type: 'line',
            smooth: false,
            showSymbol: false,
            symbolSize: 10,
            itemStyle: {
              normal: {
                color: '#FFD000',
                lineStyle: {
                  color: '#FFD000'
                }
              },
              emphasis: {
                color: '#fff',
                borderWidth: 5,
                borderColor: '#FFC100'
              }
            },
            data: this.orderdata.orderCountList
          },
          
        ]
      }
      option && myChart.setOption(option)
    }
  }
}
</script>
