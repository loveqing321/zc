import axios from '@/libs/http'

export const logout = () => {
  return axios.request({
    url: '/logout',
    method: 'post'
  })
}
