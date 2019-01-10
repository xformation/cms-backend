import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './teach.reducer';
import { ITeach } from 'app/shared/model/teach.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITeachDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class TeachDetail extends React.Component<ITeachDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { teachEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.teach.detail.title">Teach</Translate> [<b>{teachEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="desc">
                <Translate contentKey="cmsApp.teach.desc">Desc</Translate>
              </span>
            </dt>
            <dd>{teachEntity.desc}</dd>
            <dt>
              <Translate contentKey="cmsApp.teach.teacher">Teacher</Translate>
            </dt>
            <dd>{teachEntity.teacherId ? teachEntity.teacherId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.teach.subject">Subject</Translate>
            </dt>
            <dd>{teachEntity.subjectId ? teachEntity.subjectId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/teach" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/teach/${teachEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ teach }: IRootState) => ({
  teachEntity: teach.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeachDetail);
