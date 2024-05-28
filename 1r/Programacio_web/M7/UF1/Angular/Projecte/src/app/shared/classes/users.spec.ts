import { Users } from './users';

describe('Users', () => {
  it('should create an instance', () => {
    expect(new Users('marc@ies-sabadell.cat', 'tetris_123', 0)).toBeTruthy();
  });
});
