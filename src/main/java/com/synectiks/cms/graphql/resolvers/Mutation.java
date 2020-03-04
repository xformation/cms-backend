package com.synectiks.cms.graphql.resolvers;

import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.synectiks.cms.business.service.*;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.graphql.types.Contract.AddContractPayload;
import com.synectiks.cms.graphql.types.Student.StudentInput;
import com.synectiks.cms.graphql.types.Student.StudentPayload;
import com.synectiks.cms.graphql.types.TransportRoute.AddTransportRouteInput;
import com.synectiks.cms.graphql.types.TransportRoute.AddTransportRoutePayload;
import com.synectiks.cms.graphql.types.Vehicle.AddVehiclePayload;
import com.synectiks.cms.graphql.types.Insurance.AddInsurancePayload;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.business.service.exam.ExamReportFilterInput;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.enumeration.Frequency;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.exceptions.BranchIdNotFoundException;
import com.synectiks.cms.exceptions.FileNameNotFoundException;
import com.synectiks.cms.exceptions.FilePathNotFoundException;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectMutationPayload;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectProcessor;
import com.synectiks.cms.filter.admissionapplication.AdmissionApplicationProcessor;
import com.synectiks.cms.filter.admissionenquiry.AdmissionEnquiryProcessor;
import com.synectiks.cms.filter.employee.EmployeeFilterProcessor;
import com.synectiks.cms.filter.employee.EmployeeListFilterInput;
import com.synectiks.cms.filter.exam.AcademicExamSettingFilterImpl;
import com.synectiks.cms.filter.exam.AcademicExamSettingUpdateFilter;
import com.synectiks.cms.filter.exam.DailyExamVo;
import com.synectiks.cms.filter.exam.ExamFilterProcessor;
import com.synectiks.cms.filter.exam.ExamListFilterInput;
import com.synectiks.cms.filter.invoice.InvoiceFilterProcessor;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;
import com.synectiks.cms.filter.lecture.LectureScheduleProcessor;
import com.synectiks.cms.filter.library.LibraryFilterInput;
import com.synectiks.cms.filter.library.LibraryFilterProcessor;
import com.synectiks.cms.filter.student.StudentFilterProcessor;
import com.synectiks.cms.filter.student.StudentListFilterInput;
import com.synectiks.cms.filter.studentattendance.DailyAttendanceVo;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterImpl;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterInput;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceUpdateFilter;
import com.synectiks.cms.graphql.types.AcademicExamSetting.AddAcademicExamSettingInput;
import com.synectiks.cms.graphql.types.AcademicExamSetting.AddAcademicExamSettingPayload;
import com.synectiks.cms.graphql.types.AcademicExamSetting.RemoveAcademicExamSettingInput;
import com.synectiks.cms.graphql.types.AcademicExamSetting.RemoveAcademicExamSettingPayload;
import com.synectiks.cms.graphql.types.AcademicExamSetting.UpdateAcademicExamSettingInput;
import com.synectiks.cms.graphql.types.AcademicExamSetting.UpdateAcademicExamSettingPayload;
import com.synectiks.cms.graphql.types.AcademicHistory.AddAcademicHistoryInput;
import com.synectiks.cms.graphql.types.AcademicHistory.AddAcademicHistoryPayload;
import com.synectiks.cms.graphql.types.AcademicHistory.RemoveAcademicHistoryInput;
import com.synectiks.cms.graphql.types.AcademicHistory.RemoveAcademicHistoryPayload;
import com.synectiks.cms.graphql.types.AcademicHistory.UpdateAcademicHistoryInput;
import com.synectiks.cms.graphql.types.AcademicHistory.UpdateAcademicHistoryPayload;
import com.synectiks.cms.graphql.types.AcademicYear.AddAcademicYearInput;
import com.synectiks.cms.graphql.types.AcademicYear.AddAcademicYearPayload;
import com.synectiks.cms.graphql.types.AcademicYear.RemoveAcademicYearInput;
import com.synectiks.cms.graphql.types.AcademicYear.RemoveAcademicYearPayload;
import com.synectiks.cms.graphql.types.AcademicYear.UpdateAcademicYearInput;
import com.synectiks.cms.graphql.types.AcademicYear.UpdateAcademicYearPayload;
import com.synectiks.cms.graphql.types.AdminAttendance.AddAdminAttendanceInput;
import com.synectiks.cms.graphql.types.AdminAttendance.AddAdminAttendancePayLoad;
import com.synectiks.cms.graphql.types.AdminAttendance.RemoveAdminAttendanceInput;
import com.synectiks.cms.graphql.types.AdminAttendance.RemoveAdminAttendancePayLoad;
import com.synectiks.cms.graphql.types.AdminAttendance.UpdateAdminAttendanceInput;
import com.synectiks.cms.graphql.types.AdminAttendance.UpdateAdminAttendancePayLoad;
import com.synectiks.cms.graphql.types.AdmissionApplication.AddAdmissionApplicationInput;
import com.synectiks.cms.graphql.types.AdmissionApplication.AddAdmissionApplicationPayload;
import com.synectiks.cms.graphql.types.AdmissionApplication.RemoveAdmissionApplicationInput;
import com.synectiks.cms.graphql.types.AdmissionApplication.RemoveAdmissionApplicationPayload;
import com.synectiks.cms.graphql.types.AdmissionApplication.UpdateAdmissionApplicationInput;
import com.synectiks.cms.graphql.types.AdmissionApplication.UpdateAdmissionApplicationPayload;
import com.synectiks.cms.graphql.types.AdmissionEnquiry.AddAdmissionEnquiryInput;
import com.synectiks.cms.graphql.types.AdmissionEnquiry.AddAdmissionEnquiryPayload;
import com.synectiks.cms.graphql.types.AdmissionEnquiry.RemoveAdmissionEnquiryInput;
import com.synectiks.cms.graphql.types.AdmissionEnquiry.RemoveAdmissionEnquiryPayload;
import com.synectiks.cms.graphql.types.AdmissionEnquiry.UpdateAdmissionEnquiryInput;
import com.synectiks.cms.graphql.types.AdmissionEnquiry.UpdateAdmissionEnquiryPayload;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.AddAdmissionPersonalDetailsInput;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.RemoveAdmissionPersonalDetailsInput;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.RemoveAdmissionPersonalDetailsPayload;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.UpdateAdmissionPersonalDetailsInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.AddAttendanceMasterInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.AddAttendanceMasterPayload;
import com.synectiks.cms.graphql.types.AttendanceMaster.RemoveAttendanceMasterInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.RemoveAttendanceMasterPayload;
import com.synectiks.cms.graphql.types.AttendanceMaster.UpdateAttendanceMasterInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.UpdateAttendanceMasterPayload;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.AddAuthorizedSignatoryInput;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.AddAuthorizedSignatoryPayload;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.RemoveAuthorizedSignatoryInput;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.RemoveAuthorizedSignatoryPayload;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.UpdateAuthorizedSignatoryInput;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.UpdateAuthorizedSignatoryPayload;
import com.synectiks.cms.graphql.types.BankAccounts.AddBankAccountsInput;
import com.synectiks.cms.graphql.types.BankAccounts.AddBankAccountsPayload;
import com.synectiks.cms.graphql.types.BankAccounts.RemoveBankAccountsInput;
import com.synectiks.cms.graphql.types.BankAccounts.RemoveBankAccountsPayload;
import com.synectiks.cms.graphql.types.BankAccounts.UpdateBankAccountsInput;
import com.synectiks.cms.graphql.types.BankAccounts.UpdateBankAccountsPayload;
import com.synectiks.cms.graphql.types.Batch.AddBatchInput;
import com.synectiks.cms.graphql.types.Batch.AddBatchPayload;
import com.synectiks.cms.graphql.types.Batch.RemoveBatchInput;
import com.synectiks.cms.graphql.types.Batch.RemoveBatchPayload;
import com.synectiks.cms.graphql.types.Batch.UpdateBatchInput;
import com.synectiks.cms.graphql.types.Batch.UpdateBatchPayload;
import com.synectiks.cms.graphql.types.Book.AddBookInput;
import com.synectiks.cms.graphql.types.Book.RemoveBookInput;
import com.synectiks.cms.graphql.types.Book.RemoveBookPayload;
import com.synectiks.cms.graphql.types.Book.UpdateBookInput;
import com.synectiks.cms.graphql.types.Branch.AddBranchInput;
import com.synectiks.cms.graphql.types.Branch.AddBranchPayload;
import com.synectiks.cms.graphql.types.Branch.RemoveBranchInput;
import com.synectiks.cms.graphql.types.Branch.RemoveBranchPayload;
import com.synectiks.cms.graphql.types.Branch.UpdateBranchInput;
import com.synectiks.cms.graphql.types.Branch.UpdateBranchPayload;
import com.synectiks.cms.graphql.types.City.AddCityInput;
import com.synectiks.cms.graphql.types.City.AddCityPayload;
import com.synectiks.cms.graphql.types.City.RemoveCityInput;
import com.synectiks.cms.graphql.types.City.RemoveCityPayload;
import com.synectiks.cms.graphql.types.City.UpdateCityInput;
import com.synectiks.cms.graphql.types.City.UpdateCityPayload;
import com.synectiks.cms.graphql.types.College.AddCollegeInput;
import com.synectiks.cms.graphql.types.College.AddCollegePayload;
import com.synectiks.cms.graphql.types.College.RemoveCollegeInput;
import com.synectiks.cms.graphql.types.College.RemoveCollegePayload;
import com.synectiks.cms.graphql.types.College.UpdateCollegeInput;
import com.synectiks.cms.graphql.types.College.UpdateCollegePayload;
import com.synectiks.cms.graphql.types.CompetitiveExam.AddCompetitiveExamInput;
import com.synectiks.cms.graphql.types.CompetitiveExam.AddCompetitiveExamPayload;
import com.synectiks.cms.graphql.types.CompetitiveExam.RemoveCompetitiveExamInput;
import com.synectiks.cms.graphql.types.CompetitiveExam.RemoveCompetitiveExamPayload;
import com.synectiks.cms.graphql.types.CompetitiveExam.UpdateCompetitiveExamInput;
import com.synectiks.cms.graphql.types.CompetitiveExam.UpdateCompetitiveExamPayload;
import com.synectiks.cms.graphql.types.Contract.AddContractInput;
import com.synectiks.cms.graphql.types.Country.AddCountryInput;
import com.synectiks.cms.graphql.types.Country.AddCountryPayload;
import com.synectiks.cms.graphql.types.Country.RemoveCountryInput;
import com.synectiks.cms.graphql.types.Country.RemoveCountryPayload;
import com.synectiks.cms.graphql.types.Country.UpdateCountryInput;
import com.synectiks.cms.graphql.types.Country.UpdateCountryPayload;
import com.synectiks.cms.graphql.types.Department.AddDepartmentInput;
import com.synectiks.cms.graphql.types.Department.AddDepartmentPayload;
import com.synectiks.cms.graphql.types.Department.RemoveDepartmentInput;
import com.synectiks.cms.graphql.types.Department.RemoveDepartmentPayload;
import com.synectiks.cms.graphql.types.Department.UpdateDepartmentInput;
import com.synectiks.cms.graphql.types.Department.UpdateDepartmentPayload;
import com.synectiks.cms.graphql.types.Documents.AddDocumentsInput;
import com.synectiks.cms.graphql.types.Documents.AddDocumentsPayload;
import com.synectiks.cms.graphql.types.Documents.RemoveDocumentsInput;
import com.synectiks.cms.graphql.types.Documents.RemoveDocumentsPayload;
import com.synectiks.cms.graphql.types.Documents.UpdateDocumentsInput;
import com.synectiks.cms.graphql.types.Documents.UpdateDocumentsPayload;
import com.synectiks.cms.graphql.types.DueDate.AbstractDueDatePayload;
import com.synectiks.cms.graphql.types.DueDate.AddDueDateInput;
import com.synectiks.cms.graphql.types.DueDate.AddDueDatePayload;
import com.synectiks.cms.graphql.types.DueDate.RemoveDueDateInput;
import com.synectiks.cms.graphql.types.DueDate.RemoveDueDatePayload;
import com.synectiks.cms.graphql.types.DueDate.UpdateDueDateInput;
import com.synectiks.cms.graphql.types.DueDate.UpdateDueDatePayload;
import com.synectiks.cms.graphql.types.Employee.AddEmployeeInput;
import com.synectiks.cms.graphql.types.Employee.AddEmployeePayload;
import com.synectiks.cms.graphql.types.Employee.RemoveEmployeeInput;
import com.synectiks.cms.graphql.types.Employee.RemoveEmployeePayload;
import com.synectiks.cms.graphql.types.Employee.UpdateEmployeeInput;
import com.synectiks.cms.graphql.types.Employee.UpdateEmployeePayload;
import com.synectiks.cms.graphql.types.Facility.AddFacilityInput;
import com.synectiks.cms.graphql.types.Facility.AddFacilityPayload;
import com.synectiks.cms.graphql.types.Facility.RemoveFacilityInput;
import com.synectiks.cms.graphql.types.Facility.RemoveFacilityPayload;
import com.synectiks.cms.graphql.types.Facility.UpdateFacilityInput;
import com.synectiks.cms.graphql.types.Facility.UpdateFacilityPayload;
import com.synectiks.cms.graphql.types.FeeCategory.AddFeeCategoryInput;
import com.synectiks.cms.graphql.types.FeeCategory.RemoveFeeCategoryInput;
import com.synectiks.cms.graphql.types.FeeCategory.RemoveFeeCategoryPayload;
import com.synectiks.cms.graphql.types.FeeCategory.UpdateFeeCategoryInput;
import com.synectiks.cms.graphql.types.FeeDetails.AddFeeDetailsInput;
import com.synectiks.cms.graphql.types.FeeDetails.RemoveFeeDetailsInput;
import com.synectiks.cms.graphql.types.FeeDetails.RemoveFeeDetailsPayload;
import com.synectiks.cms.graphql.types.FeeDetails.UpdateFeeDetailsInput;
import com.synectiks.cms.graphql.types.FeeDetails.UpdateFeeDetailsPayload;
import com.synectiks.cms.graphql.types.Holiday.AddHolidayInput;
import com.synectiks.cms.graphql.types.Holiday.AddHolidayPayload;
import com.synectiks.cms.graphql.types.Holiday.RemoveHolidayInput;
import com.synectiks.cms.graphql.types.Holiday.RemoveHolidayPayload;
import com.synectiks.cms.graphql.types.Holiday.UpdateHolidayInput;
import com.synectiks.cms.graphql.types.Holiday.UpdateHolidayPayload;
import com.synectiks.cms.graphql.types.Invoice.AddInvoiceInput;
import com.synectiks.cms.graphql.types.Invoice.AddInvoicePayload;
import com.synectiks.cms.graphql.types.Invoice.RemoveInvoiceInput;
import com.synectiks.cms.graphql.types.Invoice.RemoveInvoicePayload;
import com.synectiks.cms.graphql.types.Invoice.UpdateInvoiceInput;
import com.synectiks.cms.graphql.types.Invoice.UpdateInvoicePayload;
import com.synectiks.cms.graphql.types.LateFee.AbstractLateFeePayload;
import com.synectiks.cms.graphql.types.LateFee.AddLateFeeInput;
import com.synectiks.cms.graphql.types.LateFee.AddLateFeePayload;
import com.synectiks.cms.graphql.types.LateFee.RemoveLateFeeInput;
import com.synectiks.cms.graphql.types.LateFee.RemoveLateFeePayload;
import com.synectiks.cms.graphql.types.LateFee.UpdateLateFeeInput;
import com.synectiks.cms.graphql.types.LateFee.UpdateLateFeePayload;
import com.synectiks.cms.graphql.types.Lecture.AddLectureInput;
import com.synectiks.cms.graphql.types.Lecture.AddLecturePayload;
import com.synectiks.cms.graphql.types.Lecture.RemoveLectureInput;
import com.synectiks.cms.graphql.types.Lecture.RemoveLecturePayload;
import com.synectiks.cms.graphql.types.Lecture.UpdateLectureInput;
import com.synectiks.cms.graphql.types.Lecture.UpdateLecturePayload;
import com.synectiks.cms.graphql.types.LegalEntity.AddLegalEntityInput;
import com.synectiks.cms.graphql.types.LegalEntity.AddLegalEntityPayload;
import com.synectiks.cms.graphql.types.LegalEntity.RemoveLegalEntityInput;
import com.synectiks.cms.graphql.types.LegalEntity.RemoveLegalEntityPayload;
import com.synectiks.cms.graphql.types.LegalEntity.UpdateLegalEntityInput;
import com.synectiks.cms.graphql.types.LegalEntity.UpdateLegalEntityPayload;
import com.synectiks.cms.graphql.types.Library.AddLibraryInput;
import com.synectiks.cms.graphql.types.Library.AddLibraryPayload;
import com.synectiks.cms.graphql.types.Library.RemoveLibraryInput;
import com.synectiks.cms.graphql.types.Library.RemoveLibraryPayload;
import com.synectiks.cms.graphql.types.Library.UpdateLibraryInput;
import com.synectiks.cms.graphql.types.Library.UpdateLibraryPayload;
import com.synectiks.cms.graphql.types.PaymentRemainder.AbstractPaymentRemainderPayload;
import com.synectiks.cms.graphql.types.PaymentRemainder.AddPaymentRemainderInput;
import com.synectiks.cms.graphql.types.PaymentRemainder.AddPaymentRemainderPayload;
import com.synectiks.cms.graphql.types.PaymentRemainder.RemovePaymentRemainderInput;
import com.synectiks.cms.graphql.types.PaymentRemainder.RemovePaymentRemainderPayload;
import com.synectiks.cms.graphql.types.PaymentRemainder.UpdatePaymentRemainderInput;
import com.synectiks.cms.graphql.types.PaymentRemainder.UpdatePaymentRemainderPayload;
import com.synectiks.cms.graphql.types.Section.AddSectionInput;
import com.synectiks.cms.graphql.types.Section.AddSectionPayload;
import com.synectiks.cms.graphql.types.Section.RemoveSectionInput;
import com.synectiks.cms.graphql.types.Section.RemoveSectionPayload;
import com.synectiks.cms.graphql.types.Section.UpdateSectionInput;
import com.synectiks.cms.graphql.types.Section.UpdateSectionPayload;
import com.synectiks.cms.graphql.types.State.AddStateInput;
import com.synectiks.cms.graphql.types.State.AddStatePayload;
import com.synectiks.cms.graphql.types.State.RemoveStateInput;
import com.synectiks.cms.graphql.types.State.RemoveStatePayload;
import com.synectiks.cms.graphql.types.State.UpdateStateInput;
import com.synectiks.cms.graphql.types.State.UpdateStatePayload;
import com.synectiks.cms.graphql.types.StudentAttendance.AddStudentAttendanceInput;
import com.synectiks.cms.graphql.types.StudentAttendance.AddStudentAttendancePayload;
import com.synectiks.cms.graphql.types.StudentAttendance.RemoveStudentAttendanceInput;
import com.synectiks.cms.graphql.types.StudentAttendance.RemoveStudentAttendancePayload;
import com.synectiks.cms.graphql.types.StudentAttendance.UpdateStudentAttendanceInput;
import com.synectiks.cms.graphql.types.StudentAttendance.UpdateStudentAttendancePayload;
import com.synectiks.cms.graphql.types.StudentExamReport.AddStudentExamReportInput;
import com.synectiks.cms.graphql.types.StudentExamReport.AddStudentExamReportPayload;
import com.synectiks.cms.graphql.types.StudentExamReport.RemoveStudentExamReportInput;
import com.synectiks.cms.graphql.types.StudentExamReport.RemoveStudentExamReportPayload;
import com.synectiks.cms.graphql.types.StudentExamReport.UpdateStudentExamReportInput;
import com.synectiks.cms.graphql.types.StudentExamReport.UpdateStudentExamReportPayload;
import com.synectiks.cms.graphql.types.Subject.AddSubjectInput;
import com.synectiks.cms.graphql.types.Subject.AddSubjectPayload;
import com.synectiks.cms.graphql.types.Subject.RemoveSubjectInput;
import com.synectiks.cms.graphql.types.Subject.RemoveSubjectPayload;
import com.synectiks.cms.graphql.types.Subject.UpdateSubjectInput;
import com.synectiks.cms.graphql.types.Subject.UpdateSubjectPayload;
import com.synectiks.cms.graphql.types.Teach.AddTeachInput;
import com.synectiks.cms.graphql.types.Teach.AddTeachPayload;
import com.synectiks.cms.graphql.types.Teach.RemoveTeachInput;
import com.synectiks.cms.graphql.types.Teach.RemoveTeachPayload;
import com.synectiks.cms.graphql.types.Teach.UpdateTeachInput;
import com.synectiks.cms.graphql.types.Teach.UpdateTeachPayload;
import com.synectiks.cms.graphql.types.Teacher.AddTeacherInput;
import com.synectiks.cms.graphql.types.Teacher.AddTeacherPayload;
import com.synectiks.cms.graphql.types.Teacher.RemoveTeacherInput;
import com.synectiks.cms.graphql.types.Teacher.RemoveTeacherPayload;
import com.synectiks.cms.graphql.types.Teacher.UpdateTeacherInput;
import com.synectiks.cms.graphql.types.Teacher.UpdateTeacherPayload;
import com.synectiks.cms.graphql.types.Term.AddTermInput;
import com.synectiks.cms.graphql.types.Term.AddTermPayload;
import com.synectiks.cms.graphql.types.Term.RemoveTermInput;
import com.synectiks.cms.graphql.types.Term.RemoveTermPayload;
import com.synectiks.cms.graphql.types.Term.UpdateTermInput;
import com.synectiks.cms.graphql.types.Term.UpdateTermPayload;
import com.synectiks.cms.graphql.types.TypeOfGrading.AddTypeOfGradingInput;
import com.synectiks.cms.graphql.types.TypeOfGrading.AddTypeOfGradingPayload;
import com.synectiks.cms.graphql.types.TypeOfGrading.RemoveTypeOfGradingInput;
import com.synectiks.cms.graphql.types.TypeOfGrading.RemoveTypeOfGradingPayload;
import com.synectiks.cms.graphql.types.TypeOfGrading.UpdateTypeOfGradingInput;
import com.synectiks.cms.graphql.types.TypeOfGrading.UpdateTypeOfGradingPayload;
import com.synectiks.cms.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.cms.graphql.types.Insurance.AddInsuranceInput;

import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.AcademicHistoryRepository;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.AdminAttendanceRepository;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
import com.synectiks.cms.repository.AdmissionEnquiryRepository;
import com.synectiks.cms.repository.AttendanceMasterRepository;
import com.synectiks.cms.repository.AuthorizedSignatoryRepository;
import com.synectiks.cms.repository.BankAccountsRepository;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.BookRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.CompetitiveExamRepository;
import com.synectiks.cms.repository.ContractRepository;
import com.synectiks.cms.repository.CountryRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.DocumentsRepository;
import com.synectiks.cms.repository.DueDateRepository;
import com.synectiks.cms.repository.EmployeeRepository;
import com.synectiks.cms.repository.FacilityRepository;
import com.synectiks.cms.repository.FeeCategoryRepository;
import com.synectiks.cms.repository.FeeDetailsRepository;
import com.synectiks.cms.repository.HolidayRepository;
import com.synectiks.cms.repository.InsuranceRepository;
import com.synectiks.cms.repository.InvoiceRepository;
import com.synectiks.cms.repository.LateFeeRepository;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.LegalEntityRepository;
import com.synectiks.cms.repository.LibraryRepository;
import com.synectiks.cms.repository.PaymentRemainderRepository;
import com.synectiks.cms.repository.SectionRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.repository.StudentExamReportRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.repository.TermRepository;
import com.synectiks.cms.repository.TransportRouteRepository;
import com.synectiks.cms.repository.TypeOfGradingRepository;
import com.synectiks.cms.repository.VehicleRepository;

import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;



@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    @Autowired
    CommonService commonService;

    @Autowired
    VehicleService vehicleService;
    @Autowired
    InsuranceService insuranceService;

    @Autowired
    ContractService contractService;

    @Autowired
    TransportService transportService;

    @Autowired
    StudentService studentService;

    @Autowired
    private AcademicYearRepository academicYearRepository;
    @Autowired
    private AttendanceMasterRepository attendanceMasterRepository;
    @Autowired
    private AuthorizedSignatoryRepository authorizedSignatoryRepository;
    @Autowired
    private AcademicHistoryRepository academicHistoryRepository;
    @Autowired
    private AcademicExamSettingRepository academicExamSettingRepository;
    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;
    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;
    @Autowired
    private BankAccountsRepository bankAccountsRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LegalEntityRepository legalEntityRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeachRepository teachRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private FeeCategoryRepository feeCategoryRepository;
    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private TransportRouteRepository transportRouteRepository;
    @Autowired
    private FeeDetailsRepository feeDetailsRepository;
    @Autowired
    private DueDateRepository dueDateRepository;
    @Autowired
    private PaymentRemainderRepository paymentRemainderRepository;
    @Autowired
    private LateFeeRepository lateFeeRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CompetitiveExamRepository competitiveExamRepository;
    @Autowired
    private DocumentsRepository documentsRepository;
    @Autowired
    private AdminAttendanceRepository adminAttendanceRepository;
    @Autowired
    private TypeOfGradingRepository typeOfGradingRepository;
    @Autowired
    private StudentExamReportRepository studentExamReportRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StudentAttendanceFilterImpl studentAttendanceFilterImpl;

    @Autowired
    private AcademicExamSettingFilterImpl academicExamSettingFilterImpl;

    @Autowired
    private LectureScheduleProcessor lectureScheduleProcessor;

    @Autowired
    private AcademicSubjectProcessor academicSubjectProcessor;

    @Autowired
    private InvoiceFilterProcessor invoiceFilterProcessor;
    @Autowired
    private EmployeeFilterProcessor employeeFilterProcessor;

    @Autowired
    private AdmissionEnquiryProcessor admissionEnquiryProcessor;

    @Autowired
    private ExamFilterProcessor examFilterProcessor;

	@Autowired
    private StudentFilterProcessor studentFilterProcessor;

    @Autowired
    private AdmissionApplicationProcessor admissionApplicationProcessor;

    @Autowired
    private LibraryFilterProcessor libraryFilterProcessor;

