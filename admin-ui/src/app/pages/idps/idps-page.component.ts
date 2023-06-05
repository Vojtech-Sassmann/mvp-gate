import {Component, Inject, Injector, OnInit} from '@angular/core';
import {
  CreateIdpGQL,
  IdpConnection,
  IdpOrderField,
  IdpsGQL,
  IdpsQuery,
  IdpsQueryVariables,
  OrderDirection
} from "../../../graphql/generated";
import {IdpKey, IdpOrderChange} from "../../components/tables/idp-table/idp-table.component";
import {QueryRef} from "apollo-angular";
import {map, Observable} from "rxjs";
import {ApolloQueryResult} from "@apollo/client";
import {TuiDialogService} from "@taiga-ui/core";
import {CreateIdpDialogComponent} from "../../components/dialogs/create-idp-dialog/create-idp-dialog.component";
import {PolymorpheusComponent} from '@tinkoff/ng-polymorpheus';

const KEY_TO_FIELD = new Map<IdpKey, IdpOrderField>([
  ['id', IdpOrderField.Name],
  ['name', IdpOrderField.Name], // TODO
  ['loginUrl', IdpOrderField.Name] // TODO
])
@Component({
  selector: 'app-idps-page',
  templateUrl: './idps-page.component.html',
  styleUrls: ['./idps-page.component.scss']
})
export class IdpsPageComponent implements OnInit {

  constructor(
    private idpsGQL: IdpsGQL,
    private createIdpGQL: CreateIdpGQL,
    @Inject(TuiDialogService) private readonly dialogs: TuiDialogService,
    @Inject(Injector) private readonly injector: Injector,
  ) {
  }

  idpLoading = true;

  readonly initPage = 0;
  readonly initSize = 10;
  readonly initDirection = OrderDirection.Desc;
  readonly initOrderField = IdpOrderField.Name;

  sortBy: IdpKey = 'name';
  direction: -1 | 1 = -1;

  queryRef?: QueryRef<IdpsQuery, IdpsQueryVariables>
  idpConnectionObservable?: Observable<IdpConnection>

  ngOnInit(): void {
    this.queryRef = this.idpsGQL.watch({
      page: this.initPage,
      size: this.initSize,
      field: this.initOrderField,
      direction: this.initDirection
    })

    this.queryRef.valueChanges
      .subscribe(result => this.idpLoading = result.loading);
    this.idpConnectionObservable = this.queryRef.valueChanges
      .pipe(map((result: ApolloQueryResult<IdpsQuery>) => result.data.idps));
  }

  pageChanged(newPage: number) {
    this.queryRef?.fetchMore({
      variables: {
        page: newPage
      }
    })
  }

  createIdp() {
    this.dialogs.open(
      new PolymorpheusComponent(CreateIdpDialogComponent, this.injector),
      {
        size: 'page',
        closeable: true,
        dismissible: true,
      },
    ).subscribe();
    this.createIdpGQL.mutate({
      input: {
        name: 'name' + new Date().getSeconds(),
        loginUrl: 'https://localhost'
      }
    }).subscribe(() => {
      this.queryRef?.refetch();
    })
  }

  orderChanged(change: IdpOrderChange) {
    console.log("order change")
    this.direction = change.direction;
    this.sortBy = change.key;
    this.queryRef?.fetchMore({
      variables: {
        direction: change.direction == 1 ? OrderDirection.Asc : OrderDirection.Desc,
        field: KEY_TO_FIELD.get(change.key),
      }
    })
  }
}
