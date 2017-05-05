<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>details</title>
</head>
<body>
	<div>
		<form action="./studentController" method="post">
			<fieldset>
				<c:if test="${not empty student}">
					<fieldset>
						<label>Student id <span>*</span></label> <input type="text"
							name="id" value="${student.sid}" readonly />
					</fieldset>
				</c:if>
				<fieldset>
					<label>Student name <span>*</span></label> <input type="text"
						name="name" value="${student.studentName}" />
				</fieldset>
				<fieldset>
					<label>Student birthday <span>*</span></label> <input type="text"
						name="birthday" value="${student.birthday}" />
				</fieldset>
				<fieldset>
					<label>Student description <span>*</span></label> <input
						type="text" name="description" value="${student.description}" />
				</fieldset>
				<fieldset>
					<label>Student avgscore <span>*</span></label> <input type="text"
						name="avgscore" value="${student.avgScore}" />
				</fieldset>
				<input type="button" value="back"
					onclick="javascript:window.location.href='./studentController'" />
				<c:if test="${null==student|| empty student}">
					<input type="reset" value="reset" />
					<input type="submit" value="Add Now!" />
					<input type="hidden" name="business" value="add" />
				</c:if>
				<c:if test="${not empty student}">
					<input type="submit" value="Update Now!" />
					<input type="hidden" name="business" value="update" />
				</c:if>
			</fieldset>
		</form>
	</div>
	<div>
		You must pay attention to the following, otherwise it will cause the
		failure of storage<br /> Student id: make sure not to repeat<br />
		Student birthday: must be in conformity with the date format<br />
		Student avgscore: must be a number<br />
	</div>
</body>