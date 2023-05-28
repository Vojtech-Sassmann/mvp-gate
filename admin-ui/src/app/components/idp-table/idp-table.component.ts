import {ChangeDetectionStrategy, Component} from '@angular/core';
import {BehaviorSubject, combineLatest, debounceTime, filter, map, Observable, share, startWith, switchMap} from "rxjs";
import {TUI_ARROW} from "@taiga-ui/kit";
import {TUI_DEFAULT_MATCHER, tuiIsFalsy, tuiIsPresent} from "@taiga-ui/cdk";
import {Idp, IdpConnection, IdpOrderField, IdpsGQL, OrderDirection} from "../../../graphql/generated";

type Key = 'id' | 'name' | 'loginUrl'

const KEY_TO_FIELD = new Map<Key, IdpOrderField>([
  ['id', IdpOrderField.Name],
  ['name', IdpOrderField.Name], // TODO
  ['loginUrl', IdpOrderField.Name] // TODO
])

const KEYS: Record<string, Key> = {
  Name: 'name',
  Id: 'id',
  'Login URL': 'loginUrl',
};

@Component({
  selector: 'app-idp-table',
  templateUrl: './idp-table.component.html',
  styleUrls: ['./idp-table.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class IdpTableComponent {

  private readonly size$ = new BehaviorSubject(5);
  private readonly page$ = new BehaviorSubject(0);

  readonly direction$ = new BehaviorSubject<-1 | 1>(-1);
  readonly sorter$ = new BehaviorSubject<Key>('name');

  search = '';

  readonly request$ = combineLatest([
    this.sorter$,
    this.direction$,
    this.page$,
    this.size$,
  ]).pipe(
    // zero time debounce for a case when both key and direction change
    debounceTime(0),
    switchMap(query => this.getData(...query).pipe(startWith(null))),
    share(),
  );

  initial: readonly string[] = ['Name', 'Id', 'Login URL'];

  enabled = this.initial;

  columns = ["name", "id", "loginUrl"];

  readonly arrow = TUI_ARROW;

  readonly loading$ = this.request$.pipe(map(tuiIsFalsy));

  readonly total$ = this.request$.pipe(
    filter(tuiIsPresent),
    map(idpConn => {
      return idpConn!.totalCount;
    },
    startWith(1)),
  );

  readonly data$: Observable<readonly Idp[]> = this.request$.pipe(
    filter(tuiIsPresent),
    map(idps => idps.nodes),
    startWith([]),
  );

  constructor(private idpsGQL: IdpsGQL) {}

  onEnabled(enabled: readonly string[]): void {
    this.enabled = enabled;
    this.columns = this.initial
      .filter(column => enabled.includes(column))
      .map(column => KEYS[column]);
  }

  onDirection(direction: -1 | 1): void {
    this.direction$.next(direction);
  }

  onSize(size: number): void {
    this.size$.next(size);
  }

  onPage(page: number): void {
    this.page$.next(page);
  }

  isMatch(value: unknown): boolean {
    return !!this.search && TUI_DEFAULT_MATCHER(value, this.search);
  }

  private getData(
    key: Key,
    direction: -1 | 1,
    page: number,
    size: number,
  ): Observable<IdpConnection> {
    console.info('Making a request');
    // return timer(1).pipe(map(() => []));

    return this.idpsGQL.fetch({
        page: page,
        size: size,
        field: KEY_TO_FIELD.get(key),
        direction: direction == 1 ? OrderDirection.Asc : OrderDirection.Desc
      }
    )
      .pipe(map(result => result.data.idps))
  }

  getLoginUrl(idp: Idp) {
    return idp.loginUrl;
  }

  addNewIdp() {

  }
}
