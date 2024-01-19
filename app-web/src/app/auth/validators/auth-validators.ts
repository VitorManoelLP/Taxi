import {AbstractControl, FormControl, ValidatorFn, Validators} from "@angular/forms";

export class AuthValidator {

  private static REGX_EMAIL = new RegExp("^[a-zA-Z0-9-_]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,15})$");

  static validateEmail(): ValidatorFn {
    return (control: AbstractControl): null | { email: boolean } => {
      if (!control.value?.length) {
        return null;
      }
      return this.REGX_EMAIL.test(control.value) ? null : { 'email': true };
    };
  }

  static validatePassword(): ValidatorFn {
    return (control: AbstractControl): null | { [key: string]: any } => {

      if (!control.value) {
        return null;
      }

      if (control.value?.length < 8) {
        return { 'password-length': 'Password must have a length greater than 8' }
      }

      return null;
    };
  }
}
