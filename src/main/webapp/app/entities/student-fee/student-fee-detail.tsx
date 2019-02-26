import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student-fee.reducer';
import { IStudentFee } from 'app/shared/model/student-fee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentFeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class StudentFeeDetail extends React.Component<IStudentFeeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentFeeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            StudentFee [<b>{studentFeeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="totalFee">Total Fee</span>
            </dt>
            <dd>{studentFeeEntity.totalFee}</dd>
            <dt>
              <span id="feesPaid">Fees Paid</span>
            </dt>
            <dd>{studentFeeEntity.feesPaid}</dd>
            <dt>
              <span id="feesDue">Fees Due</span>
            </dt>
            <dd>{studentFeeEntity.feesDue}</dd>
            <dt>
              <span id="dueDate">Due Date</span>
            </dt>
            <dd>
              <TextFormat value={studentFeeEntity.dueDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="totalRemaining">Total Remaining</span>
            </dt>
            <dd>{studentFeeEntity.totalRemaining}</dd>
          </dl>
          <Button tag={Link} to="/entity/student-fee" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student-fee/${studentFeeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ studentFee }: IRootState) => ({
  studentFeeEntity: studentFee.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentFeeDetail);
