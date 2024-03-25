<script lang="ts" setup>
import type {User} from '~/types'
import {Category} from '~/models/Category'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {ResponseCode} from '~/models/enums/ResponseCode'
import {getAllCategory} from '~/server/services/category'
import moment from 'moment'
import {debounce} from 'perfect-debounce'

definePageMeta({
  layout: 'admin',
  isAdmin: true
})

const defaultColumns = [{
  key: 'id',
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
  key: 'createdBy',
  label: 'Người tạo'
}, {
  key: 'status',
  label: 'Trạng thái'
}]

const q = ref('')
const selected = ref<User[]>([])
const selectedColumns = ref(defaultColumns)
// const selectedStatuses = ref([])
// const selectedLocations = ref([])
const sort = ref({column: 'createdAt', direction: 'desc' as const})
const input = ref<{ input: HTMLInputElement }>()
const isNewUserModalOpen = ref(false)

const columns = computed(() => defaultColumns.filter((column) => selectedColumns.value.includes(column)))

// const query = computed(() => ({
//   q: q.value,
//   statuses: selectedStatuses.value,
//   locations: selectedLocations.value,
//   sort: sort.value.column,
//   order: sort.value.direction
// }))

// const {data: users, pending} = await useFetch<User[]>('/api/users', {query, default: () => []})
//
// const defaultLocations = users.value.reduce((acc, user) => {
//   if (!acc.includes(user.location)) {
//     acc.push(user.location)
//   }
//   return acc
// }, [] as string[])
//
// const defaultStatuses = users.value.reduce((acc, user) => {
//   if (!acc.includes(user.status)) {
//     acc.push(user.status)
//   }
//   return acc
// }, [] as string[])

function onSelect(row: User) {
  console.log('aassaa')
  const index = selected.value.findIndex((item) => item.id === row.id)
  if (index === -1) {
    selected.value.push(row)
  } else {
    selected.value.splice(index, 1)
  }
}

defineShortcuts({
  '/': () => {
    input.value?.input?.focus()
  }
})

const dataTableRequest = ref<DataTableRequest>(new DataTableRequest())
const totalRows = ref(0)
const listCategory = ref<Category[]>([])
const status = ref<Status[]>([])
const listStatus = ref<Status[]>([Status.ACTIVE, Status.IN_ACTIVE])
const timeOut = ref(null)
const timer = ref(300)

onMounted(()=> {
  getAll()
})

const getAll = async () => {
  await getAllCategory(dataTableRequest.value, Status.ACTIVE)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        listCategory.value = res.data.items
        totalRows.value = res.data.totalRows
      }
    })
}

const showTheSelectedStatus = () => {
  if(status.value.length === listStatus.value.length || status.value.length === 0) {
    return 'Tất cả'
  }
  let statusString = ''
  status.value.forEach((s: any, i: any) => {
    // if(i == 0) {
    //   statusString += s === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động'
    // }
    // statusString += ', ' + s === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động'
    statusString += s === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động'
  })
  return statusString
}

const reloadDataTable = () => {
  changeSort()
  getAll()
}

const changeSort = () => {
  dataTableRequest.value.sortBy = sort.value.column
  dataTableRequest.value.sortDesc = sort.value.direction === 'desc'
}

const changeFilter = debounce(() => {
  reloadDataTable()
}, 650)

watch(() => sort.value, () => {
  reloadDataTable()
})

watch(() => dataTableRequest.value.currentPage, () => {
  reloadDataTable()
})

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
            placeholder="Tìm kiếm danh mục..."
            class="hidden lg:block"
            @keyup="changeFilter()"
          >
            <template #trailing>
              <UKbd value="/" />
            </template>
          </UInput>

          <UButton label="Thêm mới" trailing-icon="i-heroicons-plus" color="gray" @click="isNewUserModalOpen = true"/>
        </template>
      </UDashboardNavbar>

      <UDashboardToolbar>
        <template #left>
          <USelectMenu
            v-model="status"
            placeholder="Trạng thái"
            multiple
            :options="listStatus"
            @change="reloadDataTable()"
          >
            <template #label>
              Trạng thái: {{ showTheSelectedStatus() }}
            </template>

            <template #option="{ option }">
              <span>{{option === Status.ACTIVE ? 'Hoạt động' : 'Ngừng hoạt động'}}</span>
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
        v-model="isNewUserModalOpen"
        title="New user"
        description="Add a new user to your database"
        :ui="{ width: 'sm:max-w-md' }"
      >
        <!-- ~/components/users/UsersForm.vue -->
        <UsersForm @close="isNewUserModalOpen = false" />
      </UDashboardModal>

      <UTable
        v-model="selected"
        v-model:sort="sort"
        :rows="listCategory"
        :columns="columns"
        sort-mode="manual"
        class="w-full"
        :ui="{ divide: 'divide-gray-200 dark:divide-gray-800' }"
        @select="onSelect"
      >
<!--        <template #stt-data="row">-->
<!--          <span>{{ row.$index + 1 }}</span>-->
<!--        </template>-->

        <template #createdAt-data="{ row }">
          <span>{{ moment(row.createdAt).format('DD/MM/YYYY HH:mm:ss') }}</span>
        </template>

        <template #status-data="{ row }">
          <UBadge color="green" variant="outline" v-if="row.status === Status.ACTIVE">Hoạt động</UBadge>
          <UBadge color="red" variant="outline" v-else>Ngừng hoạt động</UBadge>
        </template>
      </UTable>

      <UDivider />

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
