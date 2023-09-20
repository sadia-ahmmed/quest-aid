import React, { useState } from 'react'
import { useAuthContext } from '../../context/AuthContext'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import resourceUrl from '../../config/Config'
import Dropdown from '../../components/Dropdown'
function SignUp() {

    const context = useAuthContext()
    const navigate = useNavigate()

    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [phone, setPhone] = useState("")
    const [password, setPassword] = useState("")

    const [errorMessage, setErrorMessage] = useState("")
    const [isLoading, setIsLoading] = useState(false)
    const [entityType, setEntityType] = useState("STUDENT")
    
    const entityChoiceItems = [
        "STUDENT",
        "ORGANIZATION"
    ]
    const entityChoiceValues = [
        "STUDENT",
        "ORGANIZATION"
    ]
    
    
    const onInputText = (event: any, setState: any) => {
        setState(event.target.value)
    }


    const onSignupSubmit = (event: any) => {
        event.preventDefault()
        setIsLoading(true)

        const body = {
            name, email, password, phone
        }

        const url = `${resourceUrl}/${entityType.toLowerCase()}/register`
        
        axios.post(url, body).then((response) => {
            if(response.status === 201) {
                alert("Account successfully created")
                navigate("/login")
                setIsLoading(false)
            }
        }).catch((error) => {
            console.log(error)
            alert("Error creating account")
            setIsLoading(false)
        })

    }


    return (
        <div className="flex min-h-full">
            <div className="w-1/2 bg-white flex flex-col justify-center px-6 py-12 lg:px-8" >

                {/* <img

                    className="mx-auto h-30 w-auto"
                    src={""}
                    alt="Your Company"
                /> */}
                <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-teal-800">
                    Create your account
                </h2>
                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm"  >
                    <form className="space-y-6" onSubmit={onSignupSubmit} >
                        {errorMessage.length > 0 && <p className="error">{errorMessage}</p>}
                        <div>
                            <label htmlFor="name" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -340 }}>
                                Name
                            </label>
                            <div className="mt-2">
                                <input
                                    type="text"
                                    placeholder="Enter name"
                                    value={name}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    onChange={(event) => onInputText(event, setName)}
                                />
                            </div>
                        </div>
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
                            <label htmlFor="phone" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -280 }}>
                                Contact Number
                            </label>
                            <div className="mt-2">
                                <input
                                    type="text"
                                    placeholder="Enter contact number"
                                    value={phone}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    onChange={(event) => onInputText(event, setPhone)}
                                />
                            </div>
                        </div>   
                        <div>
                            <div className="flex items-center justify-between">
                                <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                                    Password
                                </label>
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
                                    label={"Signup as"}
                                    handleChange={(e: any) => setEntityType(e.target.value)}
                                    items={entityChoiceItems}
                                    values={entityChoiceValues}
                                />
                            </div>
                        </div>

                        <div>
                            <button type="submit"
                                className="flex w-full justify-center rounded-md bg-teal-800 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600" 
                                disabled={isLoading}>
                                {isLoading ? "Sign Up....." : "Sign Up"}
                            </button>
                        </div>
                    </form>
                </div>
                <p className="mt-10 text-center text-sm text-gray-500">
                    Already have an account?     {'     '}
                    <a href="/login" className="font-semibold leading-6 text-teal-600 hover:text-teal-500">
                        Login
                    </a>
                </p>
            </div>
            <div className="w-1/2 bg-cover" style={{
                backgroundImage: `url(https://img.freepik.com/premium-vector/education-distance-education-internet-studying-elearning-remote-learning-flat-vector_1200-847.jpg?w=4000)`,
                backgroundSize: 'cover', height: '90vh', width: '120vh',
            }}>
                {/* Right-hand side image */}
            </div>
        </div>
    )
}

export default SignUp