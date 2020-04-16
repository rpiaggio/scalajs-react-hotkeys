package react.hotkeys.demo

import org.scalajs.dom
import scalajs.js
import js.|
import js.annotation._
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.hotkeys.HotKeys
import scala.scalajs.js.JSON
import react.hotkeys._

@JSExportTopLevel("DemoMain")
object DemoMain {
  @JSExport
  def main(): Unit = {
    // val conf = new HotKeysConfiguration( /*ignoreTags = js.Array[String]()*/ )

    val conf =
      js.Dynamic.literal(ignoreTags = js.Array[String]()).asInstanceOf[HotKeysConfiguration]

    //println(JSON.stringify(conf))

    HotKeysConfigure(conf)

    val keyMap =
      KeyMap(
        "UNDO" -> List("meta+z", "ctrl+z"),
        "UP" -> "up",
        "DOWN" -> OnKeyUp("down"),
        "LEFT" -> KeySequenceDesc("Move Left", "left"),
        "RIGHT" -> KeySequenceDesc("Move Right", List[KeySeq]("right", OnKeyUp("shift+right")))
      )

    val handlers =
      Handlers(
        "UNDO" -> { e => e.preventDefault(); println("UNDO!") },
        "UP" -> (() => println("UP!")),
        "DOWN" -> (() => println("DOWN!")),
        "LEFT" -> (() => println("LEFT!")),
        "RIGHT" -> (() => println("RIGHT!"))
      )

    def renderSection(i: Int) = React.Fragment(
      <.input(^.tabIndex := i + 1),
      <.div(^.tabIndex := i + 2, ^.width := "100px", ^.height := "100px", ^.background := "black"),
      <.button(^.tabIndex := i + 3, "HELLO")
    )

    val App =
      <.div(
        HotKeys(keyMap = keyMap, handlers = handlers)(
          "WITH HOTKEYS:",
          renderSection(0)
        ),
        <.div(
          "WITHOUT HOTKEYS:",
          renderSection(3)
        )
      )

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    App.renderIntoDOM(container)

    ()

  }
}
