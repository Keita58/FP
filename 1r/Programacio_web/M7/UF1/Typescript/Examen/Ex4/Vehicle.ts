
export class Vehicle {
    private _nom : string;
    private _model : string;
    private _contamina : boolean;

    get nom(): string {
        return this._nom;
    }

    set nom(value: string) {
        this._nom = value;
    }

    get model(): string {
        return this._model;
    }

    set model(value: string) {
        this._model = value;
    }

    get contamina(): boolean {
        return this._contamina;
    }

    set contamina(value: boolean) {
        this._contamina = value;
    }


    constructor(nom: string, model: string, contamina: boolean) {
        this._nom = nom;
        this._model = model;
        this._contamina = contamina;
    }
}