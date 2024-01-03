<script setup>
import { onMounted, reactive, ref } from 'vue'
import { getSearchNews } from '@/services/news/index.js'
import { useRoute } from 'vue-router'
import Navbar from '@/components/common/Navbar.vue'
import NewsList from '@/components/pages/news/NewsList.vue'

const route = useRoute()
const query = ref(route.query.query)

const newsList = reactive([])

onMounted(() => {
  getSearchNews(query.value).then(res => {
    for (let news of res.data) {
      newsList.push(news)
    }
  })
})
</script>

<template>
  <div>
    <Navbar />
    <div class="container">
      <div class="title">
        <img src="@/assets/img.png" alt="" class="icon" />
        新闻搜索
      </div>
      <NewsList :news-list="newsList" />
    </div>
  </div>
</template>

<style scoped>
.title {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 40px;
  background: white;
  padding: 40px 0 ;
  border-bottom: 1px solid #efefef;
  gap: 20px;

  img {
    height: 50px;
  }
}
</style>