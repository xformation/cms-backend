import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
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
            Term [<b>{termEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="termsDesc">Terms Desc</span>
            </dt>
            <dd>{termEntity.termsDesc}</dd>
            <dt>
              <span id="startDate">Start Date</span>
            </dt>
            <dd>
              <TextFormat value={termEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endDate">End Date</span>
            </dt>
            <dd>
              <TextFormat value={termEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="termStatus">Term Status</span>
            </dt>
            <dd>{termEntity.termStatus}</dd>
            <dt>Academicyear</dt>
            <dd>{termEntity.academicyearId ? termEntity.academicyearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/term" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/term/${termEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
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
