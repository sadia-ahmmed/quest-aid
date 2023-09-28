import React, { useState } from 'react'
import { onInputText } from '../../../middleware/functions/utils'
import { aiResourceUrl, resourceUrl } from '../../../config/Config'
import axios from 'axios'
import Dropdown from '../../general/Dropdown/Dropdown.component'
import { headerBuilder } from '../../../config/HeaderBuilder'
import FeatureHeadingTitle from '../../general/FeatureHeadingTitle/FeatureHeadingTitle.component'
import InputBuilderFactory from '../../../middleware/factories/InputBuilderFactory'
import CustomButton from '../../general/CustomButton/CustomButton.component'

export const CreateAnnouncement = () => {

    const privacyOptions = ["private", "public"]
    const entityId = localStorage.getItem("entityId")
    const token = localStorage.getItem("token")


    const [title, setTitle] = useState<string | undefined>("")
    const [content, setContent] = useState<string | undefined>("")
    const [privacy, setPrivacy] = useState<string | undefined>(privacyOptions[0])
    const [promptText, setPromptText] = useState<string | undefined>("")
    const [isPromptFieldVisible, setPromptFieldVisible] = useState<boolean | undefined>(false)
    const [ongoingPromptFetch, setOngoingPromptFetch] = useState(false)
    const [ongoingSubmit, setOngoingSubmit] = useState(false)


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
        <div className="w-1/2"  >
            <div className='flex ml-80'>
                <FeatureHeadingTitle title='Create Announcement' isCenter={false} />
            </div>
            <div className='flex ml-80'>
                <InputBuilderFactory title='Title' placeholder='Enter title' isTitleCenter={false} inputType='text' inputValue={title} setInputValue={setTitle} isTextAreaInput={false} />
            </div>
            <br />
            <div className='ml-80'>
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
                        <CustomButton buttonText='Submit' loadingButtonText='Generating' buttonLength='w-1/4' ongoingSubmit={ongoingPromptFetch} onButtonClickAction={getContentCompletion} />
                    </div>
                }
                <div className="mt-2">
                    <InputBuilderFactory isTitleCenter={false} title='' placeholder='Enter content' inputType='text' inputValue={content} isTextAreaInput={true} setInputValue={setContent} />
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
                    <CustomButton buttonText='Create Announcment' loadingButtonText='Creating Announcement' ongoingSubmit={ongoingSubmit} onButtonClickAction={onMakeAnnouncement} buttonLength='w-1/2' />
                </div>
            </div>
        </div>
    )
}
