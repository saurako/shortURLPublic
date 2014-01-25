
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
import org.bson.types.ObjectId
/**/
object error extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main("Short url")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""
	<section class="mainContent">
	
		<div class="error" id="orleerror">
			"""),_display_(Seq[Any](/*7.5*/message)),format.raw/*7.12*/("""
		</div>
		
	</section>
""")))})))}
    }
    
    def render(message:String): play.api.templates.HtmlFormat.Appendable = apply(message)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 14 15:06:14 PST 2014
                    SOURCE: E:/ScalaDevelopment/shortURL/app/views/error.scala.html
                    HASH: 6ffa4dacb925faee2cc8d438261d4c0968e2cdfd
                    MATRIX: 587->1|698->18|737->23|762->40|801->42|914->121|942->128
                    LINES: 20->1|23->1|25->3|25->3|25->3|29->7|29->7
                    -- GENERATED --
                */
            