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

			<form  action="/shiro_hai/app/role" method="post" class="form-horizontal"  >
				
				<div class="control-group">
					<label class="control-label">Name</label>
					<div class="controls">
						<input type="text" name="name" value="" placeholder="Name" id="name"/>
					</div>
				</div>

				<input type="submit" class="btn" value="Save"/>
				
			</form>	
		</div>	
	</div>
	
</body>
</html>