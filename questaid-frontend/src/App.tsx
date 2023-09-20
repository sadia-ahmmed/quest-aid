import React, { useContext, useEffect } from 'react';
import './App.css';
import { useAuthContext } from './context/AuthContext';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppRouter from './router/AppRouter';
import AuthRouter from './router/AuthRouter';
import Navbar from './components/general/Navbar/Navbar.component';
import IndexPage from './pages/IndexPage';

const App = () => {

  const { isLoggedIn, setLoggedIn, setUserCache, setJwtToken } = useAuthContext()

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
