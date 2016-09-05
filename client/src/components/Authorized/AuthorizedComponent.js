import React, { PropTypes } from 'react';
import { isLogged } from '../../services/authService';

class AuthorizedComponent extends React.Component {
  static propTypes = {
    routes: PropTypes.array.isRequired
  };

  static contextTypes = {
    router: PropTypes.object.isRequired
  };

  componentWillMount() {
    const { router } = this.context;

    if (!isLogged()) {
      router.push('/login');
    }

  }
}

export default AuthorizedComponent;
