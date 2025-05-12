insert into profiles(id,name) values (1, 'ROLE_ADMIN');
insert into profiles(id,name) values (2, 'ROLE_USER');

insert into users(id,name,lastname,email,password, is_confirmed) values (1,'Admin','do Sistema','admin@email.com','$2a$10$C6oST9tA93GodyBItgMKd.h7jLPkEZ0PHKQAkfsYSDnxgcbDtM6SG', true);
insert into users(id,name,lastname,email,password, is_confirmed) values (2,'Usuario','do Sistema','user@email.com','$2a$10$C6oST9tA93GodyBItgMKd.h7jLPkEZ0PHKQAkfsYSDnxgcbDtM6SG', true);

insert into users_profiles(users_id,profiles_id) values(1, 1);
insert into users_profiles(users_id,profiles_id) values(2, 2);