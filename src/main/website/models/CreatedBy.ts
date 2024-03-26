export class CreatedBy {
  public id?: string
  public name?: string

  constructor(init?: Partial<CreatedBy>) {
    Object.assign(this, init)
  }
}
