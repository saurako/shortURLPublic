package orle.in.util

class DuplicateCustomCodeException (msg: String) extends Exception {
  val message = msg
  
  override def getMessage(): String = {
    return message
  }
}