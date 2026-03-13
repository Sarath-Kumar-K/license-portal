-- ============================
-- OBJECT METRIC USAGE
-- ============================

CREATE TABLE object_metric_usage (
    id UUID PRIMARY KEY,
    managed_object_id UUID NOT NULL,
    metric_definition_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    reported_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_usage_object
        FOREIGN KEY (managed_object_id)
        REFERENCES managed_object(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_usage_metric
        FOREIGN KEY (metric_definition_id)
        REFERENCES metric_definition(id)
        ON DELETE CASCADE
);