<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Clienti</title>
</head>
<body>
	<h1>Elenco Clienti</h1>
	<table>
		<tr>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Email</th>
		</tr>
		<c:forEach var="cliente" items="${clienti}">
			<tr>
				<td>${cliente.nome}</td>
				<td>${cliente.cognome}</td>
				<td>${cliente.email}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>