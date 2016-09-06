import React, { Component } from 'react';
import './Login.css';
import LoginBox from '../../components/LoginBox/LoginBox';

const style = {
  height: 50,
  width: 300,
  margin: 5,
  textAlign: 'center',
  display: 'block',
};

class Login extends Component {
  render() {
    return (
      <div className="Login-background">
        <div className="Login-box">
          <div style={style}>
            <div className="Login-logo" style={{ height: '48px', width: '48px', display: 'inline-block', verticalAlign: 'middle' }}/>
            <h1 style={{ margin:0, display: 'inline-block', verticalAlign: 'middle'}}> Guard </h1>
          </div>

          <LoginBox />
        </div>
      </div>
    );
  }
}

export default Login;
