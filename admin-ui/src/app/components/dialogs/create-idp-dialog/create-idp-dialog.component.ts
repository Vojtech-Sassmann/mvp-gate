import {ChangeDetectionStrategy, Component, Inject} from '@angular/core';
import {POLYMORPHEUS_CONTEXT} from '@tinkoff/ng-polymorpheus';
import {TuiDialogContext} from "@taiga-ui/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-idp-dialog',
  templateUrl: './create-idp-dialog.component.html',
  styleUrls: ['./create-idp-dialog.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreateIdpDialogComponent {
  constructor(
    @Inject(POLYMORPHEUS_CONTEXT) private readonly context: TuiDialogContext<boolean>,
  ) {
  }

  close(): void {
    this.context.completeWith(false);
  }

  form = new FormGroup({
    nameValue: new FormControl(``, Validators.required),
    loginUrlValue: new FormControl(``, Validators.required),
  })
}
