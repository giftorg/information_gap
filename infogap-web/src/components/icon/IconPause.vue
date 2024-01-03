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
       class="arco-icon arco-icon-pause" stroke-width="4" stroke-linecap="butt" stroke-linejoin="miter" filter=""
       data-v-2bc6460e="" :width="size + 'px'" :height="size + 'px'" :color="color">
    <path d="M14 12h4v24h-4zM30 12h4v24h-4z"></path>
    <path fill="currentColor" stroke="none" d="M14 12h4v24h-4zM30 12h4v24h-4z"></path>
  </svg>
</template>