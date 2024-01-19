import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignInComponent } from './sign-in.component';

describe('SignInComponent', () => {
  let component: SignInComponent;
  let fixture: ComponentFixture<SignInComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignInComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  const invalidEmailInput = [
    'foo@gmail',
    'foo'
  ]

  test.each(invalidEmailInput)('should set error email when input as %p ', (input: string | null) => {

    component.form.patchValue({
      'email': input
    });

    expect(component.form.get('email')?.invalid).toBeTruthy();
    expect(component.form.get('email')?.errors).toEqual({ email: true });
  });

  const requiredInput = [
    '',
    null
  ];

  test.each(requiredInput)('should set required true', (input) => {

    component.form.patchValue({
      'email': input,
      'password': input
    });

    expect(component.form.get('email')?.invalid).toBeTruthy();
    expect(component.form.get('email')?.errors).toEqual({ required: true });

    expect(component.form.get('password')?.invalid).toBeTruthy();
    expect(component.form.get('password')?.errors).toEqual({ required: true });
  });

  test('should not set error when input as foo@gmail.com', () => {

    component.form.patchValue({
      'email': 'foo@gmail.com'
    });

    expect(component.form.get('email')?.invalid).toBeFalsy();
  });

  const invalidPasswordLength = [
    '1',
    '123',
    '1234',
    '12345',
    '123456',
    '1234567'
  ]

  test.each(invalidPasswordLength)('should set error password when input as %p length', (input) => {

    component.form.patchValue({
      'password': [input]
    });

    expect(component.form.invalid).toBeTruthy();
    expect(component.form.get('password')?.errors).toEqual({ 'password-length': 'Password must have a length greater than 8' });
  });

});
