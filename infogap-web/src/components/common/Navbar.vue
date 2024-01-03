<script setup>
import { reactive, ref } from 'vue'
import IconSearch from '@/components/icon/IconSearch.vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const props = defineProps({
  tab: {
    type: String,
    default: ''
  },
  style: {
    type: Object,
    default: {}
  }
})

const category = reactive([
  { value: '', title: '首页' },
  { value: 'politics', title: '政治' },
  { value: 'economy', title: '财经' },
  { value: 'culture', title: '文化' },
  { value: 'technology', title: '科技' },
  { value: 'sports', title: '体育' },
  { value: 'military', title: '军事' },
  { value: 'entertainment', title: '娱乐' }
])

const query = ref(route.query?.query ? route.query.query : '')
function search() {
  location.href = '/search?query=' + query.value
}
</script>

<template>
  <div class="container">
    <div id="nav" :style="style">
      <img class="logo" src="@/assets/logo.svg" alt="">
      <div class="tabs">
        <a v-for="cate in category" :key="cate.value" :href="'/' + cate.value"
           :class="['tab', {'tab-active': cate.value === tab}]">
          {{ cate.title }}
        </a>
      </div>
      <div class="search">
        <div class="search-box">
          <IconSearch :size="16" color="#3c3c43" />
          <input class="search-input" type="text" @keyup.enter="search" placeholder="搜索新闻" v-model="query">
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#nav {
  display: flex;
  justify-content: space-between;
  padding: 35px 0;

  .logo {
    width: 200px;
  }

  .tabs {
    display: flex;
    gap: 10px;
    align-items: center;
    font-size: 18px;

    .tab {
      padding: 10px 20px;
    }

    .tab:hover,
    .tab-active {
      color: #3260f9;
    }
  }

  .search {
    display: flex;
    align-items: center;

    .search-box {
      display: flex;
      align-items: center;
      gap: 5px;
      padding: 13px;
      border-radius: 10px;
      background: #efefef;
      border: transparent 1px solid;
      transition: .3s border-color;

      .search-input {
        min-width: 200px;
        background: none;
        border: none;
        outline: none;
      }

      .search-input:focus {
        border: none;
      }
    }

    .search-box:hover {
      border-color: #3260f9;
    }
  }
}
</style>