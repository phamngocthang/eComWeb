<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/28/2023
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>MultiShop - Online Shop Website Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/resources/lib/animate/animate.min.css" rel="stylesheet">
    <link href="/resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<!-- Shop Start -->
<div class="container-fluid">
    <div class="row px-xl-5">
        <!-- Shop Sidebar Start -->
        <div class="col-lg-3 col-md-4">
            <!-- Price Start -->
            <form method="GET" action="${pageContext.request.contextPath}/shop">
                <a href="${pageContext.request.contextPath}/shop" class="btn btn-info btn-filter">Reset Filter</a>

                <!-- Catagory Start -->
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Filter by Category</span></h5>
                <div class="bg-light p-4 mb-30">
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="-1" onchange="this.form.submit();" class="custom-control-input" id="cate-all" ${category == -1 || category == null ?"checked":""}>
                            <label class="custom-control-label" for="cate-all">All Product</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="1" onchange="this.form.submit();" class="custom-control-input" id="cate-1" ${category == 1 ?"checked":""}>
                            <label class="custom-control-label" for="cate-1">Giày Sandals Nam</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="2" onchange="this.form.submit();" class="custom-control-input" id="cate-2" ${category == 2 ?"checked":""} >
                            <label class="custom-control-label" for="cate-2">Giày Lười Nam</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="3" onchange="this.form.submit();" class="custom-control-input" id="cate-3" ${category == 3 ?"checked":""} >
                            <label class="custom-control-label" for="cate-3">Giày Sneaker Nam</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="4" onchange="this.form.submit();" class="custom-control-input" id="cate-4" ${category == 4 ?"checked":""} >
                            <label class="custom-control-label" for="cate-4">Giày Thể Thao Nam</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="5" onchange="this.form.submit();" class="custom-control-input" id="cate-5" ${category == 5 ?"checked":""} >
                            <label class="custom-control-label" for="cate-5">Giày Cao Gót Nữ</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="6" onchange="this.form.submit();" class="custom-control-input" id="cate-6" ${category == 6 ?"checked":""} >
                            <label class="custom-control-label" for="cate-6">Giày Sneaker Nữ</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="7" onchange="this.form.submit();" class="custom-control-input" id="cate-7" ${category == 7 ?"checked":""} >
                            <label class="custom-control-label" for="cate-7">Giày Thể Thao Nữ</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="category" value="8" onchange="this.form.submit();" class="custom-control-input" id="cate-8" ${category == 8 ?"checked":""} >
                            <label class="custom-control-label" for="cate-8">Giày Đế Bệ Nữ</label>
                        </div>
                </div>


                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Filter by price</span></h5>
                <div class="bg-light p-4 mb-30">
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="radio" name="price" value="" onchange="this.form.submit();" class="custom-control-input" ${price == null || price == '' || price == 'null' ? 'checked': ''} id="price-all">
                        <label class="custom-control-label" for="price-all">All Price</label>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="radio" name="price" value="1" onchange="this.form.submit();" class="custom-control-input" ${price == '1' ? 'checked' : ''} id="price-1">
                        <label class="custom-control-label" for="price-1">0 - 400K</label>
                        <span class="badge border font-weight-normal">${countProductByPrice[1]}</span>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="radio" name="price" value="2" onchange="this.form.submit();" class="custom-control-input" ${price == '2' ? 'checked' : ''} id="price-2">
                        <label class="custom-control-label" for="price-2">400K - 700K</label>
                        <span class="badge border font-weight-normal">${countProductByPrice[0]}</span>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="radio" name="price" value="3" onchange="this.form.submit();" class="custom-control-input" ${price == '3' ? 'checked' : ''} id="price-3">
                        <label class="custom-control-label" for="price-3">Above 700K</label>
                        <span class="badge border font-weight-normal">${countProductByPrice[2]}</span>
                    </div>
                </div>
                <!-- Price End -->

                <!-- Color Start -->
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Filter by color</span></h5>
                <div class="bg-light p-4 mb-30">
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="color" value="" onchange="this.form.submit();" class="custom-control-input" ${color == null || color == '' || color == 'null' ? 'checked': ''} id="color-all">
                            <label class="custom-control-label" for="color-all">All Color</label>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="color" value="Black" onchange="this.form.submit();" class="custom-control-input" ${color == 'Black' ? 'checked' : ''} id="color-1">
                            <label class="custom-control-label" for="color-1">Black</label>
                            <span class="badge border font-weight-normal">${countProductByColor[0]}</span>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input  type="radio" name="color" value="White" onchange="this.form.submit();" class="custom-control-input" ${color == 'White' ? 'checked' : ''} id="color-2">
                            <label class="custom-control-label" for="color-2">White</label>
                            <span class="badge border font-weight-normal">${countProductByColor[2]}</span>
                        </div>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" name="color" value="Blue" onchange="this.form.submit();" class="custom-control-input" ${color == 'Blue' ? 'checked' : ''} id="color-3">
                            <label class="custom-control-label" for="color-3">Blue</label>
                            <span class="badge border font-weight-normal">${countProductByColor[1]}</span>
                        </div>
                </div>
                <!-- Color End -->

