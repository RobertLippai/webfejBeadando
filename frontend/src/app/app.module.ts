import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LocationListComponent } from './components/location-list/location-list.component';
import { LocationDetailsComponent } from './components/location-details/location-details.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddLocationComponent } from './components/add-location/add-location.component';
import { TreasureListComponent } from './components/treasure-list/treasure-list.component';
import { TreasureDetailsComponent } from './components/treasure-details/treasure-details.component';
import { AddTreasureComponent } from './components/add-treasure/add-treasure.component';

@NgModule({
  declarations: [
    AppComponent,
    LocationListComponent,
    LocationDetailsComponent,
    AddLocationComponent,
    TreasureListComponent,
    TreasureDetailsComponent,
    AddTreasureComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        NgbModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
