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
        <div className="ml-80">
            <div className='flex items-center'>
                <FeatureHeadingTitle title='Departments' isCenter={false} />
                <Button sx={{ backgroundColor: 'teal' }} variant='contained' style={{ margin: 10, marginLeft: 100, marginTop: 33 }} onClick={() => navigate("/club/departments/create")}>
                    Add Department
                </Button>
            </div>
            <hr className='w-1/4 mt-5' />
            <br />
            <div className="flex">
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