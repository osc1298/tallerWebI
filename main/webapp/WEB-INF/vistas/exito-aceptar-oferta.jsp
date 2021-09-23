<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Oferta Aceptada</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container pb-5">
			<h1 class="text-center" >Se ha aceptado con exito la oferta</h1>
			<div class="row">
				<div class="col-md-4">
					<h4>${nombre}</h4>
					<div class="container text-center pb-5">
						<img src=${pageContext.servletContext.contextPath}/getImage/${imageNombre} alt="myImage" class="img-fluid" />
					</div>
					<p> ${descripcion}</p>
					<p>Esta oferta fue creada el dia ${fecha} </p>
				</div>
				<div class="col-md-4">
					<p>Ha sido intercambiada por </p>
				</div>
				<div class="col-md-4">
					<h4>${ofertaDescripcion}</h4>
					<div class="container text-center pb-5">
						<img src=${pageContext.servletContext.contextPath}/getImage/${ofertaImageNombre} alt="myImage" class="img-fluid" />
					</div>
					<p>Creada el dia ${fechaCreacion} por el usuario ${nombre}, ${apellido} </p>
				</div>
			</div>
			<div>
				<a href=${pageContext.servletContext.contextPath}/home class="btn btn-success">Volver al home</a>
				
			</div>
		</div>
	</body>
</html>