<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add Admin - FoodExpress</title>
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
        <h1 class="h2">Add New Administrator</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <a href="${pageContext.request.contextPath}/admin/manage" class="btn btn-sm btn-outline-secondary">
            <i class="fas fa-arrow-left me-1"></i> Back to List
          </a>
        </div>
      </div>

      <div class="card">
        <div class="card-body">
          <form action="${pageContext.request.contextPath}/admin/add" method="post">
            <div class="row mb-3">
              <div class="col-md-6">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
              </div>
              <div class="col-md-6">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
              </div>
            </div>

            <div class="row mb-3">
              <div class="col-md-6">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
              </div>
              <div class="col-md-3">
                <label for="adminLevel" class="form-label">Admin Level</label>
                <select class="form-select" id="adminLevel" name="adminLevel" required>
                  <option value="">Select Level</option>
                  <option value="Super Admin">Super Admin</option>
                  <option value="Content Admin">Content Admin</option>
                  <option value="Support Admin">Support Admin</option>
                </select>
              </div>
              <div class="col-md-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department" required>
              </div>
            </div>

            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
              <button type="reset" class="btn btn-secondary me-md-2">
                <i class="fas fa-undo me-1"></i> Reset
              </button>
              <button type="submit" class="btn btn-primary">
                <i class="fas fa-save me-1"></i> Save Admin
              </button>
            </div>
          </form>
        </div>
      </div>
    </main>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
</body>
</html>