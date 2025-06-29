import {Component, Input, OnInit} from '@angular/core';
import { LocationService } from 'src/app/services/location.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-location',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css']
})
export class AddLocationComponent implements OnInit {
  constructor(private locationService: LocationService, private fb: FormBuilder){}

  @Input() activeModal!: NgbActiveModal;
  newLocationForm!: FormGroup;

  canSave = true;

  ngOnInit(): void {
    this.newLocationForm = this.fb.group({
      name: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]],
      coordinates: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]]
    });
  }

  public noWhitespaceValidator(control: FormControl) {
    return (control.value || '').trim().length? null : { 'required': true };
  }

  saveLocation(): void {
    this.canSave = false;

    const data = {
      name: this.newLocationForm.get('name')?.value,
      coordinates: this.newLocationForm.get('coordinates')?.value
    };

    this.locationService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
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
