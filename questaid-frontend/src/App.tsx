import React, { useContext, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import Login from './pages/Login/index'
import Dashboard from './pages/Dashboard/index'
import { useAuthContext } from './context/AuthContext';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppRouter from './router/AppRouter';
import AuthRouter from './router/AuthRouter';

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
