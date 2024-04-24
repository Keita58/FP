import { Vehicle } from "./Vehicle.js";
export class Patinet extends Vehicle {
    get potencia() {
        return this._potencia;
    }
    set potencia(value) {
        this._potencia = value;
    }
    get factor() {
        return this._factor;
    }
    set factor(value) {
        this._factor = value;
    }
    constructor(nom, model, contamina, potencia) {
        super(nom, model, contamina);
        this._potencia = potencia;
        this._factor = Math.floor(Math.random() * 10 + 1);
        this._preuClient = 0;
    }
    toString() {
        document.body.innerHTML += "<p> Nom: " + this.nom + ", Model: " + this.model + ", Contamina: " + this.contamina
            + ", Potència: " + this._potencia + ", Factor: " + this._factor + " i Preu client: " + this.getPreuClient() + "<br>";
    }
    getPreuClient() {
        this._preuClient = this._factor * this._potencia;
        return this._preuClient;
    }
}
