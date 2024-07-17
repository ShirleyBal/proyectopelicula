const options = {
    headers: {
        accept: 'application/json',
        'Content-Type': 'application/json'
    }
};

// TRAER PELIS:
function getPelis() {
    fetch('http://localhost:8080/peliculas/getAllMovies', { ...options, method: 'GET' })
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
                <button onclick="showAddPeliModal()" class="btn-add"></button>
            </td>
        </tr>
        `;
    }
    peliculasTable.innerHTML = rows;
}

function delPeli(id) {
    fetch(`http://localhost:8080/peliculas/delete/${id}`, { ...options, method: 'DELETE' })
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

function showAddPeliModal() {
    const addPeliModal = new bootstrap.Modal(document.getElementById('addPeliModal'));
    addPeliModal.show();
}

document.getElementById('addPeliForm').addEventListener('submit', createPeli);

// Function to handle form submission and create a new movie
function createPeli(event) {
    event.preventDefault();
    const titulo = document.getElementById('titulo').value;
    const anio = document.getElementById('anio').value;
    const puntuacion = document.getElementById('puntuacion').value;
    const portada = document.getElementById('portada').value;
    const tipo = document.getElementById('tipo').value;
    const categoria = document.getElementById('categoria').value;

    const pelicula = {
        titulo,
        anio: parseInt(anio),
        puntuacion: parseInt(puntuacion),
        portada,
        tipo,
        categoria
    };

    fetch('http://localhost:8080/peliculas/addPelicula', {
        ...options,
        method: 'POST',
        body: JSON.stringify(pelicula)
    })
        .then(response => {
            if (response.ok) {
                mostrarMensaje('Pelicula creada', 'success');
                getPelis(); // Actualiza la lista de películas
                const addPeliModal = bootstrap.Modal.getInstance(document.getElementById('addPeliModal'));
                addPeliModal.hide(); // Cierra el modal después de agregar la película
            } else {
                mostrarMensaje('Error película no creada', 'danger');
            }
        })
        .catch(error => mostrarMensaje('Error al crear película: ' + error, 'danger'));
}

function dibujarError(error) {
    const peliculasTable = document.getElementById('peliculas');
    peliculasTable.innerHTML = '<tr><td colspan="7" class="text-danger">' + error + '</td></tr>';
}

//Editar peliculas
document.getElementById('editPeliForm').addEventListener('submit', updatePeli);

function updatePeli(event) {
    event.preventDefault();
    const id = document.getElementById('editPeliId').value;
    const titulo = document.getElementById('editTitulo').value;
    const anio = document.getElementById('editAnio').value;
    const puntuacion = document.getElementById('editPuntuacion').value;
    const portada = document.getElementById('editPortada').value;
    const tipo = document.getElementById('editTipo').value;
    const categoria = document.getElementById('editCategoria').value;

    const pelicula = {
        id,
        titulo,
        anio: parseInt(anio),
        puntuacion: parseInt(puntuacion),
        portada,
        tipo,
        categoria
    };

    fetch(`http://localhost:8080/peliculas/update/${id}`, {
        ...options,
        method: 'PUT',
        body: JSON.stringify(pelicula)
    })
        .then(response => {
            if (response.ok) {
                mostrarMensaje('Película actualizada', 'success');
                getPelis(); // Actualiza la lista de películas
                const editPeliModal = bootstrap.Modal.getInstance(document.getElementById('editPeliModal'));
                editPeliModal.hide(); // Cierra el modal después de actualizar la película
            } else {
                mostrarMensaje('Error al actualizar la película', 'danger');
            }
        })
        .catch(error => mostrarMensaje('Error al actualizar película: ' + error, 'danger'));
}

// Función para abrir el modal de edición y cargar los datos de la película
function editPeli(id) {
    fetch(`http://localhost:8080/peliculas/getAllMovies`, { ...options, method: 'GET' })
        .then(response => response.json())
        .then(peliculas => {
            const pelicula = peliculas.find(p => p.id === id);
            if (pelicula) {
                document.getElementById('editPeliId').value = pelicula.id;
                document.getElementById('editTitulo').value = pelicula.titulo;
                document.getElementById('editAnio').value = pelicula.anio;
                document.getElementById('editPuntuacion').value = pelicula.puntuacion;
                document.getElementById('editPortada').value = pelicula.portada;
                document.getElementById('editTipo').value = pelicula.tipo;
                document.getElementById('editCategoria').value = pelicula.categoria;
                const editPeliModal = new bootstrap.Modal(document.getElementById('editPeliModal'));
                editPeliModal.show();
            }
        })
        .catch(error => mostrarMensaje('Error al obtener datos de la película: ' + error, 'danger'));
}

