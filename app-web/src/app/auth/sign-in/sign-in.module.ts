import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {SignInComponent} from "./sign-in.component";
import {RouterModule, Routes} from "@angular/router";

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
    NgOptimizedImage
  ]
})
export class SignInModule { }
