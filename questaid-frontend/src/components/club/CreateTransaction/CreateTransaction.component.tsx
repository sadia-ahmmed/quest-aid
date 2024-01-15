import React, { useState } from 'react'
import FeatureHeadingTitle from '../../general/FeatureHeadingTitle/FeatureHeadingTitle.component'
import InputBuilderFactory from '../../../middleware/factories/InputBuilderFactory'
import { onInputText } from '../../../middleware/functions/utils'
import Dropdown from '../../general/Dropdown/Dropdown.component'
import { Button } from '@mui/material'
import CustomButton from '../../general/CustomButton/CustomButton.component'

const CreateTransaction = () => {

    const [choiceType, setChoiceType] = useState("Incoming")
    const [payedBy, setPayedBy] = useState("")
    const [amount, setAmount] = useState(0.00)
    const [reference, setReference] = useState("")
    const [payingEntity, setPayingEntity] = useState("STUDENT")
    const [transactionType, setTransactionType] = useState("RECRUITMENT")

    const types = [
        "Incoming",
        "Outgoing",
    ]

    const entities = [
        "STUDENT",
        "ORGANIZATION",
        "CLUB"
    ]

    const transType = [
        "RECRUITMENT",
        "EVENT",
        "EXPENSES"
    ]

    return (
        <div className='ml-80'>
            <div className='flex'>
                <FeatureHeadingTitle title='Add Transaction' isCenter={false} />
            </div>
            <div className='mt-5 w-1/4'>
                <hr />
            </div>
            <div className='flex mt-10'>
                <Dropdown
                    items={types}
                    value={choiceType}
                    label='Incoming/Outgoing'
                    values={types}
                    handleChange={(event: any) => setChoiceType(event.target.value)}
                />
            </div>
            <div className='flex mt-5'>
                <InputBuilderFactory
                    inputType='text'
                    inputValue={payedBy}
                    isTextAreaInput={false}
                    isTitleCenter={false}
                    placeholder='Enter payed by'
                    title='Payed by'
                    setInputValue={setPayedBy}
                />
            </div>
            <div className='flex mt-5'>
                <InputBuilderFactory
                    inputType='number'
                    inputValue={amount}
                    isTextAreaInput={false}
                    isTitleCenter={false}
                    placeholder='Enter amount'
                    title='Amount'
                    setInputValue={setAmount}
                />
            </div>
            <div className='flex mt-5'>
                <InputBuilderFactory
                    inputType='text'
                    inputValue={reference}
                    isTextAreaInput={false}
                    isTitleCenter={false}
                    placeholder='Enter reference'
                    title='Reference'
                    setInputValue={setReference}
                />
            </div>
            <div className='flex mt-10'>
                <Dropdown
                    items={entities}
                    value={payingEntity}
                    label='Type of payer'
                    values={entities}
                    handleChange={(event: any) => setPayingEntity(event.target.value)}
                />
            </div>
            <div className='flex mt-10'>
                <Dropdown
                    items={transType}
                    value={transactionType}
                    label='Transaction type'
                    values={transType}
                    handleChange={(event: any) => setTransactionType(event.target.value)}
                />
            </div>
            <div className='flex mt-5'>
                <CustomButton buttonText='Create Transaction' loadingButtonText='Creating...' buttonLength='w-1/4' ongoingSubmit={false} onButtonClickAction={() => { }} />
            </div>
        </div>
    )
}

export default CreateTransaction