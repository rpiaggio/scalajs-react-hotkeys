package react.hotkeys

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import japgolly.scalajs.react._
import react.common._
import japgolly.scalajs.react.vdom.HtmlTagOf
import japgolly.scalajs.react.Ref
import japgolly.scalajs.react.{ facade => Raw }

final case class HotKeys(
  keyMap:                js.UndefOr[KeyMap] = js.undefined,
  handlers:              js.UndefOr[Handlers] = js.undefined,
  tag:                   js.UndefOr[HtmlTagOf[_]] = js.undefined,
  tabIndex:              js.UndefOr[Int] = js.undefined,
  allowChanges:          js.UndefOr[Boolean] = js.undefined,
  innerRef:              js.UndefOr[Ref.Handle[_]] = js.undefined,
  root:                  js.UndefOr[Boolean] = js.undefined,
  override val children: CtorType.ChildrenArgs = Seq.empty
) extends GenericComponentPC[HotKeys.HotKeysProps, HotKeys] {
  override protected def cprops                     = HotKeys.props(this)
  def withChildren(children: CtorType.ChildrenArgs) =
    copy(children = children)
  val component                                     = HotKeys.component
}

object HotKeys {

  @js.native
  trait HotKeysProps extends js.Object {
    var keyMap: js.UndefOr[js.Object]                = js.native
    var handlers: js.UndefOr[js.Object]              = js.native
    var component: js.UndefOr[String]                = js.native
    var tabIndex: js.UndefOr[Int]                    = js.native
    var allowChanges: js.UndefOr[Boolean]            = js.native
    var innerRef: js.UndefOr[Raw.React.RefHandle[_]] = js.native
    var root: js.UndefOr[Boolean]                    = js.native
  }

  def props(
    q: HotKeys
  ): HotKeysProps = {
    val p = (new js.Object).asInstanceOf[HotKeysProps]
    q.keyMap.foreach(v => p.keyMap = v.toJs)
    q.handlers.foreach(v => p.handlers = v.toJs)
    q.tag.foreach(v => p.component = v.name)
    q.tabIndex.foreach(v => p.tabIndex = v)
    q.allowChanges.foreach(v => p.allowChanges = v)
    q.innerRef.foreach(v => p.innerRef = v.raw)
    p.innerRef = q.innerRef.map((_.raw))
    q.root.foreach(v => p.root = v)
    p
  }

  @JSImport("react-hotkeys", "HotKeys")
  @js.native
  object RawComponent extends js.Object

  private val component =
    JsComponent[HotKeysProps, Children.Varargs, Null](RawComponent)
}
