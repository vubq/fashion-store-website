export class DataTableRequest {
  public currentPage = 1
  public perPage = 10
  public filter = ''
  public sortBy = ''
  public sortDesc = true

  constructor(init?: Partial<DataTableRequest>) {
    Object.assign(this, init)
  }
}
