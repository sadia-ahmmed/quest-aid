import "./CreateDepartment.styles.css"
import React, { useEffect, useState } from 'react'
import FeatureHeadingTitle from '../../general/FeatureHeadingTitle/FeatureHeadingTitle.component'
import InputBuilderFactory from '../../../middleware/factories/InputBuilderFactory'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import Dropdown from '../../general/Dropdown/Dropdown.component'
import { onInputText } from '../../../middleware/functions/utils'
import CustomButton from "../../general/CustomButton/CustomButton.component"

const CreateDepartment = () => {

    const [departmentName, setDepartmentName] = useState("")
    const [clubMembers, setClubMembers] = useState([])
    const [deptHeadName, setDeptHeadName] = useState("Select member")
    const [isLoading, setIsLoading] = useState(true)
    const [ongoingSubmit, setOngoingSubmit] = useState(false)

    const clubId = localStorage.getItem("entityId")

    useEffect(() => {
        const url = `${resourceUrl}/member/get/members/details/${clubId}/club`
        axios.get(url)
            .then((response) => {
                setClubMembers(response.data)
                setIsLoading(false)
            })
            .catch((err) => {
                alert(err)
            })

    }, [])


    const onCreateDepartmentButtonClick = () => {
        setOngoingSubmit(true)

        let url = `${resourceUrl}/department/create/${clubId}/club`
        const body = {
            departmentName
        }

        axios.post(url, body)
            .then((response) => {

            })
            .catch((err) => {
                alert(err)
                setOngoingSubmit(false)
            })


        url = `${resourceUrl}/department/promote/${deptHeadName}/member`
        const anotherBody = {
            content: "DEPARTMENT_HEAD"
        }
        axios.put(url, anotherBody)
            .then((response) => {
                if (response.status === 201) {
                    alert("Created department")
                    setDepartmentName("")
                    setOngoingSubmit(false)
                }
            })
            .catch((err) => {
                alert(err)
                setOngoingSubmit(false)
            })


    }




    if (isLoading) {
        return <div>Loading.....</div>
    }


    return (
        <div className='w-9/12'>
            <div className='flex ml-80'>
                <FeatureHeadingTitle title='Create Department' isCenter={false} />
            </div>
            <div className='flex ml-80 mt-5'>
                <InputBuilderFactory title='Department Name' placeholder='Enter department name' isTitleCenter={false} inputType='text' inputValue={departmentName} setInputValue={setDepartmentName} isTextAreaInput={false} />
            </div>
            <div className='flex ml-80 mt-10 dropdown-width'>
                {
                    clubMembers.length === 0 ?
                        <p >No members to choose from</p>
                        :
                        <Dropdown
                            items={clubMembers.map((member: any) => { return member.id })}
                            label='Set department head'
                            value={deptHeadName}
                            values={clubMembers.map((member: any) => { return member.name })}
                            handleChange={(event: any) => setDeptHeadName(event.target.value)}
                        />
                }
            </div>
            <div className="flex ml-80 mt-10">
                <CustomButton
                    buttonText="Create Department"
                    loadingButtonText="Creating Department"
                    buttonLength="w-1/5"
                    ongoingSubmit={ongoingSubmit}
                    onButtonClickAction={onCreateDepartmentButtonClick}
                />
            </div>
        </div>
    )
}

export default CreateDepartment