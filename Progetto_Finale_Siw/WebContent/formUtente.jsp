<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserimento Dati Utente</title>
<link rel="stylesheet" href="CSS/style.css" />
</head>
<body>
	<form action="utenteController" method="post">
		<div>
		Nome: <input type="text" name="nome" value ="${param['nome']}"/>
				<span class="error">
					${nomeError}
				</span>
		</div>
		<div>
		Cognome: <input type="text" name="cognome" value="${param['cognome']}"/>
				<span class="error">
					${cognomeError}
				</span>
		</div>
		<div>
		Email: <input type="text" name="email" value="${param['email']}"/>
				<span class="error">
					${emailError}
				</span>
		</div>
		<div>
				<input type="submit" value="invia"/>
		</div>
	</form>
</body>
</html>