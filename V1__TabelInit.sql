CREATE TABLE sensor_type (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(20) NOT NULL UNIQUE
);
INSERT INTO sensor_type (name) VALUES ('Pressure'), ('Voltage'), ('Temperature'), ('Humidity');

CREATE TABLE sensor_unit (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(20) NOT NULL UNIQUE
);
INSERT INTO sensor_unit (name) VALUES ('bar'), ('voltage'), ('°C'), ('%');

CREATE TABLE sensor (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(30) NOT NULL CHECK (LENGTH(name) <= 30),
                        model VARCHAR(15) NOT NULL CHECK (LENGTH(model) <= 15),
                        range_from INTEGER,
                        range_to INTEGER,
                        CONSTRAINT valid_range CHECK (range_from < range_to),
                        type_id INTEGER REFERENCES sensor_type(id),
                        unit_id INTEGER REFERENCES sensor_unit(id),
                        location VARCHAR(40) CHECK (LENGTH(location) <= 40),
                        description VARCHAR(200) CHECK (LENGTH(description) <= 200)
);


CREATE TABLE role (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
                      id SERIAL PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL
);

CREATE TABLE user_role (
                           user_id INTEGER REFERENCES users(id),
                           role_id INTEGER REFERENCES role(id),
                           PRIMARY KEY (user_id, role_id)
);

INSERT INTO role (name) VALUES ('Administrator'), ('Viewer');

INSERT INTO users (username, password) VALUES
                                          ('admin', '$2a$10$YAYWg0T/ywLznFnbkmBSYefpEtLAHp36TjfPgdk58gDbcYECgT3bi'),
                                          ('user', '$2a$10$YAYWg0T/ywLznFnbkmBSYefpEtLAHp36TjfPgdk58gDbcYECgT3bi');

INSERT INTO user_role (user_id, role_id) VALUES
                                             (1, 1), -- admin has Administrator role
                                             (2, 2); -- user has Viewer role

INSERT INTO sensor (name, model, range_from, range_to, type_id, unit_id, location, description)
VALUES
    ('Sensor1', 'ModelA`', 10, 20, 1, 1, 'Location1', 'Description1'),
    ('Sensor2', 'ModelB1', 15, 25, 2, 2, 'Location2', 'Description2'),
    ('Sensor3', 'ModelC3', 5, 15, 3, 3, 'Location3', 'Description3'),
    ('Sensor4', 'ModelAvxc4', 10, 20, 1, 1, 'Location4', 'Description4'),
    ('Sensor5', 'ModelBdsa5', 15, 25, 2, 2, 'Location5', 'Description5'),
    ('Sensor6', 'ModelCcz6', 5, 15, 3, 3, 'Location6', 'Description6'),
    ('Sensor7', 'ModelCcz7', 5, 15, 3, 3, 'Location7', 'Description7'),
    ('Sensor8', 'ModelCcz8', 5, 15, 3, 3, 'Location8', 'Description8'),
    ('Sensor9', 'ModelCcz9', 5, 15, 3, 3, 'Location9', 'Description9'),
    ('Sensor10', 'Modeld10', 30, 40, 4, 4, 'Location10', 'Description10'),
    ('Sensor11', 'Model11', 30, 40, 4, 4, 'Location11', 'Description11'),
    ('Sensor12', 'Model12', 30, 40, 4, 4, 'Location12', 'Description12');
