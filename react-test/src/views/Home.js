import React from 'react';
import { Link } from 'react-router-dom';

function Home(props) {

    return (
        <div >
            <Link to={`/image/form`}>이미지 업로드 (폼 형식)</Link>
            <br/>
            <Link to={`/image/base64`}>이미지 업로드 (Base64)</Link>
            <br/>
            <Link to={`/calender`}>달력 테스트</Link>
            <br/>
            <Link to={`/kitchen`}>소켓 테스트 - 주방</Link>
            <br/>
            <Link to={`/hall`}>소켓 테스트 - 홀</Link>
            <br/>
        </div>
    );
}

export default Home;

