/**
 * 获取路由标题信息
 * @param title
 * @returns {*}
 */
export function getRouteTitle(title) {
  return  this.$te('route.'+title) ? this.$t('route.'+title) : title;
}
