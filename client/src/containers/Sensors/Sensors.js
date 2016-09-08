import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';

class Sensors extends Component {

  componentWillMount() {
    this.props.actions.loadSensors();
  }

  render() {
    // this.props.piguard.sensors.forEach(sensor => {
    //   const why = this.props.piguard.slots.find(slot => slot.id === sensor.slotId);
    //   if (why === undefined) {
    //     console.log('Id: ' + sensor.slotId);
    //   } else {
    //     console.log(why);
    //   }
    //
    //   console.log('----------------')
    // })


    return (
      <div>
        {this.props.piguard.sensors.map(sensor => (
          <Card>
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

            </CardText>
          </Card>
        ))}
      </div>
    );
  }
}

export default Sensors;
