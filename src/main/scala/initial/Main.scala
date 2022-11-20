package initial

import initial.Expression.ExpressionOps._
import initial.Expression.{Add, Literal, Negate}

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

  // Why is it not so good?
  // If we need to add new expression suddenly everything needs to change
  // let's say if we add new case class then we we to change all the methods like: evaluate, show and etc.. (to handle new case)
  // so everything will be recompiled which is not good
}
