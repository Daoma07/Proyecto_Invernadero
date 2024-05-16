let invernaderos = [];
const checkboxContacto = document.getElementById("checkboxContacto");
const contactoInput = document.getElementById("contacto");

contactoInput.disabled = true;
checkboxContacto.addEventListener("change", function() {
    if (this.checked) {
        // If checkbox is checked, enable the input field
        contactoInput.disabled = false;
    } else {
        // If checkbox is not checked, disable the input field
        contactoInput.disabled = true;
    }
});

// Function to populate the list of invernaderos
function populateInvernaderos() {
    const lista = document.getElementById("lista");
    lista.innerHTML = ""; // Clear existing list items

    // Loop through each invernadero and create list items
    invernaderos.forEach((invernadero, index) => {
        const listItem = document.createElement("li");
        listItem.textContent = invernadero.name;
        listItem.addEventListener("click", () => {
            displayInvernaderoDetails(index);
        });
        lista.appendChild(listItem);
    });
}

// muestra los detalles de invernadero en la pantalla de arriba
function displayInvernaderoDetails(index) {
    const selectedInvernadero = invernaderos[index];
    alert(`Name: ${selectedInvernadero.name}\nLocation: ${selectedInvernadero.location}\nDescription: ${selectedInvernadero.description}`);
}

// registra un nuevo invernadero
function registerInvernadero() {
    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value;
    const location = document.getElementById("location").value;
    const idContacto = document.getElementById("contacto").value;

    // checa si están vacíos
    if (nombre && descripcion && location) {
        // crea un nuevo objeto de invernadero
        const newInvernadero = {
            name: nombre,
            description: descripcion,
            location: location
        };

        // Add el nuevo invernadero a la lista
        invernaderos.push(newInvernadero);

        // Reset the form inputs
        document.getElementById("nombre").value = "";
        document.getElementById("descripcion").value = "";
        document.getElementById("location").value = "";

        populateInvernaderos(); 

    } else {
        alert("Por favor, complete todos los campos.");
    }
}

document.getElementById("agregarContacto").addEventListener("click", function() {
    const contacto = document.getElementById("contacto").value.trim(); // Captura el ID del contacto y elimina espacios en blanco

    if (contacto !== "") { // Verifica si se ha ingresado un ID de contacto
        invernaderos.forEach(invernadero => {
            if (!invernadero.contactos) {
                invernadero.contactos = []; // Crea un array de contactos si no existe
            }
            invernadero.contactos.push(contacto); // Agrega el ID del contacto al invernadero
        });
        
        // Limpia el campo de entrada del ID del contacto
        document.getElementById("contacto").value = ""; 
        // Actualiza la lista de invernaderos
        populateInvernaderos(); 
    } else {
        alert("No se ha ingresado ningún ID de contacto.");
    }
});

//esto es para que se carguen al darle registrar
document.getElementById("formulario-invernadero").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que el formulario se envíe automáticamente

    // Llama a la función registerInvernadero() para procesar los datos del formulario
    registerInvernadero();
});

// Populate the list of invernaderos when the page loads
window.addEventListener("DOMContentLoaded", populateInvernaderos);

// Event listener for the "Registrar" button
document.getElementById("registrar-btn").addEventListener("click", registerInvernadero);

