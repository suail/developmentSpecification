package vo;

import entity.StudentPO;

/**
 * 前台页面对应的实体类
 * 
 * @author admin
 *
 */
public class ForegroundVO {
	/**
	 * 业务类型
	 */
	private String business;
	/**
	 * 前台涉及到的student相关信息，封装到里面
	 */
	private StudentPO student;

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public StudentPO getStudent() {
		return student;
	}

	public void setStudent(StudentPO student) {
		this.student = student;
	}
}
