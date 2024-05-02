import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ConnectDBService {

    REST_API : string = 'http://localhost:3000';
    constructor(private httpclient : HttpClient) { }

    public getAllSongs() : Observable<any> {
        return this.httpclient.get(`${this.REST_API}/songs`);
    }

    public getSongsRecRep(form : FormGroup) : Observable<any> {
        return this.httpclient.get(`${this.REST_API}/song`, {
            params: {
                reproduccions: form.controls['reproduccions'].value,
                recaptacio: form.controls['recaptacio'].value
            }
        });
    }

    public setSong(form : FormGroup) : Observable<any>{
        return this.httpclient.post(`${this.REST_API}/songs/insert`, form.value);
    }
}
