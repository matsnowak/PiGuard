import React, { Component, PropTypes } from 'react';
import Paper from 'material-ui/Paper';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import IconButton from 'material-ui/IconButton';
import LinearProgress from 'material-ui/LinearProgress';

import { login } from '../../services/authService';

const style = {
  height: 75,
  width: 200,
  margin: 20,
  marginTop: 0,
  textAlign: 'center',
  display: 'inline-block',
};

class LoginBox extends Component {
  static contextTypes = {
    router: PropTypes.object.isRequired
  };


  constructor(props) {
    super(props);

    this.state = {
      errorText: '',
      value: '',
      disabled: true,
      isLogging: false,
    };
  }

  loginWithPin = () => {
    this.setState({
      isLogging: true,
    });
    login(this.state.value)
      .then(isSuccess => {
        if (isSuccess) {
          this.setState({
            isLogging: false,
          });
          this.context.router.push('/');
        } else {
          this.setState({
            isLogging: false,
            errorText: 'Incorrect',
          });
        }
      })
  };

  handleChange = (event) => {
    if (event.target.value.length === 0 || /^\d+$/.test(event.target.value)) {
      this.setState({
        value: event.target.value,
        disabled: event.target.value.length === '',
        errorText: '',
      });
    }
  };

  render() {
    return (
      <div style={style}>
        <TextField
          style={{ width: 75, marginLeft: '25px' }}
          hintStyle={{ color: 'black', textAlign: 'center' }}
          hintText="Enter Pin"
          value={this.state.value}
          onChange={this.handleChange}
          errorText={this.state.errorText}
          type="password"
        />
        <IconButton
          onClick={this.loginWithPin}
          iconClassName="material-icons"
          style={{ verticalAlign: 'bottom', margin: 0, padding: 0 }}
          disabled={this.state.disabled}
        >
          arrow_forward
        </IconButton>
      </div>
    );
  }
}

export default LoginBox;
