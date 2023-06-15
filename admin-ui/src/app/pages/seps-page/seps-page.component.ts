import {Component, Inject, Injector} from '@angular/core';
import {
  OrderDirection,
  SepConnection,
  SepOrderField,
  SepsGQL,
  SepsQuery,
  SepsQueryVariables
} from "../../../graphql/generated";
import {SepKey, SepOrderChange} from "../../components/tables/sep-table/sep-table.component";
import {QueryRef} from "apollo-angular";
import {map, Observable} from "rxjs";
import {ApolloQueryResult} from "@apollo/client";
import {PolymorpheusComponent} from "@tinkoff/ng-polymorpheus";
import {TuiAlertService, TuiDialogService, TuiNotification} from "@taiga-ui/core";
import {CreateSepDialogComponent} from "../../components/dialogs/create-sep-dialog/create-sep-dialog.component";

const KEY_TO_FIELD = new Map<SepKey, SepOrderField>([
  ['id', SepOrderField.Name],
  ['name', SepOrderField.Name], // TODO
])
@Component({
  selector: 'app-seps-page',
  templateUrl: './seps-page-component.html',
  styleUrls: ['./seps-page-component.scss']
})
export class SepsPageComponent {

  constructor(
    private sepsGQL: SepsGQL,
    @Inject(TuiDialogService) private readonly dialogs: TuiDialogService,
    @Inject(Injector) private readonly injector: Injector,
    @Inject(TuiAlertService) private readonly alerts: TuiAlertService,
  ) {
  }

  sepLoading = true;

  readonly initPage = 0;
  readonly initSize = 10;
  readonly initDirection = OrderDirection.Asc;
  readonly initOrderField = SepOrderField.Name;

  sortBy: SepKey = 'name';
  direction: -1 | 1 = -1;

  queryRef?: QueryRef<SepsQuery, SepsQueryVariables>
  sepConnectionObservable?: Observable<SepConnection>

  ngOnInit(): void {
    this.queryRef = this.sepsGQL.watch({
      page: this.initPage,
      size: this.initSize,
      field: this.initOrderField,
      direction: this.initDirection
    })

    this.queryRef.valueChanges
      .subscribe(result => this.sepLoading = result.loading);
    this.sepConnectionObservable = this.queryRef.valueChanges
      .pipe(map((result: ApolloQueryResult<SepsQuery>) => result.data.seps));
  }

  pageChanged(newPage: number) {
    this.sepLoading = true;
    this.queryRef?.fetchMore({
      variables: {
        page: newPage
      }
    })
  }

  orderChanged(change: SepOrderChange) {
    this.direction = change.direction;
    this.sortBy = change.key;
    this.queryRef?.fetchMore({
      variables: {
        direction: change.direction == 1 ? OrderDirection.Asc : OrderDirection.Desc,
        field: KEY_TO_FIELD.get(change.key),
      }
    })
  }

  createSep() {
    this.dialogs.open<boolean>(
      new PolymorpheusComponent(CreateSepDialogComponent, this.injector),
      {
        size: 'page',
        closeable: true,
        dismissible: true,
      },
    ).subscribe(created => {
      if (created) {
        this.alerts
          .open('Sep was successfully created', {
            label: 'Sep created',
            status: TuiNotification.Success,
          })
          .subscribe();
      }
    });
  }
}
