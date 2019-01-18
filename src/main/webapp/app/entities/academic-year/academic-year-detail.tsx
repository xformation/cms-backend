import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './academic-year.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicYearDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class AcademicYearDetail extends React.Component<IAcademicYearDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { academicYearEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AcademicYear [<b>{academicYearEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="year">Year</span>
            </dt>
            <dd>{academicYearEntity.year}</dd>
            <dt>
              <span id="startDate">Start Date</span>
            </dt>
            <dd>
              <TextFormat value={academicYearEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endDate">End Date</span>
            </dt>
            <dd>
              <TextFormat value={academicYearEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="desc">Desc</span>
            </dt>
            <dd>{academicYearEntity.desc}</dd>
          </dl>
          <Button tag={Link} to="/entity/academic-year" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/academic-year/${academicYearEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ academicYear }: IRootState) => ({
  academicYearEntity: academicYear.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AcademicYearDetail);
