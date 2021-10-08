import request from '@/utils/request'

// 查询博客用户列表
export function listUser(query) {
	return request({
		url: '/blog/user/list',
		method: 'get',
		params: query
	})
}

// 查询博客用户详细
export function getUser(userId) {
	return request({
		url: '/blog/user/' + userId,
		method: 'get'
	})
}

// 新增博客用户
export function addUser(data) {
	return request({
		url: '/blog/user',
		method: 'post',
		data: data
	})
}

// 修改博客用户
export function updateUser(data) {
	return request({
		url: '/blog/user',
		method: 'put',
		data: data
	})
}

// 删除博客用户
export function delUser(userId) {
	return request({
		url: '/blog/user/' + userId,
		method: 'delete'
	})
}

// 导出博客用户
export function exportUser(query) {
	return request({
		url: '/blog/user/export',
		method: 'get',
		params: query
	})
}

// 获取用户名和id
export function getUserId() {
	return request({
		url: '/blog/user/getUserId',
		method: 'get'
	})
}