<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dao.*"%>
<%@ page import="Bean.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JobBean> joblist = (ArrayList<JobBean>) request.getAttribute("joblist");
ArrayList<String> errorMessages = (ArrayList<String>) request.getAttribute("errorMessages");
String idfrom = (String) request.getAttribute("idfrom");
String idto = (String) request.getAttribute("idto");
String name = (String) request.getAttribute("name");
String agefrom = (String) request.getAttribute("agefrom");
String ageto = (String) request.getAttribute("ageto");
String sex = (String) request.getAttribute("sex");
String job = (String) request.getAttribute("job");
String tell = (String) request.getAttribute("tell");
String zip = (String) request.getAttribute("zip");
String address = (String) request.getAttribute("address");
String addressdetail = (String) request.getAttribute("addressdetail");
%>
<script src='AddEntry_JS.js'></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住所登録システム</title>
</head>
<body>
	<h1>住所登録システム</h1>

	<div style="display: inline-flex">
		<form method="post" action="/AddEntrySystemVersion2/Search">
			<input type="submit" value="クリア">
		</form>
		<form method="post" action="/AddEntrySystemVersion2/Entry">
			<input type="submit" value="新規">
		</form>
		<form method="get" action="/AddEntrySystemVersion2/Entry">
			<input type="submit" value="変更">
		</form>
		<form>
			<input type="submit" value="削除" class="button">
		</form>
		<form method="post" action="/AddEntrySystemVersion2/End">
			<input type="submit" value="終了">
		</form>
		<!-- <form> -->
		<!-- <input type="button" value="終了" onclick="closeWindow()"> -->
		<!-- </form> -->
	</div>

	<%
	for (int i = 0; i < errorMessages.size(); ++i) {
	%>
	<p id="errormsg" style="color: red;"><%=errorMessages.get(i)%></p>
	<%
	}
	%>

	<form method="post" action="/AddEntrySystemVersion2/Search01">

		<table border="1">
			<tr>
				<th><label for="id">登録ID</label></th>
				<td><input id="idfrom" type="text" name="idfrom" size="8"
					value="<%=idfrom%>"> ~ <input id="idto" type="text"
					name="idto" size="8" value="<%=idto%>"></td>
			</tr>

			<tr>
				<th><label for="name">氏名</label></th>
				<td><input id="name" type="text" name="name" size="20"
					value="<%=name%>"></td>
				<th><label for="tell">電話番号</label></th>
				<td><input id="tell" type="text" name="tell" size="13"
					value="<%=tell%>"></td>
			</tr>

			<tr>
				<th><label for="age">年齢</label></th>
				<td><input id="agefrom" type="text" name="agefrom" size=3
					value="<%=agefrom%>"> ~ <input id="ageto" type="text"
					name="ageto" size=3 value="<%=ageto%>"></td>
				<th><label for="zip">郵便番号</label></th>
				<td><input id="zip" type="text" name="zip" size="8"
					value="<%=zip%>"></td>
			</tr>

			<tr>
				<th><label for="sex">性別</label></th>
				<td><input type="radio" name="sex" value="male"
					<%if (sex.equals("male")) {
	out.print("checked");
}%>>男性
					<input type="radio" name="sex" value="female"
					<%if (sex.equals("female")) {
	out.print("checked");
}%>>女性
					<input id="sex" type="radio" name="sex" value="both"
					<%if (sex.equals("both")) {
	out.print("checked");
}%>>両方</td>
				<th><label for="address">市町村</label></th>
				<td><input id="address" type="text" name="address" size="20"
					value="<%=address%>"></td>
			</tr>

			<tr>
				<th><label for="job">職業</label></th>
				<td><select name="job" class="form-controll">
						<option value="0"
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
						}
						%>
				</select></td>
				<th><label for="addressdetail">番地</label></th>
				<td><input id="addressdetail" type="text" name="addressdetail"
					size="20" value="<%=addressdetail%>"></td>
			</tr>
		</table>

		<input type="submit" value="検索">
	</form>

	<p>&nbsp;</p>

	<%
	ArrayList<SearchBean> searchlist = (ArrayList<SearchBean>) request.getAttribute("searchlist");

	if (searchlist.size() == 0) {
	%>
	<table border="1">
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
			<th><label for="post">郵便番号</label></th>
			<th colspan="3" align="left"><label for="address">市町村</label></th>
			<th><label for="addressdetail">番地</label></th>
		</tr>
	</table>

	<%
	} else {
	%>
	<table border="1">
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
			<th><label for="post">郵便番号</label></th>
			<th colspan="3" align="left"><label for="address">市町村</label></th>
			<th><label for="addressdetail">番地</label></th>
		</tr>
		<%
		for (int i = 0; i < searchlist.size(); ++i) {
		%>
		<tr>
			<th rowspan="2"><input type="checkbox" name="check" value="true" /></th>
			<th rowspan="2"><a href="./test01.jsp"><%=String.format("%08d", searchlist.get(i).getId())%></a></th>
			<th><%=searchlist.get(i).getName()%></th>
			<th><%=searchlist.get(i).getAge()%></th>
			<th><%=searchlist.get(i).getSex()%></th>
			<th><%=searchlist.get(i).getJob()%></th>
			<th><%=searchlist.get(i).getTell()%></th>
		</tr>
		<tr>
			<th><%=searchlist.get(i).getZip()%></th>
			<th colspan="3" align="left"><%=searchlist.get(i).getAddress()%></th>
			<th><%=searchlist.get(i).getAddressDetail()%></th>
		</tr>
		<%
		}
		%>

	</table>
	<%
	}
	%>


</body>
</html>