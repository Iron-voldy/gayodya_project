<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Admins - FoodExpress</title>
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
        <h1 class="h2">Manage Administrators</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <a href="${pageContext.request.contextPath}/admin/add" class="btn btn-sm btn-primary">
            <i class="fas fa-user-plus me-1"></i> Add Admin
          </a>
        </div>
      </div>

      <c:if test="${param.success != null}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          <c:choose>
            <c:when test="${param.success == 'added'}">Admin added successfully!</c:when>
            <c:when test="${param.success == 'updated'}">Admin updated successfully!</c:when>
            <c:when test="${param.success == 'deleted'}">Admin deleted successfully!</c:when>
          </c:choose>
          <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
      </c:if>

      <div class="card">
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Level</th>
                <th>Department</th>
                <th>Actions</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${admins}" var="admin">
                <tr>
                  <td>${admin.id}</td>
                  <td>${admin.username}</td>
                  <td>${admin.email}</td>
                  <td><span class="badge bg-info">${admin.adminLevel}</span></td>
                  <td>${admin.department}</td>
                  <td>
                    <a href="${pageContext.request.contextPath}/admin/edit?id=${admin.id}"
                       class="btn btn-sm btn-outline-primary me-1">
                      <i class="fas fa-edit"></i>
                    </a>
                    <form action="${pageContext.request.contextPath}/admin/delete" method="post" class="d-inline">
                      <input type="hidden" name="id" value="${admin.id}">
                      <button type="submit" class="btn btn-sm btn-outline-danger"
                              onclick="return confirm('Are you sure you want to delete this admin?')">
                        <i class="fas fa-trash-alt"></i>
                      </button>
                    </form>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
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