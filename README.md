# Feral and Pulumi

An example of deploying your [Lambda](https://aws.amazon.com/lambda/) using [Scala]( https://github.com/typelevel/feral) and [Pulumi](https://www.pulumi.com/), all in Scala!

# Getting Started

You'll need the following requirements:
- [sbt](https://docs.scala-lang.org/getting-started/index.html)
- Pulumi CLI
- AWS access tokens, which you can also configure using the [CLI](https://aws.amazon.com/cli/)

You can then deploy your stack with this command:

```sh
pulumi up
```