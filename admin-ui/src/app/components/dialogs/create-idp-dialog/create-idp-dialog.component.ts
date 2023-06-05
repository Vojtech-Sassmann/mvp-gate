import {ChangeDetectionStrategy, Component, Inject, Self} from '@angular/core';
import {POLYMORPHEUS_CONTEXT} from '@tinkoff/ng-polymorpheus';
import {TuiDialogContext} from "@taiga-ui/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BehaviorSubject, takeUntil} from "rxjs";
import {CreateIdpGQL} from "../../../../graphql/generated";
import {TuiDestroyService} from "@taiga-ui/cdk";

@Component({
  selector: 'app-create-idp-dialog',
  templateUrl: './create-idp-dialog.component.html',
  styleUrls: ['./create-idp-dialog.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [TuiDestroyService],
})
export class CreateIdpDialogComponent {

  createProcessing$ = new BehaviorSubject(false);

  constructor(
    @Inject(POLYMORPHEUS_CONTEXT) private readonly context: TuiDialogContext<boolean>,
    @Self() @Inject(TuiDestroyService) private readonly destroy$: TuiDestroyService,
    private readonly createIdpGQL: CreateIdpGQL
  ) {
  }

  close(): void {
    this.context.completeWith(false);
  }

  createIdp(): void {
    if (!this.form.valid) {
      return;
    }
    this.createProcessing$.next(true);
    this.createIdpGQL.mutate({
        input: {
          name: this.form.controls.nameValue.value!!,
          loginUrl: this.form.controls.loginUrlValue.value!!
        }
      }
    )
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: result => {
          this.context.completeWith(true);
        },
        error: error => {
          console.error(error)
          this.createProcessing$.next(false)
        },
        complete: () => {
          this.createProcessing$.next(false)
        }
      })
  }

  form = new FormGroup({
    nameValue: new FormControl(``, Validators.required),
    loginUrlValue: new FormControl(``, Validators.required),
  })
}
