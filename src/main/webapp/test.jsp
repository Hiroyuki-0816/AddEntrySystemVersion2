<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="TestDao.*" %>
<%@ page import="TestBean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %> 
<%
ArrayList<JobBean> joblist = (ArrayList<JobBean>)request.getAttribute("joblist");
%>
<script src='AddEntry_JS.js'></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>住所登録システム</h1>

<div style="display:inline-flex">
<input type="submit" value="クリア" onclick ="clearButtonClickS()" class="button">
<form method="post" action="/AddEntrySystemVersion2/Test01">
<input type="submit" value="新規">
</form>
<form method="get" action="/AddEntrySystemVersion2/Test01">
<input type="submit" value="変更">
</form>
<form>
<input type="submit" value="削除" class="button">
</form>
<form method="post" action="/AddEntrySystemVersion2/Test02">
<input type="button" value="終了">
</form>
</div>

<p id="errormsg" style="color: red;"></p>

<form method="get" action="/AddEntrySystemVersion2/Test03">

<table  border="1">
<tr>
<th><label for="id">登録ID</label></th>
<td><input id = "idfrom" type="text" name="idfrom" size="8">~<input id = "idto" type="text" name="idto" size="8"></td>
</tr>

<tr>
<th><label for="name">氏名</label></th>
<td><input id = "name" type="text" name="name" size="20"></td>
<th><label for="tell">電話番号</label></th>
<td><input id = "tell" type="text" name="tell" size="13"></td>
</tr>

<tr>
<th><label for="age">年齢</label></th>
<td><input id = "agefrom" type="text" name="agefrom" size=3>~<input id = "ageto" type="text" name="ageto" size=3></td>
<th><label for ="zip">郵便番号</label></th>
<td><input id = "zip" type="text" name="zip" size="8"></td>
</tr>

<tr>
<th><label for="sex">性別</label></th>
<td>
<input type="radio" name="sex" value="male">男性
<input type="radio" name="sex" value="female">女性
<input id="sex" type="radio" name="sex" value="both" checked>両方
</td>
<th><label for ="address">市町村</label></th>
<td><input id = "address" type="text" name="address" size="20"></td>
</tr>

<tr>
<th><label for="job">職業</label></th>
<td>
<select name="job" class="form-controll">
<option value="00" selected></option>
<%for(int i=0; i < joblist.size(); ++i){%>
	<option value=<%= joblist.get(i).getId() %>><%= joblist.get(i).getJob() %></option>
<% } %>
</select>
</td>
<th><label for ="addressdetail">番地</label></th>
<td><input id = "addressdetail" type="text" name="addressdetail" size="20"></td>
</tr>
</table>

<input type="submit" value="検索">
</form>


<p>&nbsp;</p>

<table  border="1">
<tr>
<th rowspan="2"><label for="checked">選択</label></th>
<th rowspan="2"><label for="id">登録ID</label></th>
<th><label for="name">氏名</label></th>
<th><label for="age">年齢</label></th>
<th><label for="sex">性別</label></th>
<th><label for="job">職業</label></th>
<th><label for="tell">電話番号</label></th>
</tr>
<tr>
<th><label for ="post">郵便番号</label></th>
<th colspan="3" align="left"><label for ="address">市町村</label></th>
<th><label for ="addressdetail">番地</label></th>
</tr>
</table>

</body>
</html>