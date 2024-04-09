<script lang="ts" setup>
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {ResponseCode} from '~/models/enums/ResponseCode'
import {getAllProduct} from '~/server/services/product'
import moment from 'moment'
import {debounce} from 'perfect-debounce'
import type {FormError} from '#ui/types'
import {Product} from '~/models/Product'
import {ProductFilterModel} from '~/models/ProductFilterModel'
import {Size} from '~/models/Size'
import {Color} from '~/models/Color'
import {Material} from '~/models/Material'
import {Category} from '~/models/Category'
import {Brand} from '~/models/Brand'
import {getAllCategoryByStatus} from '~/server/services/category'
import {getAllBrandByStatus} from '~/server/services/brand'
import {getAllSizeByStatus} from '~/server/services/size'
import {getAllColorByStatus} from '~/server/services/color'
import {getAllMaterialByStatus} from '~/server/services/material'

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
  label: 'Tên sản phẩm',
  sortable: true
}, {
  key: 'description',
  label: 'Mô tả',
  sortable: true
}, {
  key: 'category.name',
  label: 'Danh mục',
  sortable: true
}, {
  key: 'brand.name',
  label: 'Thương hiệu',
  sortable: true
}, {
  key: 'sizes',
  label: 'Kích cỡ',
  sortable: true
}, {
  key: 'colors',
  label: 'Màu sắc',
  sortable: true
}, {
  key: 'materials',
  label: 'Chất liệu',
  sortable: true
}, {
  key: 'status',
  label: 'Trạng thái'
}, {
  key: 'action',
}]
const selectedColumns = ref(defaultColumns)
const columns = computed(() => defaultColumns.filter((column) => selectedColumns.value.includes(column)))
const dataTableRequest = ref<DataTableRequest>(new DataTableRequest())
const productFilter = ref<ProductFilterModel>(new ProductFilterModel())
const totalRows = ref(0)
const listProduct = ref<Product[]>([])
const status = ref<Status[]>([])
const statusList = ref<Status[]>([Status.ACTIVE, Status.IN_ACTIVE])
const sort = ref({column: 'createdAt', direction: 'desc' as const})
let state = reactive(new Product)
const isModalCreateOrUpdateOpen = ref(false)

const listCategory = ref<Category[]>([])
const listBrand = ref<Brand[]>([])
const listSize = ref<Size[]>([])
const listColor = ref<Color[]>([])
const listMaterial = ref<Material[]>([])

const listFilterSelectedCategory = ref<Category[]>([])
const listFilterSelectedBrand = ref<Brand[]>([])
const listFilterSelectedSize = ref<Size[]>([])
const listFilterSelectedColor = ref<Color[]>([])
const listFilterSelectedMaterial = ref<Material[]>([])

const selectedCategory = ref<Category>()
const selectedBrand = ref<Brand>()
const listSelectedSize = ref<Size[]>([])
const listSelectedColor = ref<Color[]>([])
const listSelectedMaterial = ref<Material[]>([])

const validate = (state: any): FormError[] => {
  const errors = []
  if (!state.name) errors.push({path: 'name', message: '*Tên Danh mục không được để trống'})
  if (!state.description) errors.push({path: 'description', message: '*Mô tả không được để trống'})
  if (!state.status) errors.push({path: 'status', message: '*Trạn thái không được để trống'})
  return errors
}

onMounted(() => {
  reloadDataTable()
  getListCategory()
  getListBrand()
  getListSize()
  getListColor()
  getListMaterial()
})

const getListCategory = async () => {
  await getAllCategoryByStatus(Status.ACTIVE)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        listCategory.value = res.data.data
      }
    })
}

const getListBrand = async () => {
  await getAllBrandByStatus(Status.ACTIVE)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        listBrand.value = res.data.data
      }
    })
}

const getListSize = async () => {
  await getAllSizeByStatus(Status.ACTIVE)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        listSize.value = res.data.data
      }
    })
}

const getListColor = async () => {
  await getAllColorByStatus(Status.ACTIVE)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        listColor.value = res.data.data
      }
    })
}

const getListMaterial = async () => {
  await getAllMaterialByStatus(Status.ACTIVE)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        listMaterial.value = res.data.data
      }
    })
}

