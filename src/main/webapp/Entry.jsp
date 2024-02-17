<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dao.*"%>
<%@ page import="Bean.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JobBean> joblist = (ArrayList<JobBean>) request.getAttribute("joblist");
ArrayList<String> errorMessages = (ArrayList<String>) request.getAttribute("errorMessages");
String id = (String) request.getAttribute("id");
String name = (String) request.getAttribute("name");
String age = (String) request.getAttribute("age");
String sex = (String) request.getAttribute("sex");
String job = (String) request.getAttribute("job");
String tell = (String) request.getAttribute("tell");
String zip = (String) request.getAttribute("zip");
String address = (String) request.getAttribute("address");
String addressdetail = (String) request.getAttribute("addressdetail");
String readonly = (String) request.getAttribute("readonly");

String idfromS = (String) session.getAttribute("idfromS");
String idtoS = (String) session.getAttribute("idtoS");
String nameS = (String) session.getAttribute("nameS");
String agefromS = (String) session.getAttribute("agefromS");
String agetoS = (String) session.getAttribute("agetoS");
String sexS = (String) session.getAttribute("sexS");
String jobS = (String) session.getAttribute("jobS");
String tellS = (String) session.getAttribute("tellS");
String zipS = (String) session.getAttribute("zipS");
String addressS = (String) session.getAttribute("addressS");
String addressdetailS = (String) session.getAttribute("addressdetailS");
String errorCountS = (String) session.getAttribute("errorCountS");
String searchCountS = (String) session.getAttribute("searchCountS");
String submitTypeS = (String) session.getAttribute("submitTypeS");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src='AddEntry_JS.js'></script>
<title>住所登録システム</title>
</head>
<body>
	<h1>登録画面</h1>
	<form method="post" action="/AddEntrySystemVersion2/Insert">
		<input type="hidden" name="idfromS" value="<%=idfromS%>"> <input
			type="hidden" name="idtoS" value="<%=idtoS%>"> <input
			type="hidden" name="nameS" value="<%=nameS%>"> <input
			type="hidden" name="agefromS" value="<%=agefromS%>"> <input
			type="hidden" name="agetoS" value="<%=agetoS%>"> <input
			type="hidden" name="sexS" value="<%=sexS%>"> <input
			type="hidden" name="jobS" value="<%=jobS%>"> <input
			type="hidden" name="tellS" value="<%=tellS%>"> <input
			type="hidden" name="zipS" value="<%=zipS%>"> <input
			type="hidden" name="addressS" value="<%=addressS%>"> <input
			type="hidden" name="addressdetailS" value="<%=addressdetailS%>">
		<input type="hidden" name="errorCount" value="<%=errorCountS%>">
		<input type="hidden" name="searchCount" value="<%=searchCountS%>">
		<input type="hidden" name="submitType" value="<%=submitTypeS%>">

		<input type="submit" name="button" value="登録"
			onclick="return Confirm()"> <input type="submit"
			name="button" value="クリア"> <input type="submit" name="button"
			value="中止">

		<%
		for (int i = 0; i < errorMessages.size(); ++i) {
		%>
		<p id="errormsg" style="color: red;"><%=errorMessages.get(i)%></p>
		<%
		}
		%>

		<table border="1">
			<tr>
				<th><label for="id">登録ID</label></th>
				<td><input id="id" type="text" name="id" size="8"
					value="<%=id%>"
					<%if (readonly.equals("update")) {
	out.print("readonly");
}%>></td>
			</tr>

			<tr>
				<th><label for="name">氏名</label></th>
				<td><input id="name" type="text" name="name" size="20"
					value="<%=name%>"></td>
			</tr>

			<tr>
				<th><label for="age">年齢</label></th>
				<td><input id="age" type="text" name="age" size="3"
					value="<%=age%>">歳</td>
			</tr>

			<tr>
				<th><label for="sex">性別</label></th>
				<td><input id="sex" type="radio" name="sex" value="male"
					<%if (sex.equals("male")) {
	out.print("checked");
}%>>男性
					<input type="radio" name="sex" value="female"
					<%if (sex.equals("female")) {
	out.print("checked");
}%>>女性
				</td>
			</tr>

			<tr>
				<th><label for="job">職業</label></th>
				<td><select name="job" class="form-controll">
						<option id="job" value="0"
							<%if (job.equals("0")) {
	out.print("selected");
}%>></option>
						<%
						for (int i = 0; i < joblist.size(); ++i) {
						%>
						<option value=<%=joblist.get(i).getId()%>
							<%if (joblist.get(i).getId() == Integer.parseInt(job)) {
	out.print("selected");
}%>><%=joblist.get(i).getJob()%></option>
						<%
						p

						}
						%>
				</select></td>
			</tr>

			<tr>
				<th><label for="tell">電話番号</label></th>
				<td><input id="tell" type="text" name="tell" size="13"
					value="<%=tell%>"></td>
			</tr>

			<tr>
				<th><label for="zip">郵便番号</label></th>
				<td><input id="zip" type="text" name="zip" size="8"
					value="<%=zip%>"></td>
			</tr>

			<tr>
				<th><label for="address">市町村</label></th>
				<td><input id="address" type="text" name="address" size="20"
					value="<%=address%>"></td>
			</tr>

			<tr>
				<th><label for="addressdetail">番地</label></th>
				<td><input id="addressdetail" type="text" name="addressdetail"
					size="20" value="<%=addressdetail%>"></td>
			</tr>
		</table>
	</form>



</body>
</html>