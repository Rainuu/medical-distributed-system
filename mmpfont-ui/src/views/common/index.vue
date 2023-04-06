<template>
  <div class="block">
    <el-carousel height="700px">
      <el-carousel-item v-for="item in pickList" :key="item.id">
       <img style="object-fit: scale-down;width: 100%;height: 100%" :src="item.idView"/>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "index",
  data(){
    return{
      pickList:[
        {id:0,idView:require("@/assets/index/1.jpg")},
        {id:1,idView:require("@/assets/index/2.jpg")},
        {id:2,idView:require("@/assets/index/3.jpg")},
        {id:3,idView:require("@/assets/index/4.jpg")},
      ]
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>