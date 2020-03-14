<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
int num = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title><c:out value="${accountList[0].name}" />さんの筋トレ記録</title>
</head>
<body>
<h1><c:out value="${accountList[0].name}" />さんの筋トレ記録</h1>
<c:forEach var="record" items="${recordList}">
<div class="card mt-3 mb-3">
<div class="card-body">
日時：<c:out value="${record.createDate}"></c:out>
<table>
<tr>
<th>種目：</th>
<td><c:out value="${record.type}"></c:out></td>
</tr>
<tr>
<th>Rep：</th>
<td><c:out value="${record.rep}"></c:out>回</td>
</tr>
<tr>
<th>Set：</th>
<td><c:out value="${record.exSet}"></c:out>セット</td>
</tr>
<tr>
<th>Weight：</th>
<td><c:out value="${record.weight}"></c:out>kg</td>
</tr>
</table>
</div>
</div>
</c:forEach>
<a href="/kintoreRecord/RegistMenuServlet">筋トレを記録する</a>
<a href="/kintoreRecord/MainServlet">TOPへ戻る</a>
</body>
</html>

