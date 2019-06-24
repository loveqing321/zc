<template lang="pug">
  transition-group(name="mp-fade-group" mode="out-in")
    slot
</template>

<script>
export default {
  name: 'MpFadeTransitionGroup'
}
</script>

<style scoped lang="less">
.mp-fade-group-enter-active, .mp-fade-leave-active {
  transition: opacity .3s ease;
}
.mp-fade-group-enter, .mp-fade-leave-to {
  opacity: 0;
}
</style>
