import { Button } from '@mui/material';
import React from 'react'
import { useNavigate } from "react-router-dom";

function NotFound() {

    const navigate = useNavigate();


    return (
        <>
            <div>Erro 404. Page not found ;-;</div>
            <Button onClick={() => navigate(-1)}>
                Go back
            </Button>
        </>
    )
}

export default NotFound