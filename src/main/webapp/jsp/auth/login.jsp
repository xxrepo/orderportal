<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>${title}</title>
</head>
<body>
	
	<div class="row">
		<div class="span12">
			<h1>${title}</h1>
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
	
	<div class="row" id="form">
		<div class="span12">
			<form action="/shiro_hai/app/auth/authenticate" method="post" class="form-horizontal"  >
				<div class="control-group">
					<label class="control-label">Username</label>
					<div class="controls">
						<input type="text" name="username" value="" placeholder="username" id="username"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">
						<input type="password" name="password" value="" placeholder="****" id="password">	
					</div>
				</div>
				
				<input type="submit" class="btn" id="login" value="Login"/>
				
			</form>	
		</div>	
	</div>
	
	
	


</body>
</html>