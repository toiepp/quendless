import {User} from '../../types';
import {UserCard} from '../cards/UserCard';

export function UserCardList(
    {users, emptyMessage}:
        {users: User[], emptyMessage: string})
{
    if (users.length === 0)
        return (
            <>
                <p>{emptyMessage}</p>
            </>
        )
    return (
        <>
            {users.map((user, index) => (
                <UserCard user={user} key={index}/>
            ))}
        </>
    );
}