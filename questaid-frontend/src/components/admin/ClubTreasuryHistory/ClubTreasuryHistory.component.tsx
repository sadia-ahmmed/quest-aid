import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'

const AdminClubTreasury = () => {

    const { tid, cid } = useParams()

    const [incomingTransactions, setIncomingTransactions] = useState([])
    const [outgoingTransactions, setOutgoingTransactions] = useState([])
    const [balance, setBalance] = useState<any>()
    const [club, setClub] = useState<any>()

    useEffect(() => {

        const getIncomingTransactions = () => {
            const url = `${resourceUrl}/treasury/get/all/${tid}/incoming`
            axios.get(url)
                .then((response) => {
                    setIncomingTransactions(response.data)
                })
                .catch((err) => {
                    alert(err)
                })
        }


        const getOutgoingTransactions = () => {
            const url = `${resourceUrl}/treasury/get/all/${tid}/outgoing`
            axios.get(url)
                .then((response) => {
                    setOutgoingTransactions(response.data)
                })
                .catch((err) => {
                    alert(err)
                })
        }

        const getBalance = () => {
            const url = `${resourceUrl}/treasury/get/${tid}/balance`
            axios.get(url)
                .then((response) => {
                    setBalance(response.data)
                })
                .catch((err) => {
                    alert(err)
                })
        }

        const getClubById = () => {
            const url = `${resourceUrl}/club/public/${cid}`
            axios.get(url)
                .then((response) => {
                    setClub(response.data)
                })
                .catch((err) => {
                    alert(err)
                })
        }


        getIncomingTransactions()
        getOutgoingTransactions()
        getBalance()
        getClubById()
    }, [])


    if (!balance && incomingTransactions.length === 0) {
        return <div>Loading.....</div>
    }

    return (
        <div>
            <div>
                {club.clubName}
            </div>
            <div>
                Balance: {balance.balance}
            </div>
            <ul>
                {incomingTransactions.map((transaction: any) => {
                    return <li key={transaction.id}>{ }</li>
                })}
            </ul>
        </div>
    )
}

export default AdminClubTreasury