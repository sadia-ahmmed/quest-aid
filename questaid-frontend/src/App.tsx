import React from 'react';
import logo from './logo.svg';
import './App.css';
import Login from "./screens/login/Login";
import Navbar from './components/Navbar';
import NewsFeed from './screens/student/newsfeed';
import Feed from './screens/student/Feed';
function App() {
  return (
      <div className="App">
       <div className="bg-primary w-full overflow-hidden" style={{backgroundColor:"teal"  , borderBottomLeftRadius:5,borderBottomRightRadius:5, borderBottomColor:"teal", borderBottom: 10}}>
        <Navbar />
        </div > 
        {/* <Login/> */}
       <Feed/>
        </div> 

    
  );
}

export default App;
