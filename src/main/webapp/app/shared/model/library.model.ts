export interface ILibrary {
  id?: number;
  bookTitle?: string;
  author?: string;
  noOfCopies?: number;
  bookId?: number;
  batchId?: number;
  subjectId?: number;
  departmentId?: number;
}

export const defaultValue: Readonly<ILibrary> = {};
