import { Button } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { response } from 'express'

const EventComponent = () => {

    const navigate = useNavigate()

    const entityId = localStorage.getItem("entityId")

    const [events, setEvents] = useState([])

    useEffect(() => {
        const url = `${resourceUrl}/event/view/${entityId}/club`

        axios.get(url)
            .then((response) => {
                setEvents(response.data)
            })
            .catch((err) => {
                alert(err)
            })

    }, [])

    return (
        <div>
            <div style={{ display: "flex", alignContent: "center", alignItems: "center" }}>
                <h2 className="mt-5 text-left text-3xl font-bold leading-9 tracking-tight text-teal-800" style={{ marginLeft: 300 }}>
                    Events
                </h2>
                <Button variant='contained' style={{ margin: 10, marginLeft: 100, marginTop: 33, backgroundColor: 'teal' }} onClick={() => navigate("/club/event/create")}>
                    Add Event
                </Button>
            </div>
            <br />
            <div style={{ display: "flex", marginLeft: 300 }}>
                {
                    events.length === 0 ? "No Events" :
                        events.map((event: any) => {
                            return <a href={`/event/public/${event.id}`} key={event.id}>{event.eventName}</a>
                        })
                }
            </div>
        </div>
    )
}

export default EventComponent