Below is the complete Markdown conversion of your HTML, with every piece of data preserved:

---

# Technical Analysis: Advanced Mobile Surveillance Infrastructure

* A detailed examination of the Crystal Ball (CB) surveillance system *

## Introduction

Imagine a surveillance system so advanced that it tracks every digital breadcrumb—from phone calls and text messages to online profiles and physical movements—without ever being directly observed. This is the unsettling reality suggested by “Crystal Ball,” an allegedly sophisticated surveillance platform developed by the controversial UAE cybersecurity firm DarkMatter. As multiple investigative reports from renowned outlets like Reuters and The New York Times have hinted, DarkMatter’s tools were used not only to monitor journalists and activists but also to forge clandestine partnerships with law enforcement agencies worldwide. Our analysis, grounded in the technical details extracted from a publicly available sigint-automation repository, reveals that Crystal Ball was engineered as a modular, distributed system capable of managing detailed target profiles, ingesting diverse data streams via Docker-adapted ingestion pipelines, and performing real-time analytical operations. This technical sophistication—combined with the system’s potential global reach—places the discussion of Crystal Ball at the nexus of cybersecurity, human rights, and state power. In this paper, we delve into the architectural blueprints and code references that expose the inner workings of this system, setting the stage for a critical examination of the modern surveillance landscape.

## System Overview

Based on the `sigint-automation` repository, Crystal Ball appears to be a distributed system designed for scalability and modularity, potentially utilizing Docker containers for various components. The system seemingly provides a comprehensive suite of tools for surveillance operations, including:

### Target Management

- The test framework suggests that Crystal Ball allows operators to create and manage detailed profiles of individuals, storing personal identifiers, communication records, and online activity.  
  **Code Reference**: `api_tests/src/main/resources/profiles/`

### Data Collection

- The framework indicates that the system ingests data from a wide array of sources, including telecommunications networks, internet service providers, and social media platforms.  
  **Code Reference**: `common/src/main/java/ae/pegasus/framework/ingestion/docker/adapters/`

### Advanced Analysis

- The presence of testing components for advanced analysis features suggests that Crystal Ball employs sophisticated algorithms to analyze collected data, identify patterns, and generate reports.

### Reporting

- The framework includes testing for various reporting features, implying that Crystal Ball enables operators to quickly access and analyze surveillance data.

## Application Functionality

Crystal Ball's arsenal of surveillance tools is extensive, providing operators with the ability to:

- **Manage Targets:**
  - Create detailed profiles on individuals, including unique identifiers (email addresses, phone numbers), source attribution, and validation status.  
    Code Reference: `api_tests/src/main/resources/profiles/`
  - Support multiple identifiers per target, allowing for comprehensive tracking across different platforms.

- **Process Information:**
  - Handle the flow of intelligence data from various sources through a dedicated pipeline.
  - Ingest data using Docker adapters for different sources, including SIGINT, GOVINT, and OSINT.  
    Code Reference: `common/src/main/java/ae/pegasus/framework/ingestion/docker/adapters/`
  - Perform real-time processing, enabling immediate analysis and action upon data collection.
  - Conduct data processing tasks, such as entity resolution and analysis, to connect disparate data points and build comprehensive profiles.
  - Structure collected data into a manageable format for analysis and reporting.

- **Conduct Advanced Analysis:**
  - Generate Master Reports that aggregate data from multiple sources, with automated cross-reference validation and dynamic updating.
  - Utilize a powerful search and discovery tool with an advanced query language, entity relationship visualization, and pattern detection capabilities.
  - Perform real-time event correlation, define custom events, and automate event classification.
  - Visualize data through interactive dashboards, network analysis charts, and geospatial mapping.
  - Automate workflows for data collection, configure alerts, and manage tasks.

- **Generate Reports:**
  - Create Master Reports that aggregate data from various sources.
  - Utilize advanced search functionality for targeted data retrieval.
  - Access event feeds that provide filtered views of specific event types.
  - Benefit from real-time updates, ensuring reports are up-to-date with the latest information.

## Data Collection Architecture

