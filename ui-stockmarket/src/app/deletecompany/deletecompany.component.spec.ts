import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletecompanyComponent } from './deletecompany.component';

describe('DeletecompanyComponent', () => {
  let component: DeletecompanyComponent;
  let fixture: ComponentFixture<DeletecompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletecompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletecompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
