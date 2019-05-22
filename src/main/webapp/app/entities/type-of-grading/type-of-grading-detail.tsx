import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './type-of-grading.reducer';
import { ITypeOfGrading } from 'app/shared/model/type-of-grading.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITypeOfGradingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TypeOfGradingDetail extends React.Component<ITypeOfGradingDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { typeOfGradingEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TypeOfGrading [<b>{typeOfGradingEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="minMarks">Min Marks</span>
            </dt>
            <dd>{typeOfGradingEntity.minMarks}</dd>
            <dt>
              <span id="maxMarks">Max Marks</span>
            </dt>
            <dd>{typeOfGradingEntity.maxMarks}</dd>
            <dt>
              <span id="grades">Grades</span>
            </dt>
            <dd>{typeOfGradingEntity.grades}</dd>
            <dt>Academic Exam Setting</dt>
            <dd>{typeOfGradingEntity.academicExamSettingId ? typeOfGradingEntity.academicExamSettingId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/type-of-grading" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/type-of-grading/${typeOfGradingEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ typeOfGrading }: IRootState) => ({
  typeOfGradingEntity: typeOfGrading.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TypeOfGradingDetail);
