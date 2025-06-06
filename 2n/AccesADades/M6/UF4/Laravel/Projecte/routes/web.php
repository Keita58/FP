<?php

use App\Http\Controllers\MainControlador;
use App\Http\Controllers\ProfileController;
use Illuminate\Support\Facades\Route;
use Inertia\Inertia;

Route::get('/', [MainControlador::class, "ControladorInici"],)->name('Inici');

Route::get('/dashboard', function () {
    return Inertia::render('Dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

Route::get('/fitxa', [ProfileController::class, 'edit'])->name('profile.edit');

Route::get("/sensePermisos", [MainControlador::class, "sensePermisos"])->name("sensePermisos");
Route::get("/error", [MainControlador::class, "error"])->name("error");

//ROLS
Route::get("/rols", [MainControlador::class, "getAllRols"])->middleware("permisos")->name("getRols");
Route::get("/rols/add", function () {
    return Inertia::render('AfegirRol');
})->middleware("permisos")->name("afegirRol");
Route::post("AddRols", [MainControlador::class, "addRol"])->middleware("permisos")->name("addRol");
Route::get("/rols/edit", [MainControlador::class, "getRolToEdit"])->middleware("permisos")->name("editRol");
Route::post("rols/borrar", [MainControlador::class, "deleteRol"])->middleware("permisos")->name("deleteRol");
Route::post("EditRol", [MainControlador::class, "updateRol"])->middleware("permisos")->name("updateRol");

//QÜESTIONARIS
Route::get("/questionari", [MainControlador::class, "getQuestionari"])->middleware("permisos")->name("getQuestionaris");
Route::get("/questionari/add", function () {
    return Inertia::render('AfegirQuestionari');
})->middleware("permisos")->name("AfegirQuestionari");
Route::post("AfegirQuestionari",[MainControlador::class, "addQuestionari"])->middleware("permisos")->name("addQuestionari");
Route::post("questionari/borrar", [MainControlador::class, "deleteQuestionari"])->middleware("permisos")->name("deleteQuest");
Route::get("/questionari/edit", [MainControlador::class, "editQuestionari"])->middleware("permisos")->name("editQuest");
Route::post("EditQuestionari", [MainControlador::class, "updateQuestionari"])->middleware("permisos")->name("updateQuest");
Route::post("RespostesQuestionari", [MainControlador::class, 'sendQuestionari'])->middleware("permisos")->name("sendQuestionari");

//PREGUNTES
Route::get("/pregunta", [MainControlador::class, 'getPreguntes'])->name('getPreguntes');
Route::get("/pregunta/add",[MainControlador::class, "getQuestionarisInPreguntes"])->middleware("permisos")->name("AfegirPregunta");
Route::post("AfegirPregunta", [MainControlador::class, "addPregunta"])->middleware("permisos")->name("addPregunta");
Route::post("pregunta/borrar", [MainControlador::class, "deletePregunta"])->middleware("permisos")->name("deletePregunta");
Route::get("/pregunta/edit", [MainControlador::class, "editPregunta"])->middleware("permisos")->name("editPregunta");
Route::post("EditPregunta", [MainControlador::class, "updatePregunta"])->middleware("permisos")->name("updatePregunta");
Route::get("/pregunta/alumne",[MainControlador::class, "getQuestionariAlumne"])->middleware("permisos")->name("QuestionariAlumne");
Route::post("AddQuestionariAlumne", [MainControlador::class, "addQuestionariAlumne"])->middleware("permisos")->name("addQuestionariAlumne");

//ALUMNES
Route::get("/alumnes", [MainControlador::class, 'getAlumnes'])->name('getAlumnes');
Route::get("/alumne/add", function () {
    return Inertia::render('AfegirAlumne');
})->middleware("permisos")->name("AfegirAlumne");
Route::post("AfegirAlumne", [MainControlador::class, "addAlumne"])->name("addAlumne");
Route::post("alumne/borrar", [MainControlador::class, "deleteAlumne"])->name("deleteAlumne");
Route::get("/alumne/edit", [MainControlador::class, "editAlumne"])->name("editAlumne");
Route::post("EditarAlumne", [MainControlador::class, "updateAlumne"])->name("updateAlumne");
Route::get("/alumne/resposta",[MainControlador::class, "respondrePregunta"])->name("RespostaAlumne");
Route::post("SeleccionarAlumne", [MainControlador::class, "seleccionarAlumneRespondre"])->name("seleccionarAlumneRespondre");
Route::get("/alumne/questionari/tancar",[MainControlador::class, "tancarResposta"])->middleware("permisos")->name("TancarRespostaAlumne");
Route::post("TancarQuestionari", [MainControlador::class, "tancarQuestionari"])->middleware("permisos")->name("TancarQuestionari");
Route::get("/questionari/tancat", function () {
    return Inertia::render('QuestionariTancat');
})->name("QuestionariTancat");
Route::post("QuestionariTancat", [MainControlador::class, "redirigirQuestionariTancat"])->name("RedirigirQuestionariTancat");

Route::post("FiltrarRols",[MainControlador::class, "filtrarRols"])->name("FiltrarRols");

require __DIR__.'/auth.php';
