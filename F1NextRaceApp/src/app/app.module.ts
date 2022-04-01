import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule}  from "@angular/common/http";
import { AppComponent } from './app.component';
import { TimerComponent } from './timer/timer.component';
import { GrandPrixComponent } from './grand-prix/grand-prix.component';

@NgModule({
  declarations: [
    AppComponent,
    TimerComponent,
    GrandPrixComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
