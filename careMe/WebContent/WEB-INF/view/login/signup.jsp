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
<title>회원가입폼</title>
<script type="text/javascript">
	/* 아이디 중복체크 */
//$(function(){
	/* 유효성 검사 */
	function Signup(){
	           var form = document.form2;
	                  //아이디에서 입력 필수 조건문
	                  if (form.member_id.value == "")  {
	                          alert("아이디를 입력해야 합니다!");
        				   /* ${"#memid"}.append("아이디를 입력해야 합니다!"); */
	                          
	                          form.member_id.focus();//포커스를 id박스로 이동.
	                          return false;
	                  }
	                  //아이디 입력 문자수를 4~12자로 제한하는 조건문
	                  if (form.member_id.value.length < 4 || form.member_id.value.length > 45)
	                  {
	                   alert("아이디는 4~12자 이내로 입력 가능합니다!");

	                   form.member_id.focus();//입력한 문자를 선택 상태로 만듬.
	                   return false;
	                  }
	            
	            //입력된 첫번째 문자가 숫자인지 검사하는 조건문
	            if (!isNaN(form.member_id.value.substr(0,1)))
	            {
	                 alert("아이디는 숫자로 시작할 수 없습니다!");
	                 form.member_id.focus();
	                 return false;
	            }
	            //아이디 중복검사 했는지
	            if(form.idChk.value=='N'){
		            alert("아이디 중복검사를해야합니다!");
					return false;
		            }
				//패스워드 검사
	            if (!form.member_pass.value)
	            {
	                 alert("패스워드를 입력 해야 합니다!");
	                 form.member_pass.focus();//포커스를 Password박스로 이동.
	                 return false;
	            }
	            if (form.member_pass.value.length < 4 || form.member_pass.value.length > 12){
	                 alert("비밀번호는 4~12자 이내로 입력 가능 합니다!");
	                 form.member_pass.focus();
	                 return false;
	            }
	            if (form.member_pass.value != form.member_pass2.value){
		            alert("비밀번호를 같게 입력해주세요!");
		           	form.member_pass.focus();
		           	return false;
		         }
		         if(form.member_phone.value == ""){
			         alert("연락처를 입력해야합니다!");
					form.member_phone.focus();
					return false;
			      }
				if(form.)

			      

	  // form.submit();
	     
	   }
	//아이디 중복확인   
	function fn_idChk(){
		$.ajax({
			url : "idChk",
			type : "post",
			dataType : "json",
			data : {"member_id" : $("#member_id").val()},
			success : function(data){
				if(data == 1){
					alert("중복된 아이디입니다.");
				}else if(data == 0){
					$("#idChk").attr("value", "Y");
					alert("사용가능한 아이디입니다.");
				}
			}
		});
	}


</script>

</head>
<body>
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>

	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>회원가입</h1>
	</div>
	<br>
	<div style="padding: 15px; margin: 0 auto; max-width: 700px">

		<!-- 성공하면 insertok로 감  -->
		<form name="form2" action=insertok method="get"
			onsubmit="return Signup()">
			<table width="670" height="400" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<tr>
					<!-- 아이디 입력 -->
					<td><b>ID:</b></td>
					<td><input type="text" style="width: 530px" id="member_id"
						name="member_id" maxlength="45" class="form-control"
						placeholder="※4-12자의 영문 대소문자와 숫자로만 입력" /></td>

					<!-- 아이디 중복확인 버튼 -->
					<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="idChk" value="N"
							id="idChk" onclick="fn_idChk();">중복확인</button></td>

				</tr>
		
				<tr>
					<!-- 비밀번호 입력 -->
					<td><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass" name="member_pass" maxlength="12"
						class="form-control" placeholder=" ※4-12자의 영문 대소문자와 숫자로만 입력" /></td>
				</tr>
				<tr>
					<!-- 비밀번호 확인 -->
					<td><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass2" name="member_pass2" maxlength="12"
						class="form-control" /></td>
				</tr>

				<tr>
					<!-- 닉네임 입력 -->
					<td><b>Nick:</b></td>
					<td><input type="text" style="width: 530px" id="member_nick"
						name="member_nick" class="form-control" placeholder="ex)petlove" /></td>

					<!-- 닉네임 중복확인 버튼 -->
					<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="nChk" value="N"
							id="nChk" onclick="fn_nChk();">중복확인</button></td>
				</tr>

				<tr>
					<!-- 이메일 선택 -->
					<td><b>email:</b></td>
					<td><input type="text" style="width: 530px" id="member_email"
						name="member_email" class="form-control"
						placeholder="ex)your@email.com" /></td>

					<!-- 이메일 인증 버튼 -->
					<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="eChk" value="N"
							id="eChk" onclick="fn_edChk();">중복확인</button></td>
				</tr>

				<tr>
					<!-- 연락처 -->
					<td><b>phone:</b></td>
					<td><input type="text" style="width: 530px" id="member_phone"
						name="member_phone" class="form-control"
						placeholder="000-0000-0000" /></td>

				</tr>

			</table>
			<br>
			<!-- 버튼 -->
			<!-- 짧은 버튼 -->
			<table width="400" height="50" align="center" cellspacing="0">
				<tbody>
					<tr height="10" align="center">
					</tr>

					<tr>
						<td><input type="submit" name="join" value="회원 가입"
							class="btn btn-dark btn-sm btn-block"></td>
						<td><input type="reset" name="reset" value="다시 입력"
							class="btn btn-dark btn-sm btn-block"></td>
					</tr>
				</tbody>
			</table>

			<!-- 긴거 -->
			<!-- <div class="text-center">
				<input type="submit" name="join" value="회원 가입"
					class="btn btn-dark btn-sm btn-block"> <input type="reset"
					name="reset" value="다시 입력" class="btn btn-dark btn-sm btn-block"> -->
		</form>
	</div>
</body>
</html>