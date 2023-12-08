import {Component, OnInit} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Treasure} from "../../models/treasure.model";
import {TreasureService} from "../../services/treasure.service";
import {LocationService} from "../../services/location.service";
import {Location} from "../../models/location.model";

@Component({
  selector: 'app-treasure-list',
  templateUrl: './treasure-list.component.html',
  styleUrls: ['./treasure-list.component.css']
})
export class TreasureListComponent implements OnInit {
  treasures?: Treasure[];
  currentTreasure: Treasure = {};
  currentIndex = -1;
  locations?: Location[];


  constructor(
    private treasureService: TreasureService,
    private locationService : LocationService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.retrieveTreasures();

    this.treasureService.triggerRefresh$.subscribe(() => {
      console.log('Refreshing list triggered...');
      this.refreshList();
    });

    this.locationService.getAll().subscribe((locations) => {
      this.locations = locations;
    });
  }

  retrieveTreasures(): void {
    this.treasureService.getAll()
      .subscribe({
        next: (data) => {
          this.treasures = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveTreasures();
    this.currentTreasure = {};
    this.currentIndex = -1;
  }

  setActiveTreasure(treasure: Treasure, index: number): void {
    this.currentTreasure = treasure;
    this.currentIndex = index;
  }

  openTreasureDetailsModal(content: any, treasure: Treasure, index: number): void {
    this.setActiveTreasure(treasure, index);
    this.modalService.open(content, { centered: true });
  }
  openAddTreasureModal(content: any): void {
    this.modalService.open(content, { centered: true });
  }
}
