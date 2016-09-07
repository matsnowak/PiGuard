import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

class SignallerDialog extends Component {
  state = {
    name: '',
    slotIndex: null,
  };

  handleChangeName = (event) => {
    this.setState({
      name: event.target.value,
    });
  };

  handleChangeSlot = (event, index, value) => this.setState({slotIndex:value});

  isSubmitDisabled = () => {
    return this.state.name === '';
  };

  handleClose = () => {
    this.props.setVisibility(false);
  };

  handleCreate = () => {
    this.props.setVisibility(false);
  };

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
        disabled={this.isSubmitDisabled()}
        onTouchTap={this.handleCreate}
      />,
    ];


    const slots = [];
    for (let i = 0; i < this.props.piguard.slots.length; i++) {
      slots.push(<MenuItem value={i} key={i} primaryText={this.props.piguard.slots[i].description} secondaryText={this.props.piguard.slots[i].address} />)
    }


    return (
      <Dialog
        title="Create Signaller"
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
        <SelectField style={{ textAlign: 'left'}} hintText="Slot" value={this.state.slotIndex} onChange={this.handleChangeSlot}>
          {slots}
        </SelectField>

      </Dialog>

    );
  }
}


export default SignallerDialog;
