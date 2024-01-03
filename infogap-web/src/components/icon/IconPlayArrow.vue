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
  <svg t="1703752737883" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
       p-id="5839" :width="props.size + 'px'" :height="props.size + 'px'" :fill="color">
    <path
      d="M81.967011 100.452962c0-92.149926 64.709726-126.962121 144.16344-78.839381l656.312252 401.364123c79.453714 49.146627 79.453714 129.009897 0 177.132636L226.130451 1002.293573C146.676737 1050.416312 81.967011 1015.604118 81.967011 923.658969V100.452962z m0 0"
      p-id="5840"></path>
  </svg>
</template>
