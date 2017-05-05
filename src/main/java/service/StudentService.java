package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ForegroundVO;
import dao.StudentDao;
import entity.StudentPO;

/**
 * 业务层，负责业务的处理
 * 
 * @author admin
 *
 */
public class StudentService {
	/**
	 * 由于需要持久层的支持，所以实例化一个持久层对象
	 */
	private StudentDao studentDao = new StudentDao();

	/**
	 * 业务分配
	 * 
	 * @param request
	 *            request请求
	 * @param response
	 *            response响应
	 * @param foregroundVO
	 *            包含前台传递过来的所有数据
	 * @throws ServletException
	 * @throws IOException
	 */
	public void serviceManagement(HttpServletRequest request,
			HttpServletResponse response, ForegroundVO foregroundVO)
			throws ServletException, IOException {
		// 分析业务类型，进行分配
		String business = foregroundVO.getBusiness();
		if (null == business) {
			initPage(request, response);
		} else {
			switch (business) {
			case "add":
				addStudent(request, response, foregroundVO.getStudent());
				break;
			case "update":
				updateStudent(request, response, foregroundVO.getStudent());
				break;
			case "guideUpdate":
				guideUpdate(request, response, foregroundVO.getStudent()
						.getSid());
				break;
			case "delete":
				deleteStudent(request, response, foregroundVO.getStudent()
						.getSid());
				break;
			default:
				initPage(request, response);
				break;
			}
		}

	}

	/**
	 * 增加Student的业务
	 * 
	 * @param request
	 *            request请求
	 * @param response
	 *            response响应
	 * @param student
	 *            目标Student
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addStudent(HttpServletRequest request,
			HttpServletResponse response, StudentPO student)
			throws ServletException, IOException {
		if (null != student
				&& null == studentDao.findStudentByName(student
						.getStudentName())) {
			studentDao.saveStudent(student);
		}
		initPage(request, response);
	}

	/**
	 * 初始化index.jsp页面
	 * 
	 * @param request
	 *            request请求
	 * @param response
	 *            response响应
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		updateRequestParmeter(request);
		request.getRequestDispatcher("./index.jsp").forward(request, response);
	}

	/**
	 * 更新request请求中的各个参数
	 * 
	 * @param request
	 */
	private void updateRequestParmeter(HttpServletRequest request) {
		int size = studentDao.findStudents().size();
		String jumpPage = request.getParameter("page");
		int pageCount = size / 10 + (0 == size % 10 ? 0 : 1);
		int page = null == jumpPage ? 1
				: (Integer.valueOf(jumpPage) < pageCount ? Integer
						.valueOf(jumpPage) : pageCount);
		request.setAttribute("page", page);
		request.setAttribute("students", studentDao.findStudentsByPage(page));
		request.setAttribute("pageCount", pageCount);
	}

	/**
	 * 引导更新Student的业务
	 * 
	 * @param request
	 *            request请求
	 * @param response
	 *            response响应
	 * @param sid
	 *            目标Student的id
	 * @throws ServletException
	 * @throws IOException
	 */
	public void guideUpdate(HttpServletRequest request,
			HttpServletResponse response, Integer sid) throws ServletException,
			IOException {
		request.setAttribute("student", studentDao.findStudentById(sid));
		request.getRequestDispatcher("/studentDetail.jsp").forward(request,
				response);
	}

	/**
	 * 更新Student的业务
	 * 
	 * @param request
	 *            request请求
	 * @param response
	 *            response响应
	 * @param student
	 *            目标Student
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateStudent(HttpServletRequest request,
			HttpServletResponse response, StudentPO student)
			throws ServletException, IOException {
		studentDao.updateStudent(student);
		initPage(request, response);
	}

	/**
	 * 删除Student的业务
	 * 
	 * @param request
	 *            request请求
	 * @param response
	 *            response响应
	 * @param sid
	 *            目标Student的id
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteStudent(HttpServletRequest request,
			HttpServletResponse response, Integer sid) throws ServletException,
			IOException {
		studentDao.deleteStudent(sid);
		initPage(request, response);
	}
}
