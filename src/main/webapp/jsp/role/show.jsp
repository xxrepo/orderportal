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

			<div class="control-group">
				<label class="control-label">Name</label>
				<div class="controls">${role.name}</div>
			</div>
        	
			<a href="/shiro_hai/app/role/edit/${role.id}" class="btn">Edit</a>
			
			<form action="/shiro_hai/app/role/${role.id}/delete" method="post">
				<input type="submit" class="btn" value="Delete"/>
			</form>

		</div>	
	</div>
	
</body>
</html>
