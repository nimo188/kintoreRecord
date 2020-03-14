<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>筋トレの記録</title>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-sm-4">
<a href="/kintoreRecord/RegistMenuServlet?parts=1">胸</a>
<a href="/kintoreRecord/RegistMenuServlet?parts=2">背中</a>
<a href="/kintoreRecord/RegistMenuServlet?parts=3">肩</a>
<a href="/kintoreRecord/RegistMenuServlet?parts=4">腕</a>
<a href="/kintoreRecord/RegistMenuServlet?parts=5">足</a>

<a href="/kintoreRecord/MainServlet">戻る</a>
</div>
<div class="col-sm-8">
<h1>筋トレを記録する</h1>
</div>
</div>
</div>
</body>
</html>