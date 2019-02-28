import React from 'react';
import { Input , message } from 'antd';
import {API_ROOT} from '../constants';
const Search = Input.Search;

export class SearchSubmit extends React.Component {
    onSearch = (value) => {
            console.log('values of search: ', value);
            let params = { "keyword": value }
            let query = Object.keys(params).map(k => k + '=' + encodeURI(params[k])).join('&');
            let url = API_ROOT +'/search?'+ query;
            fetch(url,{'mode': 'cors'}).then((response) => {
              if (response.ok) {
                return response.text();
              }
              //throw new Error(response.statusText);
            
            }).then((data) => {
              message.success('search Success!');
              //console.log(data);
              value='';
              this.props.handleSearch(JSON.parse(data));
            }).catch((e) => {
              console.log(e);
              message.error('search Failed.');
            });
  
            
    }
    render(){
        return(
                <Search
                placeholder="input search text"
                onSearch={this.onSearch}
                style={{ width: 309 , float:'right' ,margin:'-40px 227px 0 0'}}
                className="my-search"
                />
        )
    }
}