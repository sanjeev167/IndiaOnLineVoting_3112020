<ul class="sidebar-menu" data-widget="tree">
			<!-- <li class="header">MAIN NAVIGATION</li>  -->
			<li class="treeview"><a href="#"> <i class="fa fa-dashboard"></i>
					<span>Dashboard</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="<%=request.getContextPath() %>/pvt/adm/db"><i class="fa fa-circle-o"></i>
							Dashboard v1</a></li>

				</ul></li>
			<li><a href="<%=request.getContextPath() %>/pvt/adm/profile"> <i class="fa fa-th"></i> <span>Profile</span>
					<span class="pull-right-container"> 
				</span>
			</a></li>
			
			<!-- Start: Master Entry -->
			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
					<span>Masters</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">		
				    <li><a href="<%=request.getContextPath() %>/pvt/master/country/"><i class="fa fa-circle-o"></i>Country Master</a></li>			
					<li><a href="<%=request.getContextPath() %>/pvt/master/state/"><i class="fa fa-circle-o"></i>State Master</a></li>
					<li><a href="<%=request.getContextPath() %>/pvt/master/city/"><i class="fa fa-circle-o"></i>City Master</a></li>
					<li><a href="<%=request.getContextPath() %>/pvt/master/loksabha/"><i class="fa fa-circle-o"></i>Loksabha</a></li>
				    <li><a href="<%=request.getContextPath() %>/pvt/master/assembly/"><i class="fa fa-circle-o"></i>Assembly</a></li>
				    <li><a href="<%=request.getContextPath() %>/pvt/master/pollingBooth/"><i class="fa fa-circle-o"></i>Polling Booth</a></li>
				     <li><a href="<%=request.getContextPath() %>/pvt/master/votersEnrolled/"><i class="fa fa-circle-o"></i>Enroll Voter</a></li>
				    
				    <li><a href="<%=request.getContextPath() %>/pvt/master/party/"><i class="fa fa-circle-o"></i>Political Parties</a></li>
				    <li><a href="<%=request.getContextPath() %>/pvt/master/Lcandidate/"><i class="fa fa-circle-o"></i>Loksabha-Candidate</a></li>
				    <li><a href="<%=request.getContextPath() %>/pvt/master/Acandidate/"><i class="fa fa-circle-o"></i>Assembly-Candidate</a></li>
				   
				    <li><a href="<%=request.getContextPath() %>/pvt/master/party/"><i class="fa fa-circle-o"></i>Sensitive Link Master</a></li>
				
				</ul>
			</li>

			<!-- End: Master Entry -->
			
			
			<li><a href="<%=request.getContextPath() %>/pvt/adm/voteLockAudit"><i class="fa fa-circle-o"></i>Vote-Lock-Audit</a></li>
			<li><a href="<%=request.getContextPath() %>/pvt/adm/sensitiveLink"><i class="fa fa-circle-o"></i>Sensitive Link</a></li>
			<li><a href="<%=request.getContextPath() %>/pvt/adm/electionSchedule"><i class="fa fa-circle-o"></i>Election-Schedule</a></li>
			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
					<span>Dummy</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">				
				</ul>
			</li>

      
			<!-- End: API Manager -->
			
			
		</ul>
	