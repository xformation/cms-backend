import * as React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student-facility-link.reducer';
import { IStudentFacilityLink } from 'app/shared/model/student-facility-link.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentFacilityLinkDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class StudentFacilityLinkDetail extends React.Component<IStudentFacilityLinkDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentFacilityLinkEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            StudentFacilityLink [<b>{studentFacilityLinkEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="linkDesc">Link Desc</span>
            </dt>
            <dd>{studentFacilityLinkEntity.linkDesc}</dd>
            <dt>Student</dt>
            <dd>{studentFacilityLinkEntity.studentId ? studentFacilityLinkEntity.studentId : ''}</dd>
            <dt>Facility</dt>
            <dd>{studentFacilityLinkEntity.facilityId ? studentFacilityLinkEntity.facilityId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/student-facility-link" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student-facility-link/${studentFacilityLinkEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ studentFacilityLink }: IRootState) => ({
  studentFacilityLinkEntity: studentFacilityLink.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StudentFacilityLinkDetail);
