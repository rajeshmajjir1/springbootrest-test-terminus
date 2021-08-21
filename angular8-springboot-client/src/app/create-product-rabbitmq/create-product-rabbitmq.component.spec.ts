import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateProductRabbitmqComponent } from './create-product-rabbitmq.component';

describe('CreateProductRabbitmqComponent', () => {
  let component: CreateProductRabbitmqComponent;
  let fixture: ComponentFixture<CreateProductRabbitmqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateProductRabbitmqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateProductRabbitmqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
