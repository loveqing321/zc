import axios from 'axios'
// import qs from 'qs'
import { Message } from 'element-ui'
import { getToken, clearToken, getCsrfToken } from './util'
import router from '@/router'
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
  SUCCESS: 0,
  // 用户未认证
  UNAUTHORIZED: [100, 200],
  // 用户未授权
  FORBIDDEN: [200, 300],
  // 后端异常
  ERROR: [400, 500],

  // 一些明细Code
  // 会话失效
  EXPIRED_TOKEN: 108,
  // 用户未登录
  TOKEN_NOT_FOUND: 109,

  // Http状态码
  // 页面没找到
  NOT_FOUND: 404
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
    const csrfToken = getCsrfToken()
    if (token) {
      conf.headers = {}
      conf.headers.token = token
    }
    if (csrfToken) {
      if (!conf.headers) {
        conf.headers = {}
      }
      conf.headers['X-XSRF-TOKEN'] = csrfToken
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
      if (code === STATUS_CODE.EXPIRED_TOKEN || code === STATUS_CODE.TOKEN_NOT_FOUND) {
        let msg = (code === STATUS_CODE.EXPIRED_TOKEN ? '会话失效，请重新登录' : '请先登录')
        clearToken()
        Message({ type: 'warning', message: msg })
        router.push({
          name: 'login'
        })
      } else if (code >= STATUS_CODE.FORBIDDEN[0] && code < STATUS_CODE.FORBIDDEN[1]) { // 未认证
        router.push({
          name: '403'
        })
      }
      return Promise.reject(body)
    }, error => {
      if (error.response) {
        // 如果设置了转发地址，并且返回状态为未认证，则转发到SSO
        if (error.response.status === STATUS_CODE.NOT_FOUND) {
          router.push({
            name: '404'
          })
        } else {
          Message({ type: 'error', message: '请求出错，状态码：' + error.response.status })
        }
      }
      return Promise.reject(error)
    })
    return instance.request(options)
  }
}

export default new HttpRequest()
