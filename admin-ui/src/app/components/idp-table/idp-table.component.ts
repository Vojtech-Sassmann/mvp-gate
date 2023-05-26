import { Component } from '@angular/core';
import { Idp } from "../../model/Idp";

@Component({
  selector: 'app-idp-table',
  templateUrl: './idp-table.component.html',
  styleUrls: ['./idp-table.component.less']
})
export class IdpTableComponent {
  data: readonly Idp[] = [
    {
      name: 'Google',
      guid: 'ggggg',
      loginUrl: 'https://bla.com'
    },
    {
      name: 'Facebook',
      guid: 'ggggg',
      loginUrl: 'https://bla.com'
    },
  ] as const;

  readonly columns = ["name", "guid", "loginUrl"];
}
