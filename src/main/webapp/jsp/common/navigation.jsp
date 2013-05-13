<div class="navbar navbar-inverse">
	<div class="navbar-inner">
    	<div class="container">
       		<a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
         		<span class="icon-bar"></span>
         		<span class="icon-bar"></span>
         		<span class="icon-bar"></span>
       		</a>
       		<a class="brand" href="/shiro_hai">Shiro Hai</a>
       		<div class="nav-collapse collapse navbar-inverse-collapse pull-right">
         		<ul class="nav">
           			<li class="${homeActive}"><a href="/shiro_hai/">Home</a></li>
           			<li class="${listPropertyActive}"><a href="/shiro_hai/app/user/list?offset=0&max=10">List Users</a></li>
           			<li class="${addPropertyActive}"><a href= "/shiro_hai/app/user/create">Create User</a></li>
           			<li class="${listPropertyActive}"><a href="/shiro_hai/app/role/list?offset=0&max=10">List Roles</a></li>
           			<li class="${addPropertyActive}"><a href= "/shiro_hai/app/role/create">Create Role</a></li>
         		</ul>
       		</div>
    	</div>
	</div>
</div>