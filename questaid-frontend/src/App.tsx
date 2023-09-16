import React, { useContext, useEffect } from 'react';
import './App.css';
import { useAuthContext } from './context/AuthContext';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppRouter from './router/AppRouter';
import AuthRouter from './router/AuthRouter';
import Navbar from './components/Navbar';
function App() {

  const { isLoggedIn, setLoggedIn, userCache, setUserCache, jwtToken, setJwtToken } = useAuthContext()

  useEffect(() => {
    const locallyStoredLoggedIn = localStorage.getItem("loggedIn")

    if (locallyStoredLoggedIn) {
      setLoggedIn(true)
      setJwtToken(localStorage.getItem("token"))
      setUserCache(localStorage.getItem("userIdentifier"))
    }

  }, [])

  return (
    <div className='App'>
      <BrowserRouter>
        {isLoggedIn ? <AppRouter /> : <AuthRouter />}
      </BrowserRouter>
    </div>
  );
}

export default App;
