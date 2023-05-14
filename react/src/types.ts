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
    name?: string
    login: string,
    password?: string,
}

interface ServerMessage {
    message: string
}

interface Queue {
    queueId?: string,
    name: string,
    description?: string
    eventBegin?: string,
    eventEnd?: string,
    groupId?: string,
    membership?: boolean,
    editable?: boolean,
}

interface CardViewSettings {
    editable: boolean
}

interface QueueMember {
    queueMemberId: string,
    queue: Queue,
    user: User,
    position: number,
}

interface GroupMember {
    groupMemberId: string,
    group: Group,
    user: User
}

export type { Group, User, Queue, GroupMember, QueueMember, CardViewSettings, ServerMessage };