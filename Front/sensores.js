let sensores = [];
const checkboxContacto = document.getElementById("checkboxContacto");
const contactoInput = document.getElementById("umbral");

const checkboxContacto1 = document.getElementById("checkboxContacto1");
const contactoInput1 = document.getElementById("invernadero");

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

contactoInput1.disabled = true;
checkboxContacto1.addEventListener("change", function() {
    if (this.checked) {
        // If checkbox is checked, enable the input field
        contactoInput1.disabled = false;
    } else {
        // If checkbox is not checked, disable the input field
        contactoInput1.disabled = true;
    }
});

// Función para poblar la lista de sensores y umbrales
function populateSensores() {
    const listaSensores = document.getElementById("lista-sensores");
    const listaUmbrales = document.getElementById("umbrales"); // Nueva línea

    listaSensores.innerHTML = ""; // Limpiar elementos de la lista existentes
    listaUmbrales.innerHTML = ""; // Limpiar opciones de umbrales existentes

    // Recorrer cada sensor y crear elementos de lista
    sensores.forEach((sensor, index) => {
        const listItem = document.createElement("li");
        listItem.textContent = `Serie: ${sensor.serie}, Lugar: ${sensor.lugar}, Gateway: ${sensor.gateway}, Protocolo: ${sensor.protocolo}`;
        listItem.addEventListener("click", () => {
            displaySensorDetails(index);
        });
        listaSensores.appendChild(listItem);
    });

    // Recorrer cada umbral y crear opciones en la lista desplegable
    umbrales.forEach((umbral) => {
        const option = document.createElement("option");
        option.value = umbral.id; // Asignar el ID del umbral como valor
        option.textContent = `Umbral ID: ${umbral.id}, Máx: ${umbral.max}, Mín: ${umbral.min}, Condiciones: ${umbral.condiciones}`;
        listaUmbrales.appendChild(option);
    });
}

// Función para mostrar los detalles del sensor
function displaySensorDetails(index) {
    const selectedSensor = sensores[index];
    alert(`Detalles del sensor:\nSerie: ${selectedSensor.serie}\nLugar: ${selectedSensor.lugar}\nGateway: ${selectedSensor.gateway}\nProtocolo: ${selectedSensor.protocolo}`);
}

// Función para registrar un nuevo sensor
function registerSensor() {
    const serie = document.getElementById("serie").value;
    const lugar = document.getElementById("lugar").value;
    const gateway = document.getElementById("gateway").value;
    const protocolo = document.getElementById("protocolo").value;
    const perteneceInvernadero = document.getElementById("pertenece_invernadero").checked;
    const invernaderoSeleccionado = document.getElementById("invernadero").value;
    const umbralSeleccionado = document.getElementById("umbrales").value; // Nueva línea

    // Comprobar si los campos están vacíos
    if (serie && lugar && gateway && protocolo) {
        // Crear un nuevo objeto de sensor
        const newSensor = {
            serie: serie,
            lugar: lugar,
            gateway: gateway,
            protocolo: protocolo,
            perteneceInvernadero: perteneceInvernadero,
            invernaderoSeleccionado: perteneceInvernadero ? invernaderoSeleccionado : null,
            umbralSeleccionado: umbralSeleccionado // Nueva línea
        };

        // Agregar el nuevo sensor a la lista
        sensores.push(newSensor);

        // Limpiar los campos del formulario
        document.getElementById("serie").value = "";
        document.getElementById("lugar").value = "";
        document.getElementById("gateway").value = "";
        document.getElementById("protocolo").value = "";
        document.getElementById("pertenece_invernadero").checked = false;
        document.getElementById("invernadero").disabled = true;

        populateSensores(); // Volver a poblar la lista de sensores y umbrales
    } else {
        alert("Por favor, complete todos los campos.");
    }
}

// Event listener para el formulario de registro de sensores
document.getElementById("formulario-sensor").addEventListener("submit", function(event) {
    event.preventDefault(); // Evitar que el formulario se envíe automáticamente

    // Llamar a la función registerSensor() para procesar los datos del formulario
    registerSensor();
});

// Poblar la lista de sensores cuando la página se cargue
window.addEventListener("DOMContentLoaded", populateSensores);

// Event listener para el botón "Registrar"
document.getElementById("registrar-btn").addEventListener("click", registerSensor);

//impresion prueba
// Función para imprimir la lista de sensores
function imprimirListaSensores() {
    console.log("Lista de Sensores:");
    listaSensores.forEach(sensor => {
        console.log(`Serie: ${sensor.serie}, Lugar: ${sensor.lugar}, Gateway: ${sensor.gateway}, Protocolo: ${sensor.protocolo}`);
    });
}

// Llamar a la función para imprimir la lista de sensores
imprimirListaSensores();