import React, { useEffect, useState } from 'react';
import socketio from 'socket.io-client';

function SocketIoTest() {


    const socketUrl = "http://localhost:8888/ws?token=dd"

    const client = socketio.connect(socketUrl)

    // client.on("connect", (res) => {
    //     console.log("dd")
    // })
    // client.on("ws", (res) => {
    //     console.log("dd")
    // })


    return (
        <div >

            <h2>소켓테스트</h2>
            <hr /><br />


        </div>
    );
}

export default SocketIoTest;

