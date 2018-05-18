import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailphotosComponent } from './detailphotos.component';

describe('DetailphotosComponent', () => {
  let component: DetailphotosComponent;
  let fixture: ComponentFixture<DetailphotosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailphotosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailphotosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
