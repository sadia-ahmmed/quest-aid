import { Box, Button, Divider, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { useAuthContext } from '../../context/AuthContext'
import axios from 'axios'
import { resourceUrl } from '../../config/Config'
import { HeaderBuilder } from '../../config/HeaderBuilder'
import { useNavigate } from "react-router-dom";
import { Inbox, Mail } from '@mui/icons-material'
import Sidebar from '../../components/Sidebar/index'
import ImageDisplay from '../../components/general/ImageDisplay/ImageDisplay.component'
import EntityNameViewerFactory from '../../middleware/factories/EntityNameViewerFactory'


function Dashboard() {

    const navigate = useNavigate()

    const { isLoggedIn, setLoggedIn, userCache, setUserCache, jwtToken, setJwtToken } = useAuthContext()
    const headerBuilder = new HeaderBuilder()

    const entityEmail = localStorage.getItem("entityIdentifier")
    const token = localStorage.getItem("token")
    const entityType = localStorage.getItem("entityType")
    const entityId = localStorage.getItem("entityId")

    const [image, setImage] = useState<any | null>(null)


    useEffect(() => {

        const headers = headerBuilder.addAuthorization(token).build()

        const loadLogo = (filename: string) => {
            const url = `${resourceUrl}/files/load/logo/${filename}`

            axios.get(url)
                .then((response) => {
                    setImage(response.data)
                })
                .catch((err) => {
                    alert("Error loading image")
                })
        }


        const httpEntityPolling = () => {
            const url = `${resourceUrl}/${entityType}/by/${entityEmail}/email`

            axios.get(url)
                .then((response) => {
                    setUserCache(response.data)
                    localStorage.setItem("entityId", response.data.id)
                    if (response.data.avatarPath) {
                        loadLogo(response.data.avatarPath)
                    }
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
        <div className='ml-80'>
            <div className='flex align-center items-center mt-10'>
                {image ? <ImageDisplay width={150} height={150} imageData={image} /> : <p>No image</p>}
                <div style={{ marginLeft: 30 }}>
                    {userCache && <EntityNameViewerFactory />}
                </div>
            </div>
            <Button color='error' variant='contained' onClick={onLogoutButtonClick}>
                Logout
            </Button>
        </div>
    )
}

export default Dashboard