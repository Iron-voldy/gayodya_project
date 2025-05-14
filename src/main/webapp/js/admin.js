document.addEventListener('DOMContentLoaded', function() {
    // Confirm before deleting admin
    document.querySelectorAll('form[action*="/admin/delete"]').forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!confirm('Are you sure you want to delete this admin?')) {
                e.preventDefault();
            }
        });
    });

    // Password strength indicator
    const passwordInput = document.getElementById('password');
    if (passwordInput) {
        passwordInput.addEventListener('input', function() {
            const strengthIndicator = document.getElementById('password-strength');
            if (!strengthIndicator) return;

            const strength = calculatePasswordStrength(this.value);
            strengthIndicator.textContent = strength.text;
            strengthIndicator.className = 'form-text text-' + strength.color;
        });
    }

    // Calculate password strength
    function calculatePasswordStrength(password) {
        let strength = 0;

        // Length check
        if (password.length > 7) strength++;
        if (password.length > 11) strength++;

        // Character variety
        if (/[A-Z]/.test(password)) strength++;
        if (/[0-9]/.test(password)) strength++;
        if (/[^A-Za-z0-9]/.test(password)) strength++;

        // Return result
        if (password.length === 0) {
            return { text: '', color: 'muted' };
        } else if (strength < 3) {
            return { text: 'Weak', color: 'danger' };
        } else if (strength < 5) {
            return { text: 'Moderate', color: 'warning' };
        } else {
            return { text: 'Strong', color: 'success' };
        }
    }

    // Initialize tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
});