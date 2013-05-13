<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>${title}</title>
</head>
<body>

	<div class="container">
	
		<div class="row">
			<div class="span12" >
				<h1>Welcome</h1>
			</div>
		</div>
		
		<c:if test="${not empty message}">
			<div class="row">
				<div class="span12">
					<div class="alert alert-info">
						${message}
					</div>
				</div>
			</div>	
		</c:if>
		
	</div>
	
</body>
</html>