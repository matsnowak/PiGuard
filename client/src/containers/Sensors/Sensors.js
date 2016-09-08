import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';

class Sensors extends Component {

  componentWillMount() {
    this.props.actions.loadSensors();
  }

  render() {
    return (
      <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'space-around' }}>
        {this.props.piguard.sensors.map(sensor => (
          <Card style={{ display: 'flex'}}>
            <CardHeader
              title={sensor.name}
            />
            <CardText>
              {sensor.triggeredOn}
            </CardText>
            <CardText>
              {sensor.pullResistance}
            </CardText>
            <CardText>
              {this.props.piguard.slots.find(slot => slot.id === sensor.slotId).description}
            </CardText>
          </Card>
        ))}
      </div>
    );
  }
}

export default Sensors;
