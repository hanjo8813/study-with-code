import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Calender(props) {
    // State
    const [date, setDate] = useState();

    // Handler
    const dateHandler = (e) => setDate(e.currentTarget.value)

    // Button
    const sendBtn = () => {
        console.log(date)
    }


    return (
        <div >

            <h2>달력 테스트</h2>
            <hr /><br />

            <input type='date' onChange={dateHandler}/>

            <button onClick={sendBtn}>전송</button>

        </div>
    );
}

export default Calender;

