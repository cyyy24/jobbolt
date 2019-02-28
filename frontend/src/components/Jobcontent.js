import React from 'react';
import { Button } from 'antd';

export class Jobcontent extends React.Component {
    render(){
        return (
            <div className="job-content">
                <header>
                    <h2>Software Engineer</h2>
                    <p className="c-short">
                        <span className="c-name">eBay -<span className="c-address">San Jose,CA</span></span>
                        <Button size="small" type="primary" className="c-apply">Apply on Jobs-Ebay inc</Button>
                    </p>
                </header>
                <p className="c-info">
                Hi,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX<br />
                XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                Hi,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX<br />
                XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                Hi,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX<br />
                XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                Hi,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX<br />
                XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                Hi,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX<br />
                XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                Hi,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX<br />
                XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                Hi,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX<br />
                XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                </p>
            </div>
        )
    }
}