<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>ログイン画面</title>
</head>
<body>
<div class="container">
<h1 class="text-center">筋トレ記録アプリ</h1>
<div class="card">
<form action="/kintoreRecord/LoginServlet" method="post" class="form-horizontal">
<c:if test="${not empty errorMsg}">
  <div class="row">
  <div class="col-sm-3"></div>
  <p class="text-danger col-sm-5">${errorMsg }</p>
  </div>
</c:if>
<c:if test="${not empty logoutMsg}">
  <div class="row">
  <div class="col-sm-3"></div>
  <p class="text-danger col-sm-5">${logoutMsg }</p>
  </div>
</c:if>
<div class="form-group">
<div class="row mt-3">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">ユーザーID</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="text" name="id" class="form-control">
</div>
</div>
</div>
<div class="form-group">
<div class="row">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">パスワード</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="password" name="pass"  class="form-control">
</div>
</div>
</div>
<div class="form-group">
<div class="row mt-5">
<div class="col-sm-3"></div>
<div class="col-sm-2">
<button type="submit" class="form-control btn btn-danger">ログイン</button>
</div>
</div>
</div>
<div class="form-group">
<div class="row mt-5">
<div class="col-sm-3"></div>
<div class="col-sm-2">
<a href="/kintoreRecord/RegistAccountServlet" class="form-control btn btn-info">アカウント登録</a>
</div>
</div>
</div>
</form>
</div>
</div>
</body>
</html>