import {ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject, combineLatest, debounceTime, map, share} from "rxjs";
import {TUI_ARROW} from "@taiga-ui/kit";
import {TUI_DEFAULT_MATCHER} from "@taiga-ui/cdk";
import {Idp, IdpConnection} from "../../../graphql/generated";

export type IdpKey = 'id' | 'name' | 'loginUrl'

const KEYS: Record<string, IdpKey> = {
  Name: 'name',
  Id: 'id',
  'Login URL': 'loginUrl',
};

export interface IdpTableChange {
  key: IdpKey;
  direction: -1 | 1;
  page: number;
  size: number;
}

@Component({
  selector: 'app-idp-table',
  templateUrl: './idp-table.component.html',
  styleUrls: ['./idp-table.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class IdpTableComponent implements OnInit {

  private readonly size$ = new BehaviorSubject(5);
  private readonly page$ = new BehaviorSubject(0);

  readonly direction$ = new BehaviorSubject<-1 | 1>(-1);
  readonly sorter$ = new BehaviorSubject<IdpKey>('name');

  search = '';

  @Input()
  loading = true;

  @Input()
  idpConnection?: IdpConnection;

  @Output()
  page = new EventEmitter<IdpTableChange>();
  pageSubject$ = combineLatest([
    this.sorter$,
    this.direction$,
    this.page$,
    this.size$,
  ]).pipe(
    // zero time debounce for a case when both key and direction change
    debounceTime(0),
    map(query => this.foo(...query)),
    share(),
  )

  ngOnInit(): void {
    this.pageSubject$.subscribe(idpTableChange => this.page.emit(idpTableChange));
  }

  initial: readonly string[] = ['Name', 'Id', 'Login URL'];

  enabled = this.initial;

  columns = ["name", "id", "loginUrl"];

  readonly arrow = TUI_ARROW;

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

  private foo(
    key: IdpKey,
    direction: -1 | 1,
    page: number,
    size: number,
  ): IdpTableChange {
    return {
      key: key,
      direction: direction,
      page: page,
      size: size
    }
  }

  getLoginUrl(idp: Idp) {
    return idp.loginUrl;
  }

  addNewIdp() {

  }
}
