package entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 实体类，对应数据库的student表
 * 
 * @author admin
 *
 */
public class StudentPO {
	/**
	 * 学生的id
	 */
	private Integer sid;
	/**
	 * 学生的姓名
	 */
	private String studentName;
	/**
	 * 学生的生日
	 */
	private Date birthday;
	/**
	 * 学生的平均分
	 */
	private BigDecimal avgScore;
	/**
	 * 学生的描述
	 */
	private String description;

	/**
	 * 构造函数，初始化对象
	 */
	public StudentPO() {
	}

	/**
	 * 构造函数，初始化对象
	 * 
	 * @param studentName
	 * @param birthday
	 * @param avgScore
	 * @param description
	 */
	public StudentPO(String studentName, Date birthday, BigDecimal avgScore,
			String description) {
		this.studentName = studentName;
		this.birthday = birthday;
		this.avgScore = avgScore;
		this.description = description;
	}

	/**
	 * 构造函数，初始化对象
	 * 
	 * @param sid
	 * @param studentName
	 * @param birthday
	 * @param avgScore
	 * @param description
	 */
	public StudentPO(Integer sid, String studentName, Date birthday,
			BigDecimal avgScore, String description) {
		this(studentName, birthday, avgScore, description);
		this.sid = sid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(BigDecimal avgScore) {
		this.avgScore = avgScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
