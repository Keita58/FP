import { TestBed } from '@angular/core/testing';

import { JugadorsServiceService } from './jugadors-service.service';

describe('JugadorsServiceService', () => {
  let service: JugadorsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JugadorsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
