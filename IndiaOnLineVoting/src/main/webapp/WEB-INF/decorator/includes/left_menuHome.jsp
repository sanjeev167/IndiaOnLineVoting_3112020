     
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
			<!-- Sidebar user panel -->
		      <div class="user-panel">    
		          <div id="carousel-example-generic" class="carousel slide"
							data-ride="carousel" >
							<ol class="carousel-indicators">
								<li data-target="#carousel-example-generic" data-slide-to="0"
									class="active"></li>
								<li data-target="#carousel-example-generic" data-slide-to="1"
									class=""></li>
								<li data-target="#carousel-example-generic" data-slide-to="2"
									class=""></li>
								<li data-target="#carousel-example-generic" data-slide-to="3"
									class=""></li>
							</ol>
							<div class="carousel-inner" >
								<div class="item active">
									<img
										src="<%request.getContextPath();%>/resources/assets/ec/img/ec40.jpg"
										alt="First slide">
		
									<div class="carousel-caption">First Slide</div>
								</div>
								<div class="item">
									<img
										src="<%request.getContextPath();%>/resources/assets/ec/img/ec50.jpg"
										alt="Second slide">
		
									<div class="carousel-caption">Second Slide</div>
								</div>
								<div class="item">
									<img
										src="<%request.getContextPath();%>/resources/assets/ec/img/ec30.jpg"
										alt="Third slide">
		
									<div class="carousel-caption">Third Slide</div>
								</div>
								<div class="item">
									<img
										src="<%request.getContextPath();%>/resources/assets/ec/img/ec20.jpg"
										alt="Fourth slide">
		
									<div class="carousel-caption">Fourth Slide</div>
								</div>
							</div>
							<a class="left carousel-control" href="#carousel-example-generic"
								data-slide="prev"> <span class="fa fa-angle-left"></span>
							</a> <a class="right carousel-control" href="#carousel-example-generic"
								data-slide="next"> <span class="fa fa-angle-right"></span>
							</a>
				</div>    
		    </div><!-- user-panel -->
		    
		  <!-- search form -->
		      <form action="#" method="get" class="sidebar-form">
		        <div class="input-group">
		          <input type="text" name="q" class="form-control" placeholder="Search...">
		          <span class="input-group-btn">
		                <button type="submit" name="search" id="search-btn" class="btn btn-flat">
		                  <i class="fa fa-search"></i>
		                </button>
		              </span>
		        </div>
		      </form>
		  <!-- /.search form -->	
				<%@ include file="staticMenuHome.jsp"%>
	</section>
	<!-- /.sidebar -->
