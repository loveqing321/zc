import axios from '@/libs/http'

export const queryPost = ({ param1, param2 }) => {
  return axios.request({
    url: '/api/...',
    method: 'post',
    data: {
      param1,
      param2
    }
  })
}

export const queryGet = ({ param1, param2 }) => {
  return axios.request({
    url: '/api/actuator/buildSql',
    method: 'post',
    params: {
      param1,
      param2
    }
  })
}
