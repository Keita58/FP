import {Coord} from "./Coord.js";

export class Rol {
    private _nom : string;
    private _historia : string;
    private _posicio : Coord;

    get nom() : string{
        return this._nom;
    }

    set nom(nom : string) {
        this._nom = nom;
    }

    get historia() : string{
        return this._historia;
    }

    set historia(historia : string) {
        this._historia = historia;
    }

    get posicio() : Coord{
        return this._posicio;
    }

    set posicio(posicio : Coord) {
        this._posicio = posicio;
    }

    constructor(nom : string, historia : string, posicio : Coord) {
        this._nom = nom;
        this._historia = historia;
        this._posicio = posicio;
    }

    toString() {
        console.log("Nom: " + this._nom + ", Història: " + this._historia + ", Posicio: (x=" + this._posicio.x + ",y=" + this._posicio.y + ").");
    }
}