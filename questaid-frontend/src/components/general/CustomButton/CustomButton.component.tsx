import React from 'react'

interface ICustomButtonProps {
    buttonText: string,
    loadingButtonText: string,
    ongoingSubmit: boolean,
    onButtonClickAction: any,
    buttonLength: string
}


const CustomButton: React.FC<ICustomButtonProps> = ({ buttonText, loadingButtonText, ongoingSubmit, onButtonClickAction, buttonLength = "w-1/2" }) => {
    return (
        <button
            className={`${buttonLength} justify-center rounded-md bg-teal-800 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-60`}
            disabled={ongoingSubmit}
            onClick={onButtonClickAction}
        >
            {ongoingSubmit ? `${loadingButtonText}....` : buttonText}
        </button>
    )
}

export default CustomButton