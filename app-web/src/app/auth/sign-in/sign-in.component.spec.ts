import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SignInComponent} from './sign-in.component';
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../service/auth.service";
import {HttpClientExtensionService} from "../../core/http-client-extension.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {CookieManager} from "../../core/cookie.service";
import spyOn = jest.spyOn;
import {of} from "rxjs";
import {CookieService} from "ngx-cookie-service";

describe('SignInComponent', () => {

  let component: SignInComponent;
  let fixture: ComponentFixture<SignInComponent>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      declarations: [SignInComponent],
      providers: [
        AuthService,
        HttpClientExtensionService,
        CookieManager
      ],
      imports: [CommonModule, ReactiveFormsModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(SignInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('form validation sign-in', () => {

    const invalidEmailInput = [
      'foo@gmail',
      'foo'
    ]

    test.each(invalidEmailInput)('should set error email when input as %p ', (input: string | null) => {

      component.form.patchValue({
        'email': input
      });

      expect(component.form.get('email')?.invalid).toBeTruthy();
      expect(component.form.get('email')?.errors).toEqual({email: 'Invalid e-mail'});
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
      expect(component.form.get('email')?.errors).toEqual({required: true});

      expect(component.form.get('password')?.invalid).toBeTruthy();
      expect(component.form.get('password')?.errors).toEqual({required: true});
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

  test('should set cookie when signIn return value', () => {

    component.form.patchValue({
      'email': 'foo@gmail.com',
      'password': '12345678Test'
    });

    let signInSpy = spyOn(AuthService.prototype, 'signIn').mockClear().mockReturnValue(of('token'));
    let cookieSetToken = spyOn(CookieManager.prototype, 'setToken').mockClear();
    component.signIn();

    expect(signInSpy).toHaveBeenCalledWith({ email: 'foo@gmail.com', password: '12345678Test' });
    expect(cookieSetToken).toHaveBeenCalledWith('token');
  });

  test('should not call signIn when form is invalid', () => {

    component.form.patchValue({
      'email': 'foo@gmail',
      'password': '123456'
    });

    let signInSpy = spyOn(AuthService.prototype, 'signIn').mockClear();
    let cookieSetToken = spyOn(CookieManager.prototype, 'setToken').mockClear();

    component.signIn();
    expect(signInSpy).not.toHaveBeenCalled();
    expect(cookieSetToken).not.toHaveBeenCalled();
  });

});
