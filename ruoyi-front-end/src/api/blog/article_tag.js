import request from '@/utils/request'

// 查询文章标签列表
export function listArticle_tag(query) {
  return request({
    url: '/blog/article_tag/list',
    method: 'get',
    params: query
  })
}

// 查询文章标签详细
export function getArticle_tag(id) {
  return request({
    url: '/blog/article_tag/' + id,
    method: 'get'
  })
}

// 新增文章标签
export function addArticle_tag(data) {
  return request({
    url: '/blog/article_tag',
    method: 'post',
    data: data
  })
}

// 修改文章标签
export function updateArticle_tag(data) {
  return request({
    url: '/blog/article_tag',
    method: 'put',
    data: data
  })
}

// 删除文章标签
export function delArticle_tag(id) {
  return request({
    url: '/blog/article_tag/' + id,
    method: 'delete'
  })
}

// 导出文章标签
export function exportArticle_tag(query) {
  return request({
    url: '/blog/article_tag/export',
    method: 'get',
    params: query
  })
}