import React, { useState } from 'react'
import FeatureHeadingTitle from '../../general/FeatureHeadingTitle/FeatureHeadingTitle.component'
import useEnhancedEffect from '@mui/material/utils/useEnhancedEffect'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { Button } from '@mui/material'
import CustomButton from '../../general/CustomButton/CustomButton.component'

const MembersList = () => {

    const clubId = localStorage.getItem("entityId")

    const [members, setMembers] = useState([])
    const [isLoading, setIsLoading] = useState(true)


    useEnhancedEffect(() => {
        const url = `${resourceUrl}/member/get/members/details/${clubId}/club`
        axios.get(url)
            .then((response) => {
                setMembers(response.data)
                console.log(response.data)
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
                <FeatureHeadingTitle title='Members' isCenter={false} />
                <Button variant='contained' sx={{ backgroundColor: 'teal' }} style={{ margin: 10, marginLeft: 100, marginTop: 33 }} onClick={() => { }}>
                    Recruit members
                </Button>
            </div>
            <div className='w-1/4 mt-5'>
                <hr />
            </div>
            <div className='flex mt-10'>
                {
                    members.length === 0 ? "No members" :
                        <div>
                            {
                                members.map((member: any) => {
                                    return <p key={member.email}>{member.name} | {member.email} | {member.clubMemberRoles} | {member.departmentName} </p>
                                })
                            }
                        </div>
                }
            </div>
        </div>
    )
}

export default MembersList