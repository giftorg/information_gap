<script setup>
const props = defineProps({
  size: {
    type: Number,
    default: 28
  },
  color: {
    type: String,
    default: ''
  }
})
</script>

<template>
  <svg viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="currentColor"
       class="arco-icon arco-icon-pushpin" stroke-width="4" stroke-linecap="butt" stroke-linejoin="miter" filter=""
       data-v-2bc6460e="" :width="size + 'px'" :height="size + 'px'" :color="color">
    <path
      d="M19.921 28.163 7.193 40.89m12.728-12.728 8.884 8.883c.17.17.447.17.617 0l5.12-5.12a7.862 7.862 0 0 0 1.667-8.655.093.093 0 0 1 .02-.102l4.906-4.906a2 2 0 0 0 0-2.828L32.648 6.95a2 2 0 0 0-2.828 0l-4.89 4.889a.126.126 0 0 1-.139.027 7.828 7.828 0 0 0-8.618 1.66l-5.027 5.026a.591.591 0 0 0 0 .836l8.774 8.775Z"></path>
  </svg>
</template>