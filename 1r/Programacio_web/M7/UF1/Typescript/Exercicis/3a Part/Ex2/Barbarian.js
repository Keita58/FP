import { Player } from "./Player.js";
export class Barbarian extends Player {
    get weapon() {
        return this._weapon;
    }
    set weapon(value) {
        this._weapon = value;
    }
    get rage() {
        return this._rage;
    }
    set rage(value) {
        this._rage = value;
    }
    constructor(nom, punts, tipus, arma, rabia) {
        super(nom, punts, tipus);
        this._weapon = arma;
        this._rage = rabia;
    }
    to_string() {
        console.log("Nom: " + this._name + ", Punts: " + this._points + ", Arma: " + this._weapon + ", Ràbia: " + this._rage + ", Tipus: " + this._type);
    }
}
