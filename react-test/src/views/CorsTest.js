import React, { useEffect, useState } from 'react';
import axios from 'axios';

function CorsTest(props) {

    // Select Origin
    const api = 'http://localhost:8080/temp';

    // Button
    const sendPostRequest = async () => {
        const request = {
            "num": 1,
            "str" : "a"
        }

        axios.post(
            api,
            request
        ).then(res => {
            console.log(res);
        }).catch(
            err => {
                console.log(err.response)
            }
        );
    }

    const sendGetRequest = async () => {
        axios.get(
            api
        ).then(res => {
            console.log(res);
        }).catch(
            err => {
                console.log(err.response)
            }
        );
    }

    const sendDeleteRequest = async () => {
        axios.delete(
            api
        ).then(res => {
            console.log(res);
        }).catch(
            err => {
                console.log(err.response)
            }
        );
    }

    return (
        <div >

            <h2>cors test</h2>
            <hr /><br />

            <br /><br />
            <button onClick={sendPostRequest}>post 요청</button>
            <button onClick={sendGetRequest}>get 요청</button>
            <button onClick={sendDeleteRequest}>delete 요청</button>

            <br /><br /><hr/>

        </div>
    );
}

export default CorsTest;

