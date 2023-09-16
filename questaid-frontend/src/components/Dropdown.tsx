import { Box, FormControl, InputLabel, MenuItem, Select } from '@mui/material'
import React from 'react'

function Dropdown(props: any) {

    return (
        <Box sx={{ minHeight: 20 }}>
            <FormControl
                fullWidth
                
                className="block w-full rounded-md border-0 py-1 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            >
                <InputLabel className='block text-sm font-medium leading-6 text-gray-900'>{props.label}</InputLabel>
                <Select
                    value={props.value}
                    label={props.label}
                    onChange={props.handleChange}
                >
                    {
                        props.items.map((element: any, index: any) => {
                            return <MenuItem key={index} value={element}>{props.values[index]}</MenuItem>
                        })
                    }
                    {/* <MenuItem value={10}>Ten</MenuItem>
                <MenuItem value={20}>Twenty</MenuItem>
                <MenuItem value={30}>Thirty</MenuItem> */}
                </Select>
            </FormControl>
        </Box>
    )
}

export default Dropdown