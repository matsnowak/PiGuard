import React from 'react';
import { Route } from 'react-router';
import App from './containers/App/App';
import Login from './containers/Login/Login';

export default (
  <Route>
    <Route path="/" component={App}>
    </Route>
    <Route path="/login" component={Login} />
  </Route>
)
