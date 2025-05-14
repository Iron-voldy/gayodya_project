<div class="card text-white bg-${param.color} mb-3">
  <div class="card-body">
    <div class="d-flex justify-content-between align-items-center">
      <div>
        <h6 class="card-title mb-1">${param.title}</h6>
        <h2 class="mb-0">${param.prefix != null ? param.prefix : ''}${param.value}</h2>
      </div>
      <i class="fas fa-${param.icon} fa-3x opacity-50"></i>
    </div>
  </div>
  <div class="card-footer bg-white bg-opacity-10 border-0">
    <small>Last updated 5 mins ago</small>
  </div>
</div>