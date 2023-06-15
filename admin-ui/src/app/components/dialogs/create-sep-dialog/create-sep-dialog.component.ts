import {ChangeDetectionStrategy, Component, Inject, Self} from '@angular/core';
import {TuiDestroyService} from "@taiga-ui/cdk";
import {BehaviorSubject, takeUntil} from "rxjs";
import {POLYMORPHEUS_CONTEXT} from "@tinkoff/ng-polymorpheus";
import {TuiAlertService, TuiDialogContext, TuiNotification} from "@taiga-ui/core";
import {CreateSepGQL} from "../../../../graphql/generated";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ApolloError} from "@apollo/client";

@Component({
  selector: 'app-create-sep-dialog',
  templateUrl: './create-sep-dialog.component.html',
  styleUrls: ['./create-sep-dialog.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [TuiDestroyService],
})
export class CreateSepDialogComponent {

  createProcessing$ = new BehaviorSubject(false);

  form = new FormGroup({
    nameValue: new FormControl(``, Validators.required),
  });

  constructor(
    @Inject(POLYMORPHEUS_CONTEXT) private readonly context: TuiDialogContext<boolean>,
    @Self() @Inject(TuiDestroyService) private readonly destroy$: TuiDestroyService,
    @Inject(TuiAlertService) private readonly alerts: TuiAlertService,
    private readonly createSepGQL: CreateSepGQL,
  ) {
  }

  close(): void {
    this.context.completeWith(false);
  }


  createSep(): void {
    if (!this.form.valid) {
      return;
    }
    this.createProcessing$.next(true);
    this.createSepGQL.mutate({
        input: {
          name: this.form.controls.nameValue.value!!,
          redirectUrls: [], //TODO
        }
      }
    )
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: result => {
          this.context.completeWith(true);
        },
        error: (error: ApolloError) => {
          this.alerts.open(error.message, {
            label: 'Creation failed',
            status: TuiNotification.Error,
          })
            .subscribe();
          this.createProcessing$.next(false)
        },
        complete: () => {
          this.createProcessing$.next(false)
        }
      })
  }
}
