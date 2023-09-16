class HeaderBuilder {
    private headerDict: any

    constructor() {
        this.headerDict = {}
    }

    addAuthorization(token: string | null) {
        this.headerDict["Authorization"] = token
        return this
    }

    addContentType(contentType: string | null) {
        this.headerDict["Content-Type"] = contentType
        return this
    }

    build() {
        return this.headerDict
    }

}

export default HeaderBuilder