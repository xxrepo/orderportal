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

			<form  action="/shiro_hai/app/auth/register" method="post" class="form-horizontal"  >

				<div class="control-group">
					<label class="control-label">Username</label>
					<div class="controls">
						<input type="text" name="username" value="" placeholder="username" id="username"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">Name</label>
					<div class="controls">
						<input type="text" name="name" value="" placeholder="Name" id="name"/>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<input type="email" name="email" value="" placeholder="Email" id="email">	
					</div>
				</div>				


				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">
						<input type="password" name="passwordHash" value="" placeholder="*******" id="passwordHash">	
					</div>
				</div>		
				
				<input type="submit" class="btn" value="Register"/>
				
			</form>	
		</div>	
	</div>
	
</body>
</html>