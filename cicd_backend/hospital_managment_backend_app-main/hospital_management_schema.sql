-- ================================
-- DATABASE SCHEMA FOR HOSPITAL MANAGEMENT SYSTEM
-- Location-Based Form Submissions by Officers
-- ================================

-- Create database (if not exists)
CREATE DATABASE IF NOT EXISTS hospital_management;
USE hospital_management;

-- ================================
-- OFFICERS TABLE (EXISTING - UPDATE IF NEEDED)
-- ================================
-- This table should already exist, but here's the updated structure

CREATE TABLE IF NOT EXISTS officers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile_number VARCHAR(20) NOT NULL,
    password_hash VARCHAR(255),
    password_set BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- ================================
-- OFFICER LOCATIONS TABLE (EXISTING - UPDATE IF NEEDED)
-- ================================
CREATE TABLE IF NOT EXISTS officer_locations (
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    PRIMARY KEY (officer_id, location),
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE
);

-- ================================
-- LOCATION VISITS TABLE (EXISTING - UPDATE IF NEEDED)
-- ================================
CREATE TABLE IF NOT EXISTS location_visits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    visit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    form_type VARCHAR(50) NOT NULL,
    form_data TEXT,
    status VARCHAR(20) DEFAULT 'DRAFT',
    submitted_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE,
    INDEX idx_officer_location (officer_id, location),
    INDEX idx_form_type (form_type),
    INDEX idx_status (status)
);

-- ================================
-- ESI MED6 FORMS TABLE
-- ================================
CREATE TABLE esi_med6_forms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    month VARCHAR(7) NOT NULL, -- Format: YYYY-MM
    
    -- Institution Information
    institution_name VARCHAR(255),
    institution_code VARCHAR(50),
    region VARCHAR(100),
    
    -- Treatment Summary
    op_new_cases INT DEFAULT 0,
    op_old_cases INT DEFAULT 0,
    ip_new_cases INT DEFAULT 0,
    ip_old_cases INT DEFAULT 0,
    total_treatments INT DEFAULT 0,
    
    -- Medical Services
    specialist_consultations INT DEFAULT 0,
    emergency_cases INT DEFAULT 0,
    surgical_procedures INT DEFAULT 0,
    diagnostic_tests INT DEFAULT 0,
    physiotherapy_sessions INT DEFAULT 0,
    
    -- Medications and Supplies
    medicines_dispensed INT DEFAULT 0,
    medical_supplies_used INT DEFAULT 0,
    
    -- Financial Information
    total_expenditure DECIMAL(10,2) DEFAULT 0.00,
    medicine_cost DECIMAL(10,2) DEFAULT 0.00,
    equipment_cost DECIMAL(10,2) DEFAULT 0.00,
    
    -- Additional Notes
    remarks TEXT,
    
    -- Status and Tracking
    status VARCHAR(20) DEFAULT 'DRAFT',
    submitted_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE,
    UNIQUE KEY unique_officer_location_month (officer_id, location, month),
    INDEX idx_officer_location (officer_id, location),
    INDEX idx_month (month),
    INDEX idx_status (status)
);

