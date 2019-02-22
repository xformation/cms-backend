export interface ITransportRoute {
  id?: number;
  routeName?: string;
  routeDetails?: string;
  routeMapUrl?: string;
}

export const defaultValue: Readonly<ITransportRoute> = {};
