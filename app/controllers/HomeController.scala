package controllers

import javax.inject._
import play.api.mvc._
import play.api.db._

@Singleton
class HomeController @Inject()(db: Database,val controllerComponents: ControllerComponents) extends BaseController {

      val conn = db.getConnection()
      val stmt = conn.createStatement()
      val res = stmt.executeQuery("SELECT * from members")

      var i = 0
      var name =new Array[String](4)
      var regno = new Array[Int](4)
      var indexno = new Array[String](4)

      while(res.next()){
        name(i) = res.getString("name")
        regno(i) = res.getInt("registrationno")
        indexno(i) = res.getString("indexNo")
        i = i + 1
      }
      conn.close()
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index( name,regno,indexno))
	}
}
