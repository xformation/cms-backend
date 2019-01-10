export interface ITeach {
  id?: number;
  desc?: string;
  teacherId?: number;
  subjectId?: number;
}

export const defaultValue: Readonly<ITeach> = {};
