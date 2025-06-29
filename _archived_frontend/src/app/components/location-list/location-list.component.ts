import { Component, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location.model';
import { LocationService } from 'src/app/services/location.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-location-list',
  templateUrl: './location-list.component.html',
  styleUrls: ['./location-list.component.css']
})

export class LocationListComponent implements OnInit {
  locations?: Location[];
  currentLocation: Location = {};
  currentIndex = -1;

  constructor(
    private locationService: LocationService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.retrieveLocations();

    this.locationService.triggerRefresh$.subscribe(() => {
      console.log('Refreshing list triggered...');
      this.refreshList();
    });
  }

  retrieveLocations(): void {
    this.locationService.getAll()
      .subscribe({
        next: (data) => {
          this.locations = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveLocations();
    this.currentLocation = {};
    this.currentIndex = -1;
  }

  setActiveLocation(location: Location, index: number): void {
    this.currentLocation = location;
    this.currentIndex = index;
  }

  openLocationDetailsModal(content: any, location: Location, index: number): void {
    this.setActiveLocation(location, index);
    this.modalService.open(content, { centered: true });
  }
  openAddLocationModal(content: any): void {
    this.modalService.open(content, { centered: true });
  }
}
