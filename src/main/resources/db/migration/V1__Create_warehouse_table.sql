CREATE TABLE IF NOT EXISTS warehouse
(
    id                   BIGSERIAL PRIMARY KEY,
    warehouseNumber     VARCHAR(225) NOT NULL UNIQUE,
    name                 VARCHAR(225) NOT NULL,
    active               BOOLEAN NOT NULL DEFAULT true,
    version              INTEGER NOT NULL,
    created_time_stamp   TIMESTAMP(6) WITH TIME ZONE,
    updated_time_stamp   TIMESTAMP(6) WITH TIME ZONE
                                          );
