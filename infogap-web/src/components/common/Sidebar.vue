<script setup>

import IconHome from '@/components/icon/IconHome.vue'
import IconNews from '@/components/icon/IconNews.vue'
import IconFavorites from '@/components/icon/IconFavorites.vue'

const props = defineProps({
  tab: {
    type: String,
    default: ''
  }
})
</script>

<template>
  <div id="sidebar">
    <div :class="['tab', {'tab-active': tab===''}]">
      <IconHome :size="20" color="#000" />
      信息差
    </div>
    <div :class="['tab', {'tab-active': tab==='news'}]">
      <IconNews :size="20" color="#000" />
      每日新闻
    </div>
    <div :class="['tab', {'tab-active': tab==='favorites'}]">
      <IconFavorites :size="20" color="#000" />
      收藏夹
    </div>
  </div>
</template>

<style scoped>
#sidebar {
  display: flex;
  flex-direction: column;
  gap: 20px;

  .tab {
    width: 200px;
    border-radius: 21px;
    padding: 12px 15px;
    display: flex;
    gap: 10px;
    align-items: center;
    cursor: pointer;
  }

  .tab:hover,
  .tab-active {
    background: #efefef;
  }
}
</style>