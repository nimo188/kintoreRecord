<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>登録完了</title>
</head>
<body>
<div class="container">
<h1 class="text-center">登録完了！</h1>
<div class="row">
  <div class="col-sm-2"></div>
    <div class="col-sm-3">
    <div class="card img-thumbnail">
      <img class="card-img-top" src="/kintoreRecord/img/muscle_regist.jpg" alt="画像">
      <div class="card-body px-2 py-3">
        <h5 class="card-title">筋トレの登録</h5>
        <p class="card-text">続けて筋トレを登録します</p>
        <p class="mb-0"><a href="/kintoreRecord/RegistMenuServlet" class="btn btn-primary btn-sm">続けて登録する</a></p>
      </div>
    </div>
  </div>
  <div class="col-sm-3">
    <div class="card img-thumbnail">
      <img class="card-img-top" src="/kintoreRecord/img/record.jpg" alt="画像">
      <div class="card-body px-2 py-3">
        <h5 class="card-title">筋トレの記録</h5>
        <p class="card-text">筋トレを記録を見ます</p>
        <p class="mb-0"><a href="/kintoreRecord/RecordServlet" class="btn btn-primary btn-sm">記録を見る</a></p>
      </div>
    </div>
    <div class="col-sm-3"></div>
  </div>
  <div class="col-sm-3">
    <div class="card img-thumbnail">
      <img class="card-img-top" src="/kintoreRecord/img/main.jpg" alt="画像">
      <div class="card-body px-2 py-3">
        <h5 class="card-title">筋トレTOP</h5>
        <p class="card-text">筋トレTOP画面に戻る</p>
        <p class="mb-0"><a href="/kintoreRecord/MainServlet" class="btn btn-primary btn-sm">TOPに戻る</a></p>
      </div>
    </div>
    <div class="col-sm-3"></div>
  </div>
  </div>
  </div>
</body>
</html>