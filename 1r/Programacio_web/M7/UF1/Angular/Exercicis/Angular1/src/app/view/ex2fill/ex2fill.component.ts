import {Component, EventEmitter, inject, Injectable, Input, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Volum} from "../../shared/classes/volum";


@Component({
  selector: 'app-ex2fill',
  templateUrl: './ex2fill.component.html',
  styleUrl: './ex2fill.component.css',
  providers: []
})

@Injectable()
export class Ex2fillComponent {

  @Input() resposta:string = '';
  @Input() color: boolean = false;
  @Output() envia : EventEmitter<Volum> = new EventEmitter<Volum>();

  constructor(private form : FormBuilder) {}

  formulari : FormGroup = this.form.group ({
    radi : new FormControl('', [Validators.required, Validators.min(1), Validators.max(1000)]),
    altura : new FormControl('', [Validators.required, Validators.min(1), Validators.max(1000)]),
    unitats : [""]
  })

  Calcular():void{
    this.envia.emit(new Volum(this.formulari.controls['radi'].value, this.formulari.controls['altura'].value, this.formulari.controls['unitats'].value));
  }
}
