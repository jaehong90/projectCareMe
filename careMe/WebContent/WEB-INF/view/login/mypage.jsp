<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>

<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<!-- 헤더 -->
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>
	<form name="form7" action="memberUpdateForm" method="post">
		<a href="memberUpdateForm">정보수정</a> <a href=
			"doctorInsertForm" class="btn btn-info">의사등록</a> <a
			href="doctorUpdateForm">의사정보변경</a><br> <input
			value="id=${sc.memberDto.member_id}" /> <input type="text"
			value="idx=${sc.memberDto.member_idx}" /> <input type="text"
			value="didx=${sc.doctorDto.doctor_idx}" /> <input type="text"
			value="dname=${sc.doctorDto.doctor_name}" />

	</form>
</body>
</html>