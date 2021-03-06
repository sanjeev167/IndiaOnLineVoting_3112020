<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>



<header class="main-header">
	<!-- Logo -->
	<a href="/" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>I</b>OV</span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>IndiaOnlineVoting</b></span>
	</a>

	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="push-menu"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="http://localhost:8080/"><i class="fa fa-home"></i></a></li>
					<li><a href="/pvt/voter/db"><i class="fa fa-dashboard"></i>&nbsp;Voter</a></li>
					<li><a href="/pvt/adm/db"><i class="fa fa-dashboard"></i>&nbsp;Adm</a></li>
					<li><a href="/pub/vote/result">EC-Result</a></li>
					<li><a href="#" id="offineLinkId">Vote-Offline</a></li>
					<li><a href="/pvt/vote/onlineV" style="color:yellow;">Vote-Online</a></li>
					
					<li><a href="/pvt/voter/lock_vote"><i class="fa fa-lock" style="color:yellow"></i> - Your-Online-Vote</a></li>			
			</ul>

		</div>
		<!-- /.navbar-collapse -->

	     <!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
		    <ul class="nav navbar-nav">
					<li><a href="/pub/voter/login">Login</a></li>
					<li><a href="/pub/hm/reg/">Reg</a></li>	
					<li></li>				
				</ul>
		</div>
	
	</nav>
</header>


<div style="display: none;">
		<form action="/logout" method="post" id="logoutFormId">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit" value="Logout">
		</form>
</div>

	


	