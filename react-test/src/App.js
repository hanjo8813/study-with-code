import { BrowserRouter, Route, Link } from 'react-router-dom';
import React, { useState } from 'react';
import './App.css';

import Home from './views/Home';
import ImageUploadForm from './views/ImageUploadForm';
import ImageUploadBase64 from './views/ImageUploadBase64';
import Calender from './views/Calender';
import SockJsTest from './views/SockJsTest';
import SocketIoTest from './views/SocketIoTest';
import CorsTest from './views/CorsTest';



function App() {
  return (
    <BrowserRouter>

      <Route path="/" component={Home} exact />

      <Route path="/image/form" component={ImageUploadForm} exact />
      <Route path="/image/base64" component={ImageUploadBase64} exact />

      <Route path="/calender" component={Calender} exact />

      <Route path="/sockjs" component={SockJsTest} exact />
      <Route path="/socketio" component={SocketIoTest} exact />

      <Route path="/corstest" component={CorsTest} exact />


    </BrowserRouter>
  );
}

export default App;
