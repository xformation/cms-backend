import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student-year.reducer';
import { IStudentYear } from 'app/shared/model/student-year.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentYearDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export class StudentYearDetail extends React.Component<IStudentYearDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentYearEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.studentYear.detail.title">StudentYear</Translate> [<b>{studentYearEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sYear">
                <Translate contentKey="cmsApp.studentYear.sYear">S Year</Translate>
              </span>
            </dt>
            <dd>{studentYearEntity.sYear}</dd>
          </dl>
          <Button tag={Link} to="/entity/student-year" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student-year/${studentYearEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ studentYear }: IRootState) => ({
  studentYearEntity: studentYear.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentYearDetail);
