import {Directive, Input} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, ValidatorFn} from "@angular/forms";

@Directive({
  selector: '[appValidacio]',
  providers: [{provide: NG_VALIDATORS,
  useExisting: ValidacioDirective, multi : true}]
})
export class ValidacioDirective {
  @Input('appName') passwordStrength : boolean = false;
  @Input('appName') emailStrength : boolean = false;
  validate(control : AbstractControl) : ValidationErrors | null {
    return this.passwordStrength ? passwordValidator()(control) : null;
  }

  validate2(control : AbstractControl) : ValidationErrors | null {
    return this.emailStrength ? emailValidator()(control) : null;
  }

}

export function passwordValidator() : ValidatorFn {
  return (control : AbstractControl) : ValidationErrors | null => {
    const value = control.value;
    if(!value)
      return null;
    const hasUppperCase : boolean = /[A-Z]+/.test(value); //Comprova si les lletres del text hi ha majúscules
    const hasLowerCase : boolean = /[a-z]+/.test(value); //Comprova si les lletres del text hi ha minúscules
    const hasNumeric : boolean = /[0-9]+/.test(value); //Comprova si dins del text hi ha números
    const specialCharacters : boolean = /[#@]/.test(value); //Comprova que no tingui ni # ni @
    const passwordValid : boolean = hasNumeric && hasLowerCase && hasUppperCase && !specialCharacters; //Si totes les comprovacions anteriors han donat true és true, sinó false
    return !passwordValid ? {passwordStrength : true} : null;
  }
}

export function emailValidator() : ValidatorFn {
  return (control : AbstractControl) : ValidationErrors | null => {
    const value = control.value;
    if(!value)
      return null;
    const hasUppperCase : boolean = /[A-Z]+/.test(value); //Comprova si les lletres del text hi ha majúscules
    const hasLowerCase : boolean = /[a-z]+/.test(value); //Comprova si les lletres del text hi ha minúscules
    const isEmailSab : boolean = /[a-zA-Z]+@ies-sabadell.cat$/.test(value); //Comprova que acaba amb el correu de l'institut
    const emailValid : boolean = isEmailSab && hasLowerCase && hasUppperCase; //Si totes les comprovacions anteriors han donat true és true, sinó false
    return !emailValid ? {emailStrength : true} : null;
  }
}
