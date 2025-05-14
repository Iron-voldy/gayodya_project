document.addEventListener('DOMContentLoaded', function() {
    // Initialize charts
    initOrdersChart();
    initRevenueChart();
    initUsersChart();
    initRestaurantsChart();

    // Date range selector
    document.querySelectorAll('.dropdown-item[data-range]').forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            const range = this.getAttribute('data-range');
            const text = this.textContent;

            // Update dropdown button text
            document.querySelector('.dropdown-toggle').innerHTML =
                `<i class="fas fa-calendar me-1"></i> ${text}`;

            // In a real app, you would reload data for the selected range
            console.log(`Loading data for last ${range} days`);
        });
    });

    // Export buttons
    document.getElementById('exportPdf').addEventListener('click', function() {
        alert('PDF export functionality would be implemented here');
        // In a real app, this would generate and download a PDF report
    });

    document.getElementById('exportExcel').addEventListener('click', function() {
        alert('Excel export functionality would be implemented here');
        // In a real app, this would generate and download an Excel report
    });
});

function initOrdersChart() {
    const ctx = document.getElementById('ordersChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
            datasets: [{
                label: 'Completed Orders',
                data: [65, 59, 80, 81, 56, 55, 40],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 2,
                tension: 0.1
            }, {
                label: 'Cancelled Orders',
                data: [5, 8, 3, 2, 6, 4, 3],
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 2,
                tension: 0.1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                tooltip: {
                    mode: 'index',
                    intersect: false,
                }
            },
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function initRevenueChart() {
    const ctx = document.getElementById('revenueChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Food Sales', 'Delivery Fees', 'Taxes', 'Discounts'],
            datasets: [{
                label: 'Amount ($)',
                data: [4250, 320, 285, 150],
                backgroundColor: [
                    'rgba(54, 162, 235, 0.7)',
                    'rgba(255, 206, 86, 0.7)',
                    'rgba(153, 102, 255, 0.7)',
                    'rgba(255, 99, 132, 0.7)'
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false
                }
            },
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function initUsersChart() {
    const ctx = document.getElementById('usersChart').getContext('2d');
    new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Customers', 'Restaurants', 'Admins'],
            datasets: [{
                data: [300, 45, 5],
                backgroundColor: [
                    'rgba(54, 162, 235, 0.7)',
                    'rgba(255, 99, 132, 0.7)',
                    'rgba(75, 192, 192, 0.7)'
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 99, 132, 1)',
                    'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'right',
                }
            }
        }
    });
}

class Chart {
    constructor(ctx, param2) {

    }

}

function initRestaurantsChart() {
    const ctx = document.getElementById('restaurantsChart').getContext('2d');
    new Chart(ctx, {
        type: 'radar',
        data: {
            labels: ['Italian', 'Chinese', 'Japanese', 'American', 'Mexican', 'Indian'],
            datasets: [{
                label: 'Orders by Cuisine',
                data: [65, 59, 90, 81, 56, 55],
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 2
            }, {
                label: 'Average Rating',
                data: [4.5, 4.2, 4.7, 4.3, 4.1, 4.4],
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderColor: 'rgba(255, 159, 64, 1)',
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            scales: {
                r: {
                    angleLines: {
                        display: true
                    },
                    suggestedMin: 0,
                    suggestedMax: 5
                }
            }
        }
    });
}