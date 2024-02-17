<template>
  <div class="grid grid-cols-12">
    <div class="col-span-2 px-5 h-screen sticky top-0">
      <div class="mt-5">
        <img ref="logo" alt="" src="../assets/images/logo.png" />
      </div>

      <UAside class="!top-0 !py-0 mt-5 aside">
        <template #top>
          <UDocsSearchButton class="w-full"/>
        </template>

        <template #links>
          <UNavigationTree :links="linksAside" default-open :multiple="false" />
        </template>

        <template #bottom>
          <UDivider type="dashed" class="my-6" />
          <UPageLinks :links="pageLinks" />
        </template>
      </UAside>
    </div>

    <div class="col-span-10 border-s border-gray-200 dark:border-gray-800">
      <UHeader>
        <template #logo>
          Logo
        </template>

        <template #right>
          <UDocsSearchButton label="" />

          <UColorModeButton />

          <UDropdown :items="items" :popper="{ arrow: true }">
            <UAvatar src="https://avatars.githubusercontent.com/u/739984?v=4" />

            <template #account="{ item }">
              <div class="text-left">
                <p>
                  Signed in as
                </p>
                <p class="truncate font-medium text-gray-900 dark:text-white">
                  {{ item.label }}
                </p>
              </div>
            </template>

            <template #item="{ item }">
              <span class="truncate">{{ item.label }}</span>

              <UIcon :name="item.icon" class="flex-shrink-0 h-4 w-4 text-gray-400 dark:text-gray-500 ms-auto" />
            </template>
          </UDropdown>
        </template>
      </UHeader>

      <UMain class="p-8">
        <slot />
      </UMain>

      <UFooter class="border-t border-gray-200 dark:border-gray-800">
        <template #left>
          Copyright © {{ new Date().getFullYear() }}
        </template>
      </UFooter>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.logoHeight = this.$refs['logo'].clientHeight + 'px'
  },
  data() {
    return {
      logoHeight: 0,
      linksAside: [{
        label: 'Quản lý sản phẩm',
        icon: 'i-heroicons-clipboard-document-list',
        to: '',
        children: [{
          label: 'Sản phẩm',
          icon: 'i-heroicons-clipboard-document-list',
          to: '/admins/products'
        }, {
          label: 'Danh mục',
          icon: 'i-heroicons-clipboard-document-list',
          to: '/admins/categories'
        }]
      }, {
        label: 'Quản lý vật tư',
        icon: 'i-heroicons-clipboard-document-list',
        to: '',
        children: [{
          label: 'Test',
          icon: 'i-heroicons-clipboard-document-list',
          to: '/'
        }]
      }],
      pageLinks: [{
        icon: 'i-heroicons-heart',
        label: 'Learn how to contribute',
        to: '/',
        target: '_blank'
      }],
      items: [
        [{
          label: 'ben@example.com',
          slot: 'account',
          disabled: true
        }], [{
          label: 'Settings',
          icon: 'i-heroicons-cog-8-tooth'
        }], [{
          label: 'Documentation',
          icon: 'i-heroicons-book-open'
        }, {
          label: 'Changelog',
          icon: 'i-heroicons-megaphone'
        }, {
          label: 'Status',
          icon: 'i-heroicons-signal'
        }], [{
          label: 'Sign out',
          icon: 'i-heroicons-arrow-left-on-rectangle'
        }]
      ]
    }
  }

}
</script>

<style type="text/css">
header div:first-child {
  max-width: 100% !important;
}
footer div:first-child div:first-child {
  max-width: 100% !important;
}
.aside {
  height: calc(100vh - v-bind(logoHeight) - 3.75rem) !important;
}
</style>
