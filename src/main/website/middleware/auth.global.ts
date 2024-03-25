import {useAuthStore} from '#imports'

export default defineNuxtRouteMiddleware(async (to, from) => {
  const isAdmin = to.meta.isAdmin ? to.meta.isAdmin : false
  if (isAdmin) {
    const {isSignIn} = useAuthStore()
    if (!isSignIn) {
      navigateTo('/admins/sign-in', {external: true})
    }
  }
})
