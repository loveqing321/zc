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

// 查询所有
export const queryAll = () => {
  return axios.request({
    url: '/api/v1/system/role/queryAll',
    method: 'post'
  })
}

// 查询已经分配的权限ID
export const queryAssignedPermissionIds = ({ roleId }) => {
  return axios.request({
    url: '/api/v1/system/role/queryAssignedPermissionIds',
    method: 'get',
    params: {
      roleId
    }
  })
}

// 为角色分配权限
export const assignPermissions = ({ roleId, permissionIds }) => {
  return axios.request({
    url: '/api/v1/system/role/assignPermissions',
    method: 'post',
    data: {
      roleId,
      permissionIds
    }
  })
}
