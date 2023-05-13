export function Card(props: any) {
    return (
        <div className="d-flex flex-column align-items-start p-3 m-2 bg-light rounded text-start">
            {props.children}
        </div>
    );
}