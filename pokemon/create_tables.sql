use sql_intro;

CREATE TABLE pokemon(
    id int PRIMARY KEY,
    name varchar(255),
    height float,
    weight float
);

use sql_intro;
CREATE TABLE owners(
    name varchar(255) NOT NULL,
    town varchar(255),
    PRIMARY KEY(name, town)
);
use sql_intro;

CREATE TABLE pokemon_owners(
    pokemon_id int,
    owners_name varchar(255) NOT NULL,
    owners_town varchar(255),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    FOREIGN KEY (owners_name, owners_town) REFERENCES owners(name, town),
    PRIMARY KEY(pokemon_id, owners_name, owners_town)
);
use sql_intro;

CREATE TABLE types(
    pokemon_id int,
    pokemon_type varchar(20),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    PRIMARY KEY(pokemon_id, pokemon_type)
);

use sql_intro;

DROP TABLE pokemon_owners;
use sql_intro;

DROP TABLE types;
DROP TABLE pokemon;
DROP TABLE owners;
use sql_intro;

insert into types values(133, "normal")

