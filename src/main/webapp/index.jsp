<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ASEMGA</title>
</head>
<body>
<center>
<a href="https://asrtomcatejemplocloudantsimon.eu-gb.mybluemix.net/asrTomcatEjemploCloudant"><img src="asemga.png" ></a><br>
<h1>Base de datos de ganaderos en el mercado</h1>
<hr />
<p>Introduzca nombre y animales del ganadero</p>
<a href="listar">Listar ganaderos</a><br>
<br>
<br>
<br>

<form action="insertar">
  <ul id="fieldList">
    <li>
      <input name="nombre" type="text" placeholder="Nombre del ganadero" />
    </li>
    <br>
    <li>
      <input name="vacas" type="text" placeholder="Numero de vacas" />
    </li>
    <br>
    <li>
      <input name="caballos" type="text" placeholder="Numero de caballos">
    </li>
  </ul>
  <br>
  <input type="submit">
</form>


<form action="reconocimiento">
  <br>
  <br>
	<label for="reconocimiento">Identifica el tipo de animal de la foto:</label>
	<input type="text"name="URL">
  <br>
  <input type="submit">
</form>


</center>
</body>
</html>