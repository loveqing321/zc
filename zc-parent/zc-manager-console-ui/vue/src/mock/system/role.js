import Mock from 'mockjs'
import { getHttpParams } from '@/libs/util'

// 保存
const save = (req) => {
  const params = getHttpParams(req)
  console.log('save params: ' + JSON.stringify(params))
  return {
    code: 200
  }
}
Mock.mock('/api/v1/system/role/save', save)

// 删除
const del = (req) => {
  const params = getHttpParams(req)
  console.log('del params: ' + JSON.stringify(params))
  return {
    code: 200
  }
}
Mock.mock(/\/api\/v1\/system\/role\/del\?id=.+/, del)

// 根据ID查询
const queryById = (req) => {
  const params = getHttpParams(req)
  console.log('query by id params: ' + JSON.stringify(params))
  return {
    code: 200,
    data: {
      id: 1
    }
  }
}
Mock.mock(/\/api\/v1\/system\/role\/queryById\?id=.+/, queryById)

// 分页查询
const queryByPage = (req) => {
  const params = getHttpParams(req)
  console.log('query by page params: ' + JSON.stringify(params))
  return {
    code: 200,
    data: {
      total: 1,
      result: [
        { id: 1 }
      ]
    }
  }
}
Mock.mock('/api/v1/system/role/queryByPage', queryByPage)
