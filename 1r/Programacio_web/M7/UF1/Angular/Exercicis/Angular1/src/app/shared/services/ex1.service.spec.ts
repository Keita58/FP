import { TestBed } from '@angular/core/testing';

import { Ex1Service } from './ex1.service';

describe('Ex1Service', () => {
  let service: Ex1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Ex1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
