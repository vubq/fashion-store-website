import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {ProductFilterModel} from '~/models/ProductFilterModel'

export const getAllProduct = (params: DataTableRequest, data: ProductFilterModel) =>
  axios({
    url: '/product/get-all',
    method: 'post',
    params,
    data
  })
