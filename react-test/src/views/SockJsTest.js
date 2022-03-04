import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import StompJs from 'stompjs';

function SockJsTest() {

    const socketUrl = "http://localhost:8080/ws"

    //
    const socket = new SockJS(socketUrl);
    const client = StompJs.Client = StompJs.over(socket);

    useEffect(() => {

        client.connect(
            {}, // 헤더
            () => { // 콜백

                // 메시지 송신
                client.send(
                    '/connect',             // 소켓서버주소
                    {},                     // 헤더
                    JSON.stringify("HI")    // 바디
                );
                
                // 메시지 수신
                client.subscribe(
                    '/topic/connect',    // 구독주소
                    (res) => {  // 콜백
                        console.log(res);
                    }
                );
            }
        );

    }, []);

    return (
        <div >

            <h2>소켓테스트</h2>
            <hr /><br />


        </div>
    );
}

export default SockJsTest;

