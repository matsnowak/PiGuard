import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';

class Sensors extends Component {

  componentWillMount() {
    this.props.actions.loadSensors();
  }

  render() {
    return (
      <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
        {this.props.piguard.sensors.map(sensor => (
          <Card key={sensor.id} style={{ display: 'flex', marginRight: '20px' }}>
            <CardHeader
              title={`Name: ${sensor.name}`}
            />
            <CardText style={{ textAlign: 'left' }}>
              Triggered on: {sensor.triggeredOn}
            </CardText>
            <CardText style={{ textAlign: 'left' }}>
              Pull resistance: {sensor.pullResistance}
            </CardText>
            <CardText style={{ textAlign: 'left' }}>
              Slot: {this.props.piguard.slots.find(slot => slot.id === sensor.slotId).description}
            </CardText>
            <CardActions>
              <FlatButton secondary label="Remove sensor" onClick={() => this.props.actions.removeSensor(sensor)}/>
            </CardActions>
          </Card>
        ))}
      </div>
    );
  }
}

export default Sensors;
