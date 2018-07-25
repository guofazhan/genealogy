import Vue from 'vue';

// A modern alternative to CSS resets
import 'normalize.css/normalize.css';

//element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// global css
import '@/styles/index.scss'
import App from './App'
import router from './router'
import store from './store'
//i18n
import i18n from './lang';
// icon
import '@/icons';
// permission control
import '@/permission';

Vue.use(ElementUI, {
  size: 'medium',
  i18n: (key, value) => i18n.t(key, value)
});

Vue.config.productionTip = false;

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: { App }
});
