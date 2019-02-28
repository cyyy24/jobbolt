import React from 'react';
import { Card ,Icon } from 'antd';

function ItemList(props) {
    return (
        props.arrSearch.map( (item,i) =>  
            <Card 
                hoverable
                extra={<a href="#"><Icon theme="outlined" className="heart" type="heart"/></a>}
                title={item.job_title}
                bordered={false}
                key={i}
                url={item.url}
                job_id={item.jobId}
            >
                <p className="c-short" >
                    <span className="c-name">{item.company} </span>
                    <span className="c-address">{item.location}</span>
                    <span className="c-platform">via {item.platform} inc</span>
                </p>
            </Card>
           )
    )
// category: "glassdoor category 0"
// company: "Undefined Labs"
// jobId: "-1293910190"
// job_title: "Software Engineer - Backend (Python)"
// location: "Madrid, Community of Madrid, ES"
// platform: "glassdoor"
// url: "https://www.glassdoor"
}
export class Joblist1 extends React.Component {
    render(){
        return (
            <div className="job-list">
                <ItemList arrSearch={this.props.arrSearch}/>
                <Card 
                    hoverable
                    extra={<a href="#"><Icon theme="outlined" className="heart" type="heart"/></a>}
                    title="Software Engineer" 
                    bordered={false}
                >
                    <p className="c-short">
                        <span className="c-name">eBay </span>
                        <span className="c-address">San Jose,CA</span>
                        <span className="c-platform">via Jobs-Ebay inc</span>
                    </p>
                </Card>
                <Card 
                    hoverable
                    extra={<a href="#"><Icon type="heart" theme="filled" className="heart"/></a>}
                    title="Software Engineer" 
                    bordered={false}
                >
                    <p className="c-short">
                        <span className="c-name">eBay</span>
                        <span className="c-address">San Jose,CA</span>
                        <span className="c-platform">via Jobs-Ebay inc</span>
                    </p>
                </Card>
                <Card 
                    hoverable
                    extra={<a href="#"><Icon theme="outlined" className="heart" type="heart"/></a>}
                    title="Software Engineer" 
                    bordered={false}
                >
                    <p className="c-short">
                        <span className="c-name">eBay</span>
                        <span className="c-address">San Jose,CA</span>
                        <span className="c-platform">via Jobs-Ebay inc</span>
                    </p>
                </Card>
            </div>
        )
    }
}