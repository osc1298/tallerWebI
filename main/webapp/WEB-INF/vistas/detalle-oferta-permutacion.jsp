<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Detalle Oferta Permutaci&oacute;n</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
				<div class="col-md-8">
				<h1>Detalle de la Oferta</h1>
					<h3>${descripcion}</h3>
					<div class="container text-center pb-5">
					<c:if test="${not empty imageNombre}">
						<img src=${pageContext.servletContext.contextPath}/getImage/${imageNombre} alt="myImage" class="img-fluid" />
					</c:if>
					<c:if test="${empty imageNombre}">
					<img src=${pageContext.servletContext.contextPath}/img/noimage.jpg alt="myImage" class="img-fluid" />
					</c:if>
					</div>
					<div class="pt-5">
						<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/detalle-permutacion/${idPermutacion} >Volver al Detalle</a>
					</div>
				</div>
				<div class="col-md-4">
					<h4>Desea aceptar la Oferta?</h4>
					<c:if test="${not empty idUsuarioLogueado}">
					<c:if test="${idCreadorPerm == idUsuarioLogueado}">
					<div class="pt-5">			
						<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/aceptar-oferta/${idPermutacion}/${idOfertaPermutacion} >Aceptar Oferta</a>
					</div>
					</c:if>
					</c:if>
				</div>
				
			</div>
		</div>
		<%@include file='templates/footer.html'%>
	</body>
</html>