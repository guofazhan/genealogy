import Vue from 'vue';
import VueI18n from 'vue-i18n';
import Cookies from 'js-cookie';
// element-ui lang
import elementEnLocale from 'element-ui/lib/locale/lang/en';
// element-ui lang
import elementZhLocale from 'element-ui/lib/locale/lang/zh-CN';
//本地资源信息
import zhLocale from './zh';
import enLocale from './en';

Vue.use(VueI18n);

//合并资源
const messages = {
  en: {
    ...enLocale,
    ...elementEnLocale
  },
  zh: {
    ...zhLocale,
    ...elementZhLocale
  }
}
const i18n = new VueI18n({
  // set locale
  locale: Cookies.get('language') || 'zh',
  // set locale messages
  messages
})
export default i18n;
