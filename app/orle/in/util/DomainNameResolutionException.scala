package orle.in.util

class DomainNameResolutionException  (msg: String) extends Exception {
  val message = msg
  
  override def getMessage(): String = {
    return message
  }
}