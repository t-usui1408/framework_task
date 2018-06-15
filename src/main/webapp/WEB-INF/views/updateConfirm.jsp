<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新内容確認画面</title>
<link href="css/commons.css" rel="stylesheet">
<style>
.col {
  float: left;
}

.col-clear {
  clear: both;
}

#arrow {
  margin-top: 60px;
}
</style>
</head>
<body>
<c:if test="${not empty msg}">
	<p><span class="err">${msg}</span></p>
</c:if>
<p>これでよろしいですか？</p>

<form:form action="update" modelAttribute="form">
  <fieldset>
    <div>
      <label>ID</label><input type="text" name="id" value="${User_info.user_id}" readonly>
    </div>
  </fieldset>

  <fieldset class="col">
    <legend>変更前</legend>
    <div>
      <label>名前</label><input type="text" value="${User_info.user_name}" disabled>
    </div>
    <div>
      <label>TEL</label><input type="text" value="${User_info.telephone}" disabled>
    </div>
    <div>
      <label>PASS</label><input type="password" value="${User_info.password}" disabled>
    </div>
  </fieldset>

  <div id="arrow" class="col">⇒</div>

  <fieldset class="col label-110">
    <legend>変更後</legend>
    <div>
      <label>名前</label><form:input path="name" value="${fn:escapeXml(update.name)}" readonly="true" />
    </div>
    <div>
      <label>TEL</label><form:input path="tel" value="${fn:escapeXml(update.tel)}" readonly="true" />
    </div>
    <div>
      <label>PASS(再入力)</label><form:password path="pass" value="${fn:escapeXml(update.rePass)}" />
    </div>
  </fieldset>

  <div class="col-clear">
    <form:button>更新</form:button>
    <input type="submit" name="button" value="戻る" onclick="location.href='updateInput'; return false;">
  </div>
</form:form>
<div>
  <a href="menu">メニューに戻る</a>
</div>
</body>
</html>
