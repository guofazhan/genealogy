package com.genealogy.admin.configure.datasource;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DatasourceOptions {

	/**
	 * 数据源名称
	 */
	private String key;

	/**
	 * 驱动class名
	 */
	private String driverClassName;

	/**
	 * URL
	 */
	private String url;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 初始化时建立物理连接的个数。
	 * 初始化发生在显示调用init方法，或者第一次getConnection时
	 */
	private int initialSize = 1;

	/**
	 * 最小连接池数量
	 */
	private int minIdle;

	/**
	 * 最大连接池数量
	 */
	private int maxActive = 8;

	/**
	 * 获取连接时最大等待时间，单位毫秒。
	 * 配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，
	 * 如果需要可以通过配置useUnfairLock属性为true使用非公平锁
	 */
	private long maxWait = -1L;

	/**
	 * 有两个含义：
	 * 1) Destroy线程会检测连接的间隔时间2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
	 */
	private long timeBetweenEvictionRunsMillis;

	/**
	 *
	 */
	private long minEvictableIdleTimeMillis;

	/**
	 * 用来检测连接是否有效的sql，要求是一个查询语句。
	 * 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
	 */
	private String validationQuery;

	/**
	 * 建议配置为true，不影响性能，并且保证安全性。
	 * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，
	 * 执行validationQuery检测连接是否有效
	 */
	private boolean testWhileIdle;

	/**
	 * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
	 */
	private boolean testOnBorrow;

	/**
	 * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
	 */
	private boolean testOnReturn;

	/**
	 *
	 */
	private boolean removeAbandoned;

	/**
	 *
	 */
	private long removeAbandonedTimeout;
	/**
	 *
	 */
	private boolean logAbandoned;

	/**
	 * 是否缓存preparedStatement，也就是PSCache。
	 * PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭
	 */
	private boolean poolPreparedStatements = false;

	/**
	 * 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
	 * 监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
	 */
	private String filters;

	/**
	 * 物理连接初始化的时候执行的sql
	 */
	private String connectionProperties;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(
			long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public boolean isRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandoned(boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public long getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(long removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public boolean isLogAbandoned() {
		return logAbandoned;
	}

	public void setLogAbandoned(boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	@Override
	public String toString() {
		return "DatasourceOptions{" + "key='" + key + '\''
				+ ", driverClassName='" + driverClassName + '\'' + ", url='"
				+ url + '\'' + ", username='" + username + '\'' + ", password='"
				+ password + '\'' + ", initialSize=" + initialSize
				+ ", minIdle=" + minIdle + ", maxActive=" + maxActive
				+ ", maxWait=" + maxWait + ", timeBetweenEvictionRunsMillis='"
				+ timeBetweenEvictionRunsMillis + '\''
				+ ", minEvictableIdleTimeMillis='" + minEvictableIdleTimeMillis
				+ '\'' + ", validationQuery='" + validationQuery + '\''
				+ ", testWhileIdle=" + testWhileIdle + ", testOnBorrow="
				+ testOnBorrow + ", testOnReturn=" + testOnReturn
				+ ", removeAbandoned=" + removeAbandoned
				+ ", removeAbandonedTimeout=" + removeAbandonedTimeout
				+ ", logAbandoned=" + logAbandoned + ", poolPreparedStatements="
				+ poolPreparedStatements + ", filters='" + filters + '\''
				+ ", connectionProperties='" + connectionProperties + '\''
				+ '}';
	}
}
