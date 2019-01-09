import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './term.reducer';
import { ITerm } from 'app/shared/model/term.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITermDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class TermDetail extends React.Component<ITermDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { termEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.term.detail.title">Term</Translate> [<b>{termEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="termsDesc">
                <Translate contentKey="cmsApp.term.termsDesc">Terms Desc</Translate>
              </span>
            </dt>
            <dd>{termEntity.termsDesc}</dd>
            <dt>
              <span id="startDate">
                <Translate contentKey="cmsApp.term.startDate">Start Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={termEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endDate">
                <Translate contentKey="cmsApp.term.endDate">End Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={termEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="status">
                <Translate contentKey="cmsApp.term.status">Status</Translate>
              </span>
            </dt>
            <dd>{termEntity.status}</dd>
            <dt>
              <Translate contentKey="cmsApp.term.academicYear">Academic Year</Translate>
            </dt>
            <dd>{termEntity.academicYearId ? termEntity.academicYearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/term" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/term/${termEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ term }: IRootState) => ({
  termEntity: term.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TermDetail);
