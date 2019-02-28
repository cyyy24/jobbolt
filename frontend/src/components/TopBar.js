import React from 'react';
import { Button } from 'antd';

import logo from './../images/logo.png';

export class TopBar extends React.Component {
    render() {
      return (
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          {this.props.isLoggedIn 
            ? <Button type="primary" ghost className="logout" onClick={this.props.handleLogOut}>Logout</Button>
            : null}
        </header>
      );
    }
  }