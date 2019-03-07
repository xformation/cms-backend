export interface IDocuments {
  id?: number;
  documentName?: string;
  upload?: string;
  studentId?: number;
}

export const defaultValue: Readonly<IDocuments> = {};
