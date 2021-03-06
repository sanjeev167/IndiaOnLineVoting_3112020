<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>




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
				<li><a href="/"><i class="fa fa-home"></i> </a></li>					
				
				<li><a href="/pvt/voter/db"><i class="fa fa-dashboard" style="color:yellow"></i></a></li>				
			</ul>

		</div>
		<!-- /.navbar-collapse -->


		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- Messages: style can be found in dropdown.less-->
				<li class="dropdown messages-menu">
					<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="fa fa-envelope-o"></i> <span
						class="label label-success">0</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">You have 0 messages</li>
						<li>
							<!-- inner menu: contains the messages -->
							<ul class="menu">
								<li>
									<!-- start message --> <a href="#">
										<div class="pull-left">
											<!-- User Image -->
											<img
												src="<%request.getContextPath();%>/resources/assets/dist/img/avatar2.png"
												class="img-circle" alt="User Image">
										</div> <!-- Message title and timestamp -->
										<h4>
											IndiaOnLineVoting <small><i class="fa fa-clock-o"></i> 5
												mins</small>
										</h4> <!-- The message -->
										<p>Will keep sending you updates about site.</p>
								</a>
								</li>
								<!-- end message -->
							</ul> <!-- /.menu -->
						</li>
						<li class="footer"><a href="#">See All Messages [ Will be implemented latter]</a></li>
					</ul>
				</li>
				<!-- /.messages-menu -->

				<!-- Notifications Menu -->
				<li class="dropdown notifications-menu">
					<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span
						class="label label-warning">0</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">You have 0 notifications</li>
						<li>
							<!-- Inner Menu: contains the notifications -->
							<ul class="menu">
								<li>
									<!-- start notification --> <a href="#"> <i
										class="fa fa-users text-aqua"></i> 5 new voters registered for online voting today.
								</a>
								</li>
								<!-- end notification -->
							</ul>
						</li>
						<li class="footer"><a href="#">View all [ Implemented Latter]</a></li>
					</ul>
				</li>
				<!-- Tasks Menu -->
				<li class="dropdown tasks-menu">
					<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="fa fa-flag-o"></i> <span
						class="label label-danger">1</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">You have 0 tasks</li>
						<li>
							<!-- Inner menu: contains the tasks -->
							<ul class="menu">
								<li>
									<!-- Task item --> <a href="#"> <!-- Task title and progress text -->
										<h3>
											Lock your Online-Vote timely <small class="pull-right">20%</small>
										</h3> <!-- The progress bar -->
										<div class="progress xs">
											<!-- Change the css width attribute to simulate progress -->
											<div class="progress-bar progress-bar-aqua"
												style="width: 20%" role="progressbar" aria-valuenow="20"
												aria-valuemin="0" aria-valuemax="100">
												<span class="sr-only">20% Complete</span>
											</div>
										</div>
								</a>
								</li>
								<!-- end task item -->
							</ul>
						</li>
						<li class="footer"><a href="#">View all tasks [ Implemented Latter]</a></li>
					</ul>
				</li>
				<!-- User Account Menu -->
				<li class="dropdown user user-menu">
					<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <!-- The user image in the navbar--> <img
						src="<%request.getContextPath();%>/resources/assets/dist/img/avatar2.png"
						class="user-image" alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
						<span class="hidden-xs"> </span>
				</a>
					<ul class="dropdown-menu">
						<!-- The user image in the menu -->
						<li class="user-header"><img
							src="<%request.getContextPath();%>/resources/assets/dist/img/avatar2.png"
							class="img-circle" alt="User Image">
							<p>
							    <security:authorize access="isAuthenticated()">
									<sec:authentication property="principal.username" />
									<sec:authentication property="principal.authorities" /><security:authentication property="principal.authorities" />
								</security:authorize>
								<small>Member since Nov. 2020</small>
							</p>
						</li>
						<!-- Menu Body -->
						<li class="user-body">
							<div class="row">
								<div class="col-xs-4 text-center">
									<a href="/pvt/voter/db">Followers</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="/pvt/voter/db">Link-1</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="/pvt/voter/db">Link-2</a>
								</div>
							</div> <!-- /.row -->
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="/pvt/voter/db" class="btn btn-default btn-flat">Profile</a>
							</div>
							<div class="pull-right">

								<a href="/perform_logout" class="btn btn-default btn-flat">Signout</a>
							</div>
						</li>
					</ul>
				</li>
				<!-- Control Sidebar Toggle Button -->
				<li><a href="#" data-toggle="control-sidebar"><i
						class="fa fa-gears"></i></a></li>
			</ul>
		</div>
	
	</nav>

	<div style="display: none;">
		<form action="/logout" method="post" id="logoutFormId">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit" value="Logout">
		</form>
	</div>


	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/jquery/dist/jquery.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	
	
	</script>
</header>
