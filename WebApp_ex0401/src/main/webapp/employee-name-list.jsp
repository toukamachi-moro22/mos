<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員名-一覧</title>
</head>
<body>

	<%
		String section = request.getParameter("section");
	%>

	<%
		List<String> nameList = (List) request.getAttribute("nameList");

		if (nameList.size() == 0) {
	%>
	<%=section%>に所属する従業員はいませんでした。
	<%
		} else {
	%>
	<%=section%>の従業員名一覧
	<br>
	<%
			for (String name : nameList) {
	%>
			<%=name%><br>
	<%
			}
		}
	%>
</body>
</html>