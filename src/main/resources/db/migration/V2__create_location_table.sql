CREATE TABLE location (
     id BIGSERIAL PRIMARY KEY,
     row INTEGER NOT NULL,
     section INTEGER NOT NULL,
     shelf INTEGER NOT NULL,
     location_code VARCHAR(50) NOT NULL,
     warehouse_id BIGINT NOT NULL,
     version INTEGER DEFAULT 0,
     created_time_stamp TIMESTAMP,
     updated_time_stamp TIMESTAMP,

     CONSTRAINT fk_location_warehouse
     FOREIGN KEY (warehouse_id)
     REFERENCES warehouse (id)
     ON DELETE CASCADE,

    CONSTRAINT uk_warehouse_location_code
    UNIQUE (warehouse_id, location_code)
);
CREATE INDEX idx_location_warehouse_id ON location(warehouse_id);