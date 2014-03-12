package orle.in.util

class UnsafeURLException (msg: String) extends Exception {
  val message = msg
  
  override def getMessage(): String = {
    return message
  }
}

