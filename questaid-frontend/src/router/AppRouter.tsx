import React from 'react'
import { Routes, Route } from "react-router-dom";
import Dashboard from '../pages/Dashboard';
import NotFound from '../pages/NotFound';
import CreateClubComponent from '../components/admin/CreateClubComponent/CreateClub.component';
import StudentListComponent from '../components/admin/StudentListComponent/StudentList.component';
import { CreateAnnouncement } from '../components/club/CreateAnnoucement/CreateAnnouncement.component';
import ClubListComponent from '../components/admin/ClubListComponent/ClubListComponent.component';
import ClubTreasuryHistory from '../components/admin/ClubTreasuryHistory/ClubTreasuryHistory.component';
import { AdminTreasury } from '../components/admin/AdminTreasury/AdminTreasury.component';

function AppRouter() {

    const entityType = localStorage.getItem("entityType")

    return (
        <Routes>
            <Route path='/dashboard' element={<Dashboard />} />
            <Route path='*' element={<NotFound />} />

            {entityType === "club" && <Route path='/club/announcement/create' element={<CreateAnnouncement />} />}

            {/* ADMIN ROUTES */}
            {entityType === "admin" && <Route path='/admin/create-club' element={<CreateClubComponent />} />}
            {entityType === "admin" && <Route path='/admin/verify/students' element={<StudentListComponent />} />}
            {entityType === "admin" && <Route path='/admin/clubs' element={<ClubListComponent />} />}
            {entityType === "admin" && <Route path='/admin/club/:cid/treasury/:tid' element={<ClubTreasuryHistory />} />}
            {entityType === "admin" && <Route path='/admin/treasury' element={<AdminTreasury />} />}
        </Routes>
    )
}

export default AppRouter