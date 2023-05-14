import {User} from '../../types';
import {Card} from '../primitives/Card';
import {CardRow} from '../primitives/CardRow';
import defaultIcon from '../../res/images/user_icon.png';

export function UserCard({user}: {user: User}) {
    return (
        <Card>
            <CardRow>
                <div className='m-2'>
                    <img src={defaultIcon} alt={'avatar'} style={{display: 'block', maxWidth: '32px', maxHeight: '32px', width: 'auto', height: 'auto'}}/>
                </div>
                <h5 className={'align-self-center'}>{user.login}</h5>
            </CardRow>
        </Card>
    );
}