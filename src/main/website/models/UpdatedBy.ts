export class UpdatedBy {
  public id?: string
  public name?: string

  constructor(init?: Partial<UpdatedBy>) {
    Object.assign(this, init)
  }
}
