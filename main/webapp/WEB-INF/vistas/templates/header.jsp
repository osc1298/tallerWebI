<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/home>Home</a></li>
			<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/crear-subasta>Crear Subasta</a></li>
			<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/crear-permutacion>Crear Permutacion</a></li>
			<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/listar-permutaciones>Permutaciones Activas</a></li>
			<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/listar-subastas>Subastas Activas</a></li>
			<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/contacto>Contacto</a></li>
		</ul>
	 	<c:if test="${empty idUsuarioLogueado}">
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/login>Iniciar Session</a></li>
				<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/registro>Crear Cuenta</a></li>
			</ul>
		</c:if>
		
        <!-- valdidar la aparicion de este link -->
        <c:if test="${not empty idUsuarioLogueado}">
        	<ul class="nav navbar-nav navbar-right">
        		<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/perfil>Mi perfil - ${user}</a></li>
				<li class="nav-item"><a class="nav-link" href=${pageContext.servletContext.contextPath}/cerrar-sesion>Cerrar sesion</a></li>
			</ul>
		</c:if>
        
	</nav>
</header>