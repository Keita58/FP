export class Users {
  idUser! : Number;
  private _nom! : string;
  private _password! : string;
  private _punts! : number;

  get nom(): string {
    return this._nom;
  }

  get password(): string {
    return this._password;
  }

  get punts(): number {
    return this._punts;
  }

  set punts(value: number) {
    this._punts = value;
  }

  constructor(Nom: string, Password: string, Punts: number) {
    this._nom = Nom;
    this._password = Password;
    this._punts = Punts;
  }
}
