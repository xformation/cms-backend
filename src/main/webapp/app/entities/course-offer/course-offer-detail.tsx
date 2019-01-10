import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './course-offer.reducer';
import { ICourseOffer } from 'app/shared/model/course-offer.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICourseOfferDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class CourseOfferDetail extends React.Component<ICourseOfferDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { courseOfferEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.courseOffer.detail.title">CourseOffer</Translate> [<b>{courseOfferEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="desc">
                <Translate contentKey="cmsApp.courseOffer.desc">Desc</Translate>
              </span>
            </dt>
            <dd>{courseOfferEntity.desc}</dd>
            <dt>
              <Translate contentKey="cmsApp.courseOffer.college">College</Translate>
            </dt>
            <dd>{courseOfferEntity.collegeId ? courseOfferEntity.collegeId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.courseOffer.department">Department</Translate>
            </dt>
            <dd>{courseOfferEntity.departmentId ? courseOfferEntity.departmentId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.courseOffer.subject">Subject</Translate>
            </dt>
            <dd>{courseOfferEntity.subjectId ? courseOfferEntity.subjectId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/course-offer" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/course-offer/${courseOfferEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ courseOffer }: IRootState) => ({
  courseOfferEntity: courseOffer.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CourseOfferDetail);
