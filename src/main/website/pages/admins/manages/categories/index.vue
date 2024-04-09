<script lang="ts" setup>
import type {User} from '~/types'
import {Category} from '~/models/Category'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {ResponseCode} from '~/models/enums/ResponseCode'
import {createOrUpdateCategory, getAllCategory, getCategoryById} from '~/server/services/category'
import moment from 'moment'
import {debounce} from 'perfect-debounce'
import type {FormError} from '#ui/types'

definePageMeta({
  layout: 'admin',
  isAdmin: true
})

const input = ref<{ input: HTMLInputElement }>()
defineShortcuts({
  '/': () => {
    input.value?.input?.focus()
  }
})

const defaultColumns = [{
  key: 'index',
  label: 'STT'
}, {
  key: 'name',
  label: 'Tên',
  sortable: true
}, {
  key: 'description',
  label: 'Mô tả',
  sortable: true
}, {
  key: 'createdAt',
  label: 'Ngày tạo',
  sortable: true
}, {
  key: 'createdBy.name',
  label: 'Người tạo'
}, {
  key: 'updatedAt',
  label: 'Ngày cập nhật',
  sortable: true
}, {
  key: 'updatedBy.name',
  label: 'Người cập nhật'
}, {
  key: 'status',
  label: 'Trạng thái'
}, {
  key: 'action',
}]
const selected = ref<User[]>([])
const selectedColumns = ref(defaultColumns)
const columns = computed(() => defaultColumns.filter((column) => selectedColumns.value.includes(column)))
const dataTableRequest = ref<DataTableRequest>(new DataTableRequest())
const totalRows = ref(0)
const listCategory = ref<Category[]>([])
const status = ref<Status[]>([])
const statusList = ref<Status[]>([Status.ACTIVE, Status.IN_ACTIVE])
const sort = ref({column: 'createdAt', direction: 'desc' as const})
let state = reactive(new Category)
const isModalCreateOrUpdateOpen = ref(false)

function onSelect(row: User) {
  const index = selected.value.findIndex((item) => item.id === row.id)
  if (index === -1) {
    selected.value.push(row)
  } else {
    selected.value.splice(index, 1)
  }
}

const validate = (state: any): FormError[] => {
  const errors = []
  if (!state.name) errors.push({path: 'name', message: '*Tên Danh mục không được để trống'})
  if (!state.description) errors.push({path: 'description', message: '*Mô tả không được để trống'})
  if (!state.status) errors.push({path: 'status', message: '*Trạn thái không được để trống'})
  return errors
}

onMounted(() => {
  reloadDataTable()
})

const getAll = async () => {
  await getAllCategory(dataTableRequest.value, status.value)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        let indexDefault = (dataTableRequest.value.currentPage - 1) * 10 + 1
        listCategory.value = res.data.items.map((e: any) => {
          return {...e, index: '#' + indexDefault++}
        })
        totalRows.value = res.data.totalRows
      }
    })
}

const showTheSelectedStatus = () => {
  if (status.value.length === statusList.value.length || status.value.length === 0) {
    return 'Tất cả'
  }
  let statusString = ''
  status.value.forEach((s: any) => {
    statusString += s === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động'
  })
  return statusString
}

const reloadDataTable = () => {
  dataTableRequest.value.currentPage = 1
  changeSort()
  getAll()
}

const changeSort = () => {
  dataTableRequest.value.sortBy = sort.value.column
  dataTableRequest.value.sortDesc = sort.value.direction === 'desc'
}

const changeFilter = debounce(() => {
  reloadDataTable()
}, 500)

watch(() => sort.value, () => {
  reloadDataTable()
})

watch(() => dataTableRequest.value.currentPage, () => {
  changeSort()
  getAll()
})

const openModalCreateOrUpdate = () => {
  isModalCreateOrUpdateOpen.value = true
  state = new Category()
  state.status = Status.ACTIVE
}

const createOrUpdate = () => {
  createOrUpdateCategory(state)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        isModalCreateOrUpdateOpen.value = false
        if(state.id) {
          getAll()
        } else {
          reloadDataTable()
        }
      }
    })
}

const showCategoryDetail = (categoryId: string) => {
  getCategoryById(categoryId)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        state = res.data.data
        isModalCreateOrUpdateOpen.value = true
      }
    })
}
</script>

