<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
function valchk(){
	if(document.accountForm.pass.value != document.accountForm.pass2.value){
		alert("パスワードが一致しません");
		return false;
	}
	if(document.accountForm.userName.value == ""){
		alert("kara");
		return false;
	}
    var radio = document.getElementsByName("gender");
    var chkflg = false;
    if(!radio[0].checked && !radio[1].checked){
		alert("性別を選択してください");
		return false;
    }
	document.accountForm.submit();
}
</script>
<title>アカウント登録画面</title>
</head>
<body>
<h1>アカウント登録</h1>
<c:if test="${not empty errorMsg}">
  <p>${errorMsg }</p>
</c:if>
<form action="/kintoreRecord/RegistAccountServlet" method="post" name="accountForm">
ユーザーID<input type="text" name="userId">
パスワード<input type="password" name="pass">
パスワード（再入力）<input type="password" name="pass2">
名前<input type="text" name="userName">
男性<input type="radio" name="gender" value="1">
女性<input type="radio" name="gender" value="2"><br>
生年月日（８桁の数字）<input type="text" name="birthday">
<!--<button type="button" value="登録" onclick="valchk();">登録</button>  -->
<input type="button" value="登録" onclick="valchk();">

</form>
</body>
</html>