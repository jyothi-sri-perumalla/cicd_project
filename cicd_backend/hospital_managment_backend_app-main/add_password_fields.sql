-- SQL Script to add password fields to officers table
-- Run this script in your MySQL database

-- Add password_hash column
ALTER TABLE officers 
ADD COLUMN password_hash VARCHAR(255) NULL COMMENT 'Hashed password for officer login';

-- Add password_set column  
ALTER TABLE officers 
ADD COLUMN password_set BOOLEAN DEFAULT FALSE COMMENT 'Flag to indicate if password has been set';

-- Update existing officers to have password_set as false
UPDATE officers 
SET password_set = FALSE 
WHERE password_set IS NULL;

-- Verify the changes
DESCRIBE officers;

-- Sample query to check current state
SELECT id, full_name, mobile_number, password_set, created_at 
FROM officers 
ORDER BY created_at DESC;
