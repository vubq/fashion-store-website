import {useAuthStore, useSystemStore} from '#imports'

export default defineNuxtRouteMiddleware(async (to, from) => {
  // useSystemStore().setIsLoading(true)
  const isAdmin = to.meta.isAdmin ? to.meta.isAdmin : false
  if (isAdmin) {
    const {isSignIn} = useAuthStore()
    if (!isSignIn) {
      navigateTo('/admins/sign-in', {external: true})
    }
  }
  // useSystemStore().setIsLoading(false)
})
