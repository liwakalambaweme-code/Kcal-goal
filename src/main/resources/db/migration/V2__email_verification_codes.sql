CREATE TABLE email_verification_codes(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    email VARCHAR(254) NOT NULL,
    cose_hash VARCHAR(64) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    expires_at TIMESTAMPTZ NOT NULL,
    failed_Attempts INTEGER NOT NULL DEFAULT 0,
    consumed_at TIMESTAMPTZ,
    invalidated_at TIMESTAMPTZ

);
CREATE INDEX idx_verification_codes_user_id ON email_Verification_codes (user_id);