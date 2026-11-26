CREATE TABLE users (
    id                    UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    username              VARCHAR(30)  NOT NULL,
    email                 VARCHAR(254) NOT NULL,
    password_hash         VARCHAR(255) NOT NULL,
    email_verified_at     TIMESTAMPTZ,
    status                VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
    terms_version         VARCHAR(20)  NOT NULL,
    terms_accepted_at     TIMESTAMPTZ  NOT NULL,
    deletion_requested_at TIMESTAMPTZ,
    created_at            TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at            TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT ck_users_status CHECK (status IN ('ACTIVE', 'PENDING_DELETION'))
);

CREATE UNIQUE INDEX uq_users_username ON users (lower(username));
CREATE UNIQUE INDEX uq_users_email    ON users (lower(email));