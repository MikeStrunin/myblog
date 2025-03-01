-- Таблица с пользователями
create table if not exists posts(
                                    id bigserial primary key,
                                    caption varchar(256) not null,
    text varchar(256) not null,
    likesCount integer not null,
    creationDate timestamp null);

insert into posts(caption, text, likesCount) values ('Иван', 'Иванов', 30);
insert into posts(caption, text, likesCount) values ('Пётр', 'Петров', 25);
insert into posts(caption, text, likesCount) values ('Мария', 'Сидорова', 28);