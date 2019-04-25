package features

import controllers.HomeController
import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers, WordSpecLike}
import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class HomeFeature extends FeatureSpec with OneAppPerTest with GivenWhenThen with MustMatchers {

  feature("I can view top page `/` ") {
    scenario("render the index page from a new instance of controller") {
      Given("Application is fake.")
      val controller = new HomeController

      When("I access root page.")
      val home = controller.index().apply(
        FakeRequest())

      Then("I see `Welcome to play`")
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    scenario("render the index page from the application") {
      Given("Controller injected.")
      val controller = app.injector.instanceOf[HomeController]

      When("I access root page.")
      val home = controller.index().apply(FakeRequest())

      Then("I see `Welcome to play`")
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    scenario("render the index page from the router") {
      Given("Controller router.")
      val request = FakeRequest(GET, "/").withHeaders("Host" -> "localhost")

      When("I access root page.")
      val home = route(app, request).get

      Then("I see `Welcome to play`")
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")

    }
  }
}