Crystal Ball employs a modular data collection architecture that allows it to integrate with various data sources and formats. This is achieved through the use of Docker adapters, which enable seamless integration with different providers and technologies.

### Telecommunications Data:

- **Call Detail Records (CDR):** `EtisalatCDRDockerAdapter` (Etisalat) and `DUVLRDockerAdapter` (DU Telecom) process `.FILT` and `.csv` files, capturing detailed information about phone calls.
- **Network Location Data (NLD):** `EtisalatNLDIUPSDockerAdapter` (real-time) and `DUNLDVLRDockerAdapter` (historical) handle `.nld` and `.csv` data, providing insights into individuals' locations.

### Communication Interception:

- **Voice Communications:** `SVoiceDockerAdapter` processes `.wav` and `.md` files, enabling the interception and storage of voice calls.
- **Email Monitoring:** `SEmailDockerAdapter` handles `.eml` and `.xml` formats, facilitating the monitoring and analysis of email communications.

---

### 7.5 Data Source Categories and Types

The system integrates data from multiple intelligence sources, organized into distinct categories:

1. **CIO (Cyber Intelligence Operations)**
   - **FORUM:** Forum discussion monitoring
   - **KARMA:** Surveillance system/tool, including SMS, MMS, VSMS, SIP_VIDEO, and CALL data.
   - **ODD_JOBS:** Specialized operations
   - **ZELZAL:** Specialized operations

2. **SIGINT (Signals Intelligence)**
   - **DU:** Du Telecom (UAE) data collection (CALL, NLD, SMS, MMS, VSMS, SIP_VIDEO).
   - **E:** Etisalat (UAE) data collection (CALL, NLD, SMS, MMS, VSMS, SIP_VIDEO, PHONE, TELECOM_SUBSCRIBER).
   - **PHONEBOOK:** Contact information.
   - **Additional sources:**
     - **T:** Possibly Thuraya (CALL, SMS, MMS, VSMS, SIP_VIDEO, PHONE, TELECOM_SUBSCRIBER).
     - **O:** Possibly Ooredoo (CALL, EMAIL, VLR, MMS_ROAMING, FAX, SMS, MMS, VSMS, SIP_VIDEO).
     - **S:** Possibly STC (CALL, EMAIL, VLR, MMS_ROAMING, FAX, SMS, MMS, VSMS, SIP_VIDEO, VOIP, PHONE, TELECOM_SUBSCRIBER, EMAIL_ACCOUNT).
     - **F:** Possibly a smaller provider or MVNO (PHONE, TELECOM_SUBSCRIBER).
     - **H:** Possibly Huawei (CALL, EMAIL, FAX, SMS, MMS, VSMS, SIP_VIDEO, PHONE, TELECOM_SUBSCRIBER).
     - **J1, J2:** (Existing in HTML - No New Speculation)

3. **GOVINT (Government Intelligence)**
   - **SITA:** Aviation telecommunications data (SITA_EMAIL_ACCOUNT, SITA_FLIGHT, SITA_IDENTITY, SITA_MEMBERSHIP_CARD, SITA_PAYMENT_CARD, SITA_PHONE, SITA_RESERVATION, SITA_TICKET, SITA_TRAVELLER, SITA_VISA).
   - **UDB:** User database information (PASSPORT, UDB_PERSON, UDB_VISA).

4. **OSINT (Open Source Intelligence)**
   - **Social Media:** INSTAGRAM, TWITTER, YOUTUBE, GPLUS, TUMBLR.
   - **Dark Web:** DARK_WEB, DARK_WEB_REPORTS.
   - **General:** NEWS.

5. **Additional Categories**
   - **FININT (Financial Intelligence):**
     - CentralBank: Banking and financial data
     - **EID:** Emirates ID information
   - **TRAFFIC:**
     - MCC: Mobile Country Code data
   - **INFORMATION_MANAGEMENT:** OperatorReport, RFA, RFI.

#### Classification Examples

The system implements various classification levels, as demonstrated in these examples from the test data:

