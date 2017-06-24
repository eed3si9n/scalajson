package specs

import org.scalacheck.Prop._
import utest._
import Generators._

import scalajson.ast.JArray
import scala.scalajs.js
import js.JSConverters._

object JArray extends TestSuite with UTestScalaCheck {

  val tests = TestSuite {
    "The JString value should" - {
      "convert toUnsafe" - toUnsafe
      "equals" - testEquals
    }
  }

  def toUnsafe =
    forAll { jArray: JArray =>
      val values = jArray.value.map(_.toUnsafe).toJSArray

      Utils.unsafeJValueEquals(
        jArray.toUnsafe,
        scalajson.ast.unsafe.JArray(values)
      )
    }.checkUTest()

  def testEquals =
    forAll { jArray: JArray =>
      scalajson.ast.JArray(jArray.value) == scalajson.ast.JArray(jArray.value)
    }.checkUTest()
}
