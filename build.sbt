/*
 * Copyright (c) 2012-2019 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaUnidocPlugin, GhpagesPlugin)
  .settings(
    organization := "com.snowplowanalytics",
    name := "alex-custom-scala-maxmind-iplookups",
    version := "1.0.0",
    description := "Scala wrapper for MaxMind GeoIP2 library",
    scalaVersion := "2.11.8",
    javacOptions := BuildSettings.javaCompilerOptions,
    scalafmtOnCompile := true,
    assemblyMergeStrategy in assembly := {
      case PathList("org", "aopalliance", xs @ _*)      => MergeStrategy.last
      case PathList("javax", "inject", xs @ _*)         => MergeStrategy.last
      case PathList("javax", "servlet", xs @ _*)        => MergeStrategy.last
      case PathList("javax", "activation", xs @ _*)     => MergeStrategy.last
      case PathList("org", "apache", xs @ _*)           => MergeStrategy.last
      case PathList("com", "google", xs @ _*)           => MergeStrategy.last
      case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
      case PathList("com", "codahale", xs @ _*)         => MergeStrategy.last
      case PathList("com", "yammer", xs @ _*)           => MergeStrategy.last
      case "about.html"                                 => MergeStrategy.rename
      case "META-INF/ECLIPSEF.RSA"                      => MergeStrategy.last
      case "META-INF/mailcap"                           => MergeStrategy.last
      case "META-INF/mimetypes.default"                 => MergeStrategy.last
      case "plugin.properties"                          => MergeStrategy.last
      case "log4j.properties"                           => MergeStrategy.last
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )
  .settings(BuildSettings.publishSettings)
  .settings(BuildSettings.docSettings)
  .settings(BuildSettings.coverageSettings)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.maxmind,
      Dependencies.catsEffect,
      Dependencies.cats,
      Dependencies.lruMap,
      Dependencies.scalaCheck,
      Dependencies.specs2
    )
  )