//    public Mutation(AcademicExamSettingRepository academicExamSettingRepository,BookRepository bookRepository, AdminAttendanceRepository adminAttendanceRepository, AcademicHistoryRepository academicHistoryRepository, AdmissionEnquiryRepository admissionEnquiryRepository, CountryRepository countryRepository, LectureRepository lectureRepository, AttendanceMasterRepository attendanceMasterRepository, AdmissionApplicationRepository admissionApplicationRepository, TeachRepository teachRepository, BatchRepository batchRepository, StudentRepository studentRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicYearRepository academicYearRepository, HolidayRepository holidayRepository, TermRepository termRepository, CityRepository cityRepository, StateRepository stateRepository, FeeCategoryRepository feeCategoryRepository, FacilityRepository facilityRepository, TransportRouteRepository transportRouteRepository, FeeDetailsRepository feeDetailsRepository, DueDateRepository dueDateRepository, PaymentRemainderRepository paymentRemainderRepository, LateFeeRepository lateFeeRepository, InvoiceRepository invoiceRepository, CompetitiveExamRepository competitiveExamRepository, DocumentsRepository documentsRepository, TypeOfGradingRepository typeOfGradingRepository, StudentExamReportRepository studentExamReportRepository, LibraryRepository libraryRepository, VehicleRepository vehicleRepository, EmployeeRepository employeeRepository, ContractRepository contractRepository, InsuranceRepository insuranceRepository, EntityManager entityManager) {
//        this.academicExamSettingRepository = academicExamSettingRepository;
//        this.academicHistoryRepository = academicHistoryRepository;
//        this.admissionEnquiryRepository = admissionEnquiryRepository;
//        this.admissionApplicationRepository = admissionApplicationRepository;
//        this.batchRepository = batchRepository;
//        this.studentRepository = studentRepository;
////        this.instituteRepository = instituteRepository;
//        this.collegeRepository = collegeRepository;
////        this.studentYearRepository = studentYearRepository;
////        this.semesterRepository = semesterRepository;
//        this.branchRepository = branchRepository;
//        this.sectionRepository = sectionRepository;
//        this.subjectRepository = subjectRepository;
//        this.teacherRepository = teacherRepository;
//        this.legalEntityRepository = legalEntityRepository;
//        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
//        this.bankAccountsRepository = bankAccountsRepository;
//        this.departmentRepository = departmentRepository;
//        this.locationRepository = locationRepository;
//        this.studentAttendanceRepository = studentAttendanceRepository;
//        this.academicYearRepository = academicYearRepository;
//        this.holidayRepository = holidayRepository;
//        this.termRepository = termRepository;
//        this.teachRepository = teachRepository;
//        this.attendanceMasterRepository = attendanceMasterRepository;
//        this.lectureRepository = lectureRepository;
//        this.cityRepository = cityRepository;
//        this.stateRepository = stateRepository;
//        this.countryRepository = countryRepository;
//        this.feeCategoryRepository = feeCategoryRepository;
//        this.facilityRepository = facilityRepository;
//        this.transportRouteRepository = transportRouteRepository;
//        this.feeDetailsRepository = feeDetailsRepository;
//        this.dueDateRepository = dueDateRepository;
//        this.paymentRemainderRepository = paymentRemainderRepository;
//        this.lateFeeRepository = lateFeeRepository;
//        this.invoiceRepository = invoiceRepository;
//        this.competitiveExamRepository = competitiveExamRepository;
//        this.documentsRepository = documentsRepository;
//        this.adminAttendanceRepository = adminAttendanceRepository;
//        this.typeOfGradingRepository = typeOfGradingRepository;
//        this.studentExamReportRepository = studentExamReportRepository;
//        this.libraryRepository = libraryRepository;
//        this.bookRepository = bookRepository;
//
//        this.vehicleRepository = vehicleRepository;
//        this.employeeRepository = employeeRepository;
//        this.contractRepository = contractRepository;
//        this.insuranceRepository = insuranceRepository;
//        this.entityManager = entityManager;
//
//    }

    public StudentPayload saveStudent(StudentInput cmsStudentVo) {
    CmsStudentVo vo = this.studentService.addStudent(cmsStudentVo);
    return new StudentPayload(vo);
    }

    public AddVehiclePayload addVehicle(AddVehicleInput cmsVehicleVo) {
        CmsVehicleVo vo = this.vehicleService.addVehicle(cmsVehicleVo);
        return new AddVehiclePayload(vo);
    }
    public AddInsurancePayload addInsurance(AddInsuranceInput cmsInsuranceVo) {
        CmsInsuranceVo vo = this.insuranceService.addInsurance(cmsInsuranceVo);
        return new AddInsurancePayload(vo);
    }
    public AddContractPayload addContract(AddContractInput cmsContractVo) {
        CmsContractVo vo = this.contractService.addContract(cmsContractVo);
        return new AddContractPayload(vo);
    }

    public AddTransportRoutePayload addTransportRoute(AddTransportRouteInput cmsTransportVo) {
        CmsTransportVo vo = this.transportService.addTransportRoute(cmsTransportVo);
        return new AddTransportRoutePayload(vo);
    }

    public AddCountryPayload addCountry(AddCountryInput addCountryInput) {
        final Country country = new Country();
        country.setCountryName(addCountryInput.getCountryName());
        country.setCountryCode(addCountryInput.getCountryCode());
        country.setIsdCode(addCountryInput.getIsdCode());
        countryRepository.save(country);
        return new AddCountryPayload(country);
    }

    public UpdateCountryPayload updateCountry(UpdateCountryInput updateCountryInput) {
        Country country = countryRepository.findById(updateCountryInput.getId()).get();
        if (updateCountryInput.getCountryName() != null) {
            country.setCountryName(updateCountryInput.getCountryName());
        }
        if (updateCountryInput.getCountryCode() != null) {
            country.setCountryCode(updateCountryInput.getCountryCode());
        }
        if (updateCountryInput.getIsdCode() != null) {
            country.setIsdCode(updateCountryInput.getIsdCode());
        }

        countryRepository.save(country);

        return new UpdateCountryPayload(country);
    }

    public RemoveCountryPayload removeCountry(RemoveCountryInput removeCountryInput) {
        Country country = countryRepository.findById(removeCountryInput.getCountryId()).get();
        countryRepository.delete(country);
        return new RemoveCountryPayload(Lists.newArrayList(countryRepository.findAll()));
    }

    public AddCityPayload addCity(AddCityInput addCityInput) {
        final State state = stateRepository.findById(addCityInput.getStateId()).get();

        final City city = new City();
        city.setState(state);
        city.setCityName(addCityInput.getCityName());
        city.setCityCode(addCityInput.getCityCode());
        city.setStdCode(addCityInput.getStdCode());
        cityRepository.save(city);
        return new AddCityPayload(city);
    }

    public UpdateCityPayload updateCity(UpdateCityInput updateCityInput) {
        City city = cityRepository.findById(updateCityInput.getId()).get();
        if (updateCityInput.getCityName() != null) {
            city.setCityName(updateCityInput.getCityName());
        }
        if (updateCityInput.getCityCode() != null) {
            city.setCityCode(updateCityInput.getCityCode());
        }
        if (updateCityInput.getStdCode() != null) {
            city.setStdCode(updateCityInput.getStdCode());
        }

        if (updateCityInput.getStateId() != null) {
            final State state = stateRepository.findById(updateCityInput.getStateId()).get();
            city.setState(state);
        }
        cityRepository.save(city);

        return new UpdateCityPayload(city);
    }

    public RemoveCityPayload removeCity(RemoveCityInput removeCityInput) {
        City city = cityRepository.findById(removeCityInput.getCityId()).get();
        cityRepository.delete(city);
        return new RemoveCityPayload(Lists.newArrayList(cityRepository.findAll()));
    }

    public AddStatePayload addState(AddStateInput addStateInput) {
        final Country country = countryRepository.findById(addStateInput.getCountryId()).get();

        final State state = new State();
        state.setCountry(country);
        state.setStateName(addStateInput.getStateName());
        state.setDivisionType(addStateInput.getDivisionType());
        state.setStateCode(addStateInput.getStateCode());
        stateRepository.save(state);
        return new AddStatePayload(state);
    }

    public UpdateStatePayload updateState(UpdateStateInput updateStateInput) {
        State state = stateRepository.findById(updateStateInput.getId()).get();

        if (updateStateInput.getStateName() != null) {
            state.setStateName(updateStateInput.getStateName());
        }
        if (updateStateInput.getDivisionType() != null) {
            state.setDivisionType(updateStateInput.getDivisionType());
        }
        if (updateStateInput.getStateCode() != null) {
            state.setStateCode(updateStateInput.getStateCode());
        }

        if (updateStateInput.getCountryId() != null) {
            final Country country = countryRepository.findById(updateStateInput.getCountryId()).get();
            state.setCountry(country);
        }
        stateRepository.save(state);

        return new UpdateStatePayload(state);
    }

    public RemoveStatePayload removeState(RemoveStateInput removeStateInput) {
        State state = stateRepository.findById(removeStateInput.getStateId()).get();
        stateRepository.delete(state);
        return new RemoveStatePayload(Lists.newArrayList(stateRepository.findAll()));
    }

    public AddAdmissionApplicationPayload addAdmissionApplication(AddAdmissionApplicationInput input) {

    	AdmissionApplication admissionApplication = CommonUtil.createCopyProperties(input, AdmissionApplication.class);

    	if(input.getAdmissionEnquiryId() != null) {
    		Optional<AdmissionEnquiry> admissionEnquiry = admissionEnquiryRepository.findById(input.getAdmissionEnquiryId());
    		admissionApplication.setAdmissionEnquiry(admissionEnquiry.isPresent() ? admissionEnquiry.get() : null);
    	}
    	if(input.getAcademicHistoryId() != null) {
    		Optional<AcademicHistory> academicHistory = academicHistoryRepository.findById(input.getAcademicHistoryId());
    		admissionApplication.setAcademicHistory(academicHistory.isPresent() ? academicHistory.get() : null);
    	}
    	if(input.getDocumentsId() != null) {
    		Optional<Documents> documents = documentsRepository.findById(input.getDocumentsId());
    		admissionApplication.setDocuments(documents.isPresent() ? documents.get() : null);
    	}
    	if(input.getBranchId() != null) {
    		Optional<Branch> branch = branchRepository.findById(input.getBranchId());
    		admissionApplication.setBranch(branch.isPresent() ? branch.get() : null);
    	}
    	if(input.getBatchId() != null) {
    		Optional<Batch> batch = batchRepository.findById(input.getBatchId());
    		admissionApplication.setBatch(batch.isPresent() ? batch.get() : null);
    	}
    	if(input.getStateId() != null) {
    		Optional<State> state = stateRepository.findById(input.getStateId());
    		admissionApplication.setState(state.isPresent() ? state.get() : null);
    	}
    	if(input.getCityId() != null) {
    		Optional<City> city = cityRepository.findById(input.getCityId());
    		admissionApplication.setCity(city.isPresent() ? city.get() : null);
    	}
    	if(input.getCountryId() != null) {
    		Optional<Country> country = countryRepository.findById(input.getCountryId());
    		admissionApplication.setCountry(country.isPresent() ? country.get() : null);
    	}
    	if(input.getDepartmentId() != null) {
    		Optional<Department> department = departmentRepository.findById(input.getDepartmentId());
    		admissionApplication.setDepartment(department.isPresent() ? department.get() : null);
    	}
    	if(input.getAcademicyearId() != null) {
    		Optional<AcademicYear> academicYear = academicYearRepository.findById(input.getAcademicyearId());
    		admissionApplication.setAcademicyear(academicYear.isPresent() ? academicYear.get() : null);
    	}

        admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(input.getDateOfBirth()));
        admissionApplication.setAdmissionDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getAdmissionDate()));
        admissionApplication.setStatus(input.getStatus());
        admissionApplicationRepository.save(admissionApplication);
        return new AddAdmissionApplicationPayload(admissionApplication);
    }

    public UpdateAdmissionApplicationPayload updateAdmissionApplication(UpdateAdmissionApplicationInput input) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(input.getId()).get();


        if (input.getAdmissionStatus() != null) {
            admissionApplication.setAdmissionStatus(input.getAdmissionStatus());
        }
        if (input.getStudentName() != null) {
            admissionApplication.setStudentName(input.getStudentName());
        }
        if (input.getStudentMiddleName() != null) {
            admissionApplication.setStudentMiddleName(input.getStudentMiddleName());
        }
        if (input.getStudentLastName() != null) {
            admissionApplication.setStudentLastName(input.getStudentLastName());
        }
        if (input.getFatherName() != null) {
            admissionApplication.setFatherName(input.getFatherName());
        }
        if (input.getFatherMiddleName() != null) {
            admissionApplication.setFatherMiddleName(input.getFatherMiddleName());
        }
        if (input.getFatherLastName() != null) {
            admissionApplication.setFatherLastName(input.getFatherLastName());
        }
        if (input.getMotherName() != null) {
            admissionApplication.setMotherName(input.getMotherName());
        }
        if (input.getMotherMiddleName() != null) {
            admissionApplication.setMotherMiddleName(input.getMotherMiddleName());
        }
        if (input.getMotherLastName() != null) {
            admissionApplication.setMotherLastName(input.getMotherLastName());
        }
        if (input.getContactNumber() != null) {
            admissionApplication.setContactNumber(input.getContactNumber());
        }
        if (input.getAlternateMobileNumber() != null) {
            admissionApplication.setAlternateMobileNumber(input.getAlternateMobileNumber());
        }
        if (input.getDateOfBirth() != null) {
            admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(input.getDateOfBirth()));
        }
        if (input.getEmail() != null) {
            admissionApplication.setEmail(input.getEmail());
        }
        if (input.getSex() != null) {
            admissionApplication.setSex(input.getSex());
        }
        if (input.getComments() != null) {
            admissionApplication.setComments(input.getComments());
        }
        if (input.getApplicationId() != null) {
            admissionApplication.setApplicationId(input.getApplicationId());
        }
        if (input.getUploadPhoto() != null) {
            admissionApplication.setUploadPhoto(input.getUploadPhoto());
        }

        if (input.getCourse() != null) {
            admissionApplication.setCourse(input.getCourse());
        }
        if (input.getAdmissionDate() != null) {
            admissionApplication.setAdmissionDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getAdmissionDate()));
        }
        if (input.getAdmissionEnquiryId() != null) {
            Optional<AdmissionEnquiry> admissionEnquiry = admissionEnquiryRepository.findById(input.getAdmissionEnquiryId());
            admissionApplication.setAdmissionEnquiry(admissionEnquiry.isPresent() ? admissionEnquiry.get() : null);
        }
        if (input.getDocumentsId() != null) {
            Optional<Documents> documents = documentsRepository.findById(input.getDocumentsId());
            admissionApplication.setDocuments(documents.isPresent() ? documents.get() : null);
        }
        if (input.getAcademicHistoryId() != null) {
            Optional<AcademicHistory> academicHistory = academicHistoryRepository.findById(input.getAcademicHistoryId());
            admissionApplication.setAcademicHistory(academicHistory.isPresent() ? academicHistory.get() : null);
        }
        if (input.getBranchId() != null) {
            Optional<Branch> branch = branchRepository.findById(input.getBranchId());
            admissionApplication.setBranch(branch.isPresent() ? branch.get() : null);
        }
        if (input.getBatchId() != null) {
            Optional<Batch> batch = batchRepository.findById(input.getBatchId());
            admissionApplication.setBatch(batch.isPresent() ? batch.get() : null);
        }
        if (input.getStateId() != null) {
            Optional<State> state = stateRepository.findById(input.getStateId());
            admissionApplication.setState(state.isPresent() ? state.get() : null);
        }
        if (input.getCityId() != null) {
            Optional<City> city = cityRepository.findById(input.getCityId());
            admissionApplication.setCity(city.isPresent() ? city.get() : null);
        }
        if (input.getCountryId() != null) {
            Optional<Country> country = countryRepository.findById(input.getCountryId());
            admissionApplication.setCountry(country.isPresent() ? country.get() : null);
        }
        if (input.getDepartmentId() != null) {
            Optional<Department> department = departmentRepository.findById(input.getDepartmentId());
            admissionApplication.setDepartment(department.isPresent() ? department.get() : null);
        }
        if (input.getAcademicyearId() != null) {
            Optional<AcademicYear> academicYear = academicYearRepository.findById(input.getAcademicyearId());
            admissionApplication.setAcademicyear(academicYear.isPresent() ? academicYear.get() : null);
        }
        if (input.getStatus() != null) {
            admissionApplication.setStatus(input.getStatus());
        }
        admissionApplicationRepository.save(admissionApplication);

        return new UpdateAdmissionApplicationPayload(admissionApplication);
    }

    public RemoveAdmissionApplicationPayload removeAdmissionApplication(RemoveAdmissionApplicationInput removeAdmissionApplicationsInput) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(removeAdmissionApplicationsInput.getAdmissionApplicationId()).get();
        admissionApplicationRepository.delete(admissionApplication);
        return new RemoveAdmissionApplicationPayload(Lists.newArrayList(admissionApplicationRepository.findAll()));
    }
//    public AddTypeOfGradingPayload addTypeOfGrading(List<AddTypeOfGradingInput> list) {
//        TypeOfGrading typeOfGrading = null;
//        // get the max id from database.
//        int groupvalue = getNextGradeId()+1;
//        for (AddTypeOfGradingInput input : list) {
//            typeOfGrading = CommonUtil.createCopyProperties(input, TypeOfGrading.class);
//            typeOfGrading.setGroupvalue(new Long(groupvalue));
//            this.typeOfGradingRepository.save(typeOfGrading);
//        }
//        return new AddTypeOfGradingPayload(typeOfGrading);
//    }
//
//    private int getNextGradeId(){
//        String sql = "select max(groupvalue_id) from type_of_grading";
//        Query query = this.entityManager.createNativeQuery(sql);
//        Object groupValue = query.getSingleResult();
//        return ((BigInteger)groupValue).intValue() ;
//    }
//public AddTypeOfGradingPayload addTypeOfGrading(List<AddTypeOfGradingInput> list) {
//        TypeOfGrading typeOfGrading = null;
//        // get the max id from database.
//        int groupvalue = getNextGradeId()+1;
//        for (AddTypeOfGradingInput input : list) {
//            typeOfGrading = CommonUtil.createCopyProperties(input, TypeOfGrading.class);
//            typeOfGrading.setGroupvalue(new Long(groupvalue));
//            this.typeOfGradingRepository.save(typeOfGrading);
//        }
//        return new AddTypeOfGradingPayload(typeOfGrading);
//    }
    public AddAcademicExamSettingPayload addAcademicExamSetting(List<AddAcademicExamSettingInput> list) {
        AcademicExamSetting academicExamSetting = null;
        int countvalue = getCountvalueId()+1;
        for(AddAcademicExamSettingInput input: list ) {
//            Branch branch = this.commonService.getBranchById(input.getBranchId()); // branchRepository.findById(input.getBranchId()).get();
//            Subject subject = this.commonService.getSubjectById(input.getSubjectId());//subjectRepository.findById(input.getSubjectId()).get();
//            AcademicYear academicYear = this.commonService.getAcademicYearById(input.getAcademicyearId()); // academicYearRepository.findById(input.getAcademicyearId()).get();
//            Batch batch = this.commonService.getBatchById(input.getBatchId()); //batchRepository.findById(input.getBatchId()).get();
//            Section section = this.commonService.getSectionById(input.getSectionId());  //sectionRepository.findById(input.getSectionId()).get();
//            Department department = this.commonService.getDepartmentById(input.getDepartmentId()); //departmentRepository.findById(input.getDepartmentId()).get();

        	academicExamSetting = CommonUtil.createCopyProperties(input, AcademicExamSetting.class);
        	if("GRADE".equalsIgnoreCase(input.getGradeType().toString())) {
            	Optional<TypeOfGrading> otg = typeOfGradingRepository.findById((input.getTypeOfGradingId()));
            	if(otg.isPresent()) {
            		academicExamSetting.setTypeOfGrading(otg.get());
            	}
            }


            LocalDate exmDt =  DateFormatUtil.convertLocalDateFromUtilDate(input.getExamDate());
            academicExamSetting.setExamDate(exmDt);
            academicExamSetting.setCountvalue(new Long(countvalue));
            academicExamSetting.setBranchId(input.getBranchId());
            academicExamSetting.setSubjectId(input.getSubjectId());
            academicExamSetting.setBatchId(input.getBatchId());
            academicExamSetting.setAcademicyearId(input.getAcademicyearId());
            academicExamSetting.setSectionId(input.getSectionId());
            academicExamSetting.setDepartmentId(input.getDepartmentId());

            academicExamSetting.setExamDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getExamDate()));
            this.academicExamSettingRepository.save(academicExamSetting);

        }
        return  new AddAcademicExamSettingPayload(academicExamSetting);
    }
    private int getCountvalueId(){
        String sql = "select max(countvalue) from academic_exam_setting";
        Query query = this.entityManager.createNativeQuery(sql);
        Object countvalue = query.getSingleResult();
        if(countvalue == null) {
        	return 0;
        }
        return ((BigInteger)countvalue).intValue() ;
    }

    public UpdateAcademicExamSettingPayload updateAcademicExamSetting(UpdateAcademicExamSettingInput updateAcademicExamSettingInput) {
        AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(updateAcademicExamSettingInput.getId()).get();


        if (updateAcademicExamSettingInput.getExamName() != null) {
            academicExamSetting.setExamName(updateAcademicExamSettingInput.getExamName());
        }
        if (updateAcademicExamSettingInput.getSemester() != null) {
            academicExamSetting.setSemester(updateAcademicExamSettingInput.getSemester());
        }
        if (updateAcademicExamSettingInput.getExamDate() != null) {
            academicExamSetting.setExamDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAcademicExamSettingInput.getExamDate()));
        }
        if (updateAcademicExamSettingInput.getStartTime() != null) {
            academicExamSetting.setStartTime(updateAcademicExamSettingInput.getStartTime());
        }
        if (updateAcademicExamSettingInput.getEndTime() != null) {
            academicExamSetting.setEndTime(updateAcademicExamSettingInput.getEndTime());
        }
        if (updateAcademicExamSettingInput.getGradeType() != null) {
            academicExamSetting.setGradeType(updateAcademicExamSettingInput.getGradeType());
        }
        if (updateAcademicExamSettingInput.getTotal() != null) {
            academicExamSetting.setTotal(updateAcademicExamSettingInput.getTotal());
        }
        if (updateAcademicExamSettingInput.getPassing() != null) {
            academicExamSetting.setPassing(updateAcademicExamSettingInput.getPassing());
        }
        if (updateAcademicExamSettingInput.getActions() != null) {
            academicExamSetting.setActions(updateAcademicExamSettingInput.getActions());
        }
        if (updateAcademicExamSettingInput.getDepartmentId() != null) {
//            final Department department = departmentRepository.findById(updateAcademicExamSettingInput.getDepartmentId()).get();
            academicExamSetting.setDepartmentId(updateAcademicExamSettingInput.getDepartmentId());
        }
        if (updateAcademicExamSettingInput.getAcademicyearId() != null) {
//            final AcademicYear academicYear = academicYearRepository.findById(updateAcademicExamSettingInput.getAcademicyearId()).get();
            academicExamSetting.setAcademicyearId(updateAcademicExamSettingInput.getAcademicyearId());
        }
        if (updateAcademicExamSettingInput.getSubjectId() != null) {
//            final Subject subject = subjectRepository.findById(updateAcademicExamSettingInput.getSubjectId()).get();
            academicExamSetting.setSubjectId(updateAcademicExamSettingInput.getSubjectId());
        }
        if (updateAcademicExamSettingInput.getSectionId() != null) {
//            final Section section = sectionRepository.findById(updateAcademicExamSettingInput.getSectionId()).get();
            academicExamSetting.setSectionId(updateAcademicExamSettingInput.getSectionId());
        }
        if (updateAcademicExamSettingInput.getBatchId() != null) {
//            final Batch batch = batchRepository.findById(updateAcademicExamSettingInput.getBatchId()).get();
            academicExamSetting.setBatchId(updateAcademicExamSettingInput.getBatchId());
        }
        if (updateAcademicExamSettingInput.getBranchId() != null) {
//            final Branch branch = branchRepository.findById(updateAcademicExamSettingInput.getBranchId()).get();
            academicExamSetting.setBranchId(updateAcademicExamSettingInput.getBranchId());
        }
        if (updateAcademicExamSettingInput.getTypeOfGradingId() != null) {
            final TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(updateAcademicExamSettingInput.getTypeOfGradingId()).get();
            academicExamSetting.setTypeOfGrading(typeOfGrading);
        }

