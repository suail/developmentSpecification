package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JdbcUtil {
	private static Properties pros = null;

	// 私有化构造函数，防止实例化
	private JdbcUtil() {}

	// 静态代码块，只实现一次
	static {
		pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提供对外接口，创建MySQL数据库连接
	 * 
	 * @param database
	 *            方便跨数据库开发，如果是null就操作配置文件中所配置的数据库，如果不是null则重新生成一个指向目标数据库的URL连接
	 * @return 数据库连接
	 */
	public static Connection getMysqlConnection(String database) {
		try {
			Class.forName(pros.getProperty("mysqlDriver"));
			String URL = pros.getProperty("mysqlUrl");
			if (null != database) {
				Matcher m = Pattern.compile("(.*[/])(.*)(\\?.*)").matcher(URL);
				m.find();
				URL = m.group(1) + database + m.group(3);
			}
			return DriverManager
					.getConnection(URL, pros.getProperty("mysqlUser"),
							pros.getProperty("mysqlPwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 提供对外接口，创建Oracle数据库连接
	 * 
	 * @param database
	 *            方便跨数据库开发，如果是null就操作配置文件中所配置的数据库，如果不是null则重新生成一个指向目标数据库的URL连接
	 * @return 数据库连接
	 */
	public static Connection getOracleConnection(String database) {
		try {
			Class.forName(pros.getProperty("oracleDriver"));
			String URL = pros.getProperty("oracleUrl");
			if (null != database) {
				Matcher m = Pattern.compile("(.*:)(.*)(\\?.*)").matcher(URL);
				m.find();
				URL = m.group(1) + database + m.group(3);
			}
			return DriverManager.getConnection(URL,
					pros.getProperty("oracleUser"),
					pros.getProperty("oraclePwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 提供对外接口，创建SqlServer数据库连接
	 * 
	 * @param database
	 *            方便跨数据库开发，如果是null就操作配置文件中所配置的数据库，如果不是null则重新生成一个指向目标数据库的URL连接
	 * @return 数据库连接
	 */
	public static Connection getSqlServerConnection(String database) {
		try {
			Class.forName(pros.getProperty("sqlserverDriver"));
			String URL = pros.getProperty("sqlserverUrl");
			if (null != database) {
				Matcher m = Pattern.compile("(.*DatabaseName=)(.*)(\\?.*)")
						.matcher(URL);
				m.find();
				URL = m.group(1) + database + m.group(3);
			}
			return DriverManager.getConnection(URL,
					pros.getProperty("sqlserverUser"),
					pros.getProperty("sqlserverPwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 提供对外接口，关闭数据库中使用的流和连接
	 * 
	 * @param rs
	 *            ResultSet类型
	 * @param ps
	 *            Statement类型
	 * @param conn
	 *            Connection类型
	 */
	public static void close(ResultSet rs, Statement ps, Connection conn) {
		try {
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (null != ps) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
