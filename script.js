// ========================================
// EXPERIMENT 3
// COMPREHENSIVE CLIENT-SIDE VALIDATION
// ========================================


// ========================================
// REGISTRATION FORM VALIDATION
// ========================================

const registerForm =
    document.getElementById("registerForm");


if (registerForm) {

    registerForm.addEventListener(
        "submit",
        function (event) {

            event.preventDefault();


            // Get input values

            const fullname =
                document.getElementById("fullname")
                    .value.trim();


            const email =
                document.getElementById("email")
                    .value.trim();


            const password =
                document.getElementById("password")
                    .value;


            const confirmPassword =
                document.getElementById("confirmPassword")
                    .value;


            const message =
                document.getElementById(
                    "registerMessage"
                );


            // Clear previous message

            message.textContent = "";


            // -------------------------------
            // FULL NAME VALIDATION
            // -------------------------------

            if (fullname === "") {

                showMessage(
                    message,
                    "Full name is required.",
                    "danger"
                );

                return;
            }


            // Name must contain only letters and spaces

            const namePattern =
                /^[A-Za-z ]{3,}$/;


            if (!namePattern.test(fullname)) {

                showMessage(
                    message,
                    "Name must contain at least 3 letters and only alphabets.",
                    "danger"
                );

                return;
            }


            // -------------------------------
            // EMAIL VALIDATION
            // -------------------------------

            if (email === "") {

                showMessage(
                    message,
                    "Email address is required.",
                    "danger"
                );

                return;
            }


            const emailPattern =
                /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


            if (!emailPattern.test(email)) {

                showMessage(
                    message,
                    "Please enter a valid email address.",
                    "danger"
                );

                return;
            }


            // -------------------------------
            // PASSWORD VALIDATION
            // -------------------------------

            if (password === "") {

                showMessage(
                    message,
                    "Password is required.",
                    "danger"
                );

                return;
            }


            // Minimum 6 characters

            if (password.length < 6) {

                showMessage(
                    message,
                    "Password must contain at least 6 characters.",
                    "danger"
                );

                return;
            }


            // Password must contain uppercase,
            // lowercase and a number

            const passwordPattern =
                /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$/;


            if (!passwordPattern.test(password)) {

                showMessage(
                    message,
                    "Password must contain uppercase, lowercase and a number.",
                    "danger"
                );

                return;
            }


            // -------------------------------
            // CONFIRM PASSWORD VALIDATION
            // -------------------------------

            if (confirmPassword === "") {

                showMessage(
                    message,
                    "Please confirm your password.",
                    "danger"
                );

                return;
            }


            if (password !== confirmPassword) {

                showMessage(
                    message,
                    "Password and Confirm Password must match.",
                    "danger"
                );

                return;
            }


            // -------------------------------
            // STORE USER DATA
            // -------------------------------

            const user = {

                fullname: fullname,

                email: email,

                password: password

            };


            localStorage.setItem(

                "greenCampusUser",

                JSON.stringify(user)

            );


            // Success Message

            showMessage(

                message,

                "Registration successful!",

                "success"

            );


            // Reset Form

            registerForm.reset();

        }

    );

}



// ========================================
// LOGIN FORM VALIDATION
// ========================================

const loginForm =
    document.getElementById("loginForm");


if (loginForm) {

    loginForm.addEventListener(

        "submit",

        function (event) {

            event.preventDefault();


            // Get values

            const email =
                document.getElementById(
                    "loginEmail"
                ).value.trim();


            const password =
                document.getElementById(
                    "loginPassword"
                ).value;


            const message =
                document.getElementById(
                    "loginMessage"
                );


            // -------------------------------
            // EMAIL VALIDATION
            // -------------------------------

            if (email === "") {

                showMessage(

                    message,

                    "Email address is required.",

                    "danger"

                );

                return;

            }


            const emailPattern =
                /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


            if (!emailPattern.test(email)) {

                showMessage(

                    message,

                    "Please enter a valid email address.",

                    "danger"

                );

                return;

            }


            // -------------------------------
            // PASSWORD VALIDATION
            // -------------------------------

            if (password === "") {

                showMessage(

                    message,

                    "Password is required.",

                    "danger"

                );

                return;

            }


            // -------------------------------
            // CHECK REGISTERED USER
            // -------------------------------

            const storedUser =
                JSON.parse(

                    localStorage.getItem(
                        "greenCampusUser"
                    )

                );


            if (!storedUser) {

                showMessage(

                    message,

                    "No registered user found. Please register first.",

                    "danger"

                );

                return;

            }


            // -------------------------------
            // CHECK LOGIN CREDENTIALS
            // -------------------------------

            if (

                email === storedUser.email &&

                password === storedUser.password

            ) {

                showMessage(

                    message,

                    "Login successful! Redirecting...",

                    "success"

                );


                // Redirect to Metrics Page

                setTimeout(

                    function () {

                        window.location.href =
                            "metrics.html";

                    },

                    1000

                );

            }


            else {

                showMessage(

                    message,

                    "Invalid email or password.",

                    "danger"

                );

            }

        }

    );

}



// ========================================
// COMMON MESSAGE FUNCTION
// ========================================

function showMessage(
    element,
    text,
    type
) {

    element.textContent = text;


    element.className =
        "text-center mt-3 fw-bold text-" + type;

}   
const latitude = 17.3850;
const longitude = 78.4867;

const apiUrl =
    `https://air-quality-api.open-meteo.com/v1/air-quality?latitude=${latitude}&longitude=${longitude}&current=pm10,pm2_5,carbon_dioxide,nitrogen_dioxide,ozone&timezone=auto`;

fetch(apiUrl)
    .then(response => response.json())
    .then(data => {

        console.log(data);

        document.getElementById("pm25").textContent =
            data.current.pm2_5 + " μg/m³";

        document.getElementById("pm10").textContent =
            data.current.pm10 + " μg/m³";

        document.getElementById("co2").textContent =
            data.current.carbon_dioxide + " ppm";

        document.getElementById("no2").textContent =
            data.current.nitrogen_dioxide + " μg/m³";

        document.getElementById("ozone").textContent =
            data.current.ozone + " μg/m³";

    })
    .catch(error => {

        console.error(
            "Error fetching environmental data:",
            error
        );

    });