// https://nuxt.com/docs/api/configuration/nuxt-config

export default defineNuxtConfig({
  devtools: {enabled: true},
  modules: ['@nuxt/content', '@nuxt/ui', '@pinia/nuxt'],
  extends: ['@nuxt/ui-pro'],
  css: [
    '~/assets/css/main.css',
    '@fortawesome/fontawesome-svg-core/styles.css'
  ],
  postcss: {
    plugins: {
      'postcss-import': {},
      'tailwindcss/nesting': {},
      tailwindcss: {},
      autoprefixer: {},
    },
  },
  typescript: {
    typeCheck: true
  }
})
