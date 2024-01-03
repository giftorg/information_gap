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
       class="arco-icon arco-icon-search" stroke-width="4" stroke-linecap="butt" stroke-linejoin="miter" filter=""
       data-v-2bc6460e="" :width="size + 'px'" :height="size + 'px'" :color="color">
    <path
      d="M33.072 33.071c6.248-6.248 6.248-16.379 0-22.627-6.249-6.249-16.38-6.249-22.628 0-6.248 6.248-6.248 16.379 0 22.627 6.248 6.248 16.38 6.248 22.628 0Zm0 0 8.485 8.485"></path>
  </svg>
</template>
