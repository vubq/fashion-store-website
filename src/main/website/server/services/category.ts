import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {Category} from '~/models/Category'

export const getAllCategory = (params: DataTableRequest, data: Status[]) =>
  axios({
    url: '/category/get-all',
    method: 'post',
    params,
    data
  })

export const getCategoryById = (categoryId: String) =>
  axios({
    url: '/category/' + categoryId,
    method: 'get',
  })

export const getAllCategoryByStatus = (status: Status) =>
  axios({
    url: '/category/get-all/' + status,
    method: 'get'
  })

export const createOrUpdateCategory = (data: Category) =>
  axios({
    url: '/category',
    method: 'post',
    data
  })
