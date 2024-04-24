export class Animes {
    get titol() {
        return this._titol;
    }
    set titol(value) {
        this._titol = value;
    }
    get creador() {
        return this._creador;
    }
    set creador(value) {
        this._creador = value;
    }
    get any() {
        return this._anyCreacio;
    }
    set any(value) {
        this._anyCreacio = value;
    }
    constructor(titol, creador, any) {
        this._titol = titol;
        this._creador = creador;
        this._anyCreacio = any;
    }
    to_string() {
        console.log("Títol: " + this._titol + ", Creador/a: " + this._creador + ", Any de creació: " + this._anyCreacio);
    }
}
