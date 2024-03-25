import axios from "~/utils/axios";

export const getListCity = () =>
  axios({
    baseURL: '',
    url: 'https://vapi.vnappmob.com/api/province/',
    method: 'get'
  })

export const getListDistrict = (cityCode: any) =>
  axios({
    baseURL: '',
    url: 'https://provinces.open-api.vn/api/p/' + cityCode + '?depth=2',
    method: 'get'
  })

export const getListWard = (districtCode: any) =>
  axios({
    baseURL: '',
    url: 'https://provinces.open-api.vn/api/d/' + districtCode + '?depth=2',
    method: 'get'
  })