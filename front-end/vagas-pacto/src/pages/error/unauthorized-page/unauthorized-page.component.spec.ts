import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthorizadePageComponent } from './unauthorized-page.component';

describe('UnauthorizadePageComponent', () => {
  let component: UnauthorizadePageComponent;
  let fixture: ComponentFixture<UnauthorizadePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UnauthorizadePageComponent]
    });
    fixture = TestBed.createComponent(UnauthorizadePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
