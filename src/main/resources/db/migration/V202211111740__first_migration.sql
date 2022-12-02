
create table products
(
    id          bigserial
        primary key,
    amount      bigint         not null
        constraint products_amount_check
            check (amount >= 1),
    description varchar(255),
    img         varchar(255)   not null,
    name        varchar(255)   not null,
    price       numeric(19, 2) not null
);


create table users
(
    id        bigserial
        primary key,
    email     varchar(255)                                  not null,
    enabled   boolean     default true                      not null,
    nick_name varchar(255)                                  not null,
    password  varchar(255)                                  not null,
    role      varchar(50) default 'USER'::character varying not null
);


create table cart
(
    id        bigserial
        primary key,
    date_time timestamp not null,
    user_id   bigint
        constraint fkg5uhi8vpsuy0lgloxk2h4w5o6
            references users
);


create table cart_products_intermediate
(
    id             bigserial
        primary key,
    product_amount bigint not null,
    cart_id        bigint
        constraint fkqhb9gsynwxn1590lvchpxlrt9
            references cart,
    product_id     bigint
        constraint fkb4gdf19ci1a9utsix66sfjcxp
            references products
);



create table feedbacks
(
    id         bigserial
        primary key,
    date_time  timestamp    not null,
    text       varchar(255) not null,
    product_id bigint
        constraint fkti2ywtwc29ys1i591rmmaveyc
            references products,
    user_id    bigint
        constraint fk312drfl5lquu37mu4trk8jkwx
            references users
);


create table orders
(
    id         bigserial
        primary key,
    amount     bigint,
    date_time  timestamp      not null,
    total_sum  numeric(19, 2) not null,
    product_id bigint
        constraint fkkp5k52qtiygd8jkag4hayd0qg
            references products,
    user_id    bigint
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

