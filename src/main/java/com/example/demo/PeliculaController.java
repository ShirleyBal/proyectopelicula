package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dao.PeliculasCRUD;
import com.example.demo.model.Pelicula;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {

    private final PeliculasCRUD peliculasCRUD;

    public PeliculaController() {
        this.peliculasCRUD = new PeliculasCRUD(); // Instancia manual de PeliculasCRUD
    }

    // Endpoint para obtener todas las películas
    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Pelicula>> getAllMovies() {
        try {
            List<Pelicula> peliculas = peliculasCRUD.listar();
            return new ResponseEntity<>(peliculas, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obtener una película por su ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Pelicula> getPeliculaById(@PathVariable("id") Integer id) {
    //     try {
    //         Pelicula pelicula = peliculasCRUD.getPeliculaById(id);
    //         if (pelicula != null) {
    //             return new ResponseEntity<>(pelicula, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // Endpoint para crear una nueva película
    // @PostMapping("/create")
    // public ResponseEntity<Void> createMovie(@RequestBody Pelicula pelicula) {
    //     try {
    //         peliculasCRUD.createMovie(pelicula.getId(), pelicula.getTitulo(), pelicula.getAnio(),
    //                 pelicula.getPuntuacion(), pelicula.getPortada(), pelicula.getTipo(), pelicula.getCategoriaId());
    //         return new ResponseEntity<>(HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

     // Endpoint para crear 
     @PostMapping("/addPelicula")
     public ResponseEntity<Void> createMovie(@RequestBody Pelicula pelicula) {
         try {
             peliculasCRUD.createMovie(pelicula.getTitulo(), pelicula.getAnio(),
                     pelicula.getPuntuacion(), pelicula.getPortada(), pelicula.getTipo(), pelicula.getCategoria());
             return new ResponseEntity<>(HttpStatus.CREATED);
         } catch (Exception e) {
             e.printStackTrace();
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

    // Endpoint para actualizar una película existente
    public ResponseEntity<Pelicula> updateMovie(@PathVariable("id") Integer id, @RequestBody Pelicula pelicula) {
        try {
            pelicula.setId(id); // Asignamos el id auto
            peliculasCRUD.updateMovie(pelicula.getId(), pelicula.getTitulo(), pelicula.getAnio(),
                    pelicula.getPuntuacion(), pelicula.getPortada(), pelicula.getTipo(), pelicula.getCategoria());
            return new ResponseEntity<>(pelicula, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para eliminar una película por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable int id) {
        PeliculasCRUD peliculasCRUD = new PeliculasCRUD();
        peliculasCRUD.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}


////////////////////
// package com.example.demo;

// import java.util.List;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.dao.PeliculaDAO;
// import com.example.demo.model.Pelicula;

// @RestController
// public class PeliculaController {

//     @CrossOrigin(origins = "*")
//     @GetMapping("/listarPeliculas")
//     public List<Pelicula> listarPeliculas() {
//         PeliculaDAO peliculaDAO=new PeliculaDAO();
//         return peliculaDAO.listar();
//     }
    
//     @CrossOrigin(origins = "*")
//     @PostMapping("/addPelicula")
//     public void addPelicula(@RequestBody String titulo,  String puntuacion, String portada) {
//         Pelicula peli=new Pelicula(titulo, Double.valueOf(puntuacion), portada);

//         PeliculaDAO peliculaDAO=new PeliculaDAO();
//         peliculaDAO.add(peli);

//     }

//     @CrossOrigin(origins = "*")
//     @DeleteMapping("/delPelicula/{id}")
//     public void delPelicula(@PathVariable("id") Integer id) {
//         Pelicula peli=new Pelicula(id);

//         PeliculaDAO peliculaDAO=new PeliculaDAO();
//         peliculaDAO.del(peli);

//     }
// }
