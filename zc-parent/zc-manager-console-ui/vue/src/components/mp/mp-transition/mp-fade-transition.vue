<template lang="pug">
  transition(name="mp-fade" mode="out-in")
    slot
</template>

<script>
export default {
  name: 'MpFadeTransition'
}
</script>

<style scoped lang="less">
.mp-fade-enter-active, .mp-fade-leave-active {
  transition: opacity .3s ease;
}
.mp-fade-enter, .mp-fade-leave-to {
  opacity: 0;
}
</style>
