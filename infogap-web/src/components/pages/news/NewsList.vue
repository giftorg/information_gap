<script setup>
const props = defineProps({
  newsList: {
    type: Object,
    required: true
  }
})

function formatTime(timestamp) {
  if (!timestamp) return ''
  timestamp = parseInt(timestamp)
  return new Date(timestamp * 1000).toLocaleString().slice(0, 16)
}
</script>

<template>
  <div id="news-list">
    <div class="news" v-for="news in newsList" :key="news.id">
      <img class="news-image" :src="`/api/news/image?url=${news.image}`" alt="" />
      <div class="news-content">
        <a class="news-content-title" :href="news.link" target="_blank">{{ news.title }}</a>
        <div class="news-content-description">{{ news.description }}</div>
        <div class="news-content-footer">
          <div class="news-content-keywords">
            <div class="news-content-keyword" v-for="key in news['keyword_list']" :key="key">
              {{ key }}
            </div>
          </div>
          <div class="news-content-time">{{ formatTime(news['publish_timestamp']) }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#news-list {
  display: flex;
  flex-direction: column;
  background: white;
  padding: 10px 30px;

  .news {
    padding: 30px 0;
    display: flex;
    gap: 20px;
    border-bottom: 1px solid #efefef;

    .news-image {
      width: 240px;
      min-height: 125px;
      background: #ebebeb;
    }

    .news-content {
      flex: 1;
      display: flex;
      flex-direction: column;
      justify-content: space-between;

      .news-content-title {
        font-size: 18px;
        font-weight: 500;
      }

      .news-content-description {
        color: #999999;
      }

      .news-content-footer {
        display: flex;
        justify-content: space-between;

        .news-content-keywords {
          display: flex;
          gap: 10px;

          .news-content-keyword {
            padding: 3px;
            border-radius: 2px;
            background: #ebf3fe;
            color: #3260f9;
            font-size: 12px;
          }
        }

        .news-content-time {
          color: #666666;
        }
      }
    }
  }
}
</style>