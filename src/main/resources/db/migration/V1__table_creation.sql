CREATE TABLE contact_type (
id BIGSERIAL PRIMARY KEY,
type VARCHAR NOT NULL
);

CREATE TABLE user_profile (
id BIGSERIAL PRIMARY KEY,
role VARCHAR,
email VARCHAR NOT NULL,
first_name VARCHAR NOT NULL,
last_name VARCHAR NOT NULL,
password VARCHAR NOT NULL
);

CREATE TABLE contact (
id BIGSERIAL PRIMARY KEY,
first_name VARCHAR,
last_name VARCHAR,
address VARCHAR,
phone_number VARCHAR,
contact_type_id BIGINT,
user_profile_id BIGINT,
FOREIGN KEY (contact_type_id) REFERENCES contact_type (id),
FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
);

