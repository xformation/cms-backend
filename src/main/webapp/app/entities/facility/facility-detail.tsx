import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './facility.reducer';
import { IFacility } from 'app/shared/model/facility.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFacilityDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FacilityDetail extends React.Component<IFacilityDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { facilityEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Facility [<b>{facilityEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{facilityEntity.name}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{facilityEntity.status}</dd>
            <dt>
              <span id="startDate">Start Date</span>
            </dt>
            <dd>
              <TextFormat value={facilityEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endDate">End Date</span>
            </dt>
            <dd>
              <TextFormat value={facilityEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="suspandStartDate">Suspand Start Date</span>
            </dt>
            <dd>
              <TextFormat value={facilityEntity.suspandStartDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="suspandEndDate">Suspand End Date</span>
            </dt>
            <dd>
              <TextFormat value={facilityEntity.suspandEndDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Academic Year</dt>
            <dd>{facilityEntity.academicYearId ? facilityEntity.academicYearId : ''}</dd>
            <dt>Branch</dt>
            <dd>{facilityEntity.branchId ? facilityEntity.branchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/facility" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/facility/${facilityEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ facility }: IRootState) => ({
  facilityEntity: facility.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FacilityDetail);
