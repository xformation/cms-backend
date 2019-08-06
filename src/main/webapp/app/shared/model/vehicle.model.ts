import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE',
  DRAFT = 'DRAFT'
}

export interface IVehicle {
  id?: number;
  vehicleNumber?: number;
  vehicleType?: string;
  capacity?: number;
  ownerShip?: string;
  dateOfRegistration?: Moment;
  yearOfManufacturing?: string;
  manufacturingCompany?: string;
  model?: string;
  chasisNo?: string;
  rcNo?: string;
  contactNumber?: string;
  status?: Status;
  insuranceId?: number;
  employeeId?: number;
  transportRouteId?: number;
}

export const defaultValue: Readonly<IVehicle> = {};