//        if (updateAcademicExamSettingInput.getCountvalue() != null) {
//            academicExamSetting.setCountvalue(updateAcademicExamSettingInput.getCountvalue());
//        }


        academicExamSettingRepository.save(academicExamSetting);

        return new UpdateAcademicExamSettingPayload(academicExamSetting);
    }

    public RemoveAcademicExamSettingPayload removeAcademicExamSetting(RemoveAcademicExamSettingInput removeAcademicExamSettingsInput) {
        AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(removeAcademicExamSettingsInput.getAcademicExamSettingId()).get();
        academicExamSettingRepository.delete(academicExamSetting);
        return new RemoveAcademicExamSettingPayload(Lists.newArrayList(academicExamSettingRepository.findAll()));
    }

    public AddAcademicHistoryPayload addAcademicHistory(AddAcademicHistoryInput addAcademicHistoryInput) {
        final Student student = studentRepository.findById(addAcademicHistoryInput.getStudentId()).get();

        final AcademicHistory academicHistory = new AcademicHistory();
        academicHistory.setStudent(student);
        academicHistory.setQualification(addAcademicHistoryInput.getQualification());
        academicHistory.setYearOfPassing(addAcademicHistoryInput.getYearOfPassing());
        academicHistory.setInstitution(addAcademicHistoryInput.getInstitution());
        academicHistory.setUniversity(addAcademicHistoryInput.getUniversity());
        academicHistory.setEnrollmentNo(addAcademicHistoryInput.getEnrollmentNo());
        academicHistory.setScore(addAcademicHistoryInput.getScore());
        academicHistory.setPercentage(addAcademicHistoryInput.getPercentage());
        academicHistoryRepository.save(academicHistory);
        return new AddAcademicHistoryPayload(academicHistory);
    }

    public UpdateAcademicHistoryPayload updateAcademicHistory(UpdateAcademicHistoryInput updateAcademicHistoryInput) {
        AcademicHistory academicHistory = academicHistoryRepository.findById(updateAcademicHistoryInput.getId()).get();
        if (updateAcademicHistoryInput.getQualification() != null) {
            academicHistory.setQualification(updateAcademicHistoryInput.getQualification());
        }
        if (updateAcademicHistoryInput.getYearOfPassing() != null) {
            academicHistory.setYearOfPassing(updateAcademicHistoryInput.getYearOfPassing());
        }
        if (updateAcademicHistoryInput.getInstitution() != null) {
            academicHistory.setInstitution(updateAcademicHistoryInput.getInstitution());
        }
        if (updateAcademicHistoryInput.getUniversity() != null) {
            academicHistory.setUniversity(updateAcademicHistoryInput.getUniversity());
        }
        if (updateAcademicHistoryInput.getEnrollmentNo() != null) {
            academicHistory.setEnrollmentNo(updateAcademicHistoryInput.getEnrollmentNo());
        }
        if (updateAcademicHistoryInput.getScore() != null) {
            academicHistory.setScore(updateAcademicHistoryInput.getScore());
        }
        if (updateAcademicHistoryInput.getPercentage() != null) {
            academicHistory.setPercentage(updateAcademicHistoryInput.getPercentage());
        }

        if (updateAcademicHistoryInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateAcademicHistoryInput.getStudentId()).get();
            academicHistory.setStudent(student);
        }
        academicHistoryRepository.save(academicHistory);

        return new UpdateAcademicHistoryPayload(academicHistory);
    }

    public RemoveAcademicHistoryPayload removeAcademicHistory(RemoveAcademicHistoryInput removeAcademicHistoryInput) {
        AcademicHistory academicHistory = academicHistoryRepository.findById(removeAcademicHistoryInput.getAcademicHistoryId()).get();
        academicHistoryRepository.delete(academicHistory);
        return new RemoveAcademicHistoryPayload(Lists.newArrayList(academicHistoryRepository.findAll()));
    }

    public AddTypeOfGradingPayload addTypeOfGrading(List<AddTypeOfGradingInput> list) {
        TypeOfGrading typeOfGrading = null;
        // get the max id from database.
        int groupvalue = getNextGradeId()+1;
        for (AddTypeOfGradingInput input : list) {
            typeOfGrading = CommonUtil.createCopyProperties(input, TypeOfGrading.class);
            typeOfGrading.setGroupvalue(new Long(groupvalue));
            this.typeOfGradingRepository.save(typeOfGrading);
        }
            return new AddTypeOfGradingPayload(typeOfGrading);
    }

    private int getNextGradeId(){
        String sql = "select max(groupvalue) from type_of_grading";
        Query query = this.entityManager.createNativeQuery(sql);
        Object groupValue = query.getSingleResult();
        if(groupValue == null) {
        	return 0;
        }
        return ((BigInteger)groupValue).intValue() ;
    }

    public UpdateTypeOfGradingPayload updateTypeOfGrading(UpdateTypeOfGradingInput updateTypeOfGradingInput) {
        TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(updateTypeOfGradingInput.getId()).get();
        if (updateTypeOfGradingInput.getMinMarks() != null) {
            typeOfGrading.setMinMarks(updateTypeOfGradingInput.getMinMarks());
        }
        if (updateTypeOfGradingInput.getMaxMarks() != null) {
            typeOfGrading.setMaxMarks(updateTypeOfGradingInput.getMaxMarks());
        }
        if (updateTypeOfGradingInput.getGrades() != null) {
            typeOfGrading.setGrades(updateTypeOfGradingInput.getGrades());
        }
        typeOfGradingRepository.save(typeOfGrading);

        return new UpdateTypeOfGradingPayload(typeOfGrading);

    }

    public RemoveTypeOfGradingPayload removeTypeOfGrading(RemoveTypeOfGradingInput removeTypeOfGradingInput) {
        TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(removeTypeOfGradingInput.getTypeOfGradingId()).get();
        typeOfGradingRepository.delete(typeOfGrading);
        return new RemoveTypeOfGradingPayload(Lists.newArrayList(typeOfGradingRepository.findAll()));
    }


    public AddAdmissionEnquiryPayload addAdmissionEnquiry(AddAdmissionEnquiryInput addAdmissionEnquiryInput) {
        final AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        admissionEnquiry.setStudentName(addAdmissionEnquiryInput.getStudentName());
        admissionEnquiry.setStudentMiddleName(addAdmissionEnquiryInput.getStudentMiddleName());
        admissionEnquiry.setStudentLastName(addAdmissionEnquiryInput.getStudentLastName());
        admissionEnquiry.setFatherName(addAdmissionEnquiryInput.getFatherName());
        admissionEnquiry.setFatherMiddleName(addAdmissionEnquiryInput.getFatherMiddleName());
        admissionEnquiry.setFatherLastName(addAdmissionEnquiryInput.getFatherLastName());
        admissionEnquiry.setMotherName(addAdmissionEnquiryInput.getMotherName());
        admissionEnquiry.setMotherMiddleName(addAdmissionEnquiryInput.getMotherMiddleName());
        admissionEnquiry.setMotherLastName(addAdmissionEnquiryInput.getMotherLastName());
        admissionEnquiry.setContactNumber(addAdmissionEnquiryInput.getContactNumber());
        admissionEnquiry.setAlternateMobileNumber(addAdmissionEnquiryInput.getAlternateMobileNumber());
        admissionEnquiry.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionEnquiryInput.getDateOfBirth()));
        admissionEnquiry.setEmail(addAdmissionEnquiryInput.getEmail());
        admissionEnquiry.setSex(addAdmissionEnquiryInput.getSex());
        admissionEnquiry.setComments(addAdmissionEnquiryInput.getComments());
        admissionEnquiry.setCourseApplyingFor(addAdmissionEnquiryInput.getCourseApplyingFor());
        admissionEnquiry.setHighestQualification(addAdmissionEnquiryInput.getHighestQualification());
        admissionEnquiry.setModeOfEnquiry(addAdmissionEnquiryInput.getModeOfEnquiry());
        admissionEnquiry.setStatus(addAdmissionEnquiryInput.getStatus());
        admissionEnquiry.setDescription(addAdmissionEnquiryInput.getDescription());
        admissionEnquiry.setEnquiryDate(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionEnquiryInput.getEnquiryDate()));
        admissionEnquiry.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionEnquiryInput.getUpdatedOn()));
        admissionEnquiry.setUpdatedBy(addAdmissionEnquiryInput.getUpdatedBy());
        Branch branch = branchRepository.findById(addAdmissionEnquiryInput.getBranchId()).get();
        Department department = departmentRepository.findById(addAdmissionEnquiryInput.getDepartmentId()).get();
        State state = stateRepository.findById(addAdmissionEnquiryInput.getStateId()).get();
        City city = cityRepository.findById(addAdmissionEnquiryInput.getCityId()).get();
        Country country = countryRepository.findById(addAdmissionEnquiryInput.getCountryId()).get();
        Batch batch = batchRepository.findById(addAdmissionEnquiryInput.getBatchId()).get();
        admissionEnquiry.setBranch(branch);
        admissionEnquiry.setDepartment(department);
        admissionEnquiry.setBatch(batch);
        admissionEnquiry.setState(state);
        admissionEnquiry.setCity(city);
        admissionEnquiry.setCountry(country);
        admissionEnquiryRepository.save(admissionEnquiry);
        return new AddAdmissionEnquiryPayload(admissionEnquiry);
    }


    public UpdateAdmissionEnquiryPayload updateAdmissionEnquiry(UpdateAdmissionEnquiryInput updateAdmissionEnquiryInput) {
        AdmissionEnquiry admissionEnquiry = admissionEnquiryRepository.findById(updateAdmissionEnquiryInput.getId()).get();

        if (updateAdmissionEnquiryInput.getStudentName() != null) {
            admissionEnquiry.setStudentName(updateAdmissionEnquiryInput.getStudentName());
        }
        if (updateAdmissionEnquiryInput.getStudentMiddleName() != null) {
            admissionEnquiry.setStudentMiddleName(updateAdmissionEnquiryInput.getStudentMiddleName());
        }
        if (updateAdmissionEnquiryInput.getStudentLastName() != null) {
            admissionEnquiry.setStudentLastName(updateAdmissionEnquiryInput.getStudentLastName());
        }
        if (updateAdmissionEnquiryInput.getFatherName() != null) {
            admissionEnquiry.setFatherName(updateAdmissionEnquiryInput.getFatherName());
        }
        if (updateAdmissionEnquiryInput.getFatherMiddleName() != null) {
            admissionEnquiry.setFatherMiddleName(updateAdmissionEnquiryInput.getFatherMiddleName());
        }
        if (updateAdmissionEnquiryInput.getFatherLastName() != null) {
            admissionEnquiry.setFatherLastName(updateAdmissionEnquiryInput.getFatherLastName());
        }
        if (updateAdmissionEnquiryInput.getMotherName() != null) {
            admissionEnquiry.setMotherName(updateAdmissionEnquiryInput.getMotherName());
        }
        if (updateAdmissionEnquiryInput.getMotherMiddleName() != null) {
            admissionEnquiry.setMotherMiddleName(updateAdmissionEnquiryInput.getMotherMiddleName());
        }
        if (updateAdmissionEnquiryInput.getMotherLastName() != null) {
            admissionEnquiry.setMotherLastName(updateAdmissionEnquiryInput.getMotherLastName());
        }
        if (updateAdmissionEnquiryInput.getContactNumber() != null) {
            admissionEnquiry.setContactNumber(updateAdmissionEnquiryInput.getContactNumber());
        }
        if (updateAdmissionEnquiryInput.getAlternateMobileNumber() != null) {
            admissionEnquiry.setAlternateMobileNumber(updateAdmissionEnquiryInput.getAlternateMobileNumber());
        }
        if (updateAdmissionEnquiryInput.getDateOfBirth() != null) {
            admissionEnquiry.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionEnquiryInput.getDateOfBirth()));
        }
        if (updateAdmissionEnquiryInput.getEmail() != null) {
            admissionEnquiry.setEmail(updateAdmissionEnquiryInput.getEmail());
        }
        if (updateAdmissionEnquiryInput.getSex() != null) {
            admissionEnquiry.setSex(updateAdmissionEnquiryInput.getSex());
        }
        if (updateAdmissionEnquiryInput.getComments() != null) {
            admissionEnquiry.setComments(updateAdmissionEnquiryInput.getComments());
        }
        if (updateAdmissionEnquiryInput.getCourseApplyingFor() != null) {
            admissionEnquiry.setCourseApplyingFor(updateAdmissionEnquiryInput.getCourseApplyingFor());
        }
        if (updateAdmissionEnquiryInput.getHighestQualification() != null) {
            admissionEnquiry.setHighestQualification(updateAdmissionEnquiryInput.getHighestQualification());
        }
        if (updateAdmissionEnquiryInput.getModeOfEnquiry() != null) {
            admissionEnquiry.setModeOfEnquiry(updateAdmissionEnquiryInput.getModeOfEnquiry());
        }
        if (updateAdmissionEnquiryInput.getStatus() != null) {
            admissionEnquiry.setStatus(updateAdmissionEnquiryInput.getStatus());
        }
        if (updateAdmissionEnquiryInput.getDescription() != null) {
            admissionEnquiry.setDescription(updateAdmissionEnquiryInput.getDescription());
        }
        if (updateAdmissionEnquiryInput.getEnquiryDate() != null) {
            admissionEnquiry.setEnquiryDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionEnquiryInput.getEnquiryDate()));
        }
        if (updateAdmissionEnquiryInput.getUpdatedOn() != null) {
            admissionEnquiry.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionEnquiryInput.getUpdatedOn()));
        }
        if (updateAdmissionEnquiryInput.getUpdatedBy() != null) {
            admissionEnquiry.setUpdatedBy(updateAdmissionEnquiryInput.getUpdatedBy());
        }
        if (updateAdmissionEnquiryInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateAdmissionEnquiryInput.getBranchId()).get();
            admissionEnquiry.setBranch(branch);
        }
        if (updateAdmissionEnquiryInput.getBatchId() != null) {
            Batch batch = batchRepository.findById(updateAdmissionEnquiryInput.getBatchId()).get();
            admissionEnquiry.setBatch(batch);
        }
        if (updateAdmissionEnquiryInput.getStateId() != null) {
            State state = stateRepository.findById(updateAdmissionEnquiryInput.getStateId()).get();
            admissionEnquiry.setState(state);
        }
        if (updateAdmissionEnquiryInput.getCityId() != null) {
            City city = cityRepository.findById(updateAdmissionEnquiryInput.getCityId()).get();
            admissionEnquiry.setCity(city);
        }
        if (updateAdmissionEnquiryInput.getCountryId() != null) {
            Country country = countryRepository.findById(updateAdmissionEnquiryInput.getCountryId()).get();
            admissionEnquiry.setCountry(country);
        }
        if (updateAdmissionEnquiryInput.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updateAdmissionEnquiryInput.getDepartmentId()).get();
            admissionEnquiry.setDepartment(department);
        }
        admissionEnquiryRepository.save(admissionEnquiry);
        return new UpdateAdmissionEnquiryPayload(admissionEnquiry);
    }

    public RemoveAdmissionEnquiryPayload removeAdmissionEnquiry(RemoveAdmissionEnquiryInput removeAdmissionEnquiryInput) {
        AdmissionEnquiry admissionEnquiry = admissionEnquiryRepository.findById(removeAdmissionEnquiryInput.getAdmissionEnquiryId()).get();
        admissionEnquiryRepository.delete(admissionEnquiry);
        return new RemoveAdmissionEnquiryPayload(Lists.newArrayList(admissionEnquiryRepository.findAll()));
    }

//    public AddStudentPayload addStudent(AddStudentInput addStudentInput) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
//
//    	Section se = this.commonService.getSectionById(addStudentInput.getSectionId());
//        Branch ob = this.commonService.getBranchById(addStudentInput.getBranchId());
//        Department od = this.commonService.getDepartmentById(addStudentInput.getDepartmentId());
//        Batch obt = this.commonService.getBatchById(addStudentInput.getBatchId());
//
//        Student student = CommonUtil.createCopyProperties(addStudentInput, Student.class);
//
//        student.setDateOfBirth(addStudentInput.getDateOfBirth());
//        student.setUploadPhoto("");
//        try {
//        	if(obt != null) {
//            	student.setBatch(obt);
//            	logger.debug("Given batch : "+obt.getId());
//            }else {
//            	logger.warn("Batch is not found in the database for given batch id : "+addStudentInput.getBatchId());
//            }
//            if(se != null) {
//            	student.setSection(se);
//            	logger.debug("Given section : "+se.getId());
//            }else {
//            	logger.warn("Section is not found in the database for given section id : "+addStudentInput.getSectionId());
//            }
//            if(ob != null) {
//            	student.setBranch(ob);
//            	logger.debug("Given branch : "+ob.getId());
//            }else {
//            	logger.warn("Branch is not found in the database for given branch id : "+addStudentInput.getBranchId());
//            }
//            if(od != null) {
//            	student.setDepartment(od);
//            	logger.debug("Given department : "+od.getId());
//            }else {
//            	logger.warn("Department is not found in the database for given Department id : "+addStudentInput.getDepartmentId());
//            }
//        }catch(Exception e) {
//        	logger.warn("Exception : ",e);
//        }
//
//
//        logger.info("Saving student record.");
//        student = studentRepository.save(student);
////        saveStudentImage2(addStudentInput, student, branch);
//        return new AddStudentPayload(student);
//    }

//    private void saveStudentImage(AbstractStudentInput input, Student student, Branch branch) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
//    	if(branch == null) return;
//    	if(branch != null && branch.getCollege() == null) return;
//    	if(CommonUtil.isNullOrEmpty(input.getFileName())) return;
//    	String temp = CmsConstants.STUDENT_IMAGE_FILE_PATH.replaceAll("COLLEGE_ID", CmsConstants.COLLEGE_ID_PLACEHOLDER_REPLACER+String.valueOf(branch.getCollege().getId()));
//    	String filePath = Paths.get("", temp).toString();
//    	String fileName = String.valueOf(student.getId());
//    	String ext = this.base64FileProcessor.getFileExtensionFromBase64Srting(input.getFileName().split(",")[0]);
//    	String absFilePath = filePath+ File.separator+CmsConstants.BRANCH_ID_PLACEHOLDER_REPLACER+String.valueOf(branch.getId())+File.separator + fileName+"."+ext;
//    	student.setStudentImagePath(absFilePath);
//    	logger.info("Saving student image. File path: "+absFilePath);
//    	this.base64FileProcessor.createFileFromBase64String(input.getFileName(), filePath, fileName, String.valueOf(branch.getId()), null);
//    	logger.info("Updating student record with image file path: "+absFilePath);
//    	this.studentRepository.save(student);
//    }
//
//    private void saveStudentImage2(AbstractStudentInput input, Student student, Branch branch) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
//    	if(branch == null) return;
//    	if(branch != null && branch.getCollege() == null) return;
//    	if(CommonUtil.isNullOrEmpty(input.getFileName())) return;
////    	String temp = CmsConstants.STUDENT_IMAGE_FILE_PATH.replaceAll("COLLEGE_ID", CmsConstants.COLLEGE_ID_PLACEHOLDER_REPLACER+String.valueOf(branch.getCollege().getId()));
//    	String temp = "src/main/webapp/static/images";
//
//    	String filePath = Paths.get("", temp).toString();
//    	String fileName = String.valueOf(student.getId());
//    	String ext = this.base64FileProcessor.getFileExtensionFromBase64Srting(input.getFileName().split(",")[0]);
////    	String absFilePath = filePath+ File.separator+CmsConstants.BRANCH_ID_PLACEHOLDER_REPLACER+String.valueOf(branch.getId())+File.separator + fileName+"."+ext;
//    	String absFilePath = filePath+ File.separator+fileName+"."+ext;
//
//    	student.setStudentImagePath(absFilePath);
//    	logger.info("Saving student image. File path: "+absFilePath);
//    	this.base64FileProcessor.createFileFromBase64String(input.getFileName(), filePath, fileName, null, null);
//    	logger.info("Updating student record with image file path: "+absFilePath);
//    	this.studentRepository.save(student);
//    }

//    public UpdateStudentPayload updateStudent(UpdateStudentInput updateStudentInput) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
//        Student student = studentRepository.findById(updateStudentInput.getId()).get();
//        if (updateStudentInput.getStudentName() != null) {
//            student.setStudentName(updateStudentInput.getStudentName());
//        }
//        if (updateStudentInput.getStudentMiddleName() != null) {
//            student.setStudentMiddleName(updateStudentInput.getStudentMiddleName());
//        }
//        if (updateStudentInput.getStudentLastName() != null) {
//            student.setStudentLastName(updateStudentInput.getStudentLastName());
//        }
//        if (updateStudentInput.getFatherName() != null) {
//            student.setFatherName(updateStudentInput.getFatherName());
//        }
//        if (updateStudentInput.getFatherMiddleName() != null) {
//            student.setFatherMiddleName(updateStudentInput.getFatherMiddleName());
//        }
//        if (updateStudentInput.getFatherLastName() != null) {
//            student.setFatherLastName(updateStudentInput.getFatherLastName());
//        }
//        if (updateStudentInput.getMotherName() != null) {
//            student.setMotherName(updateStudentInput.getMotherName());
//        }
//        if (updateStudentInput.getMotherMiddleName() != null) {
//            student.setMotherMiddleName(updateStudentInput.getMotherMiddleName());
//        }
//        if (updateStudentInput.getMotherLastName() != null) {
//            student.setMotherLastName(updateStudentInput.getMotherLastName());
//        }
//        if (updateStudentInput.getAadharNo() != null) {
//            student.setAadharNo(updateStudentInput.getAadharNo());
//        }
//        if (updateStudentInput.getDateOfBirth() != null) {
//            student.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateStudentInput.getDateOfBirth()));
//        }
//        if (updateStudentInput.getPlaceOfBirth() != null) {
//            student.setPlaceOfBirth(updateStudentInput.getPlaceOfBirth());
//        }
//        if (updateStudentInput.getReligion() != null) {
//            student.setReligion(updateStudentInput.getReligion());
//        }
//        if (updateStudentInput.getCaste() != null) {
//            student.setCaste(updateStudentInput.getCaste());
//        }
//        if (updateStudentInput.getSubCaste() != null) {
//            student.setSubCaste(updateStudentInput.getSubCaste());
//        }
//        if (updateStudentInput.getAge() != null) {
//            student.setAge(updateStudentInput.getAge());
//        }
//        if (updateStudentInput.getSex() != null) {
//            student.setSex(updateStudentInput.getSex());
//        }
//        if (updateStudentInput.getBloodGroup() != null) {
//            student.setBloodGroup(updateStudentInput.getBloodGroup());
//        }
//        if (updateStudentInput.getAddressLineOne() != null) {
//            student.setAddressLineOne(updateStudentInput.getAddressLineOne());
//        }
//        if (updateStudentInput.getAddressLineTwo() != null) {
//            student.setAddressLineTwo(updateStudentInput.getAddressLineTwo());
//        }
//        if (updateStudentInput.getAddressLineThree() != null) {
//            student.setAddressLineThree(updateStudentInput.getAddressLineThree());
//        }
//        if (updateStudentInput.getTown() != null) {
//            student.setTown(updateStudentInput.getTown());
//        }
//        if (updateStudentInput.getState() != null) {
//            student.setState(updateStudentInput.getState());
//        }
//        if (updateStudentInput.getCountry() != null) {
//            student.setCountry(updateStudentInput.getCountry());
//        }
//        if (updateStudentInput.getPincode() != null) {
//            student.setPincode(updateStudentInput.getPincode());
//        }
//        if (updateStudentInput.getStudentContactNumber() != null) {
//            student.setStudentContactNumber(updateStudentInput.getStudentContactNumber());
//        }
//        if (updateStudentInput.getAlternateContactNumber() != null) {
//            student.setAlternateContactNumber(updateStudentInput.getAlternateContactNumber());
//        }
//        if (updateStudentInput.getStudentEmailAddress() != null) {
//            student.setStudentEmailAddress(updateStudentInput.getStudentEmailAddress());
//        }
//        if (updateStudentInput.getAlternateEmailAddress() != null) {
//            student.setAlternateEmailAddress(updateStudentInput.getAlternateEmailAddress());
//        }
//        if (updateStudentInput.getRelationWithStudent() != null) {
//            student.setRelationWithStudent(updateStudentInput.getRelationWithStudent());
//        }
//        if (updateStudentInput.getEmergencyContactName() != null) {
//            student.setEmergencyContactName(updateStudentInput.getEmergencyContactName());
//        }
//        if (updateStudentInput.getEmergencyContactMiddleName() != null) {
//            student.setEmergencyContactMiddleName(updateStudentInput.getEmergencyContactMiddleName());
//        }
//        if (updateStudentInput.getEmergencyContactLastName() != null) {
//            student.setEmergencyContactLastName(updateStudentInput.getEmergencyContactLastName());
//        }
//        if (updateStudentInput.getEmergencyContactNo() != null) {
//            student.setEmergencyContactNo(updateStudentInput.getEmergencyContactNo());
//        }
//        if (updateStudentInput.getEmergencyContactEmailAddress() != null) {
//            student.setEmergencyContactEmailAddress(updateStudentInput.getEmergencyContactEmailAddress());
//        }

//        if (updateStudentInput.getUploadPhoto() != null) {
//            student.setUploadPhoto(updateStudentInput.getUploadPhoto());
//        }

//        if (updateStudentInput.getAdmissionNo() != null) {
//            student.setAdmissionNo(updateStudentInput.getAdmissionNo());
//        }
//        if (updateStudentInput.getRollNo() != null) {
//            student.setRollNo(updateStudentInput.getRollNo());
//        }
//        if (updateStudentInput.getStudentType() != null) {
//            student.setStudentType(updateStudentInput.getStudentType());
//        }
//        if (updateStudentInput.getBatchId() != null) {
//            final Batch batch = batchRepository.findById(updateStudentInput.getBatchId()).get();
//            student.setBatch(batch);
//        }
//        if (updateStudentInput.getSectionId() != null) {
//            final Section section = sectionRepository.findById(updateStudentInput.getSectionId()).get();
//            student.setSection(section);
//        }
//        if (updateStudentInput.getBranchId() != null) {
//            final Branch branch = branchRepository.findById(updateStudentInput.getBranchId()).get();
//            student.setBranch(branch);
//        }
//        if (updateStudentInput.getDepartmentId() != null) {
//            final Department department = departmentRepository.findById(updateStudentInput.getDepartmentId()).get();
//            student.setDepartment(department);
//        }

//        saveStudentImage(updateStudentInput, student, student.getBranch());

//        studentRepository.save(student);
//
//        return new UpdateStudentPayload(student);
//    }
//
//    public RemoveStudentPayload removeStudent(RemoveStudentInput removeStudentInput) {
//        Student student = studentRepository.findById(removeStudentInput.getStudentId()).get();
//        studentRepository.delete(student);
//        return new RemoveStudentPayload(Lists.newArrayList(studentRepository.findAll()));
//    }


