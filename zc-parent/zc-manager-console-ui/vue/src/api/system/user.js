import axios from '@/libs/http'

// 保存
export const save = (obj) => {
  return axios.request({
    url: '/api/v1/system/user/save',
    method: 'post',
    data: obj
  })
}

// 删除
export const del = ({ id }) => {
  return axios.request({
    url: '/api/v1/system/user/del',
    method: 'get',
    params: {
      id
    }
  })
}

// 根据ID查询
export const queryOne = ({ username }) => {
  return axios.request({
    url: '/api/v1/system/user/queryOne',
    method: 'get',
    params: {
      username
    }
  })
}

// 分页查询
export const queryPage = ({ pageNo, pageSize, searchText, isDeleted }) => {
  return axios.request({
    url: '/api/v1/system/user/queryPage',
    method: 'post',
    data: {
      pageNo,
      pageSize,
      searchText,
      isDeleted
    }
  })
}

// 查询已经分配的角色ID
export const queryAssignedRoleIds = ({ userId }) => {
  return axios.request({
    url: '/api/v1/system/user/queryAssignedRoleIds',
    method: 'get',
    params: {
      userId
    }
  })
}

// 为用户分配角色
export const assignRoles = ({ userId, roleIds }) => {
  return axios.request({
    url: '/api/v1/system/user/assignRoles',
    method: 'post',
    data: {
      userId,
      roleIds
    }
  })
}
