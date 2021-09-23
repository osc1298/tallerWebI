<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Detalle Permutaci&oacute;n</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
			<c:if test="${estado == true}">
				<div class="col-md-8">
					<h1>${nombre}</h1>
					<h4>${descripcion}</h4>
					<p>Fecha de Finalizaci&oacute;n: ${fechafinalizacion}</p>
					<div class="container text-center pb-5">
					<c:if test="${not empty imageNombre}">
						<img src=${pageContext.servletContext.contextPath}/getImage/${imageNombre} alt="myImage" class="img-fluid" />
					</c:if>
					<c:if test="${empty imageNombre}">
					<img src=${pageContext.servletContext.contextPath}/img/noimage.jpg alt="myImage" class="img-fluid" />
					</c:if>
					</div>
					<div class="pt-5">
						<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/listar-permutaciones >Volver al Listado</a>
					</div>
				</div>
				<div class="col-md-4">
					<h4>Ofertas Realizadas</h4>
					<c:if test="${not empty ofertas}">
						<table width="100%" cellpadding="8">
						<tr>
							<th>Descripci&oacute;n</th>
							<th>Fecha</th>
							<th></th>
						</tr>
						<c:forEach var="oferta" items="${ofertas}">
							<tr>
								<td>${oferta.descripcion}</td>
								<td>${oferta.fechaOferta}</td>
								<c:if test="${idCreadorPerm == idUsuarioLogueado}">
								<td>
									<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/detalle-oferta-permutacion/${oferta.idOfertaPermutacion} >Detalle Oferta</a>
								</td>
								<td>
									<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/aceptar-oferta/${id}/${oferta.idOfertaPermutacion} >Aceptar Oferta</a>
								</td>
								</c:if>
							</tr>
						</c:forEach>
						</table>
					</c:if>
						<c:if test="${empty ofertas}">
						<p>No hay Ofertas. Se el primero!</p>
						</c:if>
					<c:if test="${not empty idUsuarioLogueado}">
					<c:if test="${idCreadorPerm != idUsuarioLogueado}">
					<div class="pt-5">			
						<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/ofertar-permutacion/${id}>Ofertar</a>
					</div>
					</c:if>
					</c:if>
				</div>
				</c:if>
				
			</div>
			<c:if test='${estado == false }'>
			<h4>La Permutaci&oacute;n ya ha expirado o se acept&oacute;</h4>
			</c:if>
		</div>
		<%@include file='templates/footer.html'%>
	</body>
</html>