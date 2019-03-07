import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './competitive-exam.reducer';
import { ICompetitiveExam } from 'app/shared/model/competitive-exam.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICompetitiveExamDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CompetitiveExamDetail extends React.Component<ICompetitiveExamDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { competitiveExamEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CompetitiveExam [<b>{competitiveExamEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="testName">Test Name</span>
            </dt>
            <dd>{competitiveExamEntity.testName}</dd>
            <dt>
              <span id="testScore">Test Score</span>
            </dt>
            <dd>{competitiveExamEntity.testScore}</dd>
            <dt>
              <span id="enrollmentNo">Enrollment No</span>
            </dt>
            <dd>{competitiveExamEntity.enrollmentNo}</dd>
            <dt>
              <span id="rank">Rank</span>
            </dt>
            <dd>{competitiveExamEntity.rank}</dd>
            <dt>Student</dt>
            <dd>{competitiveExamEntity.studentId ? competitiveExamEntity.studentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/competitive-exam" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/competitive-exam/${competitiveExamEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ competitiveExam }: IRootState) => ({
  competitiveExamEntity: competitiveExam.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CompetitiveExamDetail);
