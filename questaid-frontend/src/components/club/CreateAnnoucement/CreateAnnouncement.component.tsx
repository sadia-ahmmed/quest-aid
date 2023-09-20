import React, { useState } from 'react'
import { clearStates, onInputText } from '../../../middleware/functions/utils'
import { aiResourceUrl, resourceUrl } from '../../../config/Config'
import axios from 'axios'
import Dropdown from '../../general/Dropdown/Dropdown.component'
import { headerBuilder } from '../../../config/HeaderBuilder'

export const CreateAnnouncement = () => {

    const privacyOptions = ["private", "public"]
    const entityId = localStorage.getItem("entityId")
    const token = localStorage.getItem("token")


    const [title, setTitle] = useState<string | undefined>("")
    const [content, setContent] = useState<string | undefined>("")
    const [privacy, setPrivacy] = useState<string | undefined>(privacyOptions[0])
    const [promptText, setPromptText] = useState<string | undefined>("")
    const [isPromptFieldVisible, setPromptFieldVisible] = useState<boolean | undefined>(false)
    const [ongoingPromptFetch, setOngoingPromptFetch] = useState<boolean | undefined>(false)
    const [ongoingSubmit, setOngoingSubmit] = useState<boolean | undefined>(false)


    const togglePromptField = (event: any) => {
        event.preventDefault()
        setPromptFieldVisible(!isPromptFieldVisible)
    }


    const onMakeAnnouncement = (event: any) => {
        event.preventDefault()
        setOngoingSubmit(true)

        const datePublished = new Date().toISOString()

        const body = {
            title, content, privacy, datePublished
        }

        const url = `${resourceUrl}/announcement/add/by/${entityId}`
        const headers = headerBuilder.addAuthorization(token).build()

        axios.post(url, body, { headers: headers })
            .then((response) => {
                if (response.status === 201) {
                    alert("Announcement created")
                    setOngoingSubmit(false)
                    setTitle("")
                    setContent("")
                    setPromptText("")
                }
            })
            .catch((err) => {
                alert(err)
                setOngoingSubmit(false)
            })

    }



    const getContentCompletion = () => {
        setOngoingPromptFetch(true)

        const url = `${aiResourceUrl}/make/announcement`

        let context = promptText;
        if (content) {
            context = `I am writing a post. The content till now is: ${content}. Complete the following content based on the prompt: ${promptText}`
        }

        const body = {
            prompt: context
        }

        axios.post(url, body)
            .then((response) => {
                setContent("")
                setContent(response.data.ai_response)
                setOngoingPromptFetch(false)

            })
            .catch((err) => {
                alert(err)
                setOngoingPromptFetch(false)
            })
    }



    return (
        <div className="flex justify-center items-center h-screen">
            <div className="w-full bg-white flex flex-col justify-center px-3 py-12 lg:px-8" >
                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm"  >
                    <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-teal-800">
                        Create an announcement
                    </h2>
                    <div>
                        <div className="flex items-center justify-between">
                            <label htmlFor="title" className="block text-sm font-medium leading-6 text-gray-900">
                                Title
                            </label>
                        </div>
                        <div className="mt-2">
                            <input
                                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                type="text"
                                placeholder="Enter announcement title"
                                value={title}
                                onChange={(event) => onInputText(event, setTitle)}
                            />
                        </div>
                    </div>
                    <br />
                    <div>
                        <div className="flex items-center justify-between">
                            <label htmlFor="content" className="block text-sm font-medium leading-6 text-gray-900">
                                Enter content
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
                        <div className="mt-2">
                            <textarea
                                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                placeholder='Enter content'
                                rows={8}
                                value={content}
                                onChange={(event) => onInputText(event, setContent)}
                            >
                            </textarea>
                        </div>
                        <br />
                        <div className="flex justify-between">
                            <Dropdown
                                value={privacy!}
                                label={"Privacy"}
                                handleChange={(e: any) => setPrivacy(e.target.value)}
                                items={privacyOptions}
                                values={privacyOptions}
                            />
                            <button
                                className="w-1/2 justify-center rounded-md bg-teal-800 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600"
                                disabled={ongoingSubmit}
                                onClick={onMakeAnnouncement}
                            >
                                {ongoingSubmit ? "Creating announcement...." : "Make Announcement"}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