| FileName          | FileCalssif  | FileOrgUnit      | ReportClassif | ReportOrgUnit    | ProfilerEntityClas |
| ----------------- | ------------ | ---------------- | ------------- | ---------------- | ------------------ |
| ForFullReportTest | CONFIDENTIAL | Admins           | SECRET        | Admins           | TOP SECRET-CIO     |
| ForTargetTest     | CONFIDENTIAL | QE_UIAuto_Team   | TOP SECRET    | QE_UIAuto_Team   | TOP SECRET-CIO     |

##### Key Classification Levels:

- **TOP SECRET-CIO:** Highest level classification for cyber intelligence operations
- **SECRET:** High-level classification for sensitive information
- **CONFIDENTIAL:** Standard classification for controlled information

These classifications are often paired with organizational units (e.g., Admins, QE_UIAuto_Team) to control access and information flow within the system.

---

### 7.6 Advanced Analysis Capabilities

The system provides sophisticated analysis tools:

- **Master Reports:**
  - Aggregate data from multiple intelligence sources
  - Automated cross-reference validation
  - Dynamic report updating based on new information
  - Collaborative editing and review workflows
  - Version control and change tracking

- **Search and Discovery:**
  - Advanced query language with boolean operations
  - Entity relationship visualization
  - Pattern detection and anomaly identification
  - Temporal analysis and timeline generation
  - Geospatial mapping and analysis

- **Event Processing:**
  - Real-time event correlation and analysis
  - Custom event type definitions and handlers
  - Automated event classification and prioritization
  - Event chain analysis and pattern detection

- **Data Visualization:**
  - Interactive dashboards and reports
  - Network analysis and link charts
  - Temporal and geospatial visualizations
  - Custom visualization templates

- **Workflow Automation:**
  - Automated data collection and processing
  - Configurable alert and notification systems
  - Task assignment and tracking
  - Integration with external systems

### 7.7 Reporting and Analysis

The system provides comprehensive reporting capabilities:

- **Master Reports:** Aggregate data from multiple sources
- **Search Functionality:** Advanced query language support
- **Event Feeds:** Filtered views of specific event types
- **Real-time Updates:** Continuous data processing and report updates

### 7.8 Security and Infrastructure

The application maintains robust security through:

- **Certificate Management:**
  - Client/Server keystores
  - Trust relationship configuration
  - Secure communication channels

- **Infrastructure Components:**
  - SMTP server (smtpext1.darkmatter.ae)
  - Jira integration (jira.pegasus.ae)
  - Docker registry (docker-registry.pegasus.ae)
  - Artifact repository (artifactory.pegasus.ae)

*Note: This analysis is based on technical implementation details found in the codebase.*

**Note:** All file paths are relative to the project root directory.

---

## 8. Git Repository Analysis

### 8.1 Core Functionality

- Generated test/mock data for a SIGINT (Signals Intelligence) system
- Focused on SMS message generation and target surveillance
- Created various types of records including:
  - FSMS (Forward SMS)
  - Target records
  - Subscriber records (Du and Etisalat - UAE telecom providers)
  - Phone book records

### 8.2 Key Findings

#### Data Generator Module Removal

**Commit Hash:** `dc1c6e113df68ca4c05f0abc5de984504f263ef0`  
**Author:** Aleksandr Dubatovka  
**Date:** October 29, 2018  
**Message:** "remove deprecated data-generator module"

#### Authentication System Changes

**Commit Hash:** `d04f7c8426811942aef361c55118c2ee64631f02`  
**Author:** Aleksandr Dubatovka  
**Date:** May 23, 2017  
**Message:** "Usage of user permissions authentication according of new Auth Service"

### 8.3 Detailed Analysis

#### DataGenerator.java

```java
public class DataGenerator implements DataGeneratorService {
    private Class<?> aClass;
    private DataGeneratorService dataGeneratorService;

    public Object produceSMSWithMention(String mention) {
        SMSTextProvider.setMention(mention);
        Object sms = new RandomEntities().randomEntity(aClass);
        SMSTextProvider.setMention("");
        return sms;
    }
}
```

#### FSMSGenerator.java

Specialized generator for Forward SMS messages with capabilities to:

