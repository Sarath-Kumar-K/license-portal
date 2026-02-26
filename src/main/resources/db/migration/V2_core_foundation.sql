-- ============================
-- ACCOUNT (Business Layer hierarchical)
-- ============================

CREATE TABLE account (
    id UUID PRIMARY KEY,
    parent_account_id UUID NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()

    CONSTRAINT fk_parent_account
        FOREIGN KEY (parent_account_id)
        REFERENCES account(id)
        ON DELETE CASCADE
);


-- ============================
-- PRODUCT INSTANCE
-- ============================

CREATE TABLE product_instance (
    id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    product_code VARCHAR(100) NOT NULL,
    instance_uid VARCHAR(150) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_product_instance_account
        FOREIGN KEY (account_id)
        REFERENCES account(id)
        ON DELETE CASCADE
);

-- ============================
-- PRODUCT TENANT (Operational Isolation Unit)
-- ============================

CREATE TABLE product_tenant (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tenant_code VARCHAR(100) NOT NULL,
    product_instance_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT now(),

    FOREIGN KEY (product_instance_id)
        REFERENCES product_instance(id)
        ON DELETE CASCADE
);

-- ============================
-- MANAGED OBJECT (HIERARCHICAL)
-- ============================

CREATE TABLE managed_object (
    id UUID PRIMARY KEY,
    object_uid VARCHAR(150) UNIQUE NOT NULL,
    parent_object_id UUID NULL,
    product_instance_id UUID NOT NULL,
    product_tenant_id UUID DEFAULT NULL,
    object_type VARCHAR(50) NOT NULL,
    name VARCHAR(255),
    state VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_managed_object_parent
        FOREIGN KEY (parent_object_id)
        REFERENCES managed_object(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_managed_object_product_instance
        FOREIGN KEY (product_instance_id)
        REFERENCES product_instance(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_managed_object_tenant
        FOREIGN KEY (product_tenant_id)
        REFERENCES product_tenant(id)
        ON DELETE CASCADE
);


-- ============================
-- METRIC DEFINITION (STATIC)
-- ============================

CREATE TABLE metric_definition (
    id UUID PRIMARY KEY,
    metric_code VARCHAR(100) UNIQUE NOT NULL,
    unit VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);