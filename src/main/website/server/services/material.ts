import axios from '~/utils/axios'
import {DataTableRequest} from '~/models/DataTableRequest'
import {Status} from '~/models/enums/Status'
import {Material} from '~/models/Material'

export const getAllMaterial = (params: DataTableRequest, data: Status[]) =>
  axios({
    url: '/material/get-all',
    method: 'post',
    params,
    data
  })

export const getMaterialById = (materialId: String) =>
  axios({
    url: '/material/' + materialId,
    method: 'get',
  })

export const createOrUpdateMaterial = (data: Material) =>
  axios({
    url: '/material',
    method: 'post',
    data
  })

export const getAllMaterialByStatus = (status: Status) =>
  axios({
    url: '/material/get-all/' + status,
    method: 'get'
  })
