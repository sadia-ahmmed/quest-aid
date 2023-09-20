import { Box, Paper, Typography, Card, Avatar, CardContent } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { headerBuilder } from '../../../config/HeaderBuilder'
import ImageDisplay from '../ImageDisplay/ImageDisplay.component'

interface IClubCardProps {
    clubId: Number
    clubName: string,
    clubEvents: Number,
    clubAvatarPath: string,
    treasuryId: Number
}


const ClubCard: React.FC<IClubCardProps> = ({ clubId, clubName, clubEvents, clubAvatarPath, treasuryId }) => {

    const [img, setImg] = useState("")

    useEffect(() => {

        if (!clubAvatarPath) {
            return
        }

        const url = `${resourceUrl}/files/load/logo/${clubAvatarPath}`

        axios.get(url)
            .then((response) => {
                if (response.data) {
                    setImg(response.data)
                }
            })

    }, [])


    return (
        <Card className="w-72 shadow-md rounded-lg overflow-hidden flex items-center" sx={{ padding: 2, margin: 1, width: 500 }}>
            <Avatar sx={{ width: 90, height: 90 }}>
                <img
                    src={`data:image/jpeg;base64,${img}`}
                    alt="Person"
                />
            </Avatar>
            {/* <ImageDisplay imageData={img} /> */}
            <CardContent>
                <Typography variant="h6" component="div" className="mt-12 text-3xl font-bold tracking-tight text-teal-800 flex">
                    {clubName}
                </Typography>
                <Typography variant="body2" className="mt-12 text-2xl font-bold tracking-tight text-teal-800 flex">
                    {"Events: " + clubEvents}
                </Typography>
                <Typography variant="body2" className="mt-12 text-1xl font-bold tracking-tight text-teal-800 flex underline">
                    <a href={`/admin/club/${clubId}/treasury/${treasuryId}`}>
                        See treasury
                    </a>
                </Typography>
                <Typography variant="body2" className="mt-12 text-1xl font-bold tracking-tight text-teal-800 flex underline">
                    <a href={`/admin/club/stats/${clubId}`}>
                        See stats
                    </a>
                </Typography>
            </CardContent>
        </Card>
    )
}

export default ClubCard