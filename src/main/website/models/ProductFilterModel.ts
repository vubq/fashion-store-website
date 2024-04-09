import {Status} from '~/models/enums/Status'

export class ProductFilterModel {
  public categories: string[] = []
  public brands: string[] = []
  public colors: string[] = []
  public sizes: string[] = []
  public materials: string[] = []
  public status: Status[] = []
  public priceApprox: number[] = []

  constructor(init?: Partial<ProductFilterModel>) {
    Object.assign(this, init)
  }
}
