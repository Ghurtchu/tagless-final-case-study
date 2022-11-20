package v1

import v1.Expression.ExpressionOps._
import v1.Expression.{Add, Literal, Negate}

object Main extends scala.App {

  // 16 + (-(1 + 2)) = 16 + (-3) = 16 - 3 = 13

  // this is AST = Abstract Syntax Tree
  val expression: Expression =
    Add(
      Literal(16),
      Negate(
        Add(
          Literal(1),
          Literal(2)
        )
      )
    )

  expression.asString.show()
  expression.evaluate.show()

}
