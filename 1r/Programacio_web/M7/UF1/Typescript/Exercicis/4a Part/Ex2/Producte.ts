
export class Producte {
    public _name : string;
    public _price : number;
    public _type : string;

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get price(): number {
        return this._price;
    }

    set price(value: number) {
        this._price = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
    }

    constructor(name: string, price: number, type: string) {
        this._name = name;
        this._price = price;
        this._type = type;
    }
}