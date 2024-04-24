import {Vehicle} from "./Vehicle.js";

export class Patinet extends Vehicle {
    private _potencia : number;
    private _factor : number;
    private _preuClient : number;

    get potencia(): number {
        return this._potencia;
    }

    set potencia(value: number) {
        this._potencia = value;
    }

    get factor(): number {
        return this._factor;
    }

    set factor(value: number) {
        this._factor = value;
    }


    constructor(nom: string, model: string, contamina: boolean, potencia: number) {
        super(nom, model, contamina);
        this._potencia = potencia;
        this._factor = Math.floor(Math.random() * 10 + 1);
        this._preuClient = 0;
    }

    toString() {
        document.body.innerHTML += "<p> Nom: " + this.nom + ", Model: " + this.model + ", Contamina: " + this.contamina
        + ", Potència: " + this._potencia + ", Factor: " + this._factor + " i Preu client: " + this.getPreuClient() + "<br>";
    }

    public getPreuClient() {
        this._preuClient = this._factor * this._potencia;
        return this._preuClient;
    }
}