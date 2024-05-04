export const worldHolidays = [
    { month: 1, day: 1, name: '元旦' }, // New Year's Day
    { month: 2, day: 14, name: '情人节' }, // Valentine's Day
    { month: 3, day: 8, name: '妇女节' }, // International Women's Day
    { month: 4, day: 1, name: '愚人节' }, // April Fools' Day
    { month: 4, day: 22, name: '地球日' }, // Earth Day
    { month: 5, day: 1, name: '劳动节' }, // International Workers' Day
    { month: 12, day: 25, name: '圣诞节' }, // Christmas Day
]

// date原型链对象添加format方法，用于format日期格式
Date.prototype.Format = function (fmt) {
    var o = {
      "M+": this.getMonth() + 1, // 月份
      "d+": this.getDate(), // 日
      "h+": this.getHours(), // 小时
      "m+": this.getMinutes(), // 分
      "s+": this.getSeconds(), // 秒
      "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
      S: this.getMilliseconds(), // 毫秒
    }
  
    if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
    }
    for (var k in o) {
      if (new RegExp("(" + k + ")").test(fmt)) {
        fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length))
      }
    }
  
    return fmt
  }
  
  // Date对象Transfer对象，将时间戳转换成日期对象
  Date.Transfer = function (timeSpan) {
    if (!timeSpan) {
      return new FormatDateNullValue()
    }
  
    return new Date(timeSpan)
  }
  