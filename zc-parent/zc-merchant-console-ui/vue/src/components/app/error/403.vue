<template lang="pug">
  el-container.forbidden
    div.bubble
    div.bubble
    div.bubble
    div.bubble
    div.bubble
    div.main
      h1
        | FORBIDDEN
      p
        | 您没有权限访问该页面，请联系管理员！
</template>

<script>

export default {
  name: 'error403'
}
</script>

<style scoped lang="less">
  .forbidden {
    height: 100%;
    background: linear-gradient(#2a7e81, #bab8c0);
    display:flex;
    justify-content:center;
    align-items:center;
    color:#FFF;
    font-family:'Varela Round',Sans-serif;
    text-shadow:0 30px 10px rgba(0,0,0,0.15);
    .main {
      text-align:center;
      z-index:5;
      p {
        font-size:18px;
        margin-top:0;
      }
      h1 {
        font-size: 100px;
        margin:0;
      }
    }
    .bubble {
      background:linear-gradient(#93ec9d, #61d6b8);
      border-radius:50%;
      box-shadow:0 30px 15px rgba(0,0,0,0.15);
      position:absolute;
    }
    .bubble:before, .bubble:after {
      content:'';
      background:linear-gradient(#ececaa, #d6c95d);
      border-radius:50%;
      box-shadow:0 30px 15px rgba(0,0,0,0.15);
      position:absolute;
    }
    .bubble:nth-child(1) {
      top:15vh;
      left:15vw;
      height:22vmin;
      width:22vmin;
    }
    .bubble:nth-child(1):before {
      width:13vmin;
      height:13vmin;
      bottom:-25vh;
      right:-10vmin;
    }
    .bubble:nth-child(2) {
      top:20vh;
      left:38vw;
      height:10vmin;
      width:10vmin;
    }
    .bubble:nth-child(2):before {
      width:5vmin;
      height:5vmin;
      bottom:-10vh;
      left:-8vmin;
    }
    .bubble:nth-child(3){
      top:12vh;
      right:30vw;
      height:13vmin;
      width:13vmin;
    }
    .bubble:nth-child(3):before {
      width:3vmin;
      height:3vmin;
      bottom:-15vh;
      left:-18vmin;
      z-index:6;
    }
    .bubble:nth-child(4) {
      top:25vh;
      right:18vw;
      height:18vmin;
      width:18vmin;
    }
    .bubble:nth-child(4):before {
      width:7vmin;
      height:7vmin;
      bottom:-10vmin;
      left:-15vmin;
    }
    .bubble:nth-child(5) {
      top:60vh;
      right:18vw;
      height:28vmin;
      width:28vmin;
    }
    .bubble:nth-child(5):before {
      width:10vmin;
      height:10vmin;
      bottom:5vmin;
      left:-25vmin;
    }
  }
</style>
