export class Animes {
    private _titol : string;
    private _creador : string;
    private _anyCreacio : number;

    get titol(): string {
        return this._titol;
    }

    set titol(value: string) {
        this._titol = value;
    }

    get creador(): string {
        return this._creador;
    }

    set creador(value: string) {
        this._creador = value;
    }

    get any(): number {
        return this._anyCreacio;
    }

    set any(value: number) {
        this._anyCreacio = value;
    }

    constructor(titol : string, creador : string, any : number) {
        this._titol = titol;
        this._creador = creador;
        this._anyCreacio = any;
    }

    to_string() {
        console.log("Títol: " + this._titol + ", Creador/a: " + this._creador + ", Any de creació: " + this._anyCreacio);
    }
}