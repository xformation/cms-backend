import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Student entity.
 */
class StudentGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the Student entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJSON
        .check(header.get("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all students")
            .get("/api/students")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new student")
            .post("/api/students")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "studentName":"SAMPLE_TEXT"
                , "studentMiddleName":"SAMPLE_TEXT"
                , "studentLastName":"SAMPLE_TEXT"
                , "fatherName":"SAMPLE_TEXT"
                , "fatherMiddleName":"SAMPLE_TEXT"
                , "fatherLastName":"SAMPLE_TEXT"
                , "motherName":"SAMPLE_TEXT"
                , "motherMiddleName":"SAMPLE_TEXT"
                , "motherLastName":"SAMPLE_TEXT"
                , "studentAadharNo":"SAMPLE_TEXT"
                , "studentPanNo":"SAMPLE_TEXT"
                , "studentSocialSecurityNo":"SAMPLE_TEXT"
                , "studentTaxReferenceNo":"SAMPLE_TEXT"
                , "studentBplNo":"SAMPLE_TEXT"
                , "studentDrivingLicenseNo":"SAMPLE_TEXT"
                , "studentPassportNo":"SAMPLE_TEXT"
                , "dateOfBirth":"2020-01-01T00:00:00.000Z"
                , "placeOfBirth":"SAMPLE_TEXT"
                , "religion":"HINDU"
                , "caste":"GENERAL"
                , "subCaste":"SAMPLE_TEXT"
                , "age":"0"
                , "sex":"MALE"
                , "studentLocalAddress":"SAMPLE_TEXT"
                , "studentPermanentAddress":"SAMPLE_TEXT"
                , "city":"SAMPLE_TEXT"
                , "state":"SAMPLE_TEXT"
                , "country":"SAMPLE_TEXT"
                , "pinCode":"SAMPLE_TEXT"
                , "studentPrimaryCellNumber":"SAMPLE_TEXT"
                , "studentAlternateCellNumber":"SAMPLE_TEXT"
                , "studentLandLinePhoneNumber":"SAMPLE_TEXT"
                , "studentPrimaryEmailId":"SAMPLE_TEXT"
                , "studentAlternateEmailId":"SAMPLE_TEXT"
                , "relationWithStudent":"FATHER"
                , "emergencyContactName":"SAMPLE_TEXT"
                , "emergencyContactMiddleName":"SAMPLE_TEXT"
                , "emergencyContactLastName":"SAMPLE_TEXT"
                , "emergencyContactCellNumber":"SAMPLE_TEXT"
                , "emergencyContactLandLinePhoneNumber":"SAMPLE_TEXT"
                , "emergencyContactEmailId":"SAMPLE_TEXT"
                , "studentImagePath":"SAMPLE_TEXT"
                , "admissionNo":"SAMPLE_TEXT"
                , "enrollmentNo":"SAMPLE_TEXT"
                , "rollNo":"SAMPLE_TEXT"
                , "studentType":"REGULAR"
                , "fatherCellNumber":"SAMPLE_TEXT"
                , "fatherEmailId":"SAMPLE_TEXT"
                , "fatherOccupation":"SAMPLE_TEXT"
                , "fatherOfficeEmailId":"SAMPLE_TEXT"
                , "fatherOfficeAddress":"SAMPLE_TEXT"
                , "fatherOfficeCellNumber":"SAMPLE_TEXT"
                , "fatherOfficeLandLinePhoneNumber":"SAMPLE_TEXT"
                , "fatherAadharNo":"SAMPLE_TEXT"
                , "fatherPanNo":"SAMPLE_TEXT"
                , "fatherSocialSecurityNo":"SAMPLE_TEXT"
                , "fatherTaxReferenceNo":"SAMPLE_TEXT"
                , "fatherBplNo":"SAMPLE_TEXT"
                , "fatherDrivingLicenseNo":"SAMPLE_TEXT"
                , "fatherPassportNo":"SAMPLE_TEXT"
                , "fatherImagePath":"SAMPLE_TEXT"
                , "motherCellNumber":"SAMPLE_TEXT"
                , "motherEmailId":"SAMPLE_TEXT"
                , "motherOccupation":"SAMPLE_TEXT"
                , "motherOfficeEmailId":"SAMPLE_TEXT"
                , "motherOfficeAddress":"SAMPLE_TEXT"
                , "motherOfficeCellNumber":"SAMPLE_TEXT"
                , "motherOfficeLandLinePhoneNumber":"SAMPLE_TEXT"
                , "motherAadharNo":"SAMPLE_TEXT"
                , "motherPanNo":"SAMPLE_TEXT"
                , "motherSocialSecurityNo":"SAMPLE_TEXT"
                , "motherTaxReferenceNo":"SAMPLE_TEXT"
                , "motherBplNo":"SAMPLE_TEXT"
                , "motherDrivingLicenseNo":"SAMPLE_TEXT"
                , "motherPassportNo":"SAMPLE_TEXT"
                , "motherImagePath":"SAMPLE_TEXT"
                , "guardianName":"SAMPLE_TEXT"
                , "guardianMiddleName":"SAMPLE_TEXT"
                , "guardianLastName":"SAMPLE_TEXT"
                , "guardianAddress":"SAMPLE_TEXT"
                , "guardianCellNumber":"SAMPLE_TEXT"
                , "guardianLandLinePhoneNumber":"SAMPLE_TEXT"
                , "guardianEmailId":"SAMPLE_TEXT"
                , "guardianOccupation":"SAMPLE_TEXT"
                , "guardianOfficeEmailId":"SAMPLE_TEXT"
                , "guardianOfficeAddress":"SAMPLE_TEXT"
                , "guardianOfficeCellNumber":"SAMPLE_TEXT"
                , "guardianOfficeLandLinePhoneNumber":"SAMPLE_TEXT"
                , "guardianImagePath":"SAMPLE_TEXT"
                , "isGuardianSponsorAgency":"SAMPLE_TEXT"
                , "sponsorAgencyName":"SAMPLE_TEXT"
                , "sponsorAgencyRegistrationNo":"SAMPLE_TEXT"
                , "sponsorAgencyAddress":"SAMPLE_TEXT"
                , "sponsorAgencyCellNumber":"SAMPLE_TEXT"
                , "sponsorAgencyLandLineNumber":"SAMPLE_TEXT"
                , "sponsorAgencyEmailId":"SAMPLE_TEXT"
                , "sponsorAgencyAppointeeName":"SAMPLE_TEXT"
                , "sponsorAgencyAppointeeDesignation":"SAMPLE_TEXT"
                , "sponsorAgencyAppointeeCellNumber":"SAMPLE_TEXT"
                , "sponsorAgencyAppointeeLandLineNumber":"SAMPLE_TEXT"
                , "sponsorAgencyAppointeeEmailId":"SAMPLE_TEXT"
                , "sponsorAgencyAppointeeOfficeAddress":"SAMPLE_TEXT"
                , "isPhysicallyChallenged":"SAMPLE_TEXT"
                , "detailsOfDisability":"SAMPLE_TEXT"
                , "disabilityCertificateNo":"SAMPLE_TEXT"
                , "disabilityCertificateIssueAuthority":"SAMPLE_TEXT"
                , "disabilityCertificateIssueDate":"2020-01-01T00:00:00.000Z"
                , "percentagOfDisability":"0"
                , "bloodGroup":"APOSITIVE"
                , "vaccinationDetails":"SAMPLE_TEXT"
                , "otherMedicalDetails":"SAMPLE_TEXT"
                , "status":"ACTIVE"
                , "createdBy":"SAMPLE_TEXT"
                , "createdOn":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                , "updatedOn":"2020-01-01T00:00:00.000Z"
                , "comments":"SAMPLE_TEXT"
                , "departmentId":null
                , "branchId":null
                , "sectionId":null
                , "batchId":null
                }""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_student_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created student")
                .get("${new_student_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created student")
            .delete("${new_student_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) over (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
