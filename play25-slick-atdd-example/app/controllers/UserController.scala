package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{Action, Controller}

@Singleton
class UserController @Inject() extends Controller {
  def list = Action { implicit request =>
    Ok(views.html.user.list(Seq.empty))
  }

}
