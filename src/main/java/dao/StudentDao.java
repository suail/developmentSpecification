package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;
import entity.StudentPO;

/**
 * 该类封装了对于数据库的student表的相关操作
 * 
 * @author admin
 *
 */
public class StudentDao {
	/**
	 * 数据库连接
	 */
	private Connection conn;
	/**
	 * sql语句
	 */
	private String sql = "";
	/**
	 * 执行sql语句的PreparedStatement对象
	 */
	private PreparedStatement ps;
	/**
	 * 存放sql语句执行结果的ResultSet对象
	 */
	private ResultSet rs;
	/**
	 * 默认一页对应多少条数据
	 */
	private final static int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 提供对外接口，插入一条数据，返回操作执行情况
	 * 
	 * @param student
	 *            这一条数据对应的实体类
	 * @return 操作执行情况，true：执行成功；false：执行失败
	 */
	public boolean saveStudent(StudentPO student) {
		boolean result = false;
		try {
			// 获得数据库连接，因为我们所使用的数据库正是配置文件中的配置的那个数据库，所以传NULL
			conn = JdbcUtil.getMysqlConnection(null);
			sql = "insert into Student (student_name,birthday,average_score,description) values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getStudentName());
			ps.setDate(2, student.getBirthday());
			ps.setBigDecimal(3, student.getAvgScore());
			ps.setString(4, student.getDescription());
			result = ps.execute();
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of saveStudent have an error!, detail:"
							+ ex.getMessage());
		} finally {
			// 无论是否抛出异常，都要关闭可能已使用到的连接和流
			JdbcUtil.close(rs, ps, conn);
		}
		return result;
	}

	/**
	 * 提供对外接口，删除一条数据，返回操作执行情况
	 * 
	 * @param sid
	 *            这一条数据对应的sid
	 * @return 操作执行情况，true：执行成功；false：执行失败
	 */
	public boolean deleteStudent(int sid) {
		boolean result = false;
		try {
			// 获得数据库连接，因为我们所使用的数据库正是配置文件中的配置的那个数据库，所以传NULL
			conn = JdbcUtil.getMysqlConnection(null);
			sql = "delete from Student where binary sid = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			result = ps.execute();
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of deleteStudent have an error!, detail:"
							+ ex.getMessage());
		} finally {
			// 无论是否抛出异常，都要关闭可能已使用到的连接和流
			JdbcUtil.close(rs, ps, conn);
		}
		return result;
	}

	/**
	 * 提供对外接口，更新一条数据
	 * 
	 * @param student
	 *            这一条数据对应的实体类
	 * @return 操作执行情况，true：执行成功；false：执行失败
	 */
	public boolean updateStudent(StudentPO student) {
		boolean result = false;
		try {
			// 获得数据库连接，因为我们所使用的数据库正是配置文件中的配置的那个数据库，所以传NULL
			conn = JdbcUtil.getMysqlConnection(null);
			sql = "update Student set student_name = ?, birthday = ?, average_score = ?, description = ? where binary sid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getStudentName());
			ps.setDate(2, student.getBirthday());
			ps.setBigDecimal(3, student.getAvgScore());
			ps.setString(4, student.getDescription());
			ps.setInt(5, student.getSid());
			result = ps.execute();
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of updateStudent have an error!, detail:"
							+ ex.getMessage());
		} finally {
			// 无论是否抛出异常，都要关闭可能已使用到的连接和流
			JdbcUtil.close(rs, ps, conn);
		}
		return result;
	}

	/**
	 * 提供对外接口，通过sid查找数据，返回操作执行情况
	 * 
	 * @param sid
	 *            想要搜索到的数据的sid
	 * @return 操作执行情况，true：执行成功；false：执行失败
	 */
	public StudentPO findStudentById(int sid) {
		StudentPO student = null;
		try {
			// 获得数据库连接，因为我们所使用的数据库正是配置文件中的配置的那个数据库，所以传NULL
			conn = JdbcUtil.getMysqlConnection(null);
			sql = "select * from Student where binary sid = ? LIMIT 1";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			rs = ps.executeQuery();
			if (rs.next()) {
				student = new StudentPO(rs.getInt(1), rs.getString(2),
						rs.getDate(3), rs.getBigDecimal(4), rs.getString(5));
			}
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of findStudentById have an error!, detail:"
							+ ex.getMessage());
		} finally {
			// 无论是否抛出异常，都要关闭可能已使用到的连接和流
			JdbcUtil.close(rs, ps, conn);
		}
		return student;
	}

	/**
	 * 提供对外接口，通过studentName查找数据，返回操作执行情况
	 * 
	 * @param studentName
	 *            想要搜索到的数据的studentName
	 * @return 操作执行情况，true：执行成功；false：执行失败
	 */
	public StudentPO findStudentByName(String studentName) {
		StudentPO student = null;
		try {
			// 获得数据库连接，因为我们所使用的数据库正是配置文件中的配置的那个数据库，所以传NULL
			conn = JdbcUtil.getMysqlConnection(null);
			sql = "select * from Student where binary student_name = ? LIMIT 1";
			ps = conn.prepareStatement(sql);
			ps.setString(1, studentName);
			rs = ps.executeQuery();
			if (rs.next()) {
				student = new StudentPO(rs.getInt(1), rs.getString(2),
						rs.getDate(3), rs.getBigDecimal(4), rs.getString(5));
			}
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of findStudentByName have an error!, detail:"
							+ ex.getMessage());
		} finally {
			// 无论是否抛出异常，都要关闭可能已使用到的连接和流
			JdbcUtil.close(rs, ps, conn);
		}
		return student;
	}

	/**
	 * 提供对外接口，分页查询，返回得到的记录
	 * 
	 * @param page
	 *            指定的页数
	 * @return List<Student> 该页的记录
	 */
	public List<StudentPO> findStudentsByPage(int page) {
		// 给page一个默认值，防止错误查找
		page = 0 >= page ? 1 : page;
		// 分析查找范围
		int start = (page - 1) * DEFAULT_PAGE_SIZE;
		int end = page * DEFAULT_PAGE_SIZE;
		List<StudentPO> list = new ArrayList<StudentPO>();
		try {
			// 获得数据库连接，因为我们所使用的数据库正是配置文件中的配置的那个数据库，所以传NULL
			conn = JdbcUtil.getMysqlConnection(null);
			sql = "select * from Student LIMIT ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				StudentPO student = new StudentPO(rs.getInt(1), rs.getString(2),
						rs.getDate(3), rs.getBigDecimal(4), rs.getString(5));
				list.add(student);
			}
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of findStudentsByPage have an error!, detail:"
							+ ex.getMessage());
		} finally {
			// 无论是否抛出异常，都要关闭可能已使用到的连接和流
			JdbcUtil.close(rs, ps, conn);
		}
		return list;
	}
	/**
	 * 提供对外接口，获得所有记录
	 * @return 所有记录
	 */
	public List<StudentPO> findStudents() {
		List<StudentPO> list = new ArrayList<StudentPO>();
		try {
			// 获得数据库连接，因为我们所使用的数据库正是配置文件中的配置的那个数据库，所以传NULL
			conn = JdbcUtil.getMysqlConnection(null);
			sql = "select * from Student";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				StudentPO student = new StudentPO(rs.getInt(1), rs.getString(2),
						rs.getDate(3), rs.getBigDecimal(4), rs.getString(5));
				list.add(student);
			}
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of findStudents have an error!, detail:"
							+ ex.getMessage());
		} finally {
			// 无论是否抛出异常，都要关闭可能已使用到的连接和流
			JdbcUtil.close(rs, ps, conn);
		}
		return list;
	}
	
}
