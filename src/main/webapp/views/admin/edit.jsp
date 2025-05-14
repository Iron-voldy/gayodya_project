<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Admin - FoodExpress</title>
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
        <h1 class="h2">Edit Administrator</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <a href="${pageContext.request.contextPath}/admin/manage" class="btn btn-sm btn-outline-secondary">
            <i class="fas fa-arrow-left me-1"></i> Back to List
          </a>
        </div>
      </div>

      <div class="card">
        <div class="card-body">
          <form action="${pageContext.request.contextPath}/admin/edit" method="post">
            <input type="hidden" name="id" value="${admin.id}">

            <div class="row mb-3">
              <div class="col-md-6">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username"
                       value="${admin.username}" required>
              </div>
              <div class="col-md-6">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password"
                       value="${admin.password}" required>
              </div>
            </div>

            <div class="row mb-3">
              <div class="col-md-6">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${admin.email}" required>
              </div>
              <div class="col-md-3">
                <label for="adminLevel" class="form-label">Admin Level</label>
                <select class="form-select" id="adminLevel" name="adminLevel" required>
                  <option value="Super Admin" ${admin.adminLevel == 'Super Admin' ? 'selected' : ''}>Super Admin</option>
                  <option value="Content Admin" ${admin.adminLevel == 'Content Admin' ? 'selected' : ''}>Content Admin</option>
                  <option value="Support Admin" ${admin.adminLevel == 'Support Admin' ? 'selected' : ''}>Support Admin</option>
                </select>
              </div>
              <div class="col-md-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department"
                       value="${admin.department}" required>
              </div>
            </div>

            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
              <a href="${pageContext.request.contextPath}/admin/manage" class="btn btn-secondary me-md-2">
                <i class="fas fa-times me-1"></i> Cancel
              </a>
              <button type="submit" class="btn btn-primary">
                <i class="fas fa-save me-1"></i> Save Changes
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