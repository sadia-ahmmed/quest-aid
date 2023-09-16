import React from 'react'
import { useAuthContext } from '../context/AuthContext'

function EntityNameViewerFactory() {

    const entityType = localStorage.getItem("entityType")
    const { userCache } = useAuthContext()

    return (
        <p>
            {entityType === "student" && `${userCache.firstName} ${userCache.lastName}`}
            {entityType === "club" && `${userCache.clubName}`}
            {entityType === "organization" && `${userCache.organizationName}`}
        </p>
    )
}

export default EntityNameViewerFactory