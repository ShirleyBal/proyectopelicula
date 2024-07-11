// index.js

document.addEventListener('DOMContentLoaded', function () {
    console.log('DOM completamente cargado y procesado');
    // Llama a esta función al cargar la página para obtener las películas
    getPeliculas();
});

function getPeliculas() {
    console.log('Llamando a la API para obtener películas');
    fetch('http://localhost:8080/peliculas/getAllMovies')
        .then(response => {
            console.log('Respuesta recibida de la API:', response);
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos:', data);
            renderPeliculas(data);
        })
        .catch(error => console.error('Error al obtener películas:', error));
}

function renderPeliculas(peliculas) {
    const peliculasContainer = document.getElementById('peliculas');
    peliculasContainer.innerHTML = ''; // Limpia el contenedor antes de agregar nuevas películas

    peliculas.forEach(pelicula => {
        console.log('Renderizando película:', pelicula);
        const movieItem = document.createElement('div');
        movieItem.classList.add('movie-item');

        const link = document.createElement('a');
        link.href = 'detail-movie.html'; // Ajusta la URL de detalle de la película según tu proyecto

        const img = document.createElement('img');
        img.src = pelicula.portada; // Aquí asumes que tu objeto película tiene una propiedad 'portada' con la URL de la imagen
        img.alt = ''; // Ajusta el texto alternativo según sea necesario
        img.classList.add('movie-item-img');

        link.appendChild(img);
        movieItem.appendChild(link);
        peliculasContainer.appendChild(movieItem);
    });
}
