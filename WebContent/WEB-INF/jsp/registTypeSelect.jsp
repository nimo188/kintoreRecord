<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.KintoreType,java.util.List" %>
<%
// リクエストスコープから筋トレ部位、種目を取得
List<KintoreType> kintoreList = (List<KintoreType>) request.getAttribute("kintoreList");
%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script  type="text/javascript">
history.pushState(null, null, null);
// ブラウザバック禁止
window.addEventListener("popstate", function (e) {
	history.pushState(null, null, null);
	return;
});
</script>
<title>筋トレ種目の選択</title>
</head>
<body>
<div class="container">
<div class="card">
<div class="container mt-3">
<div class="row">
<div class="col-sm-2 mt-5">
<div class="list-group">
  <a href="/kintoreRecord/RegistMenuServlet?parts=1" class="text-dark mt-2 mb-2 list-group-item btn btn-warning">胸</a>
  <a href="/kintoreRecord/RegistMenuServlet?parts=2" class="text-dark mt-2 mb-2 list-group-item btn btn-warning">背中</a>
  <a href="/kintoreRecord/RegistMenuServlet?parts=3" class="text-dark mt-2 mb-2 list-group-item btn btn-warning">肩</a>
  <a href="/kintoreRecord/RegistMenuServlet?parts=4" class="text-dark mt-2 mb-2 list-group-item btn btn-warning">腕</a>
  <a href="/kintoreRecord/RegistMenuServlet?parts=5" class="text-dark mt-2 mb-2 list-group-item btn btn-warning">足</a>
</div>
<a href="/kintoreRecord/MainServlet" class="mt-5 btn btn-primary">戻る</a>
</div>
<div class="col-sm-10">
<h1 class="text-center">筋トレを記録する</h1>
<% for(KintoreType kType : kintoreList) { %>
  <div class="list-group">
  <div class="row">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
    <a href="/kintoreRecord/RegistDetailServlet?type=<%= kType.getType() %>&&parts=<%= kType.getParts() %>" class="list-group-item mt-2 text-dark btn btn-warning"><%= kType.getType() %></a>
  </div>
  <div class="col-sm-1"></div>
  </div>
  </div>
<% } %>

</div>
</div>
</div>
</div>
</div>
</body>
</html>