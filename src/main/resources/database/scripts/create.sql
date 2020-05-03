CREATE TABLE account (
                           naam varchar(255) NOT NULL,
                           voornaam varchar(255) NOT NULL,
                           login varchar(255) NOT NULL,
                           paswoord varchar(255) NOT NULL,
                           emailadres varchar(255) NOT NULL,

                           PRIMARY KEY (login),
                           UNIQUE KEY emailadres (emailadres)
);
CREATE table kind (
    id int(10) not null auto_increment,
    loginlogo varchar(255) NOT NULL,
    naam varchar(255) not null ,
    voornaam varchar(255) not null,
    email varchar(255),
    geboortedatum date not null ,
    geslacht enum('M', 'V') not null ,
    straatnaam varchar(255) ,
    huisnummer int(10) ,
    postcode int(10) ,
    gemeente varchar(255) ,
    primary key (id, loginlogo)
);

create table therapie (
    loginlogo varchar(255) NOT NULL,
    idkind int(10) not null ,
    soort varchar(255) not null ,
    datum timestamp(3) not null,
    geprint bool,
    betaald bool,
    primary key (loginlogo, idkind, soort, datum)
);

create table soortTherapie (
    naam varchar(255) not null ,
    nomenclatuurnr int(10) not null unique,
    primary key (nomenclatuurnr)
);

alter table kind add constraint kindLogo foreign key (loginlogo) references account (login);
alter table therapie add constraint therapieLogo foreign key (loginlogo) references account (login);
alter table therapie add constraint therapieKind foreign key (idkind) references kind (id);
alter table therapie add constraint therapieSoort foreign key (soort) references soortTherapie (naam);