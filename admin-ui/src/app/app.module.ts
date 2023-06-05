import {
  TuiRootModule,
  TuiDialogModule,
  TuiAlertModule,
  TuiThemeNightModule,
  TuiModeModule,
  TuiHostedDropdownModule, TuiLoaderModule, TuiButtonModule, TuiTextfieldControllerModule, TuiErrorModule
} from "@taiga-ui/core";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IdpTableComponent } from './components/tables/idp-table/idp-table.component';
import {TuiReorderModule, TuiTableModule, TuiTablePaginationModule} from "@taiga-ui/addon-table";
import {TuiFieldErrorPipeModule, TuiInputModule} from "@taiga-ui/kit";
import {TuiLetModule} from "@taiga-ui/cdk";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { GraphQLModule } from './graphql.module';
import { HttpClientModule } from '@angular/common/http';
import { IdpsPageComponent } from './pages/idps/idps-page.component';
import { CreateIdpDialogComponent } from './components/dialogs/create-idp-dialog/create-idp-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    IdpTableComponent,
    IdpsPageComponent,
    CreateIdpDialogComponent,
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
    TuiModeModule,
    TuiInputModule,
    TuiHostedDropdownModule,
    TuiReorderModule,
    TuiLoaderModule,
    TuiTablePaginationModule,
    TuiButtonModule,
    TuiTextfieldControllerModule,
    TuiLetModule,
    FormsModule,
    GraphQLModule,
    HttpClientModule,
    ReactiveFormsModule,
    TuiErrorModule,
    TuiFieldErrorPipeModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
