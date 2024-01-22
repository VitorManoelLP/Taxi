import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {AuthValidator} from "../validators/auth-validators";
import {AuthService} from "../service/auth.service";
import {CookieService} from "ngx-cookie-service";
import {CookieManager} from "../../core/cookie.service";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {

  readonly AuthValidator = AuthValidator;
  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private cookieManager: CookieManager) {
    this.form = this.createForm();
  }

  public signIn() {
    if (this.form.valid) {
      this.authService.signIn({ email: this.email, password: this.password })
        .subscribe(token => this.cookieManager.setToken(token));
    }
  }

  get email() {
    return this.form.get('email')?.value;
  }

  get password() {
    return this.form.get('password')?.value;
  }

  private createForm(): FormGroup {
    const form = this.formBuilder.group({
      'email': [''],
      'password': ['']
    });
    form.get('email')?.valueChanges.subscribe(() => form.get('email')?.setValidators(Validators.compose([AuthValidator.validateEmail(), Validators.required])));
    form.get('password')?.valueChanges.subscribe(() => form.get('password')?.setValidators(Validators.compose([AuthValidator.validatePassword(), Validators.required])));
    return form;
  }
}