const getAll = async () => {
  productFilter.value.categories = listFilterSelectedCategory.value.map((c: Category) => c.id) as string[]
  productFilter.value.brands = listFilterSelectedBrand.value.map((b: Brand) => b.id) as string[]
  productFilter.value.sizes = listFilterSelectedSize.value.map((s: Size) => s.id) as string[]
  productFilter.value.colors = listFilterSelectedColor.value.map((c: Color) => c.id) as string[]
  productFilter.value.materials = listFilterSelectedMaterial.value.map((m: Material) => m.id) as string[]
  await getAllProduct(dataTableRequest.value, productFilter.value)
    .then((res: any) => {
      if (res.data && res.data.code === ResponseCode.CODE_SUCCESS) {
        let indexDefault = (dataTableRequest.value.currentPage - 1) * 10 + 1
        listProduct.value = res.data.items.map((e: any) => {
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
  state = new Product()
}

const createOrUpdate = () => {

}

const genTextAttributeOnTable = (attribute: any) => {
  let result = ''
  attribute.forEach((a: any, index: number) => {
    result = index === attribute.length - 1 ? result + a.name : result + a.name + ', '
  })
  return result
}

const genTextFilterOnSelect = (selected: any, listSelect: any) => {
  if (selected.length === 0 || selected.length === listSelect.length) {
    return 'Tất cả'
  }
  let result = ''
  selected.forEach((s: any, index: number) => {
    result = index === selected.length - 1 ? result + s.name : result + s.name + ', '
  })
  return result
}

const items = [
  'https://lavenderstudio.com.vn/wp-content/uploads/2021/06/cach-chup-hinh-san-pham-quan-ao-dep-4.jpg',
  'https://picsum.photos/1920/1080?random=2',
  'https://picsum.photos/1920/1080?random=3',
  'https://picsum.photos/1920/1080?random=4',
  'https://picsum.photos/1920/1080?random=5',
  'https://picsum.photos/1920/1080?random=6',
  'https://picsum.photos/1920/1080?random=6',
  'https://picsum.photos/1920/1080?random=6'
]
</script>

<template>
  <UDashboardPage>
    <UDashboardPanel grow>
      <UDashboardNavbar title="Sản phẩm" :badge="totalRows">
        <template #right>
          <UInput
            ref="input"
            v-model="dataTableRequest.filter"
            icon="i-heroicons-funnel"
            autocomplete="off"
            placeholder="Tìm kiếm Sản phẩm..."
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
            v-model="listFilterSelectedCategory"
            placeholder="Danh mục"
            multiple
            :options="listCategory"
            @change="reloadDataTable()"
          >
            <template #label>
              Danh mục: {{ genTextFilterOnSelect(listFilterSelectedCategory, listCategory) }}
            </template>

            <template #option="{ option }">
              <span>{{ option.name }}</span>
            </template>
          </USelectMenu>

          <USelectMenu
            v-model="listFilterSelectedBrand"
            placeholder="Thương hiệu"
            multiple
            :options="listBrand"
            @change="reloadDataTable()"
          >
            <template #label>
              Thương hiệu: {{ genTextFilterOnSelect(listFilterSelectedBrand, listBrand) }}
            </template>

            <template #option="{ option }">
              <span>{{ option.name }}</span>
            </template>
          </USelectMenu>

          <USelectMenu
            v-model="listFilterSelectedSize"
            placeholder="Kích thước"
            multiple
            :options="listSize"
            @change="reloadDataTable()"
          >
            <template #label>
              Kích thước: {{ genTextFilterOnSelect(listFilterSelectedSize, listSize) }}
            </template>

            <template #option="{ option }">
              <span>{{ option.name }}</span>
            </template>
          </USelectMenu>

          <USelectMenu
            v-model="listFilterSelectedColor"
            placeholder="Màu sắc"
            multiple
            :options="listColor"
            @change="reloadDataTable()"
          >
            <template #label>
              Màu sắc: {{ genTextFilterOnSelect(listFilterSelectedColor, listColor) }}
            </template>

            <template #option="{ option }">
              <span>{{ option.name }}</span>
            </template>
          </USelectMenu>

          <USelectMenu
            v-model="listFilterSelectedMaterial"
            placeholder="Chất liệu"
            multiple
            :options="listMaterial"
            @change="reloadDataTable()"
          >
            <template #label>
              Chất liệu: {{ genTextFilterOnSelect(listFilterSelectedMaterial, listMaterial) }}
            </template>

            <template #option="{ option }">
              <span>{{ option.name }}</span>
            </template>
          </USelectMenu>

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
        title="Sản phẩm"
        :ui="{ width: 'sm:max-w-screen-lg' }"
        prevent-close
      >
        <div class="grid grid-cols-12">
          <div class="col-span-3">
            <UCarousel
              v-slot="{ item }"
              :items="items"
              :ui="{ item: 'basis-full' }"
              class="rounded-lg overflow-hidden"
              arrows
              :prev-button="{
                color: 'transparent',
                icon: 'i-heroicons-chevron-left',
              }"
              :next-button="{
                color: 'transparent',
                icon: 'i-heroicons-chevron-right',
              }"
            >
              <img :src="item" class="w-full" draggable="false">
            </UCarousel>
          </div>
          <div class="col-span-8"></div>
        </div>
      </UDashboardModal>

      <UTable
        v-model:sort="sort"
        :rows="listProduct"
        :columns="columns"
        sort-mode="manual"
        class="w-full"
        :ui="{ divide: 'divide-gray-200 dark:divide-gray-800' }"
        :empty-state="{ icon: 'i-heroicons-circle-stack-20-solid', label: 'Không có dữ liệu.' }"
      >
        <template #createdAt-data="{ row }">
          <span>{{ moment(row.createdAt).format('DD/MM/YYYY HH:mm:ss') }}</span>
        </template>

        <template #sizes-data="{ row }">
          <span>{{ genTextAttributeOnTable(row.sizes) }}</span>
        </template>

        <template #colors-data="{ row }">
          <span>{{ genTextAttributeOnTable(row.colors) }}</span>
        </template>

        <template #materials-data="{ row }">
          <span>{{ genTextAttributeOnTable(row.materials) }}</span>
        </template>

        <template #status-data="{ row }">
          <UBadge color="green" variant="outline" v-if="row.status === Status.ACTIVE"> Hoạt động</UBadge>
          <UBadge color="red" variant="outline" v-else> Ngừng hoạt động</UBadge>
        </template>

        <template #action-data="{ row }">
          <UButton color="primary" variant="outline">
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
