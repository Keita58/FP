import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-ex3',
  templateUrl: './ex3.component.html',
  styleUrl: './ex3.component.css'
})
export class Ex3Component {

  constructor(private form : FormBuilder) {}

  formulari : FormGroup = this.form.group ({
    text : new FormControl('', [Validators.required])
  })
}
