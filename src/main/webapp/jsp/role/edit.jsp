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
	
	<div class="row">
		<div class="span12">

			<form  action="/shiro_hai/app/role/update/${role.id}" method="POST" class="form-horizontal">

				<input type="hidden" name="id" value="${role.id}"/>
				
				<div class="control-group">
					<label class="control-label">Name</label>
					<div class="controls">
						<input type="text" name="name" value="${role.name}" placeholder="Name" id="name"/>
					</div>
				</div>

				<input type="submit" class="btn" value="Update"/>
				
			</form>	
		</div>	
	</div>
	
</body>
</html>
