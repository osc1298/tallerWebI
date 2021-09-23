<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Listado de Permutaciones Activas</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-12">
					<h1 class="pb-5">Permutaciones Activas</h1>
					<c:if test="${not empty listadoPermutaciones}">
						<table width="100%" cellpadding="12">	
							<tr class="table-info">
								<th>Nombre</th>
								<th>Descripcion</th>
								<th></th>
								<th></th>
								
							</tr>
							<c:forEach var="perm" items="${listadoPermutaciones}">   
								<tr>
									<td>${perm.nombrePermutacion}</td>  
									<td>${perm.descripcionPermutacion}</td>  
									<td><a href="detalle-permutacion/${perm.idPermutacion}" class="btn btn-primary">M&aacute;s Detalles</a></td>
									<c:if test="${not empty idUsuarioLogueado}">
									<c:if test="${perm.getUsuario().getId() != idUsuarioLogueado}">
									<td><a href="ofertar-permutacion/${perm.idPermutacion}" class="btn btn-primary">Ofertar</a></td>
									</c:if>
									</c:if>
								</tr>  
							</c:forEach>  
						</table>
					</c:if>
					<c:if test="${empty listadoPermutaciones}">
					<div class="pt-3">
						<h4>No hay permutaciones activas. Cree una!</h4>
					</div>
					</c:if>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</body>
</html>