- Generate messages from/to target phone numbers
- Insert target phone numbers in message content
- Include target keywords in messages
- Include target names in messages
- Generate cover traffic (messages without target information)

```java
class FSMSGenerator extends DataGenerator {
    @Override
    public List produceListByMatrix(GenerationMatrix generationMatrix) {
        // Generate messages based on target information
        for (GenerationMatrixRow row : generationMatrix.getRows()) {
            String phone = getTargetPhone(row.getTarget());

            // Generate messages from target
            // Generate messages to target
            // Generate messages mentioning target
            // Generate messages with target keywords
            // Generate messages with target names
            // Generate cover traffic
        }
    }
}
```

### 8.4 Subscriber Data Collection

#### Du Telecom Subscriber Data

Collected information includes:

- Phone number
- Personal information (name, address)
- Identification (visa, ID numbers)
- Service details
- Customer classification

```java
private String entryToString(DuSubscriberEntry entry) {
    return entry.getPhoneNumber() + DELIMETER +
           entry.getTitle() + DELIMETER +
           entry.getFirstName() + DELIMETER +
           entry.getVisaNumber() + DELIMETER +
           entry.getIdNumber() + DELIMETER +
           // ... other fields
}
```

#### Etisalat Subscriber Data

Collected information includes:

- Detailed personal information
- Installation location details
- Account status
- Service details
- IMSI number
- Identification documents

```java
private String entryToString(EtisalatSubscriberEntry entry) {
    return entry.getAction() + DELIMETER +
           entry.getPhoneNumber() + DELIMETER +
           entry.getImsi() + DELIMETER +
           entry.getIdentificationInfo() + DELIMETER +
           // ... other fields
}
```

### 8.5 Timeline of Significant Changes

1. **October 29, 2018:** Removal of data-generator module  
2. **May 23, 2017:** Major authentication system changes  
3. **Various Dates:** Multiple cleanup operations removing deprecated code

### 8.6 Technical Capabilities

#### SMS Surveillance

- Target-specific message generation
- Keyword monitoring
- Contact chaining
- Cover traffic generation

#### Subscriber Data Collection

- Comprehensive subscriber information from multiple telecoms
- Location tracking
- Service usage monitoring
- Identity documentation tracking

#### Target Management

- Target profiling
- Group-based targeting
- Keyword association
- Phone number tracking

### 8.7 Additional Implications

- The system appears to be designed for UAE telecommunications surveillance
- Heavy focus on SMS monitoring and subscriber data collection
- Sophisticated target management and grouping capabilities
- Integration with multiple telecom providers (Du, Etisalat)
- Module marked as "deprecated" and removed suggests possible transition to real data
- Functionality may have been moved elsewhere in the system

---

## Conclusion

Our investigation into the Crystal Ball system unearths a technology of profound capability—and equally profound consequence. By mapping out its distributed architecture, comprehensive data ingestion methods, and advanced analytical modules, we have highlighted not only the technical ingenuity behind such a platform but also the disturbing potential for misuse. Despite DarkMatter’s dissolution in 2019, its legacy persists through its rebranded technologies and successor entities like G42, which continue to shape the contours of digital surveillance. This study underscores the urgent need for robust safeguards and transparent oversight mechanisms to protect individual privacy and human rights in an era where surveillance technology can be wielded on a global scale. As our technical analysis reveals, the future of surveillance is both here and evolving—demanding that policymakers, technologists, and civil society engage in an informed debate about the balance between security and freedom.

## References

```plaintext
recipients=Dzmitry.Ihnatsyeu@pegasus.ae, Aleksandr.Dubatovka@pegasus.ae, Aleh@pegasus.ae, SoonLee@pegasus.ae, victor.kukharchuk@pegasus.ae, Anton.Yakutovich@pegasus.ae,
from=Automation@pegasus.ae
smtp_gmail=smtp.gmail.com
smtp_pegasus=smtpext1.darkmatter.ae
port=587
account=CrystalBallAutomation@gmail.com
password=cbautomation
```

*Note: This analysis is based on technical implementation details found in the codebase.*

**Note:** All file paths are relative to the project root directory.
