<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>	
		<c:set var="x" scope="page" value="${fechafinalizacionSubasta}" />	
		<script type="text/javascript">
    	var fechaSubasta= "<c:out value="${x}" />";
		</script>
		<script type="text/javascript" src=${pageContext.servletContext.contextPath}/js/countdown.js></script>
		<title>Detalle Subasta</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
			<c:if test="${estado == true}">
				<div class="col-md-8">
					<h1>${nombreSubasta}</h1>
					<h4>${descripcionSubasta}</h4>
					<h5 >Fecha de finalizacion: ${fechafinalizacionSubasta}  </h5> 
					<div class="container text-center pb-5">
					<c:if test="${not empty imageNombreSubasta}">
						<img src=${pageContext.servletContext.contextPath}/getImageSubasta/${imageNombreSubasta} alt="myImage" class="img-fluid" />
					</c:if>
					<c:if test="${empty imageNombreSubasta}">
					<img src=${pageContext.servletContext.contextPath}/img/noimage.jpg alt="myImage" class="img-fluid" />
					</c:if>
					</div>
					<div class="pt-5">
						<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/listar-subastas >Volver al listado</a>
					</div>
				</div>
				<div class="col-md-3">
				<div>
				<h3>Esta Subasta terminar&aacute; en:</h3>
				<p id="countdown">
				</p>
				</div>	
					<h4>Ofertas Realizadas</h4>
					<c:if test="${not empty ofertasSubasta}">
						<table width="100%" cellpadding="2">
						<tr>
							<th>Usuario</th>
							<th>Importe</th>
							<th></th>
						</tr>
						<c:forEach var="oferta" items="${ofertasSubasta}">
							<tr>
								<td>${oferta.usuario.userName}</td>
								<td>${oferta.precioOferta}</td>
							</tr>
						</c:forEach>
						</table>					
					</c:if>
						<c:if test="${empty ofertasSubasta}">
						<p>No hay Ofertas. Se el primero!</p>
						</c:if>
					<c:if test="${not empty idUsuarioLogueado}">
					<c:if test="${idCreadorSub != idUsuarioLogueado}">
					<div class="pt-5">			
						<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/ofertar-subasta/${id}>Ofertar</a>
					</div>
					</c:if>
					</c:if>
				</div>
				</c:if>
				
			</div>
			<c:if test='${estado == false &&  not empty ofertaGanadora }'>
			<h2>La Subasta ha expirado</h2>
			<div class="pt-5">			
				<h4>La oferta ganadora es...</h4>		
				<p>${ofertaGanadora.precioOferta} Por ${ofertaGanadora.usuario.userName}</p>
			</div>
			</c:if>
			<c:if test='${estado == false &&  empty ofertaGanadora}'>
			<h2>La Subasta ha expirado</h2>
			<div class="pt-5">			
				<h4>No hubo ofertas ganadoras</h4>		
			</div>
			</c:if>
		</div>
		<%@include file='templates/footer.html'%>
	</body>
</html>