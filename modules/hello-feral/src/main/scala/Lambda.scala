package test.lambda

import cats.effect.IO
import feral.lambda.*

/**
  * A very simple "Hello World"
  */
object mySimpleHandler extends IOLambda.Simple[Unit, INothing]:
  def apply(event: Unit, context: Context[IO], init: Init): IO[Option[INothing]] = IO.none
