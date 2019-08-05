import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
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
  employeeId?: number;
  transportRouteId?: number;
  insuranceId?: number;
}

export const defaultValue: Readonly<IVehicle> = {};
