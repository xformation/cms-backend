import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IFacility {
  id?: number;
  name?: string;
  status?: Status;
  startDate?: Moment;
  endDate?: Moment;
  suspandStartDate?: Moment;
  suspandEndDate?: Moment;
  academicYearId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<IFacility> = {};
