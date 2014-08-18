<%@ page pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]--><!-- BEGIN HEAD --><%@ include file="common/global.jsp"%><head><meta charset="utf-8" /><meta content="width=device-width, initial-scale=1.0" name="viewport" /><%@ include file="common/head-content.jsp"%><link href="<%=staticPath%>/css/grayscale.css" rel="stylesheet"	type="text/css" /></head><!-- END HEAD --><!-- BEGIN BODY --><body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">    <!-- Navigation -->    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">        <div class="container">            <div class="navbar-header">                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">                    <i class="fa fa-bars"></i>                </button>                <a class="navbar-brand page-scroll" href="#page-top">                    <i class="fa fa-play-circle"></i>  <span class="light">Start</span> Bootstrap                </a>            </div>            <!-- Collect the nav links, forms, and other content for toggling -->            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">                <ul class="nav navbar-nav">                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->                    <li class="hidden">                        <a href="#page-top"></a>                    </li>                    <li>                        <a class="page-scroll" href="#about">About</a>                    </li>                    <li>                        <a class="page-scroll" href="#download">Download</a>                    </li>                    <li>                        <a class="page-scroll" href="#contact">Contact</a>                    </li>                </ul>            </div>            <!-- /.navbar-collapse -->        </div>        <!-- /.container -->    </nav>    <!-- Intro Header -->    <header class="intro">        <div class="intro-body">            <div class="container">                <div class="row">                    <div class="col-md-8 col-md-offset-2">                        <h1 class="brand-heading">Grayscale</h1>                        <p class="intro-text">A free, responsive, one page Bootstrap theme.<br>Created by Start Bootstrap.</p>                        <a href="#about" class="btn btn-circle page-scroll">                            <i class="fa fa-angle-double-down animated"></i>                        </a>                    </div>                </div>            </div>        </div>    </header>    <!-- About Section -->    <section id="about" class="container content-section text-center">        <div class="row">            <div class="col-lg-8 col-lg-offset-2">                <h2>About Grayscale</h2>                <p>Grayscale is a free Bootstrap 3 theme created by Start Bootstrap. It can be yours right now, simply download the template on <a href="http://startbootstrap.com/template-overviews/grayscale/">the preview page</a>. The theme is open source, and you can use it for any purpose, personal or commercial.</p>                <p>This theme features stock photos by <a href="http://gratisography.com/">Gratisography</a> along with a custom Google Maps skin courtesy of <a href="http://snazzymaps.com/">Snazzy Maps</a>.</p>                <p>Grayscale includes full HTML, CSS, and custom JavaScript files along with LESS files for easy customization.</p>            </div>        </div>    </section>    <!-- Download Section -->    <section id="download" class="content-section text-center">        <div class="download-section">            <div class="container">                <div class="col-lg-8 col-lg-offset-2">                    <h2>Download Grayscale</h2>                    <p>You can download Grayscale for free on the preview page at Start Bootstrap.</p>                    <a href="http://startbootstrap.com/template-overviews/grayscale/" class="btn btn-default btn-lg">Visit Download Page</a>                </div>            </div>        </div>    </section>    <!-- Contact Section -->    <section id="contact" class="container content-section text-center">        <div class="row">            <div class="col-lg-8 col-lg-offset-2">                <h2>Contact Start Bootstrap</h2>                <p>Feel free to email us to provide some feedback on our templates, give us suggestions for new templates and themes, or to just say hello!</p>                <p><a href="mailto:feedback@startbootstrap.com">feedback@startbootstrap.com</a>                </p>                <ul class="list-inline banner-social-buttons">                    <li>                        <a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>                    </li>                    <li>                        <a href="https://github.com/IronSummitMedia/startbootstrap" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>                    </li>                    <li>                        <a href="https://plus.google.com/+Startbootstrap/posts" class="btn btn-default btn-lg"><i class="fa fa-google-plus fa-fw"></i> <span class="network-name">Google+</span></a>                    </li>                </ul>            </div>        </div>    </section>    <!-- Map Section -->    <div id="map"></div>    <!-- Footer -->    <footer>        <div class="container text-center">            <p>Copyright &copy; Your Website 2014</p>        </div>    </footer>    <!-- jQuery Version 1.11.0 -->    <script src="<%=staticPath%>/js/lib/jquery-1.11.0.js"></script>    <!-- Bootstrap Core JavaScript -->    <script src="<%=staticPath%>/js/bootstrap.min.js"></script>      <!-- Custom Theme JavaScript -->    <script src="<%=staticPath%>/js/grayscale.js"></script></body></html>