<%--                <input name="sortByPopular" value="${sortByPopular != null ? true : false}" hidden="hidden">--%>
                <input name="typeSort" value="${typeSort}" hidden="hidden">
                <input name="showing" value="${showing}" hidden="hidden">
                <input name="pageCurrent" value="${1}" hidden="hidden">

            </form>
        </div>

        <!-- Shop Sidebar End -->

        <!-- Shop Product Start -->
        <div class="col-lg-9 col-md-8">
            <div class="row pb-3">
                <div class="col-12 pb-1">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <div>
                            <button class="btn btn-sm btn-light"><i class="fa fa-th-large"></i></button>
                            <button class="btn btn-sm btn-light ml-2"><i class="fa fa-bars"></i></button>
                        </div>
                        <div class="ml-2">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Sorting</button>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item ${typeSort == 'LastedProduct' ? 'active' : ''}" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=LastedProduct&showing=${showing}&pageCurrent=${1}">Latest Product</a>
                                    <a class="dropdown-item ${typeSort == 'CheapProduct' ? 'active' : ''}" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=CheapProduct&showing=${showing}&pageCurrent=${1}">Cheap Product</a>
                                </div>
                            </div>
                            <div class="btn-group ml-2">
                                <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Showing</button>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item ${showing == '' || showing == null || showing == '3' ? 'active' : ''}" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=${typeSort}&showing=${3}&pageCurrent=${1}">3</a>
                                    <a class="dropdown-item ${showing == '6' ? 'active' : ''}" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=${typeSort}&showing=${6}&pageCurrent=${1}">6</a>
                                    <a class="dropdown-item ${showing == '9' ? 'active' : ''}" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=${typeSort}&showing=${9}&pageCurrent=${1}">9</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:forEach items="${listPageable}" var="item">
                    <div class="col-lg-4 col-md-6 col-sm-6 pb-1">
                        <div class="product-item bg-light mb-4">
                            <div class="product-img position-relative overflow-hidden">
                                <img class="img-fluid w-100" src="${item.image.pathMiddle}" alt="">
                                <div class="product-action">
                                    <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-shopping-cart"></i></a>
                                    <a class="btn btn-outline-dark btn-square" href=""><i class="far fa-heart"></i></a>
                                    <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-sync-alt"></i></a>
                                    <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>
                                </div>
                            </div>
                            <div class="text-center py-4">
                                <a class="h6 text-decoration-none text-truncate" href="${pageContext.request.contextPath}/detail/${item.id}/${item.brand}">${item.productName}</a>
                                <div class="d-flex align-items-center justify-content-center mt-2">
                                    <h5>$ ${item.price}</h5><h6 class="text-muted ml-2"><del>$ ${item.price}</del></h6>
                                </div>
                                <div class="d-flex align-items-center justify-content-center mb-1">
                                    <small class="fa fa-star text-primary mr-1"></small>
                                    <small class="fa fa-star text-primary mr-1"></small>
                                    <small class="fa fa-star text-primary mr-1"></small>
                                    <small class="far fa-star text-primary mr-1"></small>
                                    <small class="far fa-star text-primary mr-1"></small>
                                    <small>(99)</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-12">
                    <nav>
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${pageCurrent == 1 || pageCurrent == null ? 'disabled' : ''}">
                                <a class="page-link" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=${typeSort}&showing=${showing}&pageCurrent=${pageCurrent-1}">Previous
                                </a>
                            </li>
                            <c:forEach begin="1" end="${pageAmount}" var="i">
                                <li class="page-item ${pageCurrent == i || (pageCurrent == null && i == 1)  ? 'active': ''}"><a class="page-link" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=${typeSort}&showing=${showing}&pageCurrent=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${pageCurrent == pageAmount  ? 'disabled' : ''}"><a class="page-link" href="${pageContext.request.contextPath}/shop?category=${category}&price=${price}&color=${color}&typeSort=${typeSort}&showing=${showing}&pageCurrent=${pageCurrent+1}">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Shop Product End -->
    </div>
</div>
<!-- Shop End -->


<%@ include file="footer.jsp" %>
</body>
</html>
