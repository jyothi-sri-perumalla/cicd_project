package com.example.demo;

// import com.example.demo.entity.Admin;
// import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	// @Autowired
	// private AdminRepository adminRepository;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		System.out.println("🚀 Starting Hospital Management Application...");
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		// List all REST controllers
		String[] controllerBeans = context.getBeanNamesForAnnotation(RestController.class);
		System.out.println("📋 Registered REST Controllers:");
		for (String controllerBean : controllerBeans) {
			System.out.println("   ✅ " + controllerBean + " -> " + context.getBean(controllerBean).getClass().getName());
		}
		
		System.out.println("✅ Application started successfully!");
	}

	@Override
	public void run(String... args) throws Exception {
		// Create default admin if not exists
		// Temporarily commented out admin creation
		System.out.println("✅ Application initialized without admin setup");
		/*
		if (!adminRepository.existsByEmail("admin@hospital.com")) {
			Admin defaultAdmin = new Admin();
			defaultAdmin.setEmail("admin@hospital.com");
			defaultAdmin.setPassword(passwordEncoder.encode("admin123"));
			defaultAdmin.setFullName("Hospital Administrator");
			defaultAdmin.setIsActive(true);

			adminRepository.save(defaultAdmin);
			System.out.println("Default admin created:");
			System.out.println("Email: admin@hospital.com");
			System.out.println("Password: admin123");
		}
		*/
	}
}
