# 📋 **HOSPITAL MANAGEMENT SYSTEM - API DOCUMENTATION**

## 🚀 **Complete Backend Implementation with Location-Based Form Submissions**

### **System Overview**
This system allows officers to submit various ESI forms and KPI reports based on their assigned locations. Each officer can visit different locations and submit forms specific to that location with automatic date tracking.

---

## 📊 **Database Schema**

### **Core Tables**
1. **officers** - Officer information and authentication
2. **officer_locations** - Many-to-many relationship for officer-location assignments
3. **esi_med6_forms** - ESI MED6 form submissions
4. **esi_med6a_forms** - ESI MED6A form submissions  
5. **esi_med9_forms** - ESI MED9 form submissions (298 disease codes)
6. **hospital_kpis** - Hospital KPI tracking forms
7. **dc_kpis** - District Collector KPI forms
8. **location_visits** - Generic location visit tracking

---

## 🔐 **Authentication Endpoints**

### **Officer Login**
```http
POST /api/officers/login
Content-Type: application/json

{
  "email": "officer@hospital.gov.in",
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "id": 1,
    "fullName": "Dr. Raj Kumar",
    "email": "raj.kumar@hospital.gov.in",
    "locations": ["Mumbai Central Hospital", "Mumbai East Hospital"],
    "token": "eyJhbGciOiJIUzI1NiIs..."
  }
}
```

---

## 📝 **Form Submission Endpoints**

### **1. ESI MED6 Form**

#### **Create/Update ESI MED6 Form**
```http
POST /api/forms/esi-med6
Authorization: Bearer {token}
Content-Type: application/json

{
  "officerId": 1,
  "location": "Mumbai Central Hospital",
  "month": "2025-01",
  "institutionName": "Mumbai Central ESI Hospital",
  "institutionCode": "MCH001",
  "region": "Western",
  "opNewCases": 150,
  "opOldCases": 200,
  "ipNewCases": 50,
  "ipOldCases": 75,
  "specialistConsultations": 100,
  "emergencyCases": 25,
  "surgicalProcedures": 15,
  "diagnosticTests": 300,
  "physiotherapySessions": 80,
  "medicinesDispensed": 500,
  "medicalSuppliesUsed": 200,
  "totalExpenditure": 150000.50,
  "medicineCost": 50000.00,
  "equipmentCost": 25000.00,
  "remarks": "Monthly report for January 2025"
}
```

#### **Submit ESI MED6 Form**
```http
POST /api/forms/esi-med6/{formId}/submit
Authorization: Bearer {token}
```

#### **Get ESI MED6 Forms by Officer and Location**
```http
GET /api/forms/esi-med6/officer/{officerId}/location/{location}
Authorization: Bearer {token}
```

#### **Get Specific ESI MED6 Form**
```http
GET /api/forms/esi-med6/officer/{officerId}/location/{location}/month/{month}
Authorization: Bearer {token}
```

---

### **2. DC KPIs Form**

#### **Create/Update DC KPIs Form**
```http
POST /api/forms/dc-kpis
Authorization: Bearer {token}
Content-Type: application/json

{
  "officerId": 1,
  "location": "Mumbai Central Hospital",
  "month": "2025-01",
  "immunization": 150,
  "sterilization": 25,
  "opTreatment": 500,
  "ipTreatment": 100,
  "bedOccupancy": 85,
  "labInvestigations": 300,
  "healthCamps": 5
}
```

#### **Submit DC KPIs Form**
```http
POST /api/forms/dc-kpis/{formId}/submit
Authorization: Bearer {token}
```

#### **Get DC KPIs Forms**
```http
GET /api/forms/dc-kpis/officer/{officerId}/location/{location}
GET /api/forms/dc-kpis/officer/{officerId}/location/{location}/month/{month}
Authorization: Bearer {token}
```

---

### **3. Hospital KPIs Form**

#### **Create/Update Hospital KPIs Form**
```http
POST /api/forms/hospital-kpis
Authorization: Bearer {token}
Content-Type: application/json

{
  "officerId": 1,
  "location": "Mumbai Central Hospital", 
  "month": "2025-01",
  "polioDoses": 50,
  "dptDoses": 60,
  "bcgDoses": 40,
  "measlesDoses": 35,
  "hepatitisDoses": 45,
  "tetanusDoses": 30,
  "covidDoses": 200,
  "familyPlanningProcedures": 20,
  "iudInsertions": 15,
  "generalConsultation": 300,
  "specialistConsultation": 150,
  "emergencyTreatment": 50,
  "medicalAdmissions": 80,
  "surgicalAdmissions": 25,
  "majorSurgeries": 10,
  "minorSurgeries": 35,
  "bloodTests": 400,
  "urineTests": 350,
  "xRays": 200,
  "ultrasounds": 100,
  "normalDeliveries": 15,
  "cesareanDeliveries": 8,
  "dentalConsultations": 75,
  "traumaCases": 20,
  "cardiacEmergencies": 5,
  "patientSatisfactionScore": 4.5,
  "bedOccupancyRate": 85.5,
  "totalRevenue": 500000.00,
  "operationalCosts": 350000.00
}
```

---

## 📊 **Reporting and Analytics Endpoints**

### **Form Summary by Officer**
```http
GET /api/forms/summary/officer/{officerId}
Authorization: Bearer {token}
```

**Response:**
```json
{
  "success": true,
  "data": {
    "esiMed6Count": 5,
    "dcKpisCount": 3,
    "hospitalKpisCount": 4,
    "totalForms": 12
  }
}
```

### **Form Summary by Location**
```http
GET /api/forms/summary/location/{location}
Authorization: Bearer {token}
```

