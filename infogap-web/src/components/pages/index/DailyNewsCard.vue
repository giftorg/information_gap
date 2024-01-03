<script setup>
import { onMounted, ref, watch } from 'vue'
import IconPlayArrow from '@/components/icon/IconPlayArrow.vue'
import IconPushPin from '@/components/icon/IconPushPin.vue'
import IconSound from '@/components/icon/IconSound.vue'
import IconPause from '@/components/icon/IconPause.vue'
import IconStar from '@/components/icon/IconStar.vue'
import IconAudio from '@/components/icon/IconAudio.vue'
import { useRouter } from 'vue-router'
import IconStarFill from '@/components/icon/IconStarFill.vue'

const router = useRouter()

const props = defineProps({
  news: {
    info_list: {
      type: Array,
      default: []
    },
    date: {
      type: String,
      default: ''
    }
  }
})

const playing = ref(false)
const hovering = ref(false)
const date = ref(props.news.date)

function getStar(date) {
  const result = localStorage.getItem(`star::${date}`)
  return result ? result : '0'
}

function star(date) {
  const t = getStar(date)
  localStorage.setItem(`star::${date}`, t === '0' ? '1' : '0')
  isStar.value = t === '0'
}

const isStar = ref(getStar(date.value) === '1')

function speak() {
  playing.value = true

  let text = ''
  for (let i = 0; i < props.news.info_list.length; i++) {
    text += (i + 1) + '、' + props.news.info_list[i] + '\n'
  }

  const speech = new SpeechSynthesisUtterance()
  speech.text = text
  console.log(text)
  speechSynthesis.speak(speech)
}

function cancel() {
  playing.value = false
  speechSynthesis.cancel()
}

</script>

<template>
  <div id="daily-news-card">
    <div class="daily-news-header">
      <div class="daily-news-header-date">
        <IconPushPin :size="20" />
        <div>{{ news.date }}</div>
      </div>
      <div class="daily-news-header-right">
        <div class="daily-news-header-right-icon" @click="star(news.date)">
          <IconStar :size="18" color="#3260f9" v-show="!isStar" />
          <IconStarFill :size="18" color="#3260f9" v-show="isStar" />
        </div>
        <div class="daily-news-header-right-icon" v-if="!playing" @click="speak">
          <IconPlayArrow :size="12" color="#3260f9" />
        </div>
        <div class="daily-news-header-right-icon" style="padding: 0 10px" v-else @click="cancel"
             @mouseenter="hovering=true" @mouseleave="hovering=false">
          <IconSound :size="16" color="#3260f9" v-show="!hovering" />
          <IconPause :size="16" color="#3260f9" v-show="hovering" />
          <IconAudio :size="19" color="#3260f9" />
        </div>
      </div>
    </div>
    <div class="daily-news-info-list" @click="router.push(`/information_gap/${news.date}`)">
      <div class="daily-news-info-item" v-for="(info, idx) in news.info_list.slice(0, 5)" :key="idx">
        <div class="daily-news-info-item-num">
          {{ idx + 1 }}.
        </div>
        <div class="daily-news-info-item-content">
          {{ info }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#daily-news-card {
  background-color: var(--white);
  border-radius: 10px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: start;
  transition: .3s all;
  cursor: pointer;

  .daily-news-header {
    display: flex;
    width: 100%;
    justify-content: space-between;
    align-items: center;

    .daily-news-header-date {
      background-color: #ebf3fe;
      color: #3260f9;
      padding: 0 12px 0 6px;
      height: 30px;
      font-size: 14px;
      border-radius: 14px;
      display: flex;
      flex-direction: row;
      align-items: center;
      gap: 6px;
    }

    .daily-news-header-right {
      display: flex;
      gap: 10px;

      .daily-news-header-right-icon {
        background: #ebf3fe;
        min-width: 28px;
        height: 28px;
        border-radius: 14px;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 3px;
      }
    }
  }

  .daily-news-info-list {
    display: flex;
    flex-direction: column;
    gap: 10px;

    .daily-news-info-item {
      display: flex;
      gap: 10px;
      font-weight: 600;

      .daily-news-info-item-num {

      }

      .daily-news-info-item-content {

      }
    }
  }
}

#daily-news-card:hover {
  transform: scale(1.04);
  box-shadow: rgba(149, 157, 165, 0.2) 0 8px 24px;
}
</style>