import {useDispatch, useSelector} from "react-redux";
import {setCreateMode} from "../../store/slices/groupSlice";

export function GroupCreatingForm() {
    const create = useSelector((state: any) => state.group.create);
    const dispatch = useDispatch()
    if (!create.enabled) {
        return (
            <>
                <button className="btn btn-outline-primary m-1" onClick={() => dispatch(setCreateMode(true))}>Создать</button>
            </>
        )
    }
    return (
        <>
            <div className={"bg-light rounded p-3 col-3"}>
                <h5>Создать группу</h5>
                <label htmlFor="groupName">Название: </label>
                <input className="form-control" id="groupName" type="text" placeholder={"Название группы"}/>
                <label htmlFor="groupDescription">Описание: </label>
                <textarea className="form-control align-content-stretch" id="groupDescription"
                          placeholder="Описание группы"></textarea>
                <div>
                    <button className="btn btn-outline-primary m-1" onClick={() => dispatch(setCreateMode(false))}>Отмена</button>
                    <button className="btn btn-outline-primary m-1">Создать</button>
                </div>
            </div>
            <div className="text-danger">

            </div>
        </>
    );
}