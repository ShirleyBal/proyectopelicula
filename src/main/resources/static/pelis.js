const options = {
    method: 'GET',
    headers: {
      accept: 'application/json',
      Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTJjYTAwZDYxZWIzOTEyYjZlNzc4MDA4YWQ3ZmNjOCIsInN1YiI6IjYyODJmNmYwMTQ5NTY1MDA2NmI1NjlhYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4MJSPDJhhpbHHJyNYBtH_uCZh4o0e3xGhZpcBIDy-Y8'
    }
  };
  
  //TRAER PELIS:

function getPelis() {
    const respuesta = fetch(`http://localhost:8080/peliculas/getAllMovies`);
  
  //2 invocar
    respuesta
        .then(response => response.json())
        .then(response => renderPelis(response))//fulfilled
        .catch(error => dibujarError('Error al obtener peliculas: ',error))//rejected
}
  
function renderPelis(peliculas) {
    const peliculasTable = document.getElementById('peliculas');
    peliculasTable.innerHTML = ''; //limpiamos contenido anterior de la tabla
  
    //Iterar sobre las pelis
    let rows = '';
    for(let peli of peliculas) {
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
    document.querySelector('#peliculas').innerHTML = rows;
}

function delPeli(id) {
      const respuesta = fetch(`http://localhost:8080/peliculas/delete/${id}`, {
        method: 'DELETE',
      });
  
      respuesta
          .then(response => DeleteOK(response))//fulfilled
          .catch(error => dibujarError(error))//rejected
}

function DeleteOK(response) {
  document.querySelector('#peliculas').innerHTML = "Se eliminó exitosamente";
}

function dibujarError(error) {
  document.querySelector('#peliculas').innerHTML = error;
}

