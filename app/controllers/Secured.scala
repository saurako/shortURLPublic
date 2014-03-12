package controllers

import play.mvc._
import play.mvc.Http._
import play.mvc.Results._

object Secured extends Security.Authenticator {
  
  override def getUsername(ctx: Context): String = {
    return ctx.session().get("userName")
  }
  
  override def onUnauthorized(ctx: Context): SimpleResult = {
    return redirect(routes.Application.getSigninPage)
  }

}