export class Vehicle {
    get nom() {
        return this._nom;
    }
    set nom(value) {
        this._nom = value;
    }
    get model() {
        return this._model;
    }
    set model(value) {
        this._model = value;
    }
    get contamina() {
        return this._contamina;
    }
    set contamina(value) {
        this._contamina = value;
    }
    constructor(nom, model, contamina) {
        this._nom = nom;
        this._model = model;
        this._contamina = contamina;
    }
}
