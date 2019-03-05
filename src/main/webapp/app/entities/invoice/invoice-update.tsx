import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFeeCategory } from 'app/shared/model/fee-category.model';
import { getEntities as getFeeCategories } from 'app/entities/fee-category/fee-category.reducer';
import { IFeeDetails } from 'app/shared/model/fee-details.model';
import { getEntities as getFeeDetails } from 'app/entities/fee-details/fee-details.reducer';
import { IDueDate } from 'app/shared/model/due-date.model';
import { getEntities as getDueDates } from 'app/entities/due-date/due-date.reducer';
import { IPaymentRemainder } from 'app/shared/model/payment-remainder.model';
import { getEntities as getPaymentRemainders } from 'app/entities/payment-remainder/payment-remainder.reducer';
import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './invoice.reducer';
import { IInvoice } from 'app/shared/model/invoice.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IInvoiceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IInvoiceUpdateState {
  isNew: boolean;
  feeCategoryId: number;
  feeDetailsId: number;
  dueDateId: number;
  paymentRemainderId: number;
  collegeId: number;
  branchId: number;
  studentId: number;
  academicYearId: number;
}

export class InvoiceUpdate extends React.Component<IInvoiceUpdateProps, IInvoiceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      feeCategoryId: 0,
      feeDetailsId: 0,
      dueDateId: 0,
      paymentRemainderId: 0,
      collegeId: 0,
      branchId: 0,
      studentId: 0,
      academicYearId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getFeeCategories();
    this.props.getFeeDetails();
    this.props.getDueDates();
    this.props.getPaymentRemainders();
    this.props.getColleges();
    this.props.getBranches();
    this.props.getStudents();
    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { invoiceEntity } = this.props;
      const entity = {
        ...invoiceEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/invoice');
  };

  feeCategoryUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        feeCategoryId: -1
      });
    } else {
      for (const i in this.props.feeCategories) {
        if (id === this.props.feeCategories[i].id.toString()) {
          this.setState({
            feeCategoryId: this.props.feeCategories[i].id
          });
        }
      }
    }
  };

  feeDetailsUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        feeDetailsId: -1
      });
    } else {
      for (const i in this.props.feeDetails) {
        if (id === this.props.feeDetails[i].id.toString()) {
          this.setState({
            feeDetailsId: this.props.feeDetails[i].id
          });
        }
      }
    }
  };

  dueDateUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        dueDateId: -1
      });
    } else {
      for (const i in this.props.dueDates) {
        if (id === this.props.dueDates[i].id.toString()) {
          this.setState({
            dueDateId: this.props.dueDates[i].id
          });
        }
      }
    }
  };

  paymentRemainderUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        paymentRemainderId: -1
      });
    } else {
      for (const i in this.props.paymentRemainders) {
        if (id === this.props.paymentRemainders[i].id.toString()) {
          this.setState({
            paymentRemainderId: this.props.paymentRemainders[i].id
          });
        }
      }
    }
  };

  collegeUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        collegeId: -1
      });
    } else {
      for (const i in this.props.colleges) {
        if (id === this.props.colleges[i].id.toString()) {
          this.setState({
            collegeId: this.props.colleges[i].id
          });
        }
      }
    }
  };

  branchUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        branchId: -1
      });
    } else {
      for (const i in this.props.branches) {
        if (id === this.props.branches[i].id.toString()) {
          this.setState({
            branchId: this.props.branches[i].id
          });
        }
      }
    }
  };

  studentUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        studentId: -1
      });
    } else {
      for (const i in this.props.students) {
        if (id === this.props.students[i].id.toString()) {
          this.setState({
            studentId: this.props.students[i].id
          });
        }
      }
    }
  };

  academicYearUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        academicYearId: -1
      });
    } else {
      for (const i in this.props.academicYears) {
        if (id === this.props.academicYears[i].id.toString()) {
          this.setState({
            academicYearId: this.props.academicYears[i].id
          });
        }
      }
    }
  };

  render() {
    const {
      invoiceEntity,
      feeCategories,
      feeDetails,
      dueDates,
      paymentRemainders,
      colleges,
      branches,
      students,
      academicYears,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.invoice.home.createOrEditLabel">Create or edit a Invoice</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : invoiceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="invoice-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="invoiceNumberLabel" for="invoiceNumber">
                    Invoice Number
                  </Label>
                  <AvField
                    id="invoice-invoiceNumber"
                    type="text"
                    name="invoiceNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="amountPaidLabel" for="amountPaid">
                    Amount Paid
                  </Label>
                  <AvField
                    id="invoice-amountPaid"
                    type="number"
                    className="form-control"
                    name="amountPaid"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="paymentDateLabel" for="paymentDate">
                    Payment Date
                  </Label>
                  <AvField
                    id="invoice-paymentDate"
                    type="date"
                    className="form-control"
                    name="paymentDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nextPaymentDateLabel" for="nextPaymentDate">
                    Next Payment Date
                  </Label>
                  <AvField
                    id="invoice-nextPaymentDate"
                    type="date"
                    className="form-control"
                    name="nextPaymentDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="outStandingAmountLabel" for="outStandingAmount">
                    Out Standing Amount
                  </Label>
                  <AvField
                    id="invoice-outStandingAmount"
                    type="number"
                    className="form-control"
                    name="outStandingAmount"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modeOfPaymentLabel">Mode Of Payment</Label>
                  <AvInput
                    id="invoice-modeOfPayment"
                    type="select"
                    className="form-control"
                    name="modeOfPayment"
                    value={(!isNew && invoiceEntity.modeOfPayment) || 'CARD'}
                  >
                    <option value="CARD">CARD</option>
                    <option value="CASH">CASH</option>
                    <option value="NETBANKING">NETBANKING</option>
                    <option value="CHEQUE">CHEQUE</option>
                    <option value="DEMANDDRAFT">DEMANDDRAFT</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="chequeNumberLabel" for="chequeNumber">
                    Cheque Number
                  </Label>
                  <AvField id="invoice-chequeNumber" type="number" className="form-control" name="chequeNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="demandDraftNumberLabel" for="demandDraftNumber">
                    Demand Draft Number
                  </Label>
                  <AvField id="invoice-demandDraftNumber" type="number" className="form-control" name="demandDraftNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="onlineTxnRefNumberLabel" for="onlineTxnRefNumber">
                    Online Txn Ref Number
                  </Label>
                  <AvField id="invoice-onlineTxnRefNumber" type="text" name="onlineTxnRefNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="paymentStatusLabel">Payment Status</Label>
                  <AvInput
                    id="invoice-paymentStatus"
                    type="select"
                    className="form-control"
                    name="paymentStatus"
                    value={(!isNew && invoiceEntity.paymentStatus) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField
                    id="invoice-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="updatedBy">
                    Updated By
                  </Label>
                  <AvField
                    id="invoice-updatedBy"
                    type="text"
                    name="updatedBy"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedOnLabel" for="updatedOn">
                    Updated On
                  </Label>
                  <AvField
                    id="invoice-updatedOn"
                    type="date"
                    className="form-control"
                    name="updatedOn"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="feeCategory.id">Fee Category</Label>
                  <AvInput
                    id="invoice-feeCategory"
                    type="select"
                    className="form-control"
                    name="feeCategoryId"
                    onChange={this.feeCategoryUpdate}
                  >
                    <option value="" key="0" />
                    {feeCategories
                      ? feeCategories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="feeDetails.id">Fee Details</Label>
                  <AvInput
                    id="invoice-feeDetails"
                    type="select"
                    className="form-control"
                    name="feeDetailsId"
                    onChange={this.feeDetailsUpdate}
                  >
                    <option value="" key="0" />
                    {feeDetails
                      ? feeDetails.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="dueDate.id">Due Date</Label>
                  <AvInput id="invoice-dueDate" type="select" className="form-control" name="dueDateId" onChange={this.dueDateUpdate}>
                    <option value="" key="0" />
                    {dueDates
                      ? dueDates.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="paymentRemainder.id">Payment Remainder</Label>
                  <AvInput
                    id="invoice-paymentRemainder"
                    type="select"
                    className="form-control"
                    name="paymentRemainderId"
                    onChange={this.paymentRemainderUpdate}
                  >
                    <option value="" key="0" />
                    {paymentRemainders
                      ? paymentRemainders.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="college.id">College</Label>
                  <AvInput id="invoice-college" type="select" className="form-control" name="collegeId" onChange={this.collegeUpdate}>
                    <option value="" key="0" />
                    {colleges
                      ? colleges.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="invoice-branch" type="select" className="form-control" name="branchId" onChange={this.branchUpdate}>
                    <option value="" key="0" />
                    {branches
                      ? branches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput id="invoice-student" type="select" className="form-control" name="studentId" onChange={this.studentUpdate}>
                    <option value="" key="0" />
                    {students
                      ? students.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicYear.id">Academic Year</Label>
                  <AvInput
                    id="invoice-academicYear"
                    type="select"
                    className="form-control"
                    name="academicYearId"
                    onChange={this.academicYearUpdate}
                  >
                    <option value="" key="0" />
                    {academicYears
                      ? academicYears.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/invoice" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  feeCategories: storeState.feeCategory.entities,
  feeDetails: storeState.feeDetails.entities,
  dueDates: storeState.dueDate.entities,
  paymentRemainders: storeState.paymentRemainder.entities,
  colleges: storeState.college.entities,
  branches: storeState.branch.entities,
  students: storeState.student.entities,
  academicYears: storeState.academicYear.entities,
  invoiceEntity: storeState.invoice.entity,
  loading: storeState.invoice.loading,
  updating: storeState.invoice.updating
});

const mapDispatchToProps = {
  getFeeCategories,
  getFeeDetails,
  getDueDates,
  getPaymentRemainders,
  getColleges,
  getBranches,
  getStudents,
  getAcademicYears,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvoiceUpdate);
