import request from '@/utils/request'

// 查询用户点赞管理列表
export function listLike(query) {
  return request({
    url: '/blog/like/list',
    method: 'get',
    params: query
  })
}

// 查询用户点赞管理详细
export function getLike(id) {
  return request({
    url: '/blog/like/' + id,
    method: 'get'
  })
}

// 新增用户点赞管理
export function addLike(data) {
  return request({
    url: '/blog/like',
    method: 'post',
    data: data
  })
}

// 修改用户点赞管理
export function updateLike(data) {
  return request({
    url: '/blog/like',
    method: 'put',
    data: data
  })
}

// 删除用户点赞管理
export function delLike(id) {
  return request({
    url: '/blog/like/' + id,
    method: 'delete'
  })
}

// 导出用户点赞管理
export function exportLike(query) {
  return request({
    url: '/blog/like/export',
    method: 'get',
    params: query
  })
}