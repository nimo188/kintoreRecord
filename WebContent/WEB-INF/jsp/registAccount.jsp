<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// リクエストスコープに保存されたエラーメッセージを取得
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
//ブラウザバック禁止
window.addEventListener("popstate", function (e) {
	history.pushState(null, null, null);
	return;
});

function valchk(){
	if(document.accountForm.pass.value != document.accountForm.pass2.value){
		alert("パスワードが一致しません");
		return false;
	}
	if(document.accountForm.userName.value == ""){
		alert("名前が入力されていません");
		return false;
	}
    var radio = document.getElementsByName("gender");
    var chkflg = false;
    if(!radio[0].checked && !radio[1].checked){
		alert("性別を選択してください");
		return false;
    }
    if(document.accountForm.birthday.value == ""){
		alert("生年月日が入力されていません");
		return false;
	}
	document.accountForm.submit();
}
</script>
<title>アカウント登録画面</title>
</head>
<body>
<div class="container">
<div class="card">
<h1 class="text-center">アカウント登録</h1>
<% if(errorMsg != null) { %>
  <div class="row">
  <div class="col-sm-3"></div>
  <p class="text-danger col-sm-5"><%= errorMsg %></p>
  </div>
<% } %>
<form action="/kintoreRecord/RegistAccountServlet" method="post" name="accountForm">
<div class="form-group">
<div class="row mt-3">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">ユーザーID</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="text" name="userId" class="form-control" placeholder="ID（半角英数字10文字まで）">
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
<input type="password" name="pass" class="form-control" placeholder="Password">
</div>
</div>
</div>
<div class="form-group">
<div class="row">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">パスワード(再入力)</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="password" name="pass2" class="form-control" placeholder="同じパスワードを入力してください">
</div>
</div>
</div>
<div class="form-group">
<div class="row">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">名前</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="text" name="userName" class="form-control" placeholder="Name（全角英数字１０文字まで）">
</div>
</div>
</div>
<div class="form-group">
<div class="row">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">性別</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<label><input type="radio" name="gender" value="1" class="form-control">男性</label>
<label><input type="radio" name="gender" value="2" class="form-control">女性</label>
</div>
</div>
</div>
<div class="form-group">
<div class="row">
<div class="col-sm-3"></div>
<label class="control-label col-sm-2">生年月日</label>
</div>
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<input type="text" name="birthday" class="form-control" placeholder="半角数字8ケタで入力して下さい">
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
<div class="form-group">
<div class="row mt-5">
<div class="col-sm-3"></div>
<div class="col-sm-2">
<a href="/kintoreRecord/index.jsp" class="btn btn-primary" style="width:100%">ログイン画面へ</a>
</div>
</div>
</div>
</form>
</div>
</div>
</body>
</html>