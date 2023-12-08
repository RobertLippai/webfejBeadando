import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Treasure} from "../../models/treasure.model";
import {TreasureService} from "../../services/treasure.service";

@Component({
  selector: 'app-treasure-details',
  templateUrl: './treasure-details.component.html',
  styleUrls: ['./treasure-details.component.css']
})
export class TreasureDetailsComponent implements OnInit {
  @Input() viewMode = true;
  @Input() activeModal!: NgbActiveModal;
  @Input() currentTreasure: Treasure = {
    name: '',
    description: '',
    wasFound: false,
    locationName: ''
  };

  editTreasureForm!: FormGroup;
  canUpdate = true;

  infoMessage = '';

  constructor(
    private treasureService: TreasureService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.infoMessage = '';
      this.getTreasure(this.route.snapshot.params["id"]);
    }
    this.editTreasureForm = this.fb.group({
      name: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]],
      description: ['', [
        Validators.required,
        this.noWhitespaceValidator
      ]],
      wasFound: [false]
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

  getTreasure(id: string): void {
    this.treasureService.get(id)
      .subscribe({
        next: (data) => {
          this.currentTreasure = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateTreasure(): void {
    this.canUpdate = false;
    this.infoMessage = '';

    this.treasureService.update(this.currentTreasure.id, this.currentTreasure)
      .subscribe({
        next: (res) => {
          this.canUpdate = true;
          console.log(res);
          this.infoMessage = res.message ? res.message : 'The treasure was updated successfully!';
        },
        error: (e) => {
          this.canUpdate = true;
          console.error(e);
        }
      });
  }

  deleteTreasure(): void {
    this.treasureService.delete(this.currentTreasure.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/treasures']);
          this.activeModal.close();
        },
        error: (e) => console.error(e)
      });
  }

  markTreasureAsFound(): void {
    this.infoMessage = '';
    const unlockId = window.prompt('Enter Unlock ID');

    if (unlockId !== null) {
      this.treasureService.markAsFound(this.currentTreasure.id, unlockId)
        .subscribe({
          next: (res) => {
            console.log(res);
            console.log('Treasure marked as found successfully!');
            this.activeModal.close();
          },
          error: (e) => {
            console.error('Error marking treasure as found:', e);
            window.alert("Invalid unlock code!");
          }
        });
    } else {
      console.log('Unlock ID input canceled');
    }
  }
}

