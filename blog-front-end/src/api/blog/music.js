import request from '@/utils/request'

// 查询博客音乐列表
export function listMusic() {
	return request({
		url: '/blog/music/listMusic',
		method: 'get',
	})
}

// 查询博客音乐详细
export function getMusic(musicId) {
	return request({
		url: '/blog/music/' + musicId,
		method: 'get'
	})
}