-- ================================
-- ESI MED6A FORMS TABLE
-- ================================
CREATE TABLE esi_med6a_forms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    month VARCHAR(7) NOT NULL, -- Format: YYYY-MM
    
    -- Institution Information
    institution_name VARCHAR(255),
    institution_code VARCHAR(50),
    region VARCHAR(100),
    reporting_period VARCHAR(100),
    
    -- Advanced Medical Services
    icu_admissions INT DEFAULT 0,
    ventilator_usage_hours INT DEFAULT 0,
    major_surgeries INT DEFAULT 0,
    minor_surgeries INT DEFAULT 0,
    emergency_surgeries INT DEFAULT 0,
    
    -- Specialized Departments
    cardiology_cases INT DEFAULT 0,
    nephrology_cases INT DEFAULT 0,
    oncology_cases INT DEFAULT 0,
    neurology_cases INT DEFAULT 0,
    orthopedic_cases INT DEFAULT 0,
    
    -- Laboratory Services
    blood_tests INT DEFAULT 0,
    urine_tests INT DEFAULT 0,
    radiology_xray INT DEFAULT 0,
    ct_scans INT DEFAULT 0,
    mri_scans INT DEFAULT 0,
    ultrasound_scans INT DEFAULT 0,
    
    -- Pharmacy Services
    prescriptions_filled INT DEFAULT 0,
    controlled_substances INT DEFAULT 0,
    vaccines_administered INT DEFAULT 0,
    
    -- Quality Metrics
    patient_satisfaction_score DECIMAL(3,2) DEFAULT 0.00,
    infection_control_score DECIMAL(3,2) DEFAULT 0.00,
    staff_efficiency_rating DECIMAL(3,2) DEFAULT 0.00,
    
    -- Financial Metrics
    revenue_generated DECIMAL(12,2) DEFAULT 0.00,
    operational_costs DECIMAL(12,2) DEFAULT 0.00,
    equipment_maintenance_cost DECIMAL(10,2) DEFAULT 0.00,
    
    -- Staffing Information
    doctors_count INT DEFAULT 0,
    nurses_count INT DEFAULT 0,
    technicians_count INT DEFAULT 0,
    administrative_staff_count INT DEFAULT 0,
    
    -- Additional Information
    special_programs TEXT,
    challenges_faced TEXT,
    improvement_suggestions TEXT,
    
    -- Status and Tracking
    status VARCHAR(20) DEFAULT 'DRAFT',
    submitted_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE,
    UNIQUE KEY unique_officer_location_month (officer_id, location, month),
    INDEX idx_officer_location (officer_id, location),
    INDEX idx_month (month),
    INDEX idx_status (status)
);

-- ================================
-- ESI MED9 FORMS TABLE
-- ================================
CREATE TABLE esi_med9_forms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    month VARCHAR(7) NOT NULL, -- Format: YYYY-MM
    
    -- Institution Information
    institution_name VARCHAR(255),
    institution_code VARCHAR(50),
    region VARCHAR(100),
    
    -- Disease Categories (298 disease codes simplified)
    infectious_diseases INT DEFAULT 0,
    parasitic_diseases INT DEFAULT 0,
    neoplasms INT DEFAULT 0,
    blood_disorders INT DEFAULT 0,
    endocrine_disorders INT DEFAULT 0,
    mental_disorders INT DEFAULT 0,
    nervous_system_diseases INT DEFAULT 0,
    eye_diseases INT DEFAULT 0,
    ear_diseases INT DEFAULT 0,
    circulatory_diseases INT DEFAULT 0,
    respiratory_diseases INT DEFAULT 0,
    digestive_diseases INT DEFAULT 0,
    skin_diseases INT DEFAULT 0,
    musculoskeletal_diseases INT DEFAULT 0,
    genitourinary_diseases INT DEFAULT 0,
    pregnancy_complications INT DEFAULT 0,
    perinatal_conditions INT DEFAULT 0,
    congenital_anomalies INT DEFAULT 0,
    injuries_poisoning INT DEFAULT 0,
    
    -- Specialist Services
    cardiology_consultations INT DEFAULT 0,
    neurology_consultations INT DEFAULT 0,
    orthopedic_consultations INT DEFAULT 0,
    pediatric_consultations INT DEFAULT 0,
    gynecology_consultations INT DEFAULT 0,
    dermatology_consultations INT DEFAULT 0,
    psychiatry_consultations INT DEFAULT 0,
    ophthalmology_consultations INT DEFAULT 0,
    ent_consultations INT DEFAULT 0,
    radiology_services INT DEFAULT 0,
    pathology_services INT DEFAULT 0,
    physiotherapy_services INT DEFAULT 0,
    
    -- Monthly Statistics
    total_op_patients INT DEFAULT 0,
    total_ip_patients INT DEFAULT 0,
    emergency_cases INT DEFAULT 0,
    surgical_procedures INT DEFAULT 0,
    diagnostic_procedures INT DEFAULT 0,
    laboratory_tests INT DEFAULT 0,
    imaging_studies INT DEFAULT 0,
    
    -- Medications and Supplies
    antibiotics_prescribed INT DEFAULT 0,
    analgesics_prescribed INT DEFAULT 0,
    vaccines_administered INT DEFAULT 0,
    surgical_supplies_used INT DEFAULT 0,
    medical_devices_used INT DEFAULT 0,
    
    -- Financial Information
    total_revenue DECIMAL(12,2) DEFAULT 0.00,
    medicine_expenditure DECIMAL(10,2) DEFAULT 0.00,
    equipment_expenditure DECIMAL(10,2) DEFAULT 0.00,
    staff_salaries DECIMAL(10,2) DEFAULT 0.00,
    administrative_costs DECIMAL(10,2) DEFAULT 0.00,
    
    -- Quality Indicators
    patient_satisfaction_score DECIMAL(3,2) DEFAULT 0.00,
    treatment_success_rate DECIMAL(5,2) DEFAULT 0.00,
    complication_rate DECIMAL(5,2) DEFAULT 0.00,
    readmission_rate DECIMAL(5,2) DEFAULT 0.00,
    infection_control_score DECIMAL(3,2) DEFAULT 0.00,
    
    -- Additional Information
    special_campaigns TEXT,
    research_activities TEXT,
    training_programs TEXT,
    challenges_faced TEXT,
    recommendations TEXT,
    
    -- Status and Tracking
    status VARCHAR(20) DEFAULT 'DRAFT',
    submitted_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE,
    UNIQUE KEY unique_officer_location_month (officer_id, location, month),
    INDEX idx_officer_location (officer_id, location),
    INDEX idx_month (month),
    INDEX idx_status (status)
);

