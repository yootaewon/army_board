import React, { useEffect, useState } from 'react';
import axios from 'axios'; // Axios 라이브러리를 import 합니다.
import './App.css';

function App() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    axios.get('/api/hello')
      .then((res) => {
        setMessage(res.data);
      })
      .catch((e) => console.error("There was an error!", e));
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <p>
          Message from Spring Boot: {message}
        </p>
      </header>
    </div>
  );
}

export default App;