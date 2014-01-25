
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
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
    	<meta charset="UTF-8">
    	<meta name="description" content=""""),_display_(Seq[Any](/*8.41*/Messages("application.description"))),format.raw/*8.76*/("""">		
		<meta itemprop="image" content="/images/grle-favicon.png">
        <title>"""),_display_(Seq[Any](/*10.17*/Messages("application.pagetitle"))),format.raw/*10.50*/("""</title>
        
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*12.54*/routes/*12.60*/.Assets.at("stylesheets/main.css"))),format.raw/*12.94*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*13.54*/routes/*13.60*/.Assets.at("fonts/typicons-2-0/font/typicons.min.css"))),format.raw/*13.114*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*14.54*/routes/*14.60*/.Assets.at("stylesheets/liveurl.css"))),format.raw/*14.97*/("""">
        
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*16.59*/routes/*16.65*/.Assets.at("images/grle-favicon.png"))),format.raw/*16.102*/("""">
        
        <script src=""""),_display_(Seq[Any](/*18.23*/routes/*18.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*18.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*19.23*/routes/*19.29*/.Assets.at("javascripts/spin.min.js"))),format.raw/*19.66*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*20.23*/routes/*20.29*/.Assets.at("javascripts/jquery.liveurl.js"))),format.raw/*20.72*/("""" type="text/javascript"></script>
    </head>
    <body>
    	<header>
    		<img src=""""),_display_(Seq[Any](/*24.18*/routes/*24.24*/.Assets.at("images/grle-logo-small.png"))),format.raw/*24.64*/(""""/>    		
    		 """),_display_(Seq[Any](/*25.9*/Messages("application.pagetitle"))),format.raw/*25.42*/("""
    		 <sup>"""),_display_(Seq[Any](/*26.14*/Messages("application.pagetitlestage"))),format.raw/*26.52*/("""</sup>   		    				
    	</header>
        """),_display_(Seq[Any](/*28.10*/content)),format.raw/*28.17*/("""
        
        <footer>
        	"""),_display_(Seq[Any](/*31.11*/Messages("application.copyright"))),format.raw/*31.44*/("""
        	
        </footer>
    </body>
</html>


"""))}
    }
    
    def render(title:String,content:Html): play.api.templates.HtmlFormat.Appendable = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 14 15:06:15 PST 2014
                    SOURCE: E:/ScalaDevelopment/shortURL/app/views/main.scala.html
                    HASH: 11b4ed2b688b37cc2e8dea9a7d1bfbfd5ce40d2b
                    MATRIX: 591->1|715->31|855->136|911->171|1029->253|1084->286|1191->357|1206->363|1262->397|1354->453|1369->459|1446->513|1538->569|1553->575|1612->612|1718->682|1733->688|1793->725|1863->759|1878->765|1945->810|2038->867|2053->873|2112->910|2205->967|2220->973|2285->1016|2410->1105|2425->1111|2487->1151|2540->1169|2595->1202|2645->1216|2705->1254|2785->1298|2814->1305|2887->1342|2942->1375
                    LINES: 20->1|23->1|30->8|30->8|32->10|32->10|34->12|34->12|34->12|35->13|35->13|35->13|36->14|36->14|36->14|38->16|38->16|38->16|40->18|40->18|40->18|41->19|41->19|41->19|42->20|42->20|42->20|46->24|46->24|46->24|47->25|47->25|48->26|48->26|50->28|50->28|53->31|53->31
                    -- GENERATED --
                */
            