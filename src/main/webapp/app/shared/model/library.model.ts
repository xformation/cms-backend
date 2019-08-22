export interface ILibrary {
  id?: number;
  bookTitle?: string;
  author?: string;
  noOfCopies?: number;
  bookNo?: number;
  additionalInfo?: string;
  uniqueNo?: number;
  batchId?: number;
  subjectId?: number;
}

export const defaultValue: Readonly<ILibrary> = {};
