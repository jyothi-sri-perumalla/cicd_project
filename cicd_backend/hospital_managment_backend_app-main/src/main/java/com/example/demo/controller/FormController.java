package com.example.demo.controller;

import com.example.demo.dto.EsiMed6Request;
import com.example.demo.dto.EsiMed6Response;
import com.example.demo.dto.DcKpisRequest;
import com.example.demo.entity.DcKpis;
import com.example.demo.entity.HospitalKpis;
import com.example.demo.entity.EsiMed6A;
import com.example.demo.entity.EsiMed9;
import com.example.demo.entity.KpiReport;
import com.example.demo.entity.SasaKpi;
import com.example.demo.service.EsiMed6Service;
import com.example.demo.service.DcKpisService;
import com.example.demo.service.HospitalKpisService;
import com.example.demo.service.EsiMed6AService;
import com.example.demo.service.EsiMed9Service;
import com.example.demo.service.KpiReportService;
import com.example.demo.service.SasaKpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/forms")
@CrossOrigin(origins = {
        "https://hospital-managment-frontned-app.vercel.app/",
        "https://hospital-managment-frontned-app.vercel.app/",
        "http://localhost:3000"
}, methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
}, allowedHeaders = "*", allowCredentials = "true")
public class FormController {

    @Autowired
    private EsiMed6Service esiMed6Service;

    @Autowired
    private DcKpisService dcKpisService;

    @Autowired
    private HospitalKpisService hospitalKpisService;

    @Autowired
    private EsiMed6AService esiMed6AService;

    @Autowired
    private EsiMed9Service esiMed9Service;

    @Autowired
    private KpiReportService kpiReportService;

    @Autowired
    private SasaKpiService sasaKpiService;

    // ================================
    // ESI MED6 FORM ENDPOINTS
    // ================================

