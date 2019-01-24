export interface ITeach {
  id?: number;
  desc?: string;
  subjectId?: number;
  teacherId?: number;
}

export const defaultValue: Readonly<ITeach> = {};
