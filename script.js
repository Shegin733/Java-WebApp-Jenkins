// javaScript.js - Fetch Data from a Java Backend
document.addEventListener("DOMContentLoaded", function() {
    const button = document.getElementById("fetchJavaData");
    
    button.addEventListener("click", function() {
        fetch("http://localhost:8080/api/message")
            .then(response => response.text())
            .then(data => {
                document.getElementById("output").innerText = "Java Backend Says: " + data;
            })
            .catch(error => console.error("Error fetching data:", error));
    });
});