-- ================================
-- HOSPITAL KPIS TABLE
-- ================================
CREATE TABLE hospital_kpis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    month VARCHAR(7) NOT NULL, -- Format: YYYY-MM
    
    -- Immunization Data
    polio_doses INT DEFAULT 0,
    dpt_doses INT DEFAULT 0,
    bcg_doses INT DEFAULT 0,
    measles_doses INT DEFAULT 0,
    hepatitis_doses INT DEFAULT 0,
    tetanus_doses INT DEFAULT 0,
    covid_doses INT DEFAULT 0,
    total_immunization INT DEFAULT 0,
    
    -- Sterilization Data
    family_planning_procedures INT DEFAULT 0,
    iud_insertions INT DEFAULT 0,
    contraceptive_counseling INT DEFAULT 0,
    sterilization_procedures INT DEFAULT 0,
    total_sterilization INT DEFAULT 0,
    
    -- OP Treatment Data
    general_consultation INT DEFAULT 0,
    specialist_consultation INT DEFAULT 0,
    emergency_treatment INT DEFAULT 0,
    follow_up_visits INT DEFAULT 0,
    total_op_treatment INT DEFAULT 0,
    
    -- IP Treatment Data
    medical_admissions INT DEFAULT 0,
    surgical_admissions INT DEFAULT 0,
    pediatric_admissions INT DEFAULT 0,
    maternity_admissions INT DEFAULT 0,
    total_ip_treatment INT DEFAULT 0,
    
    -- Surgery Data
    major_surgeries INT DEFAULT 0,
    minor_surgeries INT DEFAULT 0,
    emergency_surgeries INT DEFAULT 0,
    laparoscopic_surgeries INT DEFAULT 0,
    total_surgeries INT DEFAULT 0,
    
    -- Investigation Data
    blood_tests INT DEFAULT 0,
    urine_tests INT DEFAULT 0,
    x_rays INT DEFAULT 0,
    ultrasounds INT DEFAULT 0,
    ct_scans INT DEFAULT 0,
    mri_scans INT DEFAULT 0,
    ecg_tests INT DEFAULT 0,
    total_investigations INT DEFAULT 0,
    
    -- Delivery Data
    normal_deliveries INT DEFAULT 0,
    cesarean_deliveries INT DEFAULT 0,
    assisted_deliveries INT DEFAULT 0,
    total_deliveries INT DEFAULT 0,
    
    -- Dental Data
    dental_consultations INT DEFAULT 0,
    tooth_extractions INT DEFAULT 0,
    dental_fillings INT DEFAULT 0,
    dental_cleanings INT DEFAULT 0,
    total_dental_procedures INT DEFAULT 0,
    
    -- Emergency Data
    trauma_cases INT DEFAULT 0,
    cardiac_emergencies INT DEFAULT 0,
    respiratory_emergencies INT DEFAULT 0,
    poisoning_cases INT DEFAULT 0,
    total_emergency_cases INT DEFAULT 0,
    
    -- Quality Metrics
    patient_satisfaction_score DECIMAL(5,2) DEFAULT 0.00,
    average_waiting_time DECIMAL(5,2) DEFAULT 0.00,
    bed_occupancy_rate DECIMAL(5,2) DEFAULT 0.00,
    infection_control_score DECIMAL(5,2) DEFAULT 0.00,
    staff_efficiency_rating DECIMAL(5,2) DEFAULT 0.00,
    
    -- Financial Information
    total_revenue DECIMAL(12,2) DEFAULT 0.00,
    operational_costs DECIMAL(12,2) DEFAULT 0.00,
    equipment_costs DECIMAL(10,2) DEFAULT 0.00,
    medicine_costs DECIMAL(10,2) DEFAULT 0.00,
    
    -- Additional Information
    special_programs TEXT,
    challenges_faced TEXT,
    improvement_suggestions TEXT,
    
    -- Status and Tracking
    status VARCHAR(20) DEFAULT 'DRAFT',
    submitted_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE,
    UNIQUE KEY unique_officer_location_month (officer_id, location, month),
    INDEX idx_officer_location (officer_id, location),
    INDEX idx_month (month),
    INDEX idx_status (status)
);

