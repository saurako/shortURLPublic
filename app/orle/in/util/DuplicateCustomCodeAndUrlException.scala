package orle.in.util

class DuplicateCustomCodeAndUrlException (msg: String) extends Exception {
  val message = msg
  
  override def getMessage(): String = {
    return message
  }
}