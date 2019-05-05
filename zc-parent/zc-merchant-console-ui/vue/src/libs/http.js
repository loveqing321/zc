import axios from 'axios'
// import qs from 'qs'
import { Message } from 'element-ui'
import { getToken } from './util'
import env from './env'

/**
 * 后端返回响应结构
 * {
 *     code: 状态码  要和后端对应
 *     msg: 提示信息
 *     data: 成功返回的数据
 * }
 * @type {{SUCCESS: number, UNAUTHORIZED: number, FORBIDDEN: number, NOT_FOUND: number, ERROR: number}}
 */
const STATUS_CODE = {
  // 成功
  SUCCESS: 200,
  // 用户未认证
  UNAUTHORIZED: 401,
  // 用户未授权
  FORBIDDEN: 403,
  // 页面没找到
  NOT_FOUND: 404,
  // 后端异常
  ERROR: 1
}

// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
// 后端使用@RequestBody来接收请求参数
axios.defaults.headers.post['Content-Type'] = 'application/json'
// 支持跨域发送请求头
axios.defaults.withCredentials = true

// 封装请求对象
class HttpRequest {
  // 请求方法
  request (options) {
    const conf = {}
    const token = getToken()
    if (token) {
      conf.headers = {}
      conf.headers.token = token
    }
    // 创建请求实例
    let instance = axios.create(conf)
    // 请求拦截
    instance.interceptors.request.use(config => {
      // 如果指定了http开头的，那么不设置baseURL
      if (config.url && config.url.startsWith('http://')) {
        return config
      }
      // 对url增加前缀
      config.url = env.baseURL + config.url
      return config
    })

    // 响应拦截
    instance.interceptors.response.use(resp => {
      const body = resp.data
      const { code, data } = body

      // 分析响应的数据做通用处理
      if (code === STATUS_CODE.SUCCESS) {
        return data
      }
      Message({ type: 'error', message: data || 'error' })
      return Promise.reject(new Error(data || 'error'))
    }, error => {
      if (error.response) {
        // 如果设置了转发地址，并且返回状态为未认证，则转发到SSO
        if (error.response.status === STATUS_CODE.UNAUTHORIZED && env.redirectURL) {
          // window.location.href = 'http://sso.hellobike.cn/?redirect_url=' + encodeURIComponent(env.redirectURL)
        } else {
          console.error(error.response)
        }
      }
      return Promise.reject(error)
    })
    return instance.request(options)
  }
}

export default new HttpRequest()
