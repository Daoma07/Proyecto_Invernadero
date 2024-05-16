let contactos = [];

// Function to populate the list of contactos
function populateContactos() {
    const lista = document.getElementById("lista");
    lista.innerHTML = ""; // Clear existing list items

    // Loop through each contacto and create list items
    contactos.forEach((contacto, index) => {
        const listItem = document.createElement("li");
        listItem.textContent = `${contacto.nombre} ${contacto.apellido}`;
        listItem.addEventListener("click", () => {
            displayContactoDetails(index);
        });
        lista.appendChild(listItem);
    });
}

// muestra los detalles de contacto en la pantalla de arriba
function displayContactoDetails(index) {
    const selectedContacto = contactos[index];
    alert(`Nombre: ${selectedContacto.nombre}\nApellido: ${selectedContacto.apellido}\nTeléfono: ${selectedContacto.telefono}\nEmail: ${selectedContacto.email}`);
}

// registra un nuevo contacto
function registerContacto() {
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const telefono = document.getElementById("telefono").value;
    const email = document.getElementById("email").value;

    // checa si están vacíos
    if (nombre && apellido && telefono && email) {
        // crea un nuevo objeto de contacto
        const newContacto = {
            nombre: nombre,
            apellido: apellido,
            telefono: telefono,
            email: email
        };

        // Add el nuevo contacto a la lista
        contactos.push(newContacto);

        // Reset the form inputs
        document.getElementById("nombre").value = "";
        document.getElementById("apellido").value = "";
        document.getElementById("telefono").value = "";
        document.getElementById("email").value = "";

        populateContactos(); 

    } else {
        alert("Por favor, complete todos los campos.");
    }
}

//esto es para que se carguen al darle registrar
document.getElementById("formulario-contacto").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que el formulario se envíe automáticamente

    // Llama a la función registerContacto() para procesar los datos del formulario
    registerContacto();
});

// Populate the list of contactos when the page loads
window.addEventListener("DOMContentLoaded", populateContactos);