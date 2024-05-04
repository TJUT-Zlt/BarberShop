import request from '@/utils/request'

// 查询工具管理列表
export function listBizTool(query) {
  return request({
    url: '/business/BizTool/list',
    method: 'get',
    params: query
  })
}

// 查询工具管理详细
export function getBizTool(toolId) {
  return request({
    url: '/business/BizTool/' + toolId,
    method: 'get'
  })
}

// 新增工具管理
export function addBizTool(data) {
  return request({
    url: '/business/BizTool',
    method: 'post',
    data: data
  })
}

// 修改工具管理
export function updateBizTool(data) {
  return request({
    url: '/business/BizTool',
    method: 'put',
    data: data
  })
}

// 删除工具管理
export function delBizTool(toolId) {
  return request({
    url: '/business/BizTool/' + toolId,
    method: 'delete'
  })
}
