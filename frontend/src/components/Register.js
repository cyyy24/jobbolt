import React from 'react';
import { Form, Input, Button, message } from 'antd';
import { Link } from 'react-router-dom';
import { API_ROOT } from '../constants';

export class RegistrationForm extends React.Component {
    state = {
      confirmDirty: false,
      autoCompleteResult: [],
    };

    handleSubmit = (e) => {
      e.preventDefault();
      this.props.form.validateFieldsAndScroll((err, values) => {
          if (!err) {
              console.log('Received values of form: ', values);
              //send request
              fetch(`${API_ROOT}/register`, {
                  method: 'POST',
                  body: JSON.stringify({
                    user_id: values.user_id,
                    password: values.password,
                    email: values.email,
                    first_name: values.first_name,
                    last_name: values.last_name,
                  }),
              }).then((response) => {
                  if (response.ok) {
                      return response.text();
                  }
                  throw new Error(response.statusText);
              }).then((data) => {
                      console.log(data);
                      message.success('Registration Succeed!');
                      this.props.history.push('/login');
              })
              .catch((e) => {
                  console.log(e);
                  message.error('Registration Failed.');
              });
          }
      });
    }

    handleConfirmBlur = (e) => {
      const value = e.target.value;
      this.setState({ confirmDirty: this.state.confirmDirty || !!value });
    }

    compareToFirstPassword = (rule, value, callback) => {
      const form = this.props.form;
      if (value && value !== form.getFieldValue('password')) {
          callback('Two passwords that you enter is inconsistent!');
      } else {
          callback();
      }
    }

    validateToNextPassword = (rule, value, callback) => {
      const form = this.props.form;
      if (value && this.state.confirmDirty) {
          form.validateFields(['confirm'], { force: true });
      }
      callback();
    }
    render() {
      const { getFieldDecorator } = this.props.form;

      const formItemLayout = {
          labelCol: {
              xs: { span: 24 },
              sm: { span: 8 },
          },
          wrapperCol: {
              xs: { span: 24 },
              sm: { span: 16 },
          },
      };
      const tailFormItemLayout = {
          wrapperCol: {
              xs: {
                  span: 24,
                  offset: 0,
              },
              sm: {
                  span: 16,
                  offset: 8,
              },
          },
      };
      return (
        <div className="register">
          <Form onSubmit={this.handleSubmit} className="register-form">
                <Form.Item
                    {...formItemLayout}
                    label="Identity"
                >
                    {getFieldDecorator('user_id', {
                        rules: [{ required: true, message: 'Please input your username!' }],
                    })(
                        <Input placeholder="ID card or mobile number"/>
                    )}
                </Form.Item>
                <Form.Item
                    {...formItemLayout}
                    label="Password"
                >
                    {getFieldDecorator('password', {
                        rules: [{
                            required: true, message: 'Please input your password!',
                        }, {
                            validator: this.validateToNextPassword,
                        }],
                    })(
                        <Input type="password" placeholder="password" />
                    )}
                </Form.Item>
                <Form.Item
                {...formItemLayout}
                label="E-mail"
                >
                {getFieldDecorator('email', {
                    rules: [{
                    type: 'email', message: 'The input is not valid E-mail!',
                    }, {
                    required: true, message: 'Please input your E-mail!',
                    }],
                })(
                    <Input placeholder="email@gmail.com"/>
                )}
                </Form.Item>
                <Form.Item
                    {...formItemLayout}
                    label="First Name"
                >
                    {getFieldDecorator('first_name', {
                        rules: [{ required: true, message: 'Please input your username!' }],
                    })(
                        <Input placeholder="first name"/>
                    )}
                </Form.Item>
                <Form.Item
                    {...formItemLayout}
                    label="Last Name"
                >
                    {getFieldDecorator('last_name', {
                        rules: [{ required: true, message: 'Please input your username!' }],
                    })(
                        <Input placeholder="Last Name"/>
                    )}
                </Form.Item>
                <Form.Item {...tailFormItemLayout}>
                <Button type="primary" htmlType="submit">Register</Button>
                    <p>I already have an account, go back to <Link to="/login" className="font-login">login</Link> </p>
                </Form.Item>
            </Form>
        </div>
        );
    }
}

export const Register = Form.create({ name: 'register' })(RegistrationForm);
