import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {Brand} from '~/models/Brand'

export const getAllBrand = (params: DataTableRequest, data: Status[]) =>
  axios({
    url: '/brand/get-all',
    method: 'post',
    params,
    data
  })

export const getBrandById = (brandId: String) =>
  axios({
    url: '/brand/' + brandId,
    method: 'get',
  })

export const createOrUpdateBrand = (data: Brand) =>
  axios({
    url: '/brand',
    method: 'post',
    data
  })

export const getAllBrandByStatus = (status: Status) =>
  axios({
    url: '/brand/get-all/' + status,
    method: 'get'
  })