//    public AddInstitutePayload addInstitute(AddInstituteInput addInstituteInput) {
//        final Institute institute = new Institute();
//        institute.setCode(addInstituteInput.getCode());
//        institute.setName(addInstituteInput.getName());
//        institute.setYear(addInstituteInput.getYear());
//
//        instituteRepository.save(institute);
//
//        return new AddInstitutePayload(institute);
//    }

    /*public AddStudentYearPayload addStudentYear(AddStudentYearInput addStudentYearInput) {
        final StudentYear studentYear = new StudentYear();
        studentYear.setYearDesc(addStudentYearInput.getYearDesc());
        studentYearRepository.save(studentYear);

        return new AddStudentYearPayload(studentYear);
    }

    public UpdateStudentYearPayload updateStudentYear(UpdateStudentYearInput updateStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(updateStudentYearInput.getId()).get();
        if (updateStudentYearInput.getYearDesc() != null) {
            studentYear.setYearDesc(updateStudentYearInput.getYearDesc());
        }

        studentYearRepository.save(studentYear);

        return new UpdateStudentYearPayload(studentYear);
    }

    public RemoveStudentYearPayload removeStudentYear(RemoveStudentYearInput removeStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(removeStudentYearInput.getStudentYearId()).get();
        studentYearRepository.delete(studentYear);

        return new RemoveStudentYearPayload(Lists.newArrayList(studentYearRepository.findAll()));
    }*/

    public AddBankAccountsPayload addBankAccounts(AddBankAccountsInput addBankAccountsInput) {
        final BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setNameOfBank(addBankAccountsInput.getNameOfBank());
        bankAccounts.setAccountNumber(addBankAccountsInput.getAccountNumber());
        bankAccounts.setTypeOfAccount(addBankAccountsInput.getTypeOfAccount());
        bankAccounts.setIfscCode(addBankAccountsInput.getIfscCode());
        bankAccounts.setBranchAddress(addBankAccountsInput.getBranchAddress());
        bankAccounts.setCorporateId(addBankAccountsInput.getCorporateId());
        Branch branch = branchRepository.findById(addBankAccountsInput.getBranchId()).get();
        College college = collegeRepository.findById(addBankAccountsInput.getCollegeId()).get();
        bankAccounts.setBranch(branch);
        bankAccounts.setCollege(college);
        bankAccountsRepository.save(bankAccounts);

        return new AddBankAccountsPayload(bankAccounts);
    }

    public UpdateBankAccountsPayload updateBankAccounts(UpdateBankAccountsInput updateBankAccountsInput) {
        BankAccounts bankAccounts = bankAccountsRepository.findById(updateBankAccountsInput.getId()).get();
        if (updateBankAccountsInput.getNameOfBank() != null) {
            bankAccounts.setNameOfBank(updateBankAccountsInput.getNameOfBank());
        }
        if (updateBankAccountsInput.getAccountNumber() != null) {
            bankAccounts.setAccountNumber(updateBankAccountsInput.getAccountNumber());
        }
        if (updateBankAccountsInput.getTypeOfAccount() != null) {
            bankAccounts.setTypeOfAccount(updateBankAccountsInput.getTypeOfAccount());
        }
        if (updateBankAccountsInput.getIfscCode() != null) {
            bankAccounts.setIfscCode(updateBankAccountsInput.getIfscCode());
        }
        if (updateBankAccountsInput.getBranchAddress() != null) {
            bankAccounts.setBranchAddress(updateBankAccountsInput.getBranchAddress());
        }
        if (updateBankAccountsInput.getCorporateId() != null) {
            bankAccounts.setCorporateId(updateBankAccountsInput.getCorporateId());
        }
        if (updateBankAccountsInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateBankAccountsInput.getBranchId()).get();
            bankAccounts.setBranch(branch);
        }
        if (updateBankAccountsInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateBankAccountsInput.getCollegeId()).get();
            bankAccounts.setCollege(college);
        }

        bankAccountsRepository.save(bankAccounts);

        return new UpdateBankAccountsPayload(bankAccounts);
    }

    public RemoveBankAccountsPayload removeBankAccounts(RemoveBankAccountsInput removeBankAccountsInput) {
        BankAccounts bankAccounts = bankAccountsRepository.findById(removeBankAccountsInput.getBankAccountsId()).get();
        bankAccountsRepository.delete(bankAccounts);
        return new RemoveBankAccountsPayload(Lists.newArrayList(bankAccountsRepository.findAll()));
    }

    public AddDepartmentPayload addDepartment(AddDepartmentInput addDepartmentsInput) {
//    	Student student = studentRepository.findById(addDepartmentsInput.getStudentId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addDepartmentsInput.getAcademicyearId()).get();
        Branch branch = branchRepository.findById(addDepartmentsInput.getBranchId()).get();
        final Department departments = new Department();
        departments.setName(addDepartmentsInput.getName());
        departments.setDescription(addDepartmentsInput.getDescription());
        departments.setDeptHead(addDepartmentsInput.getDeptHead());
//        departments.setStudent(student);
        departments.setBranch(branch);
        departments.setAcademicyear(academicYear);
        departmentRepository.save(departments);

        return new AddDepartmentPayload(departments);
    }

    public UpdateDepartmentPayload updateDepartment(UpdateDepartmentInput updateDepartmentsInput) {
        Department departments = departmentRepository.findById(updateDepartmentsInput.getId()).get();
        if (updateDepartmentsInput.getName() != null) {
            departments.setName(updateDepartmentsInput.getName());
        }
        if (updateDepartmentsInput.getDescription() != null) {
            departments.setDescription(updateDepartmentsInput.getDescription());
        }
        if (updateDepartmentsInput.getDeptHead() != null) {
            departments.setDeptHead(updateDepartmentsInput.getDeptHead());
        }
//        if(updateDepartmentsInput.getStudentId() != null) {
//        	Student student = studentRepository.findById(updateDepartmentsInput.getStudentId()).get();
//        	departments.setStudent(student);
//        }
        if (updateDepartmentsInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateDepartmentsInput.getBranchId()).get();
            departments.setBranch(branch);
        }
        if (updateDepartmentsInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateDepartmentsInput.getAcademicyearId()).get();
            departments.setAcademicyear(academicYear);
        }
        departmentRepository.save(departments);

        return new UpdateDepartmentPayload(departments);
    }

    public RemoveDepartmentPayload removeDepartment(RemoveDepartmentInput removeDepartmentsInput) {
        Department departments = departmentRepository.findById(removeDepartmentsInput.getDepartmentId()).get();
        departmentRepository.delete(departments);
        return new RemoveDepartmentPayload(Lists.newArrayList(departmentRepository.findAll()));
    }
    public AddCompetitiveExamPayload addCompetitiveExam(AddCompetitiveExamInput addCompetitiveExamInput) {
        final Student student = studentRepository.findById(addCompetitiveExamInput.getStudentId()).get();

        final CompetitiveExam competitiveExam = new CompetitiveExam();
        competitiveExam.setStudent(student);
        competitiveExam.setTestName(addCompetitiveExamInput.getTestName());
        competitiveExam.setTestScore(addCompetitiveExamInput.getTestScore());
        competitiveExam.setEnrollmentNo(addCompetitiveExamInput.getEnrollmentNo());
        competitiveExam.setRank(addCompetitiveExamInput.getRank());
        competitiveExamRepository.save(competitiveExam);
        return new AddCompetitiveExamPayload(competitiveExam);
    }

    public UpdateCompetitiveExamPayload updateCompetitiveExam(UpdateCompetitiveExamInput updateCompetitiveExamInput) {
        final CompetitiveExam competitiveExam = competitiveExamRepository.findById(updateCompetitiveExamInput.getId()).get();

        if (updateCompetitiveExamInput.getTestName() != null){
            competitiveExam.setTestName(updateCompetitiveExamInput.getTestName());
        }
        if(updateCompetitiveExamInput.getTestScore() != null){
            competitiveExam.setTestScore(updateCompetitiveExamInput.getTestScore());
        }
        if(updateCompetitiveExamInput.getEnrollmentNo() != null){
            competitiveExam.setEnrollmentNo(updateCompetitiveExamInput.getEnrollmentNo());
        }

        if(updateCompetitiveExamInput.getRank() != null){
            competitiveExam.setRank(updateCompetitiveExamInput.getRank());
        }

        if (updateCompetitiveExamInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateCompetitiveExamInput.getStudentId()).get();
            competitiveExam.setStudent(student);
        }
        competitiveExamRepository.save(competitiveExam);

        return new UpdateCompetitiveExamPayload(competitiveExam);
    }


    public RemoveCompetitiveExamPayload removeCompetitiveExam(RemoveCompetitiveExamInput removeCompetitiveExamInput) {
        CompetitiveExam CompetitiveExam = competitiveExamRepository.findById(removeCompetitiveExamInput.getCompetitiveExamId()).get();
        competitiveExamRepository.delete(CompetitiveExam);
        return new RemoveCompetitiveExamPayload(Lists.newArrayList(competitiveExamRepository.findAll()));
    }


    public AddDocumentsPayload addDocuments(AddDocumentsInput addDocumentsInput) {
        final Student student = studentRepository.findById(addDocumentsInput.getStudentId()).get();
        final Vehicle vehicle = vehicleRepository.findById(addDocumentsInput.getVehicleId()).get();
        final Employee employee = employeeRepository.findById(addDocumentsInput.getEmployeeId()).get();
        final Contract contract = contractRepository.findById(addDocumentsInput.getContractId()).get();
        final Documents documents = new Documents();
        documents.setStudent(student);
        documents.setVehicle(vehicle);
        documents.setContract(contract);
        documents.setEmployee(employee);
        documents.setDocumentFilePath(addDocumentsInput.getDocumentFilePath());
        documents.setDocumentName(addDocumentsInput.getDocumentName());
        documentsRepository.save(documents);
        return new AddDocumentsPayload(documents);
    }


    public UpdateDocumentsPayload updateDocuments(UpdateDocumentsInput updateDocumentsInput) {
        final Documents documents = documentsRepository.findById(updateDocumentsInput.getId()).get();

        if (updateDocumentsInput.getDocumentFilePath() != null){
            documents.setDocumentFilePath(updateDocumentsInput.getDocumentFilePath());
        }
        if(updateDocumentsInput.getDocumentName() != null){
            documents.setDocumentName(updateDocumentsInput.getDocumentName());
        }

        if (updateDocumentsInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateDocumentsInput.getStudentId()).get();
            documents.setStudent(student);
        }

        if (updateDocumentsInput.getEmployeeId() != null) {
            final Employee employee = employeeRepository.findById(updateDocumentsInput.getEmployeeId()).get();
            documents.setEmployee(employee);
        }
        if (updateDocumentsInput.getVehicleId() != null) {
            final Vehicle vehicle = vehicleRepository.findById(updateDocumentsInput.getVehicleId()).get();
            documents.setVehicle(vehicle);
        }

        if (updateDocumentsInput.getContractId() != null) {
            final Contract contract = contractRepository.findById(updateDocumentsInput.getContractId()).get();
            documents.setContract(contract);
        }

        documentsRepository.save(documents);

        return new UpdateDocumentsPayload(documents);
    }

    public RemoveDocumentsPayload removeDocuments(RemoveDocumentsInput removeDocumentsInput) {
        Documents documents = documentsRepository.findById(removeDocumentsInput.getDocumentsId()).get();
        documentsRepository.delete(documents);
        return new RemoveDocumentsPayload(Lists.newArrayList(documentsRepository.findAll()));
    }


    /*public AddSemesterPayload addSemester(AddSemesterInput addSemesterInput) {
        final Semester semester = new Semester();
        semester.setSem(addSemesterInput.getSem());
        semesterRepository.save(semester);

        return new AddSemesterPayload(semester);
    }

    public UpdateSemesterPayload updateSemester(UpdateSemesterInput updateSemesterInput) {
        Semester semester = semesterRepository.findById(updateSemesterInput.getId()).get();
        if (updateSemesterInput.getSem() != null) {
            semester.setSem(updateSemesterInput.getSem());
        }

        semesterRepository.save(semester);

        return new UpdateSemesterPayload(semester);
    }

    public RemoveSemesterPayload removeSemester(RemoveSemesterInput removeSemesterInput) {
        Semester semester = semesterRepository.findById(removeSemesterInput.getSemesterId()).get();
        semesterRepository.delete(semester);
        return new RemoveSemesterPayload(Lists.newArrayList(semesterRepository.findAll()));
    }
*/

//    public UpdateInstitutePayload updateInstitute(UpdateInstituteInput updateInstituteInput) {
//        Institute institute = instituteRepository.findById(updateInstituteInput.getInstituteId());
//        if (updateInstituteInput.getCode() != null) {
//            institute.setCode(updateInstituteInput.getCode());
//        }
//
//        if (updateInstituteInput.getName() != null) {
//            institute.setName(updateInstituteInput.getName());
//        }
//
//        if (updateInstituteInput.getYear() != null) {
//            institute.setYear(updateInstituteInput.getYear());
//        }
//
//        instituteRepository.save(institute);
//
//        return new UpdateInstitutePayload(institute);
//    }

//    public RemoveInstitutePayload removeInstitute(RemoveInstituteInput removeInstituteInput) {
//        Institute institute = instituteRepository.findById(removeInstituteInput.getInstituteId());
//        instituteRepository.delete(institute);
//
//        return new RemoveInstitutePayload(Lists.newArrayList(instituteRepository.findAll()));
//    }

    public AddCollegePayload addCollege(AddCollegeInput addCollegeInput) {
        final College college = new College();
        college.setShortName(addCollegeInput.getShortName());
        college.setLogoPath(addCollegeInput.getLogoPath());
        college.setBackgroundImagePath(addCollegeInput.getBackgroundImagePath());
        college.setInstructionInformation(addCollegeInput.getInstructionInformation());

        collegeRepository.save(college);

        return new AddCollegePayload(college);
    }

    public UpdateCollegePayload updateCollege(UpdateCollegeInput updateCollegeInput) {
        College college = collegeRepository.findById(updateCollegeInput.getId()).get();
        if (updateCollegeInput.getShortName() != null) {
            college.setShortName(updateCollegeInput.getShortName());
        }

        if (updateCollegeInput.getLogoPath() != null) {
            college.setLogoPath(updateCollegeInput.getLogoPath());
        }

        if (updateCollegeInput.getBackgroundImagePath() != null) {
            college.setBackgroundImagePath(updateCollegeInput.getBackgroundImagePath());
        }

        if (updateCollegeInput.getInstructionInformation() != null) {
            college.setInstructionInformation(updateCollegeInput.getInstructionInformation());
        }

        collegeRepository.save(college);

        return new UpdateCollegePayload(college);
    }

    public RemoveCollegePayload removeCollege(RemoveCollegeInput removeCollegeInput) {
        College college = collegeRepository.getOne(removeCollegeInput.getCollegeId());
        collegeRepository.delete(college);

        return new RemoveCollegePayload(Lists.newArrayList(collegeRepository.findAll()));
    }




    public AddEmployeePayload addEmployee(AddEmployeeInput addEmployeeInput) {
        final Employee employee = new Employee();
        final Branch branch = branchRepository.findById(addEmployeeInput.getBranchId()).get();
        final TransportRoute transportRoute = transportRouteRepository.findById(addEmployeeInput.getTransportRouteId()).get();
        employee.setEmployeeName(addEmployeeInput.getEmployeeName());
        employee.setDesignation(addEmployeeInput.getDesignation());
        employee.setJoiningDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getJoiningDate()));
        employee.setJobEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getJobEndDate()));
        employee.setResignationDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getResignationDate()));
        employee.setResignationAcceptanceDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getResignationAcceptanceDate()));
        employee.setAadharNo(addEmployeeInput.getAadharNo());
        employee.setPanNo(addEmployeeInput.getPanNo());
        employee.setPassportNo(addEmployeeInput.getPassportNo());
        employee.setPrimaryContactNo(addEmployeeInput.getPrimaryContactNo());
        employee.setSecondaryContactNo(addEmployeeInput.getSecondaryContactNo());
        employee.setEmployeeFatherName(addEmployeeInput.getEmployeeFatherName());
        employee.setEmployeeMotherName(addEmployeeInput.getEmployeeMotherName());
        employee.setPrimaryAddress(addEmployeeInput.getPrimaryAddress());
        employee.setSecondaryAddress(addEmployeeInput.getSecondaryAddress());
        employee.setEmployeeAddress(addEmployeeInput.getEmployeeAddress());
        employee.setPersonalMailId(addEmployeeInput.getPersonalMailId());
        employee.setOfficialMailId(addEmployeeInput.getOfficialMailId());
        employee.setDisability(addEmployeeInput.getDisability().toString());
        employee.setDrivingLicenceNo(addEmployeeInput.getDrivingLicenceNo());
        employee.setDrivingLicenceValidity(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getDrivingLicenceValidity()));
        employee.setGender(addEmployeeInput.getGender().toString());
        employee.setTypeOfEmployment(addEmployeeInput.getTypeOfEmployment());
        employee.setManagerId(addEmployeeInput.getManagerId());
        employee.setStatus(addEmployeeInput.getStatus().toString());
        employee.setMaritalStatus(addEmployeeInput.getMaritalStatus().toString());
        employee.setBranch(branch);
        employee.setTransportRoute(transportRoute);
        employeeRepository.save(employee);

        return new AddEmployeePayload(employee);

    }

    public UpdateEmployeePayload updateEmployee(UpdateEmployeeInput updateEmployeeInput) {
        Employee employee = employeeRepository.findById(updateEmployeeInput.getId()).get();
        if (updateEmployeeInput.getEmployeeName() != null) {
            employee.setEmployeeName(updateEmployeeInput.getEmployeeName());
        }

        if (updateEmployeeInput.getDesignation() != null) {
            employee.setDesignation(updateEmployeeInput.getDesignation());
        }

        if (updateEmployeeInput.getJoiningDate() != null) {
            employee.setJoiningDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getJoiningDate()));
        }

        if (updateEmployeeInput.getJobEndDate() != null) {
            employee.setJobEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getJobEndDate()));
        }

        if (updateEmployeeInput.getResignationDate() != null) {
            employee.setResignationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getResignationDate()));
        }

        if (updateEmployeeInput.getResignationAcceptanceDate() != null) {
            employee.setResignationAcceptanceDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getResignationAcceptanceDate()));
        }
        if (updateEmployeeInput.getAadharNo() != null) {
            employee.setAadharNo(updateEmployeeInput.getAadharNo());
        }
        if (updateEmployeeInput.getPanNo() != null) {
            employee.setPanNo(updateEmployeeInput.getPanNo());
        }
        if (updateEmployeeInput.getPassportNo() != null) {
            employee.setPassportNo(updateEmployeeInput.getPassportNo());
        }
        if (updateEmployeeInput.getPrimaryContactNo() != null) {
            employee.setPrimaryContactNo(updateEmployeeInput.getPrimaryContactNo());
        }
        if (updateEmployeeInput.getSecondaryContactNo() != null) {
            employee.setSecondaryContactNo(updateEmployeeInput.getSecondaryContactNo());
        }
        if (updateEmployeeInput.getEmployeeFatherName() != null) {
            employee.setEmployeeFatherName(updateEmployeeInput.getEmployeeFatherName());
        }
        if (updateEmployeeInput.getEmployeeMotherName() != null) {
            employee.setEmployeeMotherName(updateEmployeeInput.getEmployeeMotherName());
        }
        if (updateEmployeeInput.getPrimaryAddress() != null) {
            employee.setPrimaryAddress(updateEmployeeInput.getPrimaryAddress());
        }
        if (updateEmployeeInput.getSecondaryAddress() != null) {
            employee.setSecondaryAddress(updateEmployeeInput.getSecondaryAddress());
        }
        if (updateEmployeeInput.getEmployeeAddress() != null) {
            employee.setEmployeeAddress(updateEmployeeInput.getEmployeeAddress());
        }
        if (updateEmployeeInput.getPersonalMailId() != null) {
            employee.setPersonalMailId(updateEmployeeInput.getPersonalMailId());
        }
        if (updateEmployeeInput.getDisability() != null) {
            employee.setDisability(updateEmployeeInput.getDisability().toString());
        }
        if (updateEmployeeInput.getDrivingLicenceNo() != null) {
            employee.setDrivingLicenceNo(updateEmployeeInput.getDrivingLicenceNo());
        }
        if (updateEmployeeInput.getDrivingLicenceValidity() != null) {
            employee.setDrivingLicenceValidity(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getDrivingLicenceValidity()));
        }
        if (updateEmployeeInput.getGender() != null) {
            employee.setGender(updateEmployeeInput.getGender().toString());
        }
        if (updateEmployeeInput.getTypeOfEmployment() != null) {
            employee.setTypeOfEmployment(updateEmployeeInput.getTypeOfEmployment());
        }
        if (updateEmployeeInput.getManagerId() != null) {
            employee.setManagerId(updateEmployeeInput.getManagerId());
        }
        if (updateEmployeeInput.getStatus() != null) {
            employee.setStatus(updateEmployeeInput.getStatus().toString());
        }
        if (updateEmployeeInput.getMaritalStatus() != null) {
            employee.setMaritalStatus(updateEmployeeInput.getMaritalStatus().toString());
        }
        if (updateEmployeeInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateEmployeeInput.getBranchId()).get();
            employee.setBranch(branch);
        }
        if (updateEmployeeInput.getTransportRouteId() != null) {
            TransportRoute transportRoute = transportRouteRepository.findById(updateEmployeeInput.getTransportRouteId()).get();
            employee.setTransportRoute(transportRoute);
        }

        employeeRepository.save(employee);

        return new UpdateEmployeePayload(employee);
    }

    public RemoveEmployeePayload removeEmployee(RemoveEmployeeInput removeEmployeeInput) {
        Employee employee = employeeRepository.getOne(removeEmployeeInput.getEmployeeId());
        employeeRepository.delete(employee);

        return new RemoveEmployeePayload(Lists.newArrayList(employeeRepository.findAll()));
    }

    public AddBranchPayload addBranch(AddBranchInput addBranchInput) {
        College college = collegeRepository.findById(addBranchInput.getCollegeId()).get();
        City city = cityRepository.findById(addBranchInput.getCityId()).get();
        State state = stateRepository.findById(addBranchInput.getStateId()).get();
        final Branch branch = new Branch();
        branch.setBranchName(addBranchInput.getBranchName());
        branch.setAddress1(addBranchInput.getAddress1());
        branch.setAddress2(addBranchInput.getAddress2());
        branch.setBranchHead(addBranchInput.getBranchHead());
        branch.setCollege(college);
        branch.setCity(city);
        branch.setState(state);
        branchRepository.save(branch);

        return new AddBranchPayload(branch);
    }

    public UpdateBranchPayload updateBranch(UpdateBranchInput updateBranchInput) {
        Branch branch = branchRepository.findById(updateBranchInput.getId()).get();
        if (updateBranchInput.getBranchName() != null) {
            branch.setBranchName(updateBranchInput.getBranchName());
        }

        if (updateBranchInput.getAddress1() != null) {
            branch.setAddress1(updateBranchInput.getAddress1());
        }

        if (updateBranchInput.getAddress2() != null) {
            branch.setAddress2(updateBranchInput.getAddress2());
        }

        if (updateBranchInput.getBranchHead() != null) {
            branch.setBranchHead(updateBranchInput.getBranchHead());
        }
        if (updateBranchInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateBranchInput.getCollegeId()).get();
            branch.setCollege(college);
        }
        if (updateBranchInput.getStateId() != null) {
            State state = stateRepository.findById(updateBranchInput.getStateId()).get();
            branch.setState(state);
        }
        if (updateBranchInput.getCityId() != null) {
            City city = cityRepository.findById(updateBranchInput.getCityId()).get();
            branch.setCity(city);
        }
        branchRepository.save(branch);
        return new UpdateBranchPayload(branch);
    }

    public RemoveBranchPayload removeBranch(RemoveBranchInput removeBranchsInput) {
        Branch branch = branchRepository.findById(removeBranchsInput.getBranchId()).get();
        branchRepository.delete(branch);
        return new RemoveBranchPayload(Lists.newArrayList(branchRepository.findAll()));
    }

    public AddBatchPayload addBatch(AddBatchInput addBatchInput) {
        Department department = departmentRepository.findById(addBatchInput.getDepartmentId()).get();
        final Batch batch = new Batch();
        batch.setBatch(addBatchInput.getBatch());
        batch.setDepartment(department);
        batchRepository.save(batch);
        return new AddBatchPayload(batch);
    }

    public UpdateBatchPayload updateBatch(UpdateBatchInput updateBatchInput) {
        Batch batch = batchRepository.findById(updateBatchInput.getId()).get();
        if (updateBatchInput.getBatch() != null) {
            batch.setBatch(updateBatchInput.getBatch());
        }
        if (updateBatchInput.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updateBatchInput.getDepartmentId()).get();
            batch.setDepartment(department);
        }
        batchRepository.save(batch);
        return new UpdateBatchPayload(batch);
    }

    public RemoveBatchPayload removeBatch(RemoveBatchInput removeBatchInput) {
        Batch batch = batchRepository.findById(removeBatchInput.getBatchId()).get();
        batchRepository.delete(batch);
        return new RemoveBatchPayload(Lists.newArrayList(batchRepository.findAll()));
    }

    public AddSectionPayload addSection(AddSectionInput addSectionInput) {
        final Batch batch = batchRepository.findById(addSectionInput.getBatchId()).get();
        final Section section = new Section();
        section.setSection(addSectionInput.getSection());
        section.setBatch(batch);
        sectionRepository.save(section);
        return new AddSectionPayload(section);
    }

    public UpdateSectionPayload updateSection(UpdateSectionInput updateSectionInput) {
        Section section = sectionRepository.findById(updateSectionInput.getId()).get();
        if (updateSectionInput.getSection() != null) {
            section.setSection(updateSectionInput.getSection());
        }
        if (updateSectionInput.getBatchId() != null) {
            final Batch batch = batchRepository.findById(updateSectionInput.getBatchId()).get();
            section.setBatch(batch);
        }
        sectionRepository.save(section);

        return new UpdateSectionPayload(section);
    }

    public RemoveSectionPayload removeSection(RemoveSectionInput removeSectionInput) {
        Section section = sectionRepository.findById(removeSectionInput.getSectionId()).get();
        sectionRepository.delete(section);
        return new RemoveSectionPayload(Lists.newArrayList(sectionRepository.findAll()));
    }

    public AddSubjectPayload addSubject(AddSubjectInput addSubjectInput) {
        final Department department = departmentRepository.findById(addSubjectInput.getDepartmentId()).get();
        final Batch batch = batchRepository.findById(addSubjectInput.getBatchId()).get();

        final Subject subject = new Subject();
        subject.setDepartment(department);
        subject.setBatch(batch);
        subject.setSubjectType(addSubjectInput.getSubjectType().toString());
        subject.setSubjectCode(addSubjectInput.getSubjectCode());
        subject.setSubjectDesc(addSubjectInput.getSubjectDesc());
        subject.setStatus(addSubjectInput.getStatus().toString());
        subjectRepository.save(subject);
        return new AddSubjectPayload(subject);
    }

    public UpdateSubjectPayload updateSubject(UpdateSubjectInput updateSubjectInput) {
        Subject subject = subjectRepository.findById(updateSubjectInput.getId()).get();
        if (updateSubjectInput.getSubjectCode() != null) {
            subject.setSubjectCode(updateSubjectInput.getSubjectCode());
        }
        if (updateSubjectInput.getSubjectType() != null) {
            subject.setSubjectType(updateSubjectInput.getSubjectType().toString());
        }
        if (updateSubjectInput.getSubjectDesc() != null) {
            subject.setSubjectDesc(updateSubjectInput.getSubjectDesc());
        }
        if (updateSubjectInput.getStatus() != null) {
            subject.setStatus(updateSubjectInput.getStatus().toString());
        }
        if (updateSubjectInput.getDepartmentId() != null) {
            final Department department = departmentRepository.findById(updateSubjectInput.getDepartmentId()).get();
            subject.setDepartment(department);
        }
        if (updateSubjectInput.getBatchId() != null) {
            final Batch batch = batchRepository.findById(updateSubjectInput.getBatchId()).get();
            subject.setBatch(batch);
        }
        subjectRepository.save(subject);

        return new UpdateSubjectPayload(subject);
    }

    public RemoveSubjectPayload removeSubject(RemoveSubjectInput removeSubjectInput) {
        Subject subject = subjectRepository.findById(removeSubjectInput.getSubjectId()).get();
        subjectRepository.delete(subject);
        return new RemoveSubjectPayload(Lists.newArrayList(subjectRepository.findAll()));
    }

    public AddStudentAttendancePayload addStudentAttendance(AddStudentAttendanceInput addStudentAttendanceInput) {
        final Student student = studentRepository.findById(addStudentAttendanceInput.getStudentId()).get();
        final Lecture lecture = lectureRepository.findById(addStudentAttendanceInput.getLectureId()).get();
        final StudentAttendance studentAttendance = new StudentAttendance();
        studentAttendance.setAttendanceStatus(addStudentAttendanceInput.getAttendanceStatus());
        studentAttendance.setComments(addStudentAttendanceInput.getComments());
        studentAttendance.setStudent(student);
        studentAttendance.setLecture(lecture);
//        studentAttendance.setAttendanceDate(addStudentAttendanceInput.getAttendanceDate());
        studentAttendanceRepository.save(studentAttendance);
        return new AddStudentAttendancePayload(studentAttendance);
    }

    public UpdateStudentAttendancePayload updateStudentAttendance(UpdateStudentAttendanceInput updateStudentAttendanceInput) {
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(updateStudentAttendanceInput.getId()).get();


        if (updateStudentAttendanceInput.getAttendanceStatus() != null) {
            studentAttendance.setAttendanceStatus(updateStudentAttendanceInput.getAttendanceStatus());
        }
        if (updateStudentAttendanceInput.getComments() != null) {
            studentAttendance.setComments(updateStudentAttendanceInput.getComments());
        }
//        if (updateStudentAttendanceInput.getAttendanceDate() != null) {
//            studentAttendance.setAttendanceDate(updateStudentAttendanceInput.getAttendanceDate());
//        }
        if (updateStudentAttendanceInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateStudentAttendanceInput.getStudentId()).get();
            studentAttendance.setStudent(student);
        }
        if (updateStudentAttendanceInput.getLectureId() != null) {
            final Lecture lecture = lectureRepository.findById(updateStudentAttendanceInput.getLectureId()).get();
            studentAttendance.setLecture(lecture);
        }
        studentAttendanceRepository.save(studentAttendance);

        return new UpdateStudentAttendancePayload(studentAttendance);
    }

    public RemoveStudentAttendancePayload removeStudentAttendance(RemoveStudentAttendanceInput removeStudentAttendanceInput) {
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(removeStudentAttendanceInput.getStudentAttendanceId()).get();
        studentAttendanceRepository.delete(studentAttendance);
        return new RemoveStudentAttendancePayload(Lists.newArrayList(studentAttendanceRepository.findAll()));
    }


    public AddTeacherPayload addTeacher(AddTeacherInput addTeacherInput) {
        final Branch branch = branchRepository.findById(addTeacherInput.getBranchId()).get();
        final Department department = departmentRepository.findById(addTeacherInput.getDepartmentId()).get();
        final Teacher teacher = new Teacher();
        teacher.setTeacherName(addTeacherInput.getTeacherName());
        teacher.setTeacherMiddleName(addTeacherInput.getTeacherMiddleName());
        teacher.setTeacherLastName(addTeacherInput.getTeacherLastName());
        teacher.setFatherName(addTeacherInput.getFatherName());
        teacher.setFatherMiddleName(addTeacherInput.getFatherMiddleName());
        teacher.setFatherLastName(addTeacherInput.getFatherLastName());
        teacher.setSpouseName(addTeacherInput.getSpouseName());
        teacher.setSpouseMiddleName(addTeacherInput.getSpouseMiddleName());
        teacher.setSpouseLastName(addTeacherInput.getSpouseLastName());
        teacher.setMotherName(addTeacherInput.getMotherName());
        teacher.setMotherMiddleName(addTeacherInput.getMotherMiddleName());
        teacher.setMotherLastName(addTeacherInput.getMotherLastName());
        teacher.setAadharNo(String.valueOf(addTeacherInput.getAadharNo()));
        teacher.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addTeacherInput.getDateOfBirth()));
        teacher.setPlaceOfBirth(addTeacherInput.getPlaceOfBirth());
        teacher.setReligion(addTeacherInput.getReligion().toString());
        teacher.setCaste(addTeacherInput.getCaste().toString());
        teacher.setSubCaste(addTeacherInput.getSubCaste());
        teacher.setAge(addTeacherInput.getAge());
        teacher.setSex(addTeacherInput.getSex().toString());
        teacher.setBloodGroup(addTeacherInput.getBloodGroup().toString());
        teacher.setAddress(addTeacherInput.getAddressLineOne());
