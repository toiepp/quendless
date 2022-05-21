CREATE TABLE photo (
    photo_id BIGSERIAL PRIMARY KEY,
    path TEXT
);

CREATE TABLE "user" (
    user_id BIGSERIAL PRIMARY KEY,
    name TEXT,
    login TEXT,
    password TEXT,
    photo_id BIGINT REFERENCES photo(photo_id)
);
CREATE INDEX user_login_index ON "user"(login);

CREATE TABLE "group" (
    group_id BIGSERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    photo_id BIGINT REFERENCES photo(photo_id)
);
CREATE INDEX group_name_index ON "group"(name);

CREATE TABLE queue (
    queue_id BIGSERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    event_begin TIMESTAMP,
    event_end TIMESTAMP,
    group_id BIGINT REFERENCES "group"(group_id)
);

CREATE TABLE admin (
    admin_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES "user"(user_id)
);

CREATE TABLE invite (
    invite_id BIGSERIAL PRIMARY KEY,
    group_id BIGINT REFERENCES "group"(group_id),
    user_id BIGINT REFERENCES "user"(user_id),
    status TEXT
);

CREATE TABLE role (
    role_id BIGSERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE queue_member (
    queue_member_id BIGSERIAL PRIMARY KEY,
    queue_id BIGINT REFERENCES queue(queue_id),
    user_id BIGINT REFERENCES "user"(user_id),
    position INT
);

CREATE TABLE group_member (
    group_member_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES "user"(user_id),
    group_id BIGINT REFERENCES "group"(group_id),
    role_id BIGINT REFERENCES role(role_id)
);
