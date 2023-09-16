import { Button, TextField } from '@mui/material'
import React, { MouseEventHandler, useState } from 'react'
import { useAuthContext } from '../../context/AuthContext'
import resourceUrl from '../../config/Config'
import axios from 'axios'
import { useNavigate } from "react-router-dom";
import Dropdown from '../../components/Dropdown'

function LoginPage() {

    const context = useAuthContext()
    const navigate = useNavigate()

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [entityType, setEntityType] = useState("STUDENT")
    const [errorMessage, setErrorMessage] = useState("")
    const [isLoading, setIsLoading] = useState(false)


    const entityChoiceItems = [
        "STUDENT",
        "CLUB",
        "ORGANIZATION"
    ]
    const entityChoiceValues = [
        "STUDENT",
        "CLUB",
        "ORGANIZATION"
    ]


    const onInputText = (event: any, setState: any) => {
        setState(event.target.value)
    }


    const onLoginButtonClick = (event: any) => {
        event.preventDefault()

        setIsLoading(true)

        const body = {
            email, password,
            type: entityType
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
                    setIsLoading(false)
                    navigate("/dashboard")
                }
            })
            .catch(error => {
                setErrorMessage(error.response.data.key)
                setIsLoading(false)
            })
    }


    return (
        <div className="flex min-h-full">
            <div className="w-1/2 bg-cover" style={{
                backgroundImage: `url(https://img.freepik.com/premium-vector/education-distance-education-internet-studying-elearning-remote-learning-flat-vector_1200-847.jpg?w=4000)`,
                backgroundSize: 'cover', height: '90vh', width: '120vh',
            }}>
                {/* Right-hand side image */}
            </div>
            <div className="w-1/2 bg-white flex flex-col justify-center px-6 py-12 lg:px-8" >

                {/* <img

                    className="mx-auto h-30 w-auto"
                    src={""}
                    alt="Your Company"
                /> */}
                <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-teal-800">
                    Sign in to your account
                </h2>
                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm"  >
                    <form className="space-y-6" onSubmit={onLoginButtonClick} >
                        {errorMessage.length > 0 && <p className="error">{errorMessage}</p>}
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -300 }}>
                                Email address
                            </label>
                            <div className="mt-2">
                                <input
                                    type="text"
                                    placeholder="Enter email"
                                    value={email}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    onChange={(event) => onInputText(event, setEmail)}
                                />
                            </div>
                        </div>

                        <div>
                            <div className="flex items-center justify-between">
                                <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                                    Password
                                </label>
                                <div className="text-sm">
                                    <a href="#" className="font-semibold text-teal-600 hover:text-teal-500">
                                        Forgot password?
                                    </a>
                                </div>
                            </div>
                            <div className="mt-2">
                                <input
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    type="password"
                                    placeholder="password"
                                    autoComplete="new-password"
                                    value={password}
                                    onChange={(event) => onInputText(event, setPassword)}
                                />
                            </div>
                        </div>

                        <div>
                            <div className='mt-2'>
                                <Dropdown
                                    value={entityType}
                                    label={"Login as"}
                                    handleChange={(e: any) => setEntityType(e.target.value)}
                                    items={entityChoiceItems}
                                    values={entityChoiceValues}
                                />
                            </div>
                        </div>

                        <div>
                            <button type="submit"
                                className="flex w-full justify-center rounded-md bg-teal-800 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600" disabled={isLoading}>
                                {isLoading ? "Loggin in....." : "Log In"}
                            </button>
                        </div>
                    </form>
                </div>
                <p className="mt-10 text-center text-sm text-gray-500">
                    Not a member?     {'     '}
                    <a href="/signup" className="font-semibold leading-6 text-teal-600 hover:text-teal-500">
                        SignUp
                    </a>
                </p>
            </div>
        </div>
    )
}

export default LoginPage