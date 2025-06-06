<?php

namespace App\Http\Controllers;


use App\Models\Alumnes;
use App\Models\Preguntes;
use App\Models\Questionaris;
use App\Models\Respostes;
use App\Models\Rols;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Application;
use Illuminate\Support\Facades\Route;
use Inertia\Inertia;

class MainControlador extends Controller
{
    public function __construct()
    {
        /*$this->authorizeResource(Rols::class);
        $this->authorizeResource(User::class);*/
        //$this->middleware('auth');
    }

    public function ControladorInici()
    {
        if(User::find(Auth::id()) != null)
        {
            if(User::find(Auth::id())->idRols == 2)
            {
                $questionaris = Questionaris::all();
                $respostes = Respostes::all();
                $preguntes = Preguntes::all();
                return Inertia::render('Inici', ['questionaris' => $questionaris, 'respostes' => $respostes, 'preguntes' => $preguntes]);
            }
            else if(User::find(Auth::id())->idRols == 1)
            {
                $respostes = Respostes::where('tancat', false)->get();
                $preguntes = [];
                for ($i = 0; $i < count($respostes); $i++){
                    $aux = Preguntes::where('idPreguntes', $respostes[$i]->idPregunta)->first();
                    if(!in_array($aux, $preguntes))
                        $preguntes[] = $aux;
                }
                $questionaris = [];
                for ($z = 0; $z < count($preguntes); $z++) {
                    $aux2 = Questionaris::where('idQuestionaris', $preguntes[$z]->idQuestionaris)->first();
                    if(!in_array($aux2, $questionaris))
                        $questionaris[] = $aux2;
                }
                return Inertia::render('Inici', [
                    'questionaris' => $questionaris, 'respostes' => $respostes, 'preguntes' => $preguntes]);
            }
        }
        else {
            return Inertia::render('Welcome', ['canLogin' => Route::has('login'),
                'canRegister' => Route::has('register'),
                'laravelVersion' => Application::VERSION,
                'phpVersion' => PHP_VERSION]);
        }
    }

    public function sensePermisos() {
        return Inertia::render('SensePermisos');
    }

    //Funcions pels rols
    public function getAllRols(){
        $tots = Rols::all();
        return Inertia::render('LlistarRols',['rols' => $tots, 'rolsFull' => $tots]);
    }

    public function getRolToEdit(Request $request)
    {
        $rol=Rols::where('nom', $request->nom)->first();
        return Inertia::render('EditarRol',['rol' => $rol]);
    }

    public function addRol(Request $request)
    {
        $rol = new Rols();
        $rol->nom = $request->nom;
        $rol->save();
        return redirect()->route("getRols");
    }

    public function deleteRol(Request $request)
    {
        Rols::destroy($request->idRols);
        return redirect()->route("getRols");
    }

    public function updateRol(Request $request){
        $rol = Rols::find($request->idRol);
        $rol->nom = $request->nom;
        $rol->save();
        return redirect()->route("getRols");

    }

    //Funcions pels qüestionaris
    public function getQuestionari(){
        $tots = Questionaris::all();
        return Inertia::render('LlistarQuestionaris',['questionaris' => $tots]);
    }

    public function addQuestionari(Request $request)
    {
        $questionari = new Questionaris();
        $questionari->idEmpresa = $request->idEmpresa;
        $questionari->descripcio= $request->descripcio;
        $questionari->save();
        return redirect()->route("getQuestionaris");
    }

    public function deleteQuestionari(Request $request)
    {
        Questionaris::destroy($request->idQuestionaris);
        return redirect()->route("getQuestionaris");
    }

    public function editQuestionari(Request $request) {
        $quest = Questionaris::where('idQuestionaris', $request->idQuestionaris)->first();
        return Inertia::render('EditarQuestionari',['questionari' => $quest]);
    }

    public function updateQuestionari(Request $request){
        $quest=Questionaris::find($request->idQuestionari);
        $quest->idEmpresa = $request->idEmpresa;
        $quest->descripcio = $request->descripcio;
        $quest->save();
        return redirect()->route("getQuestionaris");
    }

    //Preguntes
    public function getPreguntes(){
        $tots = Preguntes::all();
        return Inertia::render('LlistarPreguntes',['preguntes' => $tots]);
    }

    public function getQuestionarisInPreguntes()
    {
        $questionaris = Questionaris::all();
        return Inertia::render("AfegirPregunta",['questionaris' => $questionaris]);
    }

    public function addPregunta(Request $request)
    {
        $pregunta = new Preguntes();
        $pregunta->descripcio = $request->descripcio;
        $questionari = Questionaris::find($request->selectedOption);
        $pregunta->idQuestionaris = $questionari->idQuestionaris;
        $pregunta->save();
        return redirect()->route("getPreguntes");

    }

    public function deletePregunta(Request $request)
    {
        Preguntes::destroy($request->idPregunta);
        return redirect()->route("getPreguntes");
    }

    public function editPregunta(Request $request) {
        $pregunta = Preguntes::where('idPreguntes', $request->idPregunta)->first();
        $questionaris = Questionaris::all();
        return Inertia::render('EditarPregunta',['pregunta' => $pregunta,  'questionaris' => $questionaris]);
    }

    public function updatePregunta(Request $request){
        $pregunta = Preguntes::find($request->idPreguntes);
        $pregunta->descripcio = $request->descripcio;
        $questionari = Questionaris::find($request->selectedOption);
        $pregunta->idQuestionaris = $questionari->idQuestionaris;
        $pregunta->save();
        return redirect()->route("getPreguntes");
    }

