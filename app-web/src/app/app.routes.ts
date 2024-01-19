import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'sign-in',
    loadChildren: () => import('./auth/sign-in/sign-in.module').then(m => m.SignInModule)
  },
  {
    path: '',
    redirectTo: 'sign-in',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: 'sign-in'
  }
];
