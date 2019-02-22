import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './late-fee.reducer';
import { ILateFee } from 'app/shared/model/late-fee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILateFeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LateFeeDetail extends React.Component<ILateFeeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { lateFeeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            LateFee [<b>{lateFeeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="assignLateFee">Assign Late Fee</span>
            </dt>
            <dd>{lateFeeEntity.assignLateFee}</dd>
            <dt>
              <span id="lateFeeDays">Late Fee Days</span>
            </dt>
            <dd>{lateFeeEntity.lateFeeDays}</dd>
            <dt>
              <span id="fixed">Fixed</span>
            </dt>
            <dd>{lateFeeEntity.fixed}</dd>
            <dt>
              <span id="percentage">Percentage</span>
            </dt>
            <dd>{lateFeeEntity.percentage}</dd>
            <dt>
              <span id="fixedCharges">Fixed Charges</span>
            </dt>
            <dd>{lateFeeEntity.fixedCharges}</dd>
            <dt>
              <span id="percentCharges">Percent Charges</span>
            </dt>
            <dd>{lateFeeEntity.percentCharges}</dd>
            <dt>
              <span id="lateFeeAssignmentFrequency">Late Fee Assignment Frequency</span>
            </dt>
            <dd>{lateFeeEntity.lateFeeAssignmentFrequency}</dd>
            <dt>College</dt>
            <dd>{lateFeeEntity.collegeId ? lateFeeEntity.collegeId : ''}</dd>
            <dt>Branch</dt>
            <dd>{lateFeeEntity.branchId ? lateFeeEntity.branchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/late-fee" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/late-fee/${lateFeeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ lateFee }: IRootState) => ({
  lateFeeEntity: lateFee.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LateFeeDetail);
