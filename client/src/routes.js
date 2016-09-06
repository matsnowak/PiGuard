import React from 'react';
import { Route, IndexRoute } from 'react-router';
import App from './containers/App/App';
import Login from './containers/Login/Login';
import Zones from './containers/Zones/Zones';
import Signallers from './containers/Signallers/Signallers';
import Sensors from './containers/Sensors/Sensors'

export default (
  <Route>
    <Route path="/" component={App}>
      <IndexRoute component={Zones} />
      <Route path="/signallers" component={Signallers} />
      <Route path="/sensors" component={Sensors} />
    </Route>
    <Route path="/login" component={Login} />
  </Route>
)
