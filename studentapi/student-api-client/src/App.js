// src/App.js

import React from 'react';
import './App.css';
import StudentList from './components/StudentList';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <StudentList />
      </header>
    </div>
  );
}

export default App;
