<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Reports - FoodExpress</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
  <!-- Chart.js for visualization -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body class="admin-dashboard">
<jsp:include page="../partials/navbar.jsp" />

<div class="container-fluid">
  <div class="row">
    <jsp:include page="partials/admin-sidebar.jsp" />

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">System Reports</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group me-2">
            <button type="button" class="btn btn-sm btn-outline-secondary" id="exportPdf">
              <i class="fas fa-file-pdf me-1"></i> Export PDF
            </button>
            <button type="button" class="btn btn-sm btn-outline-secondary" id="exportExcel">
              <i class="fas fa-file-excel me-1"></i> Export Excel
            </button>
          </div>
          <div class="dropdown">
            <button class="btn btn-sm btn-outline-secondary dropdown-toggle" type="button"
                    data-bs-toggle="dropdown">
              <i class="fas fa-calendar me-1"></i> Last 30 Days
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#" data-range="7">Last 7 Days</a></li>
              <li><a class="dropdown-item" href="#" data-range="30">Last 30 Days</a></li>
              <li><a class="dropdown-item" href="#" data-range="90">Last 90 Days</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item" href="#" data-range="0">All Time</a></li>
            </ul>
          </div>
        </div>
      </div>

      <div class="row mb-4">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h5>Orders Overview</h5>
            </div>
            <div class="card-body">
              <canvas id="ordersChart" height="250"></canvas>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h5>Revenue Breakdown</h5>
            </div>
            <div class="card-body">
              <canvas id="revenueChart" height="250"></canvas>
            </div>
          </div>
        </div>
      </div>

      <div class="row mb-4">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h5>User Registrations</h5>
            </div>
            <div class="card-body">
              <canvas id="usersChart" height="250"></canvas>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h5>Restaurant Performance</h5>
            </div>
            <div class="card-body">
              <canvas id="restaurantsChart" height="250"></canvas>
            </div>
          </div>
        </div>
      </div>

      <div class="card mb-4">
        <div class="card-header">
          <h5>Recent Activity Log</h5>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>Timestamp</th>
                <th>User</th>
                <th>Action</th>
                <th>Details</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>2023-07-15 14:30</td>
                <td>admin</td>
                <td><span class="badge bg-success">Created</span></td>
                <td>New restaurant "Pizza Heaven" added</td>
              </tr>
              <tr>
                <td>2023-07-14 09:15</td>
                <td>admin</td>
                <td><span class="badge bg-warning text-dark">Updated</span></td>
                <td>User profile updated (john_doe)</td>
              </tr>
              <tr>
                <td>2023-07-13 16:45</td>
                <td>support</td>
                <td><span class="badge bg-danger">Deleted</span></td>
                <td>Order #ORD100 cancelled</td>
              </tr>
              <tr>
                <td>2023-07-12 11:20</td>
                <td>admin</td>
                <td><span class="badge bg-info">System</span></td>
                <td>Daily backup completed</td>
              </tr>
              <tr>
                <td>2023-07-11 08:30</td>
                <td>content</td>
                <td><span class="badge bg-success">Created</span></td>
                <td>New menu category added</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </main>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin-reports.js"></script>
</body>
</html>