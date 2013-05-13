<html>
<head>
	<title>${title}</title>
</head>
<body>

	<div class="row">
		<div class="span12">
			<h1>${title} </h1>
		</div>
	</div>
	
	<div class="row" id="form">
		<div class="span12">

			<form  action="/shiro_hai/app/user/${user.id}/update" method="POST" class="form-horizontal">

				<input type="hidden" name="id" value="${user.id}"/>
				
				<div class="control-group">
					<label class="control-label">Username</label>
					<div class="controls">
						<input type="text" name="username" value="${user.username}" placeholder="username" id="username"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">Name</label>
					<div class="controls">
						<input type="text" name="name" value="${user.name}" placeholder="Name" id="name"/>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<input type="email" name="email" value="${user.email}" placeholder="Email" id="email">	
					</div>
				</div>			
				
				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">
						<input type="password" name="passwordHash" value="" placeholder="****" id="passwordHash">	
					</div>
				</div>				

				<input type="submit" class="btn" value="Update"/>
				
			</form>	
		</div>	
	</div>
	
</body>
</html>
