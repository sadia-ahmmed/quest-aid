import React from 'react'
import { useAuthContext } from '../../context/AuthContext'
import { Typography } from '@mui/material'

function EntityNameViewerFactory() {

    const entityType = localStorage.getItem("entityType")
    const { userCache } = useAuthContext()

    return (
        <Typography variant='h4'>
            {entityType === "student" && `${userCache.name}`}
            {entityType === "club" && `${userCache.clubName}`}
            {entityType === "organization" && `${userCache.name}`}
            {entityType === "admin" && `${userCache.name}`}
        </Typography>
    )
}

export default EntityNameViewerFactory