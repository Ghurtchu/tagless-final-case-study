
sealed abstract class Expression extends Product with Serializable

object Expression {
  final case class Literal(n: Int)                     extends Expression
  final case class Negate(e: Expression)               extends Expression
  final case class Add(e1: Expression, e2: Expression) extends Expression
}
