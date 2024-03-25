import {useSystemStore} from "#imports";


export default defineNuxtRouteMiddleware((to, from) => {
  useSystemStore().setIsLoading(true)
  const isAdmin = to.meta.isAdmin ? to.meta.isAdmin : false
  if (isAdmin) {
    const {isSignIn} = useAuthStore()
    console.log(isSignIn)
    if (!isSignIn) {
      navigateTo('/admins/sign-in', {external: true})
    }
  }
  useSystemStore().setIsLoading(false)
})