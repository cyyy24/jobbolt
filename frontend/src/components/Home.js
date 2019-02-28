import React from 'react';
import { Tabs ,Row ,Col,message } from 'antd';
import { SearchSubmit } from './SearchSubmit.js';
import { Jobcontent } from './Jobcontent.js';
import { Joblist1 } from './Joblist1.js';
import { Joblist2 } from './Joblist2.js';
import { Preference } from './Preference';
import { API_ROOT , USER_ID } from '../constants';
//import { instanceOf } from 'prop-types';
//import { withCookies, Cookies, CookiesProvider } from 'react-cookie';

const TabPane = Tabs.TabPane;

export class Home extends React.Component {
    state = {
        arrSearch: [],
        arrSave: [],
    }
    handleSearch = (data) => {
        this.setState({
            arrSearch: data
        })
    }
    handleOnChange(key) {
        if(key==2){
            console.log( sessionStorage.getItem(USER_ID))
            //let params = {"location": values.location,"keyword": values.keyword,"company": values.company}
              //let query = Object.keys(params).map(k => k + '=' + encodeURI(params[k])).join('&');
            let url = API_ROOT +'/save';
            fetch(url,{
                'mode': 'no-cors'
            }).then((response) => {
                console.log(response)
                if (response.ok) {
                    return response.text();
                }
                //throw new Error(response.statusText);
            }).then((data) => {
                message.success('search Success!');
                console.log(data);
                // this.setState({
                //     arrSave: data
                // })
              }).catch((e) => {
                console.log(e);
                message.error('search Failed.');
              });
        }
      }
    render(){
        return (
            <div className="home" >
                <SearchSubmit handleSearch={this.handleSearch}/>
                <Preference handleSearch={this.handleSearch}/>
                <Tabs defaultActiveKey="1" onChange={this.handleOnChange}>
                    <TabPane tab="NEARBY JOBS" key="1">
                        <Row gutter={16} type="flex" justify="space-around">
                            <Col span={6}><Joblist1 arrSearch={this.state.arrSearch}/></Col>
                            <Col span={18}><Jobcontent /></Col>
                        </Row>
                    </TabPane>
                    <TabPane tab="SAVED" key="2">
                        <Row gutter={16} type="flex" justify="space-around">
                            <Col span={6}></Col>
                            {/* <Joblist2 arrSave={this.state.arrSave}/> */}
                            <Col span={18}><Jobcontent /></Col>
                        </Row>
                    </TabPane>
                    <TabPane tab="APPLIED" key="3">Content of Tab Pane 3</TabPane>
                    <TabPane tab="RECOMMENDED JOBS" key="4">Content of Tab Pane 3</TabPane>
                </Tabs>
            </div>
        )
    }
}