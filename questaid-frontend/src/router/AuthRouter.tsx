import React from 'react'
import { Routes, Route } from 'react-router-dom'
import LoginPage from '../pages/Login'
import SignUp from '../pages/Signup'
import NotFound from '../pages/NotFound'

function AuthRouter() {
    return (
        <Routes>
            <Route index path='/login' element={<LoginPage />} />
            <Route path='/signup' element={<SignUp />} />
            <Route path='*' element={<NotFound />} />
        </Routes>
    )
}

export default AuthRouter