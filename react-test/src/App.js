import { BrowserRouter, Route, Link } from 'react-router-dom';
import React, { useState } from 'react';
import './App.css';

import Home from './views/Home';
import ImageUploadForm from './views/ImageUploadForm';
import ImageUploadBase64 from './views/ImageUploadBase64';
import Calender from './views/Calender';
import Kitchen from './views/socket-test/Kitchen';
import Hall from './views/socket-test/Hall';



function App() {
  return (
    <BrowserRouter>

      <Route path="/" component={Home} exact />

      <Route path="/image/form" component={ImageUploadForm} exact />
      <Route path="/image/base64" component={ImageUploadBase64} exact />

      <Route path="/calender" component={Calender} exact />

      <Route path="/kitchen" component={Kitchen} exact />
      <Route path="/hall" component={Hall} exact />

    </BrowserRouter>
  );
}

export default App;
