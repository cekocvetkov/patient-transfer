CREATE TABLE IF NOT EXISTS patient
(
    patientid serial PRIMARY KEY,
    url character varying(50),
    family character varying(50),
    given character varying(50),
    prefix character varying(50),
    suffix character varying(50),
    gender character varying(50),
    birthdate date,
    creationTime Timestamp
);
