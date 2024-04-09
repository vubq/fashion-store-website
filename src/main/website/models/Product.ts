import {Status} from '~/models/enums/Status'
import {CreatedBy} from '~/models/CreatedBy'
import {Category} from '~/models/Category'
import {Brand} from '~/models/Brand'
import {Size} from '~/models/Size'
import {Color} from '~/models/Color'
import {Material} from '~/models/Material'
import {UpdatedBy} from '~/models/UpdatedBy'
import {Image} from '~/models/Image'

export class Product {
  public id?: string
  public name?: string
  public description?: string
  public price?: number
  public category?: Category
  public brand?: Brand
  public sizes?: Size[]
  public colors?: Color[]
  public materials?: Material[]
  public createdBy?: CreatedBy
  public updatedBy?: UpdatedBy
  public status?: Status
  public images?: Image

  constructor(init?: Partial<Product>) {
    Object.assign(this, init)
  }
}
