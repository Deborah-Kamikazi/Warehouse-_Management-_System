CREATE TABLE IF NOT EXISTS warehouse
(
    id                 BIGSERIAL PRIMARY KEY,
    warehouse_number   VARCHAR(225),
    name               VARCHAR(225),
    active             BOOLEAN DEFAULT true,
    create_time_stamp  TIMESTAMP(6) WITH TIME ZONE,
    updated_time_stamp TIMESTAMP(6) WITH TIME ZONE
);