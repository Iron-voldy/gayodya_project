document.addEventListener('DOMContentLoaded', function() {
    // Show/hide card details based on payment method selection
    const paymentMethods = document.querySelectorAll('input[name="paymentMethod"]');
    const cardDetails = document.getElementById('creditCardDetails');

    paymentMethods.forEach(method => {
        method.addEventListener('change', function() {
            if (this.id === 'creditCard') {
                cardDetails.style.display = 'block';
            } else {
                cardDetails.style.display = 'none';
            }
        });
    });

    // Format card number input
    const cardNumber = document.getElementById('cardNumber');
    if (cardNumber) {
        cardNumber.addEventListener('input', function() {
            let value = this.value.replace(/\s+/g, '');
            if (value.length > 0) {
                value = value.match(new RegExp('.{1,4}', 'g')).join(' ');
            }
            this.value = value;
        });
    }

    // Format expiry date input
    const expiryDate = document.getElementById('expiryDate');
    if (expiryDate) {
        expiryDate.addEventListener('input', function() {
            let value = this.value.replace(/\D/g, '');
            if (value.length > 2) {
                value = value.substring(0, 2) + '/' + value.substring(2, 4);
            }
            this.value = value;
        });
    }

    // Validate form before submission
    const paymentForm = document.querySelector('form');
    if (paymentForm) {
        paymentForm.addEventListener('submit', function(e) {
            const selectedMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
            let isValid = true;

            if (selectedMethod === 'Credit Card') {
                const cardNumber = document.getElementById('cardNumber').value.replace(/\s+/g, '');
                const expiryDate = document.getElementById('expiryDate').value;
                const cvv = document.getElementById('cvv').value;

                if (cardNumber.length !== 16 || !/^\d+$/.test(cardNumber)) {
                    alert('Please enter a valid 16-digit card number');
                    isValid = false;
                }

                if (!/^\d{2}\/\d{2}$/.test(expiryDate)) {
                    alert('Please enter a valid expiry date in MM/YY format');
                    isValid = false;
                }

                if (cvv.length !== 3 || !/^\d+$/.test(cvv)) {
                    alert('Please enter a valid 3-digit CVV');
                    isValid = false;
                }
            }

            if (!isValid) {
                e.preventDefault();
            }
        });
    }
});