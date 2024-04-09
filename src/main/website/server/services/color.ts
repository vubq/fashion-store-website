import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {Color} from '~/models/Color'

export const getAllColor = (params: DataTableRequest, data: Status[]) =>
  axios({
    url: '/color/get-all',
    method: 'post',
    params,
    data
  })

export const getColorById = (colorId: String) =>
  axios({
    url: '/color/' + colorId,
    method: 'get',
  })

export const createOrUpdateColor = (data: Color) =>
  axios({
    url: '/color',
    method: 'post',
    data
  })

export const getAllColorByStatus = (status: Status) =>
  axios({
    url: '/color/get-all/' + status,
    method: 'get'
  })
