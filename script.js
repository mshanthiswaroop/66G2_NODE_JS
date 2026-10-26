// REGISTRATION
const registerForm = document.getElementById("registerForm");

if (registerForm) {
    registerForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const fullname = document.getElementById("fullname").value.trim();
        const email = document.getElementById("email").value.trim().toLowerCase();
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;
        const message = document.getElementById("registerMessage");

        if (!fullname || !email || !password || !confirmPassword) {
            showMessage(message, "Please fill in all fields.", "danger");
            return;
        }

        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            showMessage(message, "Enter a valid email address.", "danger");
            return;
        }

        if (password.length < 6) {
            showMessage(message, "Password must contain at least 6 characters.", "danger");
            return;
        }

        if (password !== confirmPassword) {
            showMessage(message, "Passwords do not match.", "danger");
            return;
        }

        const user = { fullname, email, password };

        localStorage.setItem("greenCampusUser", JSON.stringify(user));

        showMessage(message, "Registration successful! You can now log in.", "success");
        registerForm.reset();
    });
}


// LOGIN
const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const email = document.getElementById("loginEmail").value.trim().toLowerCase();
        const password = document.getElementById("loginPassword").value;
        const message = document.getElementById("loginMessage");

        if (!email || !password) {
            showMessage(message, "Please enter your email and password.", "danger");
            return;
        }

        let user;

        try {
            user = JSON.parse(localStorage.getItem("greenCampusUser"));
        } catch (error) {
            user = null;
        }

        if (!user || !user.email || typeof user.password !== "string") {
            showMessage(message, "Please register again before logging in.", "danger");
            return;
        }

        if (
            email === user.email.trim().toLowerCase() &&
            password === user.password
        ) {
            showMessage(message, "Login successful! Redirecting...", "success");

            setTimeout(function () {
                window.location.href = "metrics.html";
            }, 1000);
        } else {
            showMessage(message, "Invalid email or password.", "danger");
        }
    });
}


// COMMON MESSAGE FUNCTION
function showMessage(element, text, type) {
    element.textContent = text;
    element.className = "text-center mt-3 fw-bold text-" + type;
}