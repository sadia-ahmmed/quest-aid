import { Box, Button, Divider, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from '@mui/material'
import React, { useEffect } from 'react'
import { useAuthContext } from '../../context/AuthContext'
import axios from 'axios'
import resourceUrl from '../../config/Config'
import { HeaderBuilder } from '../../config/HeaderBuilder'
import { useNavigate } from "react-router-dom";
import { Inbox, Mail } from '@mui/icons-material'
import EntityNameViewerFactory from '../../components/EntityNameViewerFactory'
import Sidebar from '../../components/Sidebar'
function Dashboard() {

    const navigate = useNavigate()

    const { isLoggedIn, setLoggedIn, userCache, setUserCache, jwtToken, setJwtToken } = useAuthContext()
    const headerBuilder = new HeaderBuilder()

    const entityEmail = localStorage.getItem("entityIdentifier")
    const token = localStorage.getItem("token")
    const entityType = localStorage.getItem("entityType")
    const entityId = localStorage.getItem("entityId")


    useEffect(() => {

        const httpEntityPolling = async () => {

            const headers = headerBuilder.addAuthorization(token).build()

            const url = `${resourceUrl}/${entityType}/by/${entityEmail}/email`

            axios.get(url, { headers: headers })
                .then((response) => {
                    setUserCache(response.data)
                    localStorage.setItem("entityId", response.data.id)
                })
                .catch((error) => {
                    alert(error.response.data.key)
                })
        }

        httpEntityPolling()
    }, [])


    const onLogoutButtonClick = () => {
        localStorage.clear()
        setUserCache({})
        setJwtToken("")
        setLoggedIn(false)
        navigate("/login")
    }

    return (
        <div>
            <>
            <Sidebar/> 
            </>
            <br />
            <EntityNameViewerFactory />
            <br />
            <Button color='error' variant='contained' onClick={onLogoutButtonClick}>
                Logout
            </Button>
        </div>
    )
}

export default Dashboard