import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import Chip from 'material-ui/Chip';

class Zones extends Component {

  getZone(id) {
    return this.props.piguard.zones.find(zone => zone.id === id);
  }

  checkElement(element) {
    if (element < 10) {
      return `0${element}`;
    }
    return element;
  }

  contructStringDateFromCustomObject(customObject) {
    return `${customObject.year}-${this.checkElement(customObject.monthValue)}-${this.checkElement(customObject.dayOfMonth)} ${this.checkElement(customObject.hour)}:${this.checkElement(customObject.minute)}:${this.checkElement(customObject.second)}`;
  }

  render() {
    return (
      <div>

        <h3>Armed Zones </h3>
        <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
          {this.props.piguard.armedZones.map(armedZone => {

            const zone = this.getZone(armedZone.zone.id);
            return (
              <Card key={armedZone.id} style={{ display: 'flex', marginRight: '20px' }}>
                <CardHeader
                  title={`Name: ${zone.name}`}
                  subtitle={`Start guard from: ${this.contructStringDateFromCustomObject(armedZone.startGuardFrom)}`}
                />
                <CardText style={{ textAlign: 'center' }}>
                  Sensors:
                  <div style={{
                    display: 'flex',
                    flexWrap: 'wrap',
                  }}>
                    {zone.sensors.map(sensor => <Chip>{sensor}</Chip>)}
                  </div>
                </CardText>
                <CardText style={{ textAlign: 'center' }}>
                  Signallers:
                  <div style={{
                    display: 'flex',
                    flexWrap: 'wrap',
                  }}>
                    {zone.signallers.map(signaller => <Chip>{signaller}</Chip>)}
                  </div>
                </CardText>
                <CardActions>
                  <FlatButton secondary label="Disarm zone" onClick={() => this.props.actions.removeArmedZone(armedZone)}/>
                </CardActions>
              </Card>
            )
          })}
        </div>

        <h3>Zones </h3>
        <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
          {this.props.piguard.zones.map(zone => (
            <Card key={zone.id} style={{ display: 'flex', marginRight: '20px' }}>
              <CardHeader
                style={{ textAlign: 'center' }}
                textStyle={{ paddingRight: '0px' }}
                title={`Name: ${zone.name}`}
              />
              <CardText style={{ textAlign: 'center' }}>
                Sensors:
                <div style={{
                  display: 'flex',
                  flexWrap: 'wrap',
                }}>
                  {zone.sensors.map(sensor => <Chip>{sensor}</Chip>)}
                </div>
              </CardText>
              <CardText style={{ textAlign: 'center' }}>
                Signallers:
                <div style={{
                  display: 'flex',
                  flexWrap: 'wrap',
                }}>
                  {zone.signallers.map(signaller => <Chip>{signaller}</Chip>)}
                </div>
              </CardText>
              <CardActions>
                <FlatButton label="Arm zone" onClick={() => this.props.actions.startArming(zone)}/>
                <FlatButton secondary label="Remove zone" onClick={() => this.props.actions.removeZone(zone)}/>
              </CardActions>
            </Card>
          ))}
        </div>

      </div>

    );
  }
}

export default Zones;
