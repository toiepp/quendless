SELECT 'CREATE DATABASE quendless'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'quendless')\gexec

\c quendless;

CREATE TABLE "user" (
    user_id UUID PRIMARY KEY,
    name VARCHAR(256),
    login VARCHAR(256),
    password VARCHAR(256)
);
CREATE INDEX user_login_index ON "user"(login);

CREATE TABLE "group" (
    group_id UUID PRIMARY KEY,
    name VARCHAR(256),
    description VARCHAR(1024)
);
CREATE INDEX group_name_index ON "group"(name);

CREATE TABLE queue (
    queue_id UUID PRIMARY KEY,
    name VARCHAR(256),
    description VARCHAR(1024),
    event_begin TIMESTAMP,
    event_end TIMESTAMP,
    group_id UUID REFERENCES "group"(group_id)
);

CREATE TABLE invite (
    invite_id UUID PRIMARY KEY,
    group_id UUID REFERENCES "group"(group_id),
    user_id UUID REFERENCES "user"(user_id),
    status VARCHAR(256)
);

CREATE TABLE queue_member (
    queue_member_id UUID PRIMARY KEY,
    queue_id UUID REFERENCES queue(queue_id),
    user_id UUID REFERENCES "user"(user_id),
    position INT
);

CREATE TABLE group_member (
    group_member_id UUID PRIMARY KEY,
    user_id UUID REFERENCES "user"(user_id),
    group_id UUID REFERENCES "group"(group_id)
);

CREATE TABLE permission (
    permission_id UUID PRIMARY KEY,
    user_id UUID REFERENCES "user"(user_id),
    object_type VARCHAR(256),
    object_id UUID,
    permission_type VARCHAR(256),
    expire TIMESTAMP
);

CREATE TABLE action (
    action_id UUID PRIMARY KEY,
    user_id UUID REFERENCES "user"(user_id),
    action_time TIMESTAMP,
    context VARCHAR(256),
    description VARCHAR(256)
);
