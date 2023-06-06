import {ChangeDetectionStrategy, Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {combineLatest, debounceTime, map, Observable, share, Subject, Subscription} from "rxjs";
import {TUI_ARROW} from "@taiga-ui/kit";
import {TUI_DEFAULT_MATCHER} from "@taiga-ui/cdk";
import {Idp, IdpConnection} from "../../../../graphql/generated";

export type IdpKey = 'id' | 'name' | 'loginUrl'

const KEYS: Record<string, IdpKey> = {
  Name: 'name',
  Id: 'id',
  'Login URL': 'loginUrl',
}
export interface IdpOrderChange {
  key: IdpKey;
  direction: -1 | 1;
}
@Component({
  selector: 'app-idp-table',
  templateUrl: './idp-table.component.html',
  styleUrls: ['./idp-table.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class IdpTableComponent implements OnInit, OnDestroy {

  @Input()
  loading = true;
  @Input()
  idpConnectionObservable?: Observable<IdpConnection>;
  @Input()
  page = 0;
  @Input()
  size = 5;
  @Input()
  sortBy: IdpKey = 'name';
  @Input()
  direction: -1 | 1 = 1;

  @Output()
  pageChange = new EventEmitter<number>();
  @Output()
  add = new EventEmitter();
  @Output()
  orderChange = new EventEmitter<IdpOrderChange>();

  readonly arrow = TUI_ARROW;
  readonly direction$ = new Subject<-1 | 1>();
  readonly sorter$ = new Subject<IdpKey>();

  initial: readonly string[] = ['Name', 'Id', 'Login URL'];
  enabled = this.initial;
  columns = ["name", "id", "loginUrl"];
  search = '';

  subscription?: Subscription;

  ngOnInit(): void {
    this.subscription = combineLatest([
      this.sorter$,
      this.direction$,
    ]).pipe(
      // zero time debounce for a case when both key and direction change
      debounceTime(0),
      map(query => this.createIdpOrderChange(...query)),
      share(),
    ).subscribe(change => this.orderChange.emit(change))
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe()
  }

  onEnabled(enabled: readonly string[]): void {
    this.enabled = enabled;
    this.columns = this.initial
      .filter(column => enabled.includes(column))
      .map(column => KEYS[column]);
  }

  onPage(page: number): void {
    this.pageChange.emit(page)
  }

  isMatch(value: unknown): boolean {
    return !!this.search && TUI_DEFAULT_MATCHER(value, this.search);
  }

  private createIdpOrderChange(
    key: IdpKey,
    direction: -1 | 1,
  ): IdpOrderChange {
    return {
      key: key,
      direction: direction,
    }
  }

  getLoginUrl(idp: Idp) {
    return idp.loginUrl;
  }

  addNewIdp() {
    this.add.emit();
  }
}
