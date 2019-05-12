import axios from '@/libs/http'

export const getUserPermissions = () => {
  return axios.request({
    url: '/api/permission/getUserPermissions',
    method: 'post'
  })
}
