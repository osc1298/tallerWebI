<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Listado de Subastas Activas</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-12">
					<h1 class="pb-5">Subastas Activas</h1>
					<c:if test="${not empty listadoSubastas}">
						<table width="100%" cellpadding="12">
							<tr class="table-info">
								<th>Nombre</th>
								<th>Descripcion</th>
								<th></th>
								<th></th>
								
							</tr>
							<c:forEach var="sub" items="${listadoSubastas}">   
								<tr>  
									<td>${sub.nombreSubasta}</td>  
									<td>${sub.descripcionSubasta}</td>  
									<td><a href="detalle-subasta/${sub.idSubasta}" class="btn btn-primary">M&aacute;s Detalles</a></td>
									<c:if test="${not empty idUsuarioLogueado}">
									<c:if test="${sub.getUsuario().getId() != idUsuarioLogueado}">
									<td><a href="ofertar-subasta/${sub.idSubasta}" class="btn btn-primary">Ofertar</a></td>
									</c:if>
									</c:if>
								</tr>  
							</c:forEach>  
						</table>
					</c:if>
					<c:if  test="${empty listadoSubastas}">
					<div class="pt-3">
						<h4>No hay subastas activas. Cree una!</h4>
					</div>
					</c:if>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</body>
</html>