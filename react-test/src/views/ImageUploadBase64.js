import React, { useEffect, useState } from 'react';
import axios from 'axios';

function ImageUploadBase64(props) {

    // Select Origin
    const api = 'http://localhost:8080/api';

    // State
    const [imageUrl, setImageUrl] = useState();
    const [image, setImage] = useState();

    // Handler
    const fileHandler = (e) => setImage(e.target.files[0])

    // Button
    const sendFile = async () => {
        const request = {
            "imageBase64": null,
            "dummy": "dummy"
        }

        // 사진을 올렸다면 base64를 추가해주자.
        if (image != null) {
            let base64 = await fileToBase64(image);
            console.log(base64)
            request.imageBase64 = base64;
            
        }

        axios.post(
            api + `/test/image`,
            request
        ).then(res => {
            console.log(res);
            setImageUrl(res.data.resultUrl);
        }).catch(
            err => {
                console.log(err.response)
            }
        );
    }

    // Util
    const fileToBase64 = async (file) => {
        return new Promise(
            (result) => {
                let reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onloadend = () => {
                    result(reader.result)
                }
            }
        )
    }


    return (
        <div >

            <h2>이미지 업로드 테스트 (Base64)</h2>
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

export default ImageUploadBase64;

