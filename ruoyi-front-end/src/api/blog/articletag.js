import request from '@/utils/request'

// 查询文章标签列表
export function listArticletag(query) {
  return request({
    url: '/blog/articletag/list',
    method: 'get',
    params: query
  })
}

// 查询文章标签详细
export function getArticletag(id) {
  return request({
    url: '/blog/articletag/' + id,
    method: 'get'
  })
}

// 新增文章标签
export function addArticletag(data) {
  return request({
    url: '/blog/articletag',
    method: 'post',
    data: data
  })
}

// 修改文章标签
export function updateArticletag(data) {
  return request({
    url: '/blog/articletag',
    method: 'put',
    data: data
  })
}

// 删除文章标签
export function delArticletag(id) {
  return request({
    url: '/blog/articletag/' + id,
    method: 'delete'
  })
}

// 导出文章标签
export function exportArticletag(query) {
  return request({
    url: '/blog/articletag/export',
    method: 'get',
    params: query
  })
}