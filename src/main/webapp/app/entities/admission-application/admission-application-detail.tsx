import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admission-application.reducer';
import { IAdmissionApplication } from 'app/shared/model/admission-application.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdmissionApplicationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdmissionApplicationDetail extends React.Component<IAdmissionApplicationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { admissionApplicationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AdmissionApplication [<b>{admissionApplicationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="admissionStatus">Admission Status</span>
            </dt>
            <dd>{admissionApplicationEntity.admissionStatus}</dd>
            <dt>
              <span id="course">Course</span>
            </dt>
            <dd>{admissionApplicationEntity.course}</dd>
            <dt>
              <span id="date">Date</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{admissionApplicationEntity.comments}</dd>
            <dt>Student</dt>
            <dd>{admissionApplicationEntity.studentId ? admissionApplicationEntity.studentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/admission-application" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/admission-application/${admissionApplicationEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ admissionApplication }: IRootState) => ({
  admissionApplicationEntity: admissionApplication.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdmissionApplicationDetail);
