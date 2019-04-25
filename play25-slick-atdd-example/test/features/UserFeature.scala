package features

import controllers.UserController
import models.Tables.UsersRow
import org.scalactic.source.Position
import org.scalatest._
import org.scalatestplus.play.OneAppPerSuite
import play.api.test._
import play.api.test.Helpers._

class UserFeature extends FeatureSpec with OneAppPerSuite with GivenWhenThen with MustMatchers
  with BeforeAndAfter with BeforeAndAfterAll {

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
      pending

    }
  }

  feature("Feature 1") {
    println("======== feature 1 start")
    scenario("Scenario 1-1") {
      "a" must be ("a")
      println("======== scenario 1-1")
    }
    scenario("Scenario 1-2") {
      "a" must be ("a")
      println("======== scenario 1-2")
    }
    println("======== feature 1 end")
  }

  feature("Feature 2") {
    println("======== feature 2 start")
    scenario("Scenario 2-1") {
      "a" must be ("a")
      println("======== scenario 2-1")
    }
    scenario("Scenario 2-2") {
      "a" must be ("a")
      println("======== scenario 2-2")
    }
    println("======== feature 2 end")
  }

  override protected def before(fun: => Any)(implicit pos: Position): Unit = {
    println("========== before")
  }


  override protected def after(fun: => Any)(implicit pos: Position): Unit = {
    println("========== after")
  }

  override protected def beforeAll(): Unit = {
    println("========== beforeAll")
  }
  override protected def afterAll(): Unit = {
    println("========== afterAll")
  }
}
