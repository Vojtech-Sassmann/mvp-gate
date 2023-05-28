import {ChangeDetectionStrategy, Component} from '@angular/core';
import {Idp} from "../../model/Idp";
import {
  BehaviorSubject,
  combineLatest,
  debounceTime,
  filter,
  map,
  Observable,
  share,
  startWith,
  switchMap,
  timer
} from "rxjs";
import {TUI_ARROW} from "@taiga-ui/kit";
import {TUI_DEFAULT_MATCHER, tuiDefaultSort, tuiIsFalsy, tuiIsPresent} from "@taiga-ui/cdk";
import {TuiComparator} from "@taiga-ui/addon-table";

type Key = 'id' | 'name' | 'loginUrl'

const KEYS: Record<string, Key> = {
  Name: 'name',
  Id: 'id',
  'Login URL': 'loginUrl',
};
const DATA: readonly Idp[] = Array.from({length: 300}, () => ({
  name: `fb-${Math.floor(Math.random() * 10)}`,
  id: 'dfdfdfd',
  loginUrl: `https://fdfdf-${Math.floor(Math.random() * 10)}.com`,
}));

@Component({
  selector: 'app-idp-table',
  templateUrl: './idp-table.component.html',
  styleUrls: ['./idp-table.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class IdpTableComponent {

  private readonly size$ = new BehaviorSubject(10);
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
    map(({length}) => length),
    startWith(1),
  );

  readonly data$: Observable<readonly Idp[]> = this.request$.pipe(
    filter(tuiIsPresent),
    map(idps => idps.filter(tuiIsPresent)),
    startWith([]),
  );

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
  ): Observable<ReadonlyArray<Idp | null>> {
    console.info('Making a request');

    const start = page * size;
    const end = start + size;
    const result = [...DATA]
      .sort(sortBy(key, direction))
      .map((user, index) => (index >= start && index < end ? user : null));

    // Imitating server response
    return timer(3000).pipe(map(() => result));
  }

  getLoginUrl(idp: Idp) {
    return idp.loginUrl;
  }


  addNewIdp() {

  }
}

function sortBy(key: Key, direction: -1 | 1): TuiComparator<Idp> {
  return (a, b) => direction * tuiDefaultSort(a[key], b[key]);
}
