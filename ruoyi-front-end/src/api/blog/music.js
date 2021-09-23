import request from '@/utils/request'

// 查询博客音乐列表
export function listMusic(query) {
	return request({
		url: '/blog/music/list',
		method: 'get',
		params: query
	})
}

// 查询博客音乐详细
export function getMusic(musicId) {
	return request({
		url: '/blog/music/' + musicId,
		method: 'get'
	})
}

// 新增博客音乐
export function addMusic(data) {
	return request({
		url: '/blog/music',
		method: 'post',
		data: data
	})
}

// 修改博客音乐
export function updateMusic(data) {
	return request({
		url: '/blog/music',
		method: 'put',
		data: data
	})
}

// 删除博客音乐
export function delMusic(musicId) {
	return request({
		url: '/blog/music/' + musicId,
		method: 'delete'
	})
}

// 导出博客音乐
export function exportMusic(query) {
	return request({
		url: '/blog/music/export',
		method: 'get',
		params: query
	})
}

// 获取音乐名和id
export function getMusicId() {
	return request({
		url: '/blog/music/getMusicId',
		method: 'get'
	})
}