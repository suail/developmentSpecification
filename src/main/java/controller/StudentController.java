package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StudentService;
import vo.ForegroundVO;
import entity.StudentPO;
/**
 * 负责合理封装前台数据
 * @author admin
 *
 */
@WebServlet("/studentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 46578985779098979L;
	/**
	 * 由于需要业务层的支持，所以实例化一个业务层对象
	 */
	private StudentService studentService = new StudentService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置相应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 封装前台数据，交给业务层处理
		try {
			ForegroundVO foregroundVO = new ForegroundVO();
			StudentPO student = new StudentPO();
			if (null != request.getParameter("business")) {
				foregroundVO.setBusiness(request.getParameter("business"));
			}
			if (null != request.getParameter("id")) {
				student.setSid(Integer.valueOf(request.getParameter("id")));
			}
			if (null != request.getParameter("name")) {
				student.setStudentName(request.getParameter("name"));
			}
			if (null != request.getParameter("birthday")) {
				student.setBirthday(Date.valueOf(request
						.getParameter("birthday")));
			}
			if (null != request.getParameter("avgscore")) {
				BigDecimal avgScore = new BigDecimal(
						request.getParameter("avgscore"));
				avgScore.setScale(2, BigDecimal.ROUND_DOWN);
				student.setAvgScore(avgScore);
			}
			if (null != request.getParameter("description")) {
				student.setDescription(request.getParameter("description"));
			}
			foregroundVO.setStudent(student);
			//移交给业务层处理
			studentService.serviceManagement(request, response, foregroundVO);
		} catch (Exception ex) {
			System.out
					.println("[error]:the Method of doGet have an error!, detail:"
							+ ex.getMessage());
			request.setAttribute("msg", "error");
			request.getRequestDispatcher("./msg.jsp")
					.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
