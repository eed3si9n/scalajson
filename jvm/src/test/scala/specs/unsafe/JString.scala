package specs.unsafe

import specs.Spec
import scalajson.ast.unsafe._

class JString extends Spec {
  def is =
    s2"""
  The unsafe.JString value should
    read a String $readStringJString
    convert toStandard $toStandard
  """

  def readStringJString = prop { s: String =>
    JString(s).value must beEqualTo(s)
  }

  def toStandard = prop { s: String =>
    JString(s).toStandard must beEqualTo(scalajson.ast.JString(s))
  }
}
