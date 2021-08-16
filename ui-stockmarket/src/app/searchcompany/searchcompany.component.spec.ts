import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchcompanyComponent } from './searchcompany.component';

describe('SearchcompanyComponent', () => {
  let component: SearchcompanyComponent;
  let fixture: ComponentFixture<SearchcompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchcompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchcompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
