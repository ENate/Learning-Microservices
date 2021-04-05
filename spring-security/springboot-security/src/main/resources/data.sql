INSERT INTO users (username, password, enabled)
values('user', '{noop}password', true);

INSERT INTO users (username, password, enabled)
values('admin', '{noop}pass', true);

INSERT INTO authorities (username, authority)
values('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
values('admin', 'ROLE_ADMIN');