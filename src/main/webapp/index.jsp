<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>details</title>
<style type="text/css">
.find {
	font-size: 20px;
}

.guideMsg {
	font-size: 32px;
	font-family: "Arial Black";
	text-align: center;
	color: #818181;
}

.row {
	width: 100%;
	height: 100%;
	text-align: center;
}

.col {
	float: left;
	width: 16%;
	height: 20%;
	border: 1px solid rgb(0, 0, 0);
}

.page {
	clear: left;
	width: 100%;
	height: 100%;
}

.displayPage {
	float: left;
	width: 80%;
}

.jumpPage {
	float: left;
}

.notFind {
	font-size: 20px;
	text-align: center;
	color: #FF0000;
}
</style>
</head>
<body>
	<div>
		<div class="guideMsg">Student details</div>
		<c:if test="${not empty requestScope.students}">
			<div class="find">
				<a href="${pageContext.request.contextPath }/studentDetail.jsp">add</a> one
			</div>
			<div class="row clearfix">
				<div class="col">Student id</div>
				<div class="col">Student name</div>
				<div class="col">Student birthday</div>
				<div class="col">Student avgscore</div>
				<div class="col">Student description</div>
				<div class="col">Options</div>
			</div>
			<c:forEach items="${requestScope.students}" var="student">
				<div class="row clearfix">
					<div class="col"><c:out value="${student.sid }" /></div>
					<div class="col"><c:out value="${student.studentName }" /></div>
					<div class="col"><c:out value="${student.birthday }" /></div>
					<div class="col"><c:out value="${student.avgScore }" /></div>
					<div class="col"><c:out value="${student.description }" /></div>
					<div class="col">
						<a href='./studentController?business=guideUpdate&id=<c:out value="${student.sid }" />'>Update</a>|<a
							href='./studentController?business=delete&id=<c:out value="${student.sid }" />'>Delete</a>
					</div>
				</div>
			</c:forEach>
			<div class="page">
				<div class="displayPage">crrent page :${requestScope.page},
					total page :${requestScope.pageCount}</div>
				<div class="jumpPage">
					jump to:
					<c:forEach var="i" begin="1" end="${requestScope.pageCount}">
						<a href='./studentController?business=jump&page=<c:out value="${i}" />'>${i}</a>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<c:if test="${empty requestScope.students}">
			<div class="notFind">
				not find datas, go to <a href="${pageContext.request.contextPath }/studentDetail.jsp">add</a> one
			</div>
		</c:if>
	</div>
</body>