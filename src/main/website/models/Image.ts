import {Status} from '~/models/enums/Status'
import {ImageType} from '~/models/enums/ImageType'

export class Image {
  public id?: string
  public publicId?: string
  public url?: string
  public type?: ImageType
  public privateId?: string
  public createdAt?: Date
  public updatedAt?: Date
  public createdBy?: string
  public updatedBy?: string
  public status?: Status

  constructor(init?: Partial<Image>) {
    Object.assign(this, init)
  }
}
