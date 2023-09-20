import React, { useEffect, useState } from 'react'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { headerBuilder } from '../../../config/HeaderBuilder'
import ClubCard from '../../general/ClubCard/ClubCard.component'
import { Typography } from '@mui/material'

const ClubListComponent = () => {

    const entityId = localStorage.getItem("entityId")
    const token = localStorage.getItem("token")

    const [clubs, setClubs] = useState([])
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {

        const url = `${resourceUrl}/club/view-under/${entityId}/admin`
        const headers = headerBuilder.addAuthorization(token).build()

        // console.log(url)

        const fetchAllClubsUnderMeAdmin = () => {
            axios.get(url, { headers: headers })
                .then((response) => {
                    setClubs(response.data)
                    setIsLoading(false)
                })
                .catch((err) => {
                    alert(err)
                })
        }

        fetchAllClubsUnderMeAdmin()
    }, [])

    if (isLoading) {
        return <div>Loading...</div>
    }

    return (
        <div>
            <h2 className="mt-12 text-3xl font-bold tracking-tight text-teal-800 flex">
                Clubs
            </h2>
            {clubs.map((club: any) => (
                <ClubCard key={club.id} clubId={club.id} clubName={club.clubName} clubEvents={club.events.length} clubAvatarPath={club.avatarPath} treasuryId={club.treasury.id} />
            ))}
        </div>
    )
}

export default ClubListComponent