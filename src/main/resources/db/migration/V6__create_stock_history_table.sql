CREATE TABLE stock_history (
    id BIGSERIAL PRIMARY KEY,
    action VARCHAR(20) NOT NULL,
    item_number VARCHAR(50) NOT NULL,
    quantity_change INTEGER NOT NULL,
    from_location_code VARCHAR(50),
    to_location_code VARCHAR(50),
    warehouse_number INTEGER NOT NULL,
    sscc VARCHAR(50),
    created_timestamp TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_stock_history_warehouse_item
    ON stock_history (warehouse_number, item_number);

CREATE INDEX idx_stock_history_from_location
    ON stock_history (from_location_code);

CREATE INDEX idx_stock_history_to_location
    ON stock_history (to_location_code);
