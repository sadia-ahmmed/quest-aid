import React from 'react'
import { useAuthContext } from '../context/AuthContext'

function EntityNameViewerFactory() {

    const entityType = localStorage.getItem("entityType")
    const { userCache } = useAuthContext()

    return (
        <p>
            {entityType === "student" && `${userCache.name}`}
            {entityType === "club" && `${userCache.clubName}`}
            {entityType === "organization" && `${userCache.name}`}
            {entityType === "admin" && `${userCache.name}`}
        </p>
    )
}

export default EntityNameViewerFactory