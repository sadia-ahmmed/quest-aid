import React, { useState } from 'react'
import { clearStates, onInputText } from '../../middleware/functions/utils'
import resourceUrl from '../../config/Config'
import axios from 'axios'
import { headerBuilder } from '../../config/HeaderBuilder'

function CreateClubComponent() {

    const entityId = localStorage.getItem("entityId")
    const token = localStorage.getItem("token")

    const [clubName, setClubName] = useState("")
    const [clubLogo, setClubLogo] = useState<File | null>(null)
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [phone, setPhone] = useState("")
    const [isLoading, setIsLoading] = useState(false)

    const onFileUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files && event.target.files[0]) {
            setClubLogo(event.target.files[0])
        }
    }


    const onCreateClub = (event: any) => {
        event.preventDefault()

        setIsLoading(true)

        const body = JSON.stringify({
            clubName, email, password, phone
        })

        console.table(body)
        console.log(entityId)

        const formData = new FormData()
        formData.append("club", body);
        formData.append("file", clubLogo!)

        const url = `${resourceUrl}/admin/add/club/${entityId}`
        const header = headerBuilder.addAuthorization(token).build()

        axios.post(url, formData, header).then((response) => {
            if (response.status === 201) {
                alert("Club successfully created")
            }
            setIsLoading(false)
            clearStates([setEmail, setPassword, setClubName, setPhone])
        }).catch((err) => {
            alert(err)
            setIsLoading(false)
        })

    }



    return (
        <form className="space-y-6" onSubmit={onCreateClub} >
            <div className="flex justify-center items-center h-screen">
                <div className="w-1/2 bg-white flex flex-col justify-center px-6 py-12 lg:px-8" >
                    <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm"  >
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -300 }}>
                                Club name
                            </label>
                            <div className="mt-2">
                                <input
                                    type="text"
                                    placeholder="Enter club name"
                                    value={clubName}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    onChange={(event) => onInputText(event, setClubName)}
                                />
                            </div>
                        </div>
                        <br />
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -285 }}>
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
                        <br />
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -310 }}>
                                Club logo
                            </label>
                            <div className="mt-2">
                                <input
                                    type="file"
                                    placeholder="Enter club logo"
                                    className="appearance-none border border-gray-200 rounded w-full py-2 px-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                                    onChange={(event) => onFileUpload(event)}
                                />
                            </div>
                        </div>
                        <br />
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -260 }}>
                                Set club password
                            </label>
                            <div className="mt-2">
                                <input
                                    type="password"
                                    placeholder="Enter club password"
                                    value={password}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    onChange={(event) => onInputText(event, setPassword)}
                                />
                            </div>
                        </div>
                        <br />
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900" style={{ marginLeft: -220 }}>
                                Set Club phone number
                            </label>
                            <div className="mt-2">
                                <input
                                    type="text"
                                    placeholder="Enter club phone number"
                                    value={phone}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    onChange={(event) => onInputText(event, setPhone)}
                                />
                            </div>
                        </div>
                        <br />
                        <div>
                            <button type="submit"
                                className="flex w-full justify-center rounded-md bg-teal-800 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600" disabled={isLoading}>
                                {isLoading ? "Creating club....." : "Create club"}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    )
}

export default CreateClubComponent