<script setup>
import Navbar from '@/components/common/Navbar.vue'
import { onMounted, reactive, ref } from 'vue'
import { getInformationGapByDate } from '@/services/news/index.js'
import { useRoute } from 'vue-router'

const route = useRoute()
const date = ref(route.params.date)

const news = reactive({
  date: '',
  info_list: []
})

onMounted(() => {
  getInformationGapByDate(date.value).then(res => {
    for (let k in res.data) {
      news[k] = res.data[k]
    }
  })
})
</script>

<template>
  <div>
    <Navbar />
    <div class="container body">
      <div class="title">
        <img src="@/assets/img.png" alt="" class="icon" />
        每日信息 — {{ news.date.replaceAll('-', '/') }}
      </div>
      <div class="info-list">
        <div class="info" v-for="(info, idx) in news.info_list" :key="idx">
          <div class="num">{{ idx + 1 }}.</div>
          <div class="content">{{ info }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.body {
  background: #fff;
  border-radius: 10px;
}

.title {
  padding: 50px 0;
  display: flex;
  gap: 30px;
  align-items: center;
  justify-content: center;
  font-size: 40px;
  border-bottom: 1px solid #efefef;

  img {
    height: 50px;
  }
}

.info-list {
  display: flex;
  flex-direction: column;
  padding: 20px;

  .info {
    padding: 20px;
    display: flex;
    gap: 20px;
    border-bottom: 1px solid #efefef;
    cursor: pointer;

    .num {
      color: #999;
    }
  }

  .info:hover {
    background-color: #f8faff;
  }
}
</style>