//        teacher.setAddressLineTwo(addTeacherInput.getAddressLineTwo());
//        teacher.setAddressLineThree(addTeacherInput.getAddressLineThree());
        teacher.setTown(addTeacherInput.getTown());
        teacher.setState(addTeacherInput.getState());
        teacher.setCountry(addTeacherInput.getCountry());
        teacher.setPinCode(String.valueOf(addTeacherInput.getPincode()));
        teacher.setTeacherContactNumber(addTeacherInput.getTeacherContactNumber());
        teacher.setAlternateContactNumber(addTeacherInput.getAlternateContactNumber());
        teacher.setTeacherEmailAddress(addTeacherInput.getTeacherEmailAddress());
        teacher.setAlternateEmailAddress(addTeacherInput.getAlternateEmailAddress());
        teacher.setRelationWithStaff(addTeacherInput.getRelationWithStaff().toString());
        teacher.setEmergencyContactName(addTeacherInput.getEmergencyContactName());
        teacher.setEmergencyContactMiddleName(addTeacherInput.getEmergencyContactMiddleName());
        teacher.setEmergencyContactLastName(addTeacherInput.getEmergencyContactLastName());
        teacher.setEmergencyContactNo(addTeacherInput.getEmergencyContactNo());
        teacher.setEmergencyContactEmailAddress(addTeacherInput.getEmergencyContactEmailAddress());
        teacher.setUploadPhoto(addTeacherInput.getUploadPhoto());
        teacher.setEmployeeId(addTeacherInput.getEmployeeId());
        teacher.setDesignation(addTeacherInput.getDesignation());
        teacher.setStaffType(addTeacherInput.getStaffType().toString());
        teacher.setStatus(addTeacherInput.getStatus().toString());
        teacher.setBranch(branch);
        teacher.setDepartment(department);

        teacherRepository.save(teacher);

        return new AddTeacherPayload(teacher);
    }

    public UpdateTeacherPayload updateTeacher(UpdateTeacherInput updateTeacherInput) {
        Teacher teacher = teacherRepository.findById(updateTeacherInput.getId()).get();
        if (updateTeacherInput.getTeacherName() != null) {
            teacher.setTeacherName(updateTeacherInput.getTeacherName());
        }
        if (updateTeacherInput.getTeacherMiddleName() != null) {
            teacher.setTeacherMiddleName(updateTeacherInput.getTeacherMiddleName());
        }
        if (updateTeacherInput.getTeacherLastName() != null) {
            teacher.setTeacherLastName(updateTeacherInput.getTeacherLastName());
        }
        if (updateTeacherInput.getFatherName() != null) {
            teacher.setFatherName(updateTeacherInput.getFatherName());
        }
        if (updateTeacherInput.getFatherMiddleName() != null) {
            teacher.setFatherMiddleName(updateTeacherInput.getFatherMiddleName());
        }
        if (updateTeacherInput.getFatherLastName() != null) {
            teacher.setFatherLastName(updateTeacherInput.getFatherLastName());
        }

        if (updateTeacherInput.getSpouseName() != null) {
            teacher.setSpouseName(updateTeacherInput.getSpouseName());
        }
        if (updateTeacherInput.getSpouseMiddleName() != null) {
            teacher.setSpouseMiddleName(updateTeacherInput.getSpouseMiddleName());
        }
        if (updateTeacherInput.getSpouseLastName() != null) {
            teacher.setSpouseLastName(updateTeacherInput.getSpouseLastName());
        }

        if (updateTeacherInput.getMotherName() != null) {
            teacher.setMotherName(updateTeacherInput.getMotherName());
        }
        if (updateTeacherInput.getMotherMiddleName() != null) {
            teacher.setMotherMiddleName(updateTeacherInput.getMotherMiddleName());
        }
        if (updateTeacherInput.getMotherLastName() != null) {
            teacher.setMotherLastName(updateTeacherInput.getMotherLastName());
        }
        if (updateTeacherInput.getAadharNo() != null) {
            teacher.setAadharNo(String.valueOf(updateTeacherInput.getAadharNo()));
        }
        if (updateTeacherInput.getDateOfBirth() != null) {
            teacher.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateTeacherInput.getDateOfBirth()));
        }
        if (updateTeacherInput.getPlaceOfBirth() != null) {
            teacher.setPlaceOfBirth(updateTeacherInput.getPlaceOfBirth());
        }
        if (updateTeacherInput.getReligion() != null) {
            teacher.setReligion(updateTeacherInput.getReligion().toString());
        }
        if (updateTeacherInput.getCaste() != null) {
            teacher.setCaste(updateTeacherInput.getCaste().toString());
        }
        if (updateTeacherInput.getSubCaste() != null) {
            teacher.setSubCaste(updateTeacherInput.getSubCaste());
        }
        if (updateTeacherInput.getAge() != null) {
            teacher.setAge(updateTeacherInput.getAge());
        }
        if (updateTeacherInput.getSex() != null) {
            teacher.setSex(updateTeacherInput.getSex().toString());
        }
        if (updateTeacherInput.getBloodGroup() != null) {
            teacher.setBloodGroup(updateTeacherInput.getBloodGroup().toString());
        }
        if (updateTeacherInput.getAddressLineOne() != null) {
            teacher.setAddress(updateTeacherInput.getAddressLineOne());
        }
