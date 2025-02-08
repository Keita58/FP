package SpringBANG.controller;

import SpringBANG.entity.*;
import SpringBANG.repository.CartesRepository;
import SpringBANG.services.*;
import SpringBANG.utils.TextPantalla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(path="/mosqueperros")
public class MainController {
    @Autowired
    JugadorService jugadorService;
    @Autowired
    CartaService cartaService;
    @Autowired
    PartidesService partidesService;
    @Autowired
    JugadorsRivalsService jugadorsRivalsService;
    @Autowired
    private RolService rolService;

    // Pàgina web -> http://localhost:9001/swagger-ui/index.html

    // Funcions GET
    @GetMapping(path="/")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok(new TextPantalla("Servidor funcionant")) ;
    }

    @GetMapping(path = "/getJugadorsAmbPersonatgesVida")
    public ResponseEntity<?> getJugadorsAmbVida() {
        List<Jugadors> jugadors = jugadorService.getJugadorsAmbPersonatgesVida();
        return ResponseEntity.ok(jugadors);
    }

    @GetMapping(path = "/getCartesSenseJugador")
    public ResponseEntity<?> getCartesSenseJugador() {
        List<Cartes> cartes = cartaService.getCartesSenseJugador();
        return ResponseEntity.ok(cartes);
    }

    @GetMapping(path = "/getCartesBang")
    public ResponseEntity<?> getCartesBANGSenseJugador() {
        List<Cartes> cartes = cartaService.getCartesBangSenseJugador();
        return ResponseEntity.ok(cartes);
    }

    @GetMapping(path = "/getPartidesNoFinalitzades")
    public ResponseEntity<?> getPartidesNoFinalitzades() {
        List<Partides> partides = partidesService.getPartidesNoFinalitzades();
        return ResponseEntity.ok(partides);
    }

    @GetMapping(path = "/retornarJugadorsOrdenatsPosicio")
    public ResponseEntity<?> retornarJugadorsOrdenats() {
        List<Jugadors> jugadors = jugadorService.retornarJugadorsOrdenats();
        return ResponseEntity.ok(jugadors);
    }

    @GetMapping(path = "/getJugadorsRolsVida")
    public ResponseEntity<?> getJugadorsRolVida(Rol rol) {
        List<Jugadors> jugadors = jugadorService.getJugadorsRolsVida(rolService.buscarRol(rol));
        return ResponseEntity.ok(jugadors);
    }

    @GetMapping(path = "/getCartesFallaste")
    public ResponseEntity<?> getCartesFallasteJugador(Integer idJugador) {
        //Jugadors aux = new Jugadors(nomJugador);
        Jugadors aux = jugadorService.getJugadorPerId(idJugador);
        List<Cartes> cartes = cartaService.getCartesFallasteJugador(aux);
        return ResponseEntity.ok(cartes);
    }

    @GetMapping(path = "/getCartesJugador")
    public ResponseEntity<?> getCartesJugador(Integer idJugador) {
        //Jugadors aux = new Jugadors(nomJugador);
        Jugadors aux = jugadorService.getJugadorPerId(idJugador);
        List<Cartes> cartes = cartaService.getCartesJugador(aux);
        System.out.println(cartes);
        return ResponseEntity.ok(cartes);
    }

    @GetMapping(path = "/getJugadorsAmbPersonatgesVidaAltres")
    public ResponseEntity<?> getJugadorsAmbPersonatgesVidaAltres(Integer idJugador) {
        //Jugadors aux = new Jugadors(nomJugador);
        Jugadors aux = jugadorService.getJugadorPerId(idJugador);
        List<Jugadors> jugadors = jugadorService.getJugadorsAmbPersonatgesVidaAltres(aux);
        return ResponseEntity.ok(jugadors);
    }
    // Final funcions amb GET

    @PostMapping(path="/agafarCartes")
    public ResponseEntity<?> AgafarCartes(@RequestBody Integer idJugador) {
        Jugadors j = jugadorService.getJugadorPerId(idJugador);
        //Jugadors j = jugadorService.getJugadorDelNom(nomJugador);
        List<Cartes> cartesList= cartaService.getCartesSenseJugador();
        Collections.shuffle(cartesList);
        Random r = new Random();
        int cartaRandom=r.nextInt(0, cartesList.size());
        j.getCartes().add(cartesList.get(cartaRandom));
        cartesList.get(cartaRandom).setCartesJugador(j);
        jugadorService.editar(j);
        cartaService.editar(cartesList.get(cartaRandom));
        return ResponseEntity.ok(new TextPantalla("JUGADOR HA AGAFAT LA CARTA!")) ;
    }

    @PostMapping(path="/deixarCartes")
    public ResponseEntity<?> DeixarCartes(@RequestBody Integer idJugador) {
        Jugadors j = jugadorService.getJugadorPerId(idJugador);
        //Jugadors j = jugadorService.getJugadorDelNom(nomJugador);
        List<Cartes> cartesList=cartaService.getCartesJugador(j);
        Collections.shuffle(cartesList);
        if (!j.getCartes().isEmpty()){
            cartesList.get(0).setCartesJugador(null);
            j.getCartes().remove(cartesList.get(0));
            cartaService.editar(cartesList.get(0));
            jugadorService.editar(j);
            return ResponseEntity.ok(new TextPantalla("EL JUGADOR HA DEIXAT LA CARTA!!!")) ;
        }else{
            return ResponseEntity.ok(new TextPantalla("EL JUGADOR NO TENIA CARTES PER A DEIXAR"));
        }
    }

    @PostMapping(path="/tirarCartes")
    public ResponseEntity<?> TirarCartes(@RequestBody Integer idJugador) {
        Jugadors jugador = jugadorService.getJugadorPerId(idJugador);
        //Jugadors jugador = jugadorService.getJugadorDelNom(nomJugador);
        List<Cartes> retornar = new ArrayList<>();
        List<Jugadors> jugadorsAmbVida = jugadorService.getJugadorsAmbPersonatgesVida();
        List<Cartes> cartes = cartaService.getCartesJugador(jugador);
        Random r = new Random();

        int cartesATirar = 0;
        // Si el jugador té cartes a la mà crea una variable del nombre de cartes que el jugador haurà de tirar.
        if(!jugador.getCartes().isEmpty() && jugador.getCartes().size() > 1) {
            cartesATirar = r.nextInt(1, cartes.size());
        }
        else if(jugador.getCartes().size() == 1)
            cartesATirar = 1;

        Collections.shuffle(cartes);
        System.out.println("El jugador " + jugador.getNom() + " jugarà " + cartesATirar + " cartes de les " + jugador.getCartes().size() + " cartes que té: ");

        // Agafem cartes aleatòries de totes les que té el jugador.
        for(int i = 0; i < cartesATirar; i++) {
            if(!cartes.get(i).getcartaTipusCarta().getNom().equals("Has Fallat!")) {
                retornar.add(cartes.get(i));
                cartes.get(i).setCartesJugador(null);
                jugador.getCartes().remove(cartes.get(i));
                cartaService.editar(cartes.get(i));
                jugadorService.editar(jugador);
            }
        }

        boolean mort = false;
        for(Cartes c : retornar) {
            switch(c.getcartaTipusCarta().getIdTipusCartes()) {
                case 1: //BANG!
                    Collections.shuffle(jugadorsAmbVida);
                    boolean acabat = false;
                    // Mirem tots els jugadors vius actuals
                    for(Jugadors ju : jugadorsAmbVida) {
                        Set<JugadorsRivals> rivals = jugador.getJugadorsRivals();
                        // Per cada rival que té el jugador que ha tirat la carta
                        for(JugadorsRivals jr : rivals) {
                            // Si aquest jugador rival que hem agafat té vida i és el jugador del bucle anterior entrem
                            if(jr.getIdRival().getIdRival().getPersonatgeDelJugador().getBales() > 0 && jr.getIdRival().getIdRival().getIdJugador() == ju.getIdJugador() && jr.getIdRival().getIdJugador() == jugador) {
                                // Si el jugador que està tirant la carta és l'ajudant i el contrincant és el Xerif no pot tirar la carta. Ha de buscar un altre blanc
                                if((jugador.getRolJugador().getNomRol() == Rol.AJUDANT && jr.getIdRival().getIdRival().getRolJugador().getNomRol() == Rol.XERIF)) {
                                    System.out.println("L'Ajudant no pot disparar el Xerif! Seria traició.");
                                }
                                else {
                                    // Si l'arma del jugador té un rang major o igual a la distància amb el contrincant pot tirar la carta.
                                    if(jugador.getArmaJugador().getDistanciaArma() >= jr.getDistanciaRival()) {
                                        // El contrincant perd una vida.
                                        jr.getIdRival().getIdRival().getPersonatgeDelJugador().setBales(jr.getIdRival().getIdRival().getPersonatgeDelJugador().getBales() - 1);
                                        jugadorService.editar(jr.getIdRival().getIdRival());
                                        System.out.println("Ha jugat un BANG! contra el jugador " +  jr.getIdRival().getIdRival().getPersonatgeDelJugador().getNom() + "! Quina mala baba.");
                                        System.out.println("L'enemic ara té " +  jr.getIdRival().getIdRival().getPersonatgeDelJugador().getBales() + " vides.");
                                        Set<Cartes> cartesEnemic = jr.getIdRival().getIdRival().getCartes();
                                        // Una vegada el contrincant ha perdut una vida li mirem a la mà si té un "Has Fallat!"
                                        for(Cartes carta : cartesEnemic) {
                                            // Si la té li tirem la carta
                                            if(carta.getcartaTipusCarta().getNom().equals("Has Fallat!")) {
                                                carta.setCartesJugador(null);
                                                jr.getIdRival().getIdRival().getCartes().remove(carta);
                                                cartaService.editar(carta);
                                                jr.getIdRival().getIdRival().getPersonatgeDelJugador().setBales(jr.getIdRival().getIdRival().getPersonatgeDelJugador().getBales() + 1);
                                                jugadorService.editar(jr.getIdRival().getIdRival());
                                                System.out.println("L'enemic tenia un Has Fallat! No ha servit de res el BANG :(");
                                                break;
                                            }
                                        }
                                    }
                                    // En el cas que la distància fos major tiraria la carta igualment, però no faria cap efecte
                                    else {
                                        System.out.println("Ha jugat un BANG! contra el jugador " + ju.getNom() + " però no hi arriba! Quina mala sort");
                                    }
                                    acabat = true;
                                }
                                break;
                            }
                        }
                        if(acabat)
                            break;
                    }
                    break;
                case 2: //Miratelescòpica
                    Set<JugadorsRivals> rivals = jugador.getJugadorsRivals();

                    // Agafem un rival aleatòri de JugadorRivals i li disminuïm la distància en 1 (si aquesta era major a 0)
                    for(JugadorsRivals jr : rivals) {
                        if(jr.getDistanciaRival() > 0) {
                            jr.setDistanciaRival(jr.getDistanciaRival() - 1);
                            System.out.println("Ha jugat una Miratelescòpica contra el jugador " + jr.getIdRival().getIdRival().getNom() + "! Està maquinant alguna cosa...");
                            jugadorsRivalsService.editar(jr);
                            break;
                        }
                    }
                    break;
                /*case 3: //Has fallat!
                    CurarPersonatge(j);
                    System.out.println("Ha jugat un Has Fallat! i s'ha curat! Que bé que es deu sentir amb una vida més.");
                    break;*/
                case 4: //Pànic
                    AgafarCarta(jugador);
                    System.out.println("Ha jugat un Pànic!. Necessitava més cartes.");
                    break;
                case 5: //Ingenua
                    DeixarCartes(jugador);
                    System.out.println("Ha jugat una Ingenua. No deu tenir bones cartes...");
                    break;
                case 6: //Esquivar
                    CurarPersonatge(jugador);
                    AgafarCarta(jugador);
                    System.out.println("Ha tirat un Esquivar. Deu estar en les últimes...");
                    break;
                case 7: //Indis
                    jugador.getPersonatgeDelJugador().setBales(jugador.getPersonatgeDelJugador().getBales() - 1);
                    System.out.println("Ha jugat un Indis. S'ha fet un tret als peus.");
                    if(jugador.getPersonatgeDelJugador().getBales() == 0)
                        mort = true;
                    break;
                case 8: //Cervesa
                    CurarPersonatge(jugador);
                    System.out.println("Ha tirat una Cervesa. Mai va malament emborratxar-se una mica.");
                    break;
            }
            if(mort)
                break;
        }

        return ResponseEntity.ok(new TextPantalla("Carta tirada satisfactòriament")) ;
    }

    @PostMapping(path="/reassignarDistancies")
    public ResponseEntity<?> ReassignarDistancies(){
        jugadorsRivalsService.deleteAll();

        List<Jugadors> jugadorsList = jugadorService.findAll();
        Set<JugadorsRivals> enemics = new HashSet<>();
        int a = 0;
        for(Jugadors j : jugadorsList) {
            for(Jugadors k : jugadorsList) {
                // Creem dos bucles, per parsejar tots els jugadors entre ells.
                if(!j.equals(k)) {
                    // Si els jugadors són diferents (no és un jugador contras si mateix) creem el valor aux de JugadorRivalsId.
                    JugadorRivalsId aux = new JugadorRivalsId(k, j);
                    if(a > 0) {
                        // Mirem dins del set creat abans per si ja existeix la dupla d'enemics que estem intentant afegir.
                        for (JugadorsRivals jr : enemics) {
                            // Aquí comprovem si el JugadorRivals existent no és el mateix que volem afegir però en l'ordre invers. Si ho és passem al següent.
                            if (!(jr.getIdRival().getIdJugador().getIdJugador() == k.getIdJugador() && jr.getIdRival().getIdRival().getIdJugador() == j.getIdJugador())) {
                                if (Math.abs(j.getPosicio() - k.getPosicio()) < jugadorsList.size()/2) {
                                    enemics.add(new JugadorsRivals(aux, Math.abs(j.getPosicio() - k.getPosicio())));
                                } else {
                                    enemics.add(new JugadorsRivals(aux, jugadorsList.size() - Math.abs(j.getPosicio() - k.getPosicio())));
                                }
                                break;
                            }
                        }
                    }
                    else {
                        // Aquí només entrarem la primera vegada amb el primer jugador, perquè no hi haurà cap valor al set en haver-lo acabat de crear.
                        if (Math.abs(j.getPosicio() - k.getPosicio()) < jugadorsList.size()/2) {
                            enemics.add(new JugadorsRivals(aux, Math.abs(j.getPosicio() - k.getPosicio())));
                        } else {
                            enemics.add(new JugadorsRivals(aux, jugadorsList.size() - Math.abs(j.getPosicio() - k.getPosicio())));
                        }
                    }
                }
            }
            j.setJugadorsRivals(enemics);
            a++;
        }

        for(JugadorsRivals jr : enemics) {
            jugadorsRivalsService.insertar(jr);
        }

       return ResponseEntity.ok(jugadorsRivalsService.findAll()) ;
    }

    @PostMapping(path ="/finalPartida")
    public ResponseEntity<?> FinalPartida(){
        Partides p = partidesService.getPartidesFinalitzades().get(0);
        List<Jugadors> jugadorsList = jugadorService.getJugadorsAmbPersonatgesVida();
        for (Jugadors j : jugadorsList) {
            j.setGuanyats(j.getGuanyats()+1);
            jugadorService.editar(j);
        }
        if (p!=null){
            p.setPartidaFinalitzada(true);
            partidesService.editar(p);
        }
        return ResponseEntity.ok(new TextPantalla("FINAL PARTIDA"));
    }

    /**
     * Agafa una carta de la pila de cartes de la base de dades de manera aleatòria.
     * @param j Jugador que agafa la carta de la base de dades.
     */
    public void AgafarCarta(Jugadors j) {

        Random r = new Random();
        List<Cartes> listCartes = cartaService.getCartesSenseJugador();
        int cartaRandom = r.nextInt(0, listCartes.size());
        j.getCartes().add(listCartes.get(cartaRandom));
        listCartes.get(cartaRandom).setCartesJugador(j);
        jugadorService.editar(j);
        cartaService.editar(listCartes.get(cartaRandom));
        System.out.println("El jugador " + j.getNom() + " ha agafat una carta: "+listCartes.get(cartaRandom));
    }

    /**
     * Elimina una carta aleatòria de la mà del jugador que es passa per paràmetre.
     * Aquesta carta passa a estar a la pila de robar automàticament.
     * @param j Jugador que descarta la carta
     */
    public void DeixarCartes(Jugadors j) {

        List<Cartes> cartes = cartaService.getCartesJugador(j);
        Collections.shuffle(cartes);

        if(!j.getCartes().isEmpty()) {
            cartes.get(0).setCartesJugador(null);
            j.getCartes().remove(cartes.get(0));
            cartaService.editar(cartes.get(0));
            jugadorService.editar(j);
            System.out.println("El jugador " + j.getNom() + " ha deixat una carta.");
        }
        else {
            System.out.println("El jugador no té cartes per descartar :(");
        }
    }

    /**
     * Funció extreta de codi igual a {@link #TirarCartes(String)} i que cura al personatge que es passa per paràmetre un punt de vida.
     * Aquesta curació no pot superar el nombre màxim de vides del personatge que tingui un jugador.
     * @param j Personatge a curar
     */
    private void CurarPersonatge(Jugadors j) {

        if(!(j.getPersonatgeDelJugador().getNom().equals("El Gringo") || j.getPersonatgeDelJugador().getNom().equals("Paul Regret"))) {
            if(j.getPersonatgeDelJugador().getBales() < 4)
                j.getPersonatgeDelJugador().setBales(j.getPersonatgeDelJugador().getBales() + 1);
        }
        else {
            if(j.getPersonatgeDelJugador().getBales() < 3)
                j.getPersonatgeDelJugador().setBales(j.getPersonatgeDelJugador().getBales() + 1);
        }
        jugadorService.editar(j);
    }
}
