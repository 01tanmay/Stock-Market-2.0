import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowcompanydetailsComponent } from './showcompanydetails.component';

describe('ShowcompanydetailsComponent', () => {
  let component: ShowcompanydetailsComponent;
  let fixture: ComponentFixture<ShowcompanydetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowcompanydetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowcompanydetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
