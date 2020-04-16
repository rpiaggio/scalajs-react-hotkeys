package react.hotkeys

import scalajs.js
import scala.scalajs.js.annotation.JSImport

// class HotKeysConfiguration(
//   val logLevel:         js.UndefOr[String]           = js.undefined,
//   val defaultKeyEvent:  js.UndefOr[String]           = js.undefined,
//   val defaultComponent: js.UndefOr[String]           = js.undefined,
//   val defaultTabIndex:  js.UndefOr[String]           = js.undefined,
//   val ignoreTags:       js.UndefOr[js.Array[String]] = js.undefined
// ) extends js.Object

@js.native
trait HotKeysConfiguration extends js.Object

object HotKeysConfigure {
  @JSImport("react-hotkeys", "configure")
  @js.native
  object configure extends js.Object {
    def apply(conf: HotKeysConfiguration): Unit = js.native
  }

  def apply(conf: HotKeysConfiguration): Unit = configure(conf)
}
