import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './fee-details.reducer';
import { IFeeDetails } from 'app/shared/model/fee-details.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFeeDetailsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FeeDetailsDetail extends React.Component<IFeeDetailsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { feeDetailsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FeeDetails [<b>{feeDetailsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="feeParticularsName">Fee Particulars Name</span>
            </dt>
            <dd>{feeDetailsEntity.feeParticularsName}</dd>
            <dt>
              <span id="feeParticularDesc">Fee Particular Desc</span>
            </dt>
            <dd>{feeDetailsEntity.feeParticularDesc}</dd>
            <dt>
              <span id="studentType">Student Type</span>
            </dt>
            <dd>{feeDetailsEntity.studentType}</dd>
            <dt>
              <span id="gender">Gender</span>
            </dt>
            <dd>{feeDetailsEntity.gender}</dd>
            <dt>
              <span id="amount">Amount</span>
            </dt>
            <dd>{feeDetailsEntity.amount}</dd>
            <dt>Fee Category</dt>
            <dd>{feeDetailsEntity.feeCategoryId ? feeDetailsEntity.feeCategoryId : ''}</dd>
            <dt>Batch</dt>
            <dd>{feeDetailsEntity.batchId ? feeDetailsEntity.batchId : ''}</dd>
            <dt>Facility</dt>
            <dd>{feeDetailsEntity.facilityId ? feeDetailsEntity.facilityId : ''}</dd>
            <dt>Transport Route</dt>
            <dd>{feeDetailsEntity.transportRouteId ? feeDetailsEntity.transportRouteId : ''}</dd>
            <dt>College</dt>
            <dd>{feeDetailsEntity.collegeId ? feeDetailsEntity.collegeId : ''}</dd>
            <dt>Department</dt>
            <dd>{feeDetailsEntity.departmentId ? feeDetailsEntity.departmentId : ''}</dd>
            <dt>Branch</dt>
            <dd>{feeDetailsEntity.branchId ? feeDetailsEntity.branchId : ''}</dd>
            <dt>Academic Year</dt>
            <dd>{feeDetailsEntity.academicYearId ? feeDetailsEntity.academicYearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/fee-details" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/fee-details/${feeDetailsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ feeDetails }: IRootState) => ({
  feeDetailsEntity: feeDetails.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FeeDetailsDetail);