//        if (updateTeacherInput.getAddressLineTwo() != null) {
//            teacher.setAddressLineTwo(updateTeacherInput.getAddressLineTwo());
//        }
//        if (updateTeacherInput.getAddressLineThree() != null) {
//            teacher.setAddressLineThree(updateTeacherInput.getAddressLineThree());
//        }
        if (updateTeacherInput.getTown() != null) {
            teacher.setTown(updateTeacherInput.getTown());
        }
        if (updateTeacherInput.getState() != null) {
            teacher.setState(updateTeacherInput.getState());
        }
        if (updateTeacherInput.getCountry() != null) {
            teacher.setCountry(updateTeacherInput.getCountry());
        }
        if (updateTeacherInput.getPincode() != null) {
            teacher.setPinCode(String.valueOf(updateTeacherInput.getPincode()));
        }
        if (updateTeacherInput.getTeacherContactNumber() != null) {
            teacher.setTeacherContactNumber(updateTeacherInput.getTeacherContactNumber());
        }
        if (updateTeacherInput.getAlternateContactNumber() != null) {
            teacher.setAlternateContactNumber(updateTeacherInput.getAlternateContactNumber());
        }
        if (updateTeacherInput.getTeacherEmailAddress() != null) {
            teacher.setTeacherEmailAddress(updateTeacherInput.getTeacherEmailAddress());
        }
        if (updateTeacherInput.getAlternateEmailAddress() != null) {
            teacher.setAlternateEmailAddress(updateTeacherInput.getAlternateEmailAddress());
        }
        if (updateTeacherInput.getRelationWithStaff() != null) {
            teacher.setRelationWithStaff(updateTeacherInput.getRelationWithStaff().toString());
        }
        if (updateTeacherInput.getEmergencyContactName() != null) {
            teacher.setEmergencyContactName(updateTeacherInput.getEmergencyContactName());
        }
        if (updateTeacherInput.getEmergencyContactMiddleName() != null) {
            teacher.setEmergencyContactMiddleName(updateTeacherInput.getEmergencyContactMiddleName());
        }
        if (updateTeacherInput.getEmergencyContactLastName() != null) {
            teacher.setEmergencyContactLastName(updateTeacherInput.getEmergencyContactLastName());
        }
        if (updateTeacherInput.getEmergencyContactNo() != null) {
            teacher.setEmergencyContactNo(updateTeacherInput.getEmergencyContactNo());
        }
        if (updateTeacherInput.getEmergencyContactEmailAddress() != null) {
            teacher.setEmergencyContactEmailAddress(updateTeacherInput.getEmergencyContactEmailAddress());
        }
        if (updateTeacherInput.getUploadPhoto() != null) {
            teacher.setUploadPhoto(updateTeacherInput.getUploadPhoto());
        }
        if (updateTeacherInput.getEmployeeId() != null) {
            teacher.setEmployeeId(updateTeacherInput.getEmployeeId());
        }
        if (updateTeacherInput.getDesignation() != null) {
            teacher.setDesignation(updateTeacherInput.getDesignation());
        }
        if (updateTeacherInput.getStaffType() != null) {
            teacher.setStaffType(updateTeacherInput.getStaffType().toString());
        }

        if (updateTeacherInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateTeacherInput.getBranchId()).get();
            teacher.setBranch(branch);
        }

        if (updateTeacherInput.getBranchId() != null) {
            final Department department = departmentRepository.findById(updateTeacherInput.getDepartmentId()).get();
            teacher.setDepartment(department);
        }
        teacherRepository.save(teacher);

        return new UpdateTeacherPayload(teacher);
    }

    public RemoveTeacherPayload removeTeacher(RemoveTeacherInput removeTeacherInput) {
        Teacher teacher = teacherRepository.findById(removeTeacherInput.getTeacherId()).get();
        teacherRepository.delete(teacher);
        return new RemoveTeacherPayload(Lists.newArrayList(teacherRepository.findAll()));
    }

    public AddAuthorizedSignatoryPayload addAuthorizedSignatory(AddAuthorizedSignatoryInput addAuthorizedSignatoryInput) {
        final Branch branch = branchRepository.findById(addAuthorizedSignatoryInput.getBranchId()).get();
        final College college = collegeRepository.findById(addAuthorizedSignatoryInput.getCollegeId()).get();
        final AuthorizedSignatory authorizedSignatory = new AuthorizedSignatory();
        authorizedSignatory.setSignatoryName(addAuthorizedSignatoryInput.getSignatoryName());
        authorizedSignatory.setSignatoryFatherName(addAuthorizedSignatoryInput.getSignatoryFatherName());
        authorizedSignatory.setSignatoryDesignation(addAuthorizedSignatoryInput.getSignatoryDesignation());
        authorizedSignatory.setAddress1(addAuthorizedSignatoryInput.getAddress1());
        authorizedSignatory.setAddress2(addAuthorizedSignatoryInput.getAddress2());
        authorizedSignatory.setAddress3(addAuthorizedSignatoryInput.getAddress3());
        authorizedSignatory.setAddress4(addAuthorizedSignatoryInput.getAddress4());
        authorizedSignatory.setAddress5(addAuthorizedSignatoryInput.getAddress5());
        authorizedSignatory.setEmail(addAuthorizedSignatoryInput.getEmail());
        authorizedSignatory.setPanCardNumber(addAuthorizedSignatoryInput.getPanCardNumber());
        authorizedSignatory.setBranch(branch);
        authorizedSignatory.setCollege(college);
        authorizedSignatoryRepository.save(authorizedSignatory);

        return new AddAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public UpdateAuthorizedSignatoryPayload updateAuthorizedSignatory(UpdateAuthorizedSignatoryInput updateAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(updateAuthorizedSignatoryInput.getId()).get();
        if (updateAuthorizedSignatoryInput.getSignatoryName() != null) {
            authorizedSignatory.setSignatoryName(updateAuthorizedSignatoryInput.getSignatoryName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryFatherName() != null) {
            authorizedSignatory.setSignatoryFatherName(updateAuthorizedSignatoryInput.getSignatoryFatherName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryDesignation() != null) {
            authorizedSignatory.setSignatoryDesignation(updateAuthorizedSignatoryInput.getSignatoryDesignation());
        }

        if (updateAuthorizedSignatoryInput.getAddress1() != null) {
            authorizedSignatory.setAddress1(updateAuthorizedSignatoryInput.getAddress1());
        }

        if (updateAuthorizedSignatoryInput.getAddress2() != null) {
            authorizedSignatory.setAddress2(updateAuthorizedSignatoryInput.getAddress2());
        }
        if (updateAuthorizedSignatoryInput.getAddress3() != null) {
            authorizedSignatory.setAddress3(updateAuthorizedSignatoryInput.getAddress3());
        }
        if (updateAuthorizedSignatoryInput.getAddress4() != null) {
            authorizedSignatory.setAddress4(updateAuthorizedSignatoryInput.getAddress4());
        }
        if (updateAuthorizedSignatoryInput.getAddress5() != null) {
            authorizedSignatory.setAddress5(updateAuthorizedSignatoryInput.getAddress5());
        }

        if (updateAuthorizedSignatoryInput.getEmail() != null) {
            authorizedSignatory.setEmail(updateAuthorizedSignatoryInput.getEmail());
        }

        if (updateAuthorizedSignatoryInput.getPanCardNumber() != null) {
            authorizedSignatory.setPanCardNumber(updateAuthorizedSignatoryInput.getPanCardNumber());
        }
        if (updateAuthorizedSignatoryInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateAuthorizedSignatoryInput.getBranchId()).get();
            authorizedSignatory.setBranch(branch);
        }
        if (updateAuthorizedSignatoryInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateAuthorizedSignatoryInput.getCollegeId()).get();
            authorizedSignatory.setCollege(college);
        }

        authorizedSignatoryRepository.save(authorizedSignatory);

        return new UpdateAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public RemoveAuthorizedSignatoryPayload removeAuthorizedSignatory(RemoveAuthorizedSignatoryInput removeAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(removeAuthorizedSignatoryInput.getAuthorizedSignatoryId()).get();
        authorizedSignatoryRepository.delete(authorizedSignatory);
        return new RemoveAuthorizedSignatoryPayload(Lists.newArrayList(authorizedSignatoryRepository.findAll()));
    }

    public AddLegalEntityPayload addLegalEntity(AddLegalEntityInput addLegalEntityInput) {
        final LegalEntity legalEntity = new LegalEntity();
        legalEntity.setLogoFileName(addLegalEntityInput.getLogoFileName());
        legalEntity.setLogoPath(addLegalEntityInput.getLogoPath());
        legalEntity.setLegalNameOfTheCollege(addLegalEntityInput.getLegalNameOfTheCollege());
        legalEntity.setTypeOfCollege(addLegalEntityInput.getTypeOfCollege());
        legalEntity.setRegisteredOfficeAddress1(addLegalEntityInput.getRegisteredOfficeAddress1());
        legalEntity.setRegisteredOfficeAddress2(addLegalEntityInput.getRegisteredOfficeAddress2());
        legalEntity.setRegisteredOfficeAddress3(addLegalEntityInput.getRegisteredOfficeAddress3());
        legalEntity.setRegisteredOfficeAddress4(addLegalEntityInput.getRegisteredOfficeAddress4());
        legalEntity.setRegisteredOfficeAddress5(addLegalEntityInput.getRegisteredOfficeAddress5());
        legalEntity.setDateOfIncorporation(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getDateOfIncorporation()));
        legalEntity.setCollegeIdentificationNumber(addLegalEntityInput.getCollegeIdentificationNumber());
        legalEntity.setPan(addLegalEntityInput.getPan());
        legalEntity.setTan(addLegalEntityInput.getTan());
        legalEntity.setTanCircleNumber(addLegalEntityInput.getTanCircleNumber());
        legalEntity.setCitTdsLocation(addLegalEntityInput.getCitTdsLocation());
        legalEntity.setFormSignatory(addLegalEntityInput.getFormSignatory());
        legalEntity.setPfNumber(addLegalEntityInput.getPfNumber());
        legalEntity.setPfRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getPfRegistrationDate()));
        legalEntity.setPfSignatory(addLegalEntityInput.getPfSignatory());
        legalEntity.setEsiNumber(addLegalEntityInput.getEsiNumber());
        legalEntity.setEsiRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getEsiRegistrationDate()));
        legalEntity.setEsiSignatory(addLegalEntityInput.getEsiSignatory());
        legalEntity.setPtNumber(addLegalEntityInput.getPtNumber());
        legalEntity.setPtRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getPtRegistrationDate()));
        legalEntity.setPtSignatory(addLegalEntityInput.getPtSignatory());
        Branch branch = branchRepository.findById(addLegalEntityInput.getBranchId()).get();
        College college = collegeRepository.findById(addLegalEntityInput.getCollegeId()).get();
        City city = cityRepository.findById(addLegalEntityInput.getCityId()).get();
        State state = stateRepository.findById(addLegalEntityInput.getStateId()).get();
        legalEntity.setBranch(branch);
        legalEntity.setCollege(college);
        legalEntity.setCity(city);
        legalEntity.setState(state);
        legalEntityRepository.save(legalEntity);

        return new AddLegalEntityPayload(legalEntity);
    }

    public UpdateLegalEntityPayload updateLegalEntity(UpdateLegalEntityInput updateLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(updateLegalEntityInput.getId()).get();
        if (updateLegalEntityInput.getLogoFileName() != null) {
            legalEntity.setLogoFileName(updateLegalEntityInput.getLogoFileName());
        }
        if (updateLegalEntityInput.getLogoPath() != null) {
            legalEntity.setLogoPath(updateLegalEntityInput.getLogoPath());
        }
        if (updateLegalEntityInput.getLegalNameOfTheCollege() != null) {
            legalEntity.setLegalNameOfTheCollege(updateLegalEntityInput.getLegalNameOfTheCollege());
        }
        if (updateLegalEntityInput.getTypeOfCollege() != null) {
            legalEntity.setTypeOfCollege(updateLegalEntityInput.getTypeOfCollege());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress1() != null) {
            legalEntity.setRegisteredOfficeAddress1(updateLegalEntityInput.getRegisteredOfficeAddress1());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress2() != null) {
            legalEntity.setRegisteredOfficeAddress2(updateLegalEntityInput.getRegisteredOfficeAddress2());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress3() != null) {
            legalEntity.setRegisteredOfficeAddress3(updateLegalEntityInput.getRegisteredOfficeAddress3());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress4() != null) {
            legalEntity.setRegisteredOfficeAddress4(updateLegalEntityInput.getRegisteredOfficeAddress4());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress5() != null) {
            legalEntity.setRegisteredOfficeAddress5(updateLegalEntityInput.getRegisteredOfficeAddress5());
        }
        if (updateLegalEntityInput.getDateOfIncorporation() != null) {
            legalEntity.setDateOfIncorporation(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getDateOfIncorporation()));
        }

        if (updateLegalEntityInput.getCollegeIdentificationNumber() != null) {
            legalEntity.setCollegeIdentificationNumber(updateLegalEntityInput.getCollegeIdentificationNumber());
        }
        if (updateLegalEntityInput.getPan() != null) {
            legalEntity.setPan(updateLegalEntityInput.getPan());
        }
        if (updateLegalEntityInput.getTan() != null) {
            legalEntity.setTan(updateLegalEntityInput.getTan());
        }
        if (updateLegalEntityInput.getTanCircleNumber() != null) {
            legalEntity.setTanCircleNumber(updateLegalEntityInput.getTanCircleNumber());
        }
        if (updateLegalEntityInput.getCitTdsLocation() != null) {
            legalEntity.setCitTdsLocation(updateLegalEntityInput.getCitTdsLocation());
        }
        if (updateLegalEntityInput.getFormSignatory() != null) {
            legalEntity.setFormSignatory(updateLegalEntityInput.getFormSignatory());
        }
        if (updateLegalEntityInput.getPfNumber() != null) {
            legalEntity.setPfNumber(updateLegalEntityInput.getPfNumber());
        }
        if (updateLegalEntityInput.getPfRegistrationDate() != null) {
            legalEntity.setPfRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getPfRegistrationDate()));
        }
        if (updateLegalEntityInput.getPfSignatory() != null) {
            legalEntity.setPfSignatory(updateLegalEntityInput.getPfSignatory());
        }

        if (updateLegalEntityInput.getEsiNumber() != null) {
            legalEntity.setEsiNumber(updateLegalEntityInput.getEsiNumber());
        }
        if (updateLegalEntityInput.getEsiRegistrationDate() != null) {
            legalEntity.setEsiRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getEsiRegistrationDate()));
        }
        if (updateLegalEntityInput.getEsiSignatory() != null) {
            legalEntity.setEsiSignatory(updateLegalEntityInput.getEsiSignatory());
        }

        if (updateLegalEntityInput.getPtRegistrationDate() != null) {
            legalEntity.setPtRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getPtRegistrationDate()));
        }
        if (updateLegalEntityInput.getPtSignatory() != null) {
            legalEntity.setPtSignatory(updateLegalEntityInput.getPtSignatory());
        }
        if (updateLegalEntityInput.getPtNumber() != null) {
            legalEntity.setPtNumber(updateLegalEntityInput.getPtNumber());
        }

        if (updateLegalEntityInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateLegalEntityInput.getBranchId()).get();
            legalEntity.setBranch(branch);
        }
        if (updateLegalEntityInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateLegalEntityInput.getCollegeId()).get();
            legalEntity.setCollege(college);
        }
        if (updateLegalEntityInput.getCityId() != null) {
            City city = cityRepository.findById(updateLegalEntityInput.getCityId()).get();
            legalEntity.setCity(city);
        }

        if (updateLegalEntityInput.getStateId() != null) {
            State state = stateRepository.findById(updateLegalEntityInput.getStateId()).get();
            legalEntity.setState(state);
        }
        legalEntityRepository.save(legalEntity);

        return new UpdateLegalEntityPayload(legalEntity);
    }


    public AddStudentExamReportPayload addStudentExamReport(AddStudentExamReportInput addStudentExamReportInput) {
        final StudentExamReport studentExamReport = new StudentExamReport();

        TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(addStudentExamReportInput.getTypeOfGradingId()).get();
        AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(addStudentExamReportInput.getAcademicExamSettingId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addStudentExamReportInput.getAcademicyearId()).get();
        Student student = studentRepository.findById(addStudentExamReportInput.getStudentId()).get();
        Batch batch = batchRepository.findById(addStudentExamReportInput.getBatchId()).get();
        Department department = departmentRepository.findById(addStudentExamReportInput.getDepartmentId()).get();
        Subject subject = subjectRepository.findById(addStudentExamReportInput.getSubjectId()).get();
        Section section =sectionRepository.findById(addStudentExamReportInput.getSectionId()).get();
        studentExamReport.setTypeOfGrading(typeOfGrading);
        studentExamReport.setAcademicyearId(addStudentExamReportInput.getAcademicyearId());
        studentExamReport.setAcademicExamSetting(academicExamSetting);
        studentExamReport.setBatchId(addStudentExamReportInput.getBatchId());
        studentExamReport.setStudent(student);
        studentExamReport.setDepartmentId(addStudentExamReportInput.getDepartmentId());
        studentExamReport.setSectionId(addStudentExamReportInput.getSectionId());
        studentExamReport.setSubjectId(addStudentExamReportInput.getSubjectId());
        studentExamReport.setMarksObtained(addStudentExamReportInput.getMarksObtained());
        studentExamReport.setComments(addStudentExamReportInput.getComments());
        studentExamReport.setgOp(addStudentExamReportInput.getgOp());
        studentExamReportRepository.save(studentExamReport);
        return new AddStudentExamReportPayload(studentExamReport);
    }

    public UpdateStudentExamReportPayload updateStudentExamReport(UpdateStudentExamReportInput updateStudentExamReportInput) {
        StudentExamReport studentExamReport = studentExamReportRepository.findById(updateStudentExamReportInput.getId()).get();


        if (updateStudentExamReportInput.getMarksObtained() != null) {
            studentExamReport.setMarksObtained(updateStudentExamReportInput.getMarksObtained());
        }
        if (updateStudentExamReportInput.getComments() != null) {
            studentExamReport.setComments(updateStudentExamReportInput.getComments());
        }
        if (updateStudentExamReportInput.getgOp() != null) {
            studentExamReport.setgOp(updateStudentExamReportInput.getgOp());
        }
        if (updateStudentExamReportInput.getAcademicExamSettingId() != null) {
            final AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(updateStudentExamReportInput.getAcademicExamSettingId()).get();
            studentExamReport.setAcademicExamSetting(academicExamSetting);
        }
        if (updateStudentExamReportInput.getAcademicyearId() != null) {
//            final AcademicYear academicYear = academicYearRepository.findById(updateStudentExamReportInput.getAcademicyearId()).get();
            studentExamReport.setAcademicyearId(updateStudentExamReportInput.getAcademicyearId());
        }
        if (updateStudentExamReportInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateStudentExamReportInput.getStudentId()).get();
            studentExamReport.setStudent(student);
        }
        if (updateStudentExamReportInput.getBatchId() != null) {
//            final Batch batch = batchRepository.findById(updateStudentExamReportInput.getBatchId()).get();
            studentExamReport.setBatchId(updateStudentExamReportInput.getBatchId());
        }
        if (updateStudentExamReportInput.getDepartmentId() != null) {
//            final Department department = departmentRepository.findById(updateStudentExamReportInput.getDepartmentId()).get();
            studentExamReport.setDepartmentId(updateStudentExamReportInput.getDepartmentId());
        }
        if (updateStudentExamReportInput.getTypeOfGradingId() != null) {
            final TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(updateStudentExamReportInput.getTypeOfGradingId()).get();
            studentExamReport.setTypeOfGrading(typeOfGrading);
        }
        if (updateStudentExamReportInput.getSectionId() != null) {
//            final Section section = sectionRepository.findById(updateStudentExamReportInput.getSectionId()).get();
            studentExamReport.setSectionId(updateStudentExamReportInput.getSectionId());
        }
        if (updateStudentExamReportInput.getSubjectId() != null) {
//            final Subject subject = subjectRepository.findById(updateStudentExamReportInput.getSubjectId()).get();
            studentExamReport.setSubjectId(updateStudentExamReportInput.getSubjectId());
        }
        studentExamReportRepository.save(studentExamReport);

        return new UpdateStudentExamReportPayload(studentExamReport);
    }

    public RemoveStudentExamReportPayload removeStudentExamReport(RemoveStudentExamReportInput removeStudentExamReportsInput) {
        StudentExamReport studentExamReport = studentExamReportRepository.findById(removeStudentExamReportsInput.getStudentExamReportId()).get();
        studentExamReportRepository.delete(studentExamReport);
        return new RemoveStudentExamReportPayload(Lists.newArrayList(studentExamReportRepository.findAll()));
    }

    public AddAcademicYearPayload addAcademicYear(AddAcademicYearInput addAcademicYearInput) {
        final AcademicYear academicYear = new AcademicYear();
        academicYear.setYear(addAcademicYearInput.getYear());
        academicYear.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addAcademicYearInput.getStartDate()));
        academicYear.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addAcademicYearInput.getEndDate()));
        academicYear.setStatus(addAcademicYearInput.getStatus());
        academicYearRepository.save(academicYear);
        return new AddAcademicYearPayload(academicYear);
    }

    public AddAdminAttendancePayLoad addAdminAttendance(AddAdminAttendanceInput addAdminAttendanceInput) {
        final AdminAttendance adminAttendance = new AdminAttendance();
        adminAttendance.setUpdatedBy(addAdminAttendanceInput.getUpdatedBy());
        adminAttendance.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addAdminAttendanceInput.getUpdatedOn()));
        Branch branch = branchRepository.findById(addAdminAttendanceInput.getBranchId()).get();
        final College college = collegeRepository.findById(addAdminAttendanceInput.getCollegeId()).get();
        final Lecture lecture = lectureRepository.findById(addAdminAttendanceInput.getLectureId()).get();
        final Department department = departmentRepository.findById(addAdminAttendanceInput.getDepartmentId()).get();
        final AcademicYear academicYear = academicYearRepository.findById(addAdminAttendanceInput.getAcademicyearId()).get();
        final Section section = sectionRepository.findById(addAdminAttendanceInput.getSectionId()).get();
        final Student student = studentRepository.findById(addAdminAttendanceInput.getStudentId()).get();
        adminAttendance.setBranch(branch);
        adminAttendance.setCollege(college);
        adminAttendance.setLecture(lecture);
        adminAttendance.setDepartment(department);
        adminAttendance.setAcademicyear(academicYear);
        adminAttendance.setSection(section);
        adminAttendance.setStudent(student);
        adminAttendanceRepository.save(adminAttendance);
        return new AddAdminAttendancePayLoad(adminAttendance);
    }

    public UpdateAdminAttendancePayLoad updateAdminAttendance(UpdateAdminAttendanceInput updateAdminAttendanceInput) {
        AdminAttendance adminAttendance = adminAttendanceRepository.findById(updateAdminAttendanceInput.getId()).get();
        if (updateAdminAttendanceInput.getUpdatedBy() != null) {
            adminAttendance.setUpdatedBy(updateAdminAttendanceInput.getUpdatedBy());
        }
        if (updateAdminAttendanceInput.getUpdatedOn() != null) {
            adminAttendance.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateAdminAttendanceInput.getUpdatedOn()));
        }
        if (updateAdminAttendanceInput.getSectionId() != null) {
            final Section section = sectionRepository.findById(updateAdminAttendanceInput.getSectionId()).get();
            adminAttendance.setSection(section);
        }
        if (updateAdminAttendanceInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateAdminAttendanceInput.getBranchId()).get();
            adminAttendance.setBranch(branch);
        }
        if (updateAdminAttendanceInput.getDepartmentId() != null) {
            final Department department = departmentRepository.findById(updateAdminAttendanceInput.getDepartmentId()).get();
            adminAttendance.setDepartment(department);
        }
        if (updateAdminAttendanceInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateAdminAttendanceInput.getAcademicyearId()).get();
            adminAttendance.setAcademicyear(academicYear);
        }
        if (updateAdminAttendanceInput.getLectureId() != null) {
            final Lecture lecture = lectureRepository.findById(updateAdminAttendanceInput.getLectureId()).get();
            adminAttendance.setLecture(lecture);
        }
        if (updateAdminAttendanceInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateAdminAttendanceInput.getStudentId()).get();
            adminAttendance.setStudent(student);
        }
        if (updateAdminAttendanceInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateAdminAttendanceInput.getCollegeId()).get();
            adminAttendance.setCollege(college);
        }
        adminAttendanceRepository.save(adminAttendance);

        return new UpdateAdminAttendancePayLoad(adminAttendance);
    }

    public RemoveAdminAttendancePayLoad removeAdminAttendance(RemoveAdminAttendanceInput removeAdminAttendanceInput) {
        AdminAttendance adminAttendance = adminAttendanceRepository.findById(removeAdminAttendanceInput.getAdminAttendanceId()).get();
        adminAttendanceRepository.delete(adminAttendance);
        return new RemoveAdminAttendancePayLoad(Lists.newArrayList(adminAttendanceRepository.findAll()));
    }

    public RemoveLegalEntityPayload removeLegalEntity(RemoveLegalEntityInput removeLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(removeLegalEntityInput.getLegalEntityId()).get();
        legalEntityRepository.delete(legalEntity);
        return new RemoveLegalEntityPayload(Lists.newArrayList(legalEntityRepository.findAll()));
    }

    public UpdateAcademicYearPayload updateAcademicYear(UpdateAcademicYearInput updateAcademicYearInput) {
        AcademicYear academicYear = academicYearRepository.findById(updateAcademicYearInput.getId()).get();
        if (updateAcademicYearInput.getYear() != null) {
            academicYear.setYear(updateAcademicYearInput.getYear());
        }
        if (updateAcademicYearInput.getStartDate() != null) {
            academicYear.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAcademicYearInput.getStartDate()));
        }
        if (updateAcademicYearInput.getEndDate() != null) {
            academicYear.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAcademicYearInput.getEndDate()));
        }

        if (updateAcademicYearInput.getStatus() != null){
            academicYear.setStatus(updateAcademicYearInput.getStatus());
        }

        academicYearRepository.save(academicYear);

        return new UpdateAcademicYearPayload(academicYear);
    }

    public RemoveAcademicYearPayload removeAcademicYear(RemoveAcademicYearInput removeAcademicYearInput) {
        AcademicYear academicYear = academicYearRepository.findById(removeAcademicYearInput.getAcademicYearId()).get();
        academicYearRepository.delete(academicYear);
        return new RemoveAcademicYearPayload(Lists.newArrayList(academicYearRepository.findAll()));
    }

    public AddHolidayPayload addHoliday(AddHolidayInput addHolidayInput) {
        final AcademicYear academicYear = academicYearRepository.findById(addHolidayInput.getAcademicYearId()).get();
        final Holiday holiday = new Holiday();
        holiday.setHolidayDesc(addHolidayInput.getHolidayDesc());
        holiday.setHolidayDate(DateFormatUtil.convertLocalDateFromUtilDate(addHolidayInput.getHolidayDate()));
        holiday.setHolidayStatus(addHolidayInput.getHolidayStatus());
        holiday.setAcademicyear(academicYear);
        holidayRepository.save(holiday);

        return new AddHolidayPayload(holiday);
    }
    public UpdateHolidayPayload updateHoliday(UpdateHolidayInput updateHolidayInput) {
        Holiday holiday = holidayRepository.findById(updateHolidayInput.getId()).get();
        if (updateHolidayInput.getHolidayDesc() != null) {
            holiday.setHolidayDesc(updateHolidayInput.getHolidayDesc());
        }
        if (updateHolidayInput.getHolidayDate() != null) {
            holiday.setHolidayDate(DateFormatUtil.convertLocalDateFromUtilDate(updateHolidayInput.getHolidayDate()));
        }

        if (updateHolidayInput.getHolidayStatus() != null) {
            holiday.setHolidayStatus(updateHolidayInput.getHolidayStatus());
        }
        if (updateHolidayInput.getAcademicYearId() != null) {
            final AcademicYear academicYear = academicYearRepository.findById(updateHolidayInput.getAcademicYearId()).get();
            holiday.setAcademicyear(academicYear);
        }
        holidayRepository.save(holiday);

        return new UpdateHolidayPayload(holiday);
    }

    public RemoveHolidayPayload removeHoliday(RemoveHolidayInput removeHolidayInput) {
        Holiday holiday = holidayRepository.findById(removeHolidayInput.getHolidayId()).get();
        holidayRepository.delete(holiday);
        return new RemoveHolidayPayload(Lists.newArrayList(holidayRepository.findAll()));
    }

    public AddTermPayload addTerm(AddTermInput addTermInput) {
        final AcademicYear academicYear = academicYearRepository.findById(addTermInput.getAcademicYearId()).get();
        final Term term = new Term();
        term.setTermsDesc(addTermInput.getTermsDesc());
        term.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addTermInput.getStartDate()));
        term.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addTermInput.getEndDate()));
        term.setTermStatus(addTermInput.getTermStatus());
        term.setAcademicyear(academicYear);
        termRepository.save(term);

        return new AddTermPayload(term);
    }

    public UpdateTermPayload updateTerm(UpdateTermInput updateTermInput) {
        Term term = termRepository.findById(updateTermInput.getId()).get();

        if (updateTermInput.getTermsDesc() != null) {
            term.setTermsDesc(updateTermInput.getTermsDesc());
        }
        if (updateTermInput.getStartDate() != null) {
            term.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateTermInput.getStartDate()));
        }
        if (updateTermInput.getEndDate() != null) {
            term.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateTermInput.getEndDate()));
        }
        if (updateTermInput.getTermStatus() != null) {
            term.setTermStatus(updateTermInput.getTermStatus());
        }
        if (updateTermInput.getAcademicYearId() != null) {
            final AcademicYear academicYear = academicYearRepository.findById(updateTermInput.getAcademicYearId()).get();
            term.setAcademicyear(academicYear);
        }
        termRepository.save(term);

        return new UpdateTermPayload(term);
    }

    public RemoveTermPayload removeTerm(RemoveTermInput removeTermInput) {
        Term term = termRepository.findById(removeTermInput.getTermId()).get();
        termRepository.delete(term);
        return new RemoveTermPayload(Lists.newArrayList(termRepository.findAll()));
    }

    public AddTeachPayload addTeach(AddTeachInput addTeachInput) {
        final Teacher teacher = teacherRepository.findById(addTeachInput.getTeacherId()).get();
        final Subject subject = subjectRepository.findById(addTeachInput.getSubjectId()).get();
        final Teach teach = new Teach();
        teach.setDesc(addTeachInput.getDesc());
        teach.setTeacher(teacher);
        teach.setSubject(subject);
        teachRepository.save(teach);
        return new AddTeachPayload(teach);
    }

    public UpdateTeachPayload updateTeach(UpdateTeachInput updateTeachInput) {
        Teach teach = teachRepository.findById(updateTeachInput.getId()).get();

        if (updateTeachInput.getDesc() != null) {
            teach.setDesc(updateTeachInput.getDesc());
        }
        if (updateTeachInput.getTeacherId() != null) {
            final Teacher teacher = teacherRepository.findById(updateTeachInput.getTeacherId()).get();
            teach.setTeacher(teacher);
        }
        if (updateTeachInput.getSubjectId() != null) {
            final Subject subject = subjectRepository.findById(updateTeachInput.getSubjectId()).get();
            teach.setSubject(subject);
        }
        teachRepository.save(teach);
        return new UpdateTeachPayload(teach);
    }

    public RemoveTeachPayload removeTeach(RemoveTeachInput removeTeachInput) {
        Teach teach = teachRepository.findById(removeTeachInput.getTeachId()).get();
        teachRepository.delete(teach);
        return new RemoveTeachPayload(Lists.newArrayList(teachRepository.findAll()));
    }

    public AddAttendanceMasterPayload addAttendanceMaster(AddAttendanceMasterInput addAttendanceMasterInput) {
        final Teach teach = teachRepository.findById(addAttendanceMasterInput.getTeachId()).get();
        final Section section = sectionRepository.findById(addAttendanceMasterInput.getSectionId()).get();
        final Batch batch = batchRepository.findById(addAttendanceMasterInput.getBatchId()).get();
        final AttendanceMaster attendanceMaster = new AttendanceMaster();

        attendanceMaster.setDesc(addAttendanceMasterInput.getDesc());
        attendanceMaster.setTeach(teach);
        attendanceMaster.setSection(section);
        attendanceMaster.setBatch(batch);
        attendanceMasterRepository.save(attendanceMaster);
        return new AddAttendanceMasterPayload(attendanceMaster);
    }

    public UpdateAttendanceMasterPayload updateAttendanceMaster(UpdateAttendanceMasterInput updateAttendanceMasterInput) {
        AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(updateAttendanceMasterInput.getId()).get();

        if (updateAttendanceMasterInput.getDesc() != null) {
            attendanceMaster.setDesc(updateAttendanceMasterInput.getDesc());
        }
        if (updateAttendanceMasterInput.getTeachId() != null) {
            Teach teach = teachRepository.findById(updateAttendanceMasterInput.getTeachId()).get();
            attendanceMaster.setTeach(teach);
        }
        if (updateAttendanceMasterInput.getSectionId() != null) {
            Section section = sectionRepository.findById(updateAttendanceMasterInput.getSectionId()).get();
            attendanceMaster.setSection(section);
        }
        if (updateAttendanceMasterInput.getBatchId() != null) {
            Batch batch = batchRepository.findById(updateAttendanceMasterInput.getBatchId()).get();
            attendanceMaster.setBatch(batch);
        }
        attendanceMasterRepository.save(attendanceMaster);
        return new UpdateAttendanceMasterPayload(attendanceMaster);
    }

    public RemoveAttendanceMasterPayload removeAttendanceMaster(RemoveAttendanceMasterInput removeAttendanceMasterInput) {
        AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(removeAttendanceMasterInput.getAttendanceMasterId()).get();
        attendanceMasterRepository.delete(attendanceMaster);
        return new RemoveAttendanceMasterPayload(Lists.newArrayList(attendanceMasterRepository.findAll()));
    }




    public UpdateLecturePayload updateLecture(UpdateLectureInput updateLectureInput) {
        Lecture lecture = lectureRepository.findById(updateLectureInput.getId()).get();

        if (updateLectureInput.getLecDate() != null) {
            lecture.setLecDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLectureInput.getLecDate()));
        }
        if (updateLectureInput.getLastUpdatedBy() != null) {
            lecture.setLastUpdatedBy(updateLectureInput.getLastUpdatedBy());
        }
        if (updateLectureInput.getLastUpdatedOn() != null) {
            lecture.setLastUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateLectureInput.getLastUpdatedOn()));
        }
        if (updateLectureInput.getStartTime() != null) {
            lecture.setStartTime(updateLectureInput.getStartTime());
        }
        if (updateLectureInput.getEndTime() != null) {
            lecture.setEndTime(updateLectureInput.getEndTime());
        }
        if (updateLectureInput.getAttendanceMasterId() != null) {
            final AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(updateLectureInput.getAttendanceMasterId()).get();
            lecture.setAttendancemaster(attendanceMaster);
        }
        lectureRepository.save(lecture);
        return new UpdateLecturePayload(lecture);
    }

    public RemoveLecturePayload removeLecture(RemoveLectureInput removeLectureInput) {
        Lecture lecture = lectureRepository.findById(removeLectureInput.getLectureId()).get();
        lectureRepository.delete(lecture);
        return new RemoveLecturePayload(Lists.newArrayList(lectureRepository.findAll()));
    }
//    public List<Book> addBook(List<AddBookInput> list){
//        List<Book> al= new ArrayList<>();
//        Book b = null;
//        for(AddBookInput input: list ) {
//            Student student = studentRepository.findById(input.getStudentId()).get();
//            Library library = libraryRepository.findById(input.getLibraryId()).get();
//
//            b = CommonUtil.createCopyProperties(input, Book.class);
//            b.setIssueDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getIssueDate()));
//            b.setDueDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getDueDate()));
//            b.setReceivedDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getReceivedDate()));
//            b.setNoOfCopiesAvailable(input.getNoOfCopiesAvailable());
//            b.setStatus(input.getStatus());
//            b.setStudent(student);
//            b.setLibrary(library);
//            bookRepository.save(b);
//        }
//        al.add(b);
//        return al;
//    }
    public List<CmsBook> addBook(List<AddBookInput> inputlist) {
        List<CmsBook> al = new ArrayList<>();
        for (AddBookInput input : inputlist) {
            Student student = studentRepository.findById(input.getStudentId()).get();
            Library library = libraryRepository.findById(input.getLibraryId()).get();
            Book b = CommonUtil.createCopyProperties(input, Book.class);

            b.setIssueDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getIssueDate()));
            b.setDueDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getDueDate()));
            b.setReceivedDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getReceivedDate()));
            b.setNoOfCopiesAvailable(input.getNoOfCopiesAvailable());
            b.setStatus(input.getStatus());
            b.setStudent(student);
            b.setLibrary(library);
            bookRepository.save(b);

            Book bb = new Book();
            bb.setStudent(student);
            bb.setLibrary(library);
            Example<Book> example = Example.of(bb);
            List<Book> list = this.bookRepository.findAll(example, Sort.by(Direction.DESC, "id"));
