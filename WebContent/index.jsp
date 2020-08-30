<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
// リクエストスコープに保存されたログアウトメッセージを取得
String logoutMsg = (String) request.getAttribute("logoutMsg");
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

</script>
<title>ログイン画面</title>
</head>
<body>
<div class="container">
<h1 class="text-center">筋トレ記録アプリ</h1>
<div class="card">
<form action="/kintoreRecord/LoginServlet" method="post" class="form-horizontal">
<% if(errorMsg != null) { %>
  <div class="row">
  <div class="col-sm-3"></div>
  <p class="text-danger col-sm-5"><%= errorMsg %></p>
  </div>
<% }%>
<% if(logoutMsg != null) { %>
  <div class="row">
  <div class="col-sm-3"></div>
  <p class="text-danger col-sm-5"><%= logoutMsg %></p>
  </div>
<% }%>
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
<div class='card'>
<div class="row">
<div class="col-sm-3"></div>
<h2 class="col-sm-5">テスト用アカウント</h2>
</div>
<div class="row">
<div class="col-sm-3"></div>
<p class="col-sm-7 text-danger">アカウント登録からでも可能ですが、下記のアカウントでもご利用できます^^。</p>
</div>
<div class="row">
<div class="col-sm-3"></div>
<p class="col-sm-2">ユーザーID:test123</p>
</div>
<div class="row">
<div class="col-sm-3"></div>
<p class="col-sm-2">パスワード:test123</p>
</div>
</div>
</div>
</body>
</html>
