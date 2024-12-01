# **Smart Contact Manager (SCM1.0)**

SCM1.0 is a robust and feature-rich cloud-based contact management system tailored for startups and small businesses. The application empowers users to manage their contacts securely and efficiently, leveraging advanced technologies for enhanced functionality and seamless user experience.

## **Features**
- **Contact Management**: Add, update, view, and delete contacts with ease.
- **Cloud-Based Storage**: Access your contact data from anywhere.
- **Authentication and Authorization**:
  - Secure login system with Spring Security.
  - Multiple login options using Google API and GitHub API.
  - Verification via email and mobile OTP.
- **Email and SMS Integration**:
  - Send verification emails with links.
  - Send SMS notifications using Twilio API.
- **Enhanced Security**:
  - Password encryption and decryption.
  - JWT tokens for secure API communication.
- **User-Friendly Interface**:
  - Built with Tailwind CSS and HTML for a clean, modern design.
  - Fully responsive for a seamless experience across devices.

---

## **Technology Stack**
### **Backend**
- **Java**: Core business logic.
- **Spring Boot**: Framework for building the application.
- **Hibernate**: ORM for database interaction.
- **MySQL**: Relational database.

### **Frontend**
- **HTML, JavaScript**: Core structure and interactivity.
- **Tailwind CSS**: Modern styling.

### **APIs Integrated**
- **Google API**: Login and authentication.
- **GitHub API**: Login functionality.
- **Twilio API**: SMS notifications.
- **Google Mail API**: Email notifications.

---

## **Installation**
1. Clone the repository:
   ```bash
   git clone https://github.com/lucky001118/Smart_Contact_Manager.git
   ```
2. Navigate to the project directory:
   ```bash
   cd SCM1.0
   ```
3. Set up the MySQL database:
   - Create a database named `smart_contact_manager`.
   - Update `application.properties` with your database credentials.
4. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. Open the application in your browser:
   ```
   http://localhost:8080
   ```

---

## **Usage**
1. **Sign Up**: Register as a new user using email or social login.
2. **Add Contacts**: Save personal or business contacts.
3. **Manage Contacts**: Edit, view, or delete existing contacts.
4. **Notifications**:
   - Verify your account via email link or OTP.
   - Receive SMS alerts for critical actions.

---

## **Screenshots**
![Login Page](path-to-login-screenshot)
![Dashboard](path-to-dashboard-screenshot)
![Contact Management](path-to-contact-management-screenshot)

---

## **Contributing**
We welcome contributions! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Added feature-name"
   ```
4. Push to your fork and submit a pull request.

---

## **Acknowledgments**
- **Spring Boot**: For the backend framework.
- **Google and GitHub APIs**: For authentication integration.
- **Twilio**: For SMS notifications.
- **Tailwind CSS**: For styling.

---

