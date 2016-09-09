import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import IconButton from 'material-ui/IconButton';
import ContentAdd from 'material-ui/svg-icons/content/add';
import Chip from 'material-ui/Chip';
import MenuItem from 'material-ui/MenuItem';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import differenceBy from 'lodash/differenceBy';

class ZoneDialog extends Component {

  state = {
    name: '',
    signallers: [],
    sensors: [],
    sensorIndex: null,
    signallerIndex: null,
  };

  handleChangeName = (event) => {
    this.setState({
      name: event.target.value,
    });
  };

  addSensor = () => {
    const stateSensors = this.state.sensors;
    const diffSensors = differenceBy(this.props.piguard.sensors, this.state.sensors, 'id');
    stateSensors.push(diffSensors[this.state.sensorIndex]);

    this.setState({
      sensors: stateSensors,
      sensorIndex: null,
    })
  };

  addSignaller = () => {
    const stateSignallers = this.state.signallers;
    const diffSignallers = differenceBy(this.props.piguard.signallers, this.state.signallers, 'id');
    stateSignallers.push(diffSignallers[this.state.signallerIndex]);

    this.setState({
      signallers: stateSignallers,
      signallerIndex: null,
    })
  };

  removeSignaller = (id) => {
    const stateSignallers = this.state.signallers;
    const index = stateSignallers.findIndex(signaller => signaller.id === id);

    if (index > -1) {
      stateSignallers.splice(index, 1);
      this.setState({
        signallers: stateSignallers,
        signallerIndex: null,
      })
    }
  };

  handleChangeSignaller = (event, index, value) => this.setState({signallerIndex:value});
  handleChangeSensor = (event, index, value) => this.setState({sensorIndex:value});

  handleClose = () => {
    this.props.setVisibility(false);
  };

  handleCreate = () => {
    const sensorLinks = [];
    this.state.sensors.forEach(sensor => sensorLinks.push(sensor.link));

    const signallerLinks = [];
    this.state.signallers.forEach(signaller => signallerLinks.push(signaller.link));

    this.props.setVisibility(false);
    this.props.create({
      name: this.state.name,
      sensors: sensorLinks,
      signallers: signallerLinks,
    })
  };

  removeSensor = (id) => {
    const stateSensors = this.state.sensors;
    const index = stateSensors.findIndex(sensor => sensor.id === id);

    if (index > -1) {
      stateSensors.splice(index, 1);
      this.setState({
        sensors: stateSensors,
        sensorIndex: null,
      })
    }
  };

  renderSensorChip(sensor) {

    return (
      <Chip
        key={sensor.id}
        onRequestDelete={() => this.removeSensor(sensor.id)}
        style={{margin:4}}
      >
        {sensor.name}
      </Chip>
    );
  }

  renderSignallerChip(signaller) {

    return (
      <Chip
        key={signaller.id}
        onRequestDelete={() => this.removeSignaller(signaller.id)}
        style={{margin:4}}
      >
        {signaller.name}
      </Chip>
    );
  }


  render() {
    const actions = [
      <FlatButton
        label="Cancel"
        primary
        onTouchTap={this.handleClose}
      />,
      <FlatButton
        label="Create"
        primary
        disabled={this.state.name === '' || this.state.signallers.length === 0 || this.state.sensors.length === 0}
        onTouchTap={this.handleCreate}
      />,
    ];

    const sensors = [];
    const diffSensors = differenceBy(this.props.piguard.sensors, this.state.sensors, 'id');
    for (let i = 0; i < diffSensors.length; i++) {
      sensors.push(<MenuItem value={i} key={diffSensors[i].id} primaryText={diffSensors[i].name} />)
    }


    const signallers = [];
    const diffSignallers = differenceBy(this.props.piguard.signallers, this.state.signallers, 'id');
    for (let i = 0; i < diffSignallers.length; i++) {
      signallers.push(<MenuItem value={i} key={diffSignallers[i].id} primaryText={diffSignallers[i].name} />)
    }

    return (
      <Dialog
        title="Create Zone"
        actions={actions}
        modal={true}
        contentStyle={{
          width: 350,
          maxWidth: 350,
        }}
        bodyStyle={{
          textAlign: 'center'
        }}
        open={this.props.open}
      >
        <TextField
          value={this.state.name}
          onChange={this.handleChangeName}
          hintText="Name"
        />
        <div style={{ display: 'flex' }}>
          <SelectField style={{ textAlign: 'left', flex: 1}} hintText="Sensor" value={this.state.sensorIndex} onChange={this.handleChangeSensor}>
            {sensors}
          </SelectField>
          <IconButton onClick={() => this.addSensor()} mini={true} style={{ marginLeft: '20px', flex: 1 }}>
            <ContentAdd />
          </IconButton>
        </div>
        <div style={{
          display: 'flex',
          flexWrap: 'wrap',
        }}>
          {this.state.sensors.map(sensor => this.renderSensorChip(sensor))}
        </div>

        <div style={{ display: 'flex' }}>
          <SelectField style={{ textAlign: 'left', flex: 1}} hintText="Signaller" value={this.state.signallerIndex} onChange={this.handleChangeSignaller}>
            {signallers}
          </SelectField>
          <IconButton onClick={() => this.addSignaller()} mini={true} style={{ marginLeft: '20px', flex: 1 }}>
            <ContentAdd />
          </IconButton>
        </div>
        <div style={{
          display: 'flex',
          flexWrap: 'wrap',
        }}>
          {this.state.signallers.map(signaller => this.renderSignallerChip(signaller))}
        </div>
      </Dialog>

    );
  }
}


export default ZoneDialog;
