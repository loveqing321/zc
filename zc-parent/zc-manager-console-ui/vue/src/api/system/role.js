import axios from '@/libs/http'

// 保存
export const save = (obj) => {
  return axios.request({
    url: '/api/v1/system/role/save',
    method: 'post',
    data: obj
  })
}

// 删除
export const del = ({ id }) => {
  return axios.request({
    url: '/api/v1/system/role/del',
    method: 'get',
    params: {
      id
    }
  })
}

// 根据ID查询
export const queryOne = ({ id }) => {
  return axios.request({
    url: '/api/v1/system/role/queryOne',
    method: 'get',
    params: {
      id
    }
  })
}

// 分页查询
export const queryPage = ({ pageNo, pageSize, roleName, isDeleted }) => {
  return axios.request({
    url: '/api/v1/system/role/queryPage',
    method: 'post',
    data: {
      pageNo,
      pageSize,
      roleName,
      isDeleted
    }
  })
}
