-- Create users table
CREATE TABLE users (
   id BIGINT NOT NULL auto_increment,
   username CHARACTER VARYING(255) NOT NULL,
   password CHARACTER VARYING(255) NOT NULL,
   name CHARACTER VARYING(255) NOT NULL,
   address CHARACTER VARYING(255) NOT NULL,
   phone CHARACTER VARYING(30) NOT NULL,
   created_date TIMESTAMP NOT NULL,
   active BOOLEAN NOT NULL DEFAULT false
);

-- Create roles table
CREATE TABLE roles (
   id BIGINT NOT NULL auto_increment,
   username CHARACTER VARYING(255) NOT NULL,
   authority CHARACTER VARYING(255) NOT NULL,
   user_id BIGINT NOT NULL
);

-- Create bookings table
CREATE TABLE bookings (
   id BIGINT NOT NULL auto_increment,
   property_id BIGINT NOT NULL,
   guest_id BIGINT NOT NULL,
   date_from TIMESTAMP NOT NULL,
   date_to TIMESTAMP NOT NULL,
   booking_status_id BIGINT NOT NULL,
   people_amount INTEGER NOT NULL,
   babys_amount INTEGER NOT NULL,
   food_preferences TEXT NOT NULL,
   has_pets BOOLEAN NOT NULL

);

-- Create booking_status table
CREATE TABLE booking_status (
   id BIGINT NOT NULL auto_increment,
   name CHARACTER VARYING(255) NOT NULL,
   description TEXT NOT NULL
);

-- Create properties table
CREATE TABLE properties (
    id BIGINT NOT NULL auto_increment,
    description TEXT NOT NULL,
    address CHARACTER VARYING(255) NOT NULL,
    user_id BIGINT NOT NULL
);

-- Create property_date_blocks table
CREATE TABLE property_date_blocks (
    id BIGINT NOT NULL auto_increment,
    property_id BIGINT NOT NULL,
    block_from TIMESTAMP NOT NULL,
    block_to TIMESTAMP NOT NULL
);


--DML
-- Adding basic data
-- Insert Booking status
INSERT INTO booking_status (id, name, description) VALUES (1, 'Requested', 'Requested status');
INSERT INTO booking_status (id, name, description) VALUES (2, 'Canceled', 'Canceled status');
