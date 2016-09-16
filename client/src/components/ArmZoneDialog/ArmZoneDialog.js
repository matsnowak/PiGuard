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
import moment from 'moment';

class ArmZoneDialog extends Component {

  static propTypes = {
    routes: PropTypes.array.isRequired
  };

  static contextTypes = {
    router: PropTypes.object.isRequired
  };

  state = {
    name: '',
    date: moment().add(this.props.delay, 'seconds'),
  };

  componentWillMount() {
    this.ticker = setInterval(() => {
      this.setState({
        date: moment().add(this.props.delay, 'seconds')
      })
    } , 1000)
  }

  componentWillUnmount() {
    clearInterval(this.ticker);
  }

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
          const date = this.state.date.toDate().toJSON();
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
        <TextField
          floatingLabelText="Start guard from"
          inputStyle={{ color: 'red' }}
          value={this.state.date.format('YYYY-MM-DD h:mm:ss')}
          disabled
        />
      </Dialog>

    );
  }
}


export default ArmZoneDialog;
