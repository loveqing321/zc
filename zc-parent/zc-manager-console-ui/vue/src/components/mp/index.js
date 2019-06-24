import mpCol from './mp-col/index'
import mpContainer from './mp-container/index'
import mpInput from './mp-input/index'
import mpLink from './mp-link/index'
import mpMenu from './mp-menu/index'
import mpSubmenu from './mp-menu/mp-submenu'
import mpMenuItem from './mp-menu/mp-menu-item'
import mpRouteMenu from './mp-menu/mp-route-menu'
import mpPanel from './mp-panel/index'
import mpRow from './mp-row/index'
import mpScroll from './mp-scroll/index'
import mpSelect from './mp-select/index'
import mpTitle from './mp-title/index'
import mpCollapseTransition from './mp-transition/mp-collapse-transition'
import mpFadeTransition from './mp-transition/mp-fade-transition'
import mpFadeTransitionGroup from './mp-transition/mp-fade-transition-group'

export default function install (Vue, options) {
  const components = [ mpCol, mpContainer, mpInput, mpLink, mpMenu, mpSubmenu, mpMenuItem, mpRouteMenu, mpPanel, mpRow, mpScroll, mpSelect, mpTitle, mpCollapseTransition, mpFadeTransition, mpFadeTransitionGroup ]
  components.forEach(component => {
    Vue.component(component.name, component)
  })
}
