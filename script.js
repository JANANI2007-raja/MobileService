// ===============================
// SERVICE DATE
// ===============================

const dateInput = document.getElementById("date");

const today = new Date();

const minDate = today.toISOString().split("T")[0];

const maxDateObject = new Date();

maxDateObject.setDate(maxDateObject.getDate() + 30);

const maxDate =
    maxDateObject.toISOString().split("T")[0];

dateInput.min = minDate;
dateInput.max = maxDate;


// ===============================
// SERVICE FORM
// ===============================

const serviceForm =
    document.querySelector(".service-form");

serviceForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        // Get form values

        const name =
            document.getElementById("name").value.trim();

        const phone =
            document.getElementById("phone").value.trim();

        const brand =
            document.getElementById("brand").value;

        const model =
            document.getElementById("model").value.trim();

        const service =
            document.getElementById("service").value;

        const problem =
            document.getElementById("problem").value.trim();

        const date =
            document.getElementById("date").value;


        // Check phone number

        if (phone.length < 10) {

            alert(
                "Please enter a valid phone number."
            );

            return;
        }


        // Create data object

        const serviceData = {

            customerName: name,

            phone: phone,

            brand: brand,

            model: model,

            serviceType: service,

            problem: problem,

            preferredDate: date
        };


        try {

            // Send data to Spring Boot

            const response = await fetch(
                "http://localhost:8080/api/services",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(serviceData)
                }
            );


            // Check response

            if (!response.ok) {

                throw new Error(
                    "Failed to register service"
                );
            }


            // Get response from backend

            const savedService =
                await response.json();


            // Save returned details temporarily
            // for success page

            localStorage.setItem(
                "serviceId",
                savedService.serviceId
            );

            localStorage.setItem(
                "serviceName",
                savedService.customerName
            );

            localStorage.setItem(
                "servicePhone",
                savedService.phone
            );

            localStorage.setItem(
                "serviceBrand",
                savedService.brand
            );

            localStorage.setItem(
                "serviceModel",
                savedService.model
            );

            localStorage.setItem(
                "serviceType",
                savedService.serviceType
            );

            localStorage.setItem(
                "serviceProblem",
                savedService.problem
            );

            localStorage.setItem(
                "serviceDate",
                savedService.preferredDate
            );


            // Open success page

            window.location.href =
                "success.html";

        }

        catch (error) {

            console.error(error);

            alert(
                "Unable to connect to the server. Please try again."
            );
        }

    }
);