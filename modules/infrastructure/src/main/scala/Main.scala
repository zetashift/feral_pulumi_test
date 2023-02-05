package test.infrastructure

import com.pulumi.Pulumi
import com.pulumi.asset.FileArchive
import com.pulumi.asset.AssetArchive
import com.pulumi.asset.FileAsset
import com.pulumi.aws.iam.Role
import com.pulumi.aws.iam.RoleArgs
import com.pulumi.aws.lambda.{Function, FunctionArgs}
import com.pulumi.Context

object Lambda:
  val rolePolicy = RoleArgs.builder().assumeRolePolicy(
      """
      {
        "Version": "2012-10-17",
        "Statement": [
          {
            "Action": "sts:AssumeRole",
            "Principal": {
              "Service": "lambda.amazonaws.com"
            },
            "Effect": "Allow",
            "Sid": ""
          }
        ]
      }
      """
    ).build()

  /**
    * Here we specify the details of our Lambda
    *   - IAM role
    *   - the code for the Lambda
    *   - the name of the handler
    *
    * @param ctx current Stack context
    */
  def createLambda(ctx: Context): Unit =
    val iamRole = Role("feralLambdaRole", rolePolicy)
    val codeArchive = FileArchive("modules/hello-feral/target/scala-3.2.1/npm-package")
    val functionArgs = FunctionArgs.builder().code(codeArchive).role(iamRole.arn()).handler("index.HelloHandler").runtime("nodejs18.x").build()
    val helloFeral = Function("FeralFunction", functionArgs)
    ctx.`export`("Function", helloFeral.arn())

/**
  * This will build the infrastructure for our stack
  */
@main def main: Unit =
  Pulumi.run(Lambda.createLambda)
