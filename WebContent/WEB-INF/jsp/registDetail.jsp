<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.KintoreType,java.util.List" %>
<%
String parts = (String) request.getAttribute("parts");
String type = (String) request.getAttribute("type");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
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

function valchk(){
	if(document.registForm.rep.value == ""){
		alert("Rep数を入力してください。");
		return false;
	}
	if(document.registForm.exSet.value == ""){
		alert("Set数を入力してください。");
		return false;
	}
	if(document.registForm.weight.value == ""){
		alert("Weight(重量)を入力してください。");
		return false;
	}
	document.registForm.submit();
}
</script>
<title>筋トレ入力画面</title>
</head>
<body>
<div class="container">
<h1 class="text-center">筋トレ入力画面</h1>
<div class="card">
<% if(errorMsg != null) { %>
  <div class="row">
  <div class="col-sm-3"></div>
  <p class="text-danger col-sm-5"><%= errorMsg %></p>
  </div>
<% } %>
<form action="/kintoreRecord/RegistDetailServlet" method="post" name="registForm">
<div class="form-group">
<div class="row mt-3">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">Rep</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="text" name="rep" class="form-control" placeholder="回（半角数字で入力してください）">
</div>
</div>
</div>
<div class="form-group">
<div class="row mt-3">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">Set</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="text" name="exSet" class="form-control" placeholder="セット（半角数字で入力してください）">
</div>
</div>
</div>
<div class="form-group">
<div class="row mt-3">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">Weight</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="text" name="weight" class="form-control" placeholder="kg（半角数字で入力してください）">
</div>
</div>
</div>
<div class="form-group">
<div class="row mt-5">
<div class="col-sm-3"></div>
<div class="col-sm-2">
<input type="button" value="登録" onclick="valchk();"class="form-control btn btn-danger">
</div>
</div>
</div>
<input type="hidden" name="parts" value ="<%= parts %>">
<input type="hidden" name="type" value ="<%= type %>">
<a href="/kintoreRecord/RegistMenuServlet" class="btn btn-primary float-right">戻る</a>
</form>
</div>
</div>
</body>
</html>