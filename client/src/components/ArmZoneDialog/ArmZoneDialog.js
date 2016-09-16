import React, { Component, PropTypes } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import IconButton from 'material-ui/IconButton';
import ContentAdd from 'material-ui/svg-icons/content/add';
import Chip from 'material-ui/Chip';
import MenuItem from 'material-ui/MenuItem';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import differenceBy from 'lodash/differenceBy';
import DatePicker from 'material-ui/DatePicker';
import TimePicker from 'material-ui/TimePicker';

class ArmZoneDialog extends Component {

  static propTypes = {
    routes: PropTypes.array.isRequired
  };

  static contextTypes = {
    router: PropTypes.object.isRequired
  };

  state = {
    name: '',
    date: new Date(),
  };

  handleChange = (event, date) => {
    this.setState({
      date: date,
    });
  };

  render() {
    const actions = [
      <FlatButton
        label="Cancel"
        primary
        onTouchTap={() => this.props.actions.endArming()}
      />,
      <FlatButton
        label="Arm"
        primary
        onTouchTap={() =>  {
          const date = this.state.date.toJSON();
          this.props.actions.armZone({
            startGuardFrom: date.substring(0, date.length - 1),
            zone: this.props.zone.link,
          }, this.context.router);
          this.props.actions.endArming();
        }}

      />,
    ];


    return (
      <Dialog
        title="Arm Zone"
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
          value={this.props.zone.name}
          disabled={true}
          floatingLabelText="Name"
        />
        <DatePicker
          floatingLabelText="Date"
          value={this.state.date}
          onChange={this.handleChange}
        />
        <TimePicker
          format="24hr"
          floatingLabelText="Time"
          value={this.state.date}
          onChange={this.handleChange}
        />
      </Dialog>

    );
  }
}


export default ArmZoneDialog;
