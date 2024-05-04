<template>
  <div class="container">
    <h2 class="homeTitle">营业额统计</h2>
    <div class="charBox">
      <div id="main" style="width: 750px; height: 320px"></div>
      <ul class="orderListLine turnover">
        <li>营业额(元)</li>
      </ul>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default{
  name: 'TurnoverStatistics',
  props: {
    turnoverdata: {
      type: null,
      required: true
    }
  },

  watch: {
    turnoverdata() {
      this.$nextTick(() => {
        this.initChart()
      })
    }
  },
  methods: {
    initChart() {
      const EChartsOption = echarts.EChartsOption
      const chartDom = document.getElementById('main')
      const myChart = echarts.init(chartDom)
      var option
      option = {
        tooltip: {
          trigger: 'axis',
        },
        grid: {
          top: '5%',
          left: '10',
          right: '50',
          bottom: '12%',
          containLabel: true,
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            textStyle: {
              color: '#666',
              fontSize: '12px',
            },
          },
          axisLine: {
            lineStyle: {
              color: '#E5E4E4',
              width: 1,
            },
          },
          data: this.turnoverdata.dateList,
        },
        yAxis: [
          {
            type: 'value',
            min: 0,
            axisLabel: {
              textStyle: {
                color: '#666',
                fontSize: '12px',
              }
            }
          }
        ],
        series: [
          {
            name: '营业额',
            type: 'line',
            smooth: false,
            showSymbol: false,
            symbolSize: 10,
            itemStyle: {
              normal: {
                color: '#F29C1B',
                lineStyle: {
                  color: '#FFD000',
                },
              },
              emphasis: {
                color: '#fff',
                borderWidth: 5,
                borderColor: '#FFC100',
              },
            },
            data: this.turnoverdata.turnoverList,
          },
        ],
      }
      option && myChart.setOption(option)
    }
  },
}
</script>
