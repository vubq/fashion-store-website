import {defineStore} from "pinia";
import {signIn} from "~/server/services/auth";
import {ResponseCode} from "~/models/enums/ResponseCode";
import destr from "destr";
import {useSystemStore} from "~/stores/system";

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isSignIn: useCookie('auth').value && (destr(useCookie('auth').value) as any).user,
    token: useCookie('auth').value ? (destr(useCookie('auth').value) as any).token : null,
    type: useCookie('auth').value ? (destr(useCookie('auth').value) as any).type : null,
    user: useCookie('auth').value ? (destr(useCookie('auth').value) as any).user : null,
  }),

  actions: {
    async signIn(data: any) {
      useSystemStore().setIsLoading(true)
      await signIn(data).then((res: any) => {
        if (res.data.code === ResponseCode.CODE_SUCCESS && res.data.data) {
          this.isSignIn = true
          this.token = res.data.data.token
          this.user = res.data.data.user
          this.type = res.data.data.type
          useCookie('auth').value = JSON.stringify(res.data.data)
        }
      }).finally(() => useSystemStore().setIsLoading(false))
    },

    signOut() {
      useCookie('auth').value = null
      this.isSignIn = false
      this.token = null
      this.user = null
      this.type = null
      navigateTo('/admins/sign-in', {external: true})
    },
  },
});