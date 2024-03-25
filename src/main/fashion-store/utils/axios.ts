import axios from "axios";
import destr from "destr";

const service = axios.create({
  baseURL: 'http://localhost:8080/webapi/'
})

service.interceptors.request.use(
  (config) => {
    const token = destr(useCookie('auth').value) ? (destr(useCookie('auth').value) as any).token : null
    if (token) {
      config.headers["Authorization"] = 'Bearer ' + token
    }
    return config
  },

  (error) => {
    return Promise.reject(error)
  }
);

export default service