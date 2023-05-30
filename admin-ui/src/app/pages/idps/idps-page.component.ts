import {Component} from '@angular/core';
import {IdpConnection, IdpOrderField, IdpsGQL, OrderDirection} from "../../../graphql/generated";
import {IdpKey, IdpTableChange} from "../../components/idp-table/idp-table.component";

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
export class IdpsPageComponent {

  constructor(private idpsGQL: IdpsGQL) {}

  idpConnection?: IdpConnection;

  idpLoading = true;

  protected getData(page: number, size: number, direction: OrderDirection, field: IdpOrderField) {
    console.info('Making a request');

    this.idpsGQL.fetch({
        page: page,
        size: size,
        field: field,
        direction: direction
      }
    ).subscribe(result => {
      this.idpConnection = result.data.idps;
      this.idpLoading = false;
    })
  }

  pageChanged(event: IdpTableChange) {
    this.getData(event.page, event.size, event.direction == 1 ? OrderDirection.Asc : OrderDirection.Desc,
      KEY_TO_FIELD.get(event.key)!!)
  }
}
