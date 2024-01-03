<script setup>
import DailyNewsCard from '@/components/pages/index/DailyNewsCard.vue'
import { onMounted, reactive, ref } from 'vue'
import Navbar from '@/components/common/Navbar.vue'
import Sidebar from '@/components/common/Sidebar.vue'
import { getInformationGapList } from '@/services/news/index.js'

const daily_news_group = reactive([])

const daily_news_list = reactive([])

let count = 0

function reset() {
  const width = document.documentElement.clientWidth
  const count = Math.floor((width * 0.7 - 200) / 350)

  daily_news_group.length = count
  for (let i = 0; i < count; i++) {
    daily_news_group[i] = []
  }

  for (let i = 0; i < daily_news_list.length; i++) {
    daily_news_group[i % count].push(daily_news_list[i])
  }
}

onMounted(() => {
  getInformationGapList().then(res => {
    for (let x of res.data) {
      daily_news_list.push(x)
    }
    daily_news_list.sort((a, b) => {
      if (a['date'] < b['date']) {
        return 1;
      }
      return -1;
    })
    reset()
  })

  window.onresize = function() {
    reset()
  }
})

</script>

<template>
  <div>
    <Navbar />
    <div id="index" class="container">
      <div class="sidebar">
        <Sidebar />
      </div>
      <div class="daily-news-falls">
        <div class="daily-news-column" v-for="group in daily_news_group">
          <DailyNewsCard
            class="daily-news"
            v-for="news in group"
            :key="news.cvid"
            :news="news"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#index {
  display: flex;
  gap: 30px;

  .sidebar {
    margin-top: 30px;
  }

  .daily-news-falls {
    flex: 1;
    display: flex;
    justify-content: space-between;
    gap: 40px;

    .daily-news-column {
      flex: 1;

      display: flex;
      flex-direction: column;
      gap: 40px;
    }
  }
}
</style>