let umbrales = [];

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


// Function to populate the list of umbrales
function populateUmbrales() {
    const lista = document.getElementById("lista-umbrales");
    lista.innerHTML = ""; // Clear existing list items

    // Loop through each umbral and create list items
    umbrales.forEach((umbral, index) => {
        const listItem = document.createElement("li");
        listItem.textContent = `ID: ${umbral.id}, Mínimo: ${umbral.min}, Máximo: ${umbral.max}, Condiciones: ${umbral.condiciones}`;
        listItem.addEventListener("click", () => {
            displayUmbralDetails(index);
        });
        lista.appendChild(listItem);
    });
}

// muestra los detalles de umbral en la pantalla de arriba
function displayUmbralDetails(index) {
    const selectedUmbral = umbrales[index];
    alert(`ID: ${selectedUmbral.id}\nMínimo: ${selectedUmbral.min}\nMáximo: ${selectedUmbral.max}\nCondiciones: ${selectedUmbral.condiciones}`);
}

// registra un nuevo umbral
function registerUmbral() {
    const id = document.getElementById("id").value;
    const min = document.getElementById("min").value;
    const max = document.getElementById("max").value;
    const condiciones = document.getElementById("condiciones").value;

    // checa si están vacíos
    if (id && min && max && condiciones) {
        // crea un nuevo objeto de umbral
        const newUmbral = {
            id: id,
            min: min,
            max: max,
            condiciones: condiciones
        };

        // Add el nuevo umbral a la lista
        umbrales.push(newUmbral);

        // Reset the form inputs
        document.getElementById("id").value = "";
        document.getElementById("min").value = "";
        document.getElementById("max").value = "";

        populateUmbrales(); 
    } else {
        alert("Por favor, complete todos los campos.");
    }
}

// esto es para que se carguen al darle registrar
document.getElementById("formulario-umbrales").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que el formulario se envíe automáticamente

    // Llama a la función registerUmbral() para procesar los datos del formulario
    registerUmbral();
});

// Populate the list of umbrales when the page loads
window.addEventListener("DOMContentLoaded", populateUmbrales);

// Event listener for the "Registrar" button
document.getElementById("registrar-btn").addEventListener("click", registerUmbral);

