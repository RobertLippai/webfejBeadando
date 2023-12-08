import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LocationListComponent } from './components/location-list/location-list.component';
import { LocationDetailsComponent } from './components/location-details/location-details.component';
import { AddLocationComponent } from './components/add-location/add-location.component';
import { TreasureListComponent } from './components/treasure-list/treasure-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'locations', pathMatch: 'full' },
  { path: 'locations', component: LocationListComponent },
  { path: 'locations/:id', component: LocationDetailsComponent },
  { path: 'add', component: AddLocationComponent },
  { path: 'treasures', component: TreasureListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