//            List<CmsBook> ls = new ArrayList<>();
            for (Book ff : list) {
                CmsBook cfc = CommonUtil.createCopyProperties(ff, CmsBook.class);
                if (ff.getIssueDate() != null) {
                    cfc.setStrIssueDate(DateFormatUtil.changeLocalDateFormat(ff.getIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                    cfc.setIssueDate(null);
                }

                if (ff.getDueDate() != null) {
                    cfc.setStrDueDate(DateFormatUtil.changeLocalDateFormat(ff.getDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                    cfc.setDueDate(null);
                }

                if (ff.getReceivedDate() != null) {
                    cfc.setStrRecDate(DateFormatUtil.changeLocalDateFormat(ff.getReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                    cfc.setReceivedDate(null);
                }
                al.add(cfc);
            }

        }
        return al;
    }


    public List<CmsFeeCategory> addFeeCategory(AddFeeCategoryInput addFeeCategoryInput) throws Exception {
        FeeCategory fc = CommonUtil.createCopyProperties(addFeeCategoryInput, FeeCategory.class);
        fc.setCreatedOn(LocalDate.now());
        fc.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addFeeCategoryInput.getStartDate()));
        fc.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addFeeCategoryInput.getEndDate()));
        Branch branch = new Branch();
        branch.setId(addFeeCategoryInput.getBranchId());
        fc.setBranchId(addFeeCategoryInput.getBranchId());
        fc = feeCategoryRepository.save(fc);

        FeeCategory f = new FeeCategory();
//        f.setBranch(branch);
        Example<FeeCategory> example = Example.of(f);
        List<FeeCategory> list = this.feeCategoryRepository.findAll(example, Sort.by(Direction.DESC, "id"));
        List<CmsFeeCategory> ls = new ArrayList<>();
        for(FeeCategory ff: list) {
        	CmsFeeCategory cfc = CommonUtil.createCopyProperties(ff, CmsFeeCategory.class);
//        	if(ff.getStartDate() != null) {
//        		cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getStartDate()))));
//        	}
//        	if(ff.getEndDate() != null) {
//        		cfc.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getEndDate()))));
//        	}
        	if(ff.getStartDate() != null) {
            	cfc.setStrStartDate(DateFormatUtil.changeLocalDateFormat(ff.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setStartDate(null);
            }
            if(ff.getEndDate() != null) {
            	cfc.setStrEndDate(DateFormatUtil.changeLocalDateFormat(ff.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setEndDate(null);
            }
            if(ff.getCreatedOn() != null) {
            	cfc.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ff.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setCreatedOn(null);
            }
            if(ff.getUpdatedOn() != null) {
            	cfc.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ff.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setUpdatedOn(null);
            }
            ls.add(cfc);
        }
        return ls;
    }
    public List<CmsBook> updateBook(UpdateBookInput updateBookInput) throws ParseException, Exception {

        Book fc = CommonUtil.createCopyProperties(updateBookInput, Book.class);
        fc.setStatus(updateBookInput.getStatus());
        fc.setNoOfCopiesAvailable(updateBookInput.getNoOfCopiesAvailable());
        fc.setIssueDate(DateFormatUtil.convertLocalDateFromUtilDate(updateBookInput.getIssueDate()));
        fc.setDueDate(DateFormatUtil.convertLocalDateFromUtilDate(updateBookInput.getDueDate()));
        fc.setReceivedDate(DateFormatUtil.convertLocalDateFromUtilDate(updateBookInput.getReceivedDate()));
        Library lib = new Library();
        lib.setId(updateBookInput.getLibraryId());
        fc.setLibrary(lib);
        Student st = new Student();
        st.setId(updateBookInput.getStudentId());
        fc.setStudent(st);


        fc = bookRepository.save(fc);

        Book f = new Book();
        f.setLibrary(lib);
        f.setStudent(st);

        Example<Book> example = Example.of(f);
        List<Book> list = this.bookRepository.findAll(example, Sort.by(Direction.DESC, "id"));
        List<CmsBook> ls = new ArrayList<>();
        for(Book ff: list) {
            CmsBook cfc = CommonUtil.createCopyProperties(ff, CmsBook.class);

            if(ff.getIssueDate() != null) {
                cfc.setStrIssueDate(DateFormatUtil.changeLocalDateFormat(ff.getIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfc.setIssueDate(null);
            }
            if(ff.getDueDate() != null) {
                cfc.setStrDueDate(DateFormatUtil.changeLocalDateFormat(ff.getDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfc.setDueDate(null);
            }
            if(ff.getReceivedDate() != null) {
                cfc.setStrRecDate(DateFormatUtil.changeLocalDateFormat(ff.getReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfc.setReceivedDate(null);
            }
            cfc.setNoOfCopiesAvailable(ff.getNoOfCopiesAvailable());
            ls.add(cfc);
        }
        return ls;

    }

    public List<CmsFeeCategory> updateFeeCategory(UpdateFeeCategoryInput updateFeeCategoryInput) throws ParseException, Exception {
//        FeeCategory feeCategory = feeCategoryRepository.findById(updateFeeCategoryInput.getId()).get();
        FeeCategory fc = CommonUtil.createCopyProperties(updateFeeCategoryInput, FeeCategory.class);
        fc.setUpdatedOn(LocalDate.now());
        fc.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFeeCategoryInput.getStartDate()));
        fc.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFeeCategoryInput.getEndDate()));
//        Branch branch = new Branch();
//        branch.setId(updateFeeCategoryInput.getBranchId());
        fc.setBranchId(updateFeeCategoryInput.getBranchId());
        fc = feeCategoryRepository.save(fc);

        FeeCategory f = new FeeCategory();
//        f.setBranch(branch);
        Example<FeeCategory> example = Example.of(f);
        List<FeeCategory> list = this.feeCategoryRepository.findAll(example, Sort.by(Direction.DESC, "id"));
        List<CmsFeeCategory> ls = new ArrayList<>();
        for(FeeCategory ff: list) {
        	CmsFeeCategory cfc = CommonUtil.createCopyProperties(ff, CmsFeeCategory.class);
//        	if(ff.getStartDate() != null) {
//        		cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getStartDate()))));
//        	}
//        	if(ff.getEndDate() != null) {
//        		cfc.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getEndDate()))));
//        	}
        	if(ff.getStartDate() != null) {
            	cfc.setStrStartDate(DateFormatUtil.changeLocalDateFormat(ff.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setStartDate(null);
            }
            if(ff.getEndDate() != null) {
            	cfc.setStrEndDate(DateFormatUtil.changeLocalDateFormat(ff.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setEndDate(null);
            }
            if(ff.getCreatedOn() != null) {
            	cfc.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ff.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setCreatedOn(null);
            }
            if(ff.getUpdatedOn() != null) {
            	cfc.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ff.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setUpdatedOn(null);
            }
            ls.add(cfc);
        }
        return ls;

    }

    public RemoveFeeCategoryPayload removeFeeCategory(RemoveFeeCategoryInput removeFeeCategoryInput) {
        FeeCategory feeCategory = feeCategoryRepository.findById(removeFeeCategoryInput.getFeeCategoryId()).get();
        feeCategoryRepository.delete(feeCategory);
        return new RemoveFeeCategoryPayload(Lists.newArrayList(feeCategoryRepository.findAll()));
    }

    public CmsFeeDetails addFeeDetails(AddFeeDetailsInput addFeeDetailsInput) throws ParseException, Exception {

    	FeeCategory feeCategory = feeCategoryRepository.findById(addFeeDetailsInput.getFeeCategoryId()).get();

//        Batch batch = null;
//        if(addFeeDetailsInput.getBatchId() != null) {
//        	batch = batchRepository.findById(addFeeDetailsInput.getBatchId()).get();
//        }

        Facility facility = null;
        if(addFeeDetailsInput.getFacilityId() != null) {
        	facility = facilityRepository.findById(addFeeDetailsInput.getFacilityId()).get();
        }

        TransportRoute transportRoute = null;
        if(addFeeDetailsInput.getTransportRouteId() != null) {
        	transportRoute = transportRouteRepository.findById(addFeeDetailsInput.getTransportRouteId()).get();
        }

//        Department department = null;
//        if(addFeeDetailsInput.getDepartmentId() != null) {
//        	department = departmentRepository.findById(addFeeDetailsInput.getDepartmentId()).get();
//        }

        FeeDetails feeDetails = CommonUtil.createCopyProperties(addFeeDetailsInput, FeeDetails.class);
        feeDetails.setFeeCategory(feeCategory);
        feeDetails.setBatchId(addFeeDetailsInput.getBatchId());
        feeDetails.setFacility(facility);
        feeDetails.setTransportRoute(transportRoute);
        feeDetails.setDepartmentId(addFeeDetailsInput.getDepartmentId());
        feeDetails.createdOn(LocalDate.now());
        feeDetails.startDate(LocalDate.now());
        feeDetails = feeDetailsRepository.save(feeDetails);
        CmsFeeDetails cfd = CommonUtil.createCopyProperties(feeDetails, CmsFeeDetails.class);
        if(feeDetails.getStartDate() != null) {
        	cfd.setStrStartDate(DateFormatUtil.changeLocalDateFormat(feeDetails.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	cfd.setStartDate(null);
//    		cfd.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(feeDetails.getStartDate()))));
    	}
        if(feeDetails.getEndDate() != null) {
        	cfd.setStrEndDate(DateFormatUtil.changeLocalDateFormat(feeDetails.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	cfd.setEndDate(null);
    	}
    	if(feeDetails.getCreatedOn() != null) {
    		cfd.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(feeDetails.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	cfd.setCreatedOn(null);
//    		cfd.setStrCreatedOn(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(feeDetails.getCreatedOn()))));
    	}
    	if(feeDetails.getUpdatedOn() != null) {
    		cfd.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(feeDetails.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	cfd.setUpdatedOn(null);
    	}
        return cfd;
    }


    public UpdateFeeDetailsPayload updateFeeDetails(UpdateFeeDetailsInput updateFeeDetailsInput) {
        FeeDetails feeDetails = feeDetailsRepository.findById(updateFeeDetailsInput.getId()).get();
        if (updateFeeDetailsInput.getFeeParticularsName() != null) {
            feeDetails.setFeeParticularsName(updateFeeDetailsInput.getFeeParticularsName());
        }

        if (updateFeeDetailsInput.getFeeParticularDesc() != null) {
            feeDetails.setFeeParticularDesc(updateFeeDetailsInput.getFeeParticularDesc());
        }

        if (updateFeeDetailsInput.getStudentType() != null) {
            feeDetails.setStudentType(updateFeeDetailsInput.getStudentType());
        }

        if (updateFeeDetailsInput.getGender() != null) {
            feeDetails.setGender(updateFeeDetailsInput.getGender());
        }
        if (updateFeeDetailsInput.getAmount() != null) {
            feeDetails.setAmount(updateFeeDetailsInput.getAmount());
        }
        logger.debug("Amount : "+updateFeeDetailsInput.getAmount());
        if (updateFeeDetailsInput.getDepartmentId() != null) {
//            Department department = departmentRepository.findById(updateFeeDetailsInput.getDepartmentId()).get();
            feeDetails.setDepartmentId(updateFeeDetailsInput.getDepartmentId());
        }
//        if (updateFeeDetailsInput.getBranchId() != null) {
//            Branch branch = branchRepository.findById(updateFeeDetailsInput.getBranchId()).get();
//            feeDetails.setBranch(branch);
//        }
//        if (updateFeeDetailsInput.getCollegeId() != null) {
//            College college = collegeRepository.findById(updateFeeDetailsInput.getCollegeId()).get();
//            feeDetails.setCollege(college);
//        }
//        if (updateFeeDetailsInput.getAcademicyearId() != null) {
//            AcademicYear academicYear = academicYearRepository.findById(updateFeeDetailsInput.getAcademicyearId()).get();
//            feeDetails.setAcademicYear(academicYear);
//        }
        if (updateFeeDetailsInput.getFeeCategoryId() != null) {
            FeeCategory feeCategory = feeCategoryRepository.findById(updateFeeDetailsInput.getFeeCategoryId()).get();
            feeDetails.setFeeCategory(feeCategory);
        }
        if (updateFeeDetailsInput.getBatchId() != null) {
//            Batch batch = batchRepository.findById(updateFeeDetailsInput.getBatchId()).get();
            feeDetails.setBatchId(updateFeeDetailsInput.getBatchId());
        }

        if (updateFeeDetailsInput.getFacilityId() != null) {
            Facility facility = facilityRepository.findById(updateFeeDetailsInput.getFacilityId()).get();
            feeDetails.setFacility(facility);
        }
        if (updateFeeDetailsInput.getTransportRouteId() != null) {
            TransportRoute transportRoute = transportRouteRepository.findById(updateFeeDetailsInput.getTransportRouteId()).get();
            feeDetails.setTransportRoute(transportRoute);
        }

        feeDetailsRepository.save(feeDetails);
        return new UpdateFeeDetailsPayload(feeDetails);
    }

    public RemoveFeeDetailsPayload removeFeeDetails(RemoveFeeDetailsInput removeFeeDetailsInput) {
        FeeDetails feeDetails = feeDetailsRepository.findById(removeFeeDetailsInput.getFeeDetailsId()).get();
        feeDetailsRepository.delete(feeDetails);
        return new RemoveFeeDetailsPayload(Lists.newArrayList(feeDetailsRepository.findAll()));
    }

    public CmsAdmissionApplicationVo addAdmissionPersonaldetails(AddAdmissionPersonalDetailsInput addAdmissionPersonalDetailsInput) {

    	// will not pass country id
    	// from branch id, state, city will be identified. and from selected state/city, its country will be identified
//        Country country = countryRepository.findById(addAdmissionPersonalDetailsInput.getCountryId()).get();

        AdmissionApplication admissionApplication = CommonUtil.createCopyProperties(addAdmissionPersonalDetailsInput, AdmissionApplication.class);
        admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionPersonalDetailsInput.getDateOfBirth()));
        admissionApplication = admissionApplicationRepository.save(admissionApplication);
        String dt = DateFormatUtil.changeLocalDateFormat(admissionApplication.getDateOfBirth(), CmsConstants.DATE_FORMAT_MM_dd_yyyy);
        CmsAdmissionApplicationVo caa = CommonUtil.createCopyProperties(admissionApplication, CmsAdmissionApplicationVo.class);
        caa.setDateOfBirth(null);
        caa.setStrDateOfBirth(dt);
        logger.debug("date of birth : "+dt);

       return caa;
    }

    public CmsAdmissionApplicationVo updateAdmissionPersonaldetails(UpdateAdmissionPersonalDetailsInput updateAdmissionPersonalDetailsInput) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(updateAdmissionPersonalDetailsInput.getId()).get();


        if (updateAdmissionPersonalDetailsInput.getStudentName() != null) {
            admissionApplication.setStudentName(updateAdmissionPersonalDetailsInput.getStudentName());
        }
        if (updateAdmissionPersonalDetailsInput.getStudentMiddleName() != null) {
            admissionApplication.setStudentMiddleName(updateAdmissionPersonalDetailsInput.getStudentMiddleName());
        }
        if (updateAdmissionPersonalDetailsInput.getStudentLastName() != null) {
            admissionApplication.setStudentLastName(updateAdmissionPersonalDetailsInput.getStudentLastName());
        }
        if (updateAdmissionPersonalDetailsInput.getFatherName() != null) {
            admissionApplication.setFatherName(updateAdmissionPersonalDetailsInput.getFatherName());
        }
        if (updateAdmissionPersonalDetailsInput.getFatherMiddleName() != null) {
            admissionApplication.setFatherMiddleName(updateAdmissionPersonalDetailsInput.getFatherMiddleName());
        }
        if (updateAdmissionPersonalDetailsInput.getFatherLastName() != null) {
            admissionApplication.setFatherLastName(updateAdmissionPersonalDetailsInput.getFatherLastName());
        }
        if (updateAdmissionPersonalDetailsInput.getMotherName() != null) {
            admissionApplication.setMotherName(updateAdmissionPersonalDetailsInput.getMotherName());
        }
        if (updateAdmissionPersonalDetailsInput.getMotherMiddleName() != null) {
            admissionApplication.setMotherMiddleName(updateAdmissionPersonalDetailsInput.getMotherMiddleName());
        }
        if (updateAdmissionPersonalDetailsInput.getMotherLastName() != null) {
            admissionApplication.setMotherLastName(updateAdmissionPersonalDetailsInput.getMotherLastName());
        }
        if (updateAdmissionPersonalDetailsInput.getContactNumber() != null) {
            admissionApplication.setContactNumber(updateAdmissionPersonalDetailsInput.getContactNumber());
        }
        if (updateAdmissionPersonalDetailsInput.getAlternateMobileNumber() != null) {
            admissionApplication.setAlternateMobileNumber(updateAdmissionPersonalDetailsInput.getAlternateMobileNumber());
        }
        if (updateAdmissionPersonalDetailsInput.getDateOfBirth() != null) {
            admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionPersonalDetailsInput.getDateOfBirth()));
        }
        if (updateAdmissionPersonalDetailsInput.getEmail() != null) {
            admissionApplication.setEmail(updateAdmissionPersonalDetailsInput.getEmail());
        }
        if (updateAdmissionPersonalDetailsInput.getSex() != null) {
            admissionApplication.setSex(updateAdmissionPersonalDetailsInput.getSex());
        }

        if (updateAdmissionPersonalDetailsInput.getCountryId() != null) {
            Country country = countryRepository.findById(updateAdmissionPersonalDetailsInput.getCountryId()).get();
            admissionApplication.setCountry(country);
        }


        admissionApplicationRepository.save(admissionApplication);

        CmsAdmissionApplicationVo caa = CommonUtil.createCopyProperties(admissionApplication, CmsAdmissionApplicationVo.class);
        return caa;
    }

    public RemoveAdmissionPersonalDetailsPayload removeAdmissionPersonalDetails(RemoveAdmissionPersonalDetailsInput removeAdmissionPersonalDetailsInput) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(removeAdmissionPersonalDetailsInput.getAdmissionApplicationId()).get();

        admissionApplicationRepository.delete(admissionApplication);
        return new RemoveAdmissionPersonalDetailsPayload(Lists.newArrayList(admissionApplicationRepository.findAll()));
    }

    public AddFacilityPayload addFacility(AddFacilityInput addFacilityInput) {
        final Branch branch = branchRepository.findById(addFacilityInput.getBranchId()).get();
//        final Student student = studentRepository.findById(addFacilityInput.getStudentId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addFacilityInput.getAcademicyearId()).get();
        Facility facility = new Facility();

        facility.setAcademicYear(academicYear);
        facility.setBranch(branch);
        facility.setName(addFacilityInput.getName());
        facility.setStatus(Status.ACTIVE);
        facility.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addFacilityInput.getStartDate()));

        facilityRepository.save(facility);

        return new AddFacilityPayload(facility);
    }

    public UpdateFacilityPayload updateFacility(UpdateFacilityInput updateFacilityInput) {
        Facility facility = facilityRepository.findById(updateFacilityInput.getId()).get();

        if (updateFacilityInput.getName() != null) {
            facility.setName(updateFacilityInput.getName());
        }
        if (updateFacilityInput.getStatus() != null) {
            facility.setStatus(updateFacilityInput.getStatus());
        }
        if (updateFacilityInput.getStartDate() != null) {
            facility.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFacilityInput.getStartDate()));
        }
        if (updateFacilityInput.getEndDate() != null) {
            facility.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFacilityInput.getEndDate()));
        }
        if (updateFacilityInput.getSuspandStartDate() != null) {
            facility.setSuspandStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFacilityInput.getSuspandStartDate()));
        }
        if (updateFacilityInput.getSuspandEndDate() != null) {
            facility.setSuspandEndDate(DateFormatUtil.convertLocalDateFromUtilDate (updateFacilityInput.getSuspandEndDate()));
        }


        if (updateFacilityInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateFacilityInput.getBranchId()).get();
            facility.setBranch(branch);
        }
        if (updateFacilityInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateFacilityInput.getAcademicyearId()).get();
            facility.setAcademicYear(academicYear);
        }
        facilityRepository.save(facility);

        return new UpdateFacilityPayload(facility);

    }

    public RemoveFacilityPayload removeFacility(RemoveFacilityInput removeFacilityInput) {
        Facility facility = facilityRepository.getOne(removeFacilityInput.getFacilityId());
        facilityRepository.delete(facility);

        return new RemoveFacilityPayload(Lists.newArrayList(facilityRepository.findAll()));
    }

    public AddDueDatePayload addDueDate(AddDueDateInput addDueDateInput) {
//        College college = collegeRepository.findById(addDueDateInput.getCollegeId()).get();
//        Branch branch = branchRepository.findById((addDueDateInput.getBranchId())).get();
        DueDate dueDate = new DueDate();
        dueDate.setPaymentMethod(addDueDateInput.getPaymentMethod());
        dueDate.setInstallments(addDueDateInput.getInstallments());
        dueDate.setDayDesc(addDueDateInput.getDayDesc());
        dueDate.setPaymentDay(addDueDateInput.getPaymentDay());
        dueDate.setFrequency(findFrequency(addDueDateInput.getFrequency()));
        dueDate.setBranchId(addDueDateInput.getBranchId());
        dueDate.setCollegeId(addDueDateInput.getCollegeId());
        dueDate = dueDateRepository.save(dueDate);
        return new AddDueDatePayload(dueDate);
    }

    private Frequency findFrequency(Frequency frequency) {
    	if(Frequency.WEEKLY.equals(frequency)) {
    		return Frequency.WEEKLY;
    	}else if(Frequency.MONTHLY.equals(frequency)) {
    		return Frequency.MONTHLY;
    	}else if(Frequency.QUARTERLY.equals(frequency)) {
    		return Frequency.QUARTERLY;
    	}else if(Frequency.HALFYEARLY.equals(frequency)) {
    		return Frequency.HALFYEARLY;
    	}else if(Frequency.ANNUALLY.equals(frequency)) {
    		return Frequency.ANNUALLY;
    	}
    	return null;
    }

    public UpdateDueDatePayload updateDueDate(UpdateDueDateInput updateDueDateInput) {
        DueDate dueDate = dueDateRepository.findById(updateDueDateInput.getId()).get();
        if (updateDueDateInput.getPaymentMethod() != null) {
            dueDate.setPaymentMethod(updateDueDateInput.getPaymentMethod());
        }
        if (updateDueDateInput.getInstallments() != null) {
            dueDate.setInstallments(updateDueDateInput.getInstallments());
        }
        if (updateDueDateInput.getDayDesc() != null) {
            dueDate.setDayDesc(updateDueDateInput.getDayDesc());
        }
        if (updateDueDateInput.getPaymentDay() != null) {
            dueDate.setPaymentDay(updateDueDateInput.getPaymentDay());
        }
        if (updateDueDateInput.getFrequency() != null) {
            dueDate.setFrequency(updateDueDateInput.getFrequency());
        }
        if (updateDueDateInput.getCollegeId() != null) {
//            College college = collegeRepository.findById(updateDueDateInput.getCollegeId()).get();
            dueDate.setCollegeId(updateDueDateInput.getCollegeId());

        }
        if (updateDueDateInput.getBranchId() != null) {
//            Branch branch = branchRepository.findById(updateDueDateInput.getBranchId()).get();
            dueDate.setBranchId(updateDueDateInput.getBranchId());

        }
        dueDateRepository.save(dueDate);
        return new UpdateDueDatePayload(dueDate);
    }

    public RemoveDueDatePayload removeDueDate(RemoveDueDateInput removeDueDateInput) {
        DueDate dueDate = dueDateRepository.findById(removeDueDateInput.getDueDateId()).get();
        return new RemoveDueDatePayload(Lists.newArrayList(dueDateRepository.findAll()));
    }

    public AddLateFeePayload addLateFee(AddLateFeeInput addLateFeeInput) {
//        final College college = collegeRepository.findById(addLateFeeInput.getCollegeId()).get();
//        final Branch branch = branchRepository.findById(addLateFeeInput.getBranchId()).get();
        LateFee lateFee = CommonUtil.createCopyProperties(addLateFeeInput, LateFee.class);
//        lateFee.setCollegeId(addLateFeeInput.getCollegeId());
//        lateFee.setBranchId(addLateFeeInput.getBranchId());
        lateFee = lateFeeRepository.save(lateFee);
        return new AddLateFeePayload(lateFee);
    }

    public UpdateLateFeePayload updateLateFee(UpdateLateFeeInput updateLateFeeInput) {
        LateFee lateFee = CommonUtil.createCopyProperties(updateLateFeeInput, LateFee.class);

//        if (updateLateFeeInput.getCollegeId() != null) {
//            final College college = collegeRepository.findById(updateLateFeeInput.getCollegeId()).get();
//            lateFee.setCollegeId(updateLateFeeInput.getCollegeId());
//        }
//
//        if (updateLateFeeInput.getBranchId() != null) {
//            final Branch branch = branchRepository.findById(updateLateFeeInput.getBranchId()).get();
//            lateFee.setBranchId(updateLateFeeInput.getBranchId());
//        }
        lateFeeRepository.save(lateFee);
        return new UpdateLateFeePayload(lateFee);
    }

    public RemoveLateFeePayload removeLateFee(RemoveLateFeeInput removeLateFeeInput) {
        LateFee lateFee = lateFeeRepository.findById(removeLateFeeInput.getLateFeeId()).get();
        lateFeeRepository.delete(lateFee);
        return new RemoveLateFeePayload(Lists.newArrayList(lateFeeRepository.findAll()));
    }

    public AddPaymentRemainderPayload addPaymentRemainder(AddPaymentRemainderInput addPaymentRemainderInput) {
//        final College college = collegeRepository.findById(addPaymentRemainderInput.getCollegeId()).get();
//        final Branch branch = branchRepository.findById(addPaymentRemainderInput.getBranchId()).get();
        PaymentRemainder pr = CommonUtil.createCopyProperties(addPaymentRemainderInput, PaymentRemainder.class);
//        pr.setCollegeId(addPaymentRemainderInput.getCollegeId());
        pr.setBranchId(addPaymentRemainderInput.getBranchId());
        pr = paymentRemainderRepository.save(pr);
        return new AddPaymentRemainderPayload(pr);
    }

    public UpdatePaymentRemainderPayload updatePaymentRemainder(UpdatePaymentRemainderInput updatePaymentRemainderInput) {
        PaymentRemainder pr = CommonUtil.createCopyProperties(updatePaymentRemainderInput, PaymentRemainder.class);

//        if (updatePaymentRemainderInput.getCollegeId() != null) {
//            final College college = collegeRepository.findById(updatePaymentRemainderInput.getCollegeId()).get();
//            pr.setCollegeId(updatePaymentRemainderInput.getCollegeId());
//        }
//        if (updatePaymentRemainderInput.getBranchId() != null) {
//            final Branch branch = branchRepository.findById(updatePaymentRemainderInput.getBranchId()).get();
//            pr.setBranchId(updatePaymentRemainderInput.getBranchId());
//        }
        paymentRemainderRepository.save(pr);
        return new UpdatePaymentRemainderPayload(pr);
    }

    public RemovePaymentRemainderPayload removePaymentRemainder(RemovePaymentRemainderInput removePaymentRemainderInput) {
        PaymentRemainder paymentRemainder = paymentRemainderRepository.findById(removePaymentRemainderInput.getPaymentRemainderId()).get();
        paymentRemainderRepository.delete(paymentRemainder);
        return new RemovePaymentRemainderPayload(Lists.newArrayList(paymentRemainderRepository.findAll()));
    }


    public AddInvoicePayload addInvoice(AddInvoiceInput addInvoiceInput) {
        FeeCategory feeCategory = feeCategoryRepository.findById(addInvoiceInput.getFeeCategoryId()).get();
//        Branch branch = branchRepository.findById(addInvoiceInput.getBranchId()).get();
//        College college = collegeRepository.findById(addInvoiceInput.getCollegeId()).get();
//        AcademicYear academicYear = academicYearRepository.findById(addInvoiceInput.getAcademicyearId()).get();
        FeeDetails feeDetails = feeDetailsRepository.findById(addInvoiceInput.getFeeDetailsId()).get();
        DueDate dueDate = dueDateRepository.findById(addInvoiceInput.getDueDateId()).get();
        Student student = studentRepository.findById(addInvoiceInput.getStudentId()).get();
        PaymentRemainder paymentRemainder = paymentRemainderRepository.findById(addInvoiceInput.getPaymentRemainderId()).get();
        final Invoice invoice   = new Invoice();
        invoice.setInvoiceNumber(addInvoiceInput.getInvoiceNumber());
        invoice.setAmountPaid(addInvoiceInput.getAmountPaid());
        invoice.setPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(addInvoiceInput.getPaymentDate()));
        invoice.setNextPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(addInvoiceInput.getNextPaymentDate()));
        invoice.setOutStandingAmount(addInvoiceInput.getOutStandingAmount());
        invoice.setModeOfPayment(addInvoiceInput.getModeOfPayment());
        invoice.setChequeNumber(addInvoiceInput.getChequeNumber());
        invoice.setDemandDraftNumber(addInvoiceInput.getDemandDraftNumber());
        invoice.setOnlineTxnRefNumber(addInvoiceInput.getOnlineTxnRefNumber());
        invoice.setPaymentStatus(addInvoiceInput.getPaymentStatus());
        invoice.setComments(addInvoiceInput.getComments());
        invoice.setUpdatedBy(addInvoiceInput.getUpdatedBy());
        invoice.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addInvoiceInput.getUpdatedOn()));
        invoice.setFeeCategory(feeCategory);
        invoice.setFeeDetails(feeDetails);
        invoice.setDueDate(dueDate);
        invoice.setPaymentRemainder(paymentRemainder);
        invoice.setCollegeId(addInvoiceInput.getCollegeId());
        invoice.setBranchId(addInvoiceInput.getBranchId());
        invoice.setAcademicYearId(addInvoiceInput.getAcademicyearId());
        invoice.setStudent(student);
        invoiceRepository.save(invoice);
        return new AddInvoicePayload(invoice);
    }

    public UpdateInvoicePayload updateInvoice(UpdateInvoiceInput updateInvoiceInput) {
        Invoice invoice = invoiceRepository.findById(updateInvoiceInput.getId()).get();
        if (updateInvoiceInput.getInvoiceNumber() != null) {
            invoice.setInvoiceNumber(updateInvoiceInput.getInvoiceNumber());
        }

        if (updateInvoiceInput.getAmountPaid() != null) {
            invoice.setAmountPaid(updateInvoiceInput.getAmountPaid());
        }

        if (updateInvoiceInput.getPaymentDate() != null) {
            invoice.setPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(updateInvoiceInput.getPaymentDate()));
        }

        if (updateInvoiceInput.getNextPaymentDate() != null) {
            invoice.setNextPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(updateInvoiceInput.getNextPaymentDate()));
        }
        if (updateInvoiceInput.getOutStandingAmount() != null) {
            invoice.setOutStandingAmount(updateInvoiceInput.getOutStandingAmount());
        }

        if (updateInvoiceInput.getModeOfPayment() != null) {
            invoice.setModeOfPayment(updateInvoiceInput.getModeOfPayment());
        }

        if (updateInvoiceInput.getChequeNumber() != null) {
            invoice.setChequeNumber(updateInvoiceInput.getChequeNumber());
        }

        if (updateInvoiceInput.getDemandDraftNumber() != null) {
            invoice.setDemandDraftNumber(updateInvoiceInput.getDemandDraftNumber());
        }

        if (updateInvoiceInput.getOnlineTxnRefNumber() != null) {
            invoice.setOnlineTxnRefNumber(updateInvoiceInput.getOnlineTxnRefNumber());
        }
        if (updateInvoiceInput.getPaymentStatus() != null) {
            invoice.setPaymentStatus(updateInvoiceInput.getPaymentStatus());
        }

        if (updateInvoiceInput.getComments() != null) {
            invoice.setComments(updateInvoiceInput.getComments());
        }

        if (updateInvoiceInput.getUpdatedBy() != null) {
            invoice.setUpdatedBy(updateInvoiceInput.getUpdatedBy());
        }

        if (updateInvoiceInput.getUpdatedOn() != null) {
            invoice.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateInvoiceInput.getUpdatedOn()));
        }


        if(updateInvoiceInput.getFeeCategoryId() != null) {
            FeeCategory feeCategory = feeCategoryRepository.findById(updateInvoiceInput.getFeeCategoryId()).get();
            invoice.setFeeCategory(feeCategory);
        }
        if(updateInvoiceInput.getFeeDetailsId() != null) {
            FeeDetails feeDetails = feeDetailsRepository.findById(updateInvoiceInput.getFeeDetailsId()).get();
            invoice.setFeeDetails(feeDetails);
        }
        if(updateInvoiceInput.getCollegeId() != null) {
//            College college =collegeRepository.findById(updateInvoiceInput.getCollegeId()).get();
            invoice.setCollegeId(updateInvoiceInput.getCollegeId());
        }
        if(updateInvoiceInput.getAcademicyearId() != null) {
//            AcademicYear academicYear = academicYearRepository.findById(updateInvoiceInput.getAcademicyearId()).get();
            invoice.setAcademicYearId(updateInvoiceInput.getAcademicyearId());
        }
        if(updateInvoiceInput.getDueDateId() != null) {
            DueDate dueDate = dueDateRepository.findById(updateInvoiceInput.getDueDateId()).get();
            invoice.setDueDate(dueDate);
        }
        if(updateInvoiceInput.getBranchId() != null) {
//            Branch branch = branchRepository.findById(updateInvoiceInput.getBranchId()).get();
            invoice.setBranchId(updateInvoiceInput.getBranchId());
        }

        if(updateInvoiceInput.getStudentId() != null) {
            Student student = studentRepository.findById(updateInvoiceInput.getStudentId()).get();
            invoice.setStudent(student);
        }
        if(updateInvoiceInput.getPaymentRemainderId() != null) {
            PaymentRemainder paymentRemainder = paymentRemainderRepository.findById(updateInvoiceInput.getPaymentRemainderId()).get();
            invoice.setPaymentRemainder(paymentRemainder);
        }

        invoiceRepository.save(invoice);
        return new UpdateInvoicePayload(invoice);
    }

    public RemoveInvoicePayload removeInvoice(RemoveInvoiceInput removeInvoiceInput) {
        Invoice invoice = invoiceRepository.findById(removeInvoiceInput.getInvoiceId()).get();
        invoiceRepository.delete(invoice);
        return new RemoveInvoicePayload(Lists.newArrayList(invoiceRepository.findAll()));
    }

    public AddLibraryPayload addLibrary(AddLibraryInput libraryInput){
        Batch batch = batchRepository.findById(libraryInput.getBatchId()).get();
        Subject subject = subjectRepository.findById(libraryInput.getSubjectId()).get();
        final Library book = new Library ();
        book.setBookTitle(libraryInput.getBookTitle());
        book.setAuthor(libraryInput.getAuthor());
        book.setBookNo(libraryInput.getBookNo());
        book.setNoOfCopies(libraryInput.getNoOfCopies());
        book.setAdditionalInfo(libraryInput.getAdditionalInfo());
        book.setUniqueNo(libraryInput.getUniqueNo());
        book.setBatch(batch);
        book.setSubject(subject);
        libraryRepository.save(book);
        return new AddLibraryPayload(book);
    }
    public UpdateLibraryPayload updateLibrary(UpdateLibraryInput updateLibraryInput) {
        Library book = libraryRepository.findById(updateLibraryInput.getId()).get();
        if(updateLibraryInput.getBookTitle()!=null) {
            book.setBookTitle(updateLibraryInput.getBookTitle());
        }
        if (updateLibraryInput.getAuthor() != null){
            book.setAuthor(updateLibraryInput.getAuthor());
        }
        if(updateLibraryInput.getBookNo()!=null){
            book.setBookNo(updateLibraryInput.getBookNo());
        }
        if (updateLibraryInput.getNoOfCopies() != null) {
            book.setNoOfCopies(updateLibraryInput.getNoOfCopies());
        }
        if (updateLibraryInput.getAdditionalInfo() != null) {
            book.setAdditionalInfo(updateLibraryInput.getAdditionalInfo());
        }
        if (updateLibraryInput.getUniqueNo() != null) {
            book.setUniqueNo(updateLibraryInput.getUniqueNo());
        }

        if(updateLibraryInput.getBatchId()!=null){
            Batch batch =batchRepository.findById(updateLibraryInput.getBatchId()).get();
            book.setBatch(batch);
        }
        if(updateLibraryInput.getSubjectId()!=null){
            Subject subject =subjectRepository.findById(updateLibraryInput.getSubjectId()).get();
            book.setSubject(subject);
        }

        libraryRepository.save(book);
        return new UpdateLibraryPayload(book);

    }
    public RemoveLibraryPayload removeLibrary(RemoveLibraryInput removeLibraryInput){
        Library book =libraryRepository.findById(removeLibraryInput.getLibraryId()).get();
        libraryRepository.delete(book);
        return new RemoveLibraryPayload((Lists.newArrayList(libraryRepository.findAll())));
    }
