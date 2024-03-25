<template>
  <div class="mt-5">
    <img id="logo" ref="logo" alt="" src="../assets/images/logo.png"/>
  </div>

  <UAside class="!top-0 !py-0 aside-custom mt-5">
    <template #top>
      <UDashboardSearchButton class="w-full" label="Tìm kiếm..." />
    </template>

    <template #links>
      <UDashboardSidebarLinks :links="state.linksAside"/>
    </template>

    <template #bottom>
      <UDivider type="dashed" class="my-6"/>
      <UPageLinks :links="state.pageLinks"/>
    </template>
  </UAside>
</template>

<script setup lang="ts">

const state = reactive({
  linksAside: [{
    label: 'Quản lý sản phẩm',
    icon: 'i-heroicons-clipboard-document-list',
    to: '',
    children: [{
      label: 'Sản phẩm',
      to: '/admins/products'
    }, {
      label: 'Danh mục',
      to: '/admins/categories'
    }]
  }, {
    label: 'Quản lý vật tư',
    icon: 'i-heroicons-clipboard-document-list',
    to: '',
    children: [{
      label: 'Test',
      to: '/'
    }]
  }],
  pageLinks: [{
    icon: 'i-heroicons-heart',
    label: 'Learn how to contribute',
    to: '/',
    target: '_blank'
  }]
})
</script>

<style type="text/css">
#logo {
  height: 100px;
}
.aside-custom {
  height: calc(100vh - 100px - 3.75rem) !important;
}
.aside-custom .bg-background {
  background-color: #0F172A !important;
}
.aside-custom .bg-gradient-to-b {
  background-image: unset;
}
</style>