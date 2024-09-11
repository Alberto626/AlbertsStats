Create table if Not Exists dota2.heroes(
	id int Primary key NOT NULL,
	name varchar(255),
    primary_attribute varchar(255),
    roles varchar(255)
);
Create table if Not Exists dota2.items(
	id int PRIMARY KEY NOT NULL,
	name varchar(255),
	description varchar(255),
	cost int
);
create table if not exists dota2.match_details(
	id bigInt Primary Key NOT NULL AUTO_INCREMENT,
	match_id BIGINT NOT NULL Unique,
    radiant_victory bool,
    match_duration_seconds int
);
create table if not exists dota2.player_match_details(
	id bigInt PRIMARY KEY NOT NULL AUTO_INCREMENT,
	match_id BIGINT Not Null Unique,
    player_name varchar(255),
    hero_id int NOT NULL,
    is_radiant bool,
    net_worth int,
    player_slot int,
    kills int,
    deaths int,
    assists int,
    hero_level int,
    hero_damage int,
    tower_damage int,
    item_0_id int,
    item_1_id int,
    item_2_id int,
    item_3_id int,
    item_4_id int,
    item_5_id int,
    backpack_0_id int,
    backpack_1_id int,
    backpack_2_id int,
    item_neutral int,
    foreign key(hero_id) references dota2.heroes(id),
    foreign key(match_id) references dota2.match_details(match_id),
    foreign key(item_neutral) references dota2.items(id),
    foreign key(item_0_id) references dota2.items(id),
    foreign key(item_1_id) references dota2.items(id),
    foreign key(item_2_id) references dota2.items(id),
    foreign key(item_3_id) references dota2.items(id),
    foreign key(item_4_id) references dota2.items(id),
    foreign key(item_5_id) references dota2.items(id),
    foreign key(backpack_0_id) references dota2.items(id),
    foreign key(backpack_1_id) references dota2.items(id),
    foreign key(backpack_2_id) references dota2.items(id)
);