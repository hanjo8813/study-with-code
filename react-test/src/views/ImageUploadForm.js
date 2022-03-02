import React, { useEffect, useState } from 'react';
import axios from 'axios';

function ImageUploadForm(props) {

    // Select Origin
    const api = 'http://localhost:8080/api';

    // State
    const [imageUrl, setImageUrl] = useState();
    const [image, setImage] = useState();

    // Handler
    const fileHandler = (e) => setImage(e.target.files[0])

    // Button
    const sendFile = async () => {
        // 참고 : https://gaemi606.tistory.com/entry/Spring-Boot-multipartform-data-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-React-Axios-REST-API

        // json 더미 파일 생성 -> blob으로 변환
        let json = {
            "text" : "text",
            "num" : 1
        }
        let blob = new Blob([JSON.stringify(json)], { type: "application/json" })

        // 요청할 formData 생성
        const formData = new FormData();
        formData.append("file", image);
        formData.append("json", blob);

        axios.post(
            api + `/test/image/temp`,
            formData,
            { headers: { "Content-Type": `multipart/form-data` } }
        ).then(res => {
            console.log(res);
            setImageUrl(res.data.resultUrl);
        }).catch(
            err => {
                console.log(err.response)
            }
        );
    }


    return (
        <div >

            <h2>이미지 업로드 테스트 (Form)</h2>
            <hr /><br />
            <br />
            <input type="file" onChange={fileHandler} />

            <br /><br />
            <button onClick={sendFile}>전송</button>

            <br /><br /><hr/>

            <img src={imageUrl} alt="이미지" />

            <br /><br /><br /><br />


        </div>
    );
}

export default ImageUploadForm;

