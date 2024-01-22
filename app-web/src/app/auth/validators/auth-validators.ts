import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";
import e from "express";

export class AuthValidator {

  private static REGX_EMAIL = new RegExp("^[a-zA-Z0-9-_]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,15})$");
  private static REGX_LETTER = new RegExp("[aA-zZ]");
  private static REGX_CAPITAL_LETTER = new RegExp("[A-Z]");
  private static REGX_LOWERCASE_LETTER = new RegExp("[a-z]");
  private static REGX_NUMBER = new RegExp("[0-9]");

  static extractError(error: ValidationErrors | null | undefined) {
    return error ? Object.values(this.extractErrorValue(error)).join("\n") : '';
  }

  private static extractErrorValue(error: ValidationErrors | null | undefined): string[] {

    if (!error) {
      return [];
    }

    const keys = Object.keys(error);

    return keys.map(key => {

      const value = error[key];

      if (typeof value == 'boolean') {
        return key;
      }

      return value;
    })
  }

  static validateEmail(): ValidatorFn {
    return (control: AbstractControl): null | { [key: string]: any } => {
      if (!control.value?.length) {
        return null;
      }
      return this.REGX_EMAIL.test(control.value) ? null : { 'email': 'Invalid e-mail' };
    };
  }

  static validatePassword(): ValidatorFn {
    return (control: AbstractControl): null | { [key: string]: any } => {
      let errors: { [key: string]: string } = {};
      if (!control.value) return null;
      if (control.value?.length < 8) errors['password-length'] = 'Password must have a length greater than 8';
      if (!this.REGX_LETTER.test(control.value)) errors['password-minimum-letter'] = 'Password must have at least once letter';
      if (!this.REGX_CAPITAL_LETTER.test(control.value)) errors['password-capital-letter'] = 'Password must have at least once capital letter';
      if (!this.REGX_LOWERCASE_LETTER.test(control.value)) errors['password-lowercase-letter'] = 'Password must have at least once lowercase letter';
      if (!this.REGX_NUMBER.test(control.value)) errors['password-number'] = 'Password must have at least once number';
      return Object.keys(errors).length ? errors : null;
    };
  }
}
