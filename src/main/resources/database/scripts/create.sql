CREATE TABLE account (
                           naam varchar(255) NOT NULL,
                           voornaam varchar(255) NOT NULL,
                           login varchar(255) NOT NULL,
                           paswoord varchar(255) NOT NULL,
                           emailadres varchar(255) NOT NULL,
                           PRIMARY KEY (login),
                           UNIQUE KEY emailadres (emailadres)
);