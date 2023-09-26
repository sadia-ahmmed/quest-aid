import React from 'react'

interface IFeatureHeadingTitleProps {
    title: string,
    isCenter: boolean
}

const FeatureHeadingTitle: React.FC<IFeatureHeadingTitleProps> = ({ title, isCenter }) => {
    return (
        <h2 className={`mt-5 ${isCenter ? "text-center" : ""} text-2xl font-bold leading-9 tracking-tight text-teal-800`}>
            {title}
        </h2>
    )
}

export default FeatureHeadingTitle