import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './subject.reducer';
import { ISubject } from 'app/shared/model/subject.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISubjectDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SubjectDetail extends React.Component<ISubjectDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { subjectEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Subject [<b>{subjectEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="subjectCode">Subject Code</span>
            </dt>
            <dd>{subjectEntity.subjectCode}</dd>
            <dt>
              <span id="subjectType">Subject Type</span>
            </dt>
            <dd>{subjectEntity.subjectType}</dd>
            <dt>
              <span id="subjectDesc">Subject Desc</span>
            </dt>
            <dd>{subjectEntity.subjectDesc}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{subjectEntity.status}</dd>
            <dt>Department</dt>
            <dd>{subjectEntity.departmentId ? subjectEntity.departmentId : ''}</dd>
            <dt>Batch</dt>
            <dd>{subjectEntity.batchId ? subjectEntity.batchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/subject" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/subject/${subjectEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ subject }: IRootState) => ({
  subjectEntity: subject.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubjectDetail);
