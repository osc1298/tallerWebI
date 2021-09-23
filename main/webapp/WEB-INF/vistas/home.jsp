<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		
	    <title>Home</title>
	</head>
	<body>
	
		<%@include file='templates/header.jsp'%>
		<div class = "container text-center pb-5">
			<h1 class="pb-5">Bienvenidos a Echo - Subasta y Permutaci&oacute;n</h1>
			
			<div class="pt-5 pb-5">
				<h3 class="pb-3">&Uacute;ltimas Permutaciones Creadas</h3>
				<div class="row">
				<c:forEach var="permutaciones" items="${listadoPermutaciones}">
					<div class="col-md-4">
						<div class="card mb-3">
							<h3 class="text-dark card-header">${permutaciones.nombrePermutacion}</h3>
							<c:if test="${not empty permutaciones.pathImagenPermutacion}">
							<img src=${pageContext.servletContext.contextPath}/getImage/${permutaciones.pathImagenPermutacion} width="349" height="349">
							</c:if>
							<c:if test="${empty permutaciones.pathImagenPermutacion}">
							<img src=${pageContext.servletContext.contextPath}/img/noimage.jpg alt="NoImage" width="349" height="349"/>
							</c:if>
					  		<div class="card-body">
						    	<a href=${pageContext.servletContext.contextPath}/detalle-permutacion/${permutaciones.idPermutacion} class="card-link">Ver Publicaci&oacute;n</a>
						  	</div>
					  		<div class="card-footer text-muted">
						    	Fecha Finalizaci&oacute;n: ${permutaciones.fechaFinalizacion}
					  		</div>
						</div>
					</div>
					</c:forEach>			
				</div>
			</div>
			
			<div class="pb-5">
				<h3 class="pb-3">&Uacute;ltimas Subastas Creadas</h3>
				<div class="row">
				<c:forEach var="subastas" items="${listadoSubastas}">
					<div class="col-md-4">
						<div class="card mb-3">
							<h3 class="text-dark card-header">${subastas.nombreSubasta}</h3>
							<c:if test="${not empty subastas.pathImagenSubasta}">
							<img src=${pageContext.servletContext.contextPath}/getImage/${subastas.pathImagenSubasta} width="349" height="349">
							</c:if>
							<c:if test="${empty subastas.pathImagenSubasta}">
							<img src=${pageContext.servletContext.contextPath}/img/noimage.jpg width="349" height="349"/>
							</c:if>
					  		<div class="card-body">
						    	<a href=${pageContext.servletContext.contextPath}/detalle-subasta/${subastas.idSubasta} class="card-link">Ver Publicaci&oacute;n</a>
						  	</div>
					  		<div class="card-footer text-muted">
						    	Fecha Finalizaci&oacute;n: ${subastas.fechaFinalizacion}
					  		</div>
						</div>
					</div>
					</c:forEach>	
				</div>
			</div>
			
		</div>
		
		<%@include file='templates/footer.html'%>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	</body>
</html>