-- ================================
-- DC KPIS TABLE
-- ================================
CREATE TABLE dc_kpis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    officer_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    month VARCHAR(7) NOT NULL, -- Format: YYYY-MM
    
    -- DC KPI Data
    immunization INT DEFAULT 0,
    sterilization INT DEFAULT 0,
    op_treatment INT DEFAULT 0,
    ip_treatment INT DEFAULT 0,
    bed_occupancy INT DEFAULT 0,
    lab_investigations INT DEFAULT 0,
    health_camps INT DEFAULT 0,
    
    -- Status and Tracking
    status VARCHAR(20) DEFAULT 'DRAFT',
    submitted_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (officer_id) REFERENCES officers(id) ON DELETE CASCADE,
    UNIQUE KEY unique_officer_location_month (officer_id, location, month),
    INDEX idx_officer_location (officer_id, location),
    INDEX idx_month (month),
    INDEX idx_status (status)
);

-- ================================
-- ADMINS TABLE (EXISTING - UPDATE IF NEEDED)
-- ================================
CREATE TABLE IF NOT EXISTS admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile_number VARCHAR(20) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'ADMIN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- ================================
-- INSERT SAMPLE DATA FOR TESTING
-- ================================

-- Insert sample officers
INSERT INTO officers (full_name, email, mobile_number, password_hash, password_set, is_active) VALUES
('Dr. Raj Kumar', 'raj.kumar@hospital.gov.in', '9876543210', '$2a$10$example.hash.here', TRUE, TRUE),
('Dr. Priya Singh', 'priya.singh@hospital.gov.in', '9876543211', '$2a$10$example.hash.here', TRUE, TRUE),
('Dr. Amit Patel', 'amit.patel@hospital.gov.in', '9876543212', '$2a$10$example.hash.here', TRUE, TRUE);

-- Insert sample officer locations
INSERT INTO officer_locations (officer_id, location) VALUES
(1, 'Mumbai Central Hospital'),
(1, 'Mumbai East Hospital'),
(2, 'Delhi AIIMS'),
(2, 'Delhi Safdarjung Hospital'),
(3, 'Bangalore General Hospital'),
(3, 'Bangalore Victoria Hospital');

-- Insert sample admin
INSERT INTO admins (full_name, email, mobile_number, password_hash, role, is_active) VALUES
('Admin User', 'admin@hospital.gov.in', '9999999999', '$2a$10$example.admin.hash.here', 'SUPER_ADMIN', TRUE);

