<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>筋トレ入力画面</title>
</head>
<body>
<h1>筋トレ入力画面</h1>
<c:if test="${not empty errorMsg}">
  <p class="text-danger col-sm-5">${errorMsg }</p>
</c:if>
<form action="/kintoreRecord/RegistDetailServlet" method="post">
Rep：<input type="text" name="rep">回<br>
Set：<input type="text" name="exSet">セット<br>
Weight：<input type="text" name="weight">kg<br>
<input type="submit" value="登録">
<input type="hidden" name="parts" value ="${parts}">
<input type="hidden" name="type" value ="${type}">
</form>
<a href="/kintoreRecord/RegistMenuServlet">戻る</a>
</body>
</html>