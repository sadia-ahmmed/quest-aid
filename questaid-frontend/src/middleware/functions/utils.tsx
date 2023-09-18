const onInputText = (event: any, setState: any) => {
    setState(event.target.value)
}

const clearStates = (setStatesList: any[]) => {
    setStatesList.map((value: any) => {
        value(null)
    })
}

export { onInputText, clearStates }