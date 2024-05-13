const slidePage = document.querySelector(".slidepage");
const firstNextBtn =document.querySelector(".nextBtn");
const prevBtnSec =document.querySelector(".prev-1");
const nextBtnSec =document.querySelector(".next-1");
const prevBtnThird =document.querySelector(".prev-2");
const nextBtnThird =document.querySelector(".next-2");
const prevBtnFourth =document.querySelector(".prev-3");
const nextBtnFourth =document.querySelector(".next-3");
const prevBtnFifth  =document.querySelector(".prev-4");
const submitBtn =document.querySelector(".submit");

//const defined for showing password
const showPasswordCheckbox = document.getElementById("showPassword");
const passwordInput = document.getElementById("password");
//Same thing but for confirmation password
const showConfirmationPasswordCheckbox = document.getElementById("showConfirmPassword");
const confirmationPasswordInput = document.getElementById("confirmPassword");

//For the first slide
const nombreInput = document.getElementById("textsend1");
const apellidoInput = document.querySelector("input[type='text'][placeholder='Escribe tu Apellido ...']");

firstNextBtn.addEventListener("click", function(){
        // Check if nombre and apellido are filled out
        if (!nombreInput.value.trim() || !apellidoInput.value.trim()) {
            alert("Por favor, complete los campos de Nombre y Apellido.");
            return; // Stop further execution
        }
    slidePage.style.marginLeft = "-25%";
        
});
nextBtnSec.addEventListener("click", function(){
    slidePage.style.marginLeft = "-50%";
});
/*nextBtnThird.addEventListener("click", function(){
    slidePage.style.marginLeft = "-75%";
});
nextBtnFourth.addEventListener("click", function(){
    slidePage.style.marginLeft = "-100%";
});*/

prevBtnSec.addEventListener("click", function(){
    slidePage.style.marginLeft = "0%";
});
prevBtnThird.addEventListener("click", function(){
    slidePage.style.marginLeft = "-25%";
});
/*prevBtnFourth.addEventListener("click", function(){
    slidePage.style.marginLeft = "-50%";
});
prevBtnFifth.addEventListener("click", function(){
    slidePage.style.marginLeft = "-75%";
});*/
submitBtn.addEventListener("click", function(){
    event.preventDefault(); // Prevent the default form submission

    // Get form inputs
    const emailInput = document.getElementById("textsend");
    const passwordInputs = document.querySelectorAll("input[type='password']");
    const confirmPasswordInput = document.getElementById("confirmPassword");

    // Check if the email is valid
    if (!isValidEmail(emailInput.value)) {
        alert("Por favor, introduzca un correo electrónico válido.");
        return; // Stop further execution
    }

    // Check if passwords meet minimum length requirement
    if (passwordInputs[0].value.length < 8 || confirmPasswordInput.value.length < 8) {
        alert("La contraseña debe tener al menos 8 caracteres.");
        return; // Stop further execution
    }

    // Check if both passwords match
    if (passwordInputs[0].value !== confirmPasswordInput.value) {
        alert("Las contraseñas no coinciden.");
        return; // Stop further execution
    }


    // If both conditions are met, submit the form
    alert("Se ha registrado exitosamente.");
    document.querySelector("form").submit(); // Submit the form
});

// Function to check if an email is valid
function    isValidEmail(email) {
    // Regular expression for validating email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}


//showing password
showPasswordCheckbox.addEventListener("change", function () {
    if (showPasswordCheckbox.checked) {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
});

//showing confirmation password
showConfirmationPasswordCheckbox.addEventListener("change", function () {
    if (showConfirmationPasswordCheckbox.checked) {
        confirmationPasswordInput.type = "text";
    } else {
        confirmationPasswordInput.type = "password";
    }
});



function success() {
    if(document.getElementById("textsend").value==="") { 
           document.getElementById('button').disabled = true; 
       } else { 
           document.getElementById('button').disabled = false;
       }
   }

   function success1() {
    if(document.getElementById("textsend1").value==="") { 
           document.getElementById('button1').disabled = true; 
       } else { 
           document.getElementById('button1').disabled = false;
       }
   }

   function ValidateEmail() {

    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  
    if(document.getElementById("textsend").value===(document.value.match(validRegex))) { 
  
      alert("Valid email address!");
  
      document.form1.text1.focus();
      
      document.getElementById('button').disabled = true; 
      return true
      
    } else {
  
      alert("Invalid email address!");
  
      document.form1.text1.focus();
  
      document.getElementById('button').disabled = false; 
  
    }
  
  }