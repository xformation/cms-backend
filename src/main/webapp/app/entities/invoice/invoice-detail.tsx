import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './invoice.reducer';
import { IInvoice } from 'app/shared/model/invoice.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvoiceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class InvoiceDetail extends React.Component<IInvoiceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { invoiceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Invoice [<b>{invoiceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="invoiceNumber">Invoice Number</span>
            </dt>
            <dd>{invoiceEntity.invoiceNumber}</dd>
            <dt>
              <span id="amountPaid">Amount Paid</span>
            </dt>
            <dd>{invoiceEntity.amountPaid}</dd>
            <dt>
              <span id="paymentDate">Payment Date</span>
            </dt>
            <dd>
              <TextFormat value={invoiceEntity.paymentDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="nextPaymentDate">Next Payment Date</span>
            </dt>
            <dd>
              <TextFormat value={invoiceEntity.nextPaymentDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="outStandingAmount">Out Standing Amount</span>
            </dt>
            <dd>{invoiceEntity.outStandingAmount}</dd>
            <dt>
              <span id="modeOfPayment">Mode Of Payment</span>
            </dt>
            <dd>{invoiceEntity.modeOfPayment}</dd>
            <dt>
              <span id="chequeNumber">Cheque Number</span>
            </dt>
            <dd>{invoiceEntity.chequeNumber}</dd>
            <dt>
              <span id="demandDraftNumber">Demand Draft Number</span>
            </dt>
            <dd>{invoiceEntity.demandDraftNumber}</dd>
            <dt>
              <span id="onlineTxnRefNumber">Online Txn Ref Number</span>
            </dt>
            <dd>{invoiceEntity.onlineTxnRefNumber}</dd>
            <dt>
              <span id="paymentStatus">Payment Status</span>
            </dt>
            <dd>{invoiceEntity.paymentStatus}</dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{invoiceEntity.comments}</dd>
            <dt>
              <span id="updatedBy">Updated By</span>
            </dt>
            <dd>{invoiceEntity.updatedBy}</dd>
            <dt>
              <span id="updatedOn">Updated On</span>
            </dt>
            <dd>
              <TextFormat value={invoiceEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Fee Category</dt>
            <dd>{invoiceEntity.feeCategoryId ? invoiceEntity.feeCategoryId : ''}</dd>
            <dt>Fee Details</dt>
            <dd>{invoiceEntity.feeDetailsId ? invoiceEntity.feeDetailsId : ''}</dd>
            <dt>Due Date</dt>
            <dd>{invoiceEntity.dueDateId ? invoiceEntity.dueDateId : ''}</dd>
            <dt>Payment Remainder</dt>
            <dd>{invoiceEntity.paymentRemainderId ? invoiceEntity.paymentRemainderId : ''}</dd>
            <dt>College</dt>
            <dd>{invoiceEntity.collegeId ? invoiceEntity.collegeId : ''}</dd>
            <dt>Branch</dt>
            <dd>{invoiceEntity.branchId ? invoiceEntity.branchId : ''}</dd>
            <dt>Student</dt>
            <dd>{invoiceEntity.studentId ? invoiceEntity.studentId : ''}</dd>
            <dt>Academic Year</dt>
            <dd>{invoiceEntity.academicYearId ? invoiceEntity.academicYearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/invoice" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/invoice/${invoiceEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ invoice }: IRootState) => ({
  invoiceEntity: invoice.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvoiceDetail);
