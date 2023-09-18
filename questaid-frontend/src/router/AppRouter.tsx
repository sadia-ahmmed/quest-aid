import React from 'react'
import { Routes, Route } from "react-router-dom";
import Dashboard from '../pages/Dashboard';
import NotFound from '../pages/NotFound';
import CreateClubComponent from '../components/admin/CreateClubComponent';
import StudentListComponent from '../components/admin/StudentListComponent';

function AppRouter() {

    const entityType = localStorage.getItem("entityType")

    return (
        <Routes>
            <Route path='/dashboard' element={<Dashboard />} />
            {entityType === "admin" && <Route path='/admin/create-club' element={<CreateClubComponent />} />}
            {entityType === "admin" && <Route path='/admin/verify/students' element={<StudentListComponent />} />}
            <Route path='*' element={<NotFound />} />
        </Routes>
    )
}

export default AppRouter