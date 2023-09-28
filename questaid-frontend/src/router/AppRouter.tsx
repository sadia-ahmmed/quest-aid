import React from 'react'
import { Routes, Route, useLocation } from "react-router-dom";
import Dashboard from '../pages/Dashboard';
import NotFound from '../pages/NotFound';
import CreateClubComponent from '../components/admin/CreateClubComponent/CreateClub.component';
import StudentListComponent from '../components/admin/StudentListComponent/StudentList.component';
import { CreateAnnouncement } from '../components/club/CreateAnnoucement/CreateAnnouncement.component';
import ClubListComponent from '../components/admin/ClubListComponent/ClubListComponent.component';
import ClubTreasuryHistory from '../components/admin/ClubTreasuryHistory/ClubTreasuryHistory.component';
import { AdminTreasury } from '../components/admin/AdminTreasury/AdminTreasury.component';
import Sidebar from '../components/Sidebar';
import EventComponent from '../components/club/EventComponent/EventComponent.component';
import CreateEvent from '../components/club/CreateEvent/CreateEvent.component';
import EventPublicPage from '../pages/EventPublicPage';
import ManageDepartment from '../components/club/ManageDepartment/ManageDepartment.component';
import CreateDepartment from '../components/club/CreateDepartment/CreateDepartment.component';
import MembersList from '../components/club/MembersList/MembersList.component';

function AppRouter() {

    const entityType = localStorage.getItem("entityType")

    const route = useLocation()

    return (
        <>
            {/* if the url contains the word public, do not show the sidebar */}
            {!route.pathname.includes("public") && <Sidebar />}
            <Routes>
                <Route path='/dashboard' element={<Dashboard />} />
                <Route path='/event/public/:id' element={<EventPublicPage />} />
                <Route path='*' element={<NotFound />} />

                {/* CLUB ROUTES */}
                {entityType === "club" && <Route path='/club/announcement/create' element={<CreateAnnouncement />} />}

                {entityType === "club" && <Route path='/club/events' element={<EventComponent />} />}
                {entityType === "club" && <Route path='/club/event/create' element={<CreateEvent />} />}

                {entityType === "club" && <Route path='/club/departments' element={<ManageDepartment />} />}
                {entityType === "club" && <Route path='/club/departments/create' element={<CreateDepartment />} />}

                {entityType === "club" && <Route path='/club/members' element={<MembersList />} />}

                {/* ADMIN ROUTES */}
                {entityType === "admin" && <Route path='/admin/create-club' element={<CreateClubComponent />} />}
                {entityType === "admin" && <Route path='/admin/verify/students' element={<StudentListComponent />} />}
                {entityType === "admin" && <Route path='/admin/clubs' element={<ClubListComponent />} />}
                {entityType === "admin" && <Route path='/admin/club/:cid/treasury/:tid' element={<ClubTreasuryHistory />} />}
                {entityType === "admin" && <Route path='/admin/treasury' element={<AdminTreasury />} />}
            </Routes>
        </>
    )
}

export default AppRouter