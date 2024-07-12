const options = {
    method: 'GET',
    headers: {
      accept: 'application/json',
      Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTJjYTAwZDYxZWIzOTEyYjZlNzc4MDA4YWQ3ZmNjOCIsInN1YiI6IjYyODJmNmYwMTQ5NTY1MDA2NmI1NjlhYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4MJSPDJhhpbHHJyNYBtH_uCZh4o0e3xGhZpcBIDy-Y8'
    }
};

// TRAER PELIS:
function getPelis() {
    fetch('http://localhost:8080/peliculas/getAllMovies', options)
        .then(response => response.json())
        .then(response => renderPelis(response))
        .catch(error => dibujarError('Error al obtener peliculas: ' + error));
}

function renderPelis(peliculas) {
    const peliculasTable = document.getElementById('peliculas');
    peliculasTable.innerHTML = ''; // Limpiamos contenido anterior de la tabla
  
    // Iterar sobre las pelis
    let rows = '';
    for (let peli of peliculas) {
        rows += `
         <tr>
            <td>${peli.titulo}</td>
            <td>${peli.anio}</td>
            <td>${peli.puntuacion}</td>
            <td>${peli.tipo}</td>
            <td>${peli.categoria}</td>
            <td>
                <img src="${peli.portada}" alt="" class="img-fluid">
            </td>
            <td>
                <button onclick="delPeli(${peli.id})" class="btn-trash"></button>
                <button onclick="editPeli(${peli.id})" class="btn-edit"></button>
                <button onclick="addPeli()" class="btn-add"></button>
            </td>
        </tr>
        `;
    }
    peliculasTable.innerHTML = rows;
}

function delPeli(id) {
    fetch(`http://localhost:8080/peliculas/delete/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.ok) {
            mostrarMensaje('Pelicula eliminada', 'success');
            getPelis(); // Actualiza la lista de películas
        } else {
            mostrarMensaje('Error eliminando la película', 'danger');
        }
    })
    .catch(error => mostrarMensaje('Error al eliminar película: ' + error, 'danger'));
}

function mostrarMensaje(mensaje, tipo) {
    const mensajeDiv = document.getElementById('mensaje');
    mensajeDiv.textContent = mensaje;
    mensajeDiv.className = 'alert alert-' + tipo;
    mensajeDiv.style.display = 'block';
    setTimeout(() => {
        mensajeDiv.style.display = 'none';
    }, 3000); // Ocultar el mensaje después de 3 segundos
}

function dibujarError(error) {
    const peliculasTable = document.getElementById('peliculas');
    peliculasTable.innerHTML = '<tr><td colspan="7" class="text-danger">' + error + '</td></tr>';
}
