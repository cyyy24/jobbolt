import React from 'react';
import { Route, Switch , Redirect } from 'react-router-dom';
import { Login } from './Login';
import { Register } from './Register';
import { Home } from './Home';
export class Main extends React.Component {
    getLogin = () => {
      return this.props.isLoggedIn 
      ? <Redirect to="/home" /> 
      : <Login handleSuccessfulLogin={this.props.handleSuccessfulLogin}/>
    }
    getHome = () => {
        return this.props.isLoggedIn ? <Home /> : <Redirect to="/login" />;
    }
    render() {
      return (
        <div className="main">
          <Switch>
              <Route exact path="/" render={this.getLogin} />
              <Route path="/login" render={this.getLogin} />
              <Route path="/register" component={Register} />
              <Route path="/home" component={this.getHome} />
          </Switch>
        </div>
      );
    }
  }