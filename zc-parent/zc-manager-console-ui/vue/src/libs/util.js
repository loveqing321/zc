import Cookies from 'js-cookie'
import qs from 'qs'

// token名称
export const TOKEN_KEY = 'token'
// token超时
const TOKEN_EXPIRES = 7

export const CSRF_TOKEN_KEY = 'XSRF-TOKEN'

// 设置token
export const setToken = (token) => {
  Cookies.set(TOKEN_KEY, token, { expires: TOKEN_EXPIRES })
}

// 获取token
export const getToken = () => {
  return Cookies.get(TOKEN_KEY)
}

export const clearToken = () => {
  Cookies.remove(TOKEN_KEY)
}

export const getCsrfToken = () => {
  return Cookies.get(CSRF_TOKEN_KEY)
}

export const clearCsrfToken = () => {
  return Cookies.remove(CSRF_TOKEN_KEY)
}

// 设置用户信息
export const setUserInfo = (principal) => {
  localStorage.setItem('principal', JSON.stringify(principal))
}

// 获取用户信息
export const getUserInfo = () => {
  const principal = localStorage.getItem('principal')
  if (principal) {
    return JSON.parse(principal)
  }
  return null
}

// 清除用户信息
export const clearUserInfo = () => {
  localStorage.removeItem('principal')
}

// forEach方法
export const forEach = (arr, fn) => {
  if (!arr.length || !fn) return
  let i = -1
  let len = arr.length
  while (++i < len) {
    let item = arr[i]
    fn(item, i, arr)
  }
}

// 获取对象类型
const typeOf = (obj) => {
  const toString = Object.prototype.toString
  const map = {
    '[object Boolean]': 'boolean',
    '[object Number]': 'number',
    '[object String]': 'string',
    '[object Function]': 'function',
    '[object Array]': 'array',
    '[object Date]': 'date',
    '[object RegExp]': 'regExp',
    '[object Undefined]': 'undefined',
    '[object Null]': 'null',
    '[object Object]': 'object'
  }
  return map[toString.call(obj)]
}

// 深度拷贝对象
export const deepCopy = (obj) => {
  const t = typeOf(obj)
  let o
  if (t === 'array') {
    o = []
    for (let i = 0; i < obj.length; i++) {
      o.push(deepCopy(obj[i]))
    }
  } else if (t === 'object') {
    o = {}
    for (let i in obj) {
      o[i] = deepCopy(obj[i])
    }
  } else {
    return obj
  }
  return o
}

// 获取Http的请求参数
export const getHttpParams = (req) => {
  let params
  if (req.type.toUpperCase() === 'GET') {
    const segments = req.url.split('?')
    if (segments.length > 1) {
      params = {}
      const ps = segments[1].split('&')
      ps.forEach(p => {
        const entry = p.split('=')
        params[entry[0]] = entry[1]
      })
    }
  } else if (req.type.toUpperCase() === 'POST') {
    try {
      params = JSON.parse(req.body)
    } catch (e) {
      params = qs.parse(req.body)
    }
  } else if (req.type.toUpperCase() === 'PUT') {
    try {
      params = JSON.parse(req.body)
    } catch (e) {
      params = qs.parse(req.body)
    }
  }
  return params
}

// 格式化日期
export const formatDate = (v, format) => {
  const date = new Date(v)
  const year = date.getFullYear()
  const month = date.getMonth() < 10 ? ('0' + (date.getMonth() + 1)) : (date.getMonth() + 1)
  const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
  const hour = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
  const minute = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
  const second = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
  if (format) {
    if (format === 'yyyyMMdd') {
      return year + '' + month + '' + day
    } else if (format === 'yyyy-MM-dd') {
      return year + '-' + month + '-' + day
    } else if (format === 'yyyyMMddHHmmss') {
      return year + '' + month + '' + day + '' + hour + '' + minute + '' + second
    } else if (format === 'yyyy-MM-dd HH:mm:ss') {
      return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second
    } else if (format === 'HH:mm:ss') {
      return hour + ':' + minute + ':' + second
    } else if (format === 'HH:mm') {
      return hour + ':' + minute
    } else {
      return ''
    }
  }
  return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second
}

// 增加天数
export const addDay = (v, incr) => {
  const date = new Date()
  date.setTime(v.getTime() + incr * 86400000)
  return date
}

// dayStart
export const dayStart = (v) => {
  const date = new Date(v)
  date.setHours(0, 0, 0, 0)
  return date
}

export const writeJSON = (obj) => {
  const json = JSON.stringify(obj, null, 4)
  const html = json.replace(/\n/g, '<br>').replace(/\s/g, '&nbsp')
  return html
}
