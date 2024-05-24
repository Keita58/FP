import { Component } from '@angular/core';
// @ts-ignore
import Suma from 'suma-proves';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-suma',
  templateUrl: './suma.component.html',
  styleUrl: './suma.component.css'
})
export class SumaComponent {
  resultat! : string;

  constructor(private formBuilder : FormBuilder) {}

  formulari : FormGroup = this.formBuilder.group({
    num1: [0, [Validators.min(1), Validators.max(1000000), Validators.required]],
    num2: [0, [Validators.min(1), Validators.max(1000000), Validators.required]]
  });

  Calcular() : void {
    this.resultat = Suma(this.formulari.controls['num1'].value, this.formulari.controls['num2'].value);
  }
}
