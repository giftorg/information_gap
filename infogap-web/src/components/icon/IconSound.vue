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
       class="arco-icon arco-icon-sound" stroke-width="4" stroke-linecap="butt" stroke-linejoin="miter" filter=""
       data-v-2bc6460e="" :width="size + 'px'" :height="size + 'px'" :color="color">
    <path d="m14 16 10-9v34l-10-9H6V16h8Z"></path>
    <path d="M31.071 16.929c3.905 3.905 3.905 10.237 0 14.142M36.727 11.272c7.03 7.03 7.03 18.426 0 25.456"></path>
  </svg>
</template>
