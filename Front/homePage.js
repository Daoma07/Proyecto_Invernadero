document.addEventListener('DOMContentLoaded', function() {
    var userIcon = document.getElementById('user-icon');
    var userMenu = document.getElementById('user-menu');

    userIcon.addEventListener('click', function() {
        userMenu.style.display = userMenu.style.display === 'block' ? 'none' : 'block';
    });

    // Ocultar el menú cuando se haga clic fuera de él
    document.addEventListener('click', function(event) {
        if (!userMenu.contains(event.target) && event.target !== userIcon) {
            userMenu.style.display = 'none';
        }
    });
});
//Para mostrar el contenido en el mismo homePage
function showContent(contentName) {
    var mainContent = document.getElementById('main-content');

    switch(contentName) {
        case 'invernaderos':
            fetch('invernadero.html')
                .then(response => response.text())
                .then(html => {
                    mainContent.innerHTML = html;
                })
                .catch(error => console.error('Error al cargar la página de invernadero:', error));
            break;
        case 'sensores':
            mainContent.innerHTML = '<h1>Sensores</h1><p>Contenido de los sensores...</p>';
            break;
        case 'umbrales':
            mainContent.innerHTML = '<h1>Umbrales</h1><p>Contenido de los umbrales...</p>';
            break;
        case 'servicios':
            mainContent.innerHTML = '<h1>Servicios</h1><p>Contenido de los servicios...</p>';
            break;
        default:
            mainContent.innerHTML = '<h1>Contenido no encontrado</h1>';
    }
}

/*function loadInvernaderoPage() {
    // Cargar el contenido de invernadero.html en el área principal de homePage.html
    fetch('invernadero.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
        })
        .catch(error => console.error('Error al cargar la página de invernadero:', error));
}*/