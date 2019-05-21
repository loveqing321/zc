import axios from '@/libs/http'

// 获取用户许可
export const getUserPermissions = (obj) => {
  return axios.request({
    url: '/api/v1/system/permission/getUserPermissions',
    method: 'post'
  })
}

// 保存
export const save = (obj) => {
  return axios.request({
    url: '/api/v1/system/permission/save',
    method: 'post',
    data: obj
  })
}

// 删除
export const del = ({ id }) => {
  return axios.request({
    url: '/api/v1/system/permission/del',
    method: 'get',
    params: {
      id
    }
  })
}

// 根据ID查询
export const queryOne = ({ id }) => {
  return axios.request({
    url: '/api/v1/system/permission/queryOne',
    method: 'get',
    params: {
      id
    }
  })
}

// 分页查询
export const queryPage = ({ pageNo, pageSize, nameOrPerm, category, isDeleted }) => {
  return axios.request({
    url: '/api/v1/system/permission/queryPage',
    method: 'post',
    data: {
      pageNo,
      pageSize,
      nameOrPerm,
      category,
      isDeleted
    }
  })
}

// 查询所有
export const queryAll = () => {
  return axios.request({
    url: '/api/v1/system/permission/queryAll',
    method: 'post'
  })
}
