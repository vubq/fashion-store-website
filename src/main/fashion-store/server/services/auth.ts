import axios from "~/utils/axios";

export const signIn = (data: any) =>
  axios({
    url: '/auth/sign-in',
    method: 'post',
    data
  })
