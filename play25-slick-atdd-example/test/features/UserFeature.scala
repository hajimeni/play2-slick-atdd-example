package features

import controllers.UserController
import models.Tables.UsersRow
import org.scalatest.{BeforeAndAfterEachTestData, FeatureSpec, GivenWhenThen, MustMatchers, TestData, fixture}
import org.scalatestplus.play.OneAppPerSuite
import play.api.test._
import play.api.test.Helpers._

class UserFeature extends FeatureSpec with OneAppPerSuite with GivenWhenThen with MustMatchers
  with BeforeAndAfterEachTestData with fixture.FeatureSpec {



  feature("User management") {
    scenario("See user list") {
      val users = Seq(
        UsersRow(1, "John Doe", None),
        UsersRow(2, "Pole Smith", None),
        UsersRow(3, "Miku Hatsune", None)
      )

      Given(
        s"""Exists below 3 users.\n
          ||id|name|
          |${users.map(u => s"|${u.id}|${u.name}|").mkString("\n|")}
        """.stripMargin)
      setupDatabase()

      When("I access user list page.")
      val controller = new UserController()
      val result = controller.list.apply(FakeRequest())


      Then("I found 3 users.")
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
      val body = contentAsString(result)
      users.foreach{ u =>
        body must include (u.name)
      }
    }
  }

  override protected def beforeEach(testData: TestData): Unit = super.beforeEach(testData)
}