    public function getQuestionariAlumne() {
        $alumnes = Alumnes::all();
        $questionaris = Questionaris::all();
        //Subselect per a mostrar els usuaris segons el rol.
        $professor = User::addSelect(['idRol' => Rols::select('idRols')->whereColumn('idRols', 'users.idRols')])->get();
        return Inertia::render('AfegirQuestionariPregunta', ['alumnes' => $alumnes, 'questionaris' => $questionaris, 'professors' => $professor]);
    }

    public function addQuestionariAlumne(Request $request)
    {
        $preguntes = Preguntes::where('idQuestionaris', $request->idQuestionari)->get();
        foreach($preguntes as $pregunta) {
            $resposta = new Respostes();
            $resposta->idAlumne = $request->idAlumne;
            $resposta->idPregunta = $pregunta->idPreguntes;
            $resposta->idProfessor = $request->idProfessor;
            if(Respostes::where('idPregunta', $resposta->idPregunta)->where('idAlumne', $resposta->idAlumne)->where('idProfessor', $resposta->idProfessor)->exists()) {
                return Inertia::render("Error", ['missatge' => "Les respostes ja existeixen!"]);
            }
            else
                $resposta->save();
        }
        return redirect()->route("QuestionariAlumne");
    }

    public function sendQuestionari(Request $request) {
        $idsRespostes = $request->idRespostes;
        for ($i = 0; $i < count($idsRespostes); $i++) {
            $dades = $idsRespostes[$i];
            $resposta = Respostes::where("idRespostes", $dades)->first();
            $resposta->valoracio = $request->valorNumeric[$i];
            $resposta->save();
        }
        return redirect()->route("QuestionariAlumne");
    }

    //Alumnes
    public function getAlumnes(){
        $tots = Alumnes::all();
        return Inertia::render('LlistarAlumnes',['alumnes' => $tots]);
    }

    public function addAlumne(Request $request)
    {
        $alumne = new Alumnes();
        $alumne->nomComplet = $request->nomComplet;
        $alumne->questionari= $request->questionari;
        if($request->img != null) {
            $fileName=time() .'_img.'. $request->img->extension();
            $request->img->move(public_path('uploads'), $fileName);
            $alumne->img = $fileName;
        }
        else
            $alumne->img = null;
        $alumne->save();
        return redirect()->route("getAlumnes");
    }

    public function deleteAlumne(Request $request)
    {
        Alumnes::destroy($request->idAlumne);
        return redirect()->route("getAlumnes");
    }

    public function editAlumne(Request $request) {
        $quest = Alumnes::where('idAlumnes', $request->idAlumne)->first();
        $quest->questionari = $request->questionari;
        return Inertia::render('EditarAlumne',['alumnes' => $quest]);
    }

    public function updateAlumne(Request $request){
        $quest = Alumnes::find($request->idAlumne);
        $quest->nomComplet = $request->nomComplet;
        $quest->questionari = $request->questionari;
        if($request->img != null) {
            $fileName=time() .'_img.'. $request->img->extension();
            $request->img->move(public_path('uploads'), $fileName);
            $quest->img = $fileName;
        }
        $quest->save();
        return redirect()->route("getAlumnes");
    }

    public function respondrePregunta()
    {
        $alumnes = Alumnes::all();
        $questionaris = Questionaris::all();
        //Subselect per a mostrar els usuaris segons el rol.
        $professor = User::addSelect(['idRol' => Rols::select('idRols')->whereColumn('idRols', 'users.idRols')])->get();
        return Inertia::render('RespondrePregunta', ['alumnes' => $alumnes, 'questionaris' => $questionaris, 'professors' => $professor]);
    }

    public function seleccionarAlumneRespondre(Request $request)
    {
        $preguntes = Preguntes::where('idQuestionaris', $request->idQuestionari)->get();
        $respostes = [];
        foreach($preguntes as $pregunta) {
            $respostes[] = Respostes::where('idAlumne', $request->idAlumne)->where('idProfessor', $request->idProfessor)->where('idPregunta', $pregunta->idPreguntes)->where('tancat', false)->get();
        }
        if(count($respostes) > 0)
            return Inertia::render('QuestionariAlumne', ["respostes" => $respostes, "preguntes" => $preguntes]);
        else
            return redirect()->route("QuestionariAlumne");
    }

    public function tancarResposta()
    {
        $alumnes = Alumnes::all();
        $questionaris = Questionaris::all();
        //Subselect per a mostrar els usuaris segons el rol.
        $professor = User::addSelect(['idRol' => Rols::select('idRols')->whereColumn('idRols', 'users.idRols')])->get();
        return Inertia::render('TancarRespostes', ['alumnes' => $alumnes, 'questionaris' => $questionaris, 'professors' => $professor]);
    }

    public function tancarQuestionari(Request $request)
    {
        $preguntes = Preguntes::where('idQuestionaris', $request->idQuestionari)->get();
        if(!empty($preguntes)) {
            foreach($preguntes as $pregunta) {
                $aux = Respostes::where('idAlumne', $request->idAlumne)->where('idProfessor', $request->idProfessor)->where('idPregunta', $pregunta->idPreguntes)->where('tancat', false)->first();
                $aux->tancat = true;
                $aux->save();
            }
        }
        return redirect()->route("TancarRespostaAlumne");
    }

    public function redirigirQuestionariTancat()
    {
        return redirect()->route("dashboard");
    }

    public function filtrarRols(Request $request)
    {
        $rols = Rols::where('idRols', $request->selectedOption)->get();
        $rolsFull = Rols::all();
        return Inertia::render('LlistarRols',['rols' => $rols, 'rolsFull' => $rolsFull]);
    }

    public function error() {
        return Inertia::render('Error');
    }
}
