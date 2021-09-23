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
			<h4>Felicitaciones, ha ofertado con exito la subasta</h4>
			<h3>${titulo}</h3>
			<div class="container text-center pb-5">
				<h5>Por un monto de $ ${precio}</h5>
			</div>
			<div>
				<a href="home" class="btn btn-success">Volver al home</a>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
	</body>
</html>