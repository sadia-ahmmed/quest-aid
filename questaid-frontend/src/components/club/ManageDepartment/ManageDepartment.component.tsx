import "./ManageDepartment.styles.css"
import React, { useState, useEffect } from 'react'
import { resourceUrl } from '../../../config/Config'
import axios from 'axios'
import { Button } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import FeatureHeadingTitle from '../../general/FeatureHeadingTitle/FeatureHeadingTitle.component'

const ManageDepartment = () => {

    const [departments, setDepartments] = useState([])
    const entityId = localStorage.getItem("entityId")
    const navigate = useNavigate()

    useEffect(() => {
        const url = `${resourceUrl}/department/get/departments/${entityId}/club`

        axios.get(url)
            .then((response) => {
                setDepartments(response.data)
            })
            .catch((err) => {
                alert(err)
            })

    }, [])

    if (departments.length < 1) {
        return <div>Loading.....</div>
    }

    return (
        <div className='justify-start'>
            <div className='flex ml-80 gap-x-10 items-center'>
                <FeatureHeadingTitle title='Departments' isCenter={false} />
                <Button color='info' variant='contained' onClick={() => navigate("/club/departments/create")}>
                    Add Department
                </Button>
            </div>
            <hr />
            <br />
            <div className="flex ml-80">
                <ul>
                    {
                        departments.map((department: any) => {
                            return <li className="justify-start justify-items-start" key={department.id}>{department.departmentName}</li>
                        })
                    }
                </ul>
            </div>
        </div>
    )
}

export default ManageDepartment