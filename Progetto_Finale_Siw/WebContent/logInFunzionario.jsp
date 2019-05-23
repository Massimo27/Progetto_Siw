<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accesso Funzionari</title>
<link rel="stylesheet" href="CSS/style.css" />
</head>
<body>
	<form action="funzionarioController" method="post">
		<div>
			Id: <input type="text" name="id" value ="${param['id']}"/>
			<span class="error">
				${idError}
			</span>	
		</div>
		<div>
			Password: <input type="password" name="password" value="${param['password']}"/>
			<span class="error">
				${passwordError}
			</span>
		</div>
		<div>
			<input type="submit" value="invia"/>
		</div>
	</form>
</body>
</html>