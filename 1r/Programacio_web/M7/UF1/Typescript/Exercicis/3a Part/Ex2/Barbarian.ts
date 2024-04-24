import {Player} from "./Player.js";

export class Barbarian extends Player{
    private _weapon : string;
    private _rage : string;

    get weapon(): string {
        return this._weapon;
    }

    set weapon(value: string) {
        this._weapon = value;
    }

    get rage(): string {
        return this._rage;
    }

    set rage(value: string) {
        this._rage = value;
    }

    constructor(nom : string, punts : number, tipus : string, arma : string, rabia : string) {
        super(nom, punts, tipus);
        this._weapon = arma;
        this._rage = rabia;
    }

    to_string() {
        console.log("Nom: " + this._name + ", Punts: " + this._points + ", Arma: " + this._weapon + ", Ràbia: " + this._rage + ", Tipus: " + this._type);
    }
}