-- ================================
-- VIEWS FOR REPORTING
-- ================================

-- View for form submission summary
CREATE VIEW form_submission_summary AS
SELECT 
    o.id as officer_id,
    o.full_name as officer_name,
    o.email,
    COUNT(DISTINCT em6.id) as esi_med6_forms,
    COUNT(DISTINCT em6a.id) as esi_med6a_forms,
    COUNT(DISTINCT em9.id) as esi_med9_forms,
    COUNT(DISTINCT hk.id) as hospital_kpi_forms,
    COUNT(DISTINCT dk.id) as dc_kpi_forms,
    (COUNT(DISTINCT em6.id) + COUNT(DISTINCT em6a.id) + COUNT(DISTINCT em9.id) + 
     COUNT(DISTINCT hk.id) + COUNT(DISTINCT dk.id)) as total_forms
FROM officers o
LEFT JOIN esi_med6_forms em6 ON o.id = em6.officer_id
LEFT JOIN esi_med6a_forms em6a ON o.id = em6a.officer_id
LEFT JOIN esi_med9_forms em9 ON o.id = em9.officer_id
LEFT JOIN hospital_kpis hk ON o.id = hk.officer_id
LEFT JOIN dc_kpis dk ON o.id = dk.officer_id
WHERE o.is_active = TRUE
GROUP BY o.id, o.full_name, o.email;

-- View for location-wise form summary
CREATE VIEW location_form_summary AS
SELECT 
    ol.location,
    COUNT(DISTINCT ol.officer_id) as total_officers,
    COUNT(DISTINCT em6.id) as esi_med6_forms,
    COUNT(DISTINCT em6a.id) as esi_med6a_forms,
    COUNT(DISTINCT em9.id) as esi_med9_forms,
    COUNT(DISTINCT hk.id) as hospital_kpi_forms,
    COUNT(DISTINCT dk.id) as dc_kpi_forms,
    (COUNT(DISTINCT em6.id) + COUNT(DISTINCT em6a.id) + COUNT(DISTINCT em9.id) + 
     COUNT(DISTINCT hk.id) + COUNT(DISTINCT dk.id)) as total_forms
FROM officer_locations ol
LEFT JOIN esi_med6_forms em6 ON ol.officer_id = em6.officer_id AND ol.location = em6.location
LEFT JOIN esi_med6a_forms em6a ON ol.officer_id = em6a.officer_id AND ol.location = em6a.location
LEFT JOIN esi_med9_forms em9 ON ol.officer_id = em9.officer_id AND ol.location = em9.location
LEFT JOIN hospital_kpis hk ON ol.officer_id = hk.officer_id AND ol.location = hk.location
LEFT JOIN dc_kpis dk ON ol.officer_id = dk.officer_id AND ol.location = dk.location
GROUP BY ol.location;

-- ================================
-- TRIGGERS FOR AUTOMATIC CALCULATIONS
-- ================================

-- Trigger to automatically calculate total_treatments in esi_med6_forms
DELIMITER //
CREATE TRIGGER calculate_total_treatments_esi_med6
    BEFORE INSERT ON esi_med6_forms
    FOR EACH ROW
BEGIN
    SET NEW.total_treatments = COALESCE(NEW.op_new_cases, 0) + COALESCE(NEW.op_old_cases, 0) + 
                               COALESCE(NEW.ip_new_cases, 0) + COALESCE(NEW.ip_old_cases, 0);
END//

CREATE TRIGGER update_total_treatments_esi_med6
    BEFORE UPDATE ON esi_med6_forms
    FOR EACH ROW
BEGIN
    SET NEW.total_treatments = COALESCE(NEW.op_new_cases, 0) + COALESCE(NEW.op_old_cases, 0) + 
                               COALESCE(NEW.ip_new_cases, 0) + COALESCE(NEW.ip_old_cases, 0);
END//
DELIMITER ;

-- ================================
-- INDEXES FOR PERFORMANCE
-- ================================

