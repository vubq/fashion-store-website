import {config, library} from '@fortawesome/fontawesome-svg-core'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {faGoogle} from '@fortawesome/free-brands-svg-icons'

library.add(
  faGoogle,
)

config.autoAddCss = false

export default defineNuxtPlugin(({vueApp}) => {
  vueApp.component('FontAwesomeIcon', FontAwesomeIcon)
})