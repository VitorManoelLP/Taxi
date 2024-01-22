import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {SignInComponent} from "./sign-in.component";
import {RouterModule, Routes} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../service/auth.service";
import {CookieService} from "ngx-cookie-service";
import {CookieManager} from "../../core/cookie.service";

const routes: Routes = [
  {
    path: '',
    component: SignInComponent
  }
]

@NgModule({
  declarations: [SignInComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    NgOptimizedImage,
    ReactiveFormsModule
  ],
  providers: [AuthService, CookieManager]
})
export class SignInModule { }
