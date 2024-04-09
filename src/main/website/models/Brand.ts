import {Status} from '~/models/enums/Status'
import {CreatedBy} from '~/models/CreatedBy'
import {UpdatedBy} from '~/models/UpdatedBy'

export class Brand {
  public id?: string
  public name?: string
  public description?: string
  public createdBy?: CreatedBy
  public updatedBy?: UpdatedBy
  public createdAt?: Date
  public updatedAt?: Date
  public status?: Status

  constructor(init?: Partial<Brand>) {
    Object.assign(this, init)
  }
}
