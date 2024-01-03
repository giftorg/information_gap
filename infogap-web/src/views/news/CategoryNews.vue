<script setup>
import { onMounted, reactive, ref } from 'vue'
import { getCategoryNews } from '@/services/news/index.js'
import { useRoute } from 'vue-router'
import Navbar from '@/components/common/Navbar.vue'
import NewsList from '@/components/pages/news/NewsList.vue'

const categoryMap = {
  politics: '政治',
  economy: '财经',
  culture: '文化',
  technology: '科技',
  sports: '体育',
  military: '军事',
  entertainment: '娱乐'
}

const route = useRoute()
const category = ref(route.params.category)
const newsList = reactive([])

onMounted(() => {
  getCategoryNews(categoryMap[category.value]).then(res => {
    for (let news of res.data.content) {
      newsList.push(news)
    }
  })
})
</script>

<template>
  <div>
    <Navbar :tab="category"/>
    <div class="container">
      <div class="title">
        <img src="@/assets/img.png" alt="" class="icon" />
        {{categoryMap[category]}}专区
      </div>
      <NewsList :news-list="newsList"/>
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