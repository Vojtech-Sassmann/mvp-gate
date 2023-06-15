import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IdpsPageComponent} from "./pages/idps-page/idps-page.component";
import {SepsPageComponent} from "./pages/seps-page/seps-page.component";

const routes: Routes = [
  {
    path: 'admin',
    children: [
      {
        path: 'idp',
        component: IdpsPageComponent,
      },
      {
        path: 'sep',
        component: SepsPageComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
