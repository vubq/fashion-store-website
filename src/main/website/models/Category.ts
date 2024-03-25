import type {Status} from '~/models/enums/Status'

export class Category {
  public id?: string
  public name?: string
  public description?: string
  public createdBy?: string
  public createdAt?: Date
  public updatedAt?: Date
  public status?: Status

  constructor(init?: Partial<Category>) {
    Object.assign(this, init)
  }
}
