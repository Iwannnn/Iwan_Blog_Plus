import request from '@/utils/request'

export function addImg(data) {
	return request({
		url: '/api/upload/file',
		method: 'post',
		data: data,
		headers: {
			'Content-Type': 'multipart/form-data'
		}
	})
}