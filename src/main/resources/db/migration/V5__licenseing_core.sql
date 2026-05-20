CREATE TABLE product (
    id UUID PRIMARY KEY,
    product_code VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE product_metric (
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    metric_definition_id UUID NOT NULL,
    active BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (metric_definition_id) REFERENCES metric_definition(id)
);

CREATE TABLE license_model (
    id UUID PRIMARY KEY,
    model_code VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE duration_definition (
    id UUID PRIMARY KEY,
    duration_code VARCHAR(50) UNIQUE NOT NULL,
    months INTEGER,
    description VARCHAR(255)
);

-- ============================
-- LICENSE PLAN (static license type and plan information)
-- ============================

CREATE TABLE license_plan (
    id UUID PRIMARY KEY,
    product_metric_id UUID NOT NULL,
    license_model_id UUID NOT NULL,
    duration_definition_id UUID,
    plan_code VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255),
    active BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (product_metric_id) REFERENCES product_metric(id),
    FOREIGN KEY (license_model_id) REFERENCES license_model(id),
    FOREIGN KEY (duration_definition_id) REFERENCES duration_definition(id)
);

-- ============================
-- LICENSE
-- ============================

CREATE TABLE license (
    id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    license_plan_id UUID NOT NULL,
    start_time TIMESTAMP NOT NULL,
    duration TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),

    CONSTRAINT fk_license_account
        FOREIGN KEY (account_id)
        REFERENCES account(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_license_plan
        FOREIGN KEY (license_plan_id)
        REFERENCES license_plan(id)
);

-- ============================
-- LICENSE ENTITLEMENT (defines purchased items with remaining available quantity and its license plan type)
-- ============================

CREATE TABLE license_entitlement (
    id UUID PRIMARY KEY,
    license_id UUID NOT NULL,
    metric_definition_id UUID NOT NULL,
    quantity INTEGER NOT NULL,

    CONSTRAINT fk_license_paln
        FOREIGN KEY (license_id)
        REFERENCES license(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_entitlement_metric
        FOREIGN KEY (metric_definition_id)
        REFERENCES metric_definition(id)
);


-- ============================
-- LICENSE ASSIGNMENT
-- ============================

CREATE TABLE license_assignment (
    id UUID PRIMARY KEY,
    license_id UUID NOT NULL,
    metric_definition_id UUID NOT NULL,
    managed_object_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    valid_from TIMESTAMP NOT NULL,
    valid_to TIMESTAMP,
    status VARCHAR(50),

    CONSTRAINT fk_assignment_license
        FOREIGN KEY (license_id)
        REFERENCES license(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_assignment_metric
        FOREIGN KEY (metric_definition_id)
        REFERENCES metric_definition(id),

    CONSTRAINT fk_assignment_object
        FOREIGN KEY (managed_object_id)
        REFERENCES managed_object(id)
        ON DELETE CASCADE
);


-- ============================
-- LICENSE EVENT (AUDIT)
-- ============================

CREATE TABLE license_event (
    id UUID PRIMARY KEY,
    license_id UUID NOT NULL,
    event_type VARCHAR(50) NOT NULL,
    event_time TIMESTAMP NOT NULL,
    performed_by VARCHAR(100),
    notes TEXT,

    CONSTRAINT fk_event_license
        FOREIGN KEY (license_id)
        REFERENCES license(id)
        ON DELETE CASCADE
);

CREATE TABLE pricing (
    id UUID PRIMARY KEY,
    license_plan_id UUID NOT NULL,
    currency VARCHAR(10) NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    valid_from DATE,
    valid_to DATE,

    FOREIGN KEY (license_plan_id) REFERENCES license_plan(id)
);