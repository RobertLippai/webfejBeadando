import { Component, Input, OnInit } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from 'src/app/models/location.model';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-location-details',
  templateUrl: './location-details.component.html',
  styleUrls: ['./location-details.component.css']
})
export class LocationDetailsComponent implements OnInit {
  @Input() viewMode = true;
  @Input() activeModal!: NgbActiveModal;
  @Input() currentLocation: Location = {
    name: '',
    coordinates: ''
  };

  editLocationForm!: FormGroup;
  canUpdate = true;

  infoMessage = '';

  constructor(
    private locationService: LocationService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.infoMessage = '';
      this.getLocation(this.route.snapshot.params["id"]);
    }
    this.editLocationForm = this.fb.group({
      name: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]],
      coordinates: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]]
    });
    /*this.editLocationForm.valueChanges.subscribe(value => {
      //this.currentLocation.name = value.name
      //this.currentLocation.coordinates = value
      console.log("changeeee");
    });*/
  }

  public noWhitespaceValidator(control: FormControl) {
    return (control.value || '').trim().length? null : { 'required': true };
  }

  getLocation(id: string): void {
    this.locationService.get(id)
      .subscribe({
        next: (data) => {
          this.currentLocation = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateLocation(): void {
    this.canUpdate = false;
    this.infoMessage = '';

    this.locationService.update(this.currentLocation.id, this.currentLocation)
      .subscribe({
        next: (res) => {
          this.canUpdate = true;
          console.log(res);
          this.infoMessage = res.message ? res.message : 'The location was updated successfully!';
        },
        error: (e) => {
          this.canUpdate = true;
          console.error(e);
        }
      });
  }

  deleteLocation(): void {
    this.locationService.delete(this.currentLocation.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/locations']);
          this.activeModal.close();
        },
        error: (e) => console.error(e)
      });
  }
}
