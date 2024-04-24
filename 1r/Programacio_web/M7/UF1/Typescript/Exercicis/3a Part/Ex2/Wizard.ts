import {Player} from "./Player.js";

export class Wizard extends Player{
    private _conjuration : string;
    private _arcane : string;

    get conjuration(): string {
        return this._conjuration;
    }

    set conjuration(value: string) {
        this._conjuration = value;
    }

    get arcane(): string {
        return this._arcane;
    }

    set arcane(value: string) {
        this._arcane = value;
    }

    constructor(nom : string, punts : number, tipus : string, conjuracio : string, arcana : string) {
        super(nom, punts, tipus);
        this._conjuration = conjuracio;
        this._arcane = arcana;
    }

    to_string() {
        console.log("Nom: " + this._name + ", Punts: " + this._points + ", Conjuració: " + this._conjuration + ", Tradició arcana: " + this._arcane + ", Tipus: " + this._type);
    }
}