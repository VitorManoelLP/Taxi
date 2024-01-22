import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {AuthValidator} from "../validators/auth-validators";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.form = this.createForm();
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      'email': ['', Validators.compose([AuthValidator.validateEmail(), Validators.required])],
      'password': ['', Validators.compose([Validators.required, AuthValidator.validatePassword()])]
    });
  }

  protected readonly AuthValidator = AuthValidator;
}
