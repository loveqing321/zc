import axios from '@/libs/http'

// 保存
export const save = (obj) => {
  return axios.request({
    url: '/api/v1/system/dict/save',
    method: 'post',
    data: obj
  })
}

// 删除
export const del = ({ id }) => {
  return axios.request({
    url: '/api/v1/system/dict/del',
    method: 'get',
    params: {
      id
    }
  })
}

// 根据ID查询
export const queryOne = ({ id }) => {
  return axios.request({
    url: '/api/v1/system/dict/queryOne',
    method: 'get',
    params: {
      id
    }
  })
}

// 分页查询
export const queryPage = ({ pageNo, pageSize }) => {
  return axios.request({
    url: '/api/v1/system/dict/queryPage',
    method: 'post',
    data: {
      pageNo,
      pageSize
    }
  })
}
