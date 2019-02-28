import React, { Component } from 'react';
import { TopBar } from './TopBar';
import { Main } from './Main';
import { USER_ID } from '../constants';
import { message } from 'antd';
import { API_ROOT } from '../constants';
class App extends Component {
  state = {
    isLoggedIn: Boolean(sessionStorage.getItem(USER_ID))
  }
  handleSuccessfulLogin = (user_id) => {
    //localStorage.setItem(USER_ID, user_id);
    this.setState({ isLoggedIn: true});
    sessionStorage.setItem(USER_ID, user_id);
  }
  handleLogOut = () => {
    fetch(`${API_ROOT}/logout`, {
      method: 'POST'
    }).then((response) => {
      if (response.ok) {
        return response.text();
      }
      throw new Error(response.statusText);
    }).then((data) => {
      //localStorage.removeItem(USER_ID);
      sessionStorage.removeItem(USER_ID)
      this.setState({ isLoggedIn: false });
      message.success('Logout Success');
    }).catch((e) => {
      console.log(e);
      message.error('Logout Failed.');
    });
    
  }
  render() {
    return (
      <div className="App">
        <TopBar 
          isLoggedIn={this.state.isLoggedIn} 
          handleLogOut={this.handleLogOut}
          />
        <Main 
          handleSuccessfulLogin={this.handleSuccessfulLogin}
          isLoggedIn={this.state.isLoggedIn}
        />
      </div>
    );
  }
}

export default App;
