import { Player } from "./Player.js";
export class Wizard extends Player {
    get conjuration() {
        return this._conjuration;
    }
    set conjuration(value) {
        this._conjuration = value;
    }
    get arcane() {
        return this._arcane;
    }
    set arcane(value) {
        this._arcane = value;
    }
    constructor(nom, punts, tipus, conjuracio, arcana) {
        super(nom, punts, tipus);
        this._conjuration = conjuracio;
        this._arcane = arcana;
    }
    to_string() {
        console.log("Nom: " + this._name + ", Punts: " + this._points + ", Conjuració: " + this._conjuration + ", Tradició arcana: " + this._arcane + ", Tipus: " + this._type);
    }
}
