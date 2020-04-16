package react

import scalajs.js
import js.|
import js.JSConverters._

package object hotkeys {
  type KeySeq  = String | KeySequence
  type KeySpec = KeySeq | js.Array[String] | KeySequenceDesc

  implicit def seqToKeySpec(seq: Seq[String]): KeySpec =
    seq.toJSArray

  implicit def onKeyEventToKeySeq(onKeyEvent: OnKeyEvent): KeySeq =
    KeySequence(onKeyEvent.sequence, onKeyEvent.action)

  implicit def onKeyEventToKeySpec(onKeyEvent: OnKeyEvent): KeySpec =
    onKeyEventToKeySeq(onKeyEvent)
}
