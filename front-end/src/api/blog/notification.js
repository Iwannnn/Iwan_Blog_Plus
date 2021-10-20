import request from '@/utils/request'

// 查询博客通知列表
export function listNotification(query) {
  return request({
    url: '/blog/notification/list',
    method: 'get',
    params: query
  })
}

// 查询博客通知详细
export function getNotification(notificationId) {
  return request({
    url: '/blog/notification/' + notificationId,
    method: 'get'
  })
}

// 新增博客通知
export function addNotification(data) {
  return request({
    url: '/blog/notification',
    method: 'post',
    data: data
  })
}

// 修改博客通知
export function updateNotification(data) {
  return request({
    url: '/blog/notification',
    method: 'put',
    data: data
  })
}

// 删除博客通知
export function delNotification(notificationId) {
  return request({
    url: '/blog/notification/' + notificationId,
    method: 'delete'
  })
}

// 导出博客通知
export function exportNotification(query) {
  return request({
    url: '/blog/notification/export',
    method: 'get',
    params: query
  })
}