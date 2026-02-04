
CREATE TABLE ssccs (
     id BIGSERIAL PRIMARY KEY,
    sscc VARCHAR(255) NOT NULL,
    carton_header_id BIGINT NOT NULL,
    received_time_stamp TIMESTAMP,
    created_time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT uk_sscc_code UNIQUE (sscc),

         CONSTRAINT fk_sscc_carton_header
         FOREIGN KEY (carton_header_id)
         REFERENCES public.carton_header(id)
         ON DELETE CASCADE
         ON UPDATE CASCADE
);

CREATE INDEX idx_ssccs_carton_header_id ON ssccs(carton_header_id);

CREATE INDEX idx_ssccs_sscc ON ssccs(sscc);

CREATE INDEX idx_ssccs_received_time_stamp ON ssccs(received_time_stamp);
