import {Directive, Input} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, ValidatorFn} from "@angular/forms";

@Directive({
  selector: '[appValidacio]',
  providers: [{provide: NG_VALIDATORS,
  useExisting: ValidacioDirective, multi : true}]
})
export class ValidacioDirective {
  @Input('appName') passwordStrength : boolean = false;
  validate(control : AbstractControl) : ValidationErrors | null {
    return this.passwordStrength ? passwordValidator()(control) : null;
  }

}

export function passwordValidator() : ValidatorFn {
  return (control : AbstractControl) : ValidationErrors | null => {
    const value = control.value;
    if(!value)
      return null;
    const hasUppperCase : boolean = /[A-Z]+/.test(value); //Comprova si les lletres del text hi ha majúscules
    const hasLowerCase : boolean = /[a-z]+/.test(value); //Comprova si les lletres del text hi ha minúscules
    const hasNumeric : boolean = /[0-9]+/.test(value); //Comprova si dis del text hi ha números
    const passwordValid : boolean = hasNumeric && hasLowerCase && hasUppperCase; //Si totes les comprovacions anteriors han donat true és true, sinó false
    return !passwordValid ? {passwordStrength : true} : null;
  }
}
