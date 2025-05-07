-- V1__init_database.sql

-- USERS
CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       phone_number VARCHAR(255) NOT NULL,
                       national_id INTEGER NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- ROLES
CREATE TABLE user_roles (
                            id UUID PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

-- USERS_ROLES (Join table)
CREATE TABLE user_user_roles (
                                 user_id UUID NOT NULL,
                                 id UUID NOT NULL,
                                 PRIMARY KEY (user_id, id),
                                 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                 FOREIGN KEY (id) REFERENCES user_roles(id) ON DELETE CASCADE
);

-- VEHICLE OWNERS
CREATE TABLE vehicle_owners (
                                id UUID PRIMARY KEY,
                                first_name VARCHAR(255) NOT NULL,
                                last_name VARCHAR(255) NOT NULL,
                                national_id INTEGER NOT NULL,
                                phone_number VARCHAR(255) NOT NULL,
                                address_province VARCHAR(255),
                                address_district VARCHAR(255),
                                address_sector VARCHAR(255),
                                address_cell VARCHAR(255)
);

-- PLATE NUMBERS
CREATE TABLE plate_numbers (
                               id UUID PRIMARY KEY,
                               plate_number VARCHAR(255) NOT NULL UNIQUE,
                               issue_date DATE,
                               plate_availability VARCHAR(255),
                               vehicle_owner_id UUID NOT NULL,
                               FOREIGN KEY (vehicle_owner_id) REFERENCES vehicle_owners(id) ON DELETE CASCADE
);

-- VEHICLES
CREATE TABLE vehicles (
                          id UUID PRIMARY KEY,
                          chassis_number VARCHAR(255) NOT NULL UNIQUE,
                          manufacturer VARCHAR(255) NOT NULL,
                          manufacture_year INT NOT NULL,
                          price DOUBLE PRECISION NOT NULL,
                          vehicle_model VARCHAR(255) NOT NULL,
                          plate_number_id UUID UNIQUE,
                          FOREIGN KEY (plate_number_id) REFERENCES plate_numbers(id)
);

-- TRANSFERS
CREATE TABLE transfers (
                           id BIGSERIAL PRIMARY KEY,
                           amount DOUBLE PRECISION,
                           former_owner_id UUID,
                           new_owner_id UUID,
                           transfer_date TIMESTAMP,
                           plate_number_id UUID,
                           FOREIGN KEY (former_owner_id) REFERENCES vehicle_owners(id),
                           FOREIGN KEY (new_owner_id) REFERENCES vehicle_owners(id),
                           FOREIGN KEY (plate_number_id) REFERENCES plate_numbers(id)
);
