import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './payment-remainder.reducer';
import { IPaymentRemainder } from 'app/shared/model/payment-remainder.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaymentRemainderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PaymentRemainderDetail extends React.Component<IPaymentRemainderDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { paymentRemainderEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PaymentRemainder [<b>{paymentRemainderEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="isAutoRemainder">Is Auto Remainder</span>
            </dt>
            <dd>{paymentRemainderEntity.isAutoRemainder}</dd>
            <dt>
              <span id="isFirstPaymentRemainder">Is First Payment Remainder</span>
            </dt>
            <dd>{paymentRemainderEntity.isFirstPaymentRemainder}</dd>
            <dt>
              <span id="firstPaymentRemainderDays">First Payment Remainder Days</span>
            </dt>
            <dd>{paymentRemainderEntity.firstPaymentRemainderDays}</dd>
            <dt>
              <span id="isSecondPaymentRemainder">Is Second Payment Remainder</span>
            </dt>
            <dd>{paymentRemainderEntity.isSecondPaymentRemainder}</dd>
            <dt>
              <span id="secondPaymentRemainderDays">Second Payment Remainder Days</span>
            </dt>
            <dd>{paymentRemainderEntity.secondPaymentRemainderDays}</dd>
            <dt>
              <span id="isOverDuePaymentRemainder">Is Over Due Payment Remainder</span>
            </dt>
            <dd>{paymentRemainderEntity.isOverDuePaymentRemainder}</dd>
            <dt>
              <span id="overDuePaymentRemainderAfterDueDateOrUntilPaid">Over Due Payment Remainder After Due Date Or Until Paid</span>
            </dt>
            <dd>{paymentRemainderEntity.overDuePaymentRemainderAfterDueDateOrUntilPaid}</dd>
            <dt>
              <span id="overDuePaymentRemainderDays">Over Due Payment Remainder Days</span>
            </dt>
            <dd>{paymentRemainderEntity.overDuePaymentRemainderDays}</dd>
            <dt>
              <span id="isRemainderRecipients">Is Remainder Recipients</span>
            </dt>
            <dd>{paymentRemainderEntity.isRemainderRecipients}</dd>
            <dt>
              <span id="remainderRecipients">Remainder Recipients</span>
            </dt>
            <dd>{paymentRemainderEntity.remainderRecipients}</dd>
            <dt>College</dt>
            <dd>{paymentRemainderEntity.collegeId ? paymentRemainderEntity.collegeId : ''}</dd>
            <dt>Branch</dt>
            <dd>{paymentRemainderEntity.branchId ? paymentRemainderEntity.branchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/payment-remainder" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/payment-remainder/${paymentRemainderEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ paymentRemainder }: IRootState) => ({
  paymentRemainderEntity: paymentRemainder.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PaymentRemainderDetail);
