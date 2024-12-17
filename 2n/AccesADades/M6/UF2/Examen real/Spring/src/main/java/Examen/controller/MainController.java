package Examen.controller;

import Examen.entity.Jugadors;
import Examen.entity.Personatges;
import Examen.services.JugadorService;
import Examen.services.PersonatgeService;
import Examen.utils.TextPantalla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MainController {
    @Autowired
    JugadorService jugadorService;
    @Autowired
    PersonatgeService personatgeService;

    //http://localhost:9000/swagger-ui/index.html

    @GetMapping(path="/")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok(new TextPantalla("Spring funcionant")) ;
    }

    @GetMapping(path = "/jugadors")
    public ResponseEntity<?> getJugadors() {
        List<Jugadors> jugadors = jugadorService.findAll();
        return ResponseEntity.ok(jugadors);
    }

    @GetMapping(path = "/findAllPersonatges")
    public ResponseEntity<?> getPersonatges() {
        List<Personatges> personatges = personatgeService.findAll();
        return ResponseEntity.ok(personatges);
    }

    @GetMapping(path = "/getJugadorsAmbPersonatges")
    public ResponseEntity<?> getJugadorsAmbPersonatges() {
        List<Jugadors> jugadors = jugadorService.getJugadorsAmbPersonatges();
        return ResponseEntity.ok(jugadors);
    }

    @PostMapping(path="/AlvaroKeepHittingAgain")
    public ResponseEntity<?> Alvaro() {
        List<Jugadors> list = jugadorService.findAll();
        List<Jugadors> jugadorsAmbPersonatges = new ArrayList<>();
        for (Object jugador : list) {
            Jugadors j = (Jugadors) jugador;
            if(j.getPersonatgeDelJugador() != null) {
                Object[] aux = j.getPersonatgeDelJugador().toArray();
                if(((Personatges) aux[0]).getViu()) {
                    j.setPunts(j.getPunts() + 25);
                    jugadorService.editar(j);
                }
                else
                    j.setPunts(j.getPunts() - 12);
            }
            else
                j.setPunts(j.getPunts() - 12);
        }
        return ResponseEntity.ok("Dades guardades correctament!") ;
    }
}
