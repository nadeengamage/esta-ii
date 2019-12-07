import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignerHistoryComponent } from './assigner-history.component';

describe('AssignerHistoryComponent', () => {
  let component: AssignerHistoryComponent;
  let fixture: ComponentFixture<AssignerHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignerHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignerHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
