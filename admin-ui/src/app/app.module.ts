import {TuiRootModule, TuiDialogModule, TuiAlertModule, TuiThemeNightModule, TuiModeModule} from "@taiga-ui/core";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IdpTableComponent } from './components/idp-table/idp-table.component';
import {TuiTableModule} from "@taiga-ui/addon-table";

@NgModule({
  declarations: [
    AppComponent,
    IdpTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    TuiRootModule,
    TuiDialogModule,
    TuiAlertModule,
    TuiTableModule,
    TuiThemeNightModule,
    TuiModeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
