import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {Size} from '~/models/Size'

export const getAllSize = (params: DataTableRequest, data: Status[]) =>
  axios({
    url: '/size/get-all',
    method: 'post',
    params,
    data
  })

export const getSizeById = (sizeId: String) =>
  axios({
    url: '/size/' + sizeId,
    method: 'get',
  })

export const createOrUpdateSize = (data: Size) =>
  axios({
    url: '/size',
    method: 'post',
    data
  })

export const getAllSizeByStatus = (status: Status) =>
  axios({
    url: '/size/get-all/' + status,
    method: 'get'
  })
