<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Oferta Creada</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<h4>Felicitaciones, ha ofertado con exito la permutacion</h4>
			<h3>${descripcionOferta}</h3>
			<div class="container text-center pb-5">
				<img src=${pageContext.servletContext.contextPath}/getImage/${imageNombre} alt="myImage" class="img-fluid" />
			</div>
			<div>
				<a href="home" class="btn btn-success">Volver al home</a>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
	</body>
</html>