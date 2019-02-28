import React from 'react';
import { Form, Icon, Input, Button, message } from 'antd';
import { Link } from 'react-router-dom';
import { API_ROOT } from '../constants';

class NormalLoginForm extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        //console.log('Received values of form: ', values);
        fetch(`${API_ROOT}/login`, {
          method: 'POST',
          body: JSON.stringify({
            user_id: values.user_id,
            password: values.password,
          }),
        }).then((response) => {
          if (response.ok) {
            return response.text();
          }
          throw new Error(response.statusText);
        }).then((data) => {
          message.success('Login Success!');
          console.log(data);
          this.props.handleSuccessfulLogin(JSON.parse(data).user_id)
        }).catch((e) => {
          console.log(e);
          message.error('Login Failed.');
        });
      }
    });
  }
    render() {
      const { getFieldDecorator } = this.props.form;
      return (
        <div className="login">
          <Form layout="inline" onSubmit={this.handleSubmit} className="login-form">
            <Form.Item>
              {getFieldDecorator('user_id', {
                rules: [{ required: true, message: 'Please input your ID card or phone number!' }],
              })(
                <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="User ID" />
              )}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('password', {
                rules: [{ required: true, message: 'Please input your Password!' }],
              })(
                <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
              )}
            </Form.Item>
            <Form.Item>
              {/* <Link to="/home"> */}
              <Button type="primary" htmlType="submit" className="login-form-button">
                Log in
              </Button>
              {/* </Link> */}
              Or  <Link to="/register">register now !</Link>
            </Form.Item>
          </Form>
        </div>
      );
    }
   }
   
export const Login = Form.create({ name: 'normal_login' })(NormalLoginForm);