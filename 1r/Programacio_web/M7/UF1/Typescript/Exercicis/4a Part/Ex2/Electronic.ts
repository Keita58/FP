import {Producte} from "./Producte";

export class Electronic extends Producte {
    public _power : number;
    public _model : string;

    get power(): number {
        return this._power;
    }

    set power(value: number) {
        this._power = value;
    }

    get model(): string {
        return this._model;
    }

    set model(value: string) {
        this._model = value;
    }

    constructor(name: string, price: number, type: string, power: number, model: string) {
        super(name, price, type);
        this._power = power;
        this._model = model;
    }

    toString() {
        console.log("Nom: " + this._name + ", Preu: " + this._price + ", Potència: " + this._power + ", Model: " + this._model + ", Tipus: " + this._type);
    }
}