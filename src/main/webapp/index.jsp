<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1><%= "Hello World!" %>--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--</body>--%>
<%--</html>--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<% response.sendRedirect(request.getContextPath() + "/home"); %>--%>


<!DOCTYPE html>
<html>
<head>
    <title>FoodExpress - Delicious Food Delivered</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
</head>
<body>
<jsp:include page="views/partials/navbar.jsp" />

<!-- Hero Section -->
<section class="hero-section">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6">
                <h1 class="display-4 fw-bold mb-4">Delicious food delivered to your doorstep</h1>
                <p class="lead mb-4">Order from your favorite restaurants with just a few clicks</p>
                <div class="search-box">
                    <form action="${pageContext.request.contextPath}/restaurants" method="get">
                        <div class="input-group">
                            <label>
                                <input type="text" class="form-control" placeholder="Enter your address..." name="location">
                            </label>
                            <button class="btn btn-primary" type="submit">
                                <i class="fas fa-search me-2"></i> Find Food
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-6">
                <img src="${pageContext.request.contextPath}/images/hero-delivery.jpg" alt="Food Delivery" class="img-fluid">
            </div>
        </div>
    </div>
</section>

<!-- How It Works -->
<section class="how-it-works py-5 bg-light">
    <div class="container">
        <h2 class="text-center mb-5">How It Works</h2>
        <div class="row text-center">
            <div class="col-md-4 mb-4">
                <div class="step-card p-4">
                    <div class="step-icon mb-3">
                        <i class="fas fa-map-marker-alt fa-3x text-primary"></i>
                    </div>
                    <h4>1. Choose Location</h4>
                    <p>Enter your address to find restaurants near you</p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="step-card p-4">
                    <div class="step-icon mb-3">
                        <i class="fas fa-utensils fa-3x text-primary"></i>
                    </div>
                    <h4>2. Order Food</h4>
                    <p>Browse menus and add items to your cart</p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="step-card p-4">
                    <div class="step-icon mb-3">
                        <i class="fas fa-truck fa-3x text-primary"></i>
                    </div>
                    <h4>3. Delivery</h4>
                    <p>Get your food delivered fast and fresh</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Featured Restaurants -->
<section class="featured-restaurants py-5">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Featured Restaurants</h2>
            <a href="${pageContext.request.contextPath}/restaurants" class="btn btn-outline-primary">View All</a>
        </div>
        <div class="row">
            <c:forEach items="${featuredRestaurants}" var="restaurant">
                <div class="col-md-3 mb-4">
                    <div class="card restaurant-card h-100">
                        <img src="${restaurant.imageUrl}" class="card-img-top" alt="${restaurant.name}">
                        <div class="card-body">
                            <h5 class="card-title">${restaurant.name}</h5>
                            <p class="card-text text-muted">${restaurant.cuisine}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                    <span class="badge bg-warning text-dark">
                                        <i class="fas fa-star"></i> ${restaurant.rating}
                                    </span>
                                <span class="text-muted">${restaurant.deliveryTime}</span>
                            </div>
                        </div>
                        <div class="card-footer bg-white">
                            <a href="${pageContext.request.contextPath}/restaurants/${restaurant.id}" class="btn btn-sm btn-primary w-100">View Menu</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- Popular Categories -->
<section class="popular-categories py-5 bg-light">
    <div class="container">
        <h2 class="text-center mb-5">Popular Categories</h2>
        <div class="row">
            <c:forEach items="${popularCategories}" var="category">
                <div class="col-6 col-md-3 mb-4">
                    <a href="${pageContext.request.contextPath}/restaurants?category=${category}" class="category-card">
                        <div class="card text-center h-100">
                            <div class="card-body">
                                <div class="category-icon mb-3">
                                    <i class="fas fa-${category == 'Pizza' ? 'pizza-slice' :
                                                          category == 'Burger' ? 'hamburger' :
                                                          category == 'Sushi' ? 'fish' :
                                                          category == 'Pasta' ? 'utensils' : 'leaf'} fa-3x"></i>
                                </div>
                                <h5 class="card-title">${category}</h5>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- App Download CTA -->
<section class="app-download py-5" style="background-color: #f8f9fa;">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 mb-4 mb-lg-0">
                <h2 class="mb-4">Get the FoodExpress App</h2>
                <p class="lead mb-4">Download our app for faster ordering and exclusive offers</p>
                <div class="d-flex flex-wrap">
                    <a href="#" class="btn btn-dark me-3 mb-2">
                        <i class="fab fa-apple me-2"></i> App Store
                    </a>
                    <a href="#" class="btn btn-dark mb-2">
                        <i class="fab fa-google-play me-2"></i> Google Play
                    </a>
                </div>
            </div>
            <div class="col-lg-6 text-center">
                <img src="${pageContext.request.contextPath}/images/app-screenshot.jpg" alt="App Screenshot" class="img-fluid" style="max-height: 400px;">
            </div>
        </div>
    </div>
</section>

<jsp:include page="views/partials/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>