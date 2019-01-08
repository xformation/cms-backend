import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import studentYear, {
  StudentYearState
} from 'app/entities/student-year/student-year.reducer';
// prettier-ignore
import section, {
  SectionState
} from 'app/entities/section/section.reducer';
// prettier-ignore
import semester, {
  SemesterState
} from 'app/entities/semester/semester.reducer';
// prettier-ignore
import studentAttendance, {
  StudentAttendanceState
} from 'app/entities/student-attendance/student-attendance.reducer';
// prettier-ignore
import periods, {
  PeriodsState
} from 'app/entities/periods/periods.reducer';
// prettier-ignore
import subject, {
  SubjectState
} from 'app/entities/subject/subject.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';
// prettier-ignore
import student, {
  StudentState
} from 'app/entities/student/student.reducer';
// prettier-ignore
import collegeBranches, {
  CollegeBranchesState
} from 'app/entities/college-branches/college-branches.reducer';
// prettier-ignore
import departments, {
  DepartmentsState
} from 'app/entities/departments/departments.reducer';
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';
// prettier-ignore
import college, {
  CollegeState
} from 'app/entities/college/college.reducer';
// prettier-ignore
import legalEntity, {
  LegalEntityState
} from 'app/entities/legal-entity/legal-entity.reducer';
// prettier-ignore
import authorizedSignatory, {
  AuthorizedSignatoryState
} from 'app/entities/authorized-signatory/authorized-signatory.reducer';
// prettier-ignore
import bankAccounts, {
  BankAccountsState
} from 'app/entities/bank-accounts/bank-accounts.reducer';
// prettier-ignore
import periods, {
  PeriodsState
} from 'app/entities/periods/periods.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';
// prettier-ignore
import studentAttendance, {
  StudentAttendanceState
} from 'app/entities/student-attendance/student-attendance.reducer';
// prettier-ignore
import academicDepartment, {
  AcademicDepartmentState
} from 'app/entities/academic-department/academic-department.reducer';
// prettier-ignore
import academicSubject, {
  AcademicSubjectState
} from 'app/entities/academic-subject/academic-subject.reducer';
// prettier-ignore
import academicYear, {
  AcademicYearState
} from 'app/entities/academic-year/academic-year.reducer';
// prettier-ignore
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import term, {
  TermState
} from 'app/entities/term/term.reducer';
// prettier-ignore
import studentAttendance, {
  StudentAttendanceState
} from 'app/entities/student-attendance/student-attendance.reducer';
// prettier-ignore
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import term, {
  TermState
} from 'app/entities/term/term.reducer';
// prettier-ignore
import studentAttendance, {
  StudentAttendanceState
} from 'app/entities/student-attendance/student-attendance.reducer';
// prettier-ignore
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import term, {
  TermState
} from 'app/entities/term/term.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly studentYear: StudentYearState;
  readonly section: SectionState;
  readonly semester: SemesterState;
  readonly studentAttendance: StudentAttendanceState;
  readonly periods: PeriodsState;
  readonly subject: SubjectState;
  readonly teacher: TeacherState;
  readonly student: StudentState;
  readonly collegeBranches: CollegeBranchesState;
  readonly departments: DepartmentsState;
  readonly location: LocationState;
  readonly college: CollegeState;
  readonly legalEntity: LegalEntityState;
  readonly authorizedSignatory: AuthorizedSignatoryState;
  readonly bankAccounts: BankAccountsState;
  readonly academicDepartment: AcademicDepartmentState;
  readonly academicSubject: AcademicSubjectState;
  readonly academicYear: AcademicYearState;
  readonly holiday: HolidayState;
  readonly term: TermState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  studentYear,
  section,
  semester,
  studentAttendance,
  periods,
  subject,
  teacher,
  student,
  collegeBranches,
  departments,
  location,
  college,
  legalEntity,
  authorizedSignatory,
  bankAccounts,
  academicDepartment,
  academicSubject,
  academicYear,
  holiday,
  term,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
