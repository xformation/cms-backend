import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Teacher entity.
 */
class TeacherGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
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

    val scn = scenario("Test the Teacher entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all teachers")
            .get("/api/teachers")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new teacher")
            .post("/api/teachers")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "teacherName":"SAMPLE_TEXT"
                , "teacherMiddleName":"SAMPLE_TEXT"
                , "teacherLastName":"SAMPLE_TEXT"
                , "fatherName":"SAMPLE_TEXT"
                , "fatherMiddleName":"SAMPLE_TEXT"
                , "fatherLastName":"SAMPLE_TEXT"
                , "spouseName":"SAMPLE_TEXT"
                , "spouseMiddleName":"SAMPLE_TEXT"
                , "spouseLastName":"SAMPLE_TEXT"
                , "motherName":"SAMPLE_TEXT"
                , "motherMiddleName":"SAMPLE_TEXT"
                , "motherLastName":"SAMPLE_TEXT"
                , "aadharNo":"SAMPLE_TEXT"
                , "dateOfBirth":"2020-01-01T00:00:00.000Z"
                , "placeOfBirth":"SAMPLE_TEXT"
                , "religion":"SAMPLE_TEXT"
                , "caste":"SAMPLE_TEXT"
                , "subCaste":"SAMPLE_TEXT"
                , "age":"0"
                , "sex":"SAMPLE_TEXT"
                , "bloodGroup":"SAMPLE_TEXT"
                , "address":"SAMPLE_TEXT"
                , "town":"SAMPLE_TEXT"
                , "state":"SAMPLE_TEXT"
                , "country":"SAMPLE_TEXT"
                , "pinCode":"SAMPLE_TEXT"
                , "teacherContactNumber":"SAMPLE_TEXT"
                , "alternateContactNumber":"SAMPLE_TEXT"
                , "teacherEmailAddress":"SAMPLE_TEXT"
                , "alternateEmailAddress":"SAMPLE_TEXT"
                , "relationWithStaff":"SAMPLE_TEXT"
                , "emergencyContactName":"SAMPLE_TEXT"
                , "emergencyContactMiddleName":"SAMPLE_TEXT"
                , "emergencyContactLastName":"SAMPLE_TEXT"
                , "emergencyContactNo":"SAMPLE_TEXT"
                , "emergencyContactEmailAddress":"SAMPLE_TEXT"
                , "uploadPhoto":"SAMPLE_TEXT"
                , "status":"SAMPLE_TEXT"
                , "employeeId":null
                , "designation":"SAMPLE_TEXT"
                , "staffType":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_teacher_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created teacher")
                .get("${new_teacher_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created teacher")
            .delete("${new_teacher_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
