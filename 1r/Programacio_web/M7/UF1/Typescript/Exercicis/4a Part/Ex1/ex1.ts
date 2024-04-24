
type Electronic = {
    name : string,
    price : number,
    power : number,
    model : string,
    type : string
};

type Roba = {
    name : string,
    price : number,
    size : string,
    color : string,
    type : string
};

let Producte : (Electronic | Roba)[] = [];
