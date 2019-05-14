import axios from '@/libs/http'

export const getUserPermissions = () => {
  return axios.request({
    url: '/api/v1/system/permission/getUserPermissions',
    method: 'post'
  })
}
