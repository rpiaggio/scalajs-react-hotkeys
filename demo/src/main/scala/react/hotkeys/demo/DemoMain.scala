package react.hotkeys.demo

import org.scalajs.dom
import scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.hotkeys.HotKeys
import react.hotkeys._
import org.scalajs.dom.html

@JSExportTopLevel("DemoMain")
object DemoMain {
  @JSExport
  def main(): Unit = {
    HotKeysConfiguration(
      logLevel = LogLevel.Info,
      ignoreTags = List.empty[HtmlTagOf[_]],
      ignoreRepeatedEventsWhenKeyHeldDown = false
    ).apply()

    val keyMap =
      KeyMap(
        "UNDO"  -> List("meta+z", "ctrl+z"),
        "UP"    -> "up",
        "DOWN"  -> "down".on(KeyUp),
        "LEFT"  -> KeySequence("Move Left", "left"),
        "RIGHT" -> KeySequence("Move Right", List[KeySeq]("right", "shift+right".on(KeyUp))),
        "DONT"  -> "shift+1"
      )

    def undo(e: ReactKeyboardEvent) =
      e.preventDefaultCB *> Callback.log("UNDO!")

    val right = Callback.log("RIGHT!")

    val handlers =
      Handlers(
        "UNDO"  -> undo,
        "UP"    -> Callback.log("UP!"),
        "DOWN"  -> Callback.log("DOWN!"),
        "LEFT"  -> Callback.log("LEFT!"),
        "RIGHT" -> right,
        "DONT"  -> Callback(dom.window.alert("DON'T!"))
      )

    def renderSection(i: Int) =
      React.Fragment(
        <.input(^.tabIndex  := i + 1),
        <.div(^.tabIndex    := i + 2,
              ^.width       := "100px",
              ^.height      := "100px",
              ^.background  := "black"
        ),
        <.button(^.tabIndex := i + 3, "HELLO")
      )

    val ref = Ref[html.Element]

    val App =
      ScalaComponent
        .builder[Unit]("Hotkeys demo")
        .render(_ =>
          GlobalHotKeys(keyMap = KeyMap("HELP" -> "shift+?"),
                        handlers = Handlers("HELP" -> Callback(dom.window.alert("HELP!")))
          )(
            HotKeys(keyMap = keyMap,
                    handlers = handlers,
                    tag = <.span,
                    tabIndex = 1,
                    innerRef = ref
            )(
              // IgnoreKeys(only = "shift+1")(
              "WITH HOTKEYS:",
              renderSection(0)
              // )
            ),
            <.div(^.tabIndex := 3)(
              "WITHOUT HOTKEYS:",
              renderSection(3)
            )
          )
        )
        .componentDidMount(_ => ref.foreach(_.focus()))
        .build

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    App().renderIntoDOM(container)

    ()

  }
}