### **Officer's Location Assignment**
```http
GET /api/officers/{officerId}/locations
Authorization: Bearer {token}
```

---

## 🗄️ **Database Queries for Common Operations**

### **1. Get Officer's Form Status for Specific Location and Month**
```sql
CALL GetOfficerFormStatus(1, 'Mumbai Central Hospital', '2025-01');
```

### **2. Get All Forms Submitted by Officer in a Location**
```sql
SELECT 
    'ESI_MED6' as form_type, status, submitted_at, created_at
FROM esi_med6_forms 
WHERE officer_id = 1 AND location = 'Mumbai Central Hospital'

UNION ALL

SELECT 
    'DC_KPIS' as form_type, status, submitted_at, created_at
FROM dc_kpis 
WHERE officer_id = 1 AND location = 'Mumbai Central Hospital'

UNION ALL

SELECT 
    'HOSPITAL_KPIS' as form_type, status, submitted_at, created_at
FROM hospital_kpis 
WHERE officer_id = 1 AND location = 'Mumbai Central Hospital'

ORDER BY created_at DESC;
```

### **3. Get Monthly Report Summary**
```sql
SELECT 
    month,
    location,
    COUNT(*) as total_submissions,
    SUM(CASE WHEN status = 'SUBMITTED' THEN 1 ELSE 0 END) as submitted_forms,
    SUM(CASE WHEN status = 'DRAFT' THEN 1 ELSE 0 END) as draft_forms
FROM (
    SELECT month, location, status FROM esi_med6_forms
    UNION ALL
    SELECT month, location, status FROM dc_kpis
    UNION ALL
    SELECT month, location, status FROM hospital_kpis
) all_forms
WHERE month = '2025-01'
GROUP BY month, location;
```

---

## ⚡ **Frontend Integration Examples**

### **React/TypeScript Frontend Service**

```typescript
class FormApiService {
  private baseUrl = 'http://localhost:8080/api/forms';
  private token = localStorage.getItem('authToken');

  // Submit DC KPIs Form
  async submitDcKpis(formData: DcKpisData) {
    const response = await fetch(`${this.baseUrl}/dc-kpis`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
      body: JSON.stringify({
        ...formData,
        officerId: this.getCurrentOfficerId(),
        month: formData.month,
        location: formData.location
      })
    });
    
    return response.json();
  }

  // Get forms for officer and location
  async getFormsByLocation(officerId: number, location: string) {
    const response = await fetch(
      `${this.baseUrl}/dc-kpis/officer/${officerId}/location/${location}`,
      {
        headers: {
          'Authorization': `Bearer ${this.token}`
        }
      }
    );
    
    return response.json();
  }

  // Check if form exists for current month
  async getFormForCurrentMonth(officerId: number, location: string, month: string) {
    const response = await fetch(
      `${this.baseUrl}/dc-kpis/officer/${officerId}/location/${location}/month/${month}`,
      {
        headers: {
          'Authorization': `Bearer ${this.token}`
        }
      }
    );
    
    return response.json();
  }
}
```

---

## 🔄 **Workflow Example**

### **Officer Daily Workflow:**

1. **Login**: Officer authenticates and receives JWT token
2. **Location Selection**: Officer selects location from assigned locations
3. **Form Selection**: Officer chooses which form to fill (ESI MED6, DC KPIs, etc.)
4. **Form Submission**: System automatically adds:
   - Officer ID
   - Selected location
   - Current date/month
   - Submission timestamp
5. **Status Tracking**: Form status changes from DRAFT → SUBMITTED
6. **Next Location**: Officer can switch to different location and repeat

### **Database Flow:**
```
Officer Login → JWT Token → Location Selection → Form Submission
     ↓                                              ↓
Location Validation ← Officer_Locations → Auto-populate Officer ID
     ↓                                              ↓
Form Validation ← Form Tables → Auto-timestamp Creation
     ↓                                              ↓
Status Update ← Submission → Email Notifications
```

---

## 🚦 **API Response Status Codes**

- **200 OK**: Successful operation
- **201 Created**: Resource created successfully
- **400 Bad Request**: Invalid request data
- **401 Unauthorized**: Authentication required
- **403 Forbidden**: Access denied
- **404 Not Found**: Resource not found
- **409 Conflict**: Form already exists for this month/location
- **500 Internal Server Error**: Server error

---

## 🛠️ **Setup Instructions**

### **1. Database Setup**
```bash
# Run the SQL schema file
mysql -u root -p < hospital_management_schema.sql
```

### **2. Application Properties Configuration**
```properties
# Update application.properties with your database credentials
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### **3. Run the Application**
```bash
# Navigate to the demo folder
cd C:\Users\saite\OneDrive\Desktop\HospitalMobileapp\demo

# Run with Maven
./mvnw spring-boot:run

# Or with Java
mvn clean install
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### **4. Test the APIs**
```bash
# Test connection
curl http://localhost:8080/api/officers/test

# Test form submission
curl -X POST http://localhost:8080/api/forms/dc-kpis \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "officerId": 1,
    "location": "Mumbai Central Hospital",
    "month": "2025-01",
    "immunization": 150,
    "sterilization": 25
  }'
```

---

## 📱 **Mobile App Integration**

The backend is fully ready for mobile app integration with:
- **RESTful APIs** for all operations
- **JWT Authentication** for security
- **CORS enabled** for cross-origin requests
- **Location-based submissions** with automatic tracking
- **Real-time form status** updates
- **Comprehensive reporting** endpoints

Your mobile app can now connect to these APIs and provide a seamless experience for officers to submit forms based on their current location with automatic date tracking! 🎉
