import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrandPrixComponent } from './grand-prix.component';

describe('GrandPrixComponent', () => {
  let component: GrandPrixComponent;
  let fixture: ComponentFixture<GrandPrixComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GrandPrixComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GrandPrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
