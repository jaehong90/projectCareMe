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
<title>의사등록</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%-- <script src="/resources/js/addressapi.js"></script> --%>
<script type="text/javascript">

function PostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

           // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
           var extraRoadAddr = ''; // 도로명 조합형 주소 변수

           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
           }
           // 건물명이 있고, 공동주택일 경우 추가한다.
           if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
           }
           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
           if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
           }
           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
           if(fullRoadAddr !== ''){
               fullRoadAddr += extraRoadAddr;
           }

           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           console.log(data.zonecode);
           console.log(fullRoadAddr);
           
           
           $("[name=addr1]").val(data.zonecode);
           $("[name=addr2]").val(fullRoadAddr);
           
           /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
           document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
           document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
       }
    }).open();
}

function Doctorinsert(){
	var form = document.form4;
}

</script>
</head>
<body>
	<!-- 헤더 -->
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>

	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>의사등록</h1>
	</div>
	<!-- 의사 등록 버튼 클릭시  -->
	<br>
	<div style="padding: 15px; margin: 0 auto; max-width: 700px">

		<!-- 성공하면 로 감  -->
		<form name="form4" action="dinsertok" method="post"
			onsubmit="return Doctorinsert()">

			<table width="685" height="400" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<tr>
					<!-- 이름 입력 -->
					<td><b>Name:</b></td>
					<td><input type="text" style="width: 530px" id="doctor_name"
						name="name" maxlength="45" class="form-control" /></td>
				</tr>

				<tr>
					<!-- 의사면허 번호-->
					<td><b>License:</b></td>
					<td><input type="text" style="width: 530px"
						id="doctor_license" name="license" maxlength="45"
						class="form-control" placeholder=" ※면허번호" /></td>
				</tr>

				<tr>
					<!-- 근무하는 병원 입력 -->
					<td><b>Hospital:</b></td>
					<td><input type="text" style="width: 530px"
						id="doctor_hospital_name" name="h_name" maxlength="17"
						class="form-control" placeholder=" ※병원 이름" /></td>
				</tr>
				<tr>
					<!-- 주소 -->
					<!-- 우편번호 -->
					<td><b>Address:</b></td>
					<td><input type="text" style="width: 530px"
						id="doctor_hospital_zipcode" name="addr1" readonly="readonly"
						class="form-control" /></td>
					<td><button type="button" onclick="PostCode(); class="btnbtn-darkbtn-smbtn-block">주소찾기</button></td>
				</tr>
				<tr>
					<td>
						<!-- 도로명주소 -->
					</td>
					<td><input type="text" style="width: 530px"
						id="doctor_hospital_address" name="addr2" readonly="readonly"
						class="form-control" placeholder=" ※도로명 주소" /></td>
				</tr>
				<tr>
					<td>
						<!-- 상세주소 -->
					</td>
					<td><input type="text" style="width: 530px"
						id="doctor_hospital_address_detail" name="addr3" maxlength="30"
						class="form-control" placeholder="※상세주소" /></td>
				</tr>

				<tr>
					<!-- 핸드폰 입력 -->
					<td><b>H.Tel:</b></td>
					<td><input type="text" style="width: 530px"
						id="doctor_hospital_tel" name="tel" class="form-control"
						placeholder="병원 연락처를 입력해주세요" /></td>
				</tr>
				<!-- 짧은 버튼 -->
				<table width="400" height="50" align="center" cellspacing="0">
					<tbody>
						<tr height="10" align="center"></tr>

						<tr>
							<td><input type="submit" name="d_in" value="등록하기"
								class="btn btn-dark btn-sm btn-block"></td>
							<td><input type="reset" name="reset" value="다시 입력"
								class="btn btn-dark btn-sm btn-block"></td>
						</tr>
					</tbody>
				</table>
			</table>
		</form>
	</div>
</body>
</html>