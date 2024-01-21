<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dao.*"%>
<%@ page import="Bean.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JobBean> joblist = (ArrayList<JobBean>) request.getAttribute("joblist");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>住所登録システム</title>
</head>
<script src='AddEntry_JS.js'></script>
<body>
	<h1>登録画面</h1>

	<div style="display: inline-flex">
		<form>
			<input type="button" value="クリア" onclick="clearButtonClickE()">
		</form>
		<form>
			<input type="button" value="中止" onclick="history.back()">
		</form>
	</div>

	<p id="errormsg" style="color: red;"></p>

	<form method="post" action="/AddEntrySystemVersion2/Insert">
		<table border="1">
			<tr>
				<th><label for="id">登録ID</label></th>
				<td><input id="id" type="text" name="id" size="8"></td>
			</tr>

			<tr>
				<th><label for="name">氏名</label></th>
				<td><input id="name" type="text" name="name" size="20"></td>
			</tr>

			<tr>
				<th><label for="age">年齢</label></th>
				<td><input id="age" type="text" name="age" size=3>歳</td>
			</tr>

			<tr>
				<th><label for="sex">性別</label></th>
				<td><input id="sex" type="radio" name="sex" value="male"
					checked>男性 <input type="radio" name="sex" value="female">女性
				</td>
			</tr>

			<tr>
				<th><label for="job">職業</label></th>
				<td><select name="job" class="form-controll">
						<option id="job" value="0" selected></option>
						<%
						for (int i = 0; i < joblist.size(); ++i) {
						%>
						<option value=<%=joblist.get(i).getId()%>><%=joblist.get(i).getJob()%></option>
						<%
						}
						%>
				</select></td>
			</tr>

			<tr>
				<th><label for="tell">電話番号</label></th>
				<td><input id="tell" type="text" name="tell" size="13"></td>
			</tr>

			<tr>
				<th><label for="zip">郵便番号</label></th>
				<td><input id="zip" type="text" name="zip" size="8"></td>
			</tr>

			<tr>
				<th><label for="address">市町村</label></th>
				<td><input id="address" type="text" name="address" size="20"></td>
			</tr>

			<tr>
				<th><label for="addressdetail">番地</label></th>
				<td><input id="addressdetail" type="text" name="addressdetail"
					size="20"></td>
			</tr>
		</table>
		<input type="submit" value="登録" onclick="return Confirm()">
	</form>
</body>
</html>