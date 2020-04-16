package react.hotkeys

import scalajs.js
import js.|
import js.JSConverters._

@js.native
trait KeyEvent extends js.Object {
  val sequence: js.UndefOr[String] = js.native
  val action: js.UndefOr[String]   = js.native
}
object KeyEvent {
  def apply(sequence: String, action: String): KeyEvent =
    js.Dynamic.literal(sequence = sequence, action = action).asInstanceOf[KeyEvent]
}

sealed abstract class OnKeyEvent(val action: String) {
  val sequence: String
}
final case class OnKeyPress(sequence: String) extends OnKeyEvent("keypress")
final case class OnKeyDown(sequence:  String) extends OnKeyEvent("keydown")
final case class OnKeyUp(sequence:    String) extends OnKeyEvent("keyup")

@js.native
trait KeySequence extends KeyEvent {
  val name: js.UndefOr[String]                = js.native
  val sequences: js.UndefOr[js.Array[KeySeq]] = js.native
}
object KeySequence {
  def apply(name: String, sequence: String): KeySequence =
    js.Dynamic
      .literal(name = name, sequence = sequence)
      .asInstanceOf[KeySequence]

  def apply(name: String, sequence: String, action: String): KeySequence =
    js.Dynamic
      .literal(name = name, sequence = sequence, action = action)
      .asInstanceOf[KeySequence]

  def apply(name: String, sequences: Seq[KeySeq]): KeySequence =
    js.Dynamic
      .literal(name = name, sequences = sequences.toJSArray)
      .asInstanceOf[KeySequence]

  def apply(name: String, sequences: Seq[KeySeq], action: String): KeySequence =
    js.Dynamic
      .literal(name = name, sequences = sequences.toJSArray, action = action)
      .asInstanceOf[KeySequence]
}

class KeyMap {
  private val jsMap: js.Dictionary[KeySpec] = js.Dictionary[KeySpec]()

  def add(name: String, key: KeySpec): KeyMap = {
    jsMap.addOne(name -> key)
    this
  }

  def toJs: js.Object =
    jsMap.asInstanceOf[js.Object]
}

object KeyMap {
  def apply(maps: (String, KeySpec)*): KeyMap =
    maps.foldLeft(new KeyMap) { case (keyMap, (name, key)) => keyMap.add(name, key) }
}
