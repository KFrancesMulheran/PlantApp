BEGIN TRANSACTION;

DROP TABLE IF EXISTS plants;
DROP TABLE IF EXISTS my_plants;

CREATE TABLE plants
(
plant_id serial PRIMARY KEY,
plant_name varchar(100) NOT NULL,
scientific_name varchar (100) NOT NULL,
care_difficulty varchar(12),
light_needs varchar(10),
prefers_humidity boolean DEFAULT false,
pet_safe boolean DEFAULT false,
is_unusual boolean DEFAULT false,
hanging_basket boolean DEFAULT false,
is_succulent boolean DEFAULT false,
care_instructions varchar(200) NOT NULL,

CONSTRAINT check_care_difficulty CHECK (care_difficulty IN ('Beginner', 'Intermediate', 'Expert')),
CONSTRAINT check_light_needs  CHECK (light_needs IN ('Low', 'Medium', 'High'))


);


CREATE TABLE my_plants

(
plant_number serial PRIMARY KEY,
plant_id int NOT NULL,

CONSTRAINT plant_id_foreign_key FOREIGN KEY (plant_Id) REFERENCES plants (plant_id)

);


COMMIT;