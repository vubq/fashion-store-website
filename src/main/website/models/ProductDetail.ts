import {Size} from '~/models/Size'
import {Color} from '~/models/Color'
import {Material} from '~/models/Material'
import {CreatedBy} from '~/models/CreatedBy'
import {UpdatedBy} from '~/models/UpdatedBy'
import {Status} from '~/models/enums/Status'
import {Image} from '~/models/Image'
import {Product} from '~/models/Product'

export class ProductDetail {
  public id?: string
  public code?: string
  public price?: number
  public quantity?: number
  public product?: Product
  public size?: Size
  public color?: Color
  public material?: Material
  public createdAt?: Date
  public updatedAt?: Date
  public createdBy?: CreatedBy
  public updatedBy?: UpdatedBy
  public status?: Status
  public images?: Image

  constructor(init?: Partial<ProductDetail>) {
    Object.assign(this, init)
  }
}
