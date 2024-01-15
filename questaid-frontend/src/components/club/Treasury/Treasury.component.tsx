import React, { useEffect, useState } from 'react'
import FeatureHeadingTitle from '../../general/FeatureHeadingTitle/FeatureHeadingTitle.component'
import { Button } from '@mui/material'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const Treasury = () => {

    const clubId = localStorage.getItem("entityId")
    const navigate = useNavigate()

    const [incomingTransactions, setIncomingTransactions] = useState([])
    const [outgoingTransactions, setOutgoingTransactions] = useState([])

    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        let url = `${resourceUrl}/treasury/get/all/${clubId}/club/incoming`
        axios.get(url)
            .then((response) => {
                setIncomingTransactions(response.data)
            })
            .catch((err) => {
                alert(err)
            })

        url = `${resourceUrl}/treasury/get/all/${clubId}/club/outgoing`
        axios.get(url)
            .then((response) => {
                setOutgoingTransactions(response.data)
            })
            .catch((err) => {
                alert(err)
            })

        setIsLoading(false)

    }, [])

    if (isLoading) {
        return <div className='ml-80'>Loading....</div>
    }

    return (
        <div className='ml-80'>
            <div className='flex items-center'>
                <FeatureHeadingTitle title='Treasury' isCenter={false} />
                <Button
                    variant='contained'
                    style={{ margin: 10, marginLeft: 100, marginTop: 33, backgroundColor: 'teal' }}
                    onClick={() => navigate("/club/treasury/add")}
                >
                    Add Transaction
                </Button>
            </div>
            <div className='w-1/4 mt-5'>
                <hr />
            </div>
            <div className='columns-2 mt-10'>
                <div>
                    {
                        incomingTransactions.length === 0 ? "No incoming transactions" :
                            incomingTransactions.map((transaction: any) => {
                                return <li key={transaction.id}>{transaction.amount} BDT payed by {transaction.payedBy}</li>
                            })
                    }
                </div>
                <div>
                    {
                        outgoingTransactions.length === 0 ? "No outgoing transactions" :
                            outgoingTransactions.map((transaction: any) => {
                                return <li key={transaction.id}>{transaction.amount} BDT payed by {transaction.payedBy}</li>
                            })
                    }
                </div>
            </div>
        </div>
    )
}

export default Treasury