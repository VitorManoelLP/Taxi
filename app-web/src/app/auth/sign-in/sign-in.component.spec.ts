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
    expect(component.form.get('email')?.errors).toEqual({ email: 'Invalid e-mail' });
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

  test.each(invalidPasswordLength)('should set error password when input like %p not has at least 8 length', (input) => {

    component.form.patchValue({
      'password': input
    });

    expect(component.form.invalid).toBeTruthy();
    expect(component.form.get('password')?.errors?.['password-length']).toEqual('Password must have a length greater than 8');
  });

  test('should set error password when input like %p not has at least once letter', () => {

    component.form.patchValue({
      'password': '12345678'
    });

    expect(component.form.invalid).toBeTruthy();
    expect(component.form.get('password')?.errors?.['password-minimum-letter']).toEqual('Password must have at least once letter');
  });

  test('should set error password when input like %p not has at least once capital letter', () => {

    component.form.patchValue({
      'password': '12345678teste'
    });

    expect(component.form.invalid).toBeTruthy();
    expect(component.form.get('password')?.errors?.['password-capital-letter']).toEqual('Password must have at least once capital letter');
  });

  test('should set error password when input like %p not has at least once lowercase letter', () => {

    component.form.patchValue({
      'password': '12345678TESTE'
    });

    expect(component.form.invalid).toBeTruthy();
    expect(component.form.get('password')?.errors?.['password-lowercase-letter']).toEqual('Password must have at least once lowercase letter');
  });

  test('should set error password when input like %p not has at least once symbols', () => {

    component.form.patchValue({
      'password': 'aaaaaaaA'
    });

    expect(component.form.invalid).toBeTruthy();
    expect(component.form.get('password')?.errors?.['password-number']).toEqual('Password must have at least once number');
  });

  test('should not set error password when it has a right format', () => {
    component.form.patchValue({
      'password': '123456789Te'
    });
    expect(component.form.get('password')?.errors).toBeNull();
  });

});
