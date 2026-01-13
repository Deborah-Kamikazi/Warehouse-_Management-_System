CREATE TABLE item (
        id BIGSERIAL PRIMARY KEY,
        item_number VARCHAR(255) NOT NULL,
        quantity INTEGER NOT NULL CHECK (quantity >= 0),
        version INTEGER NOT NULL,
        location_id BIGINT NOT NULL,
        carton_header_id BIGINT NOT NULL,
        created_time_stamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_time_stamp TIMESTAMP WITH TIME ZONE
);

ALTER TABLE item
    ADD CONSTRAINT uk_item_item_number_location
        UNIQUE (item_number, location_id);

ALTER TABLE item
    ADD CONSTRAINT fk_item_location
        FOREIGN KEY (location_id)
            REFERENCES location(id);

ALTER TABLE item
    ADD CONSTRAINT fk_item_carton_header
        FOREIGN KEY (carton_header_id)
            REFERENCES carton_header(id);
