import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'

export const getAllCategory = (params: DataTableRequest, data: Status[]) =>
  axios({
    url: '/category/get-all',
    method: 'post',
    params,
    data
  })
