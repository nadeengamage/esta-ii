import { Component, OnInit } from '@angular/core';
import { AssignerService } from 'src/app/services/assigner/assigner.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class AssignerDeleteComponent implements OnInit {

  success: boolean;
  error: boolean;

  constructor(private route: ActivatedRoute, private service: AssignerService) { }

  ngOnInit() {
  }

  delete() {
    this.route.params.subscribe(params => {
      this.service.deleteAssigner(params['id']).subscribe(
        data => {
          this.success = true;
        },
        e => {
          this.error = true;
        });
    })
  }

}
