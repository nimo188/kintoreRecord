<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,model.RecordList,java.util.List" %>
<%
int num = 0;
// セッションスコープに保存されたユーザー情報を取得
List<Account> accountList = (List<Account>) session.getAttribute("accountList");

//リクエストスコープに保存された筋トレレコード情報を取得
List<RecordList> recordList = (List<RecordList>) request.getAttribute("recordList");
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
/***
window.addEventListener("popstate", function (e) {
	history.pushState(null, null, null);
	return;
});
**/
</script>
<title><% for(Account account : accountList) {%>
<%= account.getName() %>さんの筋トレ記録
<% } %>
</title>
</head>
<body>
<h1 class="text-center"><% for(Account account : accountList) {%>
<%= account.getName() %>
<% } %>さんの筋トレ記録
</h1>
<div class="card mt-3 mb-3">
<div class="card-body">
<table class="table table-hover table-bordered">
<tr>
<th>日時</th>
<th>種目</th>
<th>Rep</th>
<th>Set</th>
<th>Weight</th>
</tr>
<% for(RecordList record : recordList) {%>
<tr>
<td><%= record.getCreateDate() %></td>
<td><%= record.getType() %></td>
<td><%= record.getRep() %>回</td>
<td><%= record.getExSet() %>セット</td>
<td><%= record.getWeight() %>kg</td>
</tr>
<% } %>
</table>
</div>
</div>
<a href="/kintoreRecord/RegistMenuServlet" class="btn btn-danger">筋トレを記録する</a>
<a href="/kintoreRecord/MainServlet" class="btn btn-danger">TOPへ戻る</a>
</body>
</html>

