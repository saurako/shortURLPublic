package orle.in.util

class InvalidEmailException (msg: String) extends Exception {
  val message = msg
  
  override def getMessage(): String = {
    return message
  }
}