-- SQL Script for Officer Management Tables

-- Create officers table
CREATE TABLE IF NOT EXISTS officers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile_number VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    INDEX idx_email (email),
    INDEX idx_mobile_number (mobile_number),
    INDEX idx_is_active (is_active),
    INDEX idx_created_at (created_at)
);

-- Create officer_locations table for storing multiple locations per officer
CREATE TABLE IF NOT EXISTS officer_locations (
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    PRIMARY KEY (officer_id, location),
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE,
    INDEX idx_location (location)
);

-- Insert sample data (optional)
-- You can uncomment these lines to add sample officers for testing

/*
-- Sample officer 1
INSERT INTO officers (full_name, email, mobile_number, is_active) 
VALUES ('Dr. Rajesh Kumar', 'rajesh.kumar@hospital.com', '+919876543210', TRUE);

INSERT INTO officer_locations (officer_id, location) 
VALUES 
    (LAST_INSERT_ID(), 'Emergency Ward'),
    (LAST_INSERT_ID(), 'ICU'),
    (LAST_INSERT_ID(), 'General Medicine');

-- Sample officer 2
INSERT INTO officers (full_name, email, mobile_number, is_active) 
VALUES ('Dr. Priya Sharma', 'priya.sharma@hospital.com', '+919876543211', TRUE);

INSERT INTO officer_locations (officer_id, location) 
VALUES 
    (LAST_INSERT_ID(), 'Pediatrics'),
    (LAST_INSERT_ID(), 'Maternity Ward');

-- Sample officer 3
INSERT INTO officers (full_name, email, mobile_number, is_active) 
VALUES ('Mr. Amit Singh', 'amit.singh@hospital.com', '+919876543212', TRUE);

INSERT INTO officer_locations (officer_id, location) 
VALUES 
    (LAST_INSERT_ID(), 'Administration'),
    (LAST_INSERT_ID(), 'Reception'),
    (LAST_INSERT_ID(), 'Pharmacy');
*/

-- Views for easy querying (optional)

-- View to get officers with their locations as comma-separated string
CREATE OR REPLACE VIEW officer_details_view AS
SELECT 
    o.id,
    o.full_name,
    o.email,
    o.mobile_number,
    GROUP_CONCAT(ol.location SEPARATOR ', ') AS locations,
    o.created_at,
    o.updated_at,
    o.is_active
FROM officers o
LEFT JOIN officer_locations ol ON o.id = ol.officer_id
WHERE o.is_active = TRUE
GROUP BY o.id, o.full_name, o.email, o.mobile_number, o.created_at, o.updated_at, o.is_active
ORDER BY o.created_at DESC;

-- View to get active officers count by location
CREATE OR REPLACE VIEW officers_by_location_view AS
SELECT 
    ol.location,
    COUNT(DISTINCT o.id) as officer_count,
    GROUP_CONCAT(DISTINCT o.full_name SEPARATOR ', ') as officer_names
FROM officers o
JOIN officer_locations ol ON o.id = ol.officer_id
WHERE o.is_active = TRUE
GROUP BY ol.location
ORDER BY officer_count DESC, ol.location;

-- Additional indexes for better performance
CREATE INDEX idx_officers_full_name ON officers(full_name);
CREATE INDEX idx_officers_created_at_active ON officers(created_at, is_active);
CREATE INDEX idx_officer_locations_combined ON officer_locations(officer_id, location);

-- Show table structure
DESCRIBE officers;
DESCRIBE officer_locations;
