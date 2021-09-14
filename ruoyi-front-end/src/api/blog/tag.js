import request from '@/utils/request'

// 查询文章的标签列表
export function listTag(query) {
  return request({
    url: '/blog/tag/list',
    method: 'get',
    params: query
  })
}

// 查询文章的标签详细
export function getTag(tagId) {
  return request({
    url: '/blog/tag/' + tagId,
    method: 'get'
  })
}

// 新增文章的标签
export function addTag(data) {
  return request({
    url: '/blog/tag',
    method: 'post',
    data: data
  })
}

// 修改文章的标签
export function updateTag(data) {
  return request({
    url: '/blog/tag',
    method: 'put',
    data: data
  })
}

// 删除文章的标签
export function delTag(tagId) {
  return request({
    url: '/blog/tag/' + tagId,
    method: 'delete'
  })
}

// 导出文章的标签
export function exportTag(query) {
  return request({
    url: '/blog/tag/export',
    method: 'get',
    params: query
  })
}