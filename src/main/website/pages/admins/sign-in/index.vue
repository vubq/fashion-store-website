<template>
  <div class="w-full min-h-screen flex justify-center" id="login-page">
    <UCard class="max-w-sm w-full m-auto space-y-6">
      <div class="mb-2 pointer-events-none text-center">
        <UIcon name="i-heroicons-lock-closed" class="w-8 h-8 flex-shrink-0 text-gray-900 dark:text-white"/>
      </div>

      <p class="text-2xl text-gray-900 dark:text-white font-bold text-center">
        V Fashion Store
      </p>

      <p class="text-gray-500 dark:text-gray-400 mt-1 text-center">
        Đăng nhập hệ thống quản lý
      </p>

      <UForm :validate="validate" :state="state" class="space-y-4 mt-5" @submit="onSubmit">
        <UFormGroup label="Tài khoản" name="userName">
          <UInput v-model="state.userName"/>
        </UFormGroup>

        <UFormGroup label="Mật khẩu" name="password">
          <UInput v-model="state.password" type="password"/>
        </UFormGroup>

        <UButton type="submit" block class="!mt-5">
          Đăng nhập
        </UButton>

        <UDivider label="hoặc"/>

        <UButton block color="gray" variant="solid">
          <font-awesome-icon :icon="['fab', 'google']"/>
          Google
        </UButton>
      </UForm>
    </UCard>
  </div>
</template>

<script setup lang="ts">
import type {FormError} from '#ui/types'
import {useAuthStore} from '~/stores/auth'

definePageMeta({
  layout: 'sign-in-admin'
})

const state = reactive({
  userName: undefined,
  password: undefined,
})

const validate = (state: any) => {
  const errors: FormError[] = []
  if (!state.userName) errors.push({path: 'userName', message: '*Tài khoản không đuợc để trống'})
  if (!state.password) errors.push({path: 'password', message: '*Mật khẩu không được để trống'})
  return errors
}

function onSubmit(data: any) {
  useAuthStore().signIn(data.data)
}
</script>

<style type="text/css">
#login-page {
  background-image: url('@/assets/images/login-page-background.png');
}
</style>
