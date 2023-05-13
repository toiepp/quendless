export const serverUrl = "http://localhost:8080"
// export const serverUrl = "http://84.201.153.208:8080"

interface RequestInfo {
    relativeUrl: string,
    method: string,
    body?: any,
    contentType?: string,
}

async function makeRequest({relativeUrl, method, body = null, contentType = "application/json"}: RequestInfo): Promise<any> {
    let init: object = {
        method: method,
        credentials: "include"
    }
    if (body !== null) {
        init = {
            ...init,
            body: body,
            headers: {
                'Accept': contentType,
                'Content-Type': contentType
            }
        }
    }
    console.log(init)
    return await fetch(`${serverUrl}${relativeUrl}`, init)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            return data
        })
        .catch((error) => console.log(error))
}

export default makeRequest;