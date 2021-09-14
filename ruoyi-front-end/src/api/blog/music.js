import request from '@/utils/request'

// 查询文章中的音乐列表
export function listMusic(query) {
  return request({
    url: '/blog/music/list',
    method: 'get',
    params: query
  })
}

// 查询文章中的音乐详细
export function getMusic(musicId) {
  return request({
    url: '/blog/music/' + musicId,
    method: 'get'
  })
}

// 新增文章中的音乐
export function addMusic(data) {
  return request({
    url: '/blog/music',
    method: 'post',
    data: data
  })
}

// 修改文章中的音乐
export function updateMusic(data) {
  return request({
    url: '/blog/music',
    method: 'put',
    data: data
  })
}

// 删除文章中的音乐
export function delMusic(musicId) {
  return request({
    url: '/blog/music/' + musicId,
    method: 'delete'
  })
}

// 导出文章中的音乐
export function exportMusic(query) {
  return request({
    url: '/blog/music/export',
    method: 'get',
    params: query
  })
}