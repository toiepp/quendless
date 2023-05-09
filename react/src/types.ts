interface Group {
    groupId: string,
    name: string,
    description?: string,
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
    name: string,
    startDate?: Date,
    endDate?: Date,
}

export type { Group, User, ServerMessage, Queue };