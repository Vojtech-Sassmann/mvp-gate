import {Component, EventEmitter, Input, Output} from '@angular/core';
import {combineLatest, debounceTime, map, Observable, share, Subject, Subscription} from "rxjs";
import {TUI_ARROW} from "@taiga-ui/kit";
import {TUI_DEFAULT_MATCHER} from "@taiga-ui/cdk";
import {SepConnection} from "../../../../graphql/generated";

export type SepKey = 'id' | 'name'

const KEYS: Record<string, SepKey> = {
  Name: 'name',
  Id: 'id',
}
export interface SepOrderChange {
  key: SepKey;
  direction: -1 | 1;
}
@Component({
  selector: 'app-sep-table',
  templateUrl: './sep-table.component.html',
  styleUrls: ['./sep-table.component.scss']
})
export class SepTableComponent {

  @Input()
  loading = true;
  @Input()
  sepConnectionObservable?: Observable<SepConnection>;
  @Input()
  page = 0;
  @Input()
  size = 5;
  @Input()
  sortBy: SepKey = 'name';
  @Input()
  direction: -1 | 1 = 1;

  @Output()
  pageChange = new EventEmitter<number>();
  @Output()
  add = new EventEmitter();
  @Output()
  orderChange = new EventEmitter<SepOrderChange>();

  readonly arrow = TUI_ARROW;
  readonly direction$ = new Subject<-1 | 1>();
  readonly sorter$ = new Subject<SepKey>();

  initial: readonly string[] = ['Name', 'Id'];
  enabled = this.initial;
  columns = ["name", "id"];
  search = '';

  subscription?: Subscription;
  ngOnInit(): void {
    this.subscription = combineLatest([
      this.sorter$,
      this.direction$,
    ]).pipe(
      // zero time debounce for a case when both key and direction change
      debounceTime(0),
      map(query => this.createSepOrderChange(...query)),
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

  private createSepOrderChange(
    key: SepKey,
    direction: -1 | 1,
  ): SepOrderChange {
    return {
      key: key,
      direction: direction,
    }
  }

  addNewSep() {
    this.add.emit();
  }
}
