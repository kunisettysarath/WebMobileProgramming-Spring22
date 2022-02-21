import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CountDownComponent } from './count-down/count-down.component';
// import { CountApp1Component } from './count-app1/count-app1.component';

@NgModule({
  declarations: [
    AppComponent,
    CountDownComponent,
    // CountApp1Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
