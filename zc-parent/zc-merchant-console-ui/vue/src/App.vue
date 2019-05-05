<template lang="pug">
  .app
    main-page
</template>

<script>
import MainPage from './components/Main'

export default {
  name: 'App',
  components: {
    MainPage
  }
}
</script>

<style lang="less">
@import 'normalize.css/normalize.css';
@import 'style.less';
</style>
