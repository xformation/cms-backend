import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './academic-history.reducer';
import { IAcademicHistory } from 'app/shared/model/academic-history.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicHistoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AcademicHistoryDetail extends React.Component<IAcademicHistoryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { academicHistoryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AcademicHistory [<b>{academicHistoryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="qualification">Qualification</span>
            </dt>
            <dd>{academicHistoryEntity.qualification}</dd>
            <dt>
              <span id="yearOfPassing">Year Of Passing</span>
            </dt>
            <dd>{academicHistoryEntity.yearOfPassing}</dd>
            <dt>
              <span id="institution">Institution</span>
            </dt>
            <dd>{academicHistoryEntity.institution}</dd>
            <dt>
              <span id="university">University</span>
            </dt>
            <dd>{academicHistoryEntity.university}</dd>
            <dt>
              <span id="enrollmentNo">Enrollment No</span>
            </dt>
            <dd>{academicHistoryEntity.enrollmentNo}</dd>
            <dt>
              <span id="score">Score</span>
            </dt>
            <dd>{academicHistoryEntity.score}</dd>
            <dt>
              <span id="percentage">Percentage</span>
            </dt>
            <dd>{academicHistoryEntity.percentage}</dd>
            <dt>Student</dt>
            <dd>{academicHistoryEntity.studentId ? academicHistoryEntity.studentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/academic-history" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/academic-history/${academicHistoryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ academicHistory }: IRootState) => ({
  academicHistoryEntity: academicHistory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AcademicHistoryDetail);
