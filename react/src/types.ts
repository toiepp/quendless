interface Group {
    groupId?: string,
    name: string,
    description?: string,
    membership?: boolean,
    editable?: boolean,
}

interface User {
    [index: string]: any,
    userId?: string,
    login: string,
    password: string,
}

interface ServerMessage {
    message: string
}

interface Queue {
    queueId?: string,
    name: string,
    description?: string
    startDate?: Date,
    endDate?: Date,
    membership?: boolean,
    editable?: boolean,
}

interface CardViewSettings {
    editable: boolean
}

export type { Group, User, ServerMessage, Queue, CardViewSettings };