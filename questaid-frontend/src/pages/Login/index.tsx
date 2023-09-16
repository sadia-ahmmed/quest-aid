import { Button, TextField } from '@mui/material'
import React, { MouseEventHandler, useState } from 'react'
import { useAuthContext } from '../../context/AuthContext'
import resourceUrl from '../../config/Config'
import axios from 'axios'
import { useNavigate } from "react-router-dom";

function LoginPage() {

    const context = useAuthContext()
    const navigate = useNavigate()

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [errorMessage, setErrorMessage] = useState("")

    const onInputText = (event: any, setState: any) => {
        setState(event.target.value)
    }


    const onLoginButtonClick = (event: any) => {
        event.preventDefault()

        const body = {
            email, password,
            type: "STUDENT"
        }

        axios.post(`${resourceUrl}/authenticate`, body)
            .then((response) => {
                if (response.status !== 200) {
                    console.log("here")
                    setErrorMessage(response.data.key)
                } else {
                    setErrorMessage("")
                    const jwtToken = response.data.Authorization
                    const entityIdentifier = response.data.authName

                    context.setJwtToken(jwtToken)
                    context.setUserCache(entityIdentifier)

                    localStorage.setItem("loggedIn", "true")
                    localStorage.setItem("entityIdentifier", entityIdentifier)
                    localStorage.setItem("token", jwtToken)
                    localStorage.setItem("entityType", body.type.toLowerCase())

                    context.setLoggedIn(true)
                    navigate("/dashboard")
                }
            })
            .catch(error => {
                setErrorMessage(error.response.data.key)
            })
    }


    return (
        <div>
            <form>
                <TextField type='email' placeholder='Enter email' onChange={(event) => onInputText(event, setEmail)} />
                <TextField type='password' placeholder='Enter password' onChange={(event) => onInputText(event, setPassword)} />
                <Button type='submit' title='Login' onClick={onLoginButtonClick}>
                    login
                </Button>
                <br />
                <p>{errorMessage}</p>
            </form>
        </div>
    )
}

export default LoginPage