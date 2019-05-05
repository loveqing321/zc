const env = process.env.BUILD_ENV ? require('../build/' + process.env.BUILD_ENV + '.js') : require('../build/local.js')

export default env
