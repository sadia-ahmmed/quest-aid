import React from 'react'
import { Routes, Route } from "react-router-dom";
import Dashboard from '../pages/Dashboard';
import NotFound from '../pages/NotFound';

function AppRouter() {
    return (
        <Routes>
            <Route path='/dashboard' element={<Dashboard />} />
            <Route path='*' element={<NotFound />} />
        </Routes>
    )
}

export default AppRouter