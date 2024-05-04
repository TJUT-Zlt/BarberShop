import request from '@/utils/request'

// 查询客户管理列表
export function listBizCustomer(query) {
  return request({
    url: '/business/BizCustomer/list',
    method: 'get',
    params: query
  })
}

// 查询客户管理详细
export function getBizCustomer(customerId) {
  return request({
    url: '/business/BizCustomer/' + customerId,
    method: 'get'
  })
}

// 新增客户管理
export function addBizCustomer(data) {
  return request({
    url: '/business/BizCustomer',
    method: 'post',
    data: data
  })
}

// 修改客户管理
export function updateBizCustomer(data) {
  return request({
    url: '/business/BizCustomer',
    method: 'put',
    data: data
  })
}

// 删除客户管理
export function delBizCustomer(customerId) {
  return request({
    url: '/business/BizCustomer/' + customerId,
    method: 'delete'
  })
}

// 客户统计
export function getBizCustomerStatistics(query){
  return request({
    url: '/business/BizCustomer/bizCustomerStatistics',
    method: 'get',
    params: query
  })
}

// 客户总览
export function getOverviewBizCustomer(){
    return request({
      url: '/business/BizWorkSpace/overviewBizCustomer',
      method: 'get'
  })
}