export class Player {
    private _name : string;
    private _points : number;
    private _type : string;

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get points(): number {
        return this._points;
    }

    set points(value: number) {
        this._points = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
    }

    constructor(nom : string, punts : number, tipus : string) {
        this._name = nom;
        this._points = punts;
        this._type = tipus;
    }

    to_string() {
        console.log("Nom: " + this._name + ", Punts: " + this._points + ", Tipus: " + this._type);
    }
}