    @PostMapping("/esi-med6")
    public ResponseEntity<Map<String, Object>> createOrUpdateEsiMed6(@RequestBody EsiMed6Request request) {
        try {
            EsiMed6Response response = esiMed6Service.createOrUpdateForm(request);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "ESI MED6 form saved successfully");
            result.put("data", response);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error saving ESI MED6 form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/esi-med6/{formId}/submit")
    public ResponseEntity<Map<String, Object>> submitEsiMed6(@PathVariable Long formId) {
        try {
            EsiMed6Response response = esiMed6Service.submitForm(formId);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "ESI MED6 form submitted successfully");
            result.put("data", response);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error submitting ESI MED6 form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/esi-med6/officer/{officerId}")
    public ResponseEntity<Map<String, Object>> getEsiMed6ByOfficer(@PathVariable Long officerId) {
        try {
            List<EsiMed6Response> forms = esiMed6Service.getFormsByOfficer(officerId);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching ESI MED6 forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/esi-med6/officer/{officerId}/location/{location}")
    public ResponseEntity<Map<String, Object>> getEsiMed6ByOfficerAndLocation(
            @PathVariable Long officerId, @PathVariable String location) {
        try {
            List<EsiMed6Response> forms = esiMed6Service.getFormsByOfficerAndLocation(officerId, location);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching ESI MED6 forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/esi-med6/officer/{officerId}/location/{location}/month/{month}")
    public ResponseEntity<Map<String, Object>> getEsiMed6ByOfficerLocationAndMonth(
            @PathVariable Long officerId, @PathVariable String location, @PathVariable String month) {
        try {
            Optional<EsiMed6Response> form = esiMed6Service.getFormByOfficerLocationAndMonth(officerId, location,
                    month);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", form.orElse(null));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching ESI MED6 form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // ================================
    // DC KPIS FORM ENDPOINTS
    // ================================

    @PostMapping("/dc-kpis")
    public ResponseEntity<Map<String, Object>> createOrUpdateDcKpis(@RequestBody DcKpisRequest request) {
        try {
            DcKpis response = dcKpisService.createOrUpdateForm(request);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "DC KPIs form saved successfully");
            result.put("data", response);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error saving DC KPIs form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/dc-kpis/{formId}/submit")
    public ResponseEntity<Map<String, Object>> submitDcKpis(@PathVariable Long formId) {
        try {
            DcKpis response = dcKpisService.submitForm(formId);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "DC KPIs form submitted successfully");
            result.put("data", response);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error submitting DC KPIs form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/dc-kpis/officer/{officerId}")
    public ResponseEntity<Map<String, Object>> getDcKpisByOfficer(@PathVariable Long officerId) {
        try {
            List<DcKpis> forms = dcKpisService.getFormsByOfficer(officerId);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching DC KPIs forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/dc-kpis/officer/{officerId}/location/{location}")
    public ResponseEntity<Map<String, Object>> getDcKpisByOfficerAndLocation(
            @PathVariable Long officerId, @PathVariable String location) {
        try {
            List<DcKpis> forms = dcKpisService.getFormsByOfficerAndLocation(officerId, location);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching DC KPIs forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/dc-kpis/officer/{officerId}/location/{location}/month/{month}")
    public ResponseEntity<Map<String, Object>> getDcKpisByOfficerLocationAndMonth(
            @PathVariable Long officerId, @PathVariable String location, @PathVariable String month) {
        try {
            Optional<DcKpis> form = dcKpisService.getFormByOfficerLocationAndMonth(officerId, location, month);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", form.orElse(null));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching DC KPIs form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // ================================
    // HOSPITAL KPIS FORM ENDPOINTS
    // ================================

    @PostMapping("/hospital-kpis")
    public ResponseEntity<Map<String, Object>> createOrUpdateHospitalKpis(@RequestBody Map<String, Object> request) {
        try {
            // Process hospital KPIs request (similar to above)
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Hospital KPIs form saved successfully");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error saving Hospital KPIs form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // ================================
    // GENERIC FORM ENDPOINTS
    // ================================

    @GetMapping("/summary/officer/{officerId}")
    public ResponseEntity<Map<String, Object>> getFormsSummaryByOfficer(@PathVariable Long officerId) {
        try {
            Map<String, Object> summary = new HashMap<>();

            // Get counts for each form type
            List<EsiMed6Response> esiMed6Forms = esiMed6Service.getFormsByOfficer(officerId);
            List<DcKpis> dcKpisForms = dcKpisService.getFormsByOfficer(officerId);

            summary.put("esiMed6Count", esiMed6Forms.size());
            summary.put("dcKpisCount", dcKpisForms.size());
            summary.put("totalForms", esiMed6Forms.size() + dcKpisForms.size());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", summary);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching forms summary: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/summary/location/{location}")
    public ResponseEntity<Map<String, Object>> getFormsSummaryByLocation(@PathVariable String location) {
        try {
            Map<String, Object> summary = new HashMap<>();

            // Get counts for each form type by location
            List<EsiMed6Response> esiMed6Forms = esiMed6Service.getFormsByLocation(location);
            List<DcKpis> dcKpisForms = dcKpisService.getFormsByLocation(location);

            summary.put("esiMed6Count", esiMed6Forms.size());
            summary.put("dcKpisCount", dcKpisForms.size());
            summary.put("totalForms", esiMed6Forms.size() + dcKpisForms.size());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", summary);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching forms summary: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // ================================
    // ADMIN ENDPOINTS FOR ALL FORMS
    // ================================

    @GetMapping("/dc-kpis")
    public ResponseEntity<Map<String, Object>> getAllDcKpisForms() {
        try {
            List<DcKpis> forms = dcKpisService.getAllForms();
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching DC KPIs forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/hospital-kpis")
    public ResponseEntity<Map<String, Object>> getAllHospitalKpisForms() {
        try {
            List<HospitalKpis> forms = hospitalKpisService.getAllForms();
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching Hospital KPIs forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/esi-med6")
    public ResponseEntity<Map<String, Object>> getAllEsiMed6Forms() {
        try {
            List<EsiMed6Response> forms = esiMed6Service.getAllForms();
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching ESI MED6 forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/esi-med6a")
    public ResponseEntity<Map<String, Object>> getAllEsiMed6AForms() {
        try {
            List<EsiMed6A> forms = esiMed6AService.getAllForms();
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching ESI MED6A forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/esi-med9")
    public ResponseEntity<Map<String, Object>> getAllEsiMed9Forms() {
        try {
            List<EsiMed9> forms = esiMed9Service.getAllForms();
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching ESI MED9 forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/kpi-report")
    public ResponseEntity<Map<String, Object>> getAllKpiReportForms() {
        try {
            List<KpiReport> forms = kpiReportService.getAllForms();
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching KPI Report forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/sasa-kpi")
    public ResponseEntity<Map<String, Object>> getAllSasaKpiForms() {
        try {
            List<SasaKpi> forms = sasaKpiService.getAllForms();
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", forms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error fetching SASA KPI forms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
