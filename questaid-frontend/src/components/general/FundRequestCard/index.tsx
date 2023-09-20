import { Button, Card, CardContent, Typography } from '@mui/material'
import { margin } from '@mui/system'
import React from 'react'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'


interface IFundRequestProps {
    requestAmount: Number,
    requesterId: Number,
    requesterName: string
}


const FundRequestCard: React.FC<IFundRequestProps> = ({ requestAmount, requesterId, requesterName }) => {

    const onAccept = () => {
        const url = `${resourceUrl}/funds/change/${requesterId}/true`
        axios.post(url)
            .then((response) => {
                alert("Aproved")
                window.location.reload()
            })
            .catch((err) => {
                alert(err)
            })
    }

    return (
        <Card sx={{ display: "flex", marginTop: 2, alignContent: "center" }} elevation={3}>
            <CardContent>
                <Typography>{requesterName}</Typography>
            </CardContent>
            <CardContent>
                <Typography>{requestAmount.toString()} BDT</Typography>
            </CardContent>
            <Button variant='contained' color='success' sx={{ margin: 1 }} onClick={onAccept}>
                Approve
            </Button>
            <Button variant='contained' color='error' sx={{ margin: 1 }} >
                Reject
            </Button>
        </Card >
    )
}

export default FundRequestCard