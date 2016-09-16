import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

class SensorDialog extends Component {
  state = {
    name: '',
    triggerIndex: null,
    resistanceIndex: null,
    slotIndex: null,
  };

  getTriggers = () => {
    return this.props.piguard.triggers;
  };

  getResistances = () => {
    return this.props.piguard.resistances;
  };


  handleChangeName = (event) => {
    this.setState({
      name: event.target.value,
    });
  };

  handleChangeTrigger = (event, index, value) => this.setState({triggerIndex:value});

  handleChangeResistance = (event, index, value) => this.setState({resistanceIndex:value});

  handleChangeSlot = (event, index, value) => this.setState({slotIndex:value});

  handleClose = () => {
    this.props.setVisibility(false);
  };

  handleCreate = () => {
    this.props.setVisibility(false);
    this.props.create({
      name: this.state.name,
      triggeredOn: this.props.piguard.triggers[this.state.triggerIndex],
      pullResistance: this.props.piguard.resistances[this.state.resistanceIndex],
      slot: this.props.piguard.freeSlots[this.state.slotIndex].link
    });
  };

  isSubmitDisabled = () => {
    return this.state.name === '' || this.state.triggerIndex === null || this.state.resistanceIndex === null;
  };

  render() {
    const actions = [
      <FlatButton
        label="Cancel"
        secondary
        onTouchTap={this.handleClose}
      />,
      <FlatButton
        label="Create"
        primary
        disabled={this.isSubmitDisabled()}
        onTouchTap={this.handleCreate}
      />,
    ];

    const triggers = this.getTriggers();
    const resistances = this.getResistances();

    const slots = [];
    for (let i = 0; i < this.props.piguard.freeSlots.length; i++) {
      slots.push(<MenuItem value={i} key={i} primaryText={this.props.piguard.freeSlots[i].description} secondaryText={this.props.piguard.freeSlots[i].address} />)
    }

    return (
      <Dialog
        title="Create Sensor"
        actions={actions}
        contentStyle={{
          width: 350,
          maxWidth: 350,
        }}
        bodyStyle={{
          textAlign: 'center'
        }}
        modal={true}
        open={this.props.open}
      >
        <TextField
          value={this.state.name}
          onChange={this.handleChangeName}
          hintText="Name"
        />
        <SelectField style={{ textAlign: 'left'}} floatingLabelText="Triggered On" value={this.state.triggerIndex} onChange={this.handleChangeTrigger}>
          <MenuItem value={0} primaryText={triggers[0]} />
          <MenuItem value={1} primaryText={triggers[1]} />
          <MenuItem value={2} primaryText={triggers[2]} />
        </SelectField>

        <SelectField style={{ textAlign: 'left'}} floatingLabelText="Pull resistance" value={this.state.resistanceIndex} onChange={this.handleChangeResistance}>
          <MenuItem value={0} primaryText={resistances[0]} />
          <MenuItem value={1} primaryText={resistances[1]} />
          <MenuItem value={2} primaryText={resistances[2]} />
        </SelectField>

        <SelectField style={{ textAlign: 'left'}} floatingLabelText="Slot" value={this.state.slotIndex} onChange={this.handleChangeSlot}>
          {slots}
        </SelectField>


      </Dialog>

    );
  }
}


export default SensorDialog;
