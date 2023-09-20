import { Box, FormControl, InputLabel, MenuItem, Select } from '@mui/material'
import React from 'react'

interface IDropdownProps {
    value: string,
    label: string,
    handleChange: any,
    items: any[],
    values: any[]
}

const Dropdown: React.FC<IDropdownProps> = ({ value, label, handleChange, items, values }) => {

    return (
        <Box sx={{ minHeight: 20 }}>
            <FormControl
                fullWidth

                className="block w-full rounded-md border-0 py-1 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            >
                <InputLabel className='block text-sm font-medium leading-6 text-gray-900'>{label}</InputLabel>
                <Select
                    value={value}
                    label={label}
                    onChange={handleChange}
                >
                    {
                        items.map((element: any, index: any) => {
                            return <MenuItem key={index} value={element}>{values[index]}</MenuItem>
                        })
                    }
                </Select>
            </FormControl>
        </Box>
    )
}

export default Dropdown