import axios from '@/libs/http'

export const getUserDetail = () => {
  return axios.request({
    url: '/user/getUserDetail',
    method: 'post'
  })
}
