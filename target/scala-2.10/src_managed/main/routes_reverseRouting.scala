// @SOURCE:E:/ScalaDevelopment/shortURL/conf/routes
// @HASH:c791ba5bed193c53b9f4184c97714fc8cbe82906
// @DATE:Tue Jan 14 15:06:14 PST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import se.radley.plugin.salat.Binders._

import Router.queryString


// @LINE:12
// @LINE:9
// @LINE:7
// @LINE:6
package controllers {

// @LINE:12
class ReverseAssets {
    

// @LINE:12
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def getLongURL(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:9
def shorten(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "shorten")
}
                                                
    
}
                          
}
                  


// @LINE:12
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:12
class ReverseAssets {
    

// @LINE:12
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def getLongURL : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getLongURL",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:9
def shorten : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.shorten",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "shorten"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:12
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:12
class ReverseAssets {
    

// @LINE:12
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def getLongURL(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getLongURL(id), HandlerDef(this, "controllers.Application", "getLongURL", Seq(classOf[String]), "GET", """""", _prefix + """$id<[^/]+>""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:9
def shorten(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.shorten(), HandlerDef(this, "controllers.Application", "shorten", Seq(), "POST", """""", _prefix + """shorten""")
)
                      
    
}
                          
}
        
    