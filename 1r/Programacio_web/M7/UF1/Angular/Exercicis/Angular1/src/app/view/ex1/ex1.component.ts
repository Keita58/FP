import {Component} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {Ex1Service} from "../../shared/services/ex1.service";

@Component({
  selector: 'app-ex1',
  templateUrl: './ex1.component.html',
  styleUrl: './ex1.component.css'
})
export class Ex1Component {

  constructor(private daus:Ex1Service) {}

  found:boolean=false;
  dada:string='';
  data : FormControl = new FormControl('', [Validators.required, Validators.min(1), Validators.max(6)]);

  Calcular():void{
    this.found = false;
    if(this.data.value <= 6 || this.data.value >= 1){
      let nums:number[] = this.daus.Daus();
      for(let i:number = 0; i < nums.length; i++) {
        if(nums[i] == this.data.value) {
          this.dada = "Els nombres dels daus són: " + nums[0] + ", " + nums[1] + " i " + nums[2] + ". El nombre s'ha encertat!";
          this.found = true;
        }
      }
      if(!this.found)
        this.dada = "Els nombres dels daus són: " + nums[0] + ", " + nums[1] + " i " + nums[2] + ". El nombre no s'ha encertat...";
    }
  }
}
