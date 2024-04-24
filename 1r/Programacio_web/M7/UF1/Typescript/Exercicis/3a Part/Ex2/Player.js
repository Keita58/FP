export class Player {
    get name() {
        return this._name;
    }
    set name(value) {
        this._name = value;
    }
    get points() {
        return this._points;
    }
    set points(value) {
        this._points = value;
    }
    get type() {
        return this._type;
    }
    set type(value) {
        this._type = value;
    }
    constructor(nom, punts, tipus) {
        this._name = nom;
        this._points = punts;
        this._type = tipus;
    }
    to_string() {
        console.log("Nom: " + this._name + ", Punts: " + this._points + ", Tipus: " + this._type);
    }
}
