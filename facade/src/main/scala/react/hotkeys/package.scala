package react

import scalajs.js
import js.|
import js.JSConverters._
import japgolly.scalajs.react._

package object hotkeys {
  type KeySeq  = String | KeyEvent
  type KeySpec = KeySeq | js.Array[String] | KeySequence

  implicit def seqToKeySpec(seq: Seq[String]): KeySpec =
    seq.toJSArray

  implicit def onKeyEventToKeySeq(onKeyEvent: OnKeyEvent): KeySeq =
    KeyEvent(onKeyEvent.sequence, onKeyEvent.action)

  implicit def onKeyEventToKeySpec(onKeyEvent: OnKeyEvent): KeySpec =
    onKeyEventToKeySeq(onKeyEvent)

  type Handler = js.Function1[ReactKeyboardEvent, Unit]

  implicit def unitFunctionToHandler(f: () => Unit): Handler =
    _ => f()
}
