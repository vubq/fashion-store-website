<template>
  <div class="grid grid-cols-5 gap-5 w-full">
    <div class="col-span-2 bg-[#FFFFFF] rounded-lg p-4">

    </div>
    <div class="col-span-3 bg-[#FFFFFF] rounded-lg p-4">
      <label class="font-bold text-lg">Thông tin tài khoản</label>
      <UForm :validate="validate" :state="state" class="space-y-4 grid grid-cols-3 gap-x-5 mt-5" @submit="onSubmit">
        <UFormGroup class="col-span-1" label="Họ" name="lastName">
          <UInput v-model="state.lastName"/>
        </UFormGroup>

        <UFormGroup class="col-span-1 !mt-0" label="Tên" name="firstName">
          <UInput v-model="state.firstName"/>
        </UFormGroup>

        <UFormGroup class="col-span-1 !mt-0" label="Ngày sinh" name="birthday">
          <UPopover :popper="{ placement: 'bottom-start' }">
            <UButton color="gray" variant="solid" class="w-full" icon="i-heroicons-calendar-days-20-solid"
                     :label="state.birthday ? moment(state.birthday).format('DD-MM-YYYY') : ''"/>
            <template #panel="{ close }">
              <date-picker v-model="state.birthday" @close="close"/>
            </template>
          </UPopover>
        </UFormGroup>

        <div class="col-span-3 grid grid-cols-2 gap-x-5">
          <UFormGroup class="col-span-1 !mt-0" label="Email" name="email">
            <UInput v-model="state.email"/>
          </UFormGroup>

          <UFormGroup class="col-span-1 !mt-0" label="Số điện thoại" name="phoneNumber">
            <UInput v-model="state.phoneNumber"/>
          </UFormGroup>
        </div>

        <UFormGroup class="col-span-1" label="Tỉnh/Thành phố" name="city" option-attribute="name">
          <USelectMenu v-model="state.city" :options="state.listCity">
            <template #label>
              <span v-if="state.city" class="truncate">{{ state.city ? (state.city as any).province_name : '' }}</span>
            </template>
            <template #option="{ option }">
              <span class="truncate">{{ option.province_name }}</span>
            </template>
          </USelectMenu>
        </UFormGroup>

        <UFormGroup class="col-span-1" label="Quận/Huyện" name="district">
          <USelectMenu v-model="state.district" :options="state.listDistrict" :disabled="!state.city" option-attribute="name">
            <template #option="{ option }">
              <span class="truncate">{{ option }}</span>
            </template>
          </USelectMenu>
        </UFormGroup>

        <UFormGroup class="col-span-1" label="Xã/Phường" name="ward">
          <USelectMenu v-model="state.ward" :options="state.listWard" :disabled="!state.district"/>
        </UFormGroup>

        <UFormGroup class="col-span-3" label="Địa chỉ" name="address">
          <UTextarea v-model="state.address"/>
        </UFormGroup>

        <UButton class="col-span-2 text-center" type="submit">
          Cập nhật thông tin tài khoản
        </UButton>
      </UForm>
    </div>
  </div>
</template>

<script lang="ts">
import type {FormError, FormSubmitEvent} from '#ui/types'
import moment from "moment";
import {getListCity} from "~/server/services/api-provinces-open-api-vn";

export default {
  setup() {
    definePageMeta({
      layout: 'cms'
    })
  },
  data() {
    return {
      moment: moment,
      state: reactive({
        firstName: undefined,
        lastName: undefined,
        birthday: undefined,
        email: undefined,
        phoneNumber: undefined,
        city: undefined,
        district: undefined,
        ward: undefined,
        address: undefined,
        listCity: [],
        listDistrict: [],
        listWard: [],
      }),
      validate: (state: any): FormError[] => {
        const errors = []
        if (!state.firstName) errors.push({path: 'firstName', message: '*Không được để trống!'})
        if (!state.lastName) errors.push({path: 'lastName', message: '*Không được để trống!'})
        if (!state.birthday) errors.push({path: 'birthday', message: '*Không được để trống!'})
        if (!state.email) errors.push({path: 'email', message: '*Không được để trống!'})
        if (!state.phoneNumber) errors.push({path: 'phoneNumber', message: '*Không được để trống!'})
        return errors
      }
    }
  },
  created() {
    getListCity().then((res: any) => {
      if (res.data) {
        this.state.listCity = res.data.results
        console.log(this.state.listCity)
      }
    })
  },
  methods: {
    async onSubmit(event: FormSubmitEvent<any>) {
      // Do something with data
      console.log(event.data)
    }
  }
}
</script>