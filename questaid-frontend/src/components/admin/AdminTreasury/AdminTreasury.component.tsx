import React, { useEffect, useState } from 'react'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { Box, Card, CardContent, Grid, Stack, Typography } from '@mui/material'

export const AdminTreasury = () => {

    const entityIdentifier = localStorage.getItem("entityIdentifier")
    const entityId = localStorage.getItem("entityId")

    const [admin, setAdmin] = useState<any>()
    const [requests, setRequests] = useState([])


    useEffect(() => {
        let url = `${resourceUrl}/funds/get/all/${entityId}/admin`
        axios.get(url)
            .then((response) => {
                setRequests(response.data)
            })
            .catch((err) => {
                alert(err)
            })

        url = `${resourceUrl}/admin/by/${entityIdentifier}/email`
        axios.get(url)
            .then((response) => {
                setAdmin(response.data)
            })
            .catch((err) => {
                alert(err)
            })



    }, [])

    if (!admin) {
        return <div>Loading....</div>
    }


    return (
        <Stack spacing={4}>
            <Grid container spacing={2} sx={{ alignItems: "center", justifyContent: "center" }}>
                <Grid item xs={4} >
                    <Card>
                        <CardContent>
                            <Typography
                                classes={{ root: "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-teal-800" }}
                            >
                                Sponsorship Budget
                            </Typography>
                        </CardContent>
                        <CardContent>
                            <Typography
                                classes={{ root: "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-teal-800" }}
                            >
                                {admin.currentBudget} BDT
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={4}>
                    <Card>
                        <CardContent>
                            <Typography>Total spent</Typography>
                        </CardContent>
                        <CardContent>
                            <Typography>{admin.currentBudget} BDT</Typography>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
            <Box>
                <Typography
                    variant='h6'
                    classes={{ root: "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-teal-800" }}
                >
                    Incoming Fund Requests
                </Typography>
            </Box>
            <ul>
                {requests.map((request: any) => {
                    return <li key={request.id}>{ } - {request.requestedFund}</li>
                })}
            </ul>
        </Stack>

    )
}
