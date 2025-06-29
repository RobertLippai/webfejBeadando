import {Component, Input, OnInit} from '@angular/core';
import {TreasureService} from "../../services/treasure.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Location} from "../../models/location.model";
import {LocationService} from "../../services/location.service";

@Component({
  selector: 'app-add-treasure',
  templateUrl: './add-treasure.component.html',
  styleUrls: ['./add-treasure.component.css']
})
export class AddTreasureComponent  implements OnInit {
  constructor(
    private treasureService: TreasureService,
    private locationService : LocationService,
    private fb: FormBuilder
  ){}

  @Input() activeModal!: NgbActiveModal;
  newTreasureForm!: FormGroup;

  canSave = true;

  locations!: Location[];
  selectedLocation: Location = {};

  ngOnInit(): void {
    this.newTreasureForm = this.fb.group({
      name: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]],
      description: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]],
      location: [null,
        Validators.required
      ], // Add the location control
    });

    this.locationService.getAll().subscribe((locations) => {
      this.locations = locations;
      this.selectedLocation = locations[0];
    });
  }

  public noWhitespaceValidator(control: FormControl) {
    return (control.value || '').trim().length? null : { 'required': true };
  }

  saveTreasure(): void {
    this.canSave = false;

    const data = {
      name: this.newTreasureForm.get('name')?.value,
      description: this.newTreasureForm.get('description')?.value,
      wasFound: false,
      locationName: this.selectedLocation.name
    };

    this.treasureService.create(data, this.selectedLocation.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          console.log(res?.unlockID);
          window.alert("Treasure was created! Unlock id: " + res?.unlockID)
          this.activeModal.close();
        },
        error: (e) => {
          this.canSave = true;
          console.error(e);
          window.alert(e?.error.message);
          console.log(e?.error.message);
        }
      });
  }
}
