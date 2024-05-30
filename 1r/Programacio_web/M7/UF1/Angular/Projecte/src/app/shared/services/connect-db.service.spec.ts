import { TestBed } from '@angular/core/testing';

import { ConnectDBService } from './connect-db.service';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
//import {ObjectId} from "mongodb";

describe('ConnectDBService', () => {
  let service: ConnectDBService;
  let httpClientSpy : jasmine.SpyObj<HttpClient>;
  let httpClient : HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get','post']);
    service = new ConnectDBService(httpClientSpy);
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [ ConnectDBService ]
    });
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(ConnectDBService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Comprova que la URL sigui correcte - getAllUsers', () => {
    service.getAllUsers().subscribe({
      next: Users => {},
      error: fail
    });
    const req = httpTestingController.expectOne(service.REST_API + "/users");
    expect(req.request.method).toEqual('GET');
  });

  it('Comprova que la URL sigui correcte - getUser', () => {
    let loginForm : FormGroup = new FormGroup({
      nom : new FormControl('marc@ies-sabadell.cat'),
      password : new FormControl('tetris_123')
    })
    service.getUser(loginForm).subscribe({
      next: Users => {},
      error: fail
    });
    const req = httpTestingController.expectOne(service.REST_API + "/user?nom=marc@ies-sabadell.cat&password=tetris_123");
    expect(req.request.method).toEqual('GET');
  });

  it('Comprova que la URL sigui correcte - registerUser', () => {
    let loginForm : FormGroup = new FormGroup({
      nom : new FormControl('MMarc@ies-sabadell.cat'),
      password : new FormControl('Tetris_123')
    })
    service.registerUser(loginForm).subscribe({
      next: Users => {},
      error: fail
    });
    const req = httpTestingController.expectOne(service.REST_API + "/users/insert");
    expect(req.request.method).toEqual('POST');
  });

  it('Comprova que la URL sigui correcte - updateUser', () => {
    let loginForm : FormGroup = new FormGroup({
      nom : new FormControl('MMarc@ies-sabadell.cat'),
      password : new FormControl('Tetris_123'),
      punts : new FormControl(12)
    })
    service.updateUser(loginForm).subscribe({
      next: Users => {},
      error: fail
    });
    const req = httpTestingController.expectOne(service.REST_API + "/users/update");
    expect(req.request.method).toEqual('POST');
  });
});