-- Additional indexes for better query performance
CREATE INDEX idx_form_officer_location_month ON esi_med6_forms (officer_id, location, month);
CREATE INDEX idx_form_officer_location_month_6a ON esi_med6a_forms (officer_id, location, month);
CREATE INDEX idx_form_officer_location_month_9 ON esi_med9_forms (officer_id, location, month);
CREATE INDEX idx_form_officer_location_month_hkpi ON hospital_kpis (officer_id, location, month);
CREATE INDEX idx_form_officer_location_month_dkpi ON dc_kpis (officer_id, location, month);

-- Composite indexes for reporting queries
CREATE INDEX idx_submitted_forms_date ON esi_med6_forms (status, submitted_at);
CREATE INDEX idx_submitted_forms_date_6a ON esi_med6a_forms (status, submitted_at);
CREATE INDEX idx_submitted_forms_date_9 ON esi_med9_forms (status, submitted_at);
CREATE INDEX idx_submitted_forms_date_hkpi ON hospital_kpis (status, submitted_at);
CREATE INDEX idx_submitted_forms_date_dkpi ON dc_kpis (status, submitted_at);

-- ================================
-- STORED PROCEDURES FOR COMMON OPERATIONS
-- ================================

DELIMITER //

-- Procedure to get officer's form submission status for a location and month
CREATE PROCEDURE GetOfficerFormStatus(
    IN p_officer_id BIGINT,
    IN p_location VARCHAR(255),
    IN p_month VARCHAR(7)
)
BEGIN
    SELECT 
        'ESI_MED6' as form_type,
        CASE WHEN em6.id IS NOT NULL THEN em6.status ELSE 'NOT_SUBMITTED' END as status,
        em6.submitted_at
    FROM officers o
    LEFT JOIN esi_med6_forms em6 ON o.id = em6.officer_id 
        AND em6.location = p_location AND em6.month = p_month
    WHERE o.id = p_officer_id
    
    UNION ALL
    
    SELECT 
        'ESI_MED6A' as form_type,
        CASE WHEN em6a.id IS NOT NULL THEN em6a.status ELSE 'NOT_SUBMITTED' END as status,
        em6a.submitted_at
    FROM officers o
    LEFT JOIN esi_med6a_forms em6a ON o.id = em6a.officer_id 
        AND em6a.location = p_location AND em6a.month = p_month
    WHERE o.id = p_officer_id
    
    UNION ALL
    
    SELECT 
        'ESI_MED9' as form_type,
        CASE WHEN em9.id IS NOT NULL THEN em9.status ELSE 'NOT_SUBMITTED' END as status,
        em9.submitted_at
    FROM officers o
    LEFT JOIN esi_med9_forms em9 ON o.id = em9.officer_id 
        AND em9.location = p_location AND em9.month = p_month
    WHERE o.id = p_officer_id
    
    UNION ALL
    
    SELECT 
        'HOSPITAL_KPIS' as form_type,
        CASE WHEN hk.id IS NOT NULL THEN hk.status ELSE 'NOT_SUBMITTED' END as status,
        hk.submitted_at
    FROM officers o
    LEFT JOIN hospital_kpis hk ON o.id = hk.officer_id 
        AND hk.location = p_location AND hk.month = p_month
    WHERE o.id = p_officer_id
    
    UNION ALL
    
    SELECT 
        'DC_KPIS' as form_type,
        CASE WHEN dk.id IS NOT NULL THEN dk.status ELSE 'NOT_SUBMITTED' END as status,
        dk.submitted_at
    FROM officers o
    LEFT JOIN dc_kpis dk ON o.id = dk.officer_id 
        AND dk.location = p_location AND dk.month = p_month
    WHERE o.id = p_officer_id;
END//

DELIMITER ;

-- ================================
-- GRANT PERMISSIONS (ADJUST AS NEEDED)
-- ================================

-- Create application user (adjust password as needed)
-- CREATE USER 'hospital_app'@'localhost' IDENTIFIED BY 'secure_password_here';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON hospital_management.* TO 'hospital_app'@'localhost';
-- FLUSH PRIVILEGES;

-- ================================
-- END OF SQL SCRIPT
-- ================================
