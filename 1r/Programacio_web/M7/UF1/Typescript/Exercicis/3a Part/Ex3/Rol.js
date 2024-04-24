export class Rol {
    get nom() {
        return this._nom;
    }
    set nom(nom) {
        this._nom = nom;
    }
    get historia() {
        return this._historia;
    }
    set historia(historia) {
        this._historia = historia;
    }
    get posicio() {
        return this._posicio;
    }
    set posicio(posicio) {
        this._posicio = posicio;
    }
    constructor(nom, historia, posicio) {
        this._nom = nom;
        this._historia = historia;
        this._posicio = posicio;
    }
    toString() {
        console.log("Nom: " + this._nom + ", Història: " + this._historia + ", Posicio: (x=" + this._posicio.x + ",y=" + this._posicio.y + ").");
    }
}