//    public AddAcademicExamSettingPayload addAcademicExamSetting(List<AddAcademicExamSettingInput> list) {
//        AcademicExamSetting academicExamSetting = null;
//        int countvalue = getCountvalueId()+1;
//        for(AddAcademicExamSettingInput input: list ) {
//            Branch branch = branchRepository.findById(input.getBranchId()).get();
//            Subject subject = subjectRepository.findById(input.getSubjectId()).get();
//            AcademicYear academicYear = academicYearRepository.findById(input.getAcademicyearId()).get();
//            Batch batch = batchRepository.findById(input.getBatchId()).get();
//            Section section = sectionRepository.findById(input.getSectionId()).get();
//            Department department = departmentRepository.findById(input.getDepartmentId()).get();
//            TypeOfGrading typeOfGrading = typeOfGradingRepository.findById((input.getTypeOfGradingId())).get();
//
//            academicExamSetting = CommonUtil.createCopyProperties(input, AcademicExamSetting.class);
//            academicExamSetting.setCountvalue(new Long(countvalue));
//            academicExamSetting.setBranch(branch);
//            academicExamSetting.setSubject(subject);
//            academicExamSetting.setBatch(batch);
//            academicExamSetting.setAcademicyear(academicYear);
//            academicExamSetting.setSection(section);
//            academicExamSetting.setDepartment(department);
//            academicExamSetting.setTypeOfGrading(typeOfGrading);
//            academicExamSetting.setExamDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getExamDate()));
//            this.academicExamSettingRepository.save(academicExamSetting);
//
//        }
//        return  new AddAcademicExamSettingPayload(academicExamSetting);
//    }

//    public UpdateBookPayload updateBook(UpdateBookInput updateBookInput) {
//        Book b = bookRepository.findById(updateBookInput.getId()).get();
//        if(updateBookInput.getIssueDate()!=null) {
//            b.setIssueDate(DateFormatUtil.convertLocalDateFromUtilDate(updateBookInput.getIssueDate()));
//        }
//        if (updateBookInput.getDueDate() != null){
//            b.setDueDate(DateFormatUtil.convertLocalDateFromUtilDate(updateBookInput.getDueDate()));
//        }
//        if (updateBookInput.getReceivedDate() != null){
//            b.setReceivedDate(DateFormatUtil.convertLocalDateFromUtilDate(updateBookInput.getReceivedDate()));
//        }
//        if(updateBookInput.getNoOfCopiesAvailable()!=null){
//            b.setNoOfCopiesAvailable(updateBookInput.getNoOfCopiesAvailable());
//        }
//        if (updateBookInput.getStatus() != null) {
//            b.setStatus(updateBookInput.getStatus());
//        }
//
//        if(updateBookInput.getStudentId()!=null){
//            Student student =studentRepository.findById(updateBookInput.getStudentId()).get();
//            b.setStudent(student);
//        }
//        if(updateBookInput.getLibraryId()!=null){
//            Library library =libraryRepository.findById(updateBookInput.getLibraryId()).get();
//            b.setLibrary(library);
//        }
//        bookRepository.save(b);
//        return new UpdateBookPayload(b);
//
//    }
    public RemoveBookPayload removeBook(RemoveBookInput removeBookInput){
        Book b =bookRepository.findById(removeBookInput.getBookId()).get();
        bookRepository.delete(b);
        return new RemoveBookPayload((Lists.newArrayList(bookRepository.findAll())));
    }




    public AddLecturePayload addLecture(AddLectureInput addLectureInput) {
        final AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(addLectureInput.getAttendanceMasterId()).get();
        final Lecture lecture = new Lecture();
        lecture.setLecDate(DateFormatUtil.convertLocalDateFromUtilDate(addLectureInput.getLecDate()));
        lecture.setLastUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addLectureInput.getLastUpdatedOn()));
        lecture.setLastUpdatedBy(addLectureInput.getLastUpdatedBy());
        lecture.setStartTime(addLectureInput.getStartTime());
        lecture.setEndTime(addLectureInput.getEndTime());
        lecture.setAttendancemaster(attendanceMaster);
        lectureRepository.save(lecture);
        return new AddLecturePayload(lecture);
    }



    /**
     * Method to update student attendance data. It accepts a list of objects containing student attendance ids and lecture id.
     * It returns the status as zero (0) if method completes successfully or non zero (>0) if execution fails due to any reason.
     * @param list
     * @return QueryResult
     * @throws JSONException
     * @throws ParseException
     */
    public QueryResult updateStudentAttendanceData(List<StudentAttendanceUpdateFilter> list) throws JSONException, ParseException {
        logger.debug("Mutation to update student attendance data " + list.toString());
    	QueryResult res = this.studentAttendanceFilterImpl.updateStudentStatus(list);
    	if(res.getStatusCode() == 0) {
    		logger.info("Student attendance data updated successfully.");
    	}else {
    		logger.info("Due to some error student attendance data could not be updated successfully.");
    	}
        return res;
    }
    public QueryResult updateAcademicExamSettingData(List<AcademicExamSettingUpdateFilter> list) throws JSONException, ParseException {
        logger.debug("Mutation to update exam data " + list.toString());
        QueryResult res = this.academicExamSettingFilterImpl.updateExam(list);
        if(res.getStatusCode() == 0) {
            logger.info("exam data updated successfully.");
        }else {
            logger.info("Due to some error exam data could not be updated successfully.");
        }
        return res;
    }
    public QueryResult updateAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        QueryResult res = this.academicSubjectProcessor.updateAcademicSubjects(academicSubjectMutationPayload);
        return res;
    }

    public QueryResult addAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        QueryResult res = this.academicSubjectProcessor.addAcademicSubjects(academicSubjectMutationPayload);
        return res;
    }

    public QueryResult addLectures(LectureScheduleInput lectureScheduleInput, LectureScheduleFilter filter) throws JSONException, ParseException {
        QueryResult res = lectureScheduleProcessor.addLectureSchedule(lectureScheduleInput, filter);
        return res;
    }

    public QueryResult updateLectures(LectureScheduleInput lectureScheduleInput, LectureScheduleFilter filter) {
        QueryResult res = lectureScheduleProcessor.updateLectureSchedule(lectureScheduleInput, filter);
        return res;
    }

    /**
     * getDailyStudentAttendanceData(StudentAttendanceFilterInput filter)
     * Retrieves student attendance data for teacher role end user
     * @param filter
     * @return
     * @throws Exception
     */
    public List<DailyAttendanceVo> getDailyStudentAttendanceData(StudentAttendanceFilterInput filter) throws Exception {
        return Lists.newArrayList(studentAttendanceFilterImpl.getStudenceAttendanceDataForTeacher(filter));
    }
    public List<CmsInvoice> searchInvoice(String invoiceNumber, Long studentId, Long collegeId, Long branchId, Long academicYearId) throws Exception{
        return Lists.newArrayList(invoiceFilterProcessor.searchInvoice(invoiceNumber, studentId, collegeId, branchId, academicYearId));
    }
    public List<CmsLibrary>searchLib(String bookTitle, String author,Long batchId, Long subjectId)throws Exception{
        return Lists.newArrayList(libraryFilterProcessor.searchLib( bookTitle,  author, batchId, subjectId));
    }
    public Long getTotalInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalInvoice(collegeId, branchId, academicYearId);
    }

    public Long getTotalPaidInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalPaidInvoice(collegeId, branchId, academicYearId);
    }

    public Long getTotalUnPaidInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalUnPaidInvoice(collegeId, branchId, academicYearId);
    }

    public Long getTotalCanceledInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalCanceledInvoice(collegeId, branchId, academicYearId);
    }

//    public List<CmsInvoice> searchInvoiceOnType(String invoiceType, Long collegeId, Long branchId, Long academicYearId) throws Exception{
//        return Lists.newArrayList(invoiceFilterProcessor.searchInvoiceOnType(invoiceType, collegeId, branchId, academicYearId));
//    }

    public Long getTotalAdmissions( long branchId) {
        return admissionEnquiryProcessor.getTotalAdmissions( branchId);
    }

    public Long getTotalFollowup( long branchId) {
        return admissionEnquiryProcessor.getTotalFollowup( branchId);
    }
    public Long getTotalDeclined(long branchId) {
        return admissionEnquiryProcessor.getTotalDeclined( branchId);
    }

    public Long getTotalConverted( long branchId) {
        return admissionEnquiryProcessor.getTotalConverted(branchId);
    }


    public List<CmsAdmissionEnquiryVo> searchAdmissionOnType(String admissionEnquiryType,Long branchId) throws Exception{
        return Lists.newArrayList(admissionEnquiryProcessor.searchAdmissionOnType(admissionEnquiryType, branchId));
    }

    public List<CmsAdmissionEnquiryVo> admissionEnquiryList(Long branchId) throws Exception{
        return Lists.newArrayList(admissionEnquiryProcessor.admissionEnquiryList(branchId));
    }

    public List<CmsStudentVo> getStudentList(StudentListFilterInput filter) throws Exception {
    	List<Student> list = this.studentFilterProcessor.searchStudent(filter);
    	List<CmsStudentVo> ls = new ArrayList<>();
    	for(Student student: list) {
    		CmsStudentVo vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
    		vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(student.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setDateOfBirth(null);
//    		vo.setDepartmentId(student.getDepartmentId() != null ? student.getDepartmentId() : null);
//    		vo.setBatchId(student.getBatchId() != null ? student.getBatchId() : null);
//    		vo.setSectionId(student.getSectionId() != null ? student.getSectionId() : null);
//    		vo.setBranchId(student.getBranchId() != null ? student.getBranchId() : null);
    		ls.add(vo);
    	}
    	logger.debug("Total students retrieved. "+list.size());
    	return ls;
    }
    public List<CmsEmployeeVo> getEmployeeList(EmployeeListFilterInput filter) throws Exception {
        return Lists.newArrayList(employeeFilterProcessor.searchEmployee(filter));
    }

    public Long getTotalReceived( long academicyearId) {
        return admissionApplicationProcessor.getTotalReceived( academicyearId);
    }

    public Long getTotalInprocess( long academicyearId) {
        return admissionEnquiryProcessor.getTotalFollowup( academicyearId);
    }
    public Long getTotalDecline(long academicyearId) {
        return admissionApplicationProcessor.getTotalDeclined( academicyearId);
    }

    public Long getTotalAccepted( long academicyearId) {
        return admissionApplicationProcessor.getTotalAccepted(academicyearId);
    }


    public List<CmsAdmissionApplicationVo> searchAdmissionApplicationOnType(String admissionApplicationType,Long academicyearId) throws Exception{
        return Lists.newArrayList(admissionApplicationProcessor.searchAdmissionApplicationOnType(admissionApplicationType, academicyearId));
    }




    /**
	 * getStudentAttendanceDataForAdmin(StudentAttendanceFilterInput filter)
	 * Retrieves student attendance data for admin role end user
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<DailyAttendanceVo> getStudentAttendanceDataForAdmin(StudentAttendanceFilterInput filter) throws Exception {
        return Lists.newArrayList(studentAttendanceFilterImpl.getStudenceAttendanceDataForAdmin(filter));
    }
    public List<DailyExamVo> getExamDataForAdmin(ExamListFilterInput filter) throws Exception {
        return Lists.newArrayList(academicExamSettingFilterImpl.getExamDataForAdmin(filter));
    }

    public List<AcademicExamSetting> getSubjectList(ExamListFilterInput filter) throws Exception {
        List<AcademicExamSetting> list = this.examFilterProcessor.searchSubject(filter);
        logger.debug("Total Subjects retrieved. "+list.size());
        return list;
    }

    public List<StudentExamReport> getSubjectandStudents(ExamReportFilterInput filter) throws Exception {
        List<StudentExamReport> list = this.examFilterProcessor.searchSubjectandStudents(filter);
        logger.debug("Total Subjects retrieved. "+list.size());
        return list;
    }

	@Transactional(propagation=Propagation.REQUIRED)
	public QueryResult saveDueDatePaymentRemLateFee(UpdateDueDateInput udd, UpdatePaymentRemainderInput upr, UpdateLateFeeInput ulf) {
		QueryResult qr = new QueryResult();
		qr.setStatusCode(0);
		qr.setStatusDesc("DueDateId: #ddid#, PaymentRemainderId: #prid#, LateFeeId: #lfid#");
		AbstractDueDatePayload ddpl = null;
		AbstractPaymentRemainderPayload prpl = null;
		AbstractLateFeePayload lfpl = null;
		try {
			if(udd.getId() == -1) {
				logger.debug("Its a new record for due date. Adding due date.");
				AddDueDateInput add = CommonUtil.createCopyProperties(udd, AddDueDateInput.class);
				add.setId(null);
				ddpl = addDueDate(add);
			}else {
				logger.debug("Its an existing record for due date. Updating due date.");
				ddpl = updateDueDate(udd);
			}

			if(upr.getId() == -1) {
				logger.debug("Its a new record for payment remainder. Adding payment remainder.");
				AddPaymentRemainderInput apr = CommonUtil.createCopyProperties(upr, AddPaymentRemainderInput.class);
				apr.setId(null);
				prpl = addPaymentRemainder(apr);
			}else {
				logger.debug("Its an existing record for payment remainder. Updating payment remainder.");
				prpl = updatePaymentRemainder(upr);
			}

			if(ulf.getId() == -1) {
				logger.debug("Its a new record for late fee. Adding late fee.");
				AddLateFeeInput alf = CommonUtil.createCopyProperties(ulf, AddLateFeeInput.class);
				alf.setId(null);
				lfpl = addLateFee(alf);
			}else {
				logger.debug("Its an existing record for late fee. Updating late fee.");
				lfpl = updateLateFee(ulf);
			}

			qr.setStatusDesc(qr.getStatusDesc().replaceAll("#ddid#", String.valueOf(ddpl.getDueDate().getId())));
			qr.setStatusDesc(qr.getStatusDesc().replaceAll("#prid#", String.valueOf(prpl.getPaymentRemainder().getId())));
			qr.setStatusDesc(qr.getStatusDesc().replaceAll("#lfid#", String.valueOf(lfpl.getLateFee().getId())));
		}catch(Exception e) {
			qr.setStatusCode(1);
			qr.setStatusDesc("Due to some error due date, payment remainder and late fee could not be saved");
		}
		logger.debug("Success message : "+qr.getStatusDesc());
		return qr;
	}


	public CmsFeeSettingsVo getFeeSettingData(Long branchId) {
		LateFee lf = new LateFee();
		PaymentRemainder pr = new PaymentRemainder();
//		Branch branch = new Branch();
//		branch.setId(branchId);

		lf.setBranchId(branchId);
		Example<LateFee> example = Example.of(lf);
		Optional<LateFee> olf = this.lateFeeRepository.findOne(example);
		CmsFeeSettingsVo vo = new CmsFeeSettingsVo();
		if(olf.isPresent()) {
			logger.debug("Getting data for late fee");
			vo = CommonUtil.createCopyProperties(olf.get(), CmsFeeSettingsVo.class);
			vo.setLateFeeId(olf.get().getId());
		}

		pr.setBranchId(branchId);
		Example<PaymentRemainder> exPr = Example.of(pr);
		Optional<PaymentRemainder> opr = this.paymentRemainderRepository.findOne(exPr);
		if(opr.isPresent()) {
			logger.debug("Getting data for payment remainder");
			vo.setPrId(opr.get().getId());
			vo.setIsAutoRemainder(opr.get().getIsAutoRemainder());
			vo.setIsFirstPaymentRemainder(opr.get().getIsFirstPaymentRemainder());
			vo.setFirstPaymentRemainderDays(opr.get().getFirstPaymentRemainderDays());
			vo.setIsSecondPaymentRemainder(opr.get().getIsSecondPaymentRemainder());
			vo.setSecondPaymentRemainderDays(opr.get().getSecondPaymentRemainderDays());
			vo.setIsOverDuePaymentRemainder(opr.get().getIsOverDuePaymentRemainder());
			vo.setOverDuePaymentRemainderAfterDueDateOrUntilPaid(opr.get().getOverDuePaymentRemainderAfterDueDateOrUntilPaid());
			vo.setOverDuePaymentRemainderDays(opr.get().getOverDuePaymentRemainderDays());
			vo.setIsRemainderRecipients(opr.get().getIsRemainderRecipients());
			vo.setRemainderRecipients(opr.get().getRemainderRecipients());
		}
		return vo;
	}

	public CmsFeeSettingsVo getFeeSettingDueDateData(Long branchId, String paymentType) {
//		Branch branch = new Branch();
//		branch.setId(branchId);
		DueDate dueDate = new DueDate();
		dueDate.setBranchId(branchId);
		dueDate.setPaymentMethod(paymentType);

		CmsFeeSettingsVo vo = new CmsFeeSettingsVo();
		Example<DueDate> example = Example.of(dueDate);
		Optional<DueDate> odd = this.dueDateRepository.findOne(example);
		if(odd.isPresent()) {
			logger.debug("Getting data for due date");
			vo = CommonUtil.createCopyProperties(odd.get(), CmsFeeSettingsVo.class);
			vo.setDueDateId(odd.get().getId());
		}
		return vo;
	}
    public List<CmsLibraryVo> getBookList(LibraryFilterInput filter) throws Exception {
        List <Library> list = this.libraryFilterProcessor.searchBook(filter);
        List <CmsLibraryVo> ls = new ArrayList<>();
        for(Library library: list){
            CmsLibraryVo vo = CommonUtil.createCopyProperties(library, CmsLibraryVo.class);
            vo.setBatchId(library.getBatch() != null ? library.getBatch().getId() : null);
            vo.setSubjectId(library.getSubject() != null ? library.getSubject().getId() : null);
            ls.add(vo);
        }
        logger.debug("Total books retrieved. "+list.size());
        return ls;
    }
}
