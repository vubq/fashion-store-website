import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'

export const getAllCategory = (params: DataTableRequest, status: Status) =>
  axios({
    url: '/category/get-all' + '?status=' + status,
    method: 'get',
    params,
  })
