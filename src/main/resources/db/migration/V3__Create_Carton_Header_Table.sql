CREATE TABLE carton_header (
     id BIGSERIAL PRIMARY KEY,
     barcode VARCHAR(50) NOT NULL UNIQUE,
     description VARCHAR(255),
     version INTEGER NOT NULL DEFAULT 0,
     created_time_stamp TIMESTAMP NOT NULL,
    updated_time_stamp TIMESTAMP NOT NULL
);

CREATE INDEX idx_carton_header_barcode ON carton_header(barcode)