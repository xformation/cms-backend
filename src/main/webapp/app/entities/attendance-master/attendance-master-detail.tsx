import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './attendance-master.reducer';
import { IAttendanceMaster } from 'app/shared/model/attendance-master.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAttendanceMasterDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class AttendanceMasterDetail extends React.Component<IAttendanceMasterDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { attendanceMasterEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AttendanceMaster [<b>{attendanceMasterEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="desc">Desc</span>
            </dt>
            <dd>{attendanceMasterEntity.desc}</dd>
            <dt>Teach</dt>
            <dd>{attendanceMasterEntity.teachId ? attendanceMasterEntity.teachId : ''}</dd>
            <dt>Section</dt>
            <dd>{attendanceMasterEntity.sectionId ? attendanceMasterEntity.sectionId : ''}</dd>
            <dt>Academicyear</dt>
            <dd>{attendanceMasterEntity.academicyearId ? attendanceMasterEntity.academicyearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/attendance-master" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/attendance-master/${attendanceMasterEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ attendanceMaster }: IRootState) => ({
  attendanceMasterEntity: attendanceMaster.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AttendanceMasterDetail);
