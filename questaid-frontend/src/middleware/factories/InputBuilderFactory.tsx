import React from 'react'
import { onInputText } from '../functions/utils'

interface IInputBuilderFactoryProps {
    title: string,
    isTitleCenter: boolean,
    placeholder: string,
    inputValue: any,
    setInputValue: any,
    inputType: string,
    isTextAreaInput: boolean
}


const InputBuilderFactory: React.FC<IInputBuilderFactoryProps> = ({ title, isTitleCenter, placeholder, inputValue, setInputValue, inputType, isTextAreaInput }) => {
    return (
        <div>
            <div className={`flex ${isTitleCenter ? "items-center" : ""} justify-between`}>
                <label htmlFor="title" className="block text-sm font-medium leading-6 text-gray-900">
                    {title}
                </label>
            </div>
            <div className="mt-2">
                {
                    isTextAreaInput ?
                        <textarea
                            className="flex w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            placeholder={placeholder}
                            rows={8}
                            value={inputValue}
                            onChange={(event) => onInputText(event, setInputValue)}
                        >
                        </textarea>
                        :
                        <input
                            className="flex w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            type={inputType}
                            placeholder={placeholder}
                            value={inputValue}
                            onChange={(event) => onInputText(event, setInputValue)}
                        />
                }
            </div>
        </div>
    )
}

export default InputBuilderFactory