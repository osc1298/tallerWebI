<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<%@include file='templates/head.html'%>
	    <title>Error Inesperado</title>
	</head>
	<body>
		<div class = "container text-center pb-5">
		    <c:if test="${errorMsg == 'Http Error Code: 404. Resource not found'}">
		    	<div>
		    		<img class="text-center" src=${pageContext.servletContext.contextPath}/img/error404.jpg>
		    	</div>
		    	<div>
		            <a href=${pageContext.servletContext.contextPath}/home class="btn btn-secondary">Volver a Home</a>
		        </div>
			</c:if>
		    <c:if test="${errorMsg == 'Http Error Code: 500. Internal Server Error'}">
		    	<div>
		    		<img class="text-center" src=${pageContext.servletContext.contextPath}/img/error500.jpg>
		    	</div>
		    	<div>
		            <a  href=${pageContext.servletContext.contextPath}/home class="btn btn-secondary">Volver a Home</a>
		        </div>
			</c:if>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	</body>
</html>