CREATE SCHEMA auth_info;

CREATE TABLE auth_info.users(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40) NOT NULL UNIQUE,
  password VARCHAR(120) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE auth_info.roles(
  id SERIAL PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE auth_info.user_roles(
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY(user_id, role_id),
  CONSTRAINT fk_user_id
    FOREIGN KEY(user_id)
      REFERENCES auth_info.users(id),
  CONSTRAINT fk_role_id
    FOREIGN KEY(role_id)
      REFERENCES auth_info.roles(id)
);

CREATE SCHEMA persons_info;

CREATE TABLE persons_info.addresses(
  id SERIAL PRIMARY KEY,
  city VARCHAR(50) NOT NULL,
  street VARCHAR(50) NOT NULL,
  house VARCHAR(20) NOT NULL,
  flat VARCHAR(20) NOT NULL
);

CREATE TABLE persons_info.contacts(
  id SERIAL PRIMARY KEY,
  phone_number VARCHAR(50) UNIQUE NOT NULL,
  email VARCHAR(50) UNIQUE
);

CREATE TABLE persons_info.persons(
  id SERIAL PRIMARY KEY,
  address_id INTEGER NOT NULL,
  contact_id INTEGER NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50) NOT NULL,
  birth_date DATE NOT NULL,
  CONSTRAINT fk_addresses_id
    FOREIGN KEY(address_id)
      REFERENCES persons_info.addresses(id),
  CONSTRAINT fk_contacts_id
    FOREIGN KEY(contact_id)
      REFERENCES persons_info.contacts(id)
);

CREATE SCHEMA cars_info;

CREATE TABLE cars_info.cars(
  id SERIAL PRIMARY KEY,
  owner_id INTEGER,
  brand VARCHAR(50) NOT NULL,
  model VARCHAR(50) NOT NULL,
  color VARCHAR(50) NOT NULL,
  register_number VARCHAR(50) NOT NULL,
  created DATE NOT NULL,
  CONSTRAINT fk_owner_id
    FOREIGN KEY(owner_id)
      REFERENCES persons_info.persons(id)
);

CREATE TABLE cars_info.violations(
  id SERIAL PRIMARY KEY,
  car_id INTEGER NOT NULL,
  violator_id INTEGER NOT NULL,
  message VARCHAR(100) NOT NULL,
  active BOOLEAN NOT NULL,
  time TIMESTAMP NOT NULL,
  CONSTRAINT fk_car_id
    FOREIGN KEY(car_id)
      REFERENCES cars_info.cars(id),
  CONSTRAINT fk_violator_id
    FOREIGN KEY(violator_id)
      REFERENCES persons_info.persons(id)
);

