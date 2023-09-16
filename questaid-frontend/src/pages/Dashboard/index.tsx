import { Button } from '@mui/material'
import React, { useEffect } from 'react'
import { useAuthContext } from '../../context/AuthContext'
import axios from 'axios'
import resourceUrl from '../../config/Config'
import HeaderBuilder from '../../config/HeaderBuilder'
import { useNavigate } from "react-router-dom";

function Dashboard() {

    const navigate = useNavigate()

    const { isLoggedIn, setLoggedIn, userCache, setUserCache, jwtToken, setJwtToken } = useAuthContext()
    const headerBuilder = new HeaderBuilder()

    useEffect(() => {
        const entityEmail = localStorage.getItem("entityIdentifier")
        const token = localStorage.getItem("token")
        const entityType = localStorage.getItem("entityType")
        const entityId = localStorage.getItem("entityId")

        const initiallyFetchEntity = async () => {

            const headers = headerBuilder.addAuthorization(token).build()

            axios.get(`${resourceUrl}/${entityType}/by/${entityEmail}/email`, { headers: headers })
                .then((response) => {
                    setUserCache(response.data)
                    localStorage.setItem("entityId", response.data.id)
                })
                .catch((error) => {
                    alert(error.response.data.key)
                })
        }

        initiallyFetchEntity()
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
            Dashboard
            <br />
            <p>{userCache.firstName}</p>
            <br />
            <Button color='error' variant='contained' onClick={onLogoutButtonClick}>
                Logout
            </Button>
        </div>
    )
}

export default Dashboard