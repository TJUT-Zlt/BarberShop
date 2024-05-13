import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";


// 查询订单管理列表
export function listBizOrder(query) {
  return request({
    url: '/business/BizOrder/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getBizOrder(orderId) {
  return request({
    url: '/business/BizOrder/' + parseStrEmpty(orderId),
    method: 'get'
  })
}

// 新增订单管理
export function addBizOrder(data) {
  return request({
    url: '/business/BizOrder',
    method: 'post',
    data: data
  })
}

// 新增没有指定客户的订单管理
export function addBizOrderWithNoBizCustomer(data) {
  return request({
    url: '/business/BizOrder/addWithNoBizCustomer',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateBizOrder(data) {
  return request({
    url: '/business/BizOrder',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delBizOrder(orderId) {
  return request({
    url: '/business/BizOrder/' + orderId,
    method: 'delete'
  })
}

// 查询客户列表
export function bizCustomerListSelect() {
  return request({
    url: '/business/BizOrder/bizCustomerList',
    method: 'get'
  })
}

// 查询客户列表
export function sysUserListSelect() {
  return request({
    url: '/business/BizOrder/sysUserList',
    method: 'get'
  })
}


// 按照日期统计营业额
export function getTurnoverStatistics(query) {
  return request({
    url: '/business/BizOrder/turnoverStatistics',
    method: 'get',
    params: query
  })
}

// 订单统计
export function getOrderStatistics(query){
  return request({
    url: '/business/BizOrder/ordersStatistics',
    method: 'get',
    params: query
  })
}

// 营业数据
export function getBusinessData(){
  return request({
      url: '/business/BizWorkSpace/businessData',
      method: 'get'
  })
}