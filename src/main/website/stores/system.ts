import {acceptHMRUpdate, defineStore} from 'pinia'

export const useSystemStore = defineStore('system', {
  state: () => ({
    isLoading: false
  }),

  actions: {
    setIsLoading(loading: boolean) {
      this.isLoading = loading
    }
  }
})

if (import.meta.hot) {
  import.meta.hot.accept(acceptHMRUpdate(useSystemStore, import.meta.hot))
}
