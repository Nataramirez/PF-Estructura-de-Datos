import { TestBed } from '@angular/core/testing';

import { SearchBinaryTreeService } from './search-binary-tree.service';

describe('SearchBinaryTreeService', () => {
  let service: SearchBinaryTreeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchBinaryTreeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
