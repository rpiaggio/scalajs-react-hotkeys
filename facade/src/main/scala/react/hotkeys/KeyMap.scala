package react.hotkeys

import scalajs.js
import js.|
import js.JSConverters._

@js.native
trait KeySequence extends js.Object {
  val sequence: js.UndefOr[String] = js.native
  val action: js.UndefOr[String]   = js.native
}
object KeySequence {
  def apply(sequence: String, action: String): KeySequence =
    js.Dynamic.literal(sequence = sequence, action = action).asInstanceOf[KeySequence]
}

sealed abstract class OnKeyEvent(val action: String) {
  val sequence: String
}
final case class OnKeyPress(sequence: String) extends OnKeyEvent("keypress")
final case class OnKeyDown(sequence:  String) extends OnKeyEvent("keydown")
final case class OnKeyUp(sequence:    String) extends OnKeyEvent("keyup")

@js.native
trait KeySequenceDesc extends KeySequence {
  val name: js.UndefOr[String]                = js.native
  val sequences: js.UndefOr[js.Array[KeySeq]] = js.native
}
object KeySequenceDesc {
  def apply(name: String, sequence: String): KeySequenceDesc =
    js.Dynamic
      .literal(name = name, sequence = sequence)
      .asInstanceOf[KeySequenceDesc]

  def apply(name: String, sequence: String, action: String): KeySequenceDesc =
    js.Dynamic
      .literal(name = name, sequence = sequence, action = action)
      .asInstanceOf[KeySequenceDesc]

  def apply(name: String, sequences: Seq[KeySeq]): KeySequenceDesc =
    js.Dynamic
      .literal(name = name, sequences = sequences.toJSArray)
      .asInstanceOf[KeySequenceDesc]

  def apply(name: String, sequences: Seq[KeySeq], action: String): KeySequenceDesc =
    js.Dynamic
      .literal(name = name, sequences = sequences.toJSArray, action = action)
      .asInstanceOf[KeySequenceDesc]
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
