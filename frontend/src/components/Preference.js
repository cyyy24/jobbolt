import React from 'react';
import {
    Button, Modal, Form, Input, Radio,Checkbox,message
} from 'antd';
import { API_ROOT } from '../constants'

  const CollectionCreateForm = Form.create({ name: 'form_in_modal' })(
    // eslint-disable-next-line
    class extends React.Component {
      render() {
        const {
          visible, onCancel, onCreate, form,
        } = this.props;
        const { getFieldDecorator } = form;
        return (
          <Modal
            visible={visible}
            title="Create a new preference"
            okText="Create"
            onCancel={onCancel}
            onOk={onCreate}
          >
            <Form layout="vertical">
              <Form.Item label="Job Position">
                {getFieldDecorator('keyword', {
                  rules: [{ required: true, message: 'Please input the title of collection!' }],
                })(
                  <Input placeholder="Software Engineer"/>
                )}
              </Form.Item>
              <Form.Item label="Location">
                {getFieldDecorator('location', {
                  rules: [{ required: false, message: 'Please input the title of collection!' }],
                })(
                  <Input placeholder="New York"/>
                )}
              </Form.Item>

              <Form.Item label="Company">
                {getFieldDecorator('company', {
                  rules: [{ required: false, message: 'Please input the title of collection!' }],
                })(
                  <Input placeholder="Facebook"/>
                )}
              </Form.Item>
              {/* <Form.Item label="Search Range">
                {getFieldDecorator('Range', {
                  initialValue: '1000',
                })(
                  <Radio.Group>
                    <Radio value="1000">1000</Radio>
                    <Radio value="2000">2000</Radio>
                    <Radio value="5000">5000</Radio>
                  </Radio.Group>
                )}
              </Form.Item>
              <Form.Item >
                {getFieldDecorator('agreement', {
                    valuePropName: 'checked',
                    initialValue: true,
                })(
                    <Checkbox>
                      I hope to get a similar position
                    </Checkbox>
                )}
              </Form.Item> */}
            </Form>
          </Modal>
        );
      }
    }
  );
  
export  class Preference extends React.Component {
    state = {
      visible: false,
    };
  
    showModal = () => {
      this.setState({ visible: true });
    }
  
    handleCancel = () => {
      this.setState({ visible: false });
    }
  
    handleCreate = () => {
      const form = this.formRef.props.form;
      form.validateFields((err, values) => {
        if (!err) {
          //console.log('Received values of form: ', values);
          let params = {"location": values.location,"keyword": values.keyword,"company": values.company}
          let query = Object.keys(params).map(k => k + '=' + encodeURI(params[k])).join('&');
          let url = API_ROOT +'/search?'+ query;
          fetch(url,{'mode': 'cors'}).then((response) => {
            console.log(response)
            if (response.ok) {
              return response.text();
            }
            //throw new Error(response.statusText);
          
          }).then((data) => {
            message.success('search Success!');
            //console.log(data);
            this.props.handleSearch(JSON.parse(data));
          }).catch((e) => {
            console.log(e);
            message.error('search Failed.');
          });

          form.resetFields();
          this.setState({ visible: false });
        } else {
          return;
        }   
      });
    }
  
    saveFormRef = (formRef) => {
      this.formRef = formRef;
    }
  
    render() {
      return (
        <div>
          <Button className="btn-preference" type="primary" onClick={this.showModal}>Preference</Button>
          <CollectionCreateForm
            wrappedComponentRef={this.saveFormRef}
            visible={this.state.visible}
            onCancel={this.handleCancel}
            onCreate={this.handleCreate}
          />
        </div>
      );
    }
  }
  