<template>
  <div class="grid grid-cols-12">
    <div class="col-span-2 px-5 h-screen sticky top-0 ">
      <div class="mt-5 flex justify-center">
        <img id="logo" ref="logo" alt="" src="../assets/images/logo.png"/>
      </div>

      <UAside class="!top-0 !py-0 aside-custom mt-5">
        <template #top>
          <UDashboardSearchButton class="w-full" label="Tìm kiếm..." />
        </template>

        <template #links>
          <sidebar-custom-admin :links="state.linksAside"/>
        </template>

        <template #bottom>
          <UDivider type="dashed" class="my-6"/>
          <UPageLinks :links="state.pageLinks"/>
        </template>
      </UAside>
    </div>

    <div class="col-span-10 border-s border-gray-200 dark:border-gray-800">
      <UHeader>
        <template #logo>
          <div></div>
        </template>

        <template #right>
          <UDashboardSearchButton label=""/>

          <UColorModeButton/>

          <div>
            Xin chào
            <span class="font-bold">{{ fullName }}</span>!
          </div>

          <UDropdown :items="state.items" :popper="{ arrow: true }">
            <UAvatar :src="avatar"/>

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

              <UIcon :name="item.icon" class="flex-shrink-0 h-4 w-4 text-gray-400 dark:text-gray-500 ms-auto"/>
            </template>
          </UDropdown>
        </template>
      </UHeader>

      <UMain class="p-8 bg-[#F1F7FC]">
        <slot/>
      </UMain>

      <UFooter class="border-t border-gray-200 dark:border-gray-800">
        <template #left>
          Copyright © {{ new Date().getFullYear() }}
        </template>
      </UFooter>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useAuthStore} from "~/stores/auth";

const fullName = useAuthStore().user ? useAuthStore().user.firstName + useAuthStore().user.lastName : 'Admin'
const avatar = useAuthStore().user ? useAuthStore().user.avatar : ''

const state = reactive({
  logoHeight: 0,
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
  }],
  items: [
    [{
      label: 'ben@example.com',
      slot: 'account',
      disabled: true
    }], [{
      label: 'Thông tin tài khoản',
      icon: 'i-heroicons-cog-8-tooth',
      click: () => {
        useRouter().push({path: '/admins/account-information'})
      }
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
      label: 'Đăng xuất',
      icon: 'i-heroicons-arrow-left-on-rectangle',
      click: () => {
        useAuthStore().signOut()
      }
    }]
  ]
})
</script>

<style type="text/css">
header div:first-child {
  max-width: 100% !important;
}
footer div:first-child div:first-child {
  max-width: 100% !important;
}
#logo {
  height: 100px;
}
.aside-custom {
  height: calc(100vh - 100px - 3.75rem) !important;
}
</style>
