<template>
  Category
</template>

<script setup>
definePageMeta({
  layout: 'cms'
})

const { currentRoute } = useRouter();
console.log(currentRoute)
</script>
