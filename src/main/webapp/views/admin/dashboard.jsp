<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard - FoodExpress</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
</head>
<body class="admin-dashboard">
<jsp:include page="../partials/navbar.jsp" />

<div class="container-fluid">
  <div class="row">
    <jsp:include page="partials/admin-sidebar.jsp" />

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Dashboard</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group me-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
            <i class="fas fa-calendar"></i> This week
          </button>
        </div>
      </div>

      <div class="row mb-4">
        <div class="col-md-3">
          <jsp:include page="partials/stats-card.jsp">
            <jsp:param name="icon" value="users" />
            <jsp:param name="title" value="Total Users" />
            <jsp:param name="value" value="${totalUsers}" />
            <jsp:param name="color" value="primary" />
          </jsp:include>
        </div>
        <div class="col-md-3">
          <jsp:include page="partials/stats-card.jsp">
            <jsp:param name="icon" value="shopping-cart" />
            <jsp:param name="title" value="Total Orders" />
            <jsp:param name="value" value="${totalOrders}" />
            <jsp:param name="color" value="success" />
          </jsp:include>
        </div>
        <div class="col-md-3">
          <jsp:include page="partials/stats-card.jsp">
            <jsp:param name="icon" value="store" />
            <jsp:param name="title" value="Restaurants" />
            <jsp:param name="value" value="${totalRestaurants}" />
            <jsp:param name="color" value="info" />
          </jsp:include>
        </div>
        <div class="col-md-3">
          <jsp:include page="partials/stats-card.jsp">
            <jsp:param name="icon" value="dollar-sign" />
            <jsp:param name="title" value="Revenue" />
            <jsp:param name="value" value="${revenue}" />
            <jsp:param name="color" value="warning" />
            <jsp:param name="prefix" value="$" />
          </jsp:include>
        </div>
      </div>

      <div class="row">
        <div class="col-md-8">
          <div class="card mb-4">
            <div class="card-header">
              <h5>Recent Orders</h5>
            </div>
            <div class="card-body">
              <div class="text-center py-5">
                <i class="fas fa-chart-line fa-4x text-muted mb-4"></i>
                <p>Order analytics chart will be displayed here</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card mb-4">
            <div class="card-header">
              <h5>Quick Actions</h5>
            </div>
            <div class="card-body">
              <div class="d-grid gap-2">
                <a href="${pageContext.request.contextPath}/admin/add" class="btn btn-primary mb-2">
                  <i class="fas fa-user-plus me-2"></i> Add Admin
                </a>
                <a href="${pageContext.request.contextPath}/admin/manage" class="btn btn-outline-primary mb-2">
                  <i class="fas fa-users-cog me-2"></i> Manage Admins
                </a>
                <a href="${pageContext.request.contextPath}/admin/reports" class="btn btn-outline-success mb-2">
                  <i class="fas fa-file-alt me-2"></i> View Reports
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
</body>
</html>