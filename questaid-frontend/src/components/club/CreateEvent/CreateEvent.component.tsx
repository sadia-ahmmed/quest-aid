import { Button } from '@mui/material'
import React, { useState } from 'react'
import { aiResourceUrl, resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { onInputText } from '../../../middleware/functions/utils'

const CreateEvent = () => {

    const [eventName, setEventName] = useState("")
    const [eventDescription, setDescription] = useState("")
    const [eventType, setEventType] = useState("")
    const [ongoingSubmit, setOngoignSubmit] = useState(false)
    const [promptText, setPromptText] = useState<string | undefined>("")
    const [isPromptFieldVisible, setPromptFieldVisible] = useState<boolean | undefined>(false)
    const [ongoingPromptFetch, setOngoingPromptFetch] = useState<boolean | undefined>(false)

    const entityId = localStorage.getItem("entityId")

    const togglePromptField = (event: any) => {
        event.preventDefault()
        setPromptFieldVisible(!isPromptFieldVisible)
    }

    const getContentCompletion = () => {
        setOngoingPromptFetch(true)

        const url = `${aiResourceUrl}/make/announcement`

        let context = promptText;
        if (eventDescription) {
            context = `I am writing an event description. The content till now is: ${eventDescription}. Complete the following content based on the prompt: ${promptText}`
        }

        const body = {
            prompt: context
        }

        axios.post(url, body)
            .then((response) => {
                setDescription("")
                setDescription(response.data.ai_response)
                setOngoingPromptFetch(false)

            })
            .catch((err) => {
                alert(err)
                setOngoingPromptFetch(false)
            })
    }

    const onEventCreate = () => {
        setOngoignSubmit(true)

        const url = `${resourceUrl}/event/create/${entityId}/club`
        const body = {
            eventName, eventDescription, eventType
        }

        console.table(body)

        axios.post(url, body)
            .then((response) => {
                alert("Created!")
                setEventName("")
                setDescription("")
                setEventType("")
            })
            .catch((err) => {
                console.log(err)
                alert(err)
            })
        setOngoignSubmit(false)
    }


    return (
        <div>
            <div style={{ display: "flex" }}>
                <h2 className="mt-5 text-left text-3xl font-bold tracking-tight text-teal-800" style={{ marginLeft: 300 }}>
                    Create Event
                </h2>
            </div>
            <br />
            <div className='form' style={{ width: 1000 }}>
                <div style={{ marginLeft: 300 }}>
                    <div className="items-center text-left justify-between">
                        <label htmlFor="title" className="block text-md font-medium leading-6 text-gray-900" style={{ marginRight: 10 }}>
                            Name
                        </label>
                        <input
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            type="text"
                            placeholder="Enter event name"
                            value={eventName}
                            onChange={(event: any) => onInputText(event, setEventName)}
                        />
                    </div>
                </div>
                <br />
                <div style={{ marginLeft: 300 }}>
                    <div className="flex items-center text-left justify-between">
                        <label htmlFor="title" className="block text-md font-medium leading-6 text-gray-900" style={{ marginRight: 10 }}>
                            Description
                        </label>
                        <div className="text-sm">
                            <a href="" onClick={togglePromptField} className="font-semibold text-teal-600 hover:text-teal-500">
                                AI assistant
                            </a>
                        </div>

                    </div>
                    {isPromptFieldVisible &&
                        <div className="mt-2 flex">
                            <input
                                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                placeholder='Enter prompt'
                                value={promptText}
                                onChange={(event) => onInputText(event, setPromptText)}
                            />
                            <button
                                className="w-1/4 justify-center rounded-md bg-teal-800 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600"
                                onClick={getContentCompletion}
                                disabled={ongoingPromptFetch}
                            >
                                {ongoingPromptFetch ? "Generating..." : "Submit"}
                            </button>
                        </div>
                    }
                    <div>
                        <textarea
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            placeholder="Enter event description"
                            rows={10}
                            value={eventDescription}
                        >
                        </textarea>
                    </div>
                </div>
                <br />
                <div style={{ marginLeft: 300 }}>
                    <div className="items-center text-left justify-between">
                        <label htmlFor="title" className="block text-md font-medium leading-6 text-gray-900" style={{ marginRight: 10 }}>
                            Type
                        </label>
                        <input
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            type="text"
                            placeholder="Workshop/Seminar/etc"
                            value={eventType}
                            onChange={(event: any) => onInputText(event, setEventType)}
                        />
                    </div>
                </div>
                <br />
                <button
                    className="w-2/3 rounded-md bg-teal-800 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600"
                    disabled={ongoingSubmit}
                    onClick={onEventCreate}
                    style={{ marginLeft: 300, justifyContent: "left" }}
                >
                    {ongoingSubmit ? "Creating event...." : "Create Event"}
                </button>
            </div>
        </div>
    )
}

export default CreateEvent