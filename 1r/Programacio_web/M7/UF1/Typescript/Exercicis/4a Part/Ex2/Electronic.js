"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Electronic = void 0;
const Producte_1 = require("./Producte");
class Electronic extends Producte_1.Producte {
    get power() {
        return this._power;
    }
    set power(value) {
        this._power = value;
    }
    get model() {
        return this._model;
    }
    set model(value) {
        this._model = value;
    }
    constructor(name, price, type, power, model) {
        super(name, price, type);
        this._power = power;
        this._model = model;
    }
    toString() {
        console.log("Nom: " + this._name + ", Preu: " + this._price + ", Potència: " + this._power + ", Model: " + this._model + ", Tipus: " + this._type);
    }
}
exports.Electronic = Electronic;
