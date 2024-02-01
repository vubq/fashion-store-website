<template>
  <div class="grid grid-cols-12">
    <div class="col-span-2 px-5 sticky top-0">
      <UAside class="!top-0 !max-h-screen">
        <template #top>
          <div>
            <ULandingLogos>
              <img alt="" src="https://fdesign.vn/wp-content/uploads/2022/07/logo-vingroup-1.jpg" />
            </ULandingLogos>
            <UDocsSearchButton class="w-full"/>
          </div>
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
      <UMain class="p-5">
        <slot />
      </UMain>
      <UFooter :links="links" class="border-t border-gray-200 dark:border-gray-800">
        <template #left>
          Copyright © {{ new Date().getFullYear() }}
        </template>

        <template #right>
          <UButton icon="i-simple-icons-x" color="gray" variant="ghost" to="https://x.com/nuxt_js" target="_blank" />
          <UButton icon="i-simple-icons-discord" color="gray" variant="ghost" to="https://discord.com/invite/ps2h6QT" target="_blank" />
          <UButton icon="i-simple-icons-github" color="gray" variant="ghost" to="https://github.com/nuxt/nuxt" target="_blank" />
        </template>
      </UFooter>
    </div>
  </div>
</template>

<script setup lang="ts">

const links = [{
  label: 'Docs',
  icon: 'i-heroicons-book-open',
  to: '/getting-started'
}, {
  label: 'Pro',
  icon: 'i-heroicons-square-3-stack-3d',
  to: '/pro'
}, {
  label: 'Releases',
  icon: 'i-heroicons-rocket-launch',
  to: '/releases'
}]

const linksAside = [{
  label: 'Sản phẩm',
  icon: 'i-heroicons-clipboard-document-list',
  to: '/admins/products'
},{
  label: 'Sản phẩm',
  icon: 'i-heroicons-clipboard-document-list',
  to: '/admins/products'
},{
  label: 'Sản phẩm',
  icon: 'i-heroicons-clipboard-document-list',
  to: '/admins/products'
},{
  label: 'Sản phẩm',
  icon: 'i-heroicons-clipboard-document-list',
  to: '/admins/products'
},{
  label: 'Sản phẩm',
  icon: 'i-heroicons-clipboard-document-list',
  to: '/admins/products'
},{
  label: 'Sản phẩm',
  icon: 'i-heroicons-clipboard-document-list',
  to: '/admins/products'
},{
  label: 'Sản phẩm',
  icon: 'i-heroicons-clipboard-document-list',
  to: '/admins/products',
  children: [
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    }
    ,{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },
    {
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    },{
      label: 'Sản phẩm',
      icon: 'i-heroicons-clipboard-document-list',
      to: '/admins/products'
    }
  ]
}]

const pageLinks = [{
  icon: 'i-heroicons-heart',
  label: 'Learn how to contribute',
  to: '/getting-started/contributing',
  target: '_blank'
}]

const items = [
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
</script>