<template>
  <UDashboardPage>
    <UDashboardPanel grow>
      <UDashboardNavbar title="Danh mục" :badge="totalRows">
        <template #right>
          <UInput
            ref="input"
            v-model="dataTableRequest.filter"
            icon="i-heroicons-funnel"
            autocomplete="off"
            placeholder="Tìm kiếm Danh mục..."
            class="hidden lg:block"
            @keyup="changeFilter()"
          >
            <template #trailing>
              <UKbd value="/"/>
            </template>
          </UInput>

          <UButton label="Thêm mới" trailing-icon="i-heroicons-plus" color="gray" @click="openModalCreateOrUpdate()"/>
        </template>
      </UDashboardNavbar>

      <UDashboardToolbar>
        <template #left>
          <USelectMenu
            v-model="status"
            placeholder="Trạng thái"
            multiple
            :options="statusList"
            @change="reloadDataTable()"
          >
            <template #label>
              Trạng thái: {{ showTheSelectedStatus() }}
            </template>

            <template #option="{ option }">
              <span>{{ option === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động' }}</span>
            </template>
          </USelectMenu>
        </template>

        <template #right>
          <USelectMenu
            v-model="selectedColumns"
            icon="i-heroicons-adjustments-horizontal-solid"
            :options="defaultColumns"
            multiple
            class="hidden lg:block"
          >
            <template #label>
              Hiển thị
            </template>
          </USelectMenu>
        </template>
      </UDashboardToolbar>

      <UDashboardModal
        v-model="isModalCreateOrUpdateOpen"
        :title="state.id ? 'Cập nhật Danh mục' : 'Thêm mới Danh mục'"
        :ui="{ width: 'sm:max-w-md' }"
        prevent-close
      >
        <UForm
          :validate="validate"
          :validate-on="['submit']"
          :state="state"
          class="space-y-4"
          @submit="createOrUpdate()"
        >
          <UFormGroup label="Tên Danh mục" name="name">
            <UInput v-model="state.name" placeholder="Quần, Áo,..." autofocus/>
          </UFormGroup>

          <UFormGroup label="Mô tả" name="description">
            <UInput v-model="state.description" placeholder="Quần, Áo,..."/>
          </UFormGroup>

          <UFormGroup label="Trạng thái" name="status">
            <USelectMenu
              v-model="state.status"
              :options="statusList"
            >
              <template #label>
                {{ state.status === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động' }}
              </template>

              <template #option="{ option }">
                <span>{{ option === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động' }}</span>
              </template>
            </USelectMenu>
          </UFormGroup>

          <div class="flex justify-end gap-3">
            <UButton label="Hủy" color="gray" variant="ghost"/>
            <UButton type="submit" :label="state.id ? 'Cập nhật' : 'Thêm mới'" color="primary"/>
          </div>
        </UForm>
      </UDashboardModal>

      <UTable
        v-model:sort="sort"
        :rows="listCategory"
        :columns="columns"
        sort-mode="manual"
        class="w-full"
        :ui="{ divide: 'divide-gray-200 dark:divide-gray-800' }"
        :empty-state="{ icon: 'i-heroicons-circle-stack-20-solid', label: 'Không có dữ liệu.' }"
        @select="onSelect"
      >
        <template #createdAt-data="{ row }">
          <span>{{ moment(row.createdAt).format('DD/MM/YYYY HH:mm:ss') }}</span>
        </template>

        <template #updatedAt-data="{ row }">
          <span>{{ row.updatedAt ? moment(row.updatedAt).format('DD/MM/YYYY HH:mm:ss') : '' }}</span>
        </template>

        <template #status-data="{ row }">
          <UBadge color="green" variant="outline" v-if="row.status === Status.ACTIVE"> Hoạt động</UBadge>
          <UBadge color="red" variant="outline" v-else> Ngừng hoạt động</UBadge>
        </template>

        <template #action-data="{ row }">
          <UButton color="primary" variant="outline" @click="showCategoryDetail(row.id)">
            <UIcon name="i-heroicons-eye-16-solid"></UIcon>
          </UButton>
        </template>
      </UTable>

      <UDivider/>

      <UDashboardToolbar>
        <template #left>
          <USelectMenu
            v-model="dataTableRequest.perPage"
            :options="[10, 20, 30, 40, 50]"
            @change="reloadDataTable()"
          >
            <template #label>
              <span>{{ dataTableRequest.perPage }}/Trang</span>
            </template>
          </USelectMenu>
          <UPagination
            v-if="totalRows > 0"
            v-model="dataTableRequest.currentPage"
            :active-button="{ variant: 'outline' }"
            :page-count="dataTableRequest.perPage"
            :total="totalRows"
          />
        </template>
      </UDashboardToolbar>
    </UDashboardPanel>
  </UDashboardPage>
</template>
