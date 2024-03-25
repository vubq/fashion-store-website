<template>
  Product
</template>

<script setup>
definePageMeta({
  layout: 'cms',
  isAdmin: false
})
const { options } = useRouter();
console.log(options)
</script>
