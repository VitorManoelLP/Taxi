import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {SignInComponent} from "./sign-in.component";
import {RouterModule, Routes} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../service/auth.service";
import {CookieManager} from "../../core/cookie.service";
import {HttpClientExtensionService} from "../../core/http-client-extension.service";
import {HttpClientModule} from "@angular/common/http";

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
    HttpClientModule,
    NgOptimizedImage,
    ReactiveFormsModule
  ],
  providers: [AuthService, CookieManager, HttpClientExtensionService]
})
export class